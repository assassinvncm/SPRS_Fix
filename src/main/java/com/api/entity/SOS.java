package com.api.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ultils.Ultilities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_SOS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SOS extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837417697393301503L;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@JsonIgnore
    private Address address;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@OneToMany(mappedBy = "sos", fetch = FetchType.LAZY)
    private List<Notification> notifications;
	
	public SOS() {
		super();
	}

	/**
	 * @return the create_time
	 */
	public Timestamp getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public SOS(String description, int level, Integer status, String gPS_Long, String gPS_Lati) {
		super();
		this.description = description;
		this.level = level;
		this.status = status;
	}

	public SOS(Integer status, Address address, int level) {
		super();
		this.status = status;
		this.address = address;
		this.level = level;
		this.create_time = Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy"));
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
	
}
