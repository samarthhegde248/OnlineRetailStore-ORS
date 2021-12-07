package com.ORS.ProductApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ORS.ProductApplication.entity.ProductPictures;

@Repository
public interface PicturesRepository extends MongoRepository<ProductPictures, String>{

}
