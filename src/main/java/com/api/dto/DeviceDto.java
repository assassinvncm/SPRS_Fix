package com.api.dto;

import javax.validation.constraints.NotBlank;

public class DeviceDto {
	
	private long id;
	
	@NotBlank
	private String token;
	
	@NotBlank
	private String serial;
	
    private UserDto user;
	
    @NotBlank
    private AddressDto address;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	
    
    
}
