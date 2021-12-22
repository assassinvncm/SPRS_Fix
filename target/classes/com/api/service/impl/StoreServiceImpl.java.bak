package com.api.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.ImageDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.SearchFilterDto;
import com.api.dto.StoreCategoryDto;
import com.api.dto.StoreDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Image;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.StoreCategory;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.StoreRepository;
import com.api.service.AddressService;
import com.api.service.AmazonClient;
import com.api.service.NotificationService;
import com.api.service.StoreService;
import com.common.utils.DateUtils;
import com.exception.AppException;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired 
	ProcedureMapper mapper;
	
	@Autowired
	private AmazonClient amazonClient;
	
	@Autowired
	NotificationService notificationService;
	
	@Override
	public Store getStoreById(Long id) {
		Store st = storeRepository.getById(id);
		return st;
	}

	@Override
	public Store getStoreByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getStoreByLocation(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getStoreByArea(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Store createStore(StoreDto s) {
		// TODO Auto-generated method stub
		Store store = mapStructMapper.storeDtoToStore(s);
//		List<StoreCategory> lstSTCate = store.getStore_category().stream().map(st ->{
//			st.setId(store.getId());
//			return st;
//		}).collect(Collectors.toList());
//		store.setStore_category(lstSTCate);
		Store str = new Store();
		try {
			Address address = addressService.mapAddress(s.getAddress());
			store.setLocation(address);
			store.setStatus(1); 
			store.setCreate_time(DateUtils.getCurrentSqlDate());
			str = storeRepository.save(store);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public Store updateStore(StoreDto s) {
		// TODO Auto-generated method stub
//		Store st = storeRepository.getById(s.getId());
//		if(null == st) {
//			throw new AppException(402,"Store is not Found!");
//		}
//		st.setClose_time(s.getClose_time());
//		st.setDescription(s.getDescription());
//		st.setOpen_time(s.getOpen_time());
//		st.setStatus(s.getStatus());
//		st.setLocation(s.getLocation());
//		st.setStore_category(st.getStore_category());
//		
//		return storeRepository.save(st);
		Store st = storeRepository.getById(s.getId());
		if (null == st) {
			throw new AppException(402, "Store is not Found!");
		}
		if (s.getAddress().getId() == 0) {
			throw new AppException(402, "Id of Address is not Found!");
		}
		s.getStoreDetail().forEach((sct) -> {
			if(sct.getId() == 0) {
				throw new AppException(402, "Id of Category is not Found!");
			}
		});

		//BeanUtils.copyProperties(rp, reliefPoint);
		//Store storeTemp = mapStructMapper.storeDtoToStore(s);
		List<StoreCategoryDto> lstStoreDetailDto = s.getStoreDetail();
		List<StoreCategory> lstStoreDetail = lstStoreDetailDto.stream().map(storeDetailDto -> {
			StoreCategory storeDetail = new StoreCategory();
			storeDetail.setId(storeDetailDto.getId());
			storeDetail.setName(storeDetailDto.getName());
			return storeDetail;
		}).collect(Collectors.toList());
		st.setStore_category(lstStoreDetail);
		Address address = addressService.mapAddress(s.getAddress());
		st.setLocation(address);
		st.setClose_time(DateUtils.stringToTimeHHMM(s.getClose_time()));
		st.setDescription(s.getDescription());
		st.setOpen_time(DateUtils.stringToTimeHHMM(s.getOpen_time()));
		st.setStatus(s.getStatus());
		st.setName(s.getName());
		
		return storeRepository.saveAndFlush(st);
	}

	@Override
	public Store openCloseStore(StoreDto s) {
		// TODO Auto-generated method stub
		Store st = storeRepository.getById(s.getId());
		Time currTime = new Time(System.currentTimeMillis());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		if(s.getStatus() == 0) {
			st.setOpen_time(currTime);
		}else if(s.getStatus() == 1) {
			st.setClose_time(currTime);
		}
		
		return storeRepository.save(st);
	}

	@Override
	public Store uploadStoreImg(ImageDto image) {
		// TODO Auto-generated method stub
		Store st = getStoreById(image.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		String img_url = amazonClient.uploadFile(image);
		st.setImages(new Image(img_url));
//		st.getLstImage().add(new Image(st, img_url));
		
		return storeRepository.save(st);
	}

	@Override
	public List<StoreDto> getAllStore() {
		// TODO Auto-generated method stub
		List<Store> lstStore = storeRepository.findAll();
		return mapStructMapper.lstStoreToStoreDto(lstStore);
	}

	@Override
	public Store deleteStore(StoreDto s) {
		Store st = storeRepository.getById(s.getId());
		if(null == st) {
			throw new AppException(402,"Store is not Found!");
		}
		storeRepository.delete(st);
		return st;
	}

	@Override
	public List<StoreDto> getStoreOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStoreFilterByType(long user_id, SearchFilterDto filter) {
		List<StoreDto> lstStoreRs = new ArrayList<StoreDto>();
		Sort sortable = null;
	    if (filter.getSort()) {
	      sortable = Sort.by("name").descending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize());
		Page<Store> pageStore = storeRepository.getStoreByStatusOrType(user_id, filter.getStatus_store(),filter.getType(),pageable);
		lstStoreRs = mapStructMapper.lstStoreToStoreDto(pageStore.getContent());
	    Map<String, Object> response = new HashMap<>();
        response.put("stores", lstStoreRs);
        response.put("currentPage", pageStore.getNumber());
        response.put("totalItems", pageStore.getTotalElements());
        response.put("totalPages", pageStore.getTotalPages());
		return response;
	}
	
	@Override
	public Map<String, Object> getStoreCommon(long store_id, User user) {
		// TODO Auto-generated method stub
		Store st = storeRepository.getById(store_id);
		boolean isSubcribe = false;
		if(user != null){
			List<User> lstU = st.getStore_user();
			for(User u : lstU) {
				if(user.getId() == u.getId()) {
					isSubcribe = true;
					break;
				}
			}
		}
		
		StoreDto stDto =  mapStructMapper.storeToStoreDTO(st);
		Map<String, Object> response = new HashMap<>();
		response.put("stores", stDto);
	    response.put("isSubcribe", isSubcribe);
		return 	response;
	}
}
