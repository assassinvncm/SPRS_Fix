package com.api.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.dto.SPRSResponse;
import com.api.dto.UserDto;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.service.ReportService;
import com.api.service.UserService;
import com.common.utils.DateUtils;
import com.ultils.Constants;

@RestController
@RequestMapping("sprs/api/report-manage")
public class ReportController {

	public static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	ReportService reportServ;
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Autowired
	UserService userService; 

	@RequestMapping(value = "/getReportMonth", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getReportMonth(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Month");
		Map<String, Object> rs = reportServ.getReportMonth(rpdto);
		logger.info("End get report Month");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Month success", "", rs, null));
	}

	@RequestMapping(value = "/getReportYear", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getReportYear(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Year");
		Map<String, Object> rs = reportServ.getReportYear(rpdto);
		logger.info("End get report Year");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Year success", "", rs, null));
	}

	@RequestMapping(value = "/getReportMonth-org", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportMonthORG(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody ReportDto rpdto) {
		logger.info("Start get report ORG Month");
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		rpdto.setOrg_id(userDto.getOrganization().getId());
		Map<String, Object> rs = reportServ.getReportMonthORG(rpdto);
		logger.info("End get report ORG Month");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Month ORG success", "", rs, null));
	}

	@RequestMapping(value = "/getReportYear-org", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportYearORG(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody ReportDto rpdto) {
		logger.info("Start get report Year");
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		rpdto.setOrg_id(userDto.getOrganization().getId());
		Map<String, Object> rs = reportServ.getReportYearORG(rpdto);
		logger.info("End get report ORG Year");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Year ORG success", "", rs, null));
	}

	@RequestMapping(value = "/getReportProvince", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN')")
	public ResponseEntity<?> getReportProvince(@RequestBody ReportDto rpdto) {
		logger.info("Start get report Province");
		Map<String, Object> rs = reportServ.getReportProvince(rpdto);
		logger.info("End get report Province");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Province success", "", rs, null));
	}

	@RequestMapping(value = "/getReportProvinceORG", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportProvinceORG(@RequestHeader ("Authorization") String requestTokenHeader,@RequestBody ReportDto rpdto) {
		logger.info("Start get report Province ORG");
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		rpdto.setOrg_id(userDto.getOrganization().getId());
		Map<String, Object> rs = reportServ.getReportProvinceORG(rpdto);
		logger.info("End get report Province ORG");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Province ORG success", "", rs, null));
	}

	@RequestMapping(value = "/getReportTopUserORG", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportTopUserORG(@RequestHeader ("Authorization") String requestTokenHeader) {
		logger.info("Start getReportTopUserORG");
		UserDto userDto = userService.getUserbyToken(requestTokenHeader);
		ReportDto rpdto = new ReportDto();
		rpdto.setOrg_id(userDto.getOrganization().getId());
		Map<String, Object> rs = reportServ.getReportTopUserORG(userDto.getOrganization().getId());
		logger.info("End getReportTopUserORG");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Top User ORG success", "", rs, null));
	}

	@RequestMapping(value = "/getReportOverview", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_SYSADM_ACEN','PER_ORGADM_ACEN')")
	public ResponseEntity<?> getReportOverview() {
		logger.info("Start get report Overview");
		List<ReportResultDto> rs = reportServ.getReportOverview();
		logger.info("End get report Overview");
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get report Overview success", "", rs, null));
	}
	
}
