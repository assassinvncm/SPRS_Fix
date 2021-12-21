package com.api.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.api.entity.Item;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReliefInformationDto {
	
	private long id;
	
	@JsonProperty("quantity")
	private int quantity;
	
	private ReliefPointDto reliefPoint;
	
	@JsonProperty("item")
	private ItemDto item;
	
	

	public ReliefInformationDto() {
		super();
	}

	public ReliefInformationDto(long id, int quantity, ReliefPointDto reliefPoint, ItemDto item) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.reliefPoint = reliefPoint;
		this.item = item;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ReliefPointDto getReliefPoint() {
		return reliefPoint;
	}

	public void setReliefPoint(ReliefPointDto reliefPoint) {
		this.reliefPoint = reliefPoint;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto item) {
		this.item = item;
	}
	
	
}
