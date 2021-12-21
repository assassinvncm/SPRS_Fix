package com.api.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Image")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Image extends BaseEntity{

	@Column(name = "img_url")
	private String img_url;

	
	public Image() {
		super();
	}

	public Image(String img_url) {
		super();
		this.img_url = img_url;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	
}
