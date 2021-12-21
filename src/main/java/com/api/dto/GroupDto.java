package com.api.dto;


import java.util.List;

import com.api.entity.Permission;
import com.api.entity.Request;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDto {
	
	private long id;

	@JsonProperty("name")
	private String name;

	private int level;

	@JsonIgnore
	List<PermissionDto> group_permission;

	@JsonIgnore
	private List<UserDto> users_groups;

	@JsonIgnore
	private List<RequestDto> request;
	
	

	public GroupDto() {
		super();
	}

	public GroupDto(long id, String name, int level, List<PermissionDto> group_permission, List<UserDto> users_groups,
			List<RequestDto> request) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.group_permission = group_permission;
		this.users_groups = users_groups;
		this.request = request;
	}

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<PermissionDto> getGroup_permission() {
		return group_permission;
	}

	public void setGroup_permission(List<PermissionDto> group_permission) {
		this.group_permission = group_permission;
	}

	public List<UserDto> getUsers_groups() {
		return users_groups;
	}

	public void setUsers_groups(List<UserDto> users_groups) {
		this.users_groups = users_groups;
	}

	public List<RequestDto> getRequest() {
		return request;
	}

	public void setRequest(List<RequestDto> request) {
		this.request = request;
	}
	
	

}
