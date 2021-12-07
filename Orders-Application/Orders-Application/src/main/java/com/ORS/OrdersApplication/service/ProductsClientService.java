package com.ORS.OrdersApplication.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ORS.OrdersApplication.model.products.InventoryVO;
import com.ORS.OrdersApplication.model.products.ProductsVO;

public interface ProductsClientService {
final static String PREFIX = "/products";
	
	@GetMapping(PREFIX+"/welcome")
	String getWelcomeMsg();
	
	@RequestMapping(value=PREFIX+"/inventory", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	List<InventoryVO> getInventory();
	
	@RequestMapping(value=PREFIX+"/inventory/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	InventoryVO getInventoryById(@PathVariable("id") String id);
	
	@GetMapping(PREFIX+"/{id}")
	ProductsVO getProductById(@PathVariable("id") String id);
	
	@GetMapping(PREFIX+"/check/{id}")
	boolean checkProductExistsById(@PathVariable("id") String id);
	
	@GetMapping(PREFIX+"/inventory/product")
	InventoryVO getInventoryByProduct(@RequestParam String id);
	
}
