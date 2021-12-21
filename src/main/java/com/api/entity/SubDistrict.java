package com.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_SubDistrict")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubDistrict extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9193587263123659992L;
	
	@Column
	private String code;
	
	@Column
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "districts_id",referencedColumnName="id",nullable = false)
	District district;
	
	@OneToMany(mappedBy = "subDistrict", fetch = FetchType.LAZY)
	List<Address> address;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	

	public SubDistrict() {
		super();
	}

	public SubDistrict(String code, String name, District district, List<Address> address) {
		super();
		this.code = code;
		this.name = name;
		this.district = district;
		this.address = address;
	}
	
	

}
