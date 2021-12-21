package com.api.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.dto.*;
import com.api.entity.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MapStructMapperTest {

	private MapStructMapperImpl mapStructMapperImplUnderTest;

    @BeforeEach
    void setUp() {
        mapStructMapperImplUnderTest = new MapStructMapperImpl();
    }

    @Test
    void testAddressToAddressDto_UTCID01() {
        // Setup
        final Address address = new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati");

        // Run the test
        final AddressDto result = mapStructMapperImplUnderTest.addressToAddressDto(address);

        // Verify the results
    }

    @Test
    void testAddressDtoToAddress_UTCID01() {
        // Setup
        final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati");

        // Run the test
        final Address result = mapStructMapperImplUnderTest.addressDtoToAddress(addressDto);

        // Verify the results
    }

    @Test
    void testGroupToGroupDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));

        // Run the test
        final GroupDto result = mapStructMapperImplUnderTest.groupToGroupDto(group);

        // Verify the results
    }

    @Test
    void testUserToUserDto_forGet_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final User user = new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group));

        // Run the test
        final UserDto result = mapStructMapperImplUnderTest.userToUserDto_forGet(user);

        // Verify the results
    }

    @Test
    void testLstGroupToGroupDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final List<Group> lstGroup = Arrays.asList(group);

        // Run the test
        final List<GroupDto> result = mapStructMapperImplUnderTest.lstGroupToGroupDto(lstGroup);

        // Verify the results
    }

    @Test
    void testLstUserToUserDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final List<User> lstUser = Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));

        // Run the test
        final List<UserDto> result = mapStructMapperImplUnderTest.lstUserToUserDto(lstUser);

        // Verify the results
    }

    @Test
    void testOrganizationToOrganizationDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
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
        final Organization organization = new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)), group2, null)));

        // Run the test
        final OrganizationDto result = mapStructMapperImplUnderTest.organizationToOrganizationDto(organization);

        // Verify the results
    }

    @Test
    void testGroupDtoToGroup_UTCID01() {
        // Setup
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final GroupDto groupDto = new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, null))), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()), Arrays.asList(), Arrays.asList()), null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))));

        // Run the test
        final Group result = mapStructMapperImplUnderTest.groupDtoToGroup(groupDto);

        // Verify the results
    }

    @Test
    void testOrganizationDtoToOrganization_UTCID01() {
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
        final OrganizationDto orgDto = new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), null, Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList())), Arrays.asList()), new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), null, Arrays.asList(), Arrays.asList())), Arrays.asList()), null)));

        // Run the test
        final Organization result = mapStructMapperImplUnderTest.organizationDtoToOrganization(orgDto);

        // Verify the results
    }

    @Test
    void testUserToUserDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final User user = new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group));

        // Run the test
        final UserDto result = mapStructMapperImplUnderTest.userToUserDto(user);

        // Verify the results
    }

    @Test
    void testUserToUserDto_getUController_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final User user = new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group));

        // Run the test
        final UserDto result = mapStructMapperImplUnderTest.userToUserDto_getUController(user);

        // Verify the results
    }

    @Test
    void testUserDtoToUser_UTCID01() {
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
        final UserDto userDto = new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))));

        // Run the test
        final User result = mapStructMapperImplUnderTest.userDtoToUser(userDto);

        // Verify the results
    }

    @Test
    void testRequestToRequestDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
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
        final Request request = new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)), group1, new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))), Arrays.asList()));

        // Run the test
        final RequestDto result = mapStructMapperImplUnderTest.requestToRequestDto(request);

        // Verify the results
    }

    @Test
    void testLstRequestToRequestDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
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
        final List<Request> lstRequests = Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)), group1, new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))), Arrays.asList())));

        // Run the test
        final List<RequestDto> result = mapStructMapperImplUnderTest.lstRequestToRequestDto(lstRequests);

        // Verify the results
    }

    @Test
    void testItemToItemDto_UTCID01() {
        // Setup
        final Item item = new Item();
        item.setName("name");
        item.setUnit("unit");
        item.setDescription("description");
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        reliefPoint.setReliefInformations(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), new Item())));
        reliefPoint.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        item.setReliefInformation(Arrays.asList(new ReliefInformation(0, reliefPoint, new Item())));

        // Run the test
        final ItemDto result = mapStructMapperImplUnderTest.itemToItemDto(item);

        // Verify the results
    }

    @Test
    void testCateToCateDto_UTCID01() {
        // Setup
        final StoreCategory cate = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        cate.setCategory_store(Arrays.asList(store));
        cate.setName("name");

        // Run the test
        final StoreCategoryDto result = mapStructMapperImplUnderTest.cateToCateDto(cate);

        // Verify the results
    }

    @Test
    void testCateDtoToCate_UTCID01() {
        // Setup
        final StoreCategoryDto cateDto = new StoreCategoryDto();
        cateDto.setName("name");

        // Run the test
        final StoreCategory result = mapStructMapperImplUnderTest.cateDtoToCate(cateDto);

        // Verify the results
    }

    @Test
    void testItemDtoToItem_UTCID01() {
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
        final ItemDto itemDto = new ItemDto(0L, "name", "unit", "description", Arrays.asList(new ReliefInformationDto(0L, 0, new ReliefPointDto(0L, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", null, Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", null, Arrays.asList()), new SubDistrictDto(0L, "code", "name", null, Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")), null)));

        // Run the test
        final Item result = mapStructMapperImplUnderTest.itemDtoToItem(itemDto);

        // Verify the results
    }

    @Test
    void testLstitemToItemDto_UTCID01() {
        // Setup
        final Item item = new Item();
        item.setName("name");
        item.setUnit("unit");
        item.setDescription("description");
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        reliefPoint.setReliefInformations(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), new Item())));
        reliefPoint.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        item.setReliefInformation(Arrays.asList(new ReliefInformation(0, reliefPoint, new Item())));
        final List<Item> lstItem = Arrays.asList(item);

        // Run the test
        final List<ItemDto> result = mapStructMapperImplUnderTest.lstitemToItemDto(lstItem);

        // Verify the results
    }

    @Test
    void testLstStoreCateToStoreCateDto_UTCID01() {
        // Setup
        final StoreCategory storeCategory = new StoreCategory();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        store.setStore_category(Arrays.asList(new StoreCategory()));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        storeCategory.setCategory_store(Arrays.asList(store));
        storeCategory.setName("name");
        final List<StoreCategory> lstCate = Arrays.asList(storeCategory);

        // Run the test
        final List<StoreCategoryDto> result = mapStructMapperImplUnderTest.lstStoreCateToStoreCateDto(lstCate);

        // Verify the results
    }

    @Test
    void testLstItemDtoToItem_UTCID01() {
        // Setup
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final List<ItemDto> lstItemDto = Arrays.asList(new ItemDto(0L, "name", "unit", "description", Arrays.asList(new ReliefInformationDto(0L, 0, new ReliefPointDto(0L, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList()), new SubDistrictDto(0L, "code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, null)))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(null, null, null, "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")), null))));

        // Run the test
        final List<Item> result = mapStructMapperImplUnderTest.lstItemDtoToItem(lstItemDto);

        // Verify the results
    }

    @Test
    void testReliefPointToreliefPointDto_UTCID01() {
        // Setup
        final ReliefPoint reliefPoint = new ReliefPoint();
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

        // Run the test
        final ReliefPointDto result = mapStructMapperImplUnderTest.reliefPointToreliefPointDto(reliefPoint);

        // Verify the results
    }

    @Test
    void testStoreToStoreDTO_UTCID01() {
        // Setup
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        final StoreCategory storeCategory = new StoreCategory();
        storeCategory.setCategory_store(Arrays.asList(new Store()));
        storeCategory.setName("name");
        store.setStore_category(Arrays.asList(storeCategory));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));

        // Run the test
        final StoreDto result = mapStructMapperImplUnderTest.storeToStoreDTO(store);

        // Verify the results
    }

    @Test
    void testReliefPointDtoToreliefPoint_UTCID01() {
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

        // Run the test
        final ReliefPoint result = mapStructMapperImplUnderTest.reliefPointDtoToreliefPoint(reliefPointDto);

        // Verify the results
    }

    @Test
    void testLstGroupDtoToGroup_UTCID01() {
        // Setup
        final PermissionDto permissionDto = new PermissionDto();
        permissionDto.setCode("code");
        permissionDto.setId(0L);
        permissionDto.setTo("to");
        permissionDto.setIcon("icon");
        permissionDto.setChildren(Arrays.asList(new PermissionChildrenDto("name", "to", "icon")));
        permissionDto.setName("name");
        final List<GroupDto> lstGroupDto = Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, null))), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList()), new SubDistrictDto(0L, "code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()), Arrays.asList(), Arrays.asList()), null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", null, Arrays.asList()), Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));

        // Run the test
        final List<Group> result = mapStructMapperImplUnderTest.lstGroupDtoToGroup(lstGroupDto);

        // Verify the results
    }

    @Test
    void testLstReliefPointToreliefPointDto_UTCID01() {
        // Setup
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        final List<ReliefPoint> lstReliefPoint = Arrays.asList(reliefPoint);

        // Run the test
        final List<ReliefPointDto> result = mapStructMapperImplUnderTest.lstReliefPointToreliefPointDto(lstReliefPoint);

        // Verify the results
    }

    @Test
    void testReliefInforToReliefInforDto_UTCID01() {
        // Setup
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        final Item item1 = new Item();
        item1.setName("name");
        item1.setUnit("unit");
        item1.setDescription("description");
        final ReliefPoint reliefPoint1 = new ReliefPoint();
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
        reliefPoint1.setRelief_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group4))));
        final Group group5 = new Group();
        group5.setPlatform(0);
        group5.setName("name");
        group5.setLevel(0);
        group5.setCode("code");
        final Permission permission5 = new Permission();
        permission5.setLevel(0);
        permission5.setNode_index(0);
        permission5.setNode_from(0);
        permission5.setNode_to(0);
        permission5.setTo_page("to_page");
        permission5.setIcon_name("icon_name");
        permission5.setCode("code");
        permission5.setName("name");
        group5.setGroup_permission(Arrays.asList(permission5));
        group5.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final Group group6 = new Group();
        group6.setPlatform(0);
        group6.setName("name");
        group6.setLevel(0);
        group6.setCode("code");
        final Permission permission6 = new Permission();
        permission6.setLevel(0);
        permission6.setNode_index(0);
        permission6.setNode_from(0);
        permission6.setNode_to(0);
        permission6.setTo_page("to_page");
        permission6.setIcon_name("icon_name");
        permission6.setCode("code");
        permission6.setName("name");
        group6.setGroup_permission(Arrays.asList(permission6));
        group6.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final Group group7 = new Group();
        group7.setPlatform(0);
        group7.setName("name");
        group7.setLevel(0);
        group7.setCode("code");
        final Permission permission7 = new Permission();
        permission7.setLevel(0);
        permission7.setNode_index(0);
        permission7.setNode_from(0);
        permission7.setNode_to(0);
        permission7.setTo_page("to_page");
        permission7.setIcon_name("icon_name");
        permission7.setCode("code");
        permission7.setName("name");
        group7.setGroup_permission(Arrays.asList(permission7));
        group7.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        reliefPoint1.setOrganization(new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group5))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group6)), group7, null))));
        reliefPoint1.setImages(new Image("img_url"));
        reliefPoint1.setName("name");
        reliefPoint1.setDescription("description");
        reliefPoint1.setOpen_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint1.setClose_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        reliefPoint1.setStatus(0);
        reliefPoint1.setReliefInformations(Arrays.asList(new ReliefInformation(0, new ReliefPoint(), new Item())));
        reliefPoint1.setAddress(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        item1.setReliefInformation(Arrays.asList(new ReliefInformation(0, reliefPoint1, new Item())));
        final ReliefInformation reliefInfor = new ReliefInformation(0, reliefPoint, item1);

        // Run the test
        final ReliefInformationDto result = mapStructMapperImplUnderTest.reliefInforToReliefInforDto(reliefInfor);

        // Verify the results
    }

    @Test
    void testReliefInforDtoToReliefInfor_UTCID01() {
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
        final ReliefInformationDto reliefInforDto = new ReliefInformationDto(0L, 0, new ReliefPointDto(0L, "name", "description", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0, new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", null, Arrays.asList()), new SubDistrictDto(0L, "code", "name", null, Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))), Arrays.asList(), new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati")), new ItemDto(0L, "name", "unit", "description", Arrays.asList()));

        // Run the test
        final ReliefInformation result = mapStructMapperImplUnderTest.reliefInforDtoToReliefInfor(reliefInforDto);

        // Verify the results
    }

    @Test
    void testLstStoreToStoreDto_UTCID01() {
        // Setup
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        final StoreCategory storeCategory = new StoreCategory();
        storeCategory.setCategory_store(Arrays.asList(new Store()));
        storeCategory.setName("name");
        store.setStore_category(Arrays.asList(storeCategory));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        final List<Store> lststore = Arrays.asList(store);

        // Run the test
        final List<StoreDto> result = mapStructMapperImplUnderTest.lstStoreToStoreDto(lststore);

        // Verify the results
    }

    @Test
    void testStoreDtoToStore_UTCID01() {
        // Setup
        final StoreDto dto = new StoreDto();
        dto.setImages(new Image("img_url"));
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
        dto.setUser_st(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));
        dto.setId(0L);
        dto.setName("name");
        dto.setDescription("description");
        dto.setOpen_time("09:30");
        dto.setClose_time("16:30");
        dto.setStatus(0);
        final StoreCategoryDto storeCategoryDto = new StoreCategoryDto();
        storeCategoryDto.setName("name");
        dto.setStoreDetail(Arrays.asList(storeCategoryDto));
        dto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));

        // Run the test
        final Store result = mapStructMapperImplUnderTest.storeDtoToStore(dto);

        // Verify the results
    }

    @Test
    void testLstStoreCateDtoToStoreCate_UTCID01() {
        // Setup
        final StoreCategoryDto storeCategoryDto = new StoreCategoryDto();
        storeCategoryDto.setName("name");
        final List<StoreCategoryDto> lstCateDto = Arrays.asList(storeCategoryDto);

        // Run the test
        final List<StoreCategory> result = mapStructMapperImplUnderTest.lstStoreCateDtoToStoreCate(lstCateDto);

        // Verify the results
    }

    @Test
    void testDeviceToDeviceDto_UTCID01() {
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

        // Run the test
        final DeviceDto result = mapStructMapperImplUnderTest.deviceToDeviceDto(device);

        // Verify the results
    }

    @Test
    void testDeviceDtoToDevice_UTCID01() {
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

        // Run the test
        final Device result = mapStructMapperImplUnderTest.deviceDtoToDevice(deviceDto);

        // Verify the results
    }

    @Test
    void testSOSToSOSDto_UTCID01() {
        // Setup
        final SOS sos = new SOS("description", 0, 0, "gPS_Long", "gPS_Lati");

        // Run the test
        final SOSDto result = mapStructMapperImplUnderTest.SOSToSOSDto(sos);

        // Verify the results
    }

    @Test
    void testSOSDtoToSOS_UTCID01() {
        // Setup
        final SOSDto sosDto = new SOSDto();
        sosDto.setAddress(new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"));
        sosDto.setId(0L);
        sosDto.setDescription("description");
        sosDto.setLevel(0);
        sosDto.setStatus(0);
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
        sosDto.setUser(new UserDto(0L, "username", "phone", "password", "full_name", "dob", Date.valueOf(LocalDate.of(2020, 1, 1)), false, new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto), Arrays.asList(), Arrays.asList()), null))), Arrays.asList(new GroupDto(0L, "name", 0, Arrays.asList(permissionDto1), Arrays.asList(), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, null, new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList()))))), Arrays.asList(new RequestDto(0L, "type", "status", "message", new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), null, new GroupDto(0L, "name", 0, Arrays.asList(permissionDto2), Arrays.asList(), Arrays.asList()), new OrganizationDto(0L, "name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code", "name", new CityDto(0L, "code", "name"), Arrays.asList(new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))), new SubDistrictDto(0L, "code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList()))), Arrays.asList()), "addressLine1", "addressLine2", "gPS_long", "gPS_lati"), Arrays.asList())))));

        // Run the test
        final SOS result = mapStructMapperImplUnderTest.SOSDtoToSOS(sosDto);

        // Verify the results
    }

    @Test
    void testNotificationToNotificationDto_UTCID01() {
        // Setup
        final Notification notification = new Notification();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        final StoreCategory storeCategory = new StoreCategory();
        storeCategory.setCategory_store(Arrays.asList(new Store()));
        storeCategory.setName("name");
        store.setStore_category(Arrays.asList(storeCategory));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        notification.setStore(store);
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        reliefPoint.setRelief_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))));
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
        group3.setGroup_permission(Arrays.asList(permission3));
        final Group group4 = new Group();
        final Group group5 = new Group();
        reliefPoint.setOrganization(new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group3))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group4)), group5, null))));
        notification.setReliefPoint(reliefPoint);
        notification.setType("type");

        // Run the test
        final NotificationDto result = mapStructMapperImplUnderTest.notificationToNotificationDto(notification);

        // Verify the results
    }

    @Test
    void testLstNotificationToNotificationDto_UTCID01() {
        // Setup
        final Notification notification = new Notification();
        final Store store = new Store();
        store.setCreate_time(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        store.setImages(new Image("img_url"));
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
        store.setStore_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group))));
        final StoreCategory storeCategory = new StoreCategory();
        storeCategory.setCategory_store(Arrays.asList(new Store()));
        storeCategory.setName("name");
        store.setStore_category(Arrays.asList(storeCategory));
        store.setName("name");
        store.setDescription("description");
        store.setOpen_time(Time.valueOf(LocalTime.of(12, 0, 0)));
        store.setClose_time(Time.valueOf(LocalTime.of(12, 0, 0)));
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
        store.setUsers(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group1)));
        store.setLocation(new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"));
        notification.setStore(store);
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        reliefPoint.setRelief_user(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group2))));
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
        group3.setGroup_permission(Arrays.asList(permission3));
        final Group group4 = new Group();
        final Group group5 = new Group();
        reliefPoint.setOrganization(new Organization("name", Date.valueOf(LocalDate.of(2020, 1, 1)), "description", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group3))), Arrays.asList(new Request("type", "status", "message", Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)), new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group4)), group5, null))));
        notification.setReliefPoint(reliefPoint);
        notification.setType("type");
        final Stream<Notification> lstNotificaiton = Stream.of(notification);

        // Run the test
        final List<NotificationDto> result = mapStructMapperImplUnderTest.lstNotificationToNotificationDto(lstNotificaiton);

        // Verify the results
    }

    @Test
    void testPermisisonToPermisionDto_UTCID01() {
        // Setup
        final Permission per = new Permission();
        per.setLevel(0);
        per.setNode_index(0);
        per.setNode_from(0);
        per.setNode_to(0);
        per.setTo_page("to_page");
        per.setIcon_name("icon_name");
        per.setCode("code");
        per.setName("name");

        // Run the test
        final PermissionDto result = mapStructMapperImplUnderTest.permisisonToPermisionDto(per);

        // Verify the results
    }

    @Test
    void testLstPermissionToPermissionDto_UTCID01() {
        // Setup
        final Permission permission = new Permission();
        permission.setLevel(0);
        permission.setNode_index(0);
        permission.setNode_from(0);
        permission.setNode_to(0);
        permission.setTo_page("to_page");
        permission.setIcon_name("icon_name");
        permission.setCode("code");
        permission.setName("name");
        final List<Permission> lstPermission = Arrays.asList(permission);

        // Run the test
        final List<PermissionDto> result = mapStructMapperImplUnderTest.lstPermissionToPermissionDto(lstPermission);

        // Verify the results
    }

    @Test
    void testLstPermissionToLstGrantAccess_UTCID01() {
        // Setup
        final Permission permission = new Permission();
        permission.setLevel(0);
        permission.setNode_index(0);
        permission.setNode_from(0);
        permission.setNode_to(0);
        permission.setTo_page("to_page");
        permission.setIcon_name("icon_name");
        permission.setCode("code");
        permission.setName("name");
        final List<Permission> per = Arrays.asList(permission);

        // Run the test
        final List<PermissionDto> result = mapStructMapperImplUnderTest.lstPermissionToLstGrantAccess(per);

        // Verify the results
    }

    @Test
    void testLstReliefPointStreamToReliefPointDto_UTCID01() {
        // Setup
        final ReliefPoint reliefPoint = new ReliefPoint();
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
        final Stream<ReliefPoint> lstReliefPoint = Stream.of(reliefPoint);

        // Run the test
        final List<ReliefPointDto> result = mapStructMapperImplUnderTest.lstReliefPointStreamToReliefPointDto(lstReliefPoint);

        // Verify the results
    }

    @Test
    void testLstBanUserToBanUserDto_UTCID01() {
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
        group.setUsers_groups(Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(new Group()))));
        final List<User> lstUser = Arrays.asList(new User("username", "phone", "password", "full_name", "dob", new Address("city", "province", "district", new SubDistrict("code", "name", new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList()), "addressLine", "gPS_Long", "gPS_Lati"), Date.valueOf(LocalDate.of(2020, 1, 1)), false, Arrays.asList(group)));

        // Run the test
        final List<UserDto> result = mapStructMapperImplUnderTest.lstBanUserToBanUserDto(lstUser);

        // Verify the results
    }


}
