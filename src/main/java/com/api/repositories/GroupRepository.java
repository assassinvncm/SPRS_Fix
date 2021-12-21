package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Group;
import com.api.entity.SubDistrict;

public interface GroupRepository extends JpaRepository<Group, Long>{
	Group findByName(String name);
	
	Optional<Group> findById(Long id);
	
	Group findByCode(String code);

	@Query("select gr from Group gr where gr.level = :level")
	List<Group> getAllGroupByLevel(@Param("level") int level);
}
