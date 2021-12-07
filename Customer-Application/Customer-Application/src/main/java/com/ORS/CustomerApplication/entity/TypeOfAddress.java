package com.ORS.CustomerApplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

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
