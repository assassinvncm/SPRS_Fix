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

import com.api.dto.StoreDto;
import com.api.entity.Group;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.UserRepository;
import com.api.service.GroupService;
import com.exception.AppException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GroupSeviceImplTest {

	
	@Mock
	UserRepository userRepo;
	
	@Mock
	MapStructMapper mapStructMapper;
	
	@Mock
	GroupRepository groupRepo;
	
	@InjectMocks
	private GroupService groupServ = new GroupServiceImpl();
	
	@Test
	public void testGetAllGroupAuthoriedByUser_UTCID01() {
		
		// Set data test
		long uid = 1;
		
		// Mock
		User u = new User();
		u.setId(1);
		Group g = new Group();
		g.setId(1);
		List<Group> lsRs = new ArrayList<Group>();
		lsRs.add(g);
		u.setGroups_user(lsRs);
		Mockito.when(userRepo.getById(uid)).thenReturn(u);
		
		// Call method
		List<Group> actualRs = groupServ.getAllGroupAuthoriedByUser(uid);
		
	    //compare
	    assertEquals(1,actualRs.get(0).getId());
	}
	
	@Test
	public void testGetAllGroupAuthoriedByUser_UTCID02() {
		
		// Set data test
		long uid = 1;
		
		// Mock
		User u = new User();
		u.setId(1);
		Group g = new Group();
		g.setId(1);
		List<Group> lsRs = new ArrayList<Group>();
		lsRs.add(g);
		u.setGroups_user(lsRs);
		Mockito.when(userRepo.getById(uid)).thenReturn(null);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			groupServ.getAllGroupAuthoriedByUser(uid);
	    }); 
		String expectedMessage = "Username is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testGetById_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Group g = new Group();
		g.setId(1);
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			groupServ.getById(id);
	    }); 
		String expectedMessage = "Group is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testGetById_UTCID02() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Group g = new Group();
		g.setId(1);
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.of(g));
		
		// Call method
		Group gcheck = groupServ.getById(id);
		
	    //compare
	    assertEquals(id,gcheck.getId());
	}
	
	@Test
	public void testCreateGroup_UTCID01() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setName("User");
		
		// Mock
		Mockito.when(groupRepo.findByName(g.getName())).thenReturn(null);
		
		// Call method
		Group gcheck = groupServ.createGroup(g);
		
	    //compare
	    assertEquals(g.getId(),gcheck.getId());
	}
	
	@Test
	public void testCreateGroup_UTCID02() {
		
		// Set data test
		Group g = new Group();
		g.setId(1);
		g.setName("User");
		
		// Mock
		Mockito.when(groupRepo.findByName(g.getName())).thenReturn(g);
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			groupServ.createGroup(g);
	    }); 
		String expectedMessage = "Group is existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testDeleteGroup_UTCID01() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Group g = new Group();
		g.setId(1);
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			groupServ.getById(id);
	    }); 
		String expectedMessage = "Group is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	public void testDeleteGroup_UTCID02() {
		
		// Set data test
		long id = 1;
		
		// Mock
		Group g = new Group();
		g.setId(1);
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.of(g));
		Mockito.doNothing().when(groupRepo).deleteById(g.getId());
		
		// Call method
		Group gcheck = groupServ.deleteGroup(id);
		
	    //compare
	    assertEquals(id,gcheck.getId());
	}
	
	@Test
	public void testUpdateGroup_UTCID01() {
		
		// Set data test
		long id = 1;
		Group g = new Group();
		g.setId(1);
		
		// Mock
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.of(g));
		Mockito.when(groupRepo.save(g)).thenReturn(g);
		
		// Call method
		Group gcheck = groupServ.updateGroup(g,id);
		
	    //compare
	    assertEquals(id,gcheck.getId());
	}
	
	@Test
	public void testUpdateGroup_UTCID02() {
		
		// Set data test
		long id = 1;
		Group g = new Group();
		g.setId(1);
		
		// Mock
		Mockito.when(groupRepo.findById(id)).thenReturn(Optional.empty());
		
		// Call method
		AppException appException = assertThrows(AppException.class, () -> {
			//call method
			groupServ.updateGroup(g,id);
	    }); 
		String expectedMessage = "Group is not existed!";
	    String actualMessage = appException.getMessage();
		
	    //compare
	    assertEquals(expectedMessage,actualMessage);
	}

}
