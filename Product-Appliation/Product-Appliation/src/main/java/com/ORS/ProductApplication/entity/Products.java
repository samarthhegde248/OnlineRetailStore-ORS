package com.ORS.ProductApplication.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
public class Products {
	@Id
	private String id;
	
	@Field(name= "product_name")
	@NotNull(message="Product's name field must not be null")
	private String name;
	
	private Set<ProductCategory> category;
	
	private double rating;
	
	@Field(name="product_description")
	@NotNull(message="Product's Description field must not be null")
	private ProductDescription productDescription;
	
	private String price = "0";
	
	@DBRef(lazy = true)
	@NotNull(message="Product's picture field must not be null")
	private List<ProductPictures> pictures;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PersistenceConstructor
	public Products(String id, @NotNull(message = "Product's name field must not be null") String name,
			Set<ProductCategory> category, double rating,
			@NotNull(message = "Product's Description field must not be null") ProductDescription productDescription,
			String price,
			@NotNull(message = "Product's picture field must not be null") List<ProductPictures> pictures) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.productDescription = productDescription;
		this.price = price;
		this.pictures = pictures;
	}

	public Products(@NotNull(message = "Product's name field must not be null") String name,
			Set<ProductCategory> category, double rating,
			@NotNull(message = "Product's Description field must not be null") ProductDescription productDescription,
			String price,
			@NotNull(message = "Product's picture field must not be null") List<ProductPictures> pictures) {
		super();
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.productDescription = productDescription;
		this.price = price;
		this.pictures = pictures;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<ProductCategory> category) {
		this.category = category;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<ProductPictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<ProductPictures> pictures) {
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", category=" + category + ", rating=" + rating
				+ ", productDescription=" + productDescription + ", price=" + price + ", pictures=" + pictures + "]";
	}
	
}
