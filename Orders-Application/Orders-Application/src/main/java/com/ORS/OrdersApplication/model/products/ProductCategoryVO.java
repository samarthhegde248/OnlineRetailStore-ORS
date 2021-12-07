package com.ORS.OrdersApplication.model.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategoryVO {
	private String divisionType;

	public String getDivisionType() {
		return divisionType;
	}

	public void setDivisionType(String divisionType) {
		this.divisionType = divisionType;
	}

	@Override
	public String toString() {
		return "ProductCategoryVO [divisionType=" + divisionType + "]";
	}
	
}
