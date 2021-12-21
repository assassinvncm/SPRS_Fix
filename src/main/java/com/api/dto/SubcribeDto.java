package com.api.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.api.entity.Store;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubcribeDto {
	@JsonProperty("store_id")
	@NotEmpty
	private Long store_id;
	
	@JsonProperty("user_id")
	private Long user_id;
	
	@JsonProperty("user_subcribe")
	private List<StoreDto> storeSubcribe;

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<StoreDto> getStoreSubcribe() {
		return storeSubcribe;
	}

	public void setStoreSubcribe(List<StoreDto> storeSubcribe) {
		this.storeSubcribe = storeSubcribe;
	}
	
}
