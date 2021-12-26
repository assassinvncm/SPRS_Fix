package com.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.AddressDto;
import com.api.dto.GrantAccessDto;
import com.api.dto.ImageDto;
import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.SearchFilterDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;

public interface ReliefPointService {
	
	/**
	 * 
	 * @return
	 */
	List<ReliefPointDto> getAllReliefPoint();
	
	/**
	 * 
	 * @return
	 */
	List<ReliefPointDto> getReliefPoints(Long uId,ReliefPointFilterDto reliefPointFilterDto);
	
	/**
	 * 
	 * @return
	 */
	List<ReliefPointDto> getEvent(Long uId,ReliefPointFilterDto reliefPointFilterDto);
	
	/**
	 * 
	 * @return
	 */
	Map<String, Object> getReliefPointsAdmin(Long oId, SearchFilterDto filter);
	
	/**
	 * 
	 * @param uId
	 * @param reliefPointFilterDto
	 * @return
	 */
	//PagingResponse<ReliefPointDto> getReliefPoints_v2(Long uId, ReliefPointFilterDto reliefPointFilterDto);
	
	/**
	 * get  reliefpoint by id
	 * @return
	 */
	ReliefPointDto getReliefPointById(Long id);
	/**
	 * get  reliefpoint by id
	 * @return
	 */
	ReliefPointDto getReliefPointByIdORG(Long id);
	
	/**
	 * 
	 * @return
	 */
	ReliefPointDto getReliefPointByIdAndUser(Long rpId, Long uId);
	
	/**
	 * 
	 * @return
	 */
	ReliefPoint getReliefPointByUser(User user);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<ReliefPoint> getReliefPointByLocation(Address address);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<ReliefPointDto> getReliefPointByArea(AddressDto addressDto);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return 
	 */
	ReliefPoint createReliefPoint(ReliefPointDto reliefPointDto, User user);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return 
	 */
	ReliefPoint createReliefPointAdmin(ReliefPointDto reliefPointDto, User user);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateReliefPoint(ReliefPointDto reliefPoint);
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateReliefPointORG(ReliefPointDto reliefPoint);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateReliefPointAdmin(ReliefPointDto reliefPoint);
	
	/**
	 * 
	 * @param reliefPoint
	 * @return
	 */
	ReliefPoint updateStatusReliefPoint(Long rId, int status);
	
	/**
	 * 
	 * @param rId
	 * 
	 */
	void deleteReliefPointById(Long rId);
	
	/**
	 * 
	 * @param rId
	 * 
	 */
	void deleteReliefPointEvent(Long rId);
	
	/**
	 * 
	 * @param MultipartFile, store_id
	 * @return
	 */
	ReliefPoint uploadReliefImg(ImageDto image);
	
	/**
	 * assign relief for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto assignRef(GrantAccessDto gdto);
	
	/**
	 * unassign relief for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto unAssignRef(GrantAccessDto gdto);
	
	/**
	 * unassign relief for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	List<User> getAllAssignUser(Long rp_id, String search);
	
	/**
	 * unassign relief for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	List<User> getAllUnassignUser(Long rp_id, String search);
}
