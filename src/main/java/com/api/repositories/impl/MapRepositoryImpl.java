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
import com.api.repositories.MapRepository;

@Repository
public class MapRepositoryImpl implements MapRepository {
	public static Logger logger = LoggerFactory.getLogger(MapRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Object[]> getPoints(double lati, double longti, double radius, String typePoint) {
		// TODO Auto-generated method stub
		List<Object[]> mapPoints = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_map_loadPointOnMap");
			storedProcedure.registerStoredProcedureParameter("lati", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("longti", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("radius", Double.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("typeStr", String.class, ParameterMode.IN);
			storedProcedure.setParameter("lati", String.valueOf(lati));
			storedProcedure.setParameter("longti", String.valueOf(longti));
			storedProcedure.setParameter("radius", radius);
			storedProcedure.setParameter("typeStr", typePoint);
			storedProcedure.execute();
			mapPoints = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Get Map Point -> ", e.getMessage());
		}

		return mapPoints;
	}

	@Override
	public List<Object[]> search(String text, double lati, double longti, int numberOfRecord) {
		// TODO Auto-generated method stub

		List<Object[]> searchRs = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_searchMap_v2");
			storedProcedure.registerStoredProcedureParameter("searchText", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("lati", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("longti", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("numberOfRecord", Integer.class, ParameterMode.IN);
			storedProcedure.setParameter("searchText", text);
			storedProcedure.setParameter("lati", String.valueOf(lati));
			storedProcedure.setParameter("longti", String.valueOf(longti));
			storedProcedure.setParameter("numberOfRecord", numberOfRecord);
			storedProcedure.execute();
			searchRs = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Search Map -> ", e.getMessage());
		}
		return searchRs;
	}

}
