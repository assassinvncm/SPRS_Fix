package com.api.repositories.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.api.dto.ReliefPointFilterDto;
import com.api.entity.ReliefPoint;
import com.api.repositories.ReliefPointRepositoryCustom;

@Repository
public class ReliefPointRepositoryCustomImpl implements ReliefPointRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ReliefPoint> findByTypeOrStatus(Long uId, ReliefPointFilterDto reliefPointFilterDto) {
		// TODO Auto-generated method stub

		List<Long> types = reliefPointFilterDto.getTypes();
		Boolean status = reliefPointFilterDto.getStatus();

		//HashMap<String, String> sort = reliefPointFilterDto.getSort();
		Boolean sort = reliefPointFilterDto.getSort();

		int offset = reliefPointFilterDto.getPageSize() * (reliefPointFilterDto.getPageIndex() - 1);
		int limit = reliefPointFilterDto.getPageSize();

		String queryString = "select DISTINCT rp from User u inner join u.reliefPoints rp where u.id = :uId ";

		Map<Integer, String> params = new HashMap<Integer, String>();
		int count = 1;
		params.put(count, "uId");
		//types
		if (null != types && !types.isEmpty()) {
			String[] queryArr = queryString.split("where");
			queryString = queryArr[0].concat("INNER JOIN rp.reliefInformations rif INNER JOIN rif.item i").concat(" ").concat("where").concat(" ").concat(queryArr[1]);
			queryString = queryString.concat(" ").concat("AND i.id IN (:types)");
			count++;
			params.put(count, "types");
		}
		
		//status
		if (null != status) {
			queryString = queryString.concat(" ").concat("AND rp.status = :status");
			count++;
			params.put(count, "status");
		}
		
		//sort
//		for(Map.Entry<String, String> entry : sort.entrySet()) {
//			String name = entry.getKey();
//		    String typeSort = entry.getValue();
//		    queryString = queryString.concat(" ").concat("ORDER BY").concat(" ").concat("rp.name").concat(" ").concat(typeSort);
//		    
//		}
		if(null != sort) {
			String typeSort = null;
			if(sort) {
				typeSort = "asc";
			}else {
				typeSort = "desc";
			}
			queryString = queryString.concat(" ").concat("ORDER BY").concat(" ").concat("rp.create_time").concat(" ").concat(typeSort);
		}
		
		TypedQuery<ReliefPoint> query = em.createQuery(queryString, ReliefPoint.class).setFirstResult(offset)
				.setMaxResults(limit);

		
		
		//set params
		for(Map.Entry<Integer, String> entry : params.entrySet()) {
			Integer position = entry.getKey();
		    String param = entry.getValue();
		    
		    if(param.equals("uId")) {
		    	query.setParameter("uId", uId);
		    }else if(param.equals("types")) {
		    	query.setParameter("types", types);
		    }else if(param.equals("status")) {
		    	query.setParameter("status", status);
		    }
		    
		}

		List<ReliefPoint> reliefPoints = query.getResultList();
		return reliefPoints;

	}
	
	@Override
	public List<ReliefPoint> findByTypeOrStatusEv(Long uId, ReliefPointFilterDto reliefPointFilterDto) {
		// TODO Auto-generated method stub

		List<Long> types = reliefPointFilterDto.getTypes();
		Boolean status = reliefPointFilterDto.getStatus();

		//HashMap<String, String> sort = reliefPointFilterDto.getSort();
		Boolean sort = reliefPointFilterDto.getSort();

		int offset = reliefPointFilterDto.getPageSize() * (reliefPointFilterDto.getPageIndex() - 1);
		int limit = reliefPointFilterDto.getPageSize();
		 
		String queryString = "SELECT DISTINCT rp FROM User u inner join u.user_relief rp where u.id = :uId ";

		Map<Integer, String> params = new HashMap<Integer, String>();
		int count = 1;
		params.put(count, "uId");
		//types
		if (null != types && !types.isEmpty()) {
			String[] queryArr = queryString.split("where");
			queryString = queryArr[0].concat("INNER JOIN rp.reliefInformations rif INNER JOIN rif.item i").concat(" ").concat("where").concat(" ").concat(queryArr[1]);
			queryString = queryString.concat(" ").concat("AND i.id IN (:types)");
			count++;
			params.put(count, "types");
		}
		
		//status
		if (null != status) {
			queryString = queryString.concat(" ").concat("AND rp.status = :status");
			count++;
			params.put(count, "status");
		}
		
		//sort
//		for(Map.Entry<String, String> entry : sort.entrySet()) {
//			String name = entry.getKey();
//		    String typeSort = entry.getValue();
//		    queryString = queryString.concat(" ").concat("ORDER BY").concat(" ").concat("rp.name").concat(" ").concat(typeSort);
//		    
//		}
		if(null != sort) {
			String typeSort = null;
			if(sort) {
				typeSort = "asc";
			}else {
				typeSort = "desc";
			}
			queryString = queryString.concat(" ").concat("ORDER BY").concat(" ").concat("rp.create_time").concat(" ").concat(typeSort);
		}
		
		TypedQuery<ReliefPoint> query = em.createQuery(queryString, ReliefPoint.class).setFirstResult(offset)
				.setMaxResults(limit);

		
		
		//set params
		for(Map.Entry<Integer, String> entry : params.entrySet()) {
			Integer position = entry.getKey();
		    String param = entry.getValue();
		    
		    if(param.equals("uId")) {
		    	query.setParameter("uId", uId);
		    }else if(param.equals("types")) {
		    	query.setParameter("types", types);
		    }else if(param.equals("status")) {
		    	query.setParameter("status", status);
		    }
		    
		}

		List<ReliefPoint> reliefPoints = query.getResultList();
		return reliefPoints;

	}
	
	//private 

}
