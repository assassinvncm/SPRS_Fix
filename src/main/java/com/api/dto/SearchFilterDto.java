package com.api.dto;

import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchFilterDto {
	
	@JsonProperty("types")
	private List<Long> types;
	
	@JsonProperty("type")
	private Long type;
	
	@JsonProperty("status")
	private Boolean status;
	
	@JsonProperty("status_store")
	private int status_store;
	
	@JsonProperty("sort")
	private Boolean sort;
	
	@JsonProperty("pageSize")
	@NotNull
	private int pageSize;
	
	@JsonProperty("pageIndex")
	@NotNull
	private int pageIndex;
	
	@JsonProperty("search")
	private String search;

	
	
	public SearchFilterDto() {
		super();
	}

	public SearchFilterDto(List<Long> types, Boolean status, Boolean sort, @NotNull int pageSize,
			@NotNull int pageIndex) {
		super();
		this.types = types;
		this.status = status;
		this.sort = sort;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	public int getStatus_store() {
		return status_store;
	}

	public void setStatus_store(int status_store) {
		this.status_store = status_store;
	}

	public List<Long> getTypes() {
		return types;
	}

	public void setTypes(List<Long> types) {
		this.types = types;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getSort() {
		return sort;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
	
}
