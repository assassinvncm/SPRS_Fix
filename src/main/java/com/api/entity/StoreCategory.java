package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class StoreCategory extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1593227020069587312L;
	
	@Column(name = "name")
	private String name;
//	cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER
	@ManyToMany(mappedBy = "store_category")
	@JsonIgnore
	private List<Store> category_store = new ArrayList<Store>();
	
	public List<Store> getCategory_store() {
		return category_store;
	}

	public void setCategory_store(List<Store> category_store) {
		this.category_store = category_store;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
