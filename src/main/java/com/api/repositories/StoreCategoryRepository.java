package com.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.StoreCategory;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long>{

	Optional<StoreCategory> findByName(String name);
//	@Query("insert into sprs_category values (?1)")
//	void insertCategory(String name);
}
