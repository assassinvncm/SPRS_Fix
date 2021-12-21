package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
