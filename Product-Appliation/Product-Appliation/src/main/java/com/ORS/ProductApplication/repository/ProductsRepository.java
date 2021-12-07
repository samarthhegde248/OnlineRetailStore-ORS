package com.ORS.ProductApplication.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ORS.ProductApplication.entity.Products;

@Repository
public interface ProductsRepository extends MongoRepository<Products, String>{
	List<Products> findByRating(double rating);
	
	List<Products> findByName(String name);
//	findByNameStartsWith, findByNameAndPrice, findByNameOrPrice
	
	List<Products> findByCategoryDivisionTypeIsLike(String category);
	
//	@Query("{ \"name\" : \"?0\" }")
//	List<Products> getProductsByNativeQuery(String name);
}
