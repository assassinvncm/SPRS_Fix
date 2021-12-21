package com.api.mapper.proc_mapper;

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import com.api.entity.Device;

import com.api.dto.ReportResultDto;

import com.api.entity.Store;
import com.api.entity.User;

@Component
public class ProcedureMapper {
	public List<Store> getStoreByStatusOrType_Mapper(List<Object[]> lstObj){
		List<Store> rs = new ArrayList<Store>();
		for(Object[] obj : lstObj) {
			Store st = new Store();
			st.setId(((BigInteger) obj[0]).longValue());
			st.setClose_time((Time)obj[1]);
			st.setDescription((String)obj[2]);
			st.setName((String)obj[3]);
			st.setOpen_time((Time)obj[4]);
			st.setStatus((Integer) obj[5]);
			rs.add(st);
		}
		return rs;
	}
	

	public List<Device> getDevice(List<Object[]> lstObj){
		List<Device> rs = new ArrayList<Device>();
		for(Object[] obj : lstObj) {
			Device d = new Device();
			d.setId(((BigInteger) obj[0]).longValue());
			d.setToken((String)obj[1]);
			//d.setAddress((String)obj[2]);
			User u = new User();
			u.setId(((BigInteger) obj[3]).longValue());
			d.setUser(u);
			d.setSerial((String)obj[4]);
			rs.add(d);
		}
		return rs;
	}
	
	public List<ReportResultDto> reportMapping(List<Object[]> lstObj) {
		List<ReportResultDto> rs = new ArrayList<ReportResultDto>();
		for(Object[] obj : lstObj) {
			ReportResultDto rpRs = new ReportResultDto();
			rpRs.setDay((Integer) obj[0]);
			rpRs.setMonth((Integer) obj[1]);
			rpRs.setYear((Integer) obj[2]);
			rpRs.setType_point((Integer) obj[3]);
			rpRs.setTotal(((BigInteger) obj[4]).doubleValue());
			rs.add(rpRs);
		}
		return rs;
	}
}
