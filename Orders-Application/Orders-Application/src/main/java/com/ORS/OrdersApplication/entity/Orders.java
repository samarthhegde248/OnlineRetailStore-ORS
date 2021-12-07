package com.ORS.OrdersApplication.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ORS.OrdersApplication.model.customers.AddressVO;
import com.ORS.OrdersApplication.model.customers.CustomersVO;
import com.ORS.OrdersApplication.model.products.ProductsVO;

@Document(collection = "orders")
public class Orders {
	@Id
	private String id;
	private String productId;
	private long addressId;
	private String email;
	private int quantity;
	private List<OrdersStatus> status;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(String id, String productId, long addressId, String email, int quantity, List<OrdersStatus> status) {
		super();
		this.id = id;
		this.productId = productId;
		this.addressId = addressId;
		this.email = email;
		this.quantity = quantity;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrdersStatus> getStatus() {
		return status;
	}
	
	public void addStatus(OrdersStatus status) {
		this.status.add(status);
	}

	public void setStatus(List<OrdersStatus> status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", productId=" + productId + ", addressId=" + addressId + ", email=" + email
				+ ", quantity=" + quantity + ", status=" + status + "]";
	}
	
}
