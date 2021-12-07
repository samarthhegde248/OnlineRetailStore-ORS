package com.ORS.OrdersApplication.model.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InventoryVO {
	private String id;
	private ProductsVO product;
	private long quantity = 0;
	private boolean availability;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProductsVO getProduct() {
		return product;
	}
	public void setProduct(ProductsVO product) {
		this.product = product;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	@Override
	public String toString() {
		return "InventoryVO [id=" + id + ", product=" + product + ", quantity=" + quantity + ", availability="
				+ availability + "]";
	}
	
}
