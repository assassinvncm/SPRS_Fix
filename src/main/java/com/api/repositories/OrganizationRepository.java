package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{
	
	Optional<Organization> findById(Long id);
	
	@Query("Select u.organization from User u inner join u.organization where u.id = :uid")
	Optional<Organization> findByUserId(@Param("uid") Long id);
}
