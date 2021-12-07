package com.ORS.OrdersApplication.common;

public class InternalServerErrorException extends RuntimeException{
	public InternalServerErrorException(String msg) {
		super(msg);
	}
}
