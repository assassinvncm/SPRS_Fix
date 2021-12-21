package com.api.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.entity.Group;
import com.api.entity.Permission;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.PermissionRepository;
import com.api.service.GroupService;
import com.api.service.UserService;
import com.exception.AppException;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupController {

	public static Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	UserService userServ;
	
	@Autowired
	GroupService groupServ;

	@Autowired
	PermissionRepository perRepo;
	
	@Autowired
	MapStructMapper mapper;

	@RequestMapping(value = "/groups-all", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		logger.info("Start get all Group");
		List<Group> listGroup = groupServ.getAll();
		logger.info("End get all Group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get all groups success", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}

	@RequestMapping(value = "/groups-register-orgUser", method = RequestMethod.GET)
	public ResponseEntity<?> getGroupForOrgzUserRegister() {
		logger.info("Start get all Group for organize user register");
		List<Group> listGroup = groupServ.getAllGroupForRegister(3);
		logger.info("End get all Group for organize user register");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get group organize user register success", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}

	@RequestMapping(value = "/groups-register-web", method = RequestMethod.GET)
	public ResponseEntity<?> getGroupForRegister() {
		logger.info("Start get all Group for register");
		List<Group> listGroup = groupServ.getAllGroupForRegister(1);
		logger.info("End get all Group for register");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get group for register success", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}

	@RequestMapping(value = "/groups-register-mobile", method = RequestMethod.GET)
	public ResponseEntity<?> getGroupForRegisterMobile() {
		logger.info("Start get all Group for mobile register");
		List<Group> listGroup = groupServ.getAllGroupForRegister(2);
		logger.info("End get all Group for mobile register");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get group for mobile register success", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}


	@RequestMapping(value = "/groups-authoried/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllGroupAuthoried(@PathVariable("user_id") Long user_id) {
		logger.info("Start get all Group authoried by user id: "+user_id);
		List<Group> listGroup = groupServ.getAllGroupAuthoriedByUser(user_id);
		logger.info("End get all Group authoried by user id: "+user_id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}

	@RequestMapping(value = "/groups-unauthoried/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllGroupUnAuthoried(@PathVariable("user_id") Long user_id) {
		logger.info("Start get all Group unauthoried by user id: "+user_id);
		List<Group> listGroup = groupServ.getAllGroupUnAuthoriedByUser(user_id);
		logger.info("End get all Group unauthoried by user id: "+user_id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, mapper.lstGroupToGroupDto(listGroup)));
	}
	
//	@RequestMapping(value = "/group/{typeAccess}", method = RequestMethod.GET)
//	public ResponseEntity<?> listGroup(@PathVariable("typeAccess") String typeAccess) {
//		
//		if(typeAccess.equals("web")) {
//			
//		}else if(typeAccess.equals("mobile")) {
//			
//		}
//		
//		List<Group> listGroup = groupServ.findAll();
//		if (listGroup.isEmpty()) {
//			throw new AppException(404, "Group is not existed!");
//		}
//		logger.info("End get all Group");
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "", "", null, listGroup));
//	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start get Group by id: " + id);
		Group g = groupServ.getById(id);
		logger.info("End get Group by id: " + id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get group by id: "+id+" success!", "", mapper.groupToGroupDto(g), null));
	}

	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public ResponseEntity<?> saveGroup(@Validated @RequestBody Group bean) {
		logger.info("Start save Group");
		Group g = groupServ.createGroup(bean);
		logger.info("End save Group");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create group success!", "", mapper.groupToGroupDto(g), null));
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGroup(@PathVariable(value = "id") Long id, @Validated @RequestBody Group bean) {
		logger.info("Start update Group id: " + id);
		Group g = groupServ.updateGroup(bean, id);
		logger.info("End update Group id: " + id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update group success!", "", mapper.groupToGroupDto(g), null));
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGroup(@PathVariable(value = "id") Long id) {
		logger.info("Start delete Group id: " + id);
		Group g = groupServ.deleteGroup(id);
		logger.info("End delete Group id: " + id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Delete group success!", "", mapper.groupToGroupDto(g), null));
	}

//	public ResponseEntity<?> grantPermission(@Validated @RequestBody Group group) {
//		logger.info("Start grant permission!");
//		Optional<Group> g = groupServ.findById(group.getId());
//		if (g.isEmpty()) {
//			throw new AppException(404, "Group is not Found!");
//		}
//		Collection<Permission> lstTem = group.getPermissions();
//		for (Permission permis : lstTem) {
//			Optional<Permission> perTemp = perRepo.findById(permis.getId());
//			if (perTemp.isEmpty()) {
//				throw new AppException(404, "Permission not Found!");
//			}
//			groupServ.save(group);
//
//		}
//		logger.info("End grant permission!");
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Grant permission success!", "", null, null));
//	}
}
