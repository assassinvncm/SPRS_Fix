package com.api.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SPRS_Address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3712148124115036984L;
	
	@Column(name = "addressLine")
	@JsonProperty("addressLine")
	private String addressLine;
	
	@Column(name = "GPS_Long")
	@JsonProperty("GPS_Long")
	private String GPS_Long;
	
	@Column(name = "GPS_Lati")
	@JsonProperty("GPS_Lati")
	private String GPS_Lati;
	
	@Column(updatable = false)
	public String create_by;
	
	@Column(updatable = false,columnDefinition = "TIMESTAMP")
	public Timestamp create_time;
	
	@Column
	public String modified_by;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Timestamp modified_date;
	
	@ManyToOne
	@JoinColumn(name = "subDistrict_id",referencedColumnName="id")
	SubDistrict subDistrict;
	

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getGPS_Long() {
		return GPS_Long;
	}

	public void setGPS_Long(String gPS_Long) {
		GPS_Long = gPS_Long;
	}

	public String getGPS_Lati() {
		return GPS_Lati;
	}

	public void setGPS_Lati(String gPS_Lati) {
		GPS_Lati = gPS_Lati;
	}
	
	

	public SubDistrict getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(SubDistrict subDistrict) {
		this.subDistrict = subDistrict;
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

	public Address() {
		super();
	}

	public Address(String city, String province, String district, SubDistrict subDistrict, String addressLine,
			String gPS_Long, String gPS_Lati) {
		super();
		this.subDistrict = subDistrict;
		this.addressLine = addressLine;
		GPS_Long = gPS_Long;
		GPS_Lati = gPS_Lati;
	}
	
	
	
	
}
