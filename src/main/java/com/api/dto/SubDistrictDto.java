package com.api.dto;

import java.util.List;

import com.api.entity.District;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class SubDistrictDto {
	
	private long id;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
	@NotNull
	District district;
	
	List<AddressDto> address;

	public SubDistrictDto() {
		super();
	}

	public SubDistrictDto(long id, String code, String name, District district, List<AddressDto> address) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.district = district;
		this.address = address;
	}

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<AddressDto> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDto> address) {
		this.address = address;
	}
	
	
}
