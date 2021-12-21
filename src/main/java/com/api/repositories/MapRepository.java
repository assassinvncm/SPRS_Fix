package com.api.repositories;

import java.util.List;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchGoongMap;

public interface MapRepository {

	List<Object[]> getPoints(double lati, double longti, double radius, String typePoint);

	List<Object[]> search(String text, double lati, double longti, int numberOfRecord);
}
