package com.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageDto {
	@JsonProperty("imageName")
	private String imageName;
	
	@JsonProperty("encodedImage")
	private String encodedImage;
	
	@JsonProperty("id")
	private Long id;
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the encodedImage
	 */
	public String getEncodedImage() {
		return encodedImage;
	}
	/**
	 * @param encodedImage the encodedImage to set
	 */
	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
