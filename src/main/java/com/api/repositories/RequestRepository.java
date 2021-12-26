package com.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
	
	List<Request> findByOrganization_id(Long id);
	
	List<Request> findByGroup_id(Long id);
	
	@Query("select r from User u INNER JOIN u.request r INNER JOIN r.group  where r.group.id = :groupId and r.status = :status and ('' = :search OR u.username LIKE CONCAT('%',:search,'%') OR u.full_name LIKE CONCAT('%',:search,'%')) ")
	List<Request> filterRequestOfAdmin(@Param("groupId") Long id,@Param("status") String status,@Param("search") String search);
	
	@Query("select r from Request r inner join r.organization where r.organization.id = :organizationId and r.status = :status")
	List<Request> filterRequestOfOrgAdmin(@Param("organizationId") Long id,@Param("status") String status);
	
	@Query("Select r from Request r INNER JOIN r.group g INNER JOIN r.user u Where g.id = :gid "
			+ "AND ('' = :accType OR r.type = :accType) "
			+ "AND ('' = :search OR u.full_name LIKE %:search% OR u.username LIKE %:search%) "
			+ "AND ('' = :statusType OR r.status = :statusType)")
	Page<Request> getRequestByAdmin(@Param("gid") long gId,@Param("accType") String accType, 
			@Param("search") String search,@Param("statusType") String statusType, Pageable pageable);
}
