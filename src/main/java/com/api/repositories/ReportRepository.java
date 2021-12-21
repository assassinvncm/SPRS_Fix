package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository {
	List<Object[]> getReport(
			int p_district_id
			, int p_sub_district_id
			, int p_city_id
			, String p_date_from
			, String p_date_to
			, String p_type_point
			, String p_group_by);
	
	List<Object[]> getReportORG(
			int p_district_id
			, int p_sub_district_id
			, int p_city_id
			, String p_date_from
			, String p_date_to
			, String p_type_point
			, String p_group_by
			, long p_org_id);
	
	List<Object[]> getReportCity(
			int p_district_id
			, int p_sub_district_id
			, int p_city_id
			, String p_date_from
			, String p_date_to
			, String p_type_point
			, String p_group_by);
	
	List<Object[]> getReportCityORG(
			int p_district_id
			, int p_sub_district_id
			, int p_city_id
			, String p_date_from
			, String p_date_to
			, String p_type_point
			, String p_group_by
			, long p_org_id);
	
	List<Object[]> getReportTopUserORG(
			String p_date_from
			, String p_date_to
			, long p_org_id);
}
