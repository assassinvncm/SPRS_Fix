package com.api.service;

import com.api.dto.SOSDto;
import com.api.dto.UserDto;
import com.api.entity.User;

public interface SOSService {
	
	SOSDto updateStatusSOS(SOSDto dto, UserDto u);
	
	/**
	 * get SOS detail 
	 * @param id
	 * @return
	 */
	SOSDto getSOSCommon(long id);

}
