package com.api.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.GrantAccessDto;
import com.api.dto.ImageDto;
import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.SearchFilterDto;
import com.api.dto.UpdatePasswordDto;
import com.api.dto.UserDto;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.service.SOSService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;


@RestController
@RequestMapping("/sprs/api")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService; 
	
	@Autowired
	GroupRepository groupServ;
	
	@Autowired
	MapStructMapper mapper;
	
	@RequestMapping(value = "/users/search/{name}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getSearch(@PathVariable(value = "name") String name) {
		logger.info("Start get search User like");
		List<User> lst = userService.getUsernameLike(name);
		logger.info("End get search User like");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Search like User success!", "", null, mapper.lstUserToUserDto(lst)));
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getAllUser() {
		logger.info("Start get All User");
		List<User> lst = userService.getAllUser();
		logger.info("End get All User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get All User success!", "", null, mapper.lstUserToUserDto(lst)));
	}

	@RequestMapping(value = "/getOwnOrg", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getOwnOrganizeUser(@RequestHeader("Authorization") String requestTokenHeader, @RequestBody SearchFilterDto filter) {

		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		Map<String, Object> lstRs = userService.getOwnOrganizeUser(userDto, filter);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get own organize user success", "", lstRs, null));
	}

	@RequestMapping(value = "/org-user-unactive/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYS_VIEW_PROFILE')")
	public ResponseEntity<?> unActiveOrganizeUser(@PathVariable(value = "id") Long id) {
		logger.info("Start un-Active organize User");
		User u = userService.unActiveOrganizeUser(id);
		logger.info("End un-Active organize User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get own organize user success", "", mapper.userToUserDto(u), null));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getUserbyToken(@RequestHeader ("Authorization") String requestTokenHeader){
		logger.info("Start get User");
		User u = userService.getNativeUserbyToken(requestTokenHeader);
		logger.info("End get User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", mapper.userToUserDto_getUController(u), null));
	}
	
//	@RequestMapping(value = "/user", method = RequestMethod.POST)
//	public ResponseEntity<?> registerUser(@Validated @RequestBody User user) {
//		userService.registerUser(user);
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
//	}
	
	@RequestMapping(value = "/users_v2/user", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser_v2(@Validated @RequestBody UserDto userDto) {
		userService.registerUser_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create user success!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/organizationlAdmin", method = RequestMethod.POST)
	public ResponseEntity<?> registerOrganization_v2(@Validated @RequestBody UserDto userDto) {
		 userService.registerOrganization_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/organizationalUser", method = RequestMethod.POST)
	public ResponseEntity<?> registerOrganizationalUser_v2(@RequestHeader ("Authorization") String requestTokenHeader, @Validated @RequestBody UserDto userDto) {
		User u = userService.getNativeUserbyToken(requestTokenHeader);
		userService.registerOrganizationUser_v2(userDto, u);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users_v2/ownStore", method = RequestMethod.POST)
	public ResponseEntity<?> registerOwnStore_v2(@Validated @RequestBody UserDto userDto) {
		userService.registerStoreUser_v2(userDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Request to create account suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users/grantGroup", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> grantGroup(@Validated @RequestBody GrantAccessDto gtdo) {
		logger.info("Start grant group");
		userService.grantGroup(gtdo);
		logger.info("End grant group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Grant group suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users/unGrantGroup", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> unGrantGroup(@Validated @RequestBody GrantAccessDto gtdo) {
		logger.info("Start ungrant group");
		userService.unGrantGroup(gtdo);
		logger.info("End ungrant group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Ungrant group suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users/grantPermission", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> grantPermission(@Validated @RequestBody GrantAccessDto gtdo) {
		logger.info("Start grant permission");
		userService.grantPermission(gtdo);
		logger.info("End grant permission");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Grant permission suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/users/unGrantPermission", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> unGrantPermission(@Validated @RequestBody GrantAccessDto gtdo) {
		logger.info("Start ungrant permission");
		userService.unGrantPermission(gtdo);
		logger.info("End ungrant permission");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Ungrant permission suscess!", "", null, null));
	}
	
	@RequestMapping(value = "/user/update/infor", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYS_UPDATE_PROFILE')")
	public ResponseEntity<?> updateUser(@RequestHeader ("Authorization") String requestTokenHeader,@Validated @RequestBody UserDto bean){
		
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		logger.info("Start update User id: "+userDto.getId());
		userService.updateUser(userDto, bean);
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update user success!", "", null, null));
	}
	
	@RequestMapping(value = "/user/update/password", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYS_UPDATE_PASSWORD')")
	public ResponseEntity<?> updatePassword(@RequestHeader ("Authorization") String requestTokenHeader,
			@Validated @RequestBody UpdatePasswordDto updatePasswordDto){
		UserDto useDto = userService.getUserbyToken(requestTokenHeader);
		logger.info("Start update password: "+useDto.getUsername());
		userService.updatePassword(useDto, updatePasswordDto);
		logger.info("End update password");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update password success!", "", null, null));
	}

	@RequestMapping(value = "/user/uploadImg", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_NORMU_ACEN')")
	public ResponseEntity<?> uploadImg(@RequestBody ImageDto image) {
		logger.info("Start uploadImg user");
		User u = userService.uploadUserImg(image);
		logger.info("End uploadImg user");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Upload image for user success", "", "", null));
	}
	
	@RequestMapping(value = "users/admin", method = RequestMethod.GET)
	public ResponseEntity<?> getBanUser(@RequestParam(required = false,value="filterGroup") List<String> filterGroup,
			@RequestParam(required = false,value="filterStatus") List<String> filterStatus, 
			@RequestParam( required = false,value="Search", defaultValue = "") String search,
			@RequestParam(required = false, value="pageIndex") int pageIndex,
			@RequestParam(required = false, value="pageSize") int pageSize){
		logger.info("Start get banned User");
		PagingResponse<UserDto> uDto = userService.getUserByAdmin(filterGroup,filterStatus,search,pageIndex,pageSize);
		logger.info("End get banned User");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "get list banned user success!", "", uDto, null));
	}
	
	@RequestMapping(value = "/user/ban", method = RequestMethod.PUT)
	public ResponseEntity<?> banUser(@RequestHeader ("Authorization") String requestTokenHeader, @RequestParam("uId") Long uid){
		
		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		logger.info("Start ban User id: "+user.getId());
		userService.banUser(uid);
		
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "ban user success!", "", null, null));
	}
	
	@RequestMapping(value = "/user/unbanned", method = RequestMethod.PUT)
	public ResponseEntity<?> unbannedUser(@RequestHeader ("Authorization") String requestTokenHeader, @RequestParam("uId") Long uid){
		
		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		logger.info("Start unban User id: "+user.getId());
		userService.unbannedUser(uid);
		logger.info("Start unban User id: "+user.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "get list unbanned user success!", "", null, null));
	}

}
