package com.api.mapper.proc_mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.sql.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.dto.ReportResultDto;
import com.api.entity.Device;
import com.api.entity.Store;

@ExtendWith(MockitoExtension.class)
class ProcedureMapperTest {

	private ProcedureMapper procedureMapperUnderTest;

	@BeforeEach
	void setUp() {
		procedureMapperUnderTest = new ProcedureMapper();
	}

	@Test
	void testGetStoreByStatusOrType_Mapper_UTCID01() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { new BigInteger("1"),new Time(0),"okok","duongpt", new Time(0), 1};
		lstObject.add(obj);
		// Run the test
		final List<Store> result = procedureMapperUnderTest
				.getStoreByStatusOrType_Mapper(lstObject);

		// Verify the results
		assertEquals(1, result.get(0).getId());
	}

	@Test
	void testGetDevice_UTCID01() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { new BigInteger("1"),"882hđmekdd","",new BigInteger("1"),"sgsjj27sg" };
		lstObject.add(obj);
		// Run the test
		final List<Device> result = procedureMapperUnderTest.getDevice(lstObject);

		// Verify the results
		assertEquals(1, result.get(0).getId());
	}

	@Test
	void testReportMapping_UTCID01() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { 12,12,2019,1,new BigInteger("22") };
		lstObject.add(obj);
		// List<Object[]> lstObject = new A

		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest.reportMapping(lstObject);

		// Verify the results
		assertEquals(12, result.get(0).getDay());
	}

	@Test
	void testReportMappingProvince_UTCID01() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { "Hà Nội", 1, new BigInteger("22") };
		lstObject.add(obj);
		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest
				.reportMappingProvince(lstObject);

		// Verify the results
		assertEquals(1, result.get(0).getType_point());
	}

	@Test
	void testReportMappingTopUser_UTCID01() {
		// Setup
		List<Object[]> lstObject = new ArrayList<Object[]>();
		Object[] obj = new Object[] { new BigInteger("1"), "duongpt" };
		lstObject.add(obj);
		// Run the test
		final List<ReportResultDto> result = procedureMapperUnderTest
				.reportMappingTopUser(lstObject);

		// Verify the results
		assertEquals(1,result.get(0).getTotal());
	}

}
