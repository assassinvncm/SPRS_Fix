package com.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.SOSDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.SOSRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.SOSService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import com.ultils.Constants;

@Service
public class SOSServiceImpl implements SOSService{

	@Autowired
	SOSRepository sosRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	NotificationService notificationService;

	@Override
	public SOSDto updateStatusSOS(SOSDto sosDto, UserDto udto) {
		User u = userRepo.getById(udto.getId());
		if(null == u) {
			throw new AppException(402,"User is not Found!");
		}
		
		int statusSOSBF = u.getUser_sos().getStatus();
		
		Address address = addressService.mapAddress(sosDto.getAddress());
		SOS s = u.getUser_sos();
		s.setDescription(sosDto.getDescription());
		s.setAddress(address);
		s.setLevel(sosDto.getLevel());
		s.setStatus(sosDto.getStatus());
		s.setCreate_time(DateUtils.getCurrentSqlDate());
		u.setUser_sos(s);
		userRepo.save(u);
		
		if(statusSOSBF != sosDto.getStatus() && sosDto.getStatus() == Constants.SOS_STATUS_TURNON) {
			notificationService.sendPnsToDeviceWhenOpenSOS(u, "Có một địa điểm SOS được tạo gần bạn");
		}	
		return sosDto;
	}

	@Override
	public SOSDto getSOSCommon(long id) {
		User u = userRepo.getUserBySosId(id).orElseThrow(()-> new AppException(403,"SOS not exist"));
		
		SOSDto SOSDto = mapStructMapper.SOSToSOSDto(u.getUser_sos());
		UserDto uDto = new UserDto();
		uDto.setId(u.getId());
		uDto.setAddress(mapStructMapper.addressToAddressDto(u.getAddress()));
		uDto.setPhone(u.getPhone());
		uDto.setFull_name(u.getFull_name());
		SOSDto.setUser(uDto);
				
		return SOSDto;
	}

}
