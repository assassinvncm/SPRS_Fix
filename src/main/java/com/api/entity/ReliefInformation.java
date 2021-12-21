package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sprs_relief_information")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReliefInformation extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991088729929895455L;

	@Column(name = "Quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "reliefPoint_id", referencedColumnName="id",nullable = false)
	private ReliefPoint reliefPoint;
	
	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName="id" , nullable = false)
	private Item item;

	
	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public ReliefPoint getReliefPoint() {
		return reliefPoint;
	}



	public void setReliefPoint(ReliefPoint reliefPoint) {
		this.reliefPoint = reliefPoint;
	}



	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}




	public ReliefInformation() {
		super();
	}



	public ReliefInformation(int quantity, ReliefPoint reliefPoint, Item item) {
		super();
		this.quantity = quantity;
		this.reliefPoint = reliefPoint;
		this.item = item;
	}
	
	
	
}
