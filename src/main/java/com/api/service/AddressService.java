package com.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.SubDistrictDto;
import com.api.entity.Address;
import com.api.entity.SubDistrict;

public interface AddressService {

	AddressDto getAddressById();

	void saveAddress(AddressDto addressDto);

	AddressDto updateAddress();

	SubDistrictDto getSubDistrictById();

	List<SubDistrictDto> getSDByDistrictId(long id);
	
	List<DistrictDto> getDistrictByCityId(long id);

	CityDto getCityById();

	List<CityDto> getAllCity();
	
	Address mapAddress(AddressDto addressDto);

}
