package com.ORS.CustomerApplication.common;

public class InternalServerErrorException extends RuntimeException{
	public InternalServerErrorException(String msg) {
		super(msg);
	}
}
