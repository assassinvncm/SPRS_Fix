package com.api.service;

import java.util.List;

import com.api.dto.RequestDto;
import com.api.entity.Organization;
import com.api.entity.Request;

public interface RequestService {
	
	List<Request> getRequestbyOrganization(Long id);
	
	List<Request> getRequestbySysAdmin(Long id);
	
	List<RequestDto> filterRequestSysAdmin(Long gid,String status, String search);
	List<RequestDto> filterRequestOrgAdmin (Long oid,String status);
	Request handleRequest(Request req);
	
	void acceptRequest(List<Long> rId ,Long uid);
	
	void RejectRequest(List<Long> rId ,Long uid);
}
