package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.api.dto.*;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.exception.AppException;
import com.ultils.Constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

	private RequestServiceImpl requestServiceImplUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		requestServiceImplUnderTest = new RequestServiceImpl();
		requestServiceImplUnderTest.requestRepository = mock(RequestRepository.class);
		requestServiceImplUnderTest.organizationRepository = mock(OrganizationRepository.class);
		requestServiceImplUnderTest.mapStructMapper = mock(MapStructMapper.class);
	}

	@Test
	void testGetRequestbyOrganization_UTCID01() {
		// Setup
		// Configure RequestRepository.findByOrganization_id(...).
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Organization org1 = new Organization();
		org1.setId(2L);
		org1.setName("To chuc tu thien");
		req1.setOrganization(org1);

		requests.add(req1);
		when(requestServiceImplUnderTest.requestRepository.findByOrganization_id(org1.getId())).thenReturn(requests);

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbyOrganization(org1.getId());

		// Verify the results
		assertEquals(requests.get(0).getId(), result.get(0).getId());
	}

	@Test
	void testGetRequestbyOrganization_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findByOrganization_id(0L))
				.thenReturn(Collections.emptyList());

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbyOrganization(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testGetRequestbySysAdmin_UTCID01() {
		// Setup
		// Configure RequestRepository.findByGroup_id(...).
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);

		requests.add(req1);
		when(requestServiceImplUnderTest.requestRepository.findByGroup_id(requests.get(0).getGroup().getId()))
				.thenReturn(requests);

		// Run the test
		final List<Request> result = requestServiceImplUnderTest
				.getRequestbySysAdmin(requests.get(0).getGroup().getId());

		// Verify the results
		assertEquals(requests.get(0).getGroup().getId(), result.get(0).getGroup().getId());
		assertEquals(requests.get(0).getGroup().getName(), result.get(0).getGroup().getName());
	}

	@Test
	void testGetRequestbySysAdmin_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findByGroup_id(0L)).thenReturn(Collections.emptyList());

		// Run the test
		final List<Request> result = requestServiceImplUnderTest.getRequestbySysAdmin(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestSysAdmin_UTCID01() {
		// Setup
		// Configure RequestRepository.filterRequestOfAdmin(...).

		List<RequestDto> requestDtos = new ArrayList<RequestDto>();
		RequestDto reqDto1 = new RequestDto();
		reqDto1.setId(1L);
		requestDtos.add(reqDto1);
		requestDtos.add(reqDto1);
		
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);;
		
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfAdmin(0L, "status", "search")).thenReturn(requests);
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(requests)).thenReturn(requestDtos);

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestSysAdmin(0L, "status", "search");

		// Verify the results
		assertEquals(requestDtos.get(0).getId(), result.get(0).getId());
	}

	@Test
	void testFilterRequestSysAdmin_UTCID02() {
		// Setup
		
		List<RequestDto> requestDtos = new ArrayList<RequestDto>();
		RequestDto reqDto1 = new RequestDto();
		reqDto1.setId(1L);
		requestDtos.add(reqDto1);
		requestDtos.add(reqDto1);
		
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);
		
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfAdmin(0L, "status", "search"))
				.thenReturn(Collections.emptyList());
		
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(requests)).thenReturn(Collections.emptyList());

		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestSysAdmin(0L, "status", "search");

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testFilterRequestOrgAdmin_UTCID01() {
		// Setup
		// Configure RequestRepository.filterRequestOfOrgAdmin(...).
		List<RequestDto> requestDtos = new ArrayList<RequestDto>();
		RequestDto reqDto1 = new RequestDto();
		reqDto1.setId(1L);
		requestDtos.add(reqDto1);
		requestDtos.add(reqDto1);
		
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);
		
		//data input
		Long orgId = 2L;
		String status = "active";

		when(requestServiceImplUnderTest.requestRepository.filterRequestOfOrgAdmin(orgId,status))
				.thenReturn(requests);
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(requests)).thenReturn(requestDtos);


		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestOrgAdmin(orgId, status);

		// Verify the results
		assertEquals(requestDtos.get(0).getId(), result.get(0).getId());
	}

	@Test
	void testFilterRequestOrgAdmin_UTCID02() {
		// Setup
		List<RequestDto> requestDtos = new ArrayList<RequestDto>();
		RequestDto reqDto1 = new RequestDto();
		reqDto1.setId(1L);
		requestDtos.add(reqDto1);
		requestDtos.add(reqDto1);
		
		List<Request> requests = new ArrayList<Request>();
		Request req1 = new Request();
		req1.setId(1L);
		Group group1 = new Group();
		group1.setId(3L);
		group1.setName("Admin");
		req1.setGroup(group1);
		
		Long orgId = 2L;
		String status = "active";
		
		when(requestServiceImplUnderTest.requestRepository.filterRequestOfOrgAdmin(orgId, status))
				.thenReturn(Collections.emptyList());
		
		when(requestServiceImplUnderTest.mapStructMapper.lstRequestToRequestDto(requests)).thenReturn(Collections.emptyList());
		
		// Run the test
		final List<RequestDto> result = requestServiceImplUnderTest.filterRequestOrgAdmin(orgId, status);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testHandleRequest_UTCID01() {
		// Setup
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Request request = new Request("type", "accept", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList()));
		Optional<Request> reqOtp = Optional.of(request);
		//Input data
		//Long reqId = 1L;
		
		when(requestServiceImplUnderTest.requestRepository.findById(request.getId())).thenReturn(reqOtp);
		when(requestServiceImplUnderTest.requestRepository.save(request)).thenReturn(request);
		
		// Run the test
		final Request result = requestServiceImplUnderTest.handleRequest(request);

		// Verify the results
		assertEquals(request.getStatus(), result.getStatus());
		assertTrue(result.getUser().getIsActive());
		assertEquals(Constants.USER_STATUS_ACTIVE,result.getUser().getStatus());
		
	}
	
	@Test
	void testHandleRequest_UTCID02() {
		// Setup
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Request request = new Request("type", "reject", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList()));
		Optional<Request> reqOtp = Optional.of(request);
		//Input data
		//Long reqId = 1L;
		
		when(requestServiceImplUnderTest.requestRepository.findById(request.getId())).thenReturn(reqOtp);
		when(requestServiceImplUnderTest.requestRepository.save(request)).thenReturn(request);
		
		// Run the test
		final Request result = requestServiceImplUnderTest.handleRequest(request);

		// Verify the results
		assertEquals(request.getStatus(), result.getStatus());
		assertFalse(result.getUser().getIsActive());
		assertEquals(Constants.USER_STATUS_REJECT,result.getUser().getStatus());
		
	}
	
	@Test
	void testHandleRequest_UTCID03() {
		// Setup
		Request req = new Request();
		req.setId(1L);
		req.setMessage("Send Notification");
		
		when(requestServiceImplUnderTest.requestRepository.findById(any())).thenReturn(Optional.empty());
		
		
		AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			final Request result = requestServiceImplUnderTest.handleRequest(req);
	    });
		
		final String expectedMessage = "Request not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Verify the results
	    assertEquals(expectedMessage,actualMessage);
	}

	@Test
	void testAcceptRequest_UTCID01() {
		// Setup
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Request request = new Request("type", "reject", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList()));
		Optional<Request> reqOtp = Optional.of(request);
		when(requestServiceImplUnderTest.requestRepository.findById(request.getId())).thenReturn(reqOtp);
		when(requestServiceImplUnderTest.requestRepository.save(request)).thenReturn(request);
		
		Long uid = 2L;
		// Run the test
		 requestServiceImplUnderTest.acceptRequest(Arrays.asList(0L), uid);

		// Verify the results
		assertTrue(true);
		
	}
	
	@Test
	void testAcceptRequest_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findById(any())).thenReturn(Optional.empty());
		
		//input
		Long uid = 2L;
		List<Long> lstReqId = new ArrayList<Long>();
		lstReqId.add(3L);
		lstReqId.add(2L);
		
		 AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			 requestServiceImplUnderTest.acceptRequest(lstReqId, uid);
		    });
			
		final String expectedMessage = "request ID not exist";
		String actualMessage = appException.getMessage();
			
		    // Verify the results
		assertEquals(expectedMessage,actualMessage);
		
	}

	@Test
	void testRejectRequest_UTCID01() {
		// Setup
		// Run the test
		final Group group = new Group();
		group.setPlatform(0);
		group.setName("name");
		group.setLevel(0);
		group.setCode("code");
		final Permission permission = new Permission();
		permission.setLevel(0);
		permission.setNode_index(0);
		permission.setNode_from(0);
		permission.setNode_to(0);
		permission.setTo_page("to_page");
		permission.setIcon_name("icon_name");
		permission.setCode("code");
		permission.setName("name");
		group.setGroup_permission(Arrays.asList(permission));
		group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group1 = new Group();
		group1.setPlatform(0);
		group1.setName("name");
		group1.setLevel(0);
		group1.setCode("code");
		final Permission permission1 = new Permission();
		permission1.setLevel(0);
		permission1.setNode_index(0);
		permission1.setNode_from(0);
		permission1.setNode_to(0);
		permission1.setTo_page("to_page");
		permission1.setIcon_name("icon_name");
		permission1.setCode("code");
		permission1.setName("name");
		group1.setGroup_permission(Arrays.asList(permission1));
		group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Group group2 = new Group();
		group2.setPlatform(0);
		group2.setName("name");
		group2.setLevel(0);
		group2.setCode("code");
		final Permission permission2 = new Permission();
		permission2.setLevel(0);
		permission2.setNode_index(0);
		permission2.setNode_from(0);
		permission2.setNode_to(0);
		permission2.setTo_page("to_page");
		permission2.setIcon_name("icon_name");
		permission2.setCode("code");
		permission2.setName("name");
		group2.setGroup_permission(Arrays.asList(permission2));
		group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
				new Address("city", "province", "district",
						new SubDistrict("code", "name",
								new District("code", "name", new City("code", "name", Arrays.asList()),
										Arrays.asList()),
								Arrays.asList()),
						"addressLine", "gPS_Long", "gPS_Lati"),
				Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
		final Request request = new Request("type", "reject", "message",
				Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)),
				new User("username", "phone", "password", "full_name", "dob",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)),
				group1,
				new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description",
						new Address("city", "province", "district",
								new SubDistrict("code", "name",
										new District("code", "name", new City("code", "name", Arrays.asList()),
												Arrays.asList()),
										Arrays.asList()),
								"addressLine", "gPS_Long", "gPS_Lati"),
						Arrays.asList(new User("username", "phone", "password", "full_name", "dob",
								new Address("city", "province", "district",
										new SubDistrict("code", "name",
												new District("code", "name", new City("code", "name", Arrays.asList()),
														Arrays.asList()),
												Arrays.asList()),
										"addressLine", "gPS_Long", "gPS_Lati"),
								Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))),
						Arrays.asList()));
		Optional<Request> reqOtp = Optional.of(request);
		when(requestServiceImplUnderTest.requestRepository.findById(request.getId())).thenReturn(reqOtp);
		when(requestServiceImplUnderTest.requestRepository.save(request)).thenReturn(request);
		
		//input
		Long uid = 2L;
//		List<Long> lstReqId = new ArrayList<Long>();
//		lstReqId.add(3L);
//		lstReqId.add(2L);
		
		// Run the test
		requestServiceImplUnderTest.RejectRequest(Arrays.asList(0L), uid);
		// Verify the results
		assertTrue(true);


		// Verify the results
	}
	
	@Test
	void testRejectRequest_UTCID02() {
		// Setup
		when(requestServiceImplUnderTest.requestRepository.findById(any())).thenReturn(Optional.empty());
		
		//input
		Long uid = 2L;
		List<Long> lstReqId = new ArrayList<Long>();
		lstReqId.add(3L);
		lstReqId.add(2L);
		
		 AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			 requestServiceImplUnderTest.RejectRequest(lstReqId, uid);
		    });
			
		final String expectedMessage = "request ID not exist";
		String actualMessage = appException.getMessage();
			
		    // Verify the results
		assertEquals(expectedMessage,actualMessage);
		
	}

}
