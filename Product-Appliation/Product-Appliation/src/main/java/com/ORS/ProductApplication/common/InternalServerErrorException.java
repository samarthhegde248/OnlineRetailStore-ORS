package com.ORS.ProductApplication.common;

public class InternalServerErrorException extends RuntimeException{
	public InternalServerErrorException(String msg) {
		super(msg);
	}
}
