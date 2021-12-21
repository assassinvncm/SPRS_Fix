package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SOSDto;
import com.api.dto.SPRSResponse;
import com.api.dto.StoreDto;
import com.api.dto.UserDto;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.SOSRepository;
import com.api.repositories.UserRepository;
import com.api.service.SOSService;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;

@RestController
@RequestMapping("sprs/api/sos-manage")
public class SOSController {
	
	public static Logger logger = LoggerFactory.getLogger(SOSController.class);

	@Autowired
	SOSService sosServ;
	
	@Autowired
	UserService userSerivce;
	
	@Autowired
	SOSRepository sosRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private MapStructMapper structMapper;
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_SOS')")
	public ResponseEntity<?> createStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody SOSDto s) {
		logger.info("Start update SOS");
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		
		SOSDto sdto = sosServ.updateStatusSOS(s, userDto);
		logger.info("End update SOS");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update SOS successfully", "", sdto, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_SOS')")
	public ResponseEntity<?> getAllStore(@RequestHeader ("Authorization") String requestTokenHeader) {
		logger.info("Start get SOS");
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		User u = userRepo.getById(userDto.getId());
		logger.info("End get SOS");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get SOS success", "", structMapper.SOSToSOSDto(u.getUser_sos()), null));
	}
	
	@RequestMapping(value = "/common/get", method = RequestMethod.GET)
	public ResponseEntity<?> getSOSDetail(@RequestParam("id") long id) {
		logger.info("Start get SOS common");
		SOSDto sosDto = sosServ.getSOSCommon(id);
		logger.info("End get SOS common");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get SOS success", "", sosDto, null));
	}
}
