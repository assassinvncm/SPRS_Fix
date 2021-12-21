package com.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.entity.User;
import com.ultils.Constants;

@RestController
public class TestCors {
	
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUser() {
		logger.info("Start get all User");
		return new ResponseEntity<String>("manh dep trai", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/test", method = RequestMethod.GET)
	public ResponseEntity<?> getAllssUser() {
		logger.info("Start get alls User");
		return new ResponseEntity<String>("manh dep trai 2", HttpStatus.OK);
	}
}