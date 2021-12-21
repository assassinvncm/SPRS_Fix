/**
 * 
 */
package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.dto.AddressDto;
import com.api.dto.OrganizationDto;
import com.api.entity.Organization;
import com.api.entity.ReliefPoint;
import com.api.mapper.MapStructMapper;
import com.api.repositories.OrganizationRepository;
import com.api.service.OrganizationService;
import com.api.service.ReliefPointService;
import com.exception.AppException;

/**
 * @author MY PC
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

	@Mock
	OrganizationRepository organizationRepository;

	@Mock
	ModelMapper modelMapper;

	@Mock
	MapStructMapper mapStructMapper;

	@InjectMocks
	private OrganizationService organizationService = new OrganizationServiceImpl();

	@Test
	public void testGetAllOrganzization_UTCID01() {
		// TODO Auto-generated method stub

		// data mock
		List<Organization> orgs = new ArrayList<Organization>();
		Organization org = new Organization();
		org.setId(2);
		orgs.add(org);
		// mock call function
		Mockito.when(organizationRepository.findAll()).thenReturn(orgs);

		// call function
		List<Organization> orgDtoRes = organizationService.getAllOrganzization();
		assertEquals(2, orgDtoRes.get(0).getId());
	}

	@Test
	public void testUpdateOrganzization_UTCID01() {
		// set data input
		OrganizationDto orgDto = new OrganizationDto();

		// data mock
		Organization org = new Organization();
		org.setId(2);

		// mock call function
		Mockito.when(mapStructMapper.organizationDtoToOrganization(orgDto)).thenReturn(org);
		Mockito.when(organizationRepository.save(org)).thenReturn(org);

		// call method
		organizationService.updateOrganzization(orgDto);
	}

	@Test
	public void testGetOrganizationByUser_UTCID01() {
		// set data input
		long uId = 2;
		
		//mock
		Optional<Organization> organization = Optional.empty();
		Mockito.<Optional<Organization>>when(organizationRepository.findByUserId(uId)).thenReturn(organization);
		AppException appException = assertThrows(AppException.class, () -> {

			// call method
			organizationService.getOrganizationByUser(uId);
		});
		String expectedMessage = "User not valid";
		String actualMessage = appException.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testGetOrganizationByUser_UTCID02() {
		// set data input
		long uId = 2;
		
		//mock
		Organization org = new Organization();
		Optional<Organization> organization = Optional.of(org);
		Mockito.<Optional<Organization>>when(organizationRepository.findByUserId(uId)).thenReturn(organization);
		
		OrganizationDto orgDto = new OrganizationDto();
		orgDto.setId(4);
		Mockito.when( mapStructMapper.organizationToOrganizationDto(org)).thenReturn(orgDto);
		
		Mockito.when( mapStructMapper.addressToAddressDto(org.getAddress())).thenReturn(new AddressDto());
		
		//call method
		OrganizationDto orgDtoRes =  organizationService.getOrganizationByUser(uId);
		
		//compare result
		assertEquals(orgDto.getId(), orgDtoRes.getId());
		
		
	}

	@Test
	public void testGetOrganizationById_UTCID01() {
		
		long OId = 6;
		
		//mock
		Optional<Organization> organization = Optional.empty();
		Mockito.<Optional<Organization>>when(organizationRepository.findById(OId)).thenReturn(organization);
		AppException appException = assertThrows(AppException.class, () -> {

			// call method
			organizationService.getOrganizationById(OId);
		});
		String expectedMessage = "Point has removed";
		String actualMessage = appException.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testGetOrganizationById_UTCID02() {
		
		long OId = 2;
		
		//mock
		Organization org = new Organization();

		Optional<Organization> organization = Optional.of(org);
		Mockito.<Optional<Organization>>when(organizationRepository.findById(OId)).thenReturn(organization);
		
		OrganizationDto orgDto = new OrganizationDto();
		orgDto.setId(4);
		Mockito.when( mapStructMapper.organizationToOrganizationDto(org)).thenReturn(orgDto);
		
		Mockito.when( mapStructMapper.addressToAddressDto(org.getAddress())).thenReturn(new AddressDto());
		
		//call method
		OrganizationDto orgDtoRes = organizationService.getOrganizationById(OId);
		
		//compare result
		assertEquals(orgDto.getId(), orgDtoRes.getId());
		
	}

}
