package com.api.dto;

import java.sql.Date;
import java.util.List;

import com.api.entity.Image;
import com.api.entity.Organization;
import com.api.entity.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//@JsonIgnoreProperties({"handler"})
public class UserDto {
	
	private long id;

	@JsonProperty("username")
	private String username;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@JsonProperty("full_name")
	private String full_name;
	
	@JsonProperty("dob")
	private String dob;

	private Date create_time;
	
	private String status;

	private Boolean isActive;
	
	private AddressDto address;
	
	private OrganizationDto organization;
	
	private List<GroupDto> groups_user;
	
	@JsonIgnore
	private List<RequestDto> request;
	
	@JsonIgnore
	private List<StoreDto> user_store;

	@JsonProperty("images")
    private Image images;
	

	public UserDto() {
		super();
	}

	

	public UserDto(long id, String username, String phone, String password, String full_name, String dob,
			Date create_time, Boolean isActive, AddressDto address, OrganizationDto organization,
			List<GroupDto> groups_user, List<RequestDto> request) {
		super();
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.full_name = full_name;
		this.dob = dob;
		this.create_time = create_time;
		this.isActive = isActive;
		this.address = address;
		this.organization = organization;
		this.groups_user = groups_user;
		this.request = request;
	}

	/**
	 * @return the images
	 */
	public Image getImages() {
		return images;
	}



	/**
	 * @param images the images to set
	 */
	public void setImages(Image images) {
		this.images = images;
	}



	public List<StoreDto> getUser_store() {
		return user_store;
	}

	public void setUser_store(List<StoreDto> user_store) {
		this.user_store = user_store;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}



	public OrganizationDto getOrganization() {
		return organization;
	}



	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}



	public List<GroupDto> getGroups_user() {
		return groups_user;
	}



	public void setGroups_user(List<GroupDto> groups_user) {
		this.groups_user = groups_user;
	}



	public List<RequestDto> getRequest() {
		return request;
	}



	public void setRequest(List<RequestDto> request) {
		this.request = request;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
