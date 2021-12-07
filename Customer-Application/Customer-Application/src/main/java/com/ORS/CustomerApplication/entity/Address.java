package com.ORS.CustomerApplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
