package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.api.dto.SPRSResponse;
import com.api.entity.Group;
import com.api.entity.Permission;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.PermissionRepository;
import com.api.repositories.UserRepository;
import com.api.repositories.custom.UserRepositoryCustom;
import com.api.service.PermissionService;
import com.exception.AppException;
import com.ultils.Constants;

@Service
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	PermissionRepository perRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	GroupRepository groupRepo;
	
	@Override
	public List<Permission> getAll() {
		List<Permission> lst = perRepo.findAll();
		return lst;
	}

	@Override
	public Permission getById(Long id) {
		Optional<Permission> gr = perRepo.findById(id);
		if (gr.isEmpty()) {;
			throw new AppException(404, "Permission is not existed!");
		}
		return gr.get();
	}

	@Override
	public Permission updatePermission(Permission p, Long id) {
		Optional<Permission> per = perRepo.findById(id);
		if (per.isEmpty()) {
			throw new AppException(404, "Permission is not existed!");
		}
		return perRepo.save(p);
	}

	@Override
	public Permission createPermission(Permission p) {
		Permission per = perRepo.findByName(p.getName());
		if (per != null) {
			throw new AppException(404, "Permission is existed!");
		}
		return perRepo.save(p);
	}
	
	@Override
	public List<Permission> getAllPermissionAuthoriedByGroup(Long group_id) {
		Group g = groupRepo.getById(group_id);
		if (g == null) {
			throw new AppException(403, "Group is not existed!");
		}
		List<Permission> lsRs = g.getPermissions();
		return lsRs;
	}

	@Override
	public List<Permission> getAllPermissionUnAuthoriedByGroup(Long group_id) {
		List<Permission> lstRs = new ArrayList<Permission>();
		Group g = groupRepo.getById(group_id);
		if (g == null) {
			throw new AppException(403, "Group is not existed!");
		}
		List<Permission> lstAuthoried = g.getPermissions();
		List<Permission> lstAll = perRepo.findAll();
		for (Permission permission : lstAll) {
			int check = 1;
			for (Permission permission2 : lstAuthoried) {
				if(permission2.getId() == permission.getId()) {
					check = 0;
				}
			}
			if(check == 1) {
				lstRs.add(permission);
			}
		}
		return lstRs;
	}

	@Override
	public List<Permission> getOwnPermission(Long user_id) {
		User u = userRepo.getById(user_id);
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		List<Permission> listPermissions = new ArrayList<Permission>();
		u.getGroups_user().forEach(r -> {
			r.getPermissions().forEach(p -> listPermissions.add(p));
		});
		return listPermissions;
	}

	@Override
	public List<Permission> getOwnPermission_Mobile(Long user_id) {
		User u = userRepo.getById(user_id);
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		List<Permission> listPermissions = perRepo.getAllPermissionByLevel(2);
		return listPermissions;
	}

}
