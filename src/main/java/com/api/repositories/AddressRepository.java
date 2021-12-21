package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
