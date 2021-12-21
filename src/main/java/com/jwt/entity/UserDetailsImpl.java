package com.jwt.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.entity.Group;
import com.api.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwt.config.JwtRequestFilter;

public class UserDetailsImpl implements UserDetails {
	
	public static Logger logger = LoggerFactory.getLogger(UserDetailsImpl.class);
	
	private static final long serialVersionUID = 1L;

	// private User user;
	private Long id;
	private String username;
	private String phoneNumber;

	@JsonIgnore
	private String password;

	Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {
		super();
	}

	public UserDetailsImpl(Long id, String username, String phoneNumber, String password,
			Collection<? extends GrantedAuthority> authories) {
		super();
		// this.user = user;
		this.id = id;
		this.username = username;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.authorities = authories;
	}

	public static UserDetailsImpl build(User user) {
//		List<Group> lstGr = (List<Group>) user.getGroups_user().stream().map(gr -> gr.getGroup_permission().stream().map(pr -> pr.getCode()));
//		List<GrantedAuthority> authorities = user.getGroups_user().stream()
//				.map(gr -> gr.getGroup_permission().stream().map(pr -> new SimpleGrantedAuthority(pr.getCode()))).collect(Collectors.toList());
		logger.info("Access Code of user "+user.getUsername()+" is: "+ user.toString());
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		user.getGroups_user().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getCode()));
			r.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getCode())));
		});
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPhone(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
