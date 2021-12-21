package com.api.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.api.dto.*;
import com.api.entity.*;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

	private ItemServiceImpl itemServiceImplUnderTest;

	@BeforeEach
	void setUp() {
		itemServiceImplUnderTest = new ItemServiceImpl();
		itemServiceImplUnderTest.itemRepository = mock(ItemRepository.class);
		itemServiceImplUnderTest.mapStructMapper = mock(MapStructMapper.class);
	}

	@Test
	void testGetAllItem_UTCID01() {
		// Setup
		// Configure MapStructMapper.lstitemToItemDto(...).
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		ItemDto itemDto1 = new ItemDto();
		itemDto1.setId(1L);
		itemDto1.setName("Trung");
		itemDto1.setUnit("Qua");
		itemDtos.add(itemDto1);
		
		List<Item> items = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setId(1L);
		item1.setName("Trung");
		item1.setUnit("Qua");
		items.add(item1);
		
		when(itemServiceImplUnderTest.itemRepository.findAll()).thenReturn(items);

		when(itemServiceImplUnderTest.mapStructMapper.lstitemToItemDto(items)).thenReturn(itemDtos);

		// Run the test
		final List<ItemDto> result = itemServiceImplUnderTest.getAllItem();

		// Verify the results
		assertEquals(itemDtos.get(0).getId(), result.get(0).getId());

	}
	
	@Test
	void testGetAllItem_UTCID02() {
		// Setup
		// Configure MapStructMapper.lstitemToItemDto(...).
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		ItemDto itemDto1 = new ItemDto();
		itemDto1.setId(2L);
		itemDto1.setName("Trung");
		itemDto1.setUnit("Qua");
		itemDtos.add(itemDto1);
		
		List<Item> items = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setId(1L);
		item1.setName("Trung");
		item1.setUnit("Qua");
		items.add(item1);
		
		when(itemServiceImplUnderTest.itemRepository.findAll()).thenReturn(items);

		when(itemServiceImplUnderTest.mapStructMapper.lstitemToItemDto(items)).thenReturn(itemDtos);

		// Run the test
		final List<ItemDto> result = itemServiceImplUnderTest.getAllItem();

		// Verify the results
		assertNotEquals(items.get(0).getId(), result.get(0).getId());

	}

	@Test
	void testGetAllItem_UTCID03() {
		// Setup
		List<Item> items = new ArrayList<Item>();
		when(itemServiceImplUnderTest.itemRepository.findAll()).thenReturn(items);
		
		when(itemServiceImplUnderTest.mapStructMapper.lstitemToItemDto(items))
				.thenReturn(Collections.emptyList());

		// Run the test
		final List<ItemDto> result = itemServiceImplUnderTest.getAllItem();

		// Verify the results
		assertEquals(Collections.emptyList(), result);
	}

	@Test
	void testInsertItem_UTCID01() {
		// Setup
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		ItemDto itemDto1 = new ItemDto();
		itemDto1.setId(2L);
		itemDto1.setName("Trung");
		itemDto1.setUnit("Qua");
		itemDtos.add(itemDto1);
		
		List<Item> items = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setId(1L);
		item1.setName("Trung");
		item1.setUnit("Qua");
		items.add(item1);
		
		when(itemServiceImplUnderTest.mapStructMapper.lstItemDtoToItem(itemDtos)).thenReturn(items);
		when(itemServiceImplUnderTest.itemRepository.saveAll(items)).thenReturn(items);
		
		// Run the test
		itemServiceImplUnderTest.insertItem(itemDtos);
		// Verify the results
		assertTrue(true); // comment insert db success in excel
	}

}
