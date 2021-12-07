package com.ORS.OrdersApplication.clients;

import org.springframework.cloud.openfeign.FeignClient;

import com.ORS.OrdersApplication.service.ProductsClientService;

@FeignClient(name="ORS-Product-Application")
public interface ProductsClient extends ProductsClientService {

}
