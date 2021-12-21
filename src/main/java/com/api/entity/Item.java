package com.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item extends BaseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4881146337443164167L;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "Unit")
	private String unit;
	
	@Column(name = "Description")
	private String description;
	
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ReliefInformation> reliefInformation;

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

	public List<ReliefInformation> getReliefInformation() {
		return reliefInformation;
	}

	public void setReliefInformation(List<ReliefInformation> reliefInformation) {
		this.reliefInformation = reliefInformation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
