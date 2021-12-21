package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
