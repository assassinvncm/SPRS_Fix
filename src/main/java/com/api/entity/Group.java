package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "SPRS_Group")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group extends BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -70187117365177866L;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "level")
	private int level;

	@Column(name = "platform")
	private int platform;
	
	@ManyToMany//(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	@JoinTable(name = "SPRS_group_permission",
			joinColumns = @JoinColumn(name = "group_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	List<Permission> group_permission = new ArrayList<Permission>();

	@ManyToMany(mappedBy = "groups_user", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<User> users_groups = new ArrayList<User>();
	
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Request> request;
	
	public List<Permission> getPermissions() {
		return group_permission;
	}
	public void setPermissions(List<Permission> permissions) {
		this.group_permission = permissions;
	}
//	@JsonIgnore
//	public List<User> getUsers() {
//		return users_groups;
//	}
//	public void setUsers(List<User> users) {
//		this.users_groups = users;
//	}
	public String getName() {
		return name;
	}
	/**
	 * @return the platform
	 */
	public int getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Permission> getGroup_permission() {
		return group_permission;
	}
	public void setGroup_permission(List<Permission> group_permission) {
		this.group_permission = group_permission;
	}
	public List<User> getUsers_groups() {
		return users_groups;
	}
	public void setUsers_groups(List<User> users_groups) {
		this.users_groups = users_groups;
	}
	
}
