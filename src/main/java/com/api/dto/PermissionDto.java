package com.api.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.api.entity.Group;

public class PermissionDto {
	
	private long id;

	private String name;

	private String code;

	private String to;

	private String icon;

	private List<PermissionChildrenDto> children = new ArrayList<PermissionChildrenDto>();

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<PermissionChildrenDto> getChildren() {
		return children;
	}

	public void setChildren(List<PermissionChildrenDto> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
