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

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "SPRS_Users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1845793972265729408L;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "phone")
	private String phone;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String full_name;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "isActive")
	private Boolean isActive;
	
	//@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",insertable = true, updatable = false)
	private Address address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization_id",referencedColumnName="id",insertable = true, updatable = false)
	private Organization organization;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SPRS_user_group",
			joinColumns = @JoinColumn(name = "user_id",insertable = true, updatable = false),
			inverseJoinColumns = @JoinColumn(name ="group_id"))
	private List<Group> groups_user;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Request> request;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<ReliefPoint> reliefPoints;
	
	@OneToMany(mappedBy="users")
    private List<Store> lstStore;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "SPRS_store_subcribe",
			joinColumns = @JoinColumn(name = "user_id",insertable = true, updatable = false),
			inverseJoinColumns = @JoinColumn(name ="store_id"))
	private List<Store> user_store;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sos_id")
	@JsonIgnore
    private SOS user_sos;
	
	@OneToMany(mappedBy = "sender_user",fetch = FetchType.LAZY)
	private List<Notification> notifications_sender;
	
	@ManyToMany(mappedBy = "receivers")
	private List<Notification> notification_receiver;
	
	@Column(updatable = false)
	public String create_by;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@Column
	public String modified_by;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Timestamp modified_date;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image images;
	
	@ManyToMany(mappedBy = "relief_user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<ReliefPoint> user_relief;
	
	/**
	 * @return the user_relief
	 */
	public List<ReliefPoint> getUser_relief() {
		return user_relief;
	}

	/**
	 * @param user_relief the user_relief to set
	 */
	public void setUser_relief(List<ReliefPoint> user_relief) {
		this.user_relief = user_relief;
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

	public List<Store> getLstStore() {
		return lstStore;
	}

	public SOS getUser_sos() {
		return user_sos;
	}

	public void setUser_sos(SOS user_sos) {
		this.user_sos = user_sos;
	}

	public void setLstStore(List<Store> lstStore) {
		this.lstStore = lstStore;
	}

//	@JsonIgnore
	public List<Group> getGroups_user() {
		return groups_user;
	}

	public List<Store> getUser_store() {
		return user_store;
	}

	public void setUser_store(List<Store> user_store) {
		this.user_store = user_store;
	}

	public User(String username, String phone, String password, String full_name, String dob, Address address,
		Date create_time, Boolean isActive, List<Group> groups_user) {
	super();
	this.username = username;
	this.phone = phone;
	this.password = password;
	this.full_name = full_name;
	this.dob = dob;
	this.address = address;
	this.isActive = isActive;
	this.groups_user = groups_user;
}

	public User() {
		super();
	}

	public void setGroups_user(List<Group> groups_user) {
		this.groups_user = groups_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}

	public List<ReliefPoint> getReliefPoints() {
		return reliefPoints;
	}

	public void setReliefPoints(List<ReliefPoint> reliefPoints) {
		this.reliefPoints = reliefPoints;
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

	@Override
	public String toString() {
		String rs = "Access Code is: ";
//		groups_user.forEach(r -> {
//			rs+=r.getCode();
//			r.getPermissions().forEach(p -> rs+=p.getCode());
//		});
		for (Group group : groups_user) {
			rs+=group.getCode()+" ,";
			for (Permission grouPermission : group.getPermissions()) {
				rs+=grouPermission.getCode()+" ,";
			}
		}
		return rs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
