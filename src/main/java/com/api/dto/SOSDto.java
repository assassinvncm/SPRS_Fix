package com.api.dto;

import java.io.Serializable;

import javax.persistence.Column;

import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class SOSDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541843061802968807L;
	
	private long id;

	@JsonProperty("description")
	private String description;

	@JsonProperty("level")
	private int level;

	@JsonProperty("status")
	private int status;
	
	@JsonProperty("address")
	@NotNull
	private AddressDto address;
	
	@JsonProperty("user")
	private UserDto user;

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
}
