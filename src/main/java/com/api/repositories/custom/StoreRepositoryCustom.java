package com.api.repositories.custom;

import java.util.List;

import com.api.dto.StoreDto;

public interface StoreRepositoryCustom {
	List<StoreDto> filterStoreByStatusType(long user_id, int status, String type);
}
