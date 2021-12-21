package com.api.dto;

import java.util.HashMap;

public class SearchMapResponse {
	
	private String place_id;
	private String name;
	private String description;
	private HashMap<String, String> location;
	private String type;
	private String distance;
	
	public String getPlace_id() {
		return place_id;
	}
	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HashMap<String, String> getLocation() {
		return location;
	}
	public void setLocation(HashMap<String, String> location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
}
