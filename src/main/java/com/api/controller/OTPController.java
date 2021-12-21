package com.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.SmsPojo;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.api.service.OtpService;
import com.api.service.SmsService;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
public class OTPController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OtpService otpService;
	
	@Autowired
	SmsService smsService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/generateOtp", method = RequestMethod.POST)
	public ResponseEntity<?> generateOtp(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start generate OTP and send to: "+pojo.getTo());
//
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
//		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		User user = userService.getUserByPhone(pojo.getTo());
		
		int otp = otpService.generateOTP(user.getUsername());
		logger.info("OTP : " + otp +" of user: "+user.getUsername());
		pojo.setMessage(Constants.OTP_MESSAGE+otp);
		smsService.send(pojo);
		logger.info("End generate OTP and send to: "+pojo.getTo());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Send OTP Success!", "", null, null));
	}

	@RequestMapping(value = "/generateOtp-verify", method = RequestMethod.POST)
	public ResponseEntity<?> generateOtpVerify(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start generate OTP verify phone and send to: "+pojo.getTo());

		if(userService.checkRegisUser(pojo.getTo(), pojo.getUsername())) {
			throw new AppException(403,"Số điện thoại hoặc username đã được đăng ký!");
		}
		int otp = otpService.generateOTP(pojo.getTo());
		logger.info("OTP : " + otp +" for phone "+pojo.getTo());
		pojo.setMessage(Constants.OTP_MESSAGE+otp);
		smsService.send(pojo);
		logger.info("End generate OTP verify phone and send to: "+pojo.getTo());

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Send OTP Success!", "", null, null));
	}

	@RequestMapping(value = "/validateOtp-verify", method = RequestMethod.POST)
	public ResponseEntity<?> validateOtpVerify(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start validate Otp Verify");

		String message = "";
		
		int otpnum = pojo.getOtp();
		String username = pojo.getUsername();
		logger.info("Otp Number : " + otpnum +" of phone: "+pojo.getTo());

		//Validate the Otp 
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(pojo.getTo());

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(pojo.getTo());
					return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Xác minh OTP thành công", "", null, null));
				} else {
					message = "Mã xác minh OTP không đúng";
				}
			} else {
				message = "Mã xác minh hết hạn";
			}
		} else {
			message = "Không tìm thấy OTP của tài khoản";
		}
		logger.info("End validate Otp Verify");
		return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, message, "", null, null));
	}

	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST)
	public ResponseEntity<?> validateOtp(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start validate Otp");

		String message = "";

		int otpnum = pojo.getOtp();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String username = auth.getName();
		String username = userService.getUsernameByPhone(pojo.getTo());
		logger.info("Otp Number : " + otpnum +" of user: "+username);

		//Validate the Otp 
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Xác minh OTP thành công", "", null, null));
				} else {
					message = "Mã xác minh OTP không đúng";
				}
			} else {
				message = "Mã xác minh hết hạn";
			}
		} else {
			message = "Không tìm thấy OTP của tài khoản";
		}
		logger.info("End validate Otp");
		return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, message, "", null, null));
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@Validated @RequestBody SmsPojo pojo) {
		logger.info("Start forgot Password");

		String message = "";
		int otpnum = pojo.getOtp();
		String username = userService.getUsernameByPhone(pojo.getTo());
		logger.info("Otp Number : " + otpnum +" of user: "+username);

		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					String new_pass = userService.generatePassword(8);
					User u = userService.findByUsername(username);
					u.setPassword(passwordEncoder.encode(new_pass));
					userRepository.save(u);
					pojo.setMessage(Constants.RESET_PASSWORD_MESSAGE+new_pass);
					smsService.send(pojo);
					return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Cấp lại mật khẩu thành công", "", null, null));
				} else {
					message = "Mã xác minh OTP không đúng";
				}
			} else {
				message = "Mã xác minh hết hạn";
			}
		} else {
			message = "Không tìm thấy OTP của tài khoản";
		}
		logger.info("End forgot Password");
		return ResponseEntity.ok(new SPRSResponse(Constants.FAILED, message, "", null, null));
	}
}
