package com.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ImageDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SearchFilterDto;
import com.api.dto.StoreDto;
import com.api.entity.Address;
import com.api.entity.Store;
import com.api.entity.User;

public interface StoreService {
	/**
	 * 
	 * @return
	 */
	Store getStoreById(Long id);
	
	/**
	 * 
	 * @return
	 */
	Store getStoreByUser(User user);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<Store> getStoreByLocation(Address address);
	
	/**
	 * 
	 * @param location
	 * @return
	 */
	List<Store> getStoreByArea(Address address);
	
	/**
	 * 
	 * @param Store
	 * @return 
	 */

	Store createStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return 
	 */

	Store deleteStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store updateStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store openCloseStore(StoreDto s);
	
	/**
	 * 
	 * @param Store
	 * @return
	 */
	Store uploadStoreImg(ImageDto image);
	
	/**
	 * 
	 * @return
	 */
	List<StoreDto> getAllStore();
	
	/**
	 * 
	 * @return
	 */
	List<StoreDto> getStoreOwner();
	
	/**
	 * List<StoreFilterMapper>
	 * @return
	 */
	Map<String, Object> getStoreFilterByType(long user_id, SearchFilterDto filter);
	
	/**
	 * get infor store by store_id
	 * @param store
	 * @return
	 */
	Map<String, Object> getStoreCommon(long store_id, User u);
}
