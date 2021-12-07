package com.ORS.OrdersApplication.model.products;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductsVO {
	private String id;
	private String name;
	private Set<ProductCategoryVO> category;
	private double rating;
	private ProductDescriptionVO productDescription;
	private String price = "0";
	private List<ProductPicturesVO> pictures;
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
	public Set<ProductCategoryVO> getCategory() {
		return category;
	}
	public void setCategory(Set<ProductCategoryVO> category) {
		this.category = category;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public ProductDescriptionVO getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(ProductDescriptionVO productDescription) {
		this.productDescription = productDescription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<ProductPicturesVO> getPictures() {
		return pictures;
	}
	public void setPictures(List<ProductPicturesVO> pictures) {
		this.pictures = pictures;
	}
	@Override
	public String toString() {
		return "ProductsVO [id=" + id + ", name=" + name + ", category=" + category + ", rating=" + rating
				+ ", productDescription=" + productDescription + ", price=" + price + ", pictures=" + pictures + "]";
	}
	
}
