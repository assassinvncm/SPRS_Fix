package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Request")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Request extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3197715027374149935L;
	
	@Column
	private String type;
	
	@Column
	private String status;
	
	@Column
	private String message;
	
	@Column
	private Timestamp timestamp;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id",referencedColumnName="id")
	private User user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "group_id",referencedColumnName="id")
	private Group group;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "organization_id",referencedColumnName="id")
	private Organization organization;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "reliefPoint_id",referencedColumnName="id")
	private ReliefPoint reliefPoint;

	public Request(String type, String status, String message, Timestamp timestamp, User user, Group group,
			Organization organization) {
		super();
		this.type = type;
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.user = user;
		this.group = group;
		this.organization = organization;
	}

	public Request() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public ReliefPoint getReliefPoint() {
		return reliefPoint;
	}

	public void setReliefPoint(ReliefPoint reliefPoint) {
		this.reliefPoint = reliefPoint;
	}
	
	
	
	
	
	
}
