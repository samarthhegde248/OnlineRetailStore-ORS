package com.ORS.OrdersApplication.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ORS.OrdersApplication.model.customers.AddressVO;
import com.ORS.OrdersApplication.model.customers.CustomersVO;


public interface CustomersClientService {
final static String PREFIX = "/customers";
	
	@GetMapping(PREFIX+"/welcome")
	String getWelcomeMsg();
	
	@RequestMapping(value=PREFIX+"/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	List<CustomersVO> getAllCustomers();
	
	@RequestMapping(value=PREFIX+"/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	CustomersVO getCustomerById(@PathVariable("id") String id);
	
	@GetMapping(value = PREFIX+"/email")
	CustomersVO getCustomerByEmail(@RequestParam String email);
	
	@GetMapping(value = PREFIX+"/{id}/address/{adrId}")
	AddressVO getCustomerAddressById(@PathVariable long id, @PathVariable long adrId);
}
