package com.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.ItemDto;
import com.api.dto.SPRSResponse;
import com.api.service.ItemService;
import com.ultils.Constants;


@RestController
@RequestMapping("/sprs/api/item")
public class ItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getItem() {
		List<ItemDto> itemDto = itemService.getAllItem();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Item Successfull", "", itemDto, null));
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<?> insertItem(@Validated @RequestBody List<ItemDto> lstItemDto) {
		itemService.insertItem(lstItemDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Insert list Item Successfull", "", lstItemDto, null));
	}
}
