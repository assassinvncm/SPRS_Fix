package com.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.dto.SPRSResponse;
import com.api.entity.Device;
import com.api.entity.User;
import com.api.service.DeviceService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/device")
public class DeviceController {

	public static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	@Autowired
	DeviceService deviceService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createDevice(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody DeviceDto deviceDto) {
		logger.info("Start get create Device");
		User user = userService.getUserbyTokenAuth(requestTokenHeader);

		DeviceDto d = deviceService.insertDevice(user, deviceDto);
		logger.info("End get create Device");
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", d, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDevice(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("serialNumber") String serial) {
		logger.info("Start delete Device");

		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		deviceService.deleteDeviceByUserIdAndSeri(user.getId(), serial);
		logger.info("End delete Device");
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", null, null));
	}

	@RequestMapping(value = "/update/token", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceToken(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestParam("token") String token) {
		logger.info("Start update Device Token");

		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		DeviceDto deviceDto = deviceService.updateDeviceToken(user.getId(), token);
		logger.info("End update Device Token");
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", deviceDto, null));
	}

	@RequestMapping(value = "/update/{serial}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDeviceAddress(@RequestHeader("Authorization") String requestTokenHeader,
			@RequestBody AddressDto addressDto, @PathVariable("serial") String serial) {
		logger.info("Start Device Address");
		DeviceDto deviceDto = deviceService.updateDeviceAddress(serial, addressDto);
		logger.info("End Device Address");
		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Update reliefpoint By ID " + "" + " success", "", deviceDto, null));
	}
}
