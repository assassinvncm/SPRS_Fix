package com.api.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.api.repositories.ReportRepository;

@Repository
public class ReportRepositoryImpl implements ReportRepository{
	
	public static Logger logger = LoggerFactory.getLogger(ReportRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Object[]> getReport(int p_district_id, int p_sub_district_id, int p_city_id, String p_date_from, String p_date_to,
			String p_type_point, String p_group_by) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_get_report");
			storedProcedure.registerStoredProcedureParameter("p_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_group_by", String.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district_id", p_district_id);
			storedProcedure.setParameter("p_sub_district_id", p_sub_district_id);
			storedProcedure.setParameter("p_city_id", p_city_id);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.setParameter("p_group_by", p_group_by);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}
	
	@Override
	public List<Object[]> getReportORG(int p_district_id, int p_sub_district_id, int p_city_id, String p_date_from, String p_date_to,
			String p_type_point, String p_group_by, long p_org_id) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_getReportOrg");
			storedProcedure.registerStoredProcedureParameter("p_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_group_by", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_org_id", Long.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district_id", p_district_id);
			storedProcedure.setParameter("p_sub_district_id", p_sub_district_id);
			storedProcedure.setParameter("p_city_id", p_city_id);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.setParameter("p_group_by", p_group_by);
			storedProcedure.setParameter("p_org_id", p_org_id);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

	@Override
	public List<Object[]> getReportCity(int p_district_id, int p_sub_district_id, int p_city_id, String p_date_from,
			String p_date_to, String p_type_point, String p_group_by) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_get_report_city");
			storedProcedure.registerStoredProcedureParameter("p_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_group_by", String.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district_id", p_district_id);
			storedProcedure.setParameter("p_sub_district_id", p_sub_district_id);
			storedProcedure.setParameter("p_city_id", p_city_id);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.setParameter("p_group_by", p_group_by);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

	@Override
	public List<Object[]> getReportCityORG(int p_district_id, int p_sub_district_id, int p_city_id, String p_date_from,
			String p_date_to, String p_type_point, String p_group_by, long p_org_id) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_getReportCityOrg");
			storedProcedure.registerStoredProcedureParameter("p_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_sub_district_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_city_id", Integer.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_type_point", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_group_by", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_org_id", Long.class, ParameterMode.IN);
			storedProcedure.setParameter("p_district_id", p_district_id);
			storedProcedure.setParameter("p_sub_district_id", p_sub_district_id);
			storedProcedure.setParameter("p_city_id", p_city_id);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_type_point", p_type_point);
			storedProcedure.setParameter("p_group_by", p_group_by);
			storedProcedure.setParameter("p_org_id", p_org_id);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

	@Override
	public List<Object[]> getReportTopUserORG(String p_date_from, String p_date_to, long p_org_id) {
		List<Object[]> rs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_report_getReportTopUserOrg");
			storedProcedure.registerStoredProcedureParameter("p_date_from", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_date_to", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("p_org_id", Long.class, ParameterMode.IN);
			storedProcedure.setParameter("p_date_from", p_date_from);
			storedProcedure.setParameter("p_date_to", p_date_to);
			storedProcedure.setParameter("p_org_id", p_org_id);
			storedProcedure.execute();
			rs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Report -> ", e.getMessage());
		}
		return rs;
	}

}
