package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Group;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;
import com.api.service.GroupService;
import com.exception.AppException;
import com.ultils.Constants;

@Service
public class GroupServiceImpl implements GroupService{

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	GroupRepository groupRepo;

	@Override
	public List<Group> getAllGroupAuthoriedByUser(Long user_id) {
		User u = userRepo.getById(user_id);
		if (u == null) {
			throw new AppException(403, "Username is not existed!");
		}
		List<Group> lsRs = u.getGroups_user();
		return lsRs;
	}

	@Override
	public List<Group> getAllGroupUnAuthoriedByUser(Long user_id) {
		List<Group> lstRs = new ArrayList<Group>();
		User u = userRepo.getById(user_id);
		if (u == null) {
			throw new AppException(403, "Username is not existed!");
		}
		List<Group> lstAuthoried = u.getGroups_user();
		List<Group> lstAll = groupRepo.findAll();
		for (Group group : lstAll) {
			int check = 1;
			for (Group group2 : lstAuthoried) {
				if(group2.getId() == group.getId()) {
					check = 0;
				}
				if(group.getCode().equals(Constants.SYSTEM_ADMIN_PER_CODE) || group.getCode().equals(Constants.ORG_ADMIN_PER_CODE)) {
					check = 0;
				}
			}
			if(check == 1) {
				lstRs.add(group);
			}
		}
		return lstRs;
	}

	@Override
	public Group getById(Long id) {
		Optional<Group> gr = groupRepo.findById(id);
		if (gr.isEmpty()) {;
			throw new AppException(404, "Group is not existed!");
		}
		return gr.get();
	}

	@Override
	public Group createGroup(Group g) {
		Group gr = groupRepo.findByName(g.getName());
		if (gr != null) {
			throw new AppException(404, "Group is existed!");
		} else {
			groupRepo.save(g);
		}
		return g;
	}

	@Override
	public Group deleteGroup(Long id) {
		Optional<Group> gr = groupRepo.findById(id);
		if (gr.isEmpty()) {
			throw new AppException(404, "Group is not existed!");
		}
		groupRepo.deleteById(gr.get().getId());
		return gr.get();
	}

	@Override
	public Group updateGroup(Group g, Long id) {
		Optional<Group> gr = groupRepo.findById(id);
		if (gr.isEmpty()) {
			throw new AppException(404, "Group is not existed!");
		}
		return groupRepo.save(g);
	}

	@Override
	public List<Group> getAllGroupForRegister(int level) {
		List<Group> lstRs = groupRepo.getAllGroupByLevel(level);
		return lstRs;
	}

	@Override
	public List<Group> getAll() {
		List<Group> lstRs = new ArrayList<Group>();
		List<Group> lstTemp = groupRepo.findAll();
		lstTemp.forEach(g ->{
			if(!g.getCode().equals(Constants.SYSTEM_ADMIN_PER_CODE) && !g.getCode().equals(Constants.ORG_USER_PER_CODE)&& !g.getCode().equals(Constants.ORG_ADMIN_PER_CODE)) {
				lstRs.add(g);
			}
		});
		return lstRs;
	}
	
}
