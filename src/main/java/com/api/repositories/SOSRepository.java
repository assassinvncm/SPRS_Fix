package com.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.SOS;
import com.api.entity.User;

public interface SOSRepository  extends JpaRepository<SOS, Long>{
//	SOS findbyUser_sos(User u);
}
