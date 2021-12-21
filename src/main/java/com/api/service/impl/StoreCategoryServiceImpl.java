package com.api.service.impl;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dto.StoreCategoryDto;
import com.api.entity.StoreCategory;
import com.api.mapper.MapStructMapper;
import com.api.repositories.StoreCategoryRepository;
import com.api.service.StoreCategoryService;
import com.exception.AppException;

@Service
public class StoreCategoryServiceImpl implements StoreCategoryService{
	
	@Autowired
	StoreCategoryRepository cateRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Override
	public List<StoreCategoryDto> getAllCategory() {
		List<StoreCategory> rs = cateRepository.findAll();
		return mapStructMapper.lstStoreCateToStoreCateDto(rs);
	}
	
	@Override
	public void insertCategory(StoreCategoryDto storeCategory) {
		if(null == storeCategory) {
			throw new AppException(402,"Store category cannot null");
		}
		if (cateRepository.findByName(storeCategory.getName()).isPresent()) {
			throw new AppException(403, "Category is exsit!");
		}
		StoreCategory cate = mapStructMapper.cateDtoToCate(storeCategory);
		cateRepository.save(cate);
	}
	
	@Override
	public void deleteCategory(List<StoreCategoryDto> storeCategory) {
		// TODO Auto-generated method stub
		List<StoreCategory> lstcate = mapStructMapper.lstStoreCateDtoToStoreCate(storeCategory);
		cateRepository.deleteAll(lstcate);
	}

	@Override
	public void updateCategory(StoreCategoryDto storeCategory) {
		StoreCategory stCate = cateRepository.getById(storeCategory.getId());
		if(null == stCate) {
			throw new AppException(402,"Store category cannot null");
		}

		if (cateRepository.findByName(storeCategory.getName()).isPresent()) {
			throw new AppException(403, "Category is exsit!");
		}
		
		BeanUtils.copyProperties(storeCategory, stCate);
		
		cateRepository.save(stCate);
	}
}
