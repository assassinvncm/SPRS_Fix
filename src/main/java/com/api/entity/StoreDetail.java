package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StoreDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1593227020069587312L;
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "store_category")
	@JsonIgnore
	private List<Store> storePoint = new ArrayList<Store>();
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private String quantity;

	public List<Store> getStorePoint() {
		return storePoint;
	}

	public void setStorePoint(List<Store> storePoint) {
		this.storePoint = storePoint;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}
