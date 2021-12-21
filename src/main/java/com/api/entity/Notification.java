package com.api.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SPRS_Notification")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_user_id",referencedColumnName="id",insertable = true)
	private User sender_user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_store_id",referencedColumnName="id",insertable = true)
	private Store store;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_reliefpoint_id",referencedColumnName="id",insertable = true)
	private ReliefPoint reliefPoint;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_sos_id",referencedColumnName="id",insertable = true)
	private SOS sos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SPRS_user_receiver_notification",
			joinColumns = @JoinColumn(name = "notification_id",insertable = true),
			inverseJoinColumns = @JoinColumn(name ="reveiver_id"))
	private List<User> receivers;
	
	@Column
	private String type;
	
	@Column
	private String title;
	
	@Column
	private String message;
	
	@Column
	private String status;
	
	@Column(columnDefinition = "TIMESTAMP")
	public Timestamp create_time;


	public User getSender() {
		return sender_user;
	}

	public void setSender(User sender_user) {
		this.sender_user = sender_user;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public ReliefPoint getReliefPoint() {
		return reliefPoint;
	}

	public void setReliefPoint(ReliefPoint reliefPoint) {
		this.reliefPoint = reliefPoint;
	}

	public List<User> getReceiver() {
		return receivers;
	}

	public void setReceiver(List<User> receivers) {
		this.receivers = receivers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SOS getSos() {
		return sos;
	}

	public void setSos(SOS sos) {
		this.sos = sos;
	}
	
	
}
