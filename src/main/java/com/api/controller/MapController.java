package com.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.MapPointsDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SPRSResponse;
import com.api.dto.SearchMapResponse;
import com.api.dto.UserDto;
import com.api.entity.ReliefPoint;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.service.MapService;
import com.api.service.UserService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/manage-map")
public class MapController {

	@Autowired
	MapService mapService;
	
	@Autowired
	UserService userService;

	@Autowired
	MapStructMapper mapStructMapper;

//	@RequestMapping(value = "/getReliefPoint", method = RequestMethod.GET)
//	public ResponseEntity<?> getReliefPoints(@RequestParam("long") double lo, @RequestParam("lat") double lat,
//			@RequestParam("radius") double radius) {
//
//		List<ReliefPoint> lstRp = (List<ReliefPoint>) mapService.findPointinRadius(lat, lo, radius, ReliefPoint.class);
//		List<ReliefPointDto> lstRpDto = mapStructMapper.lstReliefPointToreliefPointDto(lstRp);
//
//		return ResponseEntity
//				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstRpDto, null));
//	}
//
//	@RequestMapping(value = "/getStore", method = RequestMethod.GET)
//	public ResponseEntity<?> getStores(@RequestParam("long") double lo, @RequestParam("lat") double lat,
//			@RequestParam("radius") double radius) {
//
//		// List<MapPointsDto> lstStore = mapService.getClass();
//
//		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", null, null));
//	}
//
//	@RequestMapping(value = "/getSos", method = RequestMethod.GET)
//	public ResponseEntity<?> getSOS(@RequestParam("long") double lo, @RequestParam("lat") double lat,
//			@RequestParam("radius") double radius) {
//
//		List<ReliefPoint> lstRp = (List<ReliefPoint>) mapService.findPointinRadius(lat, lo, radius, ReliefPoint.class);
//		List<ReliefPointDto> lstRpDto = mapStructMapper.lstReliefPointToreliefPointDto(lstRp);
//
//		return ResponseEntity
//				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstRpDto, null));
//	}
//
//	@RequestMapping(value = "/getOrganizations", method = RequestMethod.GET)
//	public ResponseEntity<?> getOrganizations(@RequestParam("long") double lo, @RequestParam("lat") double lat,
//			@RequestParam("radius") double radius) {
//
//		List<ReliefPoint> lstRp = (List<ReliefPoint>) mapService.findPointinRadius(lat, lo, radius, ReliefPoint.class);
//		List<ReliefPointDto> lstRpDto = mapStructMapper.lstReliefPointToreliefPointDto(lstRp);
//
//		return ResponseEntity
//				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstRpDto, null));
//	}

	@RequestMapping(value = "/getPoints", method = RequestMethod.GET)
	public ResponseEntity<?> getPoints(@RequestParam("long") double lo, @RequestParam("lat") double lat,
			@RequestParam("radius") double radius, @RequestParam(value = "filter", required = false, defaultValue = "") String typePoint) {

		List<MapPointsDto> lstPoints = mapService.findAllPoints(lat, lo, radius,typePoint);

		return ResponseEntity
				.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstPoints, null));
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> search(@RequestParam("search") String searchStr, @RequestParam("lati") double lati,
			@RequestParam("long") double longti, @RequestParam("limit") int limit) {

		List<SearchMapResponse> lstSearchMapRes =  mapService.search(searchStr, lati, longti, limit);

		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Relief Point success", "", lstSearchMapRes, null));
	}
	
	@RequestMapping(value = "/isOwnPoint", method = RequestMethod.GET)
	public ResponseEntity<?> checkIsOwnPoint(@RequestHeader ("Authorization") String requestTokenHeader,
			@RequestParam("p_id") long p_id,@RequestParam("p_type") String type) {
		User user = userService.getUserbyTokenAuth(requestTokenHeader);
		boolean rs =  mapService.checkIsOwnPoint(user, p_id, type);
		HashMap<String, Boolean> status = new HashMap<String, Boolean>();
		status.put("isOwnPoint", rs);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Check is own point", "", status, null));
	}
}
