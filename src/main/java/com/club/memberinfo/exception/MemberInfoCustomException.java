package com.club.memberinfo.exception;

import org.springframework.http.HttpStatus;

public class MemberInfoCustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;
	int statusCode;
	HttpStatus httpStatus;
	
	public MemberInfoCustomException(String mssg)
	{
		super(mssg);
	}
	public MemberInfoCustomException(String mssg,int statusCode,HttpStatus httpStatus)
	{
		super(mssg);
		this.message=mssg;
		this.statusCode=statusCode;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
	

}
