package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_Organization")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4715377594031347875L;
	
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "founded")
	//@JsonProperty("founded")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date founded;
	
	@Column(name = "description")
	@JsonProperty("description")
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName="id")
	@JsonProperty("address")
	private Address address;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonProperty("user")
	private List<User> user;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonProperty("request")
	private List<Request> request;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ReliefPoint> reliefs;

	public Organization() {
		super();
	}

	public Organization(String name, Date founded, String description, Address address, List<User> user,
			List<Request> request) {
		super();
		this.name = name;
		this.founded = founded;
		this.description = description;
		this.address = address;
		this.user = user;
		this.request = request;
	}

	/**
	 * @return the reliefs
	 */
	public List<ReliefPoint> getReliefs() {
		return reliefs;
	}

	/**
	 * @param reliefs the reliefs to set
	 */
	public void setReliefs(List<ReliefPoint> reliefs) {
		this.reliefs = reliefs;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}
	
	
	
}
