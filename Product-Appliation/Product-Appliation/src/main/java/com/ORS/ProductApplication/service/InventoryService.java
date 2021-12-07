package com.ORS.ProductApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ORS.ProductApplication.common.NotFoundException;
import com.ORS.ProductApplication.entity.Inventory;
import com.ORS.ProductApplication.entity.Products;
import com.ORS.ProductApplication.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepo;
	
	public List<Inventory> getAllInventory(){
		return this.inventoryRepo.findAll();
	}
	
	public Inventory getInventoryById(String id) {
		if(this.inventoryRepo.existsById(id)) {
		return this.inventoryRepo.findById(id).get();
		}else {
			return null;
		}
	}
	
	public long getInventoryCount() {
		return this.inventoryRepo.count();
	}
	
	public Inventory addInventory(Inventory inventory) {
		return this.inventoryRepo.save(inventory);
	}
	
	public void deleteInventoryById(String id) throws NotFoundException {
		if(this.inventoryRepo.existsById(id)) {
			this.inventoryRepo.deleteById(id);
		}else {
			throw new NotFoundException("Inventory with ID "+id+" does not exists.");
		}
	}
	
	public Inventory updateInventory(Inventory inventory) {
		return this.inventoryRepo.save(inventory);
	}
	
	public Inventory findInventoryByProduct(String productId) {
		try {
			return this.inventoryRepo.findByProductId(productId).get(0);
		}catch(Exception e) {
			throw new NotFoundException("Could not fetch Inventory using Product Id " + productId);
		}
	}
	
	public List<Inventory> getInventoryByPage(int pageNo, int pageSize){
//		need to handle pageNo >= 1 in calling class
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return this.inventoryRepo.findAll(pageable).getContent();
	}
}
