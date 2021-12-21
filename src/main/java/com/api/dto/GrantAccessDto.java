package com.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrantAccessDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4629837552875787400L;
	
	@JsonProperty("source_id")
	private Long source_id;
	
	@JsonProperty("target_id")
	private Long target_id;

	public Long getSource_id() {
		return source_id;
	}

	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}

	public Long getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Long target_id) {
		this.target_id = target_id;
	}
}
