package com.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.api.dto.AddressDto;
import com.api.dto.GrantAccessDto;
import com.api.dto.ImageDto;
import com.api.dto.PagingResponse;
import com.api.dto.SearchFilterDto;
import com.api.dto.SubcribeDto;
import com.api.dto.UpdatePasswordDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Store;
import com.api.entity.User;

public interface UserService {
	
	/**
	 * 
	 * @param userDto
	 * @return list user
	 */
	List<User> getAllUser();
	
	/**
	 * 
	 * @param phone number
	 * @return username
	 */
	String getUsernameByPhone(String phone);
	
	/**
	 * 
	 * @param phone number
	 * @return username
	 */
	User getUserByPhone(String phone);
	
	/**
	 * 
	 * @param phone number
	 * @return username
	 */
	boolean checkRegisUser(String phone, String username);
	
	/**
	 * 
	 * @return user
	 */
	UserDto getUserbyToken(String requestTokenHeader);
	
	/**
	 * 
	 * @return user
	 */
	User getNativeUserbyToken(String requestTokenHeader);
	
	/**
	 * 
	 * @param requestTokenHeader
	 * @return
	 */
	User getUserbyTokenAuth(String requestTokenHeader);
	
	/**
	 * register user
	 * @param userDto
	 * @return user
	 */
	User registerUser(User user);
	
	/**
	 * register user
	 * @param userDto
	 * @return user
	 */
	void registerUser_v2(UserDto userDto);

	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerOrganization_v2(UserDto userDto);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerOrganizationUser_v2(UserDto userDto, User admin);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void registerStoreUser_v2(UserDto userDto);
	
	/**
	 * 
	 * @param userDto
	 */
	void updateUser(UserDto userDto,UserDto bean);
	
	/**
	 * @param newPassword
	 */
	void updatePassword(UserDto userDto, UpdatePasswordDto updatePasswordDto);
	
//	/**
//	 * 
//	 * @param username
//	 * @return
//	 */
//	User findByUsername1(String username);
//	
//	/**
//	 * 
//	 * @param 
//	 * @return char[]
//	 */
//	String generatePassword(int len);
	
	
	User findByUsername(String username);
	
	/**
	 * 
	 * @param name
	 * @return List<User>
	 */
	Map<String, Object> getUsernameLike(SearchFilterDto sft);
	
	/**
	 * 
	 * @param 
	 * @return String
	 */
	String generatePassword(int len);
	
	/**
	 * 
	 * @param 
	 * @return SubcribeDto
	 */
	SubcribeDto subcribeStore(SubcribeDto s);
	
	/**
	 * 
	 * @param 
	 * @return SubcribeDto
	 */
	SubcribeDto unSubcribeStore(SubcribeDto s);
	
	/**
	 * 
	 * @param 
	 * @return SubcribeDto
	 */
	SubcribeDto getListSubcribe(Long id);
	
	/**
	 * grant group for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto grantGroup(GrantAccessDto gdto);
	
	/**
	 * ungrant group for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto unGrantGroup(GrantAccessDto gdto);
	
	/**
	 * grant group for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto grantPermission(GrantAccessDto gdto);
	
	/**
	 * grant group for user
	 * @param GrantAccessDto
	 * @return GrantAccessDto
	 */
	GrantAccessDto unGrantPermission(GrantAccessDto gdto);
	
	/**
	 * 
	 * @param name
	 * @return List<User>
	 */
	Map<String, Object> getOwnOrganizeUser(UserDto u, SearchFilterDto filter);
	
	/**
	 * 
	 * @param id
	 * @return user
	 */
	User unActiveOrganizeUser(Long id);
	
	/**
	 * 
	 * @param id
	 * @return user
	 */
	User activeOrganizeUser(Long id);
	
	/**
	 * 
	 * @param ImageDto
	 * @return User
	 */
	User uploadUserImg(ImageDto image);
	
	/**
	 * banned user
	 * @param Long
	 * @return User
	 */
	void banUser(Long user_id);
	
	/**
	 * unbanned user by user id
	 * @param Long
	 * @return User
	 */
	void unbannedUser(Long user_id);
	
	/**
	 * get banned user
	 * @param ImageDto
	 * @return User
	 */
	PagingResponse<UserDto> getUserByAdmin(List<String> groupCode,List<String> filterStatus ,String searchStr, int pageIndex, int pageSize);

}
