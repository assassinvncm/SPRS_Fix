package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Group;
import com.api.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
	Optional<Permission> findById(Long id);
	
	Permission findByName(String name);

	@Query("select per from Permission per where per.level = :level")
	List<Permission> getAllPermissionByLevel(@Param("level") int level);
}
