package com.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AddressDto;
import com.api.dto.SPRSResponse;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.SubDistrict;
import com.api.repositories.CityRepository;
import com.api.repositories.DistrictRepository;
import com.api.repositories.SubDistrictRepository;
import com.api.service.AddressService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultils.Constants;

@RestController
@RequestMapping("/utilities")
public class UtilitieController {

	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	SubDistrictRepository subDistrictRepository;
	
	@Autowired
	AddressService addressService;

	@RequestMapping("/loadData")
	public ResponseEntity<?> loadData() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String path = "E:\\Data FPT University\\Project_FPT_20210528\\SPRS\\Source\\Source_api\\File Provinces.json";
		List<City> someClassObject = mapper.readValue(new File(path), new TypeReference<List<City>>() {
		});
//		List<City> cv = setAss(someClassObject);
//		cv.stream().forEach(obj -> {
//			cityRepo.saveAndFlush(obj);
//		});
		
		saveAddress(someClassObject);
		return ResponseEntity.ok("suscess");
	}
	
	//@Transactional
	private void saveAddress(List<City> citys) {
		for (int i = 0; i < citys.size(); i++) {
			City city = cityRepo.saveAndFlush(citys.get(i));
			List<District> dis = citys.get(i).getDistricts();
			for (int j = 0; j <dis.size(); j++) {
				dis.get(j).setCity(city);
				District d = districtRepository.saveAndFlush(dis.get(j));
				List<SubDistrict> lstSubDis = dis.get(j).getSubDistrict().stream().map(subDistt ->{
					subDistt.setDistrict(d);
					return subDistt;
				}).collect(Collectors.toList());
				subDistrictRepository.saveAllAndFlush(lstSubDis);

			}
		}
	}

//	private List<City> setAss(List<City> citys) {
//		List<City> cs = citys;
//		for (int i = 0; i < cs.size(); i++) {
//			List<District> dis = cs.get(i).getDistricts();
//
//			for (int j = 0; j < dis.size(); j++) {
//				List<SubDistrict> sdis = dis.get(j).getSubDistrict();
//				for (int k = 0; k < sdis.size(); k++) {
//					//sdis.get(k).setDistrict(dis.get(j));
//					cs.get(i).getDistricts().get(j).getSubDistrict().get(k).setDistrict(dis.get(j));
//				}
//				//dis.get(i).setCity(cs.get(i));
//				cs.get(i).getDistricts().get(j).setCity(cs.get(i));
//
//			}
//		}
//		return cs;
//	}
	@RequestMapping(value = "/testSaveAddress", method = RequestMethod.POST)
	public ResponseEntity<?> saveAddress(@RequestBody AddressDto addressDto) throws JsonParseException, JsonMappingException, IOException {
		addressService.saveAddress(addressDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Save Address Success!", "", null, null));
	}
	

}
