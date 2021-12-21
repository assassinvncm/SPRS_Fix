package com.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.ItemDto;
import com.api.entity.Item;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ItemRepository;
import com.api.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Override
	public List<ItemDto> getAllItem() {
		// TODO Auto-generated method stub
		List<Item> items = itemRepository.findAll();
		return mapStructMapper.lstitemToItemDto(items);
	}

	@Override
	public void insertItem(List<ItemDto> item) {
		// TODO Auto-generated method stub
		List<Item> items = mapStructMapper.lstItemDtoToItem(item);
		itemRepository.saveAll(items);
	}
	
}
