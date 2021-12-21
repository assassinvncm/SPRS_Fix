package com.api.service;

import java.util.List;

import com.api.dto.StoreCategoryDto;

public interface StoreCategoryService {
	
	List<StoreCategoryDto> getAllCategory();
	
	void insertCategory(StoreCategoryDto storeCategory);
	
	void deleteCategory(List<StoreCategoryDto> storeCategory);
	
	void updateCategory(StoreCategoryDto storeCategory);
}
