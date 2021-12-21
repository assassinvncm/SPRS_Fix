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

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DistrictDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SOSDto;
import com.api.dto.SubDistrictDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.SOS;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.SOSRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.api.service.SOSService;
import com.exception.AppException;


/**
 * @author MY PC
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SOSServiceImplTest {

	@Mock
	SOSRepository sosRepo;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	MapStructMapper mapStructMapper;

	@Mock
	AddressService addressService;
	
	@Mock
	NotificationService notificationService;
	
	@InjectMocks
	SOSService sosServ = new SOSServiceImpl();
	
	@Test
	public void testUpdateStatusSOS_UTCID01() {
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(3);
		
		//set data input for SOSDto
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
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		
		Mockito.when(userRepo.getById(udto.getId())).thenReturn(null);
		
		AppException appException = assertThrows(AppException.class, () -> {
			sosServ.updateStatusSOS(sdto, udto);
	    }); 
		
		String expectedMessage = "User is not Found!";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	public void testUpdateStatusSOS_UTCID02() {
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(395);
		
		//set data input for SOSDto
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
		SOSDto sdto = new SOSDto();
		sdto.setId(1);
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(0);
		s.setLevel(1);
		u.setUser_sos(s);

		//mock
		Mockito.when(userRepo.getById(udto.getId())).thenReturn(u);
		Mockito.when(addressService.mapAddress(sdto.getAddress())).thenReturn(new Address());
		Mockito.when(userRepo.save(u)).thenReturn(u);
		Mockito.doNothing().when(notificationService).sendPnsToDeviceWhenOpenSOS(u, "Có một địa điểm SOS được tạo gần bạn");

		//call method
		SOSDto scheck = sosServ.updateStatusSOS(sdto, udto);
		
		//
		assertEquals(sdto.getId(), scheck.getId());
	}
	
	@Test
	public void testGetSOSCommon_UTCID01() {
		long id = 395;
		//set data input for SOS
		UserDto udto = new UserDto();
		udto.setId(3);
		
		//set data input for SOSDto
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
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(1);
		s.setLevel(1);
		Address a = new Address();
		a.setId(1);
		u.setUser_sos(s);
		u.setAddress(a);
		
		//mock
		Mockito.when(userRepo.getUserBySosId(id)).thenReturn(Optional.empty());
		
		//call method
		AppException appException = assertThrows(AppException.class, () -> {
			sosServ.getSOSCommon(id);
	    }); 
		
		String expectedMessage = "SOS not exist";
	    String actualMessage = appException.getMessage();
		
	    assertEquals(expectedMessage,actualMessage); 
	}
	
	@Test
	public void testGetSOSCommon_UTCID02() {
		long id = 395;
		//set data input for SOS
		
		//set data input for SOSDto
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
		SOSDto sdto = new SOSDto();
		sdto.setDescription("test");
		sdto.setAddress(addressDto);
		sdto.setStatus(1);
		sdto.setLevel(1);
		User u = new User();
		SOS s = new SOS();
		s.setDescription("test");
		s.setStatus(1);
		s.setLevel(1);
		u.setUser_sos(s);
		u.setFull_name("Duongpt");
		u.setPhone("12345");
		u.setId(1);
		//mock
		Mockito.when(userRepo.getUserBySosId(id)).thenReturn(Optional.of(u));
		Mockito.when(mapStructMapper.SOSToSOSDto(u.getUser_sos())).thenReturn(sdto);
		Mockito.when(mapStructMapper.addressToAddressDto(u.getAddress())).thenReturn(addressDto);

		//call method
		sosServ.getSOSCommon(id);
	}

}
