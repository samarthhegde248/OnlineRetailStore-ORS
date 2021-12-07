package com.ORS.ProductApplication.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "products_inventory")
public class Inventory {
	@Id
	private String id;
	
	@DBRef
	@NotNull(message="Inventory's product field must not be null")
	private Products product;
	
	private long quantity = 0;
	
	@Transient
	private boolean availability;
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(String id, @NotNull(message = "Inventory's product field must not be null") Products product,
			long quantity, boolean availability) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.availability = availability;
	}

	public Inventory(@NotNull(message = "Inventory's product field must not be null") Products product, long quantity,
			boolean availability) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.availability = availability;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	public boolean isAvailability() {
		if(quantity > 0) {
			return true;
		}else {
			return false;
		}
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product=" + product + ", quantity=" + quantity + ", availability="
				+ availability + "]";
	}
	
}
