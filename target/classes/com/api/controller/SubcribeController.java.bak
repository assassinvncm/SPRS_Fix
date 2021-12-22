package com.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.SubcribeDto;
import com.api.dto.UserDto;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("sprs/api/subcribe-manage")
public class SubcribeController {
	
	public static Logger logger = LoggerFactory.getLogger(SubcribeController.class);
	
	@Autowired
	UserService userSerivce;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/subcribe", method = RequestMethod.POST)
	public ResponseEntity<?> subcribeStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody SubcribeDto s) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_id(userDto.getId());
		
		SubcribeDto sdto = userSerivce.subcribeStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Subcribe store successfully", "", sdto, null));
	}
	
	@RequestMapping(value = "/unsubcribe", method = RequestMethod.POST)
	public ResponseEntity<?> unSubcribeStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody SubcribeDto s) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_id(userDto.getId());
		
		SubcribeDto sdto = userSerivce.unSubcribeStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Unsubcribe store successfully", "", sdto, null));
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getListSubcribe(@RequestHeader ("Authorization") String requestTokenHeader) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		SubcribeDto sdto = userSerivce.getListSubcribe(userDto.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get list subcribe store successfully", "", sdto, null));
	}

}
