package com.api.dto;

import java.io.Serializable;


import com.api.entity.SubDistrict;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@JsonIgnoreProperties({"handler"})
public class AddressDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8177686290832133045L;

	private long id;
	
	@JsonProperty("city")
	@NotNull
	private CityDto city;
	
	@JsonProperty("district")
	@NotNull
	private DistrictDto district;
	
	@JsonProperty("subDistrict")
	@NotNull
	private SubDistrictDto subDistrict;
	
	@JsonProperty("addressLine")
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	private String addressLine2;
	
	@JsonProperty("GPS_long")
	private String GPS_long;
	
	@JsonProperty("GPS_lati")
	private String GPS_lati;

	public AddressDto() {
		super();
	}

	public AddressDto(CityDto city, DistrictDto district, SubDistrictDto subDistrict, String addressLine1, String addressLine2,
			String gPS_long, String gPS_lati) {
		super();
		this.city = city;
		this.district = district;
		this.subDistrict = subDistrict;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		GPS_long = gPS_long;
		GPS_lati = gPS_lati;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public DistrictDto getDistrict() {
		return district;
	}

	public void setDistrict(DistrictDto district) {
		this.district = district;
	}

	public SubDistrictDto getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(SubDistrictDto subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getGPS_long() {
		return GPS_long;
	}

	public void setGPS_long(String gPS_long) {
		GPS_long = gPS_long;
	}

	public String getGPS_lati() {
		return GPS_lati;
	}

	public void setGPS_lati(String gPS_lati) {
		GPS_lati = gPS_lati;
	}
	
	
}
