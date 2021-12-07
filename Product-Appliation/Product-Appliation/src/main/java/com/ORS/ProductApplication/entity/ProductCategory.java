package com.ORS.ProductApplication.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class ProductCategory {
	@Field(name="division")
	private String divisionType;
	
	
	
	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(String divisionType) {
		super();
		this.divisionType = divisionType;
	}

	public String getDivisionType() {
		return divisionType;
	}

	public void setDivisionType(String divisionType) {
		this.divisionType = divisionType;
	}

	@Override
	public String toString() {
		return "ProductCategory [divisionType=" + divisionType + "]";
	}
	
	
	
}