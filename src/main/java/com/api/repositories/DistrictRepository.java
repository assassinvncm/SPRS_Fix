package com.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.District;

public interface DistrictRepository extends JpaRepository<District, Long>{
	
	@Query("select d from District d inner join d.city where d.city.id = :cityId")
	List<District> findByCityId(@Param("cityId") Long id);
}
