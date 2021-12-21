/**
 * 
 */
package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.api.dto.*;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.DeviceRepository;
import com.api.service.AddressService;
import com.api.service.NotificationService;
import com.exception.AppException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * @author MY PC
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {

	 private DeviceServiceImpl deviceServiceImplUnderTest;

	    @BeforeEach
	    void setUp() {
	        deviceServiceImplUnderTest = new DeviceServiceImpl();
	        deviceServiceImplUnderTest.deviceRepository = mock(DeviceRepository.class);
	        deviceServiceImplUnderTest.mapStructMapper = mock(MapStructMapper.class);
	        deviceServiceImplUnderTest.addressService = mock(AddressService.class);
	        deviceServiceImplUnderTest.notificationService = mock(NotificationService.class);
	    }

	    @Test
	    void testInsertDevice_UTCID01() {
	        // Setup
	        final Group group = new Group();
	        group.setPlatform(0);
	        group.setName("name");
	        group.setLevel(0);
	        group.setCode("code");
	        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
	        final User user = new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group));
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("token");
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
	        deviceDto.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto.setSerial("serial");

	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).delete(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).deleteBySerial(device.getSerial());
	        
	        // Configure MapStructMapper.deviceDtoToDevice(...).
	        final Device device1 = new Device();
	        device1.setToken("token");
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
	        device1.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2)));
	        device1.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device1.setSerial("serial");
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceDtoToDevice(any(DeviceDto.class))).thenReturn(device1);

	        // Configure AddressService.mapAddress(...).
	        final Address address = new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati");
	        when(deviceServiceImplUnderTest.addressService.mapAddress(any(AddressDto.class))).thenReturn(address);

	        // Configure MapStructMapper.deviceToDeviceDto(...).
	        final DeviceDto deviceDto1 = new DeviceDto();
	        deviceDto1.setId(1L);
	        deviceDto1.setToken("token");
	        final PermissionDto permissionDto3 = new PermissionDto();
	        permissionDto3.setCode("code");
	        permissionDto3.setId(0L);
	        permissionDto3.setTo("to");
	        permissionDto3.setIcon("icon");
	        permissionDto3.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto3.setName("name");
	        final PermissionDto permissionDto4 = new PermissionDto();
	        permissionDto4.setCode("code");
	        permissionDto4.setId(0L);
	        permissionDto4.setTo("to");
	        permissionDto4.setIcon("icon");
	        permissionDto4.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto4.setName("name");
	        final PermissionDto permissionDto5 = new PermissionDto();
	        permissionDto5.setCode("code");
	        permissionDto5.setId(0L);
	        permissionDto5.setTo("to");
	        permissionDto5.setIcon("icon");
	        permissionDto5.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto5.setName("name");
	        deviceDto1.setUser(new UserDto(1L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto3), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto4), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto5), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto1.setAddress(new AddressDto(new CityDto(1L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(1L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto1.setSerial("serial");
	        when(deviceServiceImplUnderTest.deviceRepository.save(any(Device.class))).thenReturn(device);
	        
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceToDeviceDto(any(Device.class))).thenReturn(deviceDto1);
	        
	        // Run the test
	        final DeviceDto result = deviceServiceImplUnderTest.insertDevice(user, deviceDto);

	        // Verify the results
	        verify(deviceServiceImplUnderTest.deviceRepository).deleteBySerial("serial");
	    }
	    
	    @Test
	    void testInsertDevice_UTCID02() {
	        // Setup
	     
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("token");
	        deviceDto.setAddress(null);
	        deviceDto.setSerial("serial");
	        
	        final User user = new User();
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = new Device();
	        device.setToken("token");
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).delete(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).deleteBySerial(device.getSerial());
	        
	        // Configure MapStructMapper.deviceDtoToDevice(...).
	        final Device device1 = new Device();
	        device1.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device1.setSerial("serial");
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceDtoToDevice(any(DeviceDto.class))).thenReturn(device1);

	        // Configure MapStructMapper.deviceToDeviceDto(...).
	        final DeviceDto deviceDto1 = new DeviceDto();
	        UserDto userDto = new UserDto();
	        userDto.setId(1L);
	        deviceDto1.setId(1L);
	        deviceDto1.setToken("token");
			deviceDto1.setUser(userDto);
	        deviceDto1.setAddress(new AddressDto(new CityDto(1L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(1L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto1.setSerial("serial");
	        when(deviceServiceImplUnderTest.deviceRepository.save(any(Device.class))).thenReturn(device);
	        
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceToDeviceDto(any(Device.class))).thenReturn(deviceDto1);
	        
	        // Run the test
	        final DeviceDto result = deviceServiceImplUnderTest.insertDevice(user, deviceDto);

	        // Verify the results
	        //verify(deviceServiceImplUnderTest.deviceRepository).deleteBySerial("serial");
	    }

	    @Test
	    void testUpdateDevice_UTCID01() {
	        // Setup
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("token");
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
	        deviceDto.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto.setSerial("serial");

	        // Configure MapStructMapper.deviceDtoToDevice(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        final Optional<Device> deviceOpt = Optional.of(device);
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);
	        
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceDtoToDevice(any(DeviceDto.class))).thenReturn(device);

	        // Configure AddressService.mapAddress(...).
	        final Address address = new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati");
	        when(deviceServiceImplUnderTest.addressService.mapAddress(any(AddressDto.class))).thenReturn(address);

	        // Configure MapStructMapper.deviceToDeviceDto(...).
	        final DeviceDto deviceDto1 = new DeviceDto();
	        deviceDto1.setId(0L);
	        deviceDto1.setToken("token");
	        final PermissionDto permissionDto3 = new PermissionDto();
	        permissionDto3.setCode("code");
	        permissionDto3.setId(0L);
	        permissionDto3.setTo("to");
	        permissionDto3.setIcon("icon");
	        permissionDto3.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto3.setName("name");
	        final PermissionDto permissionDto4 = new PermissionDto();
	        permissionDto4.setCode("code");
	        permissionDto4.setId(0L);
	        permissionDto4.setTo("to");
	        permissionDto4.setIcon("icon");
	        permissionDto4.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto4.setName("name");
	        final PermissionDto permissionDto5 = new PermissionDto();
	        permissionDto5.setCode("code");
	        permissionDto5.setId(0L);
	        permissionDto5.setTo("to");
	        permissionDto5.setIcon("icon");
	        permissionDto5.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
	        permissionDto5.setName("name");
	        deviceDto1.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto3), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto4), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto5), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto1.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto1.setSerial("serial");
	        
	        when(deviceServiceImplUnderTest.deviceRepository.save(any(Device.class))).thenReturn(device);
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceToDeviceDto(device)).thenReturn(deviceDto1);

	        // Run the test
	        final DeviceDto result = deviceServiceImplUnderTest.updateDevice(deviceDto);

	        // Verify the results
	        assertEquals(deviceDto.getId(), result.getId());
	    }
	    
	    @Test
	    void testUpdateDevice_UTCID02() {
	        // Setup
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("token");
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
	        deviceDto.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto.setSerial("serial");


	        final Optional<Device> deviceOpt = Optional.empty();
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);

	        AppException appException = assertThrows(AppException.class, () -> {
	        	 // Run the test
		        final DeviceDto result = deviceServiceImplUnderTest.updateDevice(deviceDto);
		    });
			
	        final String expectedMessage = "Device not exist";
		    String actualMessage = appException.getMessage();
			
		    // Verify the results
		    assertEquals(expectedMessage,actualMessage);
	    }

	    @Test
	    void testUpdateDeviceAddress_UTCID01() {
	        // Setup
	        final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati");

	        // Configure DeviceRepository.findBySerial(...).
	        final Device device1 = new Device();
	        device1.setToken("token");
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
	        device1.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device1.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device1.setSerial("serial");
	        final Optional<Device> device = Optional.of(device1);
	        when(deviceServiceImplUnderTest.deviceRepository.findBySerial("serial")).thenReturn(device);

	        // Configure AddressService.mapAddress(...).
	        final Address address = new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati");
	        when(deviceServiceImplUnderTest.addressService.mapAddress(any(AddressDto.class))).thenReturn(address);

	        // Configure MapStructMapper.deviceToDeviceDto(...).
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("token");
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
	        deviceDto.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
	        deviceDto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
	        deviceDto.setSerial("serial");
	        
	        when(deviceServiceImplUnderTest.deviceRepository.saveAndFlush(any(Device.class))).thenReturn(device1);
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceToDeviceDto(device1)).thenReturn(deviceDto);

	        // Run the test
	        final DeviceDto result = deviceServiceImplUnderTest.updateDeviceAddress("serial", addressDto);

	        // Verify the results
	    }
	    
	    @Test
	    void testUpdateDeviceAddress_UTCID02() {
	    	// Setup
	        final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati");
	    	
	        final Optional<Device> device = Optional.empty();
	        //when(deviceServiceImplUnderTest.deviceRepository.findBySerial("serial")).thenReturn(device);
	        	        
	        AppException appException = assertThrows(AppException.class, () -> {
		        // Run the test
		        final DeviceDto result = deviceServiceImplUnderTest.updateDeviceAddress("serial", addressDto);
		    });
			
	        final String expectedMessage = "User not have device ";
		    String actualMessage = appException.getMessage();
			
		    // Verify the results
		    assertEquals(expectedMessage,actualMessage);

	    }

	    @Test
	    void testUpdateDeviceToken_UTCID01() {
	        // Setup
	        // Configure MapStructMapper.deviceToDeviceDto(...).
	        final DeviceDto deviceDto = new DeviceDto();
	        deviceDto.setId(0L);
	        deviceDto.setToken("12345677890"); 
	        // Setup
	    	final Device device = new Device();
	        device.setToken("12345677890");
	        Optional<Device> deviceOpt = Optional.of(device);
	        //mock method
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);
	        when(deviceServiceImplUnderTest.deviceRepository.save(any(Device.class))).thenReturn(device);
	        when(deviceServiceImplUnderTest.mapStructMapper.deviceToDeviceDto(device)).thenReturn(deviceDto);

	        // Run the test
	        final DeviceDto result = deviceServiceImplUnderTest.updateDeviceToken(0L, "12345677890");

	        // Verify the results
	        assertEquals(device.getToken(), result.getToken());
	    }
	    
	    @Test
	    void testUpdateDeviceToken_UTCID02() {
	        // Setup
	        // Configure MapStructMapper.deviceToDeviceDto(...).

	        Optional<Device> deviceOpt = Optional.empty();
	        //mock method
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);
	        
	        AppException appException = assertThrows(AppException.class, () -> {
		        // Run the test
		        final DeviceDto result = deviceServiceImplUnderTest.updateDeviceToken(0L, "12345677890");
		    });
			
	        final String expectedMessage = "Device not exist";
		    String actualMessage = appException.getMessage();
			
		    // Verify the results
		    assertEquals(expectedMessage,actualMessage);
	    }

	    @Test
	    void testGetDeviceTokenByUserId_UTCID01() {
	        // Setup
	    	final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        Optional<Device> deviceOtp = Optional.of(device);
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOtp);
	        // Run the test
	        final Device result = deviceServiceImplUnderTest.getDeviceTokenByUserId(0L);

	        // Verify the results
	        assertEquals(device.getId(), result.getId());
	    }
	    
	    @Test
	    void testGetDeviceTokenByUserId_UTCID02() {
	        // Setup

	        Optional<Device> deviceOtp = Optional.empty();
	        when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOtp);
	        
	        AppException appException = assertThrows(AppException.class, () -> {
	        	// Run the test
		        final Device result = deviceServiceImplUnderTest.getDeviceTokenByUserId(0L);
		    });
			
	    	String expectedMessage = "User Id not exist";
		    String actualMessage = appException.getMessage();
			
		    // Verify the results
		    assertEquals(expectedMessage,actualMessage);
	    }
	    
	    @Test
	    void getDeviceTokenByStoreId_UTCID01() {
	    	
	    	//setup data
	    	final Store store = new Store();
	    	store.setId(2L);
	    	
	    	List<Device> lstDevice = new ArrayList<Device>();
	    	Device device = new Device();
	    	device.setId(1L);
	    	lstDevice.add(device);
	    	//mock
	    	when(deviceServiceImplUnderTest.deviceRepository.findTokenUserByStore(store.getId())).thenReturn(lstDevice);
	    	
	    	// Run the test
	        final List<Device> result = deviceServiceImplUnderTest.getDeviceTokenByStoreId(store.getId());
	    	
	    	//Verify the results
	    	assertEquals(device.getId(),result.get(0).getId());
	    }

	    @Test
	    void testGetDeviceTokenByCity_UTCID01() {
	        // Setup
	        // Configure DeviceRepository.findTokenUserByCityId(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        final List<Device> devices = Arrays.asList(device);
	        when(deviceServiceImplUnderTest.deviceRepository.findTokenUserByCityId(0L, 0L)).thenReturn(devices);

	        // Run the test
	        final List<Device> result = deviceServiceImplUnderTest.getDeviceTokenByCity(0L, 0L);

	        // Verify the results
	    }

	    @Test
	    void testGetDeviceTokenByCity_UTCID02() {
	        // Setup
	        when(deviceServiceImplUnderTest.deviceRepository.findTokenUserByCityId(0L, 0L)).thenReturn(Collections.emptyList());

	        // Run the test
	        final List<Device> result = deviceServiceImplUnderTest.getDeviceTokenByCity(0L, 0L);

	        // Verify the results
	        assertEquals(Collections.emptyList(), result);
	    }

//	    @Test
//	    void testGetDeviceTokenByDistrict() {
//	        assertEquals(Arrays.asList("value"), deviceServiceImplUnderTest.getDeviceTokenByDistrict(0L));
//	        assertEquals(Collections.emptyList(), deviceServiceImplUnderTest.getDeviceTokenByDistrict(0L));
//	    }
//
//	    @Test
//	    void testGetDeviceTokenBySubDistrict() {
//	        assertEquals(Arrays.asList("value"), deviceServiceImplUnderTest.getDeviceTokenBySubDistrict(0L));
//	        assertEquals(Collections.emptyList(), deviceServiceImplUnderTest.getDeviceTokenBySubDistrict(0L));
//	    }

	    @Test
	    void testDeleteDeviceByUserId_UTCID01() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).delete(device);

	        // Run the test
	        deviceServiceImplUnderTest.deleteDeviceByUserId(0L);

	        // Verify the results
	    }
	    
	    @Test
	    void testDeleteDeviceByUserId_UTCID02() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = null;
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);

	        // Run the test
	        deviceServiceImplUnderTest.deleteDeviceByUserId(0L);

	        // Verify the results
	    }

	    @Test
	    void testDeleteDeviceByUserIdAndSeri_UTCID01() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserIdAndSerial(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserIdAndSerial(0L, "serial")).thenReturn(device);
	        doNothing().when(deviceServiceImplUnderTest.deviceRepository).delete(device);
	        
	        // Run the test
	        deviceServiceImplUnderTest.deleteDeviceByUserIdAndSeri(0L, "serial");

	        // Verify the results
	    }
	    
	    @Test
	    void testDeleteDeviceByUserIdAndSeri_UTCID02() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserIdAndSerial(...).
	        final Device device = null;
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserIdAndSerial(0L, "serial")).thenReturn(device);
	        
	        // Run the test
	        deviceServiceImplUnderTest.deleteDeviceByUserIdAndSeri(0L, "serial");

	        // Verify the results
	    }

	    @Test
	    void testDeleteDeviceToken_UTCID01() {
	        // Setup
	    	final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        Optional<Device> deviceOpt = Optional.of(device);
	    	//Device device = deviceRepository.findById(device_id).orElseThrow(() -> new AppException());
	    	when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);
	    	when(deviceServiceImplUnderTest.deviceRepository.save(device)).thenReturn(device);
			
	        // Run the test
	        deviceServiceImplUnderTest.deleteDeviceToken(0L);

	        // Verify the results
	    }
	    
	    @Test
	    void testDeleteDeviceToken_UTCID02() {
	        // Setup
	        Optional<Device> deviceOpt = Optional.empty();
	    	when(deviceServiceImplUnderTest.deviceRepository.findById(0L)).thenReturn(deviceOpt);
	    	
	    	AppException appException = assertThrows(AppException.class, () -> {
		        // Run the test
		        deviceServiceImplUnderTest.deleteDeviceToken(0L);
		    });
			
	    	String expectedMessage = "Device not exist";
		    String actualMessage = appException.getMessage();
			
		    //compare
		    assertEquals(expectedMessage,actualMessage);

	        // Verify the results
	    }

	    @Test
	    void testCheckUserLoginAnotherDevice_UTCID01() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("serial");
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);

	        // Run the test
	        final boolean result = deviceServiceImplUnderTest.checkUserLoginAnotherDevice(0L, "serialNum");

	        // Verify the results
	        assertTrue(result);
	    }
	    
	    @Test
	    void testCheckUserLoginAnotherDevice_UTCID02() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = null;
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);

	        // Run the test
	        final boolean result = deviceServiceImplUnderTest.checkUserLoginAnotherDevice(0L, "serialNum");

	        // Verify the results
	        assertFalse(result);
	    }
	    
	    @Test
	    void testCheckUserLoginAnotherDevice_UTCID03() {
	        // Setup
	        // Configure DeviceRepository.findDeviceByUserId(...).
	        final Device device = new Device();
	        device.setToken("token");
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
	        device.setUser(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));
	        device.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
	        device.setSerial("123456789");
	        when(deviceServiceImplUnderTest.deviceRepository.findDeviceByUserId(0L)).thenReturn(device);

	        // Run the test
	        final boolean result = deviceServiceImplUnderTest.checkUserLoginAnotherDevice(0L, "123456789");

	        // Verify the results
	        assertFalse(result);
	    }

}
