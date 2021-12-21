package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.RequestDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.Group;
import com.api.entity.Request;
import com.api.entity.User;
import com.api.service.RequestService;
import com.api.service.UserService;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/request-manage")
public class RequestController {

	@Autowired
	RequestService requestService;

	@Autowired
	UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping("/request-organizationalAdmin")
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getRequestOrgAdmin(@RequestHeader("Authorization") String requestTokenHeader) {
		
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		List<Request> requests = requestService.getRequestbyOrganization(userDto.getOrganization().getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requests, null));
	}
	
	@RequestMapping("/request-organizationalAdmin/{status}")
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> filterRequestOrgAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@PathVariable("status") String status) {
		
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		List<RequestDto> requestDtos = requestService.filterRequestOrgAdmin(userDto.getOrganization().getId(),status);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requestDtos, null));
	}

	@RequestMapping("/request-systemAdmin")
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader) {
		
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		List<Request> requests = requestService.getRequestbySysAdmin(userDto.getGroups_user().get(0).getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requests, null));
	}
	
	@RequestMapping("/request-systemAdmin/{status}")
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> filterRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@PathVariable("status") String status, @RequestParam(required = false, value = "search", defaultValue = "") String search) {
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		
		List<RequestDto> requestDtos = requestService.filterRequestSysAdmin(userDto.getGroups_user().get(0).getId(),status, search);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get request success!", "", requestDtos, null));
	}

	@RequestMapping(value = "/admin/register/accept", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> acceptRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody List<Long> rId) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		requestService.acceptRequest(rId,userDto.getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Accept Request success!", "", null, null));
	}

	@RequestMapping(value = "/admin/register/reject", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> rejectRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody List<Long> rId) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		requestService.RejectRequest(rId,userDto.getId());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", null, null));
	}

	@RequestMapping(value = "/request-systemAdmin", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> updateRequestSysAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody Request request) {
		
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		Request req = requestService.handleRequest(request);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", req, null));
	}

	@RequestMapping(value = "/request-organizationalAdmin", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRequestOrgAdmin(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody Request request) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);

		Request req = requestService.handleRequest(request);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Request success!", "", req, null));
	}

}
