package com.api.dto;

public class PermissionChildrenDto {
	private String name;

	private String to;

	private String icon;

	public PermissionChildrenDto() {
		super();
	}

	public PermissionChildrenDto(String name, String to, String icon) {
		super();
		this.name = name;
		this.to = to;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
}
