package com.api.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.OrganizationDto;
import com.api.entity.Organization;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.service.OrganizationService;
import com.exception.AppException;

@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Override
	public List<Organization> getAllOrganzization() {
		// TODO Auto-generated method stub
		List<Organization> orgs = organizationRepository.findAll();
		List<OrganizationDto> orgDto = orgs.stream().map(org -> modelMapper.map(org, OrganizationDto.class)).collect(Collectors.toList()); 
		int x;
		return orgs;
	}

	@Override
	public void updateOrganzization(OrganizationDto organizationDto) {
		// TODO Auto-generated method stub
		Organization organization = mapStructMapper.organizationDtoToOrganization(organizationDto);
		organizationRepository.save(organization);
	}

	@Override
	public OrganizationDto getOrganizationByUser(long uId) {
		// TODO Auto-generated method stub
		 Organization organization = organizationRepository.findByUserId(uId).orElseThrow(() -> new AppException(402, "User not valid"));
		 OrganizationDto orgDto = mapStructMapper.organizationToOrganizationDto(organization);
		 orgDto.setAddress(mapStructMapper.addressToAddressDto(organization.getAddress()));
		return orgDto;
	}

	@Override
	public OrganizationDto getOrganizationById(long OId) {
		// TODO Auto-generated method stub
		Organization organization = organizationRepository.findById(OId).orElseThrow(() -> new AppException(402, "Point has removed"));
		OrganizationDto orgDto = mapStructMapper.organizationToOrganizationDto(organization);
		orgDto.setAddress(mapStructMapper.addressToAddressDto(organization.getAddress()));
		return orgDto;
	}

}
