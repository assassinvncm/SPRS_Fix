package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.SPRSResponse;
import com.api.dto.StoreCategoryDto;
import com.api.entity.StoreCategory;
import com.api.repositories.StoreCategoryRepository;
import com.api.service.StoreCategoryService;
import com.ultils.Constants;

@RestController
@RequestMapping("/sprs/api/store-category")
public class StoreCategoryController {
	
	@Autowired
	StoreCategoryService cateServ;
	
	@Autowired
	StoreCategoryRepository cateRepo;
	
	@RequestMapping(value = "/get",method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getCategory() {
		List<StoreCategoryDto> lstCateDto = cateServ.getAllCategory();
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get List Store Category Successfull", "", lstCateDto, null));
	}
	
	@RequestMapping(value = "/get{id}",method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> getCategoryById(@PathVariable(value = "id") Long id) {
		StoreCategory rs = cateRepo.getById(id);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Get Store By ID "+id+" success", "", rs, null));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> createCategory(@Validated @RequestBody StoreCategoryDto cateDto) {
		try {
			cateServ.insertCategory(cateDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Insert Store Category Successfull", "", cateDto, null));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.PUT)
	@PreAuthorize("hasAnyAuthority('PER_MOB_STORE')")
	public ResponseEntity<?> deleteCategory(@Validated @RequestBody List<StoreCategoryDto> lstItemDto) {
		cateServ.deleteCategory(lstItemDto);
		return ResponseEntity.ok(new SPRSResponse(Constants.SUCCESS, "Delete list Store Category Successfull", "", lstItemDto, null));
	}
}
