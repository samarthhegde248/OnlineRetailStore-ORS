package com.ORS.ProductApplication.entity;

import jakarta.validation.constraints.NotNull;

public class ProductDescription {
	@NotNull(message="Product's Description definition field must not be null")
	private String definition;
	@NotNull(message="Product's Description description field must not be null")
	private String description;
	@NotNull(message="Product's Description specification field must not be null")
	private String specification;
	
	
	public ProductDescription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductDescription(
			@NotNull(message = "Product's Description definition field must not be null") String definition,
			@NotNull(message = "Product's Description description field must not be null") String description,
			@NotNull(message = "Product's Description specification field must not be null") String specification) {
		super();
		this.definition = definition;
		this.description = description;
		this.specification = specification;
	}

	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	@Override
	public String toString() {
		return "ProductDescription [definition=" + definition + ", description=" + description + ", specification="
				+ specification + "]";
	}
	
}
