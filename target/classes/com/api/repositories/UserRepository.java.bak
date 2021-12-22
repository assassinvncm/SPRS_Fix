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

import com.api.entity.User;
import com.api.repositories.custom.UserRepositoryCustom;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	User findByUsername(String username);

	Optional<User> findByPhone(String phone);

//	User saveUser(User user);

	@Modifying
	@Query("update User u set u.password = :newPass where u.id = :uId")
	void updateUser(@Param("uId") Long id, @Param("newPass") String newPass);
	
	@Transactional
	@Procedure(procedureName  = "prc_grant_group")
	String grantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_ungrant_group")
	String ungrantGroup(@Param("user_id") long user_id, @Param("group_id") long group_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_grant_permission")
	String grantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);
	
	@Transactional
	@Procedure(procedureName  = "prc_ungrant_permission")
	String ungrantPermission(@Param("group_id") long group_id, @Param("permission_id") long permission_id);
	
}
