package com.api.service;

import java.util.List;

import com.api.dto.ItemDto;

public interface ItemService {
	
	List<ItemDto> getAllItem();
	
	void insertItem(List<ItemDto> item);
}
