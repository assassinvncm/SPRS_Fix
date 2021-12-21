package com.api.entity;

import java.io.Serializable;
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
@Table(name = "SPRS_Store")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9208186724167391866L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;

	@Column(name = "open_time")
	private Time open_time;
	
	@Column(name = "close_time")
	private Time close_time;
	
	@Column(name = "status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	@JsonIgnore
    private Address location;
	
//	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
//	@JsonIgnore
//    private List<Image> lstImage;
//	cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id")
	private Image images;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SPRS_store_category",
			joinColumns = @JoinColumn(name = "store_id",insertable = true, updatable = false),
			inverseJoinColumns = @JoinColumn(name ="category_id"))
	private List<StoreCategory> store_category;

	@ManyToMany(mappedBy = "user_store", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<User> store_user = new ArrayList<User>();
	
	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Notification> notifications;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;

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

	public List<User> getStore_user() {
		return store_user;
	}

	public void setStore_user(List<User> store_user) {
		this.store_user = store_user;
	}

	public List<StoreCategory> getStore_category() {
		return store_category;
	}

	public void setStore_category(List<StoreCategory> store_category) {
		this.store_category = store_category;
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

	public Time getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Time open_time) {
		this.open_time = open_time;
	}

	public Time getClose_time() {
		return close_time;
	}

	public void setClose_time(Time close_time) {
		this.close_time = close_time;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Store [name=" + name + ", description=" + description + ", open_time=" + open_time + ", close_time="
				+ close_time + ", status=" + status + ", users=" + users + ", location=" + location  + ", store_category=" + store_category + ", store_user=" + store_user + "]";
	}
}
