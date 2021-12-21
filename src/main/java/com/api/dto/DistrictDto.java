package com.api.dto;


import java.util.List;

import com.api.entity.SubDistrict;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DistrictDto {
	
	private long id;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
	private CityDto city;
	
	@JsonProperty("wards")
	private List<SubDistrict> subDistrict;

	public DistrictDto() {
		super();
	}

	public DistrictDto(long id, String code, String name, CityDto city, List<SubDistrict> subDistrict) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.city = city;
		this.subDistrict = subDistrict;
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

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public List<SubDistrict> getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(List<SubDistrict> subDistrict) {
		this.subDistrict = subDistrict;
	}
	
	
	
	
	
}
