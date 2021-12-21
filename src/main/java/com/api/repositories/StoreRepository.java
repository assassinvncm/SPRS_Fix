package com.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.dto.StoreDto;
import com.api.entity.Store;
import com.api.repositories.custom.StoreRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
//	@Query(value = "CALL prc_store_getStoreByTypeOrStatus(:p_user_id,:p_status,:p_type);", nativeQuery = true)
//	Page<Object[]> getStoreByStatusOrType(@Param("p_user_id") Long p_user_id,@Param("p_status") int p_status,@Param("p_type") Long types, Pageable pageable);
	
	@Query("select distinct s from Store s JOIN s.store_category sc where s.users.id = :p_user_id and (:p_status = 3 OR s.status = :p_status) AND (CONCAT(:p_type, '') = '0' OR sc.id = :p_type)")//
	Page<Store> getStoreByStatusOrType(@Param("p_user_id") Long p_user_id,@Param("p_status") int p_status,@Param("p_type") Long p_type, Pageable pageable);
	
//	@Query("select s from Store s JOIN s.store_category sc where s.store_user.id = :p_user_id AND (s.status = :p_status) AND (sc.id = :p_type)")//select s from User u JOIN u.user_store s JOIN s.store_category sc where u.id = :p_user_id and (s.status = :p_status) AND (sc.id = :p_type)
//	Page<Store> getStoreByStatusOrType(@Param("p_user_id") Long p_user_id,@Param("p_status") int p_status,@Param("p_type") Long p_type, Pageable pageable);
	
//	@Transactional
//	@Query(value = "CALL prc_store_delete(:p_id);", nativeQuery = true)
//	Object[] deleteStore(@Param("p_id") Long p_id);
	
	@Transactional
	@Procedure(procedureName = "prc_store_delete")
	String deleteStore(@Param("p_id") long p_id);
}
