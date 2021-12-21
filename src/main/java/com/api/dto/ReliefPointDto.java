package com.api.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.api.entity.Image;
import com.api.entity.ReliefInformation;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class ReliefPointDto {

	private long id;

	@JsonProperty("name")
	@NotEmpty
	private String name;

	@JsonProperty("description")
	private String description;

	@NotEmpty
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date open_time;

	@NotEmpty
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Ho_Chi_Minh")
	private Date close_time;

	private int status;

	private UserDto user_rp;

	@JsonProperty("reliefInformations")
	private List<ReliefInformationDto> reliefInformations;

	@JsonProperty("lstUser_rp")
	private List<UserDto> lstUser_rp;

	@JsonProperty("address")
	@NotNull
	private AddressDto address;

	@JsonProperty("create_by")
	public String create_by;

	@JsonProperty("create_time")
	public String create_time;

	@JsonProperty("modified_by")
	public String modified_by;

	@JsonProperty("modified_date")
	public String modified_date;

	@JsonProperty("images")
    private Image images;

	public ReliefPointDto() {
		super();
	}

	public ReliefPointDto(long id, String name, String description, Date open_time, Date close_time, int status,
			UserDto user_rp, List<ReliefInformationDto> reliefInformations, AddressDto address) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.open_time = open_time;
		this.close_time = close_time;
		this.status = status;
		this.user_rp = user_rp;
		this.reliefInformations = reliefInformations;
		this.address = address;
	}

	/**
	 * @return the lstUser_rp
	 */
	public List<UserDto> getLstUser_rp() {
		return lstUser_rp;
	}

	/**
	 * @param lstUser_rp the lstUser_rp to set
	 */
	public void setLstUser_rp(List<UserDto> lstUser_rp) {
		this.lstUser_rp = lstUser_rp;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getOpen_time() {
		return open_time;
	}

	public void setOpen_time(Date open_time) {
		this.open_time = open_time;
	}

	public Date getClose_time() {
		return close_time;
	}

	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserDto getUser_rp() {
		return user_rp;
	}

	public void setUser_rp(UserDto user_rp) {
		this.user_rp = user_rp;
	}

	public List<ReliefInformationDto> getReliefInformations() {
		return reliefInformations;
	}

	public void setReliefInformations(List<ReliefInformationDto> reliefInformations) {
		this.reliefInformations = reliefInformations;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

}
