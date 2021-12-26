package com.api.repositories.custom;

import java.util.List;


public interface DeviceRepositoryCustom {

	/**
	 * get device by areas and group user
	 * @param group_ids
	 * @param sub_district_id
	 * @param district_id
	 * @param city_id
	 * @return
	 */
	List<Object[]> getDeviceByAreasAndGroup(List<Long> group_ids, Long sub_district_id, Long district_id, Long city_id);
	
	/**
	 * get device of user who subcribe store id
	 * @return
	 */
	List<Object[]> getDeviceBySubStore(long st_id);
}
