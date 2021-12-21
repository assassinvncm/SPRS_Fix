package com.api.repositories.impl;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.api.entity.Group;
import com.api.entity.User;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;
import com.api.repositories.custom.UserRepositoryCustom;

@Component
public class UserRepositoryImpl implements UserRepositoryCustom{
	public static final String SQL_Grant_Group = "CALL prc_grant_group";
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Override
//	public int grantGroup(long user_id, long group_id) {
//		String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
//		StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery(dbName + SQL_Grant_Group);
//		
//        query.registerStoredProcedureParameter("user_id", Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("group_id", Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Result_Param", Boolean.class, ParameterMode.OUT);
//        
//        query.setParameter("user_id", (int)user_id);
//        query.setParameter("group_id", (int)group_id);
//
//        int result = ((Number) query.getOutputParameterValue("Result_Param")).intValue();
//        return result;
//	}
}
