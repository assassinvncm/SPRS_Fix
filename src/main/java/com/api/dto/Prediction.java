package com.api.dto;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class Prediction {

	@JsonProperty("description")
	private String description;
	@JsonProperty("place_id")
	private String place_id;
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("structured_formatting")
	private HashMap<String, String> structured_formatting;
	@JsonProperty("has_children")
	private Boolean has_children;
	@JsonProperty("display_type")
	private String display_type;
	@JsonProperty("score")
	private double score;
	@JsonProperty("plus_code")
	private HashMap<String, String> plus_code;
	
	private String type;
	
	private String location;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public HashMap<String, String> getStructured_formatting() {
		return structured_formatting;
	}

	public void setStructured_formatting(HashMap<String, String> structured_formatting) {
		this.structured_formatting = structured_formatting;
	}

	public Boolean getHas_children() {
		return has_children;
	}

	public void setHas_children(Boolean has_children) {
		this.has_children = has_children;
	}

	public String getDisplay_type() {
		return display_type;
	}

	public void setDisplay_type(String display_type) {
		this.display_type = display_type;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public HashMap<String, String> getPlus_code() {
		return plus_code;
	}

	public void setPlus_code(HashMap<String, String> plus_code) {
		this.plus_code = plus_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
