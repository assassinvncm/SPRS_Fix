package com.jwt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.controller.UserController;
import com.api.entity.Group;
import com.api.entity.User;
import com.api.repositories.UserRepository;
import com.exception.AppException;
import com.exception.AuthenException;
import com.jwt.entity.UserDetailsImpl;
import com.ultils.Constants;


@Service
public class JwtUserDetailsService implements UserDetailsService {


	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,AuthenException {
		User user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		if(user.getIsActive() == false) {//user.getGroups_user().get(0).getPermissions().get(0)
			throw new AuthenException("Account is not active");
		}
//		Set<String> authorities = new HashSet<>();
//        if (null != user.getGroups_user()) user.getGroups_user().forEach(r -> {
//            authorities.add(r.getCode());
//            r.getPermissions().forEach(p -> authorities.add(p.getCode()));
//        });
//        user.setAuthorities(authorities);
		return UserDetailsImpl.build(user);
	}
	
	public UserDetails loadUserByUsernameByPlatform(String username, int platform) throws UsernameNotFoundException,AuthenException {
		User user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
		
		if(user.getStatus() != null && user.getStatus().equals(Constants.USER_STATUS_BANNED)) {
			throw new AppException(201, "Tài khoản bị khóa");
		}
		
		if(user.getStatus() != null && user.getStatus().equals(Constants.USER_STATUS_UNACTIVE)) {
			throw new AppException(202, "Tài khoản chưa được duyệt");
		}
		
		if(user.getStatus() != null && user.getStatus().equals(Constants.USER_STATUS_REJECT)) {
			throw new AppException(203, "Tài khoản không được duyệt");
		}
		
		if(user.getIsActive() != null && user.getIsActive() == false) {
			throw new AuthenException("Account is not active");
		}

		List<Group> lstGr = user.getGroups_user();
		boolean check = false;
		for (Group group : lstGr) {
			if(group.getPlatform() == platform) {
				check = true;
			}
		}
		if(!check) {
			throw new AppException(204, "Tài khoản không được phép truy cập!");
		}
//		Set<String> authorities = new HashSet<>();
//        if (null != user.getGroups_user()) user.getGroups_user().forEach(r -> {
//            authorities.add(r.getCode());
//            r.getPermissions().forEach(p -> authorities.add(p.getCode()));
//        });
//        user.setAuthorities(authorities);
		return UserDetailsImpl.build(user);
	}
	
	
}