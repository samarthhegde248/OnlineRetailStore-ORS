package com.ORS.OrdersApplication.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ORS.OrdersApplication.clients.CustomersClient;
import com.ORS.OrdersApplication.clients.ProductsClient;
import com.ORS.OrdersApplication.common.InternalServerErrorException;
import com.ORS.OrdersApplication.common.NotFoundException;
import com.ORS.OrdersApplication.entity.Orders;
import com.ORS.OrdersApplication.entity.OrdersStatus;
import com.ORS.OrdersApplication.message.ProductsProducerService;
import com.ORS.OrdersApplication.model.customers.AddressVO;
import com.ORS.OrdersApplication.model.customers.CustomersVO;
import com.ORS.OrdersApplication.model.products.InventoryVO;
import com.ORS.OrdersApplication.model.products.ProductsVO;
import com.ORS.OrdersApplication.service.OrdersService;

@RestController
@RequestMapping(value = "/orders")
public class OrdersRestController {
	
	@Value("${message}")
	private String welcomeMsg;
	
	@Autowired
	private ProductsClient productsClient;
	
	@Autowired
	private CustomersClient customersClient;
	
	@Autowired
	private OrdersService orderService;
	
	private final ProductsProducerService kafkaProductsProducerService;
	
	@Autowired
	public OrdersRestController(ProductsProducerService kafkaProductsProducerService) {
		this.kafkaProductsProducerService = kafkaProductsProducerService;
	}
	
	List<String> orderStatus = Arrays.asList("incart", "ordered", "shipped", "delivered", "cancelled", "exchanged", "returned", "replaced");
	
	@GetMapping(value="/welcome")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String showWelcomeMsg() {
		this.kafkaProductsProducerService.sendMessage("welcome", this.welcomeMsg);
		return this.welcomeMsg;
	}
	
	@GetMapping(value = "/productsWelcome")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String showProductsWelcomeMsg() {
		return this.productsClient.getWelcomeMsg();
	}
	
	@GetMapping(value = "/customersWelcome")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String showCustomersWelcomeMsg() {
		return this.customersClient.getWelcomeMsg();
	}
	
	@GetMapping(value = "/")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Orders> getAllOrders() {
		 return this.orderService.getAllOrders();
	}
	
	@PostMapping(value = "/")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Orders saveOrder(@RequestBody Orders order) {
		CustomersVO customer;
		AddressVO address;
		ProductsVO product;
		InventoryVO inventory;
		if(order.getQuantity()<1) {
			throw new InternalServerErrorException("Quantity value cannot be less than 1.");
		}
		try {
			customer = this.customersClient.getCustomerByEmail(order.getEmail());
			address = this.customersClient.getCustomerAddressById(customer.getId(), order.getAddressId());
			inventory = this.productsClient.getInventoryByProduct(order.getProductId());
			product = inventory.getProduct();
		}catch(Exception e) {
			throw new InternalServerErrorException("Something went wrong during fetching product and customer information.");
		}
		if(inventory.getQuantity()<order.getQuantity()) {
			throw new InternalServerErrorException("Ordered product quantity value cannot be greater than available quantity in inventory.");
		}
		order.setProductId(product.getId());
		order.setEmail(customer.getEmail());
		order.setAddressId(address.getId());
		if(!this.orderStatus.contains(order.getStatus().get(0).getStatus())) {
			throw new InternalServerErrorException("Wrong order status value.");
		}
		this.kafkaProductsProducerService.sendMessage("decrementProductQuantity", inventory.getId().toString()+"-"+Integer.toString(order.getQuantity()));
		return this.orderService.saveOrder(order);
	}
	
	@GetMapping(value = "/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Orders getOrderById(@PathVariable String id) {
		if(this.orderService.checkOrderExistsById(id)){
			return this.orderService.getOrderById(id);
		}
		throw new NotFoundException("Order with id " + id + " not found.");
	}
	
	@PutMapping(value = "/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Orders updateOrderById(@PathVariable String id, @RequestBody Orders order) {
		if(!this.orderService.checkOrderExistsById(id)){
			throw new NotFoundException("Order with id " + id + " not found.");
		}
		return this.saveOrder(order);
	}
	
	@DeleteMapping(value = "/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteOrderById(@PathVariable String id) {
		if(!this.orderService.checkOrderExistsById(id)){
			throw new NotFoundException("Order with id " + id + " not found.");
		}
		this.orderService.deleteOrderById(id);
		return "Order with id " + id + " has been deleted successfully.";
	}
	
	@PutMapping(value = "/{id}/addStatus")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Orders addOrderStatus(@PathVariable String id, @RequestBody OrdersStatus status) {
		if(!this.orderStatus.contains(status.getStatus())) {
			throw new InternalServerErrorException("Wrong order status value.");
		}
		if(!this.orderService.checkOrderExistsById(id)){
			throw new NotFoundException("Order with id " + id + " not found.");
		}
		Orders updateOrder = this.orderService.getOrderById(id);
		updateOrder.addStatus(status);
		return this.orderService.saveOrder(updateOrder);
	}
	
	@GetMapping(value = "/{id}/status")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<OrdersStatus> getOrdersStatus(@PathVariable String id) {
		if(!this.orderService.checkOrderExistsById(id)){
			throw new NotFoundException("Order with id " + id + " not found.");
		}
		return this.orderService.getOrderById(id).getStatus();
	}
	
	@GetMapping(value = "/{id}/status/latest")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public OrdersStatus getLatestOrdersStatus(@PathVariable String id) {
		if(!this.orderService.checkOrderExistsById(id)){
			throw new NotFoundException("Order with id " + id + " not found.");
		}
		return this.orderService.getOrderLatestStatusById(id);
	}
}
