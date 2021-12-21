package com.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminPushNotifcationRequest {
	
	@JsonProperty("title")
	@NotBlank
	private String title;
	
	@JsonProperty("message")
	@NotBlank
	private String message;
	
	@JsonProperty("groupUsers")
	private List<Long> groupUsers;
		
	@JsonProperty("city_id")
	private long city_id;
	
	@JsonProperty("district_id")
	private long district_id;
	
	@JsonProperty("subdistrict_id")
	private long subdistrict_id;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Long> getGroupUsers() {
		return groupUsers;
	}
	public void setGroupUsers(List<Long> groupUsers) {
		this.groupUsers = groupUsers;
	}
	public long getCity_id() {
		return city_id;
	}
	public void setCity_id(long city_id) {
		this.city_id = city_id;
	}
	public Long getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(long district_id) {
		this.district_id = district_id;
	}
	public long getSubdistrict_id() {
		return subdistrict_id;
	}
	public void setSubdistrict_id(long subdistrict_id) {
		this.subdistrict_id = subdistrict_id;
	}
	
}
