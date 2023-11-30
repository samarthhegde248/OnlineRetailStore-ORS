package com.ORS.CustomerApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String addressDetail;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="address_type", nullable = false)
	@JsonIgnore
	private TypeOfAddress type;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private Customers customer;
	
	@Transient
	private String email;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(long id, @NotNull String addressDetail, TypeOfAddress type, String email) {
		super();
		this.id = id;
		this.addressDetail = addressDetail;
		this.type = type;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public TypeOfAddress getType() {
		return type;
	}

	public void setType(TypeOfAddress type) {
		this.type = type;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressDetail=" + addressDetail + ", type=" + type + ", customer=" + customer
				+ ", email=" + email + "]";
	}
	
}
