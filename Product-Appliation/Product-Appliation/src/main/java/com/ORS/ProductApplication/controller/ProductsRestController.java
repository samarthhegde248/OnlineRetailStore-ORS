package com.ORS.ProductApplication.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ORS.ProductApplication.common.InternalServerErrorException;
import com.ORS.ProductApplication.common.NotFoundException;
import com.ORS.ProductApplication.entity.Inventory;
import com.ORS.ProductApplication.entity.ProductCategory;
import com.ORS.ProductApplication.entity.ProductPictures;
import com.ORS.ProductApplication.entity.Products;
import com.ORS.ProductApplication.service.InventoryService;
import com.ORS.ProductApplication.service.PicturesService;
import com.ORS.ProductApplication.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsRestController {
	@Value("${message}")
	private String welcomeMsg;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private PicturesService pictureService;
	
	@GetMapping(value="/welcome")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String showWelcomeMsg() {
		return this.welcomeMsg;
	}
	
	@GetMapping(value="/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Inventory> showAllInventory(){
		return this.inventoryService.getAllInventory();
	}
	
	@GetMapping(value="/inventory/product", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Inventory getInventoryByProductId(@RequestParam String id) {
		return this.inventoryService.findInventoryByProduct(this.getProductById(id).getId());
	}
	
	@GetMapping(value="/inventory/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Inventory> showPagedInventory(@RequestParam int pageNo, @RequestParam int pageSize){
		if(pageNo<1) {
			throw new InternalServerErrorException("Page number cannot be less than 1.");
		}else if(pageSize<1) {
			throw new InternalServerErrorException("Page size cannot be less than 1.");
		}
		return this.inventoryService.getInventoryByPage(pageNo, pageSize);
	}
	
	@GetMapping(value="/inventory/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Inventory showInventoryById(@PathVariable String id) {
		Inventory inventory = this.inventoryService.getInventoryById(id);
		if(inventory == null) {
			throw new NotFoundException("Inventory with ID " + id.toString() + " does not exists.");
		}
		return inventory;
	}
	
	@PostMapping(value="/inventory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Inventory saveInventory(@RequestBody Inventory inventory) {
		if(inventory == null || inventory.equals(null)) {
			throw new InternalServerErrorException("Inventory object cannot be null.");
		}else if(inventory.getProduct().equals(null) || inventory.getProduct()==null) {
			throw new InternalServerErrorException("Inventory's product object cannot be null.");
		}else if(inventory.getProduct().getName()==null || inventory.getProduct().getName().equals(null) || inventory.getProduct().getName().equalsIgnoreCase("")) {
			throw new InternalServerErrorException("Inventory's Product's name cannot be null.");
		}else if(inventory.getProduct().getProductDescription()==null || inventory.getProduct().getProductDescription().equals(null)) {
			throw new InternalServerErrorException("Inventory's Product's Description cannot be null.");
		}else if(inventory.getProduct().getProductDescription().getDefinition()==null || inventory.getProduct().getProductDescription().getDefinition().equals(null) || inventory.getProduct().getProductDescription().getDefinition().equals("")) {
			throw new InternalServerErrorException("Inventory's Product's Description definition cannot be null.");
		}else if(inventory.getProduct().getProductDescription().getDescription()==null || inventory.getProduct().getProductDescription().getDescription().equals(null) || inventory.getProduct().getProductDescription().getDescription().equals("")) {
			throw new InternalServerErrorException("Inventory's Product's Description description cannot be null.");
		}else if(inventory.getProduct().getProductDescription().getSpecification()==null || inventory.getProduct().getProductDescription().getSpecification().equals(null) || inventory.getProduct().getProductDescription().getSpecification().equals("")) {
			throw new InternalServerErrorException("Inventory's Product's Description specification cannot be null.");
		}else if(inventory.getProduct().getPictures().isEmpty() || inventory.getProduct().getPictures()==null) {
			throw new InternalServerErrorException("Inventory's Product's picture's list cannot be null.");
		}else if(inventory.getProduct().getPictures().size()>0) {
			int i = 1;
			for(ProductPictures picture : inventory.getProduct().getPictures()) {
				if(picture == null || picture.equals(null)) {
					throw new InternalServerErrorException("Inventory's Product's Picture object of index " + i + " cannot be null.");
				}else if(picture.getName()==null || picture.getName().equals(null) || picture.getName().equalsIgnoreCase("")) {
					throw new InternalServerErrorException("Inventory's Product's Picture's name of index " + i + " cannot be null.");
				}else if(picture.getLocation()==null || picture.getLocation().equals(null) || picture.getLocation().equalsIgnoreCase("")) {
					throw new InternalServerErrorException("Inventory's Product's Picture's location of index " + i + " cannot be null.");
				}
				i++;
			}
		}
		
		inventory.getProduct().setPictures(this.pictureService.addProductPicture(inventory.getProduct().getPictures()));
		inventory.setProduct(this.productsService.addProduct(inventory.getProduct()));
		return this.inventoryService.addInventory(inventory);
	}
	
	@DeleteMapping(value="/inventory/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteInventoryById(@PathVariable String id) {
		try {
			Inventory inventory = this.showInventoryById(id);
			for(ProductPictures picture: inventory.getProduct().getPictures()) {
			this.pictureService.deletePictureById(picture.getId());
			}
			this.productsService.deleteProductById(inventory.getProduct().getId());
			this.inventoryService.deleteInventoryById(id);
			return "Inventory with ID " + id + " has been deleted successfully.";
		}catch(Exception e) {
			throw new NotFoundException("Inventory with ID "+id+" does not exists.");
		}
	}
	
	@PutMapping(value="/inventory/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Inventory updateInventoryQuantityById(@PathVariable String id, @RequestParam long quantity) {
		Inventory inventory = this.showInventoryById(id);
		if(inventory==null) {
			throw new NotFoundException("Inventory with ID " + id.toString() + " does not exists.");
		}
		inventory.setQuantity(quantity);
		return this.inventoryService.updateInventory(inventory);
	}
	
	@PutMapping(value="/inventory/{id}/makeUnavailable")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Inventory makeInventoryUnavailableById(@PathVariable String id) {
		Inventory inventory = this.showInventoryById(id);
		if(inventory==null) {
			throw new NotFoundException("Inventory with ID " + id.toString() + " does not exists.");
		}
		inventory.setQuantity(0);
		inventory.setAvailability(false);
		return this.inventoryService.updateInventory(inventory);
	}
	
	@GetMapping(value="/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Products getProductById(@PathVariable String id) {
		Products product = this.productsService.getProductById(id);
		if(product == null) {
			throw new NotFoundException("Product with ID " + id.toString() + " does not exists.");
		}
		return product;
	}
	
	@GetMapping(value="/check/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public boolean checkProductById(@PathVariable String id) {
		return this.productsService.checkProductExistsById(id);
	}
	
}
