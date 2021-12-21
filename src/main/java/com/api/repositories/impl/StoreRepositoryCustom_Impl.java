package com.api.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.dto.StoreDto;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.StoreRepository;
import com.api.repositories.custom.StoreRepositoryCustom;

@Repository
public class StoreRepositoryCustom_Impl implements StoreRepositoryCustom{
	@PersistenceContext
	private EntityManager em;
	
	@Autowired ProcedureMapper mapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<StoreDto> filterStoreByStatusType(long user_id, int status, String type) {
		StoredProcedureQuery storedProcedure = em.createNamedStoredProcedureQuery("prc_store_getStoreByTypeOrStatus");
		storedProcedure.setParameter(0, user_id);
		storedProcedure.setParameter(1, status);
		storedProcedure.setParameter(2, type);
		storedProcedure.execute();
		return mapper.getStoreByStatusOrType_Mapper(storedProcedure.getResultList());
	}
}
