package com.ORS.OrdersApplication.model.customers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AddressVO {
	private long id;
	private String addressDetail;
	private TypeOfAddressVO type;
	private CustomersVO customer;
	private String email;
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
	public TypeOfAddressVO getType() {
		return type;
	}
	public void setType(TypeOfAddressVO type) {
		this.type = type;
	}
	public CustomersVO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomersVO customer) {
		this.customer = customer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "AddressVO [id=" + id + ", addressDetail=" + addressDetail + ", type=" + type + ", customer=" + customer
				+ ", email=" + email + "]";
	}
	
}
