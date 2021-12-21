package com.api.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.Property;

public class PushNotificationRequest {
	
	@JsonProperty("lstToken")
	private List<String> lstToken;
	private String title;
	private String body;
	private Map<String, String> data;
	
	public List<String> getTarget() {
		return lstToken;
	}
	public void setTarget(List<String> lstToken) {
		this.lstToken = lstToken;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<String> getLstToken() {
		return lstToken;
	}
	public void setLstToken(List<String> lstToken) {
		this.lstToken = lstToken;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
	
	
}
