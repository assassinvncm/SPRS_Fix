package com.api.service;

import java.util.List;

import com.api.entity.Group;

public interface GroupService {
	List<Group> getAllGroupAuthoriedByUser(Long user_id);
	List<Group> getAllGroupUnAuthoriedByUser(Long user_id);
	List<Group> getAll();
	List<Group> getAllGroupForRegister(int level);
	Group getById(Long id);
	Group createGroup(Group g);
	Group deleteGroup(Long id);
	Group updateGroup(Group g, Long id);
}
