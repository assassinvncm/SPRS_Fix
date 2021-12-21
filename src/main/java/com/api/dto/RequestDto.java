package com.api.dto;

import java.util.*;

public class RequestDto {
	
	private long id;

	private String type;

	private String status;

	private String message;

	private Date timestamp;

	private UserDto user;

	private GroupDto group;

	private OrganizationDto organization;
	
	
	public RequestDto() {
		super();
	}

	public RequestDto(long id, String type, String status, String message, Date timestamp, UserDto user, GroupDto group,
			OrganizationDto organization) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.user = user;
		this.group = group;
		this.organization = organization;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public GroupDto getGroup() {
		return group;
	}

	public void setGroup(GroupDto group) {
		this.group = group;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	
	
}
