package com.api.repositories.custom;

import java.util.List;


public interface DeviceRepositoryCustom {

	List<Object[]> getDeviceByAreasAndGroup(List<Long> group_ids, Long sub_district_id, Long district_id, Long city_id);
}
