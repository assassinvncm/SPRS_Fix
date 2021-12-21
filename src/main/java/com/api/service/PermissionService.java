package com.api.service;

import java.util.List;

import com.api.entity.Group;
import com.api.entity.Permission;

public interface PermissionService {
	List<Permission> getAll();
	List<Permission> getOwnPermission(Long user_id);
	List<Permission> getOwnPermission_Mobile(Long user_id);
	Permission getById(Long id);
	Permission updatePermission(Permission p, Long id);
	Permission createPermission(Permission p);
	List<Permission> getAllPermissionAuthoriedByGroup(Long group_id);
	List<Permission> getAllPermissionUnAuthoriedByGroup(Long group_id);
}
