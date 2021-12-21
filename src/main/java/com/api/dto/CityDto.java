package com.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938900620983579241L;

	private long id;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public CityDto() {
		super();
	}

	public CityDto(long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	
	
	
}
