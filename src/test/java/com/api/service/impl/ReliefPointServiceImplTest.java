/**
 * 
 */
package com.api.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.api.dto.*;
import com.api.entity.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.GrantAccessDto;
import com.api.dto.GroupDto;
import com.api.dto.ItemDto;
import com.api.dto.OrganizationDto;
import com.api.dto.PermissionChildrenDto;
import com.api.dto.PermissionDto;
import com.api.dto.ReliefInformationDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.RequestDto;
import com.api.dto.SearchFilterDto;
import com.api.dto.SubDistrictDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.Group;
import com.api.entity.Image;
import com.api.entity.Item;
import com.api.entity.Organization;
import com.api.entity.Permission;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Request;
import com.api.entity.SubDistrict;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.ReliefPointService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import com.api.dto.*;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.AmazonClient;
import com.api.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author MY PC
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReliefPointServiceImplTest {
	
	
	
	@Mock
	ReliefPointRepository reliefPointRepository;
	
	@Mock
	MapStructMapper mapStructMapper;

	@Mock
	AddressService addressService;
	
	@Mock
	NotificationService notificationService;
	
    @Mock
    private UserRepository mockUserRepo;
    
    @Mock
    private AmazonClient mockAmazonClient;
	
	@InjectMocks
	private ReliefPointService reliefPointService = new ReliefPointServiceImpl();

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointById(java.lang.Long)}.
	 */
	@Test
	void testGetReliefPointById_UTCID01() {
		//set input data for method
		long id = 2;
		
		//mock data
		ReliefPoint rp = new ReliefPoint();
		User user = new User();
		user.setId(2L);
		rp.setUser_rp(user);
		//mock
		Mockito.when(reliefPointRepository.getById(id)).thenReturn(rp);
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(2);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(rp)).thenReturn(rpDto);
		
		//call method
		ReliefPointDto rpDtoRes = reliefPointService.getReliefPointById(id);
		assertEquals(rpDto.getId(), rpDtoRes.getId());
	}
	
	@Test
	void testGetReliefPointByIdORG_UTCID01() {
		//set input data for method
		long id = 2L;
		
		//mock data
		ReliefPointDto rpDto = new ReliefPointDto();
		UserDto userDto = new UserDto();
		userDto.setId(2L);
		rpDto.setUser_rp(userDto);
		
		ReliefPoint rp = new ReliefPoint();
		User user = new User();
		user.setId(2L);
		rp.setUser_rp(user);
		
		//mock
		Mockito.when(reliefPointRepository.getById(id)).thenReturn(rp);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(any(ReliefPoint.class))).thenReturn(rpDto);
		
		//call method
		ReliefPointDto rpDtoRes = reliefPointService.getReliefPointByIdORG(id);
		
		//verify
		assertEquals(rpDto.getId(), rpDtoRes.getId());
	}
	
//	@Test
//    void testGetReliefPointByUser() {
//        // Setup
//		User user = new User();
//		user.setId(2L);
//		
//        // Run the test
//        final ReliefPoint result = reliefPointService.getReliefPointByUser(user);
//
//        // Verify the results
//    }
		

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#createReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
	@Test
	void testCreateReliefPoint_UTCID01() {
		//set data input for method
		User u = new User();
		u.setId(1);
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		//data mock
		ReliefPoint rp = new ReliefPoint();
		List<ReliefInformation> lstRpInfor = new ArrayList<ReliefInformation>();
		ReliefInformation rpInfor = new ReliefInformation();
		lstRpInfor.add(rpInfor);
		rp.setReliefInformations(lstRpInfor);
		
		
		Mockito.when(mapStructMapper.reliefPointDtoToreliefPoint(rpDto)).thenReturn(rp);
		
		rp.setId(2);
		Mockito.when(reliefPointRepository.save(rp)).thenReturn(rp);
		Mockito.doNothing().when(notificationService).sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
		
		//call method
		ReliefPoint rpRes = reliefPointService.createReliefPoint(rpDto,u);
		assertEquals(rp.getId(), rpRes.getId());
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#updateReliefPoint(com.api.dto.ReliefPointDto)}.
	 */
	@Test
	void testUpdateReliefPoint_UTCID01() {
		
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(null);
		
		AppException appException = assertThrows(AppException.class, () -> {
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Relief point is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID02() {
		
		//set data input for reliefpointDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(0);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		AppException appException = assertThrows(AppException.class, () -> {
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Id of Address is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID03() {
		
		//set data input for reliefpointDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		
		List<ReliefInformationDto> lstReliefInformationDto = new ArrayList<ReliefInformationDto>();
		
		ReliefInformationDto reliefInformationDto = new ReliefInformationDto();
		reliefInformationDto.setId(2);
		ItemDto itemDto = new ItemDto();
		itemDto.setId(0);
		reliefInformationDto.setItem(itemDto);
		lstReliefInformationDto.add(reliefInformationDto);
		
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		rpDto.setReliefInformations(lstReliefInformationDto);
		
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			reliefPointService.updateReliefPoint(rpDto);
	    }); 
		String expectedMessage = "Id of Item is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	void testUpdateReliefPoint_UTCID04() {
		
		//set data input for reliefpointDto
		AddressDto addressDto = new AddressDto();
		addressDto.setId(2);
		CityDto c = new CityDto();
		addressDto.setCity(c);
		DistrictDto d = new DistrictDto();
		addressDto.setDistrict(d);
		SubDistrictDto sd = new SubDistrictDto();
		addressDto.setSubDistrict(sd);
		addressDto.setAddressLine1("Ha Bằng");
		addressDto.setGPS_lati("21.243124323");
		addressDto.setGPS_long("24.154353443");
		
		List<ReliefInformationDto> lstReliefInformationDto = new ArrayList<ReliefInformationDto>();
		
		ReliefInformationDto reliefInformationDto = new ReliefInformationDto();
		reliefInformationDto.setId(2);
		ItemDto itemDto = new ItemDto();
		itemDto.setId(3);
		reliefInformationDto.setItem(itemDto);
		lstReliefInformationDto.add(reliefInformationDto);
		
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		rpDto.setAddress(addressDto);
		rpDto.setReliefInformations(lstReliefInformationDto);
		
		//mock
		Mockito.when(reliefPointRepository.getById(rpDto.getId())).thenReturn(new ReliefPoint());
		
		ReliefPoint rp = new ReliefPoint();
		rp.setId(1);
		List<ReliefInformation> lstRpInfor = new ArrayList<ReliefInformation>();
		ReliefInformation rpInfor = new ReliefInformation();
		lstRpInfor.add(rpInfor);
		rp.setReliefInformations(lstRpInfor);
		Mockito.when(mapStructMapper.reliefPointDtoToreliefPoint(rpDto)).thenReturn(rp);
		Mockito.when(addressService.mapAddress(rpDto.getAddress())).thenReturn(new Address());
		Mockito.when(reliefPointRepository.saveAndFlush(rp)).thenReturn(rp);
		
		//call method
		ReliefPoint rcheck = reliefPointService.updateReliefPoint(rpDto);
		
		//verify
		assertEquals(1, rcheck.getId());
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPointByIdAndUser(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testGetReliefPointByIdAndUser_UTCID01() {
		//init param
		long rpId = 2;
		long uId =3;
		
		//mock
		ReliefPoint rp = new ReliefPoint();
		ReliefPointDto rpDto = new ReliefPointDto();
		rpDto.setId(1);
		Optional<ReliefPoint> reliefPoint = Optional.of(rp);
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findByIdAndUser(rpId, uId)).thenReturn(reliefPoint);
		Mockito.when(mapStructMapper.reliefPointToreliefPointDto(rp)).thenReturn(rpDto);
		
		//call method
		ReliefPointDto rcheck = reliefPointService.getReliefPointByIdAndUser(rpId, uId);
		
		//verify
		assertEquals(1, rcheck.getId());
	}
	
	@Test
	void testGetReliefPointByIdAndUser_UTCID02() {
		long rpId = 2;
		long uId =3;
		
		//setup input
		Optional<ReliefPoint> reliefPoint = Optional.empty();
		
		//mock method with return empty
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findByIdAndUser(rpId, uId)).thenReturn(reliefPoint);
		
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			reliefPointService.getReliefPointByIdAndUser(rpId, uId);
	    }); 
		String expectedMessage = "Reliefpoint not exist";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}

//	@InjectMocks
//	ReliefPointFilterDto reliefPointFilterDto;
	
	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#getReliefPoints(java.lang.Long, com.api.dto.ReliefPointFilterDto)}.
	 */
	@Test
	void testGetReliefPoints_UTCID01() {
		ReliefPointFilterDto reliefPointFilterDto = new ReliefPointFilterDto();
		List<ReliefPoint> reliefPoints = new ArrayList<ReliefPoint>();
		List<ReliefPointDto> reliefpointDto = new ArrayList<ReliefPointDto>();
		Long u_id = new Long(1);
		
		Mockito.when(reliefPointRepository.findByTypeOrStatus(u_id,reliefPointFilterDto)).thenReturn(reliefPoints);
		Mockito.when(mapStructMapper.lstReliefPointToreliefPointDto(reliefPoints)).thenReturn(reliefpointDto);
		
		//call method
		reliefPointService.getReliefPoints(u_id, reliefPointFilterDto);
		assertTrue(true);
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#updateStatusReliefPoint(java.lang.Long, java.lang.Boolean)}.
	 */
	@Test
	void testUpdateStatusReliefPoint_UTCID01() {
		Long r_id = new Long(1);
		int status = 1;
		ReliefPoint rp = new ReliefPoint();
		Optional<ReliefPoint> reliefPoint = Optional.of(rp);
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findById(r_id)).thenReturn(reliefPoint);
		Mockito.when(reliefPointRepository.save(rp)).thenReturn(rp);
		
		//call method
		reliefPointService.updateStatusReliefPoint(r_id, status);
		
		// Verify the results
        verify(reliefPointRepository).save(rp);
	}
	
	@Test
	void testUpdateStatusReliefPoint_UTCID02() {
		Long r_id = new Long(1);
		int status = 1;
		ReliefPoint rp = new ReliefPoint();
		Optional<ReliefPoint> reliefPoint = Optional.empty();
		Mockito.<Optional<ReliefPoint>>when(reliefPointRepository.findById(r_id)).thenReturn(reliefPoint);
		AppException appException = assertThrows(AppException.class, () -> {
			
			//call method
			reliefPointService.updateStatusReliefPoint(r_id, status);
	    }); 
		String expectedMessage = "ReliefPoint not exist";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage);
	}

	/**
	 * Test method for {@link com.api.service.impl.ReliefPointServiceImpl#deleteReliefPointById(java.lang.Long)}.
	 */
	@Test
	void testDeleteReliefPointById_UTCID01() {
		long rId = 2;
		
		//mock type void method
		Mockito.doNothing().when(reliefPointRepository).deleteById(rId);
		
		//call method
		reliefPointService.deleteReliefPointById(rId);
		
		// Verify the results
        verify(reliefPointRepository).deleteById(rId);
	}

    @Test
    void testCreateReliefPointAdmin_UTCID01() {
        // Setup
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final PermissionDto permissionDto1 = new PermissionDto();
        permissionDto1.setCode("code");
        permissionDto1.setId(0L);
        permissionDto1.setTo("to");
        permissionDto1.setIcon("icon");
        permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto1.setName("name");
        final PermissionDto permissionDto2 = new PermissionDto();
        permissionDto2.setCode("code");
        permissionDto2.setId(0L);
        permissionDto2.setTo("to");
        permissionDto2.setIcon("icon");
        permissionDto2.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto2.setName("name");
        final ReliefPointDto reliefPointDto = new ReliefPointDto(0L, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList()), new SubDistrictDto(0L, "code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(new ReliefInformationDto(0L, 0, null, new ItemDto(0L, "name", "unit", "description", Arrays.asList()))), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final User user = new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group));

        // Configure MapStructMapper.reliefPointDtoToreliefPoint(...).
        final ReliefPoint reliefPoint = new ReliefPoint();
        reliefPoint.setId(1);
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        reliefPoint.setRelief_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1))));
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
        group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final Group group3 = new Group();
        group3.setPlatform(0);
        group3.setName("name");
        group3.setLevel(0);
        group3.setCode("code");
        final Permission permission3 = new Permission();
        permission3.setLevel(0);
        permission3.setNode_index(0);
        permission3.setNode_from(0);
        permission3.setNode_to(0);
        permission3.setTo_page("to_page");
        permission3.setIcon_name("icon_name");
        permission3.setCode("code");
        permission3.setName("name");
        group3.setGroup_permission(Arrays.asList(permission3));
        group3.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final Group group4 = new Group();
        group4.setPlatform(0);
        group4.setName("name");
        group4.setLevel(0);
        group4.setCode("code");
        final Permission permission4 = new Permission();
        permission4.setLevel(0);
        permission4.setNode_index(0);
        permission4.setNode_from(0);
        permission4.setNode_to(0);
        permission4.setTo_page("to_page");
        permission4.setIcon_name("icon_name");
        permission4.setCode("code");
        permission4.setName("name");
        group4.setGroup_permission(Arrays.asList(permission4));
        group4.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        reliefPoint.setOrganization(new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group3)), group4, null))));
        reliefPoint.setImages(new Image("img_url"));
        reliefPoint.setName("name");
        reliefPoint.setDescription("description");
        reliefPoint.setOpen_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint.setClose_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint.setStatus(0);
        final Item item = new Item();
        item.setName("name");
        item.setUnit("unit");
        item.setDescription("description");
        item.setReliefInformation(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), new Item())));
        reliefPoint.setReliefInformations(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), item)));
        reliefPoint.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        when(mapStructMapper.reliefPointDtoToreliefPoint(any(ReliefPointDto.class))).thenReturn(reliefPoint);

        // Configure AddressService.mapAddress(...).
        final Address address = new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati");
        when(addressService.mapAddress(any(AddressDto.class))).thenReturn(address);

        // Run the test
        final ReliefPoint result = reliefPointService.createReliefPointAdmin(reliefPointDto, user);

        // Verify the results
        assertEquals(null, result);
    }

    @Test
    void testGetEvent_UTCID01() {
        // Setup
        final ReliefPointFilterDto reliefPointFilterDto = new ReliefPointFilterDto(Arrays.asList((long)1,(long)2,(long)3,(long)4), false, false, 0, 0);

        // Configure ReliefPointRepository.findByTypeOrStatusEv(...).
        final ReliefPoint reliefPoint = new ReliefPoint();
        reliefPoint.setId(1);
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        reliefPoint.setRelief_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
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
        group1.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
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
        group2.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final Group group3 = new Group();
        group3.setPlatform(0);
        group3.setName("name");
        group3.setLevel(0);
        group3.setCode("code");
        final Permission permission3 = new Permission();
        permission3.setLevel(0);
        permission3.setNode_index(0);
        permission3.setNode_from(0);
        permission3.setNode_to(0);
        permission3.setTo_page("to_page");
        permission3.setIcon_name("icon_name");
        permission3.setCode("code");
        permission3.setName("name");
        group3.setGroup_permission(Arrays.asList(permission3));
        group3.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        reliefPoint.setOrganization(new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2)), group3, null))));
        reliefPoint.setImages(new Image("img_url"));
        reliefPoint.setName("name");
        reliefPoint.setDescription("description");
        reliefPoint.setOpen_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint.setClose_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint.setStatus(0);
        final Item item = new Item();
        item.setName("name");
        item.setUnit("unit");
        item.setDescription("description");
        item.setReliefInformation(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), new Item())));
        reliefPoint.setReliefInformations(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), item)));
        reliefPoint.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        final List<ReliefPoint> reliefPoints = Arrays.asList(reliefPoint);
        when(reliefPointRepository.findByTypeOrStatusEv((long) 1, reliefPointFilterDto)).thenReturn(reliefPoints);

        // Configure MapStructMapper.lstReliefPointToreliefPointDto(...).
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final PermissionDto permissionDto1 = new PermissionDto();
        permissionDto1.setCode("code");
        permissionDto1.setId(0L);
        permissionDto1.setTo("to");
        permissionDto1.setIcon("icon");
        permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto1.setName("name");
        final PermissionDto permissionDto2 = new PermissionDto();
        permissionDto2.setCode("code");
        permissionDto2.setId(0L);
        permissionDto2.setTo("to");
        permissionDto2.setIcon("icon");
        permissionDto2.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto2.setName("name");
        final List<ReliefPointDto> reliefPointDtos = Arrays.asList(new ReliefPointDto(0L, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", null, Arrays.asList()), new SubDistrictDto(0L, "code", "name", null, Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(new ReliefInformationDto(0L, 0, null, new ItemDto(0L, "name", "unit", "description", Arrays.asList()))), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")));
        when(mapStructMapper.lstReliefPointToreliefPointDto(reliefPoints)).thenReturn(reliefPointDtos);

        // Run the test
        final List<ReliefPointDto> result = reliefPointService.getEvent((long) 1, reliefPointFilterDto);

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testGetEvent_UTCID02() {
        // Setup
    	final ReliefPointFilterDto reliefPointFilterDto = new ReliefPointFilterDto(Arrays.asList((long)1,(long)2,(long)3,(long)4), false, false, 0, 0);

        when(reliefPointRepository.findByTypeOrStatusEv((long) 1, reliefPointFilterDto)).thenReturn(Collections.emptyList());

        // Configure MapStructMapper.lstReliefPointToreliefPointDto(...).
        when(mapStructMapper.lstReliefPointToreliefPointDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Run the test
        final List<ReliefPointDto> result = reliefPointService.getEvent((long) 1, reliefPointFilterDto);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetReliefPointsAdmin_UTCID01() {
        // Setup
    	List<ReliefPoint> lstRp = new ArrayList<ReliefPoint>();
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	lstRp.add(rp);
        final SearchFilterDto filter = new SearchFilterDto(Arrays.asList((long)1,(long)2), false, false, 10, 0);
		Sort sortable = null;
	    if (filter.getSort()) {
	    	sortable = Sort.by("name").descending();
	    }else {
	    	sortable = Sort.by("name").ascending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sortable);
	    Page<ReliefPoint> pageRp = new PageImpl<>(lstRp);
        when(reliefPointRepository.getOwnOrgReliefPoint((long) 1, filter.getStatus_store(), filter.getSearch(), pageable)).thenReturn(pageRp);

        // Configure MapStructMapper.lstReliefPointToreliefPointDto(...).
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final PermissionDto permissionDto1 = new PermissionDto();
        permissionDto1.setCode("code");
        permissionDto1.setId(0L);
        permissionDto1.setTo("to");
        permissionDto1.setIcon("icon");
        permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto1.setName("name");
        final PermissionDto permissionDto2 = new PermissionDto();
        permissionDto2.setCode("code");
        permissionDto2.setId(0L);
        permissionDto2.setTo("to");
        permissionDto2.setIcon("icon");
        permissionDto2.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto2.setName("name");
        final List<ReliefPointDto> reliefPointDtos = Arrays.asList(new ReliefPointDto(1, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", null, Arrays.asList()), new SubDistrictDto(0L, "code", "name", null, Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(new ReliefInformationDto(0L, 0, null, new ItemDto(0L, "name", "unit", "description", Arrays.asList()))), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")));
        when(mapStructMapper.lstReliefPointToreliefPointDto(pageRp.getContent())).thenReturn(reliefPointDtos);

        // Run the test
        final Map<String, Object> result = reliefPointService.getReliefPointsAdmin((long) 1, filter);

        // Verify the results
        assertEquals(4, result.size());
    }

    @Test
    void testGetReliefPointsAdmin_UTCID02() {
        // Setup
    	List<ReliefPoint> lstRp = new ArrayList<ReliefPoint>();
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	lstRp.add(rp);
        final SearchFilterDto filter = new SearchFilterDto(Arrays.asList((long)1,(long)2), false, false, 10, 0);
		Sort sortable = null;
	    if (filter.getSort()) {
	    	sortable = Sort.by("name").descending();
	    }else {
	    	sortable = Sort.by("name").ascending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sortable);
	    Page<ReliefPoint> pageRp = new PageImpl<>(lstRp);
        when(reliefPointRepository.getOwnOrgReliefPoint((long) 1, filter.getStatus_store(), filter.getSearch(), pageable)).thenReturn(pageRp);

        // Configure MapStructMapper.lstReliefPointToreliefPointDto(...).
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final PermissionDto permissionDto1 = new PermissionDto();
        permissionDto1.setCode("code");
        permissionDto1.setId(0L);
        permissionDto1.setTo("to");
        permissionDto1.setIcon("icon");
        permissionDto1.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto1.setName("name");
        final PermissionDto permissionDto2 = new PermissionDto();
        permissionDto2.setCode("code");
        permissionDto2.setId(0L);
        permissionDto2.setTo("to");
        permissionDto2.setIcon("icon");
        permissionDto2.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto2.setName("name");
        final List<ReliefPointDto> reliefPointDtos = Arrays.asList(new ReliefPointDto(1, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", null, Arrays.asList()), new SubDistrictDto(0L, "code", "name", null, Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(new ReliefInformationDto(0L, 0, null, new ItemDto(0L, "name", "unit", "description", Arrays.asList()))), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")));
        when(mapStructMapper.lstReliefPointToreliefPointDto(pageRp.getContent())).thenReturn(reliefPointDtos);

        // Run the test
        final Map<String, Object> result = reliefPointService.getReliefPointsAdmin((long) 1, filter);

        // Verify the results
        assertEquals(4, result.size());
    }

    @Test
    void testGetReliefPointsAdmin_UTCID03() {
        // Setup
    	List<ReliefPoint> lstRp = new ArrayList<ReliefPoint>();
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	lstRp.add(rp);
        final SearchFilterDto filter = new SearchFilterDto(Arrays.asList((long)1,(long)2), true, false, 10, 0);
		Sort sortable = null;
	    if (filter.getSort()) {
	    	sortable = Sort.by("name").descending();
	    }else {
	    	sortable = Sort.by("name").ascending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sortable);
	    Page<ReliefPoint> pageRp = new PageImpl<>(lstRp);
        when(reliefPointRepository.getOwnOrgReliefPoint((long) 1, filter.getStatus_store(), filter.getSearch(), pageable)).thenReturn(pageRp);
        when(mapStructMapper.lstReliefPointToreliefPointDto(pageRp.getContent())).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, Object> result = reliefPointService.getReliefPointsAdmin((long) 1, filter);

        // Verify the results
        assertEquals(Collections.emptyList(), result.get("reliefs"));
    }

    @Test
    void testDeleteReliefPointEvent_UTCID01() {
        // Setup
        when(reliefPointRepository.deleteEvent(0L)).thenReturn("result");

        // Run the test
        reliefPointService.deleteReliefPointEvent(0L);

        // Verify the results
        verify(reliefPointRepository).deleteEvent(0L);
    }

    @Test
    void testUploadReliefImg_UTCID01() {
        // Setup
        final ImageDto image = new ImageDto();
        image.setImageName("imageName");
        image.setEncodedImage("encodedImage");
        image.setId((long) 1);
        ReliefPoint rp = new ReliefPoint();
        rp.setId(1);

        when(reliefPointRepository.getById(image.getId())).thenReturn(rp);
        when(mockAmazonClient.uploadFile(any(ImageDto.class))).thenReturn("result");
        when(reliefPointRepository.save(rp)).thenReturn(rp);

        // Run the test
        final ReliefPoint result = reliefPointService.uploadReliefImg(image);

        // Verify the results
        assertEquals(image.getId(), result.getId());
    }

    @Test
    void testAssignRef_UTCID01() {
        // Setup
        final GrantAccessDto gdto = new GrantAccessDto();
        gdto.setSource_id(0L);
        gdto.setTarget_id(0L);

        when(reliefPointRepository.assignRef(gdto.getSource_id(), gdto.getTarget_id())).thenReturn("DONE-E0001-result");

        // Run the test
        final GrantAccessDto result = reliefPointService.assignRef(gdto);

        // Verify the results
        assertEquals(gdto.getSource_id(), result.getSource_id());
    }

    @Test
    void testAssignRef_UTCID02() {
        // Setup
        final GrantAccessDto gdto = new GrantAccessDto();
        gdto.setSource_id(0L);
        gdto.setTarget_id(0L);

        when(reliefPointRepository.assignRef(gdto.getSource_id(), gdto.getTarget_id())).thenReturn("FAIL-E0001-result");

        // Call method
 		AppException appException = assertThrows(AppException.class, () -> {
 			//call method
 			reliefPointService.assignRef(gdto);
 	    }); 
 		String expectedMessage = "result";
 	    String actualMessage = appException.getMessage();
 		
 	    //compare
 	    assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void testUnAssignRef_UTCID01() {
        // Setup
        final GrantAccessDto gdto = new GrantAccessDto();
        gdto.setSource_id((long) 1);
        gdto.setTarget_id((long) 3);

        when(reliefPointRepository.unAssignRef(gdto.getSource_id(), gdto.getTarget_id())).thenReturn("DONE-E0001-result");

        // Run the test
        final GrantAccessDto result = reliefPointService.unAssignRef(gdto);

        // Verify the results
        assertEquals(gdto.getSource_id(), result.getSource_id());
    }

    @Test
    void testUnAssignRef_UTCID02() {
        // Setup
        final GrantAccessDto gdto = new GrantAccessDto();
        gdto.setSource_id((long) 1);
        gdto.setTarget_id((long) 3);

        when(reliefPointRepository.unAssignRef(gdto.getSource_id(), gdto.getTarget_id())).thenReturn("FAIL-E0001-result");

        // Call method
 		AppException appException = assertThrows(AppException.class, () -> {
 			//call method
 			reliefPointService.unAssignRef(gdto);
 	    }); 
 		String expectedMessage = "result";
 	    String actualMessage = appException.getMessage();
 		
 	    //compare
 	    assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void testGetAllAssignUser_UTCID01() {
        // Setup
    	Long rp_id = (long) 1;
    	String search = "s";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
        // Run the test
        when(reliefPointRepository.findById(rp_id)).thenReturn(Optional.of(rp));
        final List<User> result = reliefPointService.getAllAssignUser(rp_id,search);

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllAssignUser_UTCID02() {
        // Setup
    	Long rp_id = (long) 1;
    	String search = "";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
        // Run the test
        when(reliefPointRepository.findById(rp_id)).thenReturn(Optional.of(rp));
        final List<User> result = reliefPointService.getAllAssignUser(rp_id,search);

        // Verify the results
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllAssignUser_UTCID03() {
        // Setup
    	Long rp_id = (long) 2;
    	String search = "";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
        // Run the test
        when(reliefPointRepository.findById(rp_id)).thenReturn(Optional.empty());

        AppException appException = assertThrows(AppException.class, () -> {
			//call method
        	reliefPointService.getAllAssignUser(rp_id,search);
	    }); 
		String expectedMessage = "Relief point is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void testGetAllUnassignUser_UTCID01() {
        // Setup
    	Long rp_id = (long) 1;
    	String search = "s";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
    	Organization o = new Organization();
    	o.setId(1);
    	rp.setOrganization(o);
        // Configure UserRepository.getUserInOrg(...).
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        
        when(reliefPointRepository.getById(rp_id)).thenReturn(rp);
        final List<User> users = Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
        when(mockUserRepo.getUserInOrg(o.getId())).thenReturn(users);

        // Run the test
        final List<User> result = reliefPointService.getAllUnassignUser(rp_id, search);

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllUnassignUser_UTCID02() {
        // Setup
    	Long rp_id = (long) 1;
    	String search = "";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
    	Organization o = new Organization();
    	o.setId(1);
    	rp.setOrganization(o);
        // Configure UserRepository.getUserInOrg(...).
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        
        when(reliefPointRepository.getById(rp_id)).thenReturn(rp);
        final List<User> users = Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
        when(mockUserRepo.getUserInOrg(o.getId())).thenReturn(users);

        // Run the test
        final List<User> result = reliefPointService.getAllUnassignUser(rp_id, search);

        // Verify the results
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllUnassignUser_UTCID03() {
        // Setup
    	Long rp_id = (long) 1;
    	String search = "";
    	ReliefPoint rp = new ReliefPoint();
    	rp.setId(1);
    	List<User> lstUser = new ArrayList<User>();
    	User u = new User();
    	u.setId(1);
    	u.setUsername("ss");
    	User u1 = new User();
    	u1.setId(1);
    	u1.setUsername("uu");
    	lstUser.add(u1);
    	lstUser.add(u);
    	rp.setRelief_user(lstUser);
    	Organization o = new Organization();
    	o.setId(1);
    	rp.setOrganization(o);
        // Configure UserRepository.getUserInOrg(...).
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        
        when(reliefPointRepository.getById(rp_id)).thenReturn(null);

        // Run the test

        AppException appException = assertThrows(AppException.class, () -> {
			//call method
        	reliefPointService.getAllUnassignUser(rp_id, search);
	    }); 
		String expectedMessage = "Relief point is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
    }

}
