package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.OrganizationDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.Organization;
import com.api.service.OrganizationService;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/sprs/api/organization-manage")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/origanization", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOrganization() {
		List<Organization> orgs = organizationService.getAllOrganzization();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", orgs, null));
	}

	@RequestMapping(value = "/origanization/update", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> updateOrganization(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody OrganizationDto organizationDto) {
		UserDto useDto = userService.getUserbyToken(requestTokenHeader);
		// check access
		if (null == useDto.getOrganization() || useDto.getOrganization().getId() != organizationDto.getId()) {
			throw new AppException(405, "User haven't accesss to update");
		}
		organizationService.updateOrganzization(organizationDto);
		return ResponseEntity.ok(
				new SPRSResponse(Constants.SUCCESS, "Update information of orgnization successfull", "", null, null));
	}

	@RequestMapping(value = "/origanization/get-by-user", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getOrganizationById(@RequestHeader("Authorization") String requestTokenHeader) {
		UserDto useDto = userService.getUserbyToken(requestTokenHeader);
		// check access
		OrganizationDto orgDto = organizationService.getOrganizationByUser(useDto.getId());
		return ResponseEntity.ok(
				new SPRSResponse(Constants.SUCCESS, "Get organization by user successfull", "", orgDto, null));
	}
	
	@RequestMapping(value = "/common/origanization", method = RequestMethod.GET)
	public ResponseEntity<?> getOrganizationById(@RequestParam("id") Long id) {
		// check access
		OrganizationDto orgDto = organizationService.getOrganizationById(id);
		return ResponseEntity.ok(
				new SPRSResponse(Constants.SUCCESS, "Get organization by user successfull", "", orgDto, null));
	}
}
