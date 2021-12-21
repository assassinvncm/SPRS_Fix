package com.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;

public interface ReportService {
	Map<String, Object> getReportYear(ReportDto rpdto);
	Map<String, Object> getReportMonth(ReportDto rpdto);
	Map<String, Object> getReportYearORG(ReportDto rpdto);
	Map<String, Object> getReportMonthORG(ReportDto rpdto);
	Map<String, Object> getReportProvince(ReportDto rpdto);
	Map<String, Object> getReportProvinceORG(ReportDto rpdto);
	Map<String, Object> getReportTopUserORG(Long o_id);
	List<ReportResultDto> getReportOverview();
	List<ReportResultDto> getReportUserORGOverview();
}
