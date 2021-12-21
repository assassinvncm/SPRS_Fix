package com.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.api.dto.PermissionDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Permission")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permission extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085626722387918888L;
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "to_page")
	private String to_page;
	
	@Column(name = "icon_name")
	private String icon_name;
	
	@Column(name = "node_index")
	private int node_index;
	
	@Column(name = "node_from")
	private int node_from;
	
	@Column(name = "node_to")
	private int node_to;
	
	@Column(name = "level")
	private int level;

//	private List<PermissionDto> children;

	@ManyToMany(mappedBy = "group_permission")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Group> groups_link = new ArrayList<Group>();

//	public List<PermissionDto> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<PermissionDto> children) {
//		this.children = children;
//	}

	public int getNode_index() {
		return node_index;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	public void setNode_index(int node_index) {
		this.node_index = node_index;
	}

	public void setNode_from(int node_from) {
		this.node_from = node_from;
	}

	public void setNode_to(int node_to) {
		this.node_to = node_to;
	}

	public String getTo_page() {
		return to_page;
	}

	public void setTo_page(String to_page) {
		this.to_page = to_page;
	}

	public String getIcon_name() {
		return icon_name;
	}

	public void setIcon_name(String icon_name) {
		this.icon_name = icon_name;
	}

	public Collection<Group> getGroups() {
		return groups_link;
	}

	public void setGroups(List<Group> groups) {
		this.groups_link = groups;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNode_from() {
		return node_from;
	}

	public int getNode_to() {
		return node_to;
	}
	
}
