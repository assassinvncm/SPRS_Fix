package com.api.entity;

import java.io.Serializable;
import java.sql.Date;
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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_Relief_Point")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ReliefPoint  extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4021755107320804060L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@Column(name = "open_time", columnDefinition = "TIMESTAMP")
	private Timestamp open_time;
	
	@Column(name = "close_time", columnDefinition = "TIMESTAMP")
	private Timestamp close_time;
	
	@Column(name = "status")
	private int status;
	
	@Column(updatable = false)
	public String create_by;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@Column
	public String modified_by;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Timestamp modified_date;

	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName="id", insertable = true, updatable = false)
	private User user;
	
	@OneToMany(mappedBy = "reliefPoint", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<ReliefInformation> reliefInformations;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@JsonIgnore
    private Address address;
	
	@OneToMany(mappedBy = "reliefPoint", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	@JsonIgnore
    private List<Notification> notifications;	
	
	@OneToMany(mappedBy = "reliefPoint", fetch = FetchType.LAZY)
	@JsonIgnore
	@JsonProperty("request")
	private List<Request> request;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image images;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization_id",referencedColumnName="id",insertable = true)
	private Organization organization;

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "SPRS_user_relief",
		joinColumns = @JoinColumn(name = "relief_id",insertable = true, updatable = false),
		inverseJoinColumns = @JoinColumn(name ="u_id"))
	private List<User> relief_user = new ArrayList<User>();

	/**
	 * @return the relief_user
	 */
	
	public List<User> getRelief_user() {
		return relief_user;
	}

	/**
	 * @param relief_user the relief_user to set
	 */
	public void setRelief_user(List<User> relief_user) {
		this.relief_user = relief_user;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the images
	 */
	public Image getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(Image images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Timestamp open_time) {
		this.open_time = open_time;
	}

	public Timestamp getClose_time() {
		return close_time;
	}

	public void setClose_time(Timestamp close_time) {
		this.close_time = close_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public User getUsers() {
		return user;
	}

	public void setUsers(User users) {
		this.user = users;
	}

	public User getUser_rp() {
		return user;
	}

	public void setUser_rp(User user_rp) {
		this.user = user_rp;
	}

	public List<ReliefInformation> getReliefInformations() {
		return reliefInformations;
	}

	public void setReliefInformations(List<ReliefInformation> reliefInformations) {
		this.reliefInformations = reliefInformations;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}
	
	
}
