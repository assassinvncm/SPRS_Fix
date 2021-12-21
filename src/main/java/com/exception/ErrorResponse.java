package com.exception;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;


//@JsonRootName("error")
public class ErrorResponse {
	
	public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
	
	public ErrorResponse(int code,String message, List<String> details) {
        super();
        this.code = code;
        this.message = message;
        this.details = details;
    }
	
	private int code;
 
    //General error message about nature of error
    private String message;
 
    //Specific errors in API request processing
    private List<String> details;

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

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
 
    //Getter and setters
    
    
}
