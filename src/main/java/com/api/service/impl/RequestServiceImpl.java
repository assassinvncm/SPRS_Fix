package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.dto.NotificationDto;
import com.api.dto.PagingResponse;
import com.api.dto.RequestDto;
import com.api.entity.Notification;
import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.service.RequestService;
import com.exception.AppException;
import com.ultils.Constants;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;

	@Override
	public List<Request> getRequestbyOrganization(Long id) {
		// TODO Auto-generated method stub
		
		return requestRepository.findByOrganization_id(id);
	}

	@Override
	public PagingResponse<RequestDto> getRequestbySysAdmin(User user, String accType, int pageIndex, int pageSize, String search, String statusType) {
		// TODO Auto-generated method stub
		
		Sort sort = Sort.by("timestamp").descending();
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
		Long gid = user.getGroups_user().get(0).getId();
		//List<Request> req = requestRepository.findByGroup_id(id);
		//User r = req.getUser();
		Page<Request> lstRequest = requestRepository.getRequestByAdmin(gid, accType, search, statusType, pageable);

		PagingResponse<RequestDto> pagingResonpne = new PagingResponse<RequestDto>();
		pagingResonpne.setObject(mapStructMapper.lstRequestToRequestDto(lstRequest.toList()));
		pagingResonpne.setTotalPage(lstRequest.getTotalPages());
		pagingResonpne.setTotalRecord(lstRequest.getTotalElements());
		return pagingResonpne;
	}
	

	@Override
	public List<RequestDto> filterRequestSysAdmin(Long gid, String status, String search) {
		// TODO Auto-generated method stub
		
		List<Request> req = requestRepository.filterRequestOfAdmin(gid, status , search);
		 
		return mapStructMapper.lstRequestToRequestDto(req);
	}
	
	@Override
	public List<RequestDto> filterRequestOrgAdmin(Long oid, String status) {
		// TODO Auto-generated method stub
		List<Request> req = requestRepository.filterRequestOfOrgAdmin(oid,status);
		 
		return mapStructMapper.lstRequestToRequestDto(req);
	}

	@Override
	public Request handleRequest(Request request) {
		// TODO Auto-generated method stub
		Request req = requestRepository.findById(request.getId()).orElseThrow(()-> new AppException(405,"Request not exist!"));
		req.setStatus(request.getStatus());
		if(req.getStatus().equals("accept")) {
			req.getUser().setIsActive(true);
			req.getUser().setStatus(Constants.USER_STATUS_ACTIVE);
		}
		if(req.getStatus().equals("reject")) {
			req.getUser().setIsActive(false);
			req.getUser().setStatus(Constants.USER_STATUS_REJECT);
		}
		
		//BeanUtils.copyProperties(request, req);
		return requestRepository.save(req);
	}

	@Override
	public void acceptRequest(List<Long> rid, Long uid) {
		// TODO Auto-generated method stub
		rid.stream().forEach((id) ->{
			Optional<Request> reqOpt = requestRepository.findById(id);
			if(!reqOpt.isPresent()) {
				throw new AppException(405,"request ID not exist");
			}
			Request req = reqOpt.get();
			req.setStatus(Constants.REQUEST_STATUS_ACCEPT);
			req.getUser().setIsActive(true);
			req.getUser().setStatus(Constants.USER_STATUS_ACTIVE);
			requestRepository.save(req);
		});
	}

	@Override
	public void RejectRequest(List<Long> rid, Long uid) {
		// TODO Auto-generated method stub
		rid.stream().forEach((id) ->{
			Optional<Request> reqOpt = requestRepository.findById(id);
			if(!reqOpt.isPresent()) {
				throw new AppException(405,"request ID not exist");
			}
			Request req = reqOpt.get();
			req.setStatus(Constants.REQUEST_STATUS_REJECT);
			req.getUser().setIsActive(false);
			req.getUser().setStatus(Constants.USER_STATUS_REJECT);
			requestRepository.save(req);
		});
	}




}
