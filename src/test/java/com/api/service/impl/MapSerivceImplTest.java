package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import com.api.dto.MapPointsDto;
import com.api.dto.SearchMapResponse;
import com.api.repositories.MapRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@TestPropertySource(properties = { "goong.map.api.key=LwBkYGIlqOTtypHy0aq9AL3vdMLYgnM0JX9BHJON"})
class MapSerivceImplTest {

	@Mock
	MapRepository mapRepository;

	@InjectMocks
	MapSerivceImpl mapSerivceImpl;
	
//	@InjectMocks
//	private String api_key;
	
	@BeforeEach
    public void setUp() {
		ReflectionTestUtils.setField(mapSerivceImpl, "api_key", "goong.map.api.key=LwBkYGIlqOTtypHy0aq9AL3vdMLYgnM0JX9BHJON");
    }

	@Test
	void testFindAllPoints_UTCID01() {
		double la = 9.81791814590403;
		double lo = 105.5900620709036;
		double radius = 100.3;
		String typePoint = null;

		// mock data
		List<Object[]> mapPoints = new ArrayList<Object[]>();
		Object[] mapPoint1 = new Object[] { new BigInteger("1"), "abc", "12.3412123", "5.2132132",new BigInteger("2"), "acs" };
		Object[] mapPoint2 = new Object[] { new BigInteger("2"), "store", "11.3412123", "7.2132132",new BigInteger("4"), "abc" };
		mapPoints.add(mapPoint1);
		mapPoints.add(mapPoint2);
		// mock method
		Mockito.when(mapRepository.getPoints(la, lo,radius, "rp,sos,st,org")).thenReturn(mapPoints);

		// call api
		List<MapPointsDto> lstMapPoints = mapSerivceImpl.findAllPoints(la, lo, radius, typePoint);

		// compare result
		assertEquals(mapPoint1[0].toString(), lstMapPoints.get(0).getId().toString());

	}
	
	@Test
	void testFindAllPoints_UTCID02() {
		// init input data
		double la = 9.81791814590403;
		double lo = 105.5900620709036;
		double radius = 100.3;
		String typePoint = "";

		// mock data
		List<Object[]> mapPoints = new ArrayList<Object[]>();
		Object[] mapPoint1 = new Object[] { new BigInteger("1"), "abc", "12.3412123", "5.2132132",new BigInteger("2"), "acs" };
		Object[] mapPoint2 = new Object[] { new BigInteger("2"), "store", "11.3412123", "7.2132132",new BigInteger("4"), "abc" };
		mapPoints.add(mapPoint1);
		mapPoints.add(mapPoint2);
		// mock
		Mockito.when(mapRepository.getPoints(la, lo, radius, "rp,sos,st,org")).thenReturn(mapPoints);

		List<MapPointsDto> lstMapPoints = new ArrayList<MapPointsDto>();
		// call api
		lstMapPoints = mapSerivceImpl.findAllPoints(la, lo, radius, typePoint);

		// compare result
		assertEquals(mapPoint1[0].toString(), lstMapPoints.get(0).getId().toString());

	}

	@Test
	void testFindAllPoints_UTCID03() {
		// init input data
		double la = 9.81791814590403;
		double lo = 105.5900620709036;
		double radius = 100.3;
		String typePoint = "st";

		// data mock 
		List<Object[]> mapPoints = new ArrayList<Object[]>();
		Object[] mapPoint1 = new Object[] { new BigInteger("1"), "abc", "12.3412123", "5.2132132",new BigInteger("2"), "acs" };
		Object[] mapPoint2 = new Object[] { new BigInteger("2"), "store", "11.3412123", "7.2132132",new BigInteger("4"), "abc" };
		mapPoints.add(mapPoint1);
		mapPoints.add(mapPoint2);
		// mock
		Mockito.when(mapRepository.getPoints(la, lo, radius, typePoint)).thenReturn(mapPoints);

		List<MapPointsDto> lstMapPoints = new ArrayList<MapPointsDto>();
		// call api
		lstMapPoints = mapSerivceImpl.findAllPoints(la, lo, radius, typePoint);

		// compare result
		assertEquals(mapPoint1[0].toString(), lstMapPoints.get(0).getId().toString());

	}
	
	
	@Test
	void search_UTCID01() {
		
		//Data input for method
		String text = "abvc"; 
		double lati = 9.81791814590403; 
		double longti = 105.5900620709036; 
		int numberOfRecord = 2;
		
		//data mocks
		List<Object[]> lstRs = new ArrayList<Object[]>();
		Object[] rs = new Object[] { new BigInteger("1"), "place_id", "name", "5.2132132", "6.2132132","","st"};
		lstRs.add(rs);
		lstRs.add(rs);
		
		// mock
		Mockito.when(mapRepository.search(text, lati, longti, numberOfRecord)).thenReturn(lstRs);
		
		// call api
		List<SearchMapResponse> searchRs = mapSerivceImpl.search(text, lati, longti, numberOfRecord);
		
		// compare result
		assertEquals(2, searchRs.size());

	}
	
//	@Test
//	void search_UTCID02() {
//		
//		//Data input for method
//		String text = "abvc"; 
//		double lati = 9.81791814590403; 
//		double longti = 105.5900620709036; 
//		int numberOfRecord = 5;
//		
//		//data mocks
//		List<Object[]> lstRs = new ArrayList<Object[]>();
//		Object[] rs = new Object[] { new BigInteger("1"), "place_id", "name", "5.2132132", "6.2132132","","st"};
//		lstRs.add(rs);
//		lstRs.add(rs);
//		
//		// mock
//		Mockito.when(mapRepository.search(text, lati, longti, numberOfRecord)).thenReturn(lstRs);
//		
//		// call api
//		List<SearchMapResponse> searchRs = mapSerivceImpl.search(text, lati, longti, numberOfRecord);
//		
//		// compare result
//		assertEquals(5, searchRs.size());
//
//	}

}
