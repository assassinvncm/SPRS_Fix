package com.api.service;

import java.util.List;

import com.api.dto.PagingResponse;
import com.api.dto.RequestDto;
import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.entity.User;

public interface RequestService {
	
	/**
	 * get request by Organization
	 * @param id
	 * @return
	 */
	List<Request> getRequestbyOrganization(Long id);
	
	/**
	 * get request by systemadmin
	 * @param id
	 * @return
	 */
	PagingResponse<RequestDto> getRequestbySysAdmin(User user, String accType, int pageIndex, int pageSize,String search,String statusType);
	
	/**
	 * filter request by system admin
	 * @param gid
	 * @param status
	 * @param search
	 * @return
	 */
	List<RequestDto> filterRequestSysAdmin(Long gid,String status, String search);
	
	/**
	 * filter request by org admin
	 * @param oid
	 * @param status
	 * @return
	 */
	List<RequestDto> filterRequestOrgAdmin (Long oid,String status);
	
	/**
	 * handle request
	 * @param req
	 * @return
	 */
	Request handleRequest(Request req);
	
	/**
	 * accept request
	 * @param rId
	 * @param uid
	 */
	void acceptRequest(List<Long> rId ,Long uid);
	
	/**
	 * reject request
	 * @param rId
	 * @param uid
	 */
	void RejectRequest(List<Long> rId ,Long uid);
}
