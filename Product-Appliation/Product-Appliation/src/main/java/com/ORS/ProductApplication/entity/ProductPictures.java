package com.ORS.ProductApplication.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "products_pictures")
public class ProductPictures {
	@Id
	private String id;
	@NotNull(message="Picture's name field must not be null")
	private String name;
	@NotNull(message="Picture's location field must not be null")
	private String location;

	
	public ProductPictures() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductPictures(String id, @NotNull(message = "Picture's name field must not be null") String name,
			@NotNull(message = "Picture's location field must not be null") String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public ProductPictures(@NotNull(message = "Picture's name field must not be null") String name,
			@NotNull(message = "Picture's location field must not be null") String location) {
		super();
		this.name = name;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ProductImageObj [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
}
