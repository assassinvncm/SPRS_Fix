package com.common.utils;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OTPUltils {
	public static Logger logger = LoggerFactory.getLogger(OTPUltils.class);
	
	private static HashMap<String, String> otp_locale = new HashMap<String, String>();
	
	public static boolean getCheckOTP(String phone, String otp_entered) {
		String otp_generated = otp_locale.get(phone);
		return otp_generated.equals(otp_entered)?true:false;
	}
	
	public static boolean addOtp(String phone, String in_otp) {
		String val_otp = otp_locale.get(phone);
		if(val_otp == null || val_otp.equals("")) {
			otp_locale.put(phone, in_otp);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("null")
	public static void clearOtp(String phone) {
		String val_otp = otp_locale.get(phone);
		if(val_otp != null || !val_otp.equals("")) {
			otp_locale.remove(phone);
		}
	}
}
