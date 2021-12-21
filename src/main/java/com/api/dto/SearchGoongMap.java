package com.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchGoongMap {
	
	@JsonProperty("predictions")
	private List<Prediction> predictions;
	@JsonProperty("executed_time")
	private int executed_time;
	@JsonProperty("status")
	private String status;
	public List<Prediction> getPredictions() {
		return predictions;
	}
	public void setPredictions(List<Prediction> predictions) {
		this.predictions = predictions;
	}
	public int getExecuted_time() {
		return executed_time;
	}
	public void setExecuted_time(int executed_time) {
		this.executed_time = executed_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
