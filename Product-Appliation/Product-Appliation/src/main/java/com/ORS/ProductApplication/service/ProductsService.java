package com.ORS.ProductApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ORS.ProductApplication.common.NotFoundException;
import com.ORS.ProductApplication.entity.ProductCategory;
import com.ORS.ProductApplication.entity.Products;
import com.ORS.ProductApplication.repository.ProductsRepository;


@Service
public class ProductsService {
	@Autowired
	private ProductsRepository productsRepo;
	
	public Products addProduct(Products product) {
		return this.productsRepo.save(product);
	}
	
	public void deleteProductById(String id) throws NotFoundException {
		if(this.productsRepo.existsById(id)) {
			this.productsRepo.deleteById(id);
		}else {
			throw new NotFoundException("Product with ID "+id+" does not exists.");
		}
	}
	
	public Products getProductById(String id) {
		if(this.productsRepo.existsById(id)) {
			return this.productsRepo.findById(id).get();
		}
		return null;
	}
	
	public Products updateProductById(String id, Products product) {
		if(this.productsRepo.existsById(id)) {
			Products updateProduct = this.productsRepo.findById(id).get();
			updateProduct.getCategory().addAll(product.getCategory());
			updateProduct.setName(product.getName());
			updateProduct.setPrice(product.getPrice());
			updateProduct.setPictures(product.getPictures());
			updateProduct.setProductDescription(product.getProductDescription());
			updateProduct.setRating(product.getRating());
			return this.productsRepo.save(updateProduct);
		}
		return null;
	}
	
	public Products deleteCategoryFromProductById(String id, ProductCategory category) {
		if(this.productsRepo.existsById(id)) {
			Products product = this.productsRepo.findById(id).get();
			if(product.getCategory().contains(category)) {
				product.getCategory().remove(category);
			}
			return this.productsRepo.save(product);
		}
		return null;
	}
	
	public List<Products> filterProductsByRating(double rating){
		return this.productsRepo.findByRating(rating);
	}
	
	public List<Products> sortProductsByName(String direction){
		Sort sort;
		if(direction.equalsIgnoreCase("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, "name");
		} else {
			sort = Sort.by(Sort.Direction.ASC, "name");
		}
		return this.productsRepo.findAll(sort);
	}
	
	public boolean checkProductExistsById(String id) {
		return this.productsRepo.existsById(id);
	}
}
