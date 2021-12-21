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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.entity.Group;
import com.api.entity.Permission;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.PermissionRepository;
import com.api.repositories.UserRepository;
import com.api.service.PermissionService;
import com.exception.AppException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PermissionServiceImplTest {
	@Mock
	PermissionRepository perRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	MapStructMapper mapStructMapper;
	
	@Mock
	GroupRepository groupRepo;
	
	@InjectMocks
	PermissionService perServ = new PermissionServiceImpl();
	
	@Test
	public void testGetById_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Permission p = new Permission();
		p.setId(1);
		Mockito.when(perRepo.findById(id)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			perServ.getById(id);
	    }); 
		String expectedMessage = "Permission is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testGetById_UTCID02() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Permission p = new Permission();
		p.setId(1);
		Mockito.when(perRepo.findById(id)).thenReturn(Optional.of(p));
		
		// Call method
		Permission pcheck = perServ.getById(id);
		
	    //compare
	    assertEquals(id,pcheck.getId());
	}
	
	@Test
	public void testUdatePermission_UTCID01() {
		
		// Set data test
		long id = 1;
		Permission p = new Permission();
		p.setId(1);
		
		// Mock
		Mockito.when(perRepo.findById(id)).thenReturn(Optional.of(p));
		Mockito.when(perRepo.save(p)).thenReturn(p);
		
		// Call method
		Permission pcheck = perServ.updatePermission(p,id);
		
	    //compare
	    assertEquals(id,pcheck.getId());
	}
	
	@Test
	public void testUdatePermission_UTCID02() {
		
		// Set data test
		long id = 1;
		Permission p = new Permission();
		p.setId(1);
		
		// Mock
		Mockito.when(perRepo.findById(id)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			perServ.updatePermission(p,id);
	    }); 
		String expectedMessage = "Permission is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testCreatePermission_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Permission p = new Permission();
		p.setId(1);
		Mockito.when(perRepo.findByName(p.getName())).thenReturn(p);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			perServ.createPermission(p);
	    }); 
		String expectedMessage = "Permission is existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testCreatePermission_UTCID02() {
		
		// Set data test
		Permission p = new Permission();
		p.setId(1);
		
		// Mock
		Mockito.when(perRepo.findByName(p.getName())).thenReturn(null);
		Mockito.when(perRepo.save(p)).thenReturn(p);
		
		// Call method
		Permission pcheck = perServ.createPermission(p);
		
	    //compare
	    assertEquals(p.getId(),pcheck.getId());
	}
	
	@Test
	public void testGetAllPermissionAuthoriedByGroup_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Mockito.when(groupRepo.getById(id)).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			perServ.getAllPermissionAuthoriedByGroup(id);
	    }); 
		String expectedMessage = "Group is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testGetAllPermissionAuthoriedByGroup_UTCID02() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Permission p = new Permission();
		p.setId(1);
		List<Permission> lstPer = new ArrayList<Permission>();
		lstPer.add(p);
		Group g = new Group();
		g.setId(1);
		g.setPermissions(lstPer);
		Mockito.when(groupRepo.getById(id)).thenReturn(g);
		
		// Call method

		List<Permission> pcheck = perServ.getAllPermissionAuthoriedByGroup(id);
		
	    //compare
	    assertEquals(1,pcheck.get(0).getId());
	}
	
	@Test
	public void testGetOwnPermission_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Mockito.when(userRepo.getById(id)).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			perServ.getOwnPermission(id);
	    }); 
		String expectedMessage = "User is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testGetOwnPermission_UTCID02() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Permission p = new Permission();
		p.setId(1);
		List<Permission> lstPer = new ArrayList<Permission>();
		lstPer.add(p);
		Group g = new Group();
		g.setId(1);
		g.setPermissions(lstPer);
		User u = new User();
		List<Group> lstGr = new ArrayList<Group>();
		lstGr.add(g);
		u.setGroups_user(lstGr);
		Mockito.when(userRepo.getById(id)).thenReturn(u);
		
		// Call method

		List<Permission> pcheck = perServ.getOwnPermission(id);
		
	    //compare
	    assertEquals(1,pcheck.get(0).getId());
	}

}
