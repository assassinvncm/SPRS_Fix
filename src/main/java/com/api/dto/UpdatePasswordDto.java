package com.api.dto;

public class UpdatePasswordDto {
	
	private String oldPassword;
	private String newPassword;
	
	public UpdatePasswordDto() {
		super();
	}
	public UpdatePasswordDto(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
