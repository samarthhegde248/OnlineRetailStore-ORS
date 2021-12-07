package com.ORS.OrdersApplication.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Comparator;

public class OrdersStatus implements Comparable<OrdersStatus>{
	private String status;
	private Date timestamp;
	
	public OrdersStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersStatus(String status, Date timestamp) {
		super();
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "OrdersStatus [status=" + status + ", timestamp=" + timestamp + "]";
	}

	@Override
	public int compareTo(OrdersStatus o) {
		// TODO Auto-generated method stub
		return this.timestamp.compareTo(o.getTimestamp());
	}
}
