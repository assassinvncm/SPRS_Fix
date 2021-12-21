package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DeviceDto;
import com.api.dto.DistrictDto;
import com.api.dto.SubDistrictDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.District;
import com.api.entity.SubDistrict;
import com.api.repositories.AddressRepository;
import com.api.repositories.CityRepository;
import com.api.repositories.DistrictRepository;
import com.api.repositories.SubDistrictRepository;
import com.exception.AppException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AddressSerivceImplTest {

	private AddressSerivceImpl addressSerivceImplUnderTest;

	@BeforeEach
	void setUp() throws Exception {
		addressSerivceImplUnderTest = new AddressSerivceImpl();
		addressSerivceImplUnderTest.addressRepository = mock(AddressRepository.class);
		addressSerivceImplUnderTest.subDistrictRepository = mock(SubDistrictRepository.class);
		addressSerivceImplUnderTest.districtRepository = mock(DistrictRepository.class);
		addressSerivceImplUnderTest.cityRepository = mock(CityRepository.class);
//	        addressSerivceImplUnderTest.modelMapper = mock(${param.type.canonicalName
//	        }.class);
	}

	@Test
	void testGetAddressById_UTCID01() {
		// Setup
		// Run the test
		final AddressDto result = addressSerivceImplUnderTest.getAddressById();

		// Verify the results
	}
	
	/**
	 * Test for function SaveAddress
	 * throw exception when function findSubDistrict is empty
	 */
	@Test
	void testSaveAddress_UTCID01() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
				"name", new CityDto(0L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(0L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");
		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
				addressDto.getCity().getName())).thenReturn(Optional.empty());

		
		
		AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			addressSerivceImplUnderTest.saveAddress(addressDto);
	    });
		
		final String expectedMessage = "Address Not Exits In DB";
	    String actualMessage = appException.getMessage();
		
	    // Verify the results
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	void testSaveAddress_UTCID02() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
				"name", new CityDto(0L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(0L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");
		
		Address adress = new Address();
		adress.setId(0L);
		
		// Configure SubDistrictRepository.findSubDistrict(...).
		final Optional<List<SubDistrict>> subDistricts = Optional.of(Arrays.asList(new SubDistrict("code", "name",
				new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati")))));
		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
				addressDto.getCity().getName())).thenReturn(subDistricts);
		when(addressSerivceImplUnderTest.addressRepository.save(any(Address.class))).thenReturn(adress);

		// Run the test
		addressSerivceImplUnderTest.saveAddress(addressDto);

		// Verify the results
        verify(addressSerivceImplUnderTest.addressRepository).save(any(Address.class));
	}

	
	@Test
	void testSaveAddress_UTCID03() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(1L, "code", "name"), new DistrictDto(1L, "code",
				"name", new CityDto(1L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(1L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");
		
		
		Address adress = new Address();
		adress.setId(1L);
		// Configure SubDistrictRepository.findSubDistrict(...).
		SubDistrict sd = new SubDistrict();
		sd.setId(1L);
		when(addressSerivceImplUnderTest.subDistrictRepository.getById(addressDto.getSubDistrict().getId())).thenReturn(sd);
		when(addressSerivceImplUnderTest.addressRepository.save(any(Address.class))).thenReturn(adress);
		
		// Run the test
		addressSerivceImplUnderTest.saveAddress(addressDto);

		// Verify the results
        verify(addressSerivceImplUnderTest.subDistrictRepository).getById(addressDto.getSubDistrict().getId());
	}

	


//	@Test
//	void testSaveAddress_UTCID04() {
//		// Setup
//		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
//				"name", new CityDto(0L, "code", "name"),
//				Arrays.asList(new SubDistrict("code", "name",
//						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
//						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
//								"gPS_Lati"))))),
//				new SubDistrictDto(0L, "code", "name",
//						new District("code", "name", new City("code", "name", Arrays.asList()),
//								Arrays.asList(new SubDistrict("code", "name", null,
//										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
//												"gPS_Long", "gPS_Lati"))))),
//						Arrays.asList()),
//				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");
//		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
//				addressDto.getCity().getName())).thenReturn(Optional.of(Collections.emptyList()));
//
//
//		AppException appException = assertThrows(AppException.class, () -> {
//			// Run the test
//			addressSerivceImplUnderTest.saveAddress(addressDto);
//	    });
//		
//		final String expectedMessage = "Address Not Exits In DB";
//	    String actualMessage = appException.getMessage();
//		
//	    // Verify the results
//	    assertEquals(expectedMessage,actualMessage);
//	}
	
	@Test
	void testMapAddress_UTCID01() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(1L, "code", "name"), new DistrictDto(1L, "code",
				"name", new CityDto(1L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(1L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");

		// Configure SubDistrictRepository.findSubDistrict(...).
		Address adress = new Address();
		adress.setId(1L);
		// Configure SubDistrictRepository.findSubDistrict(...).
		SubDistrict sd = new SubDistrict();
		sd.setId(1L);
		Optional<SubDistrict> spOpt = Optional.of(sd);
		when(addressSerivceImplUnderTest.subDistrictRepository.findById(addressDto.getSubDistrict().getId())).thenReturn(spOpt);

		// Run the test
		final Address result = addressSerivceImplUnderTest.mapAddress(addressDto);

	    // Verify the results
	    assertEquals(addressDto.getId(),result.getId());
	    assertEquals(result.getSubDistrict(),spOpt.get());
	}
	
	@Test
	void testMapAddress_UTCID02() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(1L, "code", "name"), new DistrictDto(1L, "code",
				"name", new CityDto(1L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(1L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");

		// Configure SubDistrictRepository.findSubDistrict(...).
		
		Address adress = new Address();
		adress.setId(1L);
		// Configure SubDistrictRepository.findSubDistrict(...).
		SubDistrict sd = new SubDistrict();
		sd.setId(1L);
		Optional<SubDistrict> spOpt = Optional.of(sd);
		when(addressSerivceImplUnderTest.subDistrictRepository.findById(addressDto.getSubDistrict().getId())).thenReturn(Optional.empty());

		AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			final Address result = addressSerivceImplUnderTest.mapAddress(addressDto);
	    });
		
		final String expectedMessage = "Id address not valid";
	    String actualMessage = appException.getMessage();
		
	    // Verify the results
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	void testMapAddress_UTCID03() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
				"name", new CityDto(0L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(0L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");

		// Configure SubDistrictRepository.findSubDistrict(...).
		final Optional<List<SubDistrict>> subDistricts = Optional.of(Arrays.asList(new SubDistrict("code", "name",
				new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati")))));
		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
				addressDto.getCity().getName())).thenReturn(Optional.empty());

		AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			final Address result = addressSerivceImplUnderTest.mapAddress(addressDto);
	    });
		
		final String expectedMessage = "Address Not Exits In DB";
	    String actualMessage = appException.getMessage();
		
	    // Verify the results
	    assertEquals(expectedMessage,actualMessage);
	}
	
	@Test
	void testMapAddress_UTCID04() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
				"name", new CityDto(0L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(0L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");

		// Configure SubDistrictRepository.findSubDistrict(...).
		final Optional<List<SubDistrict>> subDistricts = Optional.of(Arrays.asList(new SubDistrict("code", "name",
				new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati")))));
		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
				addressDto.getCity().getName())).thenReturn(Optional.of(Collections.emptyList()));

		AppException appException = assertThrows(AppException.class, () -> {
			// Run the test
			final Address result = addressSerivceImplUnderTest.mapAddress(addressDto);
	    });
		
		final String expectedMessage = "Address Not Exits In DB";
	    String actualMessage = appException.getMessage();
		
	    // Verify the results
	    assertEquals(expectedMessage,actualMessage);
	}

	@Test
	void testMapAddress_UTCID05() {
		// Setup
		final AddressDto addressDto = new AddressDto(new CityDto(0L, "code", "name"), new DistrictDto(0L, "code",
				"name", new CityDto(0L, "code", "name"),
				Arrays.asList(new SubDistrict("code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()),
						Arrays.asList(new Address("city", "province", "district", null, "addressLine", "gPS_Long",
								"gPS_Lati"))))),
				new SubDistrictDto(0L, "code", "name",
						new District("code", "name", new City("code", "name", Arrays.asList()),
								Arrays.asList(new SubDistrict("code", "name", null,
										Arrays.asList(new Address("city", "province", "district", null, "addressLine",
												"gPS_Long", "gPS_Lati"))))),
						Arrays.asList()),
				"addressLine1", "addressLine2", "gPS_long", "gPS_lati");

		// Configure SubDistrictRepository.findSubDistrict(...).
		final Optional<List<SubDistrict>> subDistricts = Optional.of(Arrays.asList(new SubDistrict("code", "name",
				new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati")))));
		when(addressSerivceImplUnderTest.subDistrictRepository.findSubDistrict(addressDto.getSubDistrict().getName(), addressDto.getDistrict().getName(),
				addressDto.getCity().getName())).thenReturn(subDistricts);

		// Run the test
		final Address result = addressSerivceImplUnderTest.mapAddress(addressDto);

		// Verify the results
		assertEquals(addressDto.getId(),result.getId());
		assertEquals(subDistricts.get().get(0),result.getSubDistrict());
		
	}

	@Test
	void testUpdateAddress() {
		// Setup
		// Run the test
		final AddressDto result = addressSerivceImplUnderTest.updateAddress();

		// Verify the results
	}

	@Test
	void testGetSubDistrictById() {
		// Setup
		// Run the test
		final SubDistrictDto result = addressSerivceImplUnderTest.getSubDistrictById();

		// Verify the results
	}

	@Test
	void testGetSDByDistrictId_UTCID01() {
		// Setup
		// Configure SubDistrictRepository.findByDistrictId(...).
		final List<SubDistrict> subDistricts = Arrays.asList(new SubDistrict("code", "name",
				new District("code", "name", new City("code", "name", Arrays.asList()), Arrays.asList()), Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))));
		when(addressSerivceImplUnderTest.subDistrictRepository.findByDistrictId(0L)).thenReturn(subDistricts);

		// Run the test
		final List<SubDistrictDto> result = addressSerivceImplUnderTest.getSDByDistrictId(0L);

		// Verify the results
	}

	@Test
	void testGetSDByDistrictId_UTCID02() {
		// Setup
		when(addressSerivceImplUnderTest.subDistrictRepository.findByDistrictId(0L))
				.thenReturn(Collections.emptyList());

		// Run the test
		final List<SubDistrictDto> result = addressSerivceImplUnderTest.getSDByDistrictId(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testGetDistrictByCityId_UTCID01() {
		// Setup
		// Configure DistrictRepository.findByCityId(...).
		final List<District> districts = Arrays.asList(new District("code", "name",
				new City("code", "name", Arrays.asList()),
				Arrays.asList(new SubDistrict("code", "name", null, Arrays.asList(
						new Address("city", "province", "district", null, "addressLine", "gPS_Long", "gPS_Lati"))))));
		when(addressSerivceImplUnderTest.districtRepository.findByCityId(0L)).thenReturn(districts);

		// Run the test
		final List<DistrictDto> result = addressSerivceImplUnderTest.getDistrictByCityId(0L);

		// Verify the results
	}

	@Test
	void testGetDistrictByCityId_UTCID02() {
		// Setup
		when(addressSerivceImplUnderTest.districtRepository.findByCityId(0L)).thenReturn(Collections.emptyList());

		// Run the test
		final List<DistrictDto> result = addressSerivceImplUnderTest.getDistrictByCityId(0L);

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testGetCityById() {
		// Setup
		// Run the test
		final CityDto result = addressSerivceImplUnderTest.getCityById();

		// Verify the results
	}

//	@Test
//	void testGetAllCity() {
//		// Setup
//		List<City> listCity = new ArrayList<City>();
//		City city1 = new City();
//		city1.setId(1L);
//		city1.setName("Ha Noi");
//		
//		City city2 = new City();
//		city2.setId(2L);
//		city2.setName("Viet Nam");
//		listCity.add(city1);
//		listCity.add(city2);
//		
//		List<CityDto> listCityDto = new ArrayList<CityDto>();
//		CityDto cityDto1 = new CityDto();
//		cityDto1.setId(1L);
//		listCityDto.add(cityDto1);
//		
//		when(addressSerivceImplUnderTest.cityRepository.findAll()).thenReturn(listCity);
//		
//		// Run the test
//		final List<CityDto> result = addressSerivceImplUnderTest.getAllCity();
//
//		// Verify the results
//	}
	
	@Test
	void testGetAllCity_UTCID01() {
		// Setup
		// Run the test
		final List<CityDto> result = addressSerivceImplUnderTest.getAllCity();

		// Verify the results
	}

}
