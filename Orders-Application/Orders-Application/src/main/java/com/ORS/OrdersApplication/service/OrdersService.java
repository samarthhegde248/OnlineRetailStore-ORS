package com.ORS.OrdersApplication.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ORS.OrdersApplication.entity.Orders;
import com.ORS.OrdersApplication.entity.OrdersStatus;
import com.ORS.OrdersApplication.repository.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	private OrdersRepository ordersRepo;
	
	public List<Orders> getAllOrders(){
		return this.ordersRepo.findAll();
	}
	
	public Orders getOrderById(String id) {
		return this.ordersRepo.findById(id).get();
	}
	
	public Orders saveOrder(Orders order) {
		return this.ordersRepo.save(order);
	}
	
	public void deleteOrderById(String id) {
		this.ordersRepo.deleteById(id);
	}
	
	public List<Orders> getAllOrdersByEmail(String email) {
		return this.ordersRepo.findByEmail(email);
	}
	
	public OrdersStatus getOrderLatestStatusById(String id) {
		List<OrdersStatus> statuses = this.ordersRepo.findById(id).get().getStatus();
		Collections.sort(statuses);
		return statuses.get(statuses.size()-1);
	}
	
	public boolean checkOrderExistsById(String id) {
		return this.ordersRepo.existsById(id);
	}
	
	public boolean checkOrderExistsByEmail(String email) {
		return this.ordersRepo.findByEmail(email).size()>0;
	}
}
