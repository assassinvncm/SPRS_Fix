package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.District;
import com.api.entity.SubDistrict;

public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long>{
	
	@Query("select sb from SubDistrict sb inner join sb.district d inner join d.city c where sb.name LIKE CONCAT('%',:subDistrictName) and d.name LIKE CONCAT('%',:ditrictName) and c.name LIKE CONCAT('%',:cityName)")
	Optional<List<SubDistrict>> findSubDistrict(@Param("subDistrictName") String subDistrictName,@Param("ditrictName") String ditrictName,@Param("cityName") String cityName);
	
	@Query("select sd from SubDistrict sd inner join sd.district where sd.district.id = :districtId")
	List<SubDistrict> findByDistrictId(@Param("districtId") Long id);
}
