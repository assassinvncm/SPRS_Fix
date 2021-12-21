package com.api.dto;

import java.sql.Date;
import java.util.List;


import com.api.entity.Address;
import com.api.entity.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JsonInclude(value = Include.NON_NULL)
public class OrganizationDto {
	
	public OrganizationDto() {
		super();
	}

	public OrganizationDto(long id, String name, Date founded, String description, AddressDto address, List<RequestDto> request) {
		super();
		this.id = id;
		this.name = name;
		this.founded = founded;
		this.description = description;
		this.address = address;
		this.request = request;
	}
	
	private long id;
	
	private String name;
	
	private Date founded;

	private String description;
	
	private AddressDto address;
	
	private List<RequestDto> request;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public List<RequestDto> getRequest() {
		return request;
	}

	public void setRequest(List<RequestDto> request) {
		this.request = request;
	}
	
	
}
