package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.SubDistrictDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.SubDistrict;
import com.api.repositories.AddressRepository;
import com.api.repositories.CityRepository;
import com.api.repositories.DistrictRepository;
import com.api.repositories.SubDistrictRepository;
import com.api.service.AddressService;
import com.common.utils.DateUtils;
import com.exception.AppException;

@Service
public class AddressSerivceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	SubDistrictRepository subDistrictRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public AddressDto getAddressById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		SubDistrict sb = null;
		List<SubDistrict> lstSb = null;
		if (0 != addressDto.getSubDistrict().getId()) {
			sb = subDistrictRepository.getById(addressDto.getSubDistrict().getId());
		} else {
			lstSb = subDistrictRepository
					.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
							addressDto.getCity().getName())
					.orElseThrow(() -> new AppException(403, "Address Not Exits In DB"));
			sb = lstSb.get(0);
		}

		// mapper
		Address address = new Address();
		address.setAddressLine(addressDto.getAddressLine1());
		address.setGPS_Lati(addressDto.getGPS_lati());
		address.setGPS_Long(addressDto.getGPS_long());
		address.setSubDistrict(sb);

		// save
		addressRepository.save(address);
	}

	@Override
	public Address mapAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		SubDistrict sb = null;
		List<SubDistrict> lstSb = null;
		if (0 != addressDto.getSubDistrict().getId()) {
			sb = subDistrictRepository.findById(addressDto.getSubDistrict().getId())
					.orElseThrow(() -> new AppException(403, "Id address not valid"));

		} else {
			lstSb = subDistrictRepository
					.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
							addressDto.getCity().getName())
					.orElseThrow(() -> new AppException(403, "Address Not Exits In DB"));
			if(lstSb.size() == 0) {
				throw new AppException(403, "Address Not Exits In DB");
			}
			sb = lstSb.get(0);
		}
		// mapper
		Address address = new Address();
		address.setId(addressDto.getId());
		address.setModified_date(DateUtils.getCurrentSqlDate());
		address.setAddressLine(addressDto.getAddressLine1());
		address.setGPS_Lati(addressDto.getGPS_lati());
		address.setGPS_Long(addressDto.getGPS_long());
		address.setSubDistrict(sb);

		// save
		return address;
	}

	@Override
	public AddressDto updateAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubDistrictDto getSubDistrictById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubDistrictDto> getSDByDistrictId(long id) {
		// TODO Auto-generated method stub
		List<SubDistrict> subDistricts = subDistrictRepository.findByDistrictId(id);

		List<SubDistrictDto> subDistrictDtos = subDistricts.stream().map(subDistrict -> {
			// return modelMapper.map(subDistrict, SubDistrictDto.class);
			SubDistrictDto subDistrictDto = new SubDistrictDto();
			subDistrictDto.setId(subDistrict.getId());
			subDistrictDto.setCode(subDistrict.getCode());
			subDistrictDto.setName(subDistrict.getName());
			return subDistrictDto;
		}).collect(Collectors.toList());
		return subDistrictDtos;
	}

	@Override
	public List<DistrictDto> getDistrictByCityId(long id) {
		// TODO Auto-generated method stub
		List<District> districts = districtRepository.findByCityId(id);

		List<DistrictDto> districtDtos = districts.stream().map(district -> {
			// return modelMapper.map(district, DistrictDto.class);
			DistrictDto dtsDto = new DistrictDto();
			dtsDto.setId(district.getId());
			dtsDto.setName(district.getName());
			dtsDto.setCode(district.getCode());
			return dtsDto;
		}).collect(Collectors.toList());

		return districtDtos;
	}

	@Override
	public CityDto getCityById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityDto> getAllCity() {
		// TODO Auto-generated method stub
		List<City> citys = cityRepository.findAll();

		List<CityDto> cityDtos = citys.stream().map(city -> {
			return modelMapper.map(city, CityDto.class);
		}).collect(Collectors.toList());
		return cityDtos;
	}

}
