package com.exception;

public class AppException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5473247118246384585L;
	
	private int code;
    private String message;
    
	public AppException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public AppException() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}   
    
}
