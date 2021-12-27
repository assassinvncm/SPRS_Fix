package com.api.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.api.entity.Device;
import com.api.repositories.custom.DeviceRepositoryCustom;

@Repository
public class DeviceRepositoryCustromImpl implements DeviceRepositoryCustom{

	public static Logger logger = LoggerFactory.getLogger(DeviceRepositoryCustromImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDeviceByAreasAndGroup(List<Long> group_ids, Long sub_district_id, Long district_id,
			Long city_id) {
		// TODO Auto-generated method stub
		List<Object[]> devices = new ArrayList<Object[]>();
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_notification_getDeviceByAreaAndGroup");
			storedProcedure.registerStoredProcedureParameter("group_ids", String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("district_id", Long.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("sub_district_id", Long.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter("city_id", Long.class, ParameterMode.IN);
			
			String groupIdStr = "";
			if(group_ids != null) {
				groupIdStr = group_ids.stream().map(Object::toString).collect(Collectors.joining(","));
			}
			storedProcedure.setParameter("group_ids", groupIdStr);
			storedProcedure.setParameter("district_id", district_id);
			storedProcedure.setParameter("sub_district_id", sub_district_id);
			storedProcedure.setParameter("city_id", city_id);
			storedProcedure.execute();
			devices = storedProcedure.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("get device by area and group -> {}", e.getMessage());
		}
		return devices;
	}

	@Override
	public List<Object[]> getDeviceBySubStore(long st_id) {
		// TODO Auto-generated method stub
		List<Object[]> devices = new ArrayList<Object[]>();
		try {
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_device_getDeviceThatSubStore");
		storedProcedure.registerStoredProcedureParameter("storeId", Long.class, ParameterMode.IN);
		
		storedProcedure.setParameter("storeId", st_id);
		storedProcedure.execute();
		devices = storedProcedure.getResultList();
		} catch (Exception e) {
			logger.error("get device of user that sub store -> {}", e.getMessage());
		}
		return devices;
	}

	@Override
	public List<Object[]> getUserInRangeRp() {
		// TODO Auto-generated method stub
		List<Object[]> users = new ArrayList<Object[]>();
		try {
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("prc_getUserInRangeRp");
		storedProcedure.execute();
		users = storedProcedure.getResultList();
		} catch (Exception e) {
			logger.error("get user in range relief point -> {}", e.getMessage());
		}
		return users;
	}

}
