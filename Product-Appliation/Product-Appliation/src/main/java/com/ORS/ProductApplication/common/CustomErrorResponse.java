package com.ORS.ProductApplication.common;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomErrorResponse {
	private String errorCode;
	private String errorResponse;
	private int status;
	@JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd hh:mm:ss")
	LocalDateTime timeStamp;
	public CustomErrorResponse(String errorCode, String errorResponse, int status, LocalDateTime timeStamp) {
		super();
		this.errorCode = errorCode;
		this.errorResponse = errorResponse;
		this.status = status;
		this.timeStamp = timeStamp;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "CustomErrorResponse [errorCode=" + errorCode + ", errorResponse=" + errorResponse + ", status=" + status
				+ ", timeStamp=" + timeStamp + "]";
	}
	
}
