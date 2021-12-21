package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.GroupDto;
import com.api.dto.OrganizationDto;
import com.api.dto.SOSDto;
import com.api.dto.StoreDto;
import com.api.dto.SubDistrictDto;
import com.api.dto.SubcribeDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.Group;
import com.api.entity.Image;
import com.api.entity.Organization;
import com.api.entity.Permission;
import com.api.entity.Store;
import com.api.entity.SubDistrict;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.repositories.StoreRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.SOSService;
import com.api.service.UserService;
import com.exception.AppException;
import com.jwt.config.JwtTokenUtil;
import com.ultils.Constants;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	StoreRepository storeRepo;

	@Mock
	GroupRepository groupRepository;

	@Mock
	RequestRepository requestRepository;

	@Mock
	OrganizationRepository organizationRepository;

	@Mock
	AddressService addressService;

	@Mock
	ModelMapper modelMapper;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JwtTokenUtil jwtTokenUtil;
	
	@Mock
	MapStructMapper mapStructMapper;
	
	@Mock
	SOSService sosServ;
	
	@InjectMocks
	UserService userServ = new UserSerivceImpl();
	
	@Test
	public void testGetAllUser_UTCID01() {
		
		// Set data test
		User u = new User();
		u.setId(1);
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(u);
		// Mock
		Mockito.when(userRepository.findAll()).thenReturn(lstUser);
		
		// Call method

		List<User> ucheck = userServ.getAllUser();
		
	    //compare
	    assertEquals(1,ucheck.get(0).getId());
		
	}
	
	@Test
	public void getUserbyToken_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		u.setPassword("password");
		u.setImages(new Image());
		UserDto udto = new UserDto();
		udto.setId(2);
		Address a = new Address();
		a.setId(1);
		u.setAddress(a);
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
		OrganizationDto odto = new OrganizationDto();
		odto.setId(1);
		udto.setOrganization(odto);
		Group g = new Group();
		g.setId(1);
		List<Group> lsgr = new ArrayList<Group>();
		lsgr.add(g);
		u.setGroups_user(lsgr);
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		List<GroupDto> lsgdto = new ArrayList<GroupDto>();
		lsgdto.add(gdto);
		Organization o = new Organization();
		o.setId(1);
		u.setOrganization(o);
		
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		Mockito.when(mapStructMapper.userToUserDto(u)).thenReturn(udto);
		Mockito.when(mapStructMapper.lstGroupToGroupDto(u.getGroups_user())).thenReturn(lsgdto);
		Mockito.when(mapStructMapper.addressToAddressDto(u.getAddress())).thenReturn(addressDto);
		Mockito.when(mapStructMapper.organizationToOrganizationDto(u.getOrganization())).thenReturn(odto);
		
		// Call method

		UserDto ucheck = userServ.getUserbyToken(token);
		
	    // Compare
	    assertEquals(2,ucheck.getId());
	}
	
	@Test
	public void getUserbyToken_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUserbyToken(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getNativeUserbyToken_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		Mockito.when(userRepository.getById(u.getId())).thenReturn(u);
		
		// Call method

		User ucheck = userServ.getNativeUserbyToken(token);
		
	    // Compare
	    assertEquals(1,ucheck.getId());
	}
	
	@Test
	public void getNativeUserbyToken_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getNativeUserbyToken(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getUserbyTokenAuth_UTCID01() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(u);
		
		// Call method

		User ucheck = userServ.getUserbyTokenAuth(token);
		
	    // Compare
	    assertEquals(1,ucheck.getId());
	}
	
	@Test
	public void getUserbyTokenAuth_UTCID02() {
		
		// Set data test
		String token = "hsjusnwkscyshwja";
		User u = new User();
		u.setId(1);
		// Mock
		Mockito.when(jwtTokenUtil.getUserNameByToken(token)).thenReturn("Duong123");
		Mockito.when(userRepository.findByUsername("Duong123")).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUserbyTokenAuth(token);
	    }); 
		String expectedMessage = "Error when query to get user";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setUsername("duongpt");
		// Mock
		Mockito.when(userRepository.findByUsername(u.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(u.getPhone())).thenReturn(Optional.empty());
		Mockito.when(groupRepository.findById(g.getId())).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Group is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(new Group());
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setUsername("Duong123");
		// Mock
		Mockito.when(userRepository.findByUsername(u.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(u.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(new Group());
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setUsername("duongpt");
		// Mock
		Mockito.when(userRepository.findByUsername(u.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser(u);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganization_v2_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganization_v2(udto);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganization_v2_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganization_v2(udto);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganization_v2_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGrDto.add(gdto);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(u.getPhone())).thenReturn(Optional.empty());
		Mockito.when(groupRepository.findById(gid)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganization_v2(udto);
	    }); 
		String expectedMessage = "Group is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_v2_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser_v2(udto);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_v2_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser_v2(udto);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerUser_v2_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGrDto.add(gdto);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.empty());
		Mockito.when(groupRepository.findById(gdto.getId())).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerUser_v2(udto);
	    }); 
		String expectedMessage = "Group is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganizationUser_v2_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganizationUser_v2(udto, u);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganizationUser_v2_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganizationUser_v2(udto, u);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerOrganizationUser_v2_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername("duongpt")).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.empty());
		Mockito.when(organizationRepository.findById(org.getId())).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerOrganizationUser_v2(udto, u);
	    }); 
		String expectedMessage = "organization is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerStoreUser_v2_UTCID01() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.of(u));
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerStoreUser_v2(udto);
	    }); 
		String expectedMessage = "Phone is exsit!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerStoreUser_v2_UTCID02() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGr.add(g);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(u);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerStoreUser_v2(udto);
	    }); 
		String expectedMessage = "Username is existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void registerStoreUser_v2_UTCID03() {
		
		// Set data test
		long gid = 1;
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		GroupDto gdto = new GroupDto();
		gdto.setId(1);
		gdto.setLevel(1);
		List<GroupDto> lstGrDto = new ArrayList<GroupDto>();
		lstGrDto.add(gdto);
		UserDto udto = new UserDto();
		udto.setUsername("check");
		udto.setPhone("0912572299");
		udto.setUsername("duongpt");
		udto.setGroups_user(lstGrDto);
		// Mock
		Mockito.when(userRepository.findByUsername(udto.getUsername())).thenReturn(null);
		Mockito.when(userRepository.findByPhone(udto.getPhone())).thenReturn(Optional.empty());
		Mockito.when(groupRepository.findById(gdto.getId())).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.registerStoreUser_v2(udto);
	    }); 
		String expectedMessage = "Group is not exist!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getUsernameByPhone_UTCID01() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		u.setGroups_user(lstGr);
		String phone ="+84966048002";
		// Mock
		Mockito.when(userRepository.findByPhone("0966048002")).thenReturn(Optional.of(u));
		
		// Call method
		String scheck = userServ.getUsernameByPhone(phone);
		
	    // Compare
	    assertEquals("duongpt",scheck);
	}
	
	@Test
	public void getUsernameByPhone_UTCID02() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		u.setGroups_user(lstGr);
		String phone ="+84966048002";
		// Mock
		Mockito.when(userRepository.findByPhone("0966048002")).thenReturn(Optional.empty());

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUsernameByPhone(phone);
	    }); 
		String expectedMessage = "Phone number not Found";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getUserByPhone_UTCID01() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		u.setGroups_user(lstGr);
		String phone ="+84966048002";
		// Mock
		Mockito.when(userRepository.findByPhone("0966048002")).thenReturn(Optional.of(u));
		
		// Call method
		User ucheck = userServ.getUserByPhone(phone);
		
	    // Compare
	    assertEquals(1,ucheck.getId());
	}
	
	@Test
	public void getUserByPhone_UTCID02() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setLevel(1);
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		Organization org = new Organization();
		org.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setGroups_user(lstGr);
		u.setPhone("0912572299");
		u.setOrganization(org);
		u.setGroups_user(lstGr);
		String phone ="+84966048002";
		// Mock
		Mockito.when(userRepository.findByPhone("0966048002")).thenReturn(Optional.empty());

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getUserByPhone(phone);
	    }); 
		String expectedMessage = "Số điện thoại không tồn tại trong hệ thống";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void subcribeStore_UTCID01() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(u);
		Mockito.when(storeRepo.getById(sdto.getStore_id())).thenReturn(str);
		Mockito.when(userRepository.save(u)).thenReturn(u);
		
		// Call method
		SubcribeDto scheck = userServ.subcribeStore(sdto);
		
	    // Compare
	    assertEquals(1,scheck.getStore_id());
	}
	
	@Test
	public void subcribeStore_UTCID02() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(u);
		Mockito.when(storeRepo.getById(sdto.getStore_id())).thenReturn(null);

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.subcribeStore(sdto);
	    }); 
		String expectedMessage = "Store is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void subcribeStore_UTCID03() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(null);

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.subcribeStore(sdto);
	    }); 
		String expectedMessage = "User is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void unSubcribeStore_UTCID01() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(u);
		Mockito.when(storeRepo.getById(sdto.getStore_id())).thenReturn(str);
		Mockito.when(userRepository.save(u)).thenReturn(u);
		
		// Call method
		SubcribeDto scheck = userServ.subcribeStore(sdto);
		
	    // Compare
	    assertEquals(1,scheck.getStore_id());
	}
	
	@Test
	public void unSubcribeStore_UTCID02() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(u);
		Mockito.when(storeRepo.getById(sdto.getStore_id())).thenReturn(null);

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.subcribeStore(sdto);
	    }); 
		String expectedMessage = "Store is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void unSubcribeStore_UTCID03() {
		
		// Set data test
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(null);

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.subcribeStore(sdto);
	    }); 
		String expectedMessage = "User is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void getListSubcribe_UTCID01() {
		
		// Set data test
		Long id = (long) 1;
		StoreDto ssdto = new StoreDto();
		ssdto.setId(1);
		List<StoreDto> lstsdto = new ArrayList<StoreDto>();
		lstsdto.add(ssdto);
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(u);
		Mockito.when(mapStructMapper.lstStoreToStoreDto(u.getUser_store())).thenReturn(lstsdto);
		
		// Call method
		
		SubcribeDto scheck = userServ.getListSubcribe(id);
		
	    // Compare
	    assertEquals(1,scheck.getStoreSubcribe().get(0).getId());
	}
	
	@Test
	public void getListSubcribe_UTCID02() {
		
		// Set data test
		Long id = (long) 1;
		Store str = new Store();
		str.setId(1);
		User u = new User();
		u.setId(1);
		u.setUsername("duongpt");
		u.setUser_store(new ArrayList<Store>());
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStore_id((long) 1);
		sdto.setUser_id((long) 1);
		// Mock
		Mockito.when(userRepository.getById(sdto.getUser_id())).thenReturn(null);

	    // Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			userServ.getListSubcribe(id);
	    }); 
		String expectedMessage = "User is not existed!";
	    String actualMessage = appException.getMessage();
		
	    // Compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
}
