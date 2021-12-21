package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.Group;
import com.api.entity.Permission;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.PermissionRepository;
import com.api.service.PermissionService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
public class PermissionController {
	public static Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	PermissionService perServ;
	
	@Autowired
	UserService userSerivce;
	
	@Autowired 
	MapStructMapper mapper;
	
	@RequestMapping(value = "/permissions/getOwn", method = RequestMethod.GET)
	public ResponseEntity<?> getOwnPermission(@RequestHeader ("Authorization") String requestTokenHeader){
		logger.info("Start get own Permission");
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		List<Permission> lst = perServ.getOwnPermission(userDto.getId());
		logger.info("End get own Permission");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get All Permission success!", "", null, mapper.lstPermissionToLstGrantAccess(lst)));
	}
	
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public ResponseEntity<?> getAllPermission(){
		logger.info("Start get all Permission");
		List<Permission> lst = perServ.getAll();
		logger.info("End get all Permission");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get All Permission success!", "", null, mapper.lstPermissionToPermissionDto(lst)));
	}
	
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getPermissionById(@PathVariable(value = "id") Long id){
		logger.info("Start Permission by id: "+id);
		Permission p = perServ.getById(id);
		logger.info("End get Permission by id: "+id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Permission by id: "+id+" success!", "", mapper.permisisonToPermisionDto(p), null));
	}
	
	@RequestMapping(value = "/permissions", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> savePermission(@Validated @RequestBody Permission per){
		logger.info("Start save permission id: "+per.getId());
		Permission p = perServ.createPermission(per);
		logger.info("End save permission id: "+per.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create Permission success!", "", mapper.permisisonToPermisionDto(p), null));
	}
	
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> updatePermission(@PathVariable(value = "id") Long id ,@Validated @RequestBody Permission per){
		logger.info("Start update permission id: "+per.getId());
		Permission p = perServ.updatePermission(per, id);
		logger.info("End update permission id: "+per.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Permission success!", "", mapper.permisisonToPermisionDto(p), null));
	}
	@RequestMapping(value = "/permissions-authoried/{group_id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getAllPermissionAuthoried(@PathVariable("group_id") Long group_id) {
		logger.info("Start get all Permission authoried by group id: "+group_id);
		List<Permission> listGroup = perServ.getAllPermissionAuthoriedByGroup(group_id);
		logger.info("End get all Permission authoried by group id: "+group_id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get all permission authoried by group id: "+group_id+" success!", "", null, mapper.lstPermissionToPermissionDto(listGroup)));
	}

	@RequestMapping(value = "/permissions-unauthoried/{group_id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getAllPermissionUnAuthoried(@PathVariable("group_id") Long group_id) {
		logger.info("Start get all Permission unauthoried by group id: "+group_id);
		List<Permission> listGroup = perServ.getAllPermissionUnAuthoriedByGroup(group_id);
		logger.info("End get all Permission unauthoried by group id: "+group_id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get all permission unauthoried by group id: "+group_id+" success!", "", null, mapper.lstPermissionToPermissionDto(listGroup)));
	}
}
