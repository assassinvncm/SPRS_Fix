package com.api.repositories;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.repositories.custom.UserRepositoryCustom;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	User findByUsername(String username);

	Optional<User> findByPhone(String phone);

//	User saveUser(User user);

	@Modifying
	@Transactional
	@Query("update User u set u.password = :newPass where u.id = :uId")
	void updateUser(@Param("uId") Long id, @Param("newPass") String newPass);

	@Query("SELECT u FROM User u inner join u.groups_user gu WHERE :name IS NULL OR :name = '' OR u.username LIKE %:name% and gu.id in (1,7)")
	Page<User> searchByNameLike(@Param("name") String name, Pageable pageable);

	@Query("select u from User u where u.organization.id = :orgId and u.id != :uId and (:name IS NULL OR :name = '' OR u.username LIKE %:name%)")
	Page<User> getOwnOrganizeUser(@Param("orgId") Long orgId, @Param("uId") Long uId, @Param("name") String name,
			Pageable pageable);//

	@Transactional
	@Procedure(procedureName = "prc_grant_group")
	String grantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);

	@Transactional
	@Procedure(procedureName = "prc_ungrant_group")
	String ungrantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);

	@Transactional
	@Procedure(procedureName = "prc_grant_permission")
	String grantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);

	@Transactional
	@Procedure(procedureName = "prc_ungrant_permission")
	String ungrantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);

	@Query("SELECT u FROM User u INNER JOIN u.user_sos s WHERE s.id = :SOS_id")
	Optional<User> getUserBySosId(@Param("SOS_id") long id);

	@Query("SELECT u FROM User u  WHERE u.status = :status") 
	List<User> getUserByStatus(@Param("status") String status);

	@Query("select u from Organization org inner join org.user u where org.id = :org_id") 
	List<User> getUserInOrg(@Param("org_id") long org_id);

	@Query("SELECT DISTINCT u FROM User u INNER JOIN u.groups_user gr WHERE ('' = :exceptGroup OR gr.code != :exceptGroup) AND gr.code IN :filterGroup AND u.status IN :filterStatus AND ('' = :search OR u.username LIKE %:search% ) ")
	Page<User> getUserByGroup(@Param("exceptGroup") String exceptGroup, @Param("filterGroup") List<String> filterGroup,
			@Param("filterStatus") List<String> filterStatus, @Param("search") String search, Pageable pageable);

	@Query("SELECT u FROM User u  WHERE u.id = :id AND u.status != :status")
	Optional<User> findUserByIdAndNotStatus(@Param("id") Long id, @Param("status") String status);

	@Query("SELECT u FROM User u  WHERE u.id = :id AND u.status = :status")
	Optional<User> findUserByIdAndStatus(@Param("id") Long id, @Param("status") String status);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE sprs_v1.sprs_users INNER JOIN sprs_v1.sprs_organization ON sprs_users.organization_id = sprs_organization.id\r\n"
			+ "SET sprs_users.status = :status \r\n"
			+ "WHERE sprs_organization.id = :org",nativeQuery = true)
	void updateStatusUserByOrg(@Param("org") Long orgId,@Param("status") String status);

}
