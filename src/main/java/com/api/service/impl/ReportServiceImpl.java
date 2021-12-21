package com.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ReportDto;
import com.api.dto.ReportResultDto;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.ReportRepository;
import com.api.service.ReportService;
import com.common.utils.DateUtils;
import com.ultils.Ultilities;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	ReportRepository rpRepo;
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Override
	public Map<String, Object> getReportYear(ReportDto rpdto) {
		String group_by = "m_month";
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String monthAgo = DateUtils.getMonthAgo("yyyy-MM-dd", 12);
		String type_point = "";
		rpdto.setDate_from(monthAgo);
		rpdto.setDate_to(currDate);
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		List<Integer> values1 = new ArrayList<Integer>();
		List<Integer> values2 = new ArrayList<Integer>();
		List<Integer> values3 = new ArrayList<Integer>();
		List<Integer> values4 = new ArrayList<Integer>();
		lstRs.forEach(l -> labelsTemp.add(l.getYear()+"-"+l.getMonth()));
		labels = Ultilities.getLabelReport(labelsTemp);

		for (String label : labels) {
			boolean isVal1 = false;
			boolean isVal2 = false;
			boolean isVal3 = false;
			boolean isVal4 = false;
			for (ReportResultDto reportResultDto : lstRs) {
				if(label.equals(reportResultDto.getYear()+"-"+reportResultDto.getMonth()) && reportResultDto.getType_point() == 1) {
					values1.add((int)reportResultDto.getTotal());
					isVal1 = true;
				}
				if(label.equals(reportResultDto.getYear()+"-"+reportResultDto.getMonth()) && reportResultDto.getType_point() == 2) {
					values2.add((int)reportResultDto.getTotal());
					isVal2 = true;
				}
				if(label.equals(reportResultDto.getYear()+"-"+reportResultDto.getMonth()) && reportResultDto.getType_point() == 3) {
					values3.add((int)reportResultDto.getTotal());
					isVal3 = true;
				}
				if(label.equals(reportResultDto.getYear()+"-"+reportResultDto.getMonth()) && reportResultDto.getType_point() == 4) {
					values4.add((int)reportResultDto.getTotal());
					isVal4 = true;
				}
			}
			if(!isVal1) {
				values1.add(0);
			}
			if(!isVal2) {
				values2.add(0);
			}
			if(!isVal3) {
				values3.add(0);
			}
			if(!isVal4) {
				values4.add(0);
			}
		}
		Map<String, Object> data = new HashMap<>();
		if(Ultilities.checkExistIn(1, rpdto.getType_point())) {
			data.put("ReliefPoint", values1);
		}
		if(Ultilities.checkExistIn(2, rpdto.getType_point())) {
			data.put("StorePoint", values2);
		}
		if(Ultilities.checkExistIn(3, rpdto.getType_point())) {
			data.put("Organization", values3);
		}
		if(Ultilities.checkExistIn(4, rpdto.getType_point())) {
			data.put("SOS", values4);
		}
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}
	

	@Override
	public Map<String, Object> getReportMonth(ReportDto rpdto) {
		String group_by = "m_day";
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String dayAgo = DateUtils.getDateAgo("yyyy-MM-dd", 30);
		String type_point = "";
		rpdto.setDate_from(dayAgo);
		rpdto.setDate_to(currDate);
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReport(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		List<Integer> values1 = new ArrayList<Integer>();
		List<Integer> values2 = new ArrayList<Integer>();
		List<Integer> values3 = new ArrayList<Integer>();
		List<Integer> values4 = new ArrayList<Integer>();
		lstRs.forEach(l -> labelsTemp.add(l.getDay()+"-"+l.getMonth()+"-"+l.getYear()));
		labels = Ultilities.getLabelReport(labelsTemp);

		for (String label : labels) {
			boolean isVal1 = false;
			boolean isVal2 = false;
			boolean isVal3 = false;
			boolean isVal4 = false;
			for (ReportResultDto reportResultDto : lstRs) {
				if(label.equals(reportResultDto.getDay()+"-"+reportResultDto.getMonth()+"-"+reportResultDto.getYear()) && reportResultDto.getType_point() == 1) {
					values1.add((int)reportResultDto.getTotal());
					isVal1 = true;
				}
				if(label.equals(reportResultDto.getDay()+"-"+reportResultDto.getMonth()+"-"+reportResultDto.getYear()) && reportResultDto.getType_point() == 2) {
					values2.add((int)reportResultDto.getTotal());
					isVal2 = true;
				}
				if(label.equals(reportResultDto.getDay()+"-"+reportResultDto.getMonth()+"-"+reportResultDto.getYear()) && reportResultDto.getType_point() == 3) {
					values3.add((int)reportResultDto.getTotal());
					isVal3 = true;
				}
				if(label.equals(reportResultDto.getDay()+"-"+reportResultDto.getMonth()+"-"+reportResultDto.getYear()) && reportResultDto.getType_point() == 4) {
					values4.add((int)reportResultDto.getTotal());
					isVal4 = true;
				}
			}
			if(!isVal1) {
				values1.add(0);
			}
			if(!isVal2) {
				values2.add(0);
			}
			if(!isVal3) {
				values3.add(0);
			}
			if(!isVal4) {
				values4.add(0);
			}
		}
		Map<String, Object> data = new HashMap<>();
		if(Ultilities.checkExistIn(1, rpdto.getType_point())) {
			data.put("ReliefPoint", values1);
		}
		if(Ultilities.checkExistIn(2, rpdto.getType_point())) {
			data.put("StorePoint", values2);
		}
		if(Ultilities.checkExistIn(3, rpdto.getType_point())) {
			data.put("Organization", values3);
		}
		if(Ultilities.checkExistIn(4, rpdto.getType_point())) {
			data.put("SOS", values4);
		}
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}
	
	@Override
	public Map<String, Object> getReportYearORG(ReportDto rpdto) {
		String group_by = "m_month";
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String monthAgo = DateUtils.getMonthAgo("yyyy-MM-dd", 12);
		String type_point = "";
		rpdto.setDate_from(monthAgo);
		rpdto.setDate_to(currDate);
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReportORG(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by
				, rpdto.getOrg_id());
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		lstRs.forEach(l -> labelsTemp.add(l.getYear()+"-"+l.getMonth()));
		labels = Ultilities.getLabelReport(labelsTemp);

		Map<String, Object> data = new HashMap<>();
		data.put("ReliefPoint", lstRs);
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}

	@Override
	public Map<String, Object> getReportMonthORG(ReportDto rpdto) {
		String group_by = "m_day";
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String dayAgo = DateUtils.getDateAgo("yyyy-MM-dd", 30);
		String type_point = "";
		rpdto.setDate_from(dayAgo);
		rpdto.setDate_to(currDate);
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReportORG(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, group_by
				, rpdto.getOrg_id());
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		lstRs.forEach(l -> labelsTemp.add(l.getDay()+"-"+l.getMonth()+"-"+l.getYear()));
		labels = Ultilities.getLabelReport(labelsTemp);

		Map<String, Object> data = new HashMap<>();
		data.put("ReliefPoint", lstRs);
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}

	@Override
	public List<ReportResultDto> getReportOverview() {
		String group_by = "1=1";
		String type_point = "1,2,3,4";
		List<Object[]> lstObj = rpRepo.getReport(0
				, 0
				, 0
				, ""
				, ""
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		int total = 0;
		for (ReportResultDto reportResultDto : lstRs) {
			total += reportResultDto.getTotal();
		}

		for (ReportResultDto reportResultDto : lstRs) {
			double percent = reportResultDto.getTotal()*100/total;
			reportResultDto.setTotal(percent);
		}
		
		return lstRs;
	}

	@Override
	public List<ReportResultDto> getReportUserORGOverview() {
		String group_by = "1=1";
		String type_point = "1,2,3,4";
		List<Object[]> lstObj = rpRepo.getReport(0
				, 0
				, 0
				, ""
				, ""
				, type_point
				, group_by);
		
		List<ReportResultDto> lstRs = mapper.reportMapping(lstObj);
		int total = 0;
		for (ReportResultDto reportResultDto : lstRs) {
			total += reportResultDto.getTotal();
		}

		for (ReportResultDto reportResultDto : lstRs) {
			double percent = reportResultDto.getTotal()*100/total;
			reportResultDto.setTotal(percent);
		}
		
		return lstRs;
	}


	@Override
	public Map<String, Object> getReportProvince(ReportDto rpdto) {
		String type_point = "";
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReportCity(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, "");
		
		List<ReportResultDto> lstRs = mapper.reportMappingProvince(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		List<Integer> values1 = new ArrayList<Integer>();
		List<Integer> values2 = new ArrayList<Integer>();
		List<Integer> values3 = new ArrayList<Integer>();
		List<Integer> values4 = new ArrayList<Integer>();
		lstRs.forEach(l -> labelsTemp.add(l.getName()));
		labels = Ultilities.getLabelReport(labelsTemp);

		for (String label : labels) {
			boolean isVal1 = false;
			boolean isVal2 = false;
			boolean isVal3 = false;
			boolean isVal4 = false;
			for (ReportResultDto reportResultDto : lstRs) {
				if(label.equals(reportResultDto.getName()) && reportResultDto.getType_point() == 1) {
					values1.add((int)reportResultDto.getTotal());
					isVal1 = true;
				}
				if(label.equals(reportResultDto.getName()) && reportResultDto.getType_point() == 2) {
					values2.add((int)reportResultDto.getTotal());
					isVal2 = true;
				}
				if(label.equals(reportResultDto.getName()) && reportResultDto.getType_point() == 3) {
					values3.add((int)reportResultDto.getTotal());
					isVal3 = true;
				}
				if(label.equals(reportResultDto.getName()) && reportResultDto.getType_point() == 4) {
					values4.add((int)reportResultDto.getTotal());
					isVal4 = true;
				}
			}
			if(!isVal1) {
				values1.add(0);
			}
			if(!isVal2) {
				values2.add(0);
			}
			if(!isVal3) {
				values3.add(0);
			}
			if(!isVal4) {
				values4.add(0);
			}
		}
		Map<String, Object> data = new HashMap<>();
		if(Ultilities.checkExistIn(1, rpdto.getType_point())) {
			data.put("ReliefPoint", values1);
		}
		if(Ultilities.checkExistIn(2, rpdto.getType_point())) {
			data.put("StorePoint", values2);
		}
		if(Ultilities.checkExistIn(3, rpdto.getType_point())) {
			data.put("Organization", values3);
		}
		if(Ultilities.checkExistIn(4, rpdto.getType_point())) {
			data.put("SOS", values4);
		}
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}@Override
	public Map<String, Object> getReportProvinceORG(ReportDto rpdto) {
		String type_point = "";
		for (int i = 0; i < rpdto.getType_point().length; i++) {
			type_point+=String.valueOf(rpdto.getType_point()[i]);
			if(i!=rpdto.getType_point().length-1) {
				type_point+=",";
			}
		}
		List<Object[]> lstObj = rpRepo.getReportCityORG(rpdto.getDistrict_id()
				, rpdto.getSub_district_id()
				, rpdto.getCity_id()
				, rpdto.getDate_from()
				, rpdto.getDate_to()
				, type_point
				, ""
				, rpdto.getOrg_id());
		
		List<ReportResultDto> lstRs = mapper.reportMappingProvince(lstObj);
		final List<String> labelsTemp = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		lstRs.forEach(l -> labelsTemp.add(l.getName()));
		labels = Ultilities.getLabelReport(labelsTemp);

		
		Map<String, Object> data = new HashMap<>();
		data.put("ReliefPoint", lstRs);
		Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("label", labels);
		return response;
	}


	@Override
	public Map<String, Object> getReportTopUserORG(Long o_id) {
		String currDate = DateUtils.getCurrentDate("yyyy-MM-dd");
		String dayAgo = DateUtils.getDateAgo("yyyy-MM-dd", 30);
		List<Object[]> lstObj = rpRepo.getReportTopUserORG(
				  dayAgo
				, currDate
				, o_id);
		
		List<ReportResultDto> lstRs = mapper.reportMappingTopUser(lstObj);
		
		Map<String, Object> data = new HashMap<>();
		data.put("data", lstRs);
		return data;
	}

}
