package com.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto{
	
	private long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("unit")
	private String unit;
	
	private String description;
	
	@JsonIgnore
    private List<ReliefInformationDto> reliefInformation;

	public ItemDto() {
		super();
	}
	
	public ItemDto(String name, String unit, String description) {
		super();
		this.name = name;
		this.unit = unit;
		this.description = description;
	}

	public ItemDto(long id, String name, String unit, String description, List<ReliefInformationDto> reliefInformation) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.description = description;
		this.reliefInformation = reliefInformation;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ReliefInformationDto> getReliefInformation() {
		return reliefInformation;
	}

	public void setReliefInformation(List<ReliefInformationDto> reliefInformation) {
		this.reliefInformation = reliefInformation;
	}
	
	
}
