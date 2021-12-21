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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
@Table(name = "SPRS_District")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class District extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320353513481521225L;
	
	@Column
	private String code;
	
	@Column
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id",referencedColumnName="id",nullable = false)
	@NotNull
	private City city;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	@JsonProperty("wards")
	private List<SubDistrict> subDistrict = new ArrayList<SubDistrict>();

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<SubDistrict> getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(List<SubDistrict> subDistrict) {
		this.subDistrict = subDistrict;
	}
	
	

	public District() {
		super();
	}

	public District(String code, String name, City city, List<SubDistrict> subDistrict) {
		super();
		this.code = code;
		this.name = name;
		this.city = city;
		this.subDistrict = subDistrict;
	}
	
	

}
