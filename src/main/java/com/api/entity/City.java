package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_City")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8862061711309048786L;
	
	@Column
	private String code;
	
	@Column
	private String name;
	
	@OneToMany( mappedBy = "city", fetch = FetchType.LAZY)
	List<District> districts = new ArrayList<District>();


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<District> getDistricts() {
		return districts;
	}


	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	
	public City() {
		super();
	}


	public City(String code, String name, List<District> districts) {
		super();
		this.code = code;
		this.name = name;
		this.districts = districts;
	}
	
	
	
}
