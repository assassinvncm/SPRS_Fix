package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.controller.UserController;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

public class TokenUtils {
	
	public static Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public String getUserName(String requestToken) {
		String token = null;
		String username = null;
		if (requestToken != null && requestToken.startsWith("Bearer ")) {
			token = requestToken.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				throw new AppException(501,"Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				throw new AppException(501,"JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
			throw new AppException(501,"JWT Token does not begin with Bearer String");
		}
		return username;
	}
}
