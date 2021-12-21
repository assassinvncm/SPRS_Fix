package com.api.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;

public interface ReliefPointRepository extends JpaRepository<ReliefPoint, Long>, ReliefPointRepositoryCustom {

	@Query("select rp from ReliefPoint rp inner join rp.address address inner join address.subDistrict sd inner join sd.district d inner join d.city  where d.city.id = :cId")
	List<ReliefPoint> findReliefPointByArea(@Param("cId") long id);

//	@Query("SELECT rp FROM User u inner join u.user_relief rp WHERE :name IS NULL OR :name = '' OR u.username LIKE %:name%")
//	Page<ReliefPoint> getEvent(@Param("name") String name, Pageable pageable);

	@Query("select rp from User u inner join u.reliefPoints rp where u.id = :uId and rp.id = :rpId")
	Optional<ReliefPoint> findByIdAndUser(@Param("rpId") Long rpId, @Param("uId") Long uId);
	
	@Query("select COUNT(rp) from User u inner join u.reliefPoints rp where u.id = :uId and rp.close_time > :currentTime")
	long getTotalRpByTime(@Param("uId") Long uId, @Param("currentTime") Timestamp currentTime);

	@Query("select DISTINCT rp from Organization o inner join o.reliefs rp where o.id = :oId and (:p_status = 2 OR rp.status = :p_status)"
			+ " and (:name IS NULL OR :name = '' OR rp.name LIKE %:name%)")
	Page<ReliefPoint> getOwnOrgReliefPoint(@Param("oId") Long oId,@Param("p_status") int p_status, @Param("name") String name, Pageable pageable);

	@Query("select u from ReliefPoint rp inner join rp.relief_user u where rp.id = :rp_id")
	List<User> getUByRpId(@Param("rp_id") Long rp_id);

	@Transactional
	@Procedure(procedureName = "prc_assign_event")
	String assignRef(@Param("p_u_id") long p_u_id, @Param("p_relief_id") long p_relief_id);

	@Transactional
	@Procedure(procedureName = "prc_unassign_event")
	String unAssignRef(@Param("p_u_id") long p_u_id, @Param("p_relief_id") long p_relief_id);
	
	@Transactional
	@Procedure(procedureName = "prc_event_delete")
	String deleteEvent(@Param("p_id") long p_id);

	@Modifying
	@Transactional
	@Query("update ReliefPoint rp set rp.status = :status where rp.id = :rId")
	void updateUser(@Param("rId") Long rId, @Param("status") String status);

//	@Query("select rp from User u inner join u.reliefPoints rp inner join rp.reliefInformations rif inner join rif.item i where u.id = :uId and (rp.status =:status or i.id = :typeId)")
//	Slice<ReliefPoint> findByTypeOrStatus(@Param("uId") Long uId,@Param("typeId") Long typeId ,@Param("status") Boolean status,Pageable pageRequest);

	// Optional<ReliefPoint> findReliefPoint();

//	@Query("select DISTINCT rp from User u inner join u.reliefPoints rp " 
//			+ "INNER JOIN rp.reliefInformations rif "
//			+ "INNER JOIN rif.item i "
//			+ "WHERE u.id = :uId AND (  i.id = :types) AND ( rp.status = :status) "
//			+ "ORDER BY rp.create_time")
//	Page<ReliefPoint> findByTypeOrStatys_v2(@Param("uId") long uId, @Param("types") long type_id,
//			@Param("status") Boolean status, @Param("typeSort") String typeSort, Pageable pageable);
}
