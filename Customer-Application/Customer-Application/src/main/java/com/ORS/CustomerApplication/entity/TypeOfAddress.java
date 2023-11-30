package com.ORS.CustomerApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "address_type", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
public class TypeOfAddress {
	@Id
	@NotNull
	private String type;

	public TypeOfAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeOfAddress(@NotNull String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TypeOfAddress [type=" + type + "]";
	}
	
}
