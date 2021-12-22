package com.api.service;

import java.util.List;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchMapResponse;
import com.api.entity.User;

public interface MapService {
	
	/**
	 * find all points
	 * @param la
	 * @param lo
	 * @param radius
	 * @param typePoint
	 * @return
	 */
	List<MapPointsDto> findAllPoints(double la, double lo, double radius, String typePoint);
	
	/**
	 * search point or location
	 * @param text
	 * @param lati
	 * @param longti
	 * @param numberOfRecord
	 * @return
	 */
	List<SearchMapResponse> search(String text, double lati, double longti, int numberOfRecord);
	
	/**
	 * check point is belong user
	 * @param u
	 * @param p_id
	 * @param type
	 * @return
	 */
	boolean checkIsOwnPoint(User u ,Long p_id,String type);
	
}
