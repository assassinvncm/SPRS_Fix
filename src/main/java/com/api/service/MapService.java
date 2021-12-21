package com.api.service;

import java.util.List;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchMapResponse;

public interface MapService {

	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2);

	public List<?> findPointinRadius(double la, double lo, double radius, Class<?> destinationType);

	public List<MapPointsDto> findAllPoints(double la, double lo, double radius);

	public List<MapPointsDto> findAllPoints(double la, double lo, double radius, String typePoint);

	List<SearchMapResponse> search(String text, double lati, double longti, int numberOfRecord);
}
