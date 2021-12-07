package com.ORS.OrdersApplication.clients;

import org.springframework.cloud.openfeign.FeignClient;

import com.ORS.OrdersApplication.service.CustomersClientService;

@FeignClient(name="ORS-Customer-Application")
public interface CustomersClient extends CustomersClientService {
	
}
