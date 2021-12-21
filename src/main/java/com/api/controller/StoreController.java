package com.api.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ImageDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.SearchFilterDto;
import com.api.dto.StoreDto;
import com.api.dto.SubcribeDto;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.StoreRepository;
import com.api.service.AmazonClient;
import com.api.service.StoreService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("sprs/api/store-manage")
public class StoreController {
	
	public static Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	StoreService storeService;
	
	@Autowired
	UserService userSerivce;
	
	@Autowired
	private MapStructMapper structMapper;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> createStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody StoreDto s) {
		logger.info("Start create Store");
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_st(userDto);
		
		Store store = storeService.createStore(s);
		logger.info("End create Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Create store successfully", "", store, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getStoreFilter(@RequestHeader ("Authorization") String requestTokenHeader,
			@RequestBody SearchFilterDto sft) {
		logger.info("Start get Store filter");
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		Map<String, Object> lstStore = storeService.getStoreFilterByType(userDto.getId(), sft);
		logger.info("End get Store filter");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store owner success", "", lstStore, null));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getStoreOwer(@RequestHeader ("Authorization") String requestTokenHeader) {
		logger.info("Start get Store owner");
		List<StoreDto> lstStore = storeService.getStoreOwner();
		logger.info("End get Store owner");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store owner success", "", lstStore, null));
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getAll() {
		logger.info("Start get all Store");
		List<StoreDto> lstStore = storeService.getAllStore();
		logger.info("End get all Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get All Store success", "", lstStore, null));
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getStoreById(@PathVariable(value = "id") Long id) {
		logger.info("Start get Store by id: "+id);
		Store st = storeService.getStoreById(id);
		StoreDto rs = structMapper.storeToStoreDTO(st);
		logger.info("End get Store by id: "+id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store By ID "+id+" success", "", rs, null));
	}
	
	@RequestMapping(value = "/common/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStoreByIdCommon(@RequestHeader (required = false, value = "Authorization", defaultValue = "") String requestTokenHeader, @PathVariable(value = "id") Long id) {
		logger.info("Start get Store by id: "+id);
		User u = null;
		if(!requestTokenHeader.isBlank()) {
			u = userSerivce.getUserbyTokenAuth(requestTokenHeader);
		}
		Map<String, Object> rs = storeService.getStoreCommon(id,u);
		logger.info("End get Store by id: "+id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store By ID "+id+" success", "", rs, null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> updateStore(@RequestBody StoreDto storeDto) {
		logger.info("Start update Store");
		storeService.updateStore(storeDto);
		logger.info("End update Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Store By ID "+storeDto.getId()+" success", "", storeDto, null));
	}

	@RequestMapping(value = "/openCloseStore", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> openCloseStore(@RequestBody StoreDto storeDto) {
		logger.info("Start update Store");
		storeService.openCloseStore(storeDto);
		logger.info("End update Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Update Open Close Store By ID "+storeDto.getId()+" success", "", storeDto, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> deleteStore(@RequestBody StoreDto storeDto) {
		logger.info("Start delete Store");
		storeService.deleteStore(storeDto);
		logger.info("End delete Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Delete Store By ID "+storeDto.getId()+" success", "", storeDto, null));
	}

//	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
//	public ResponseEntity<?> uploadImg(@RequestParam(value = "file") MultipartFile file, String store_id) {
//		logger.info("Start uploadImg Store");
//		Store st = storeService.uploadStoreImg(file, store_id);
//		logger.info("End uploadImg Store");
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Upload image for store success", "", "", null));
//	}

	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> uploadImg(@RequestBody ImageDto image) {
		logger.info("Start uploadImg Store");
		Store st = storeService.uploadStoreImg(image);
		logger.info("End uploadImg Store");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Upload image for store success", "", "", null));
	}
	
	@RequestMapping(value = "/subcribe", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_SUBCRIBE')")
	public ResponseEntity<?> subcribeStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody SubcribeDto s) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_id(userDto.getId());
		
		SubcribeDto sdto = userSerivce.subcribeStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Subcribe store successfully", "", sdto, null));
	}
	
	@RequestMapping(value = "/unsubcribe", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_SUBCRIBE')")
	public ResponseEntity<?> unSubcribeStore(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody SubcribeDto s) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		s.setUser_id(userDto.getId());
		
		SubcribeDto sdto = userSerivce.unSubcribeStore(s);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Unsubcribe store successfully", "", sdto, null));
	}
	
	@RequestMapping(value = "/getSubcribe", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_SUBCRIBE')")
	public ResponseEntity<?> getListSubcribe(@RequestHeader ("Authorization") String requestTokenHeader) {
		UserDto userDto = userSerivce.getUserbyToken(requestTokenHeader);
		SubcribeDto sdto = userSerivce.getListSubcribe(userDto.getId());
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get list subcribe store successfully", "", sdto, null));
	}
}
