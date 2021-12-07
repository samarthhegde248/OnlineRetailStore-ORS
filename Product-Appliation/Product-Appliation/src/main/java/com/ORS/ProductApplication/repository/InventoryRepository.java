package com.ORS.ProductApplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ORS.ProductApplication.entity.Inventory;
import com.ORS.ProductApplication.entity.Products;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
	List<Inventory> findByProductId(String productId);
}
