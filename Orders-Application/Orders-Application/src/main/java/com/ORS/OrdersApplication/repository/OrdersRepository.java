package com.ORS.OrdersApplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ORS.OrdersApplication.entity.Orders;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String> {
	List<Orders> findByEmail(String email);
}
