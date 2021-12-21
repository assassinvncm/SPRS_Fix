package com.api.dto;

import java.io.Serializable;
import java.util.List;

public class SPRSResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3564179977498207994L;
	
	private String code;
	private String description;
	private String errors;
	
	private Object obj;
	
	private List lstObj;
	
	public SPRSResponse() {
		super();
	}
	public SPRSResponse(String code, String description, String errors, Object obj, List lstObj) {
		super();
		this.code = code;
		this.description = description;
		this.errors = errors;
		this.obj = obj;
		this.lstObj = lstObj;
	}
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public String getErrors() {
		return errors;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public List getLstObj() {
		return lstObj;
	}
	public void setLstObj(List lstObj) {
		this.lstObj = lstObj;
	}
	
	
}
