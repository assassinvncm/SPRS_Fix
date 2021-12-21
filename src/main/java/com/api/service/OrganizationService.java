package com.api.service;

import java.util.List;

import com.api.dto.OrganizationDto;
import com.api.entity.Organization;



public interface OrganizationService {
	
	List<Organization> getAllOrganzization();
	
	void updateOrganzization(OrganizationDto organizationDto);
	
	OrganizationDto getOrganizationByUser(long uId);
	
	OrganizationDto getOrganizationById(long OId);
	
}
