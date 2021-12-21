package com.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.api.entity.Device;
import com.api.repositories.custom.DeviceRepositoryCustom;

public interface DeviceRepository extends JpaRepository<Device, Long> {

//	@Query(value = "SELECT d.* FROM Device d where d.user_id = :uid",nativeQuery = true)
//	Optional<Device> findDeviceByUserId(@Param("uid") Long uId);

	@Query(value = "SELECT DISTINCT d.* FROM sprs_users u " 
			+ "left join sprs_device d on u.id = d.user_id \r\n"
			+ "inner join sprs_address a on d.address_id = a.id\r\n"
			+ "inner join sprs_sub_district sd on a.sub_district_id = sd.id\r\n"
			+ "inner join sprs_district d2 on sd.districts_id = d2.id\r\n"
			+ "inner join sprs_city c on d2.city_id = c.id where c.id = :cid and u.id != :uId ", nativeQuery = true)
	List<Device> findTokenUserByCityId(@Param("uId") Long u_id, @Param("cid") Long city_id);

	@Query(value = "SELECT DISTINCT d.* FROM sprs_v1.sprs_store s \r\n"
			+ "INNER JOIN sprs_v1.sprs_store_subcribe ss ON s.id = ss.store_id\r\n"
			+ "INNER JOIN sprs_v1.sprs_users u ON ss.user_id = u.id\r\n"
			+ "INNER JOIN sprs_v1.sprs_device d ON u.id = d.user_id\r\n" 
			+ "WHERE s.id = :sid", nativeQuery = true)
	List<Device> findTokenUserByStore(@Param("sid") Long store_id);

	@Query(value = "SELECT sd.* FROM sprs_device sd INNER JOIN sprs_users u ON sd.user_id = u.id WHERE u.id = :uid", nativeQuery = true)
	Device findDeviceByUserId(@Param("uid") Long user_id);

	@Query(value = "SELECT sd.* FROM sprs_device sd INNER JOIN sprs_users u ON sd.user_id = u.id WHERE u.id = :uid AND sd.serial = :serial ", nativeQuery = true)
	Device findDeviceByUserIdAndSerial(@Param("uid") Long user_id, @Param("serial") String serial);

	@Modifying
	@Query("delete from Device d where d.serial = :serial")
	void deleteBySerial(@Param("serial") String serial);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM sprs_device WHERE sprs_device.user_id = :uid", nativeQuery = true)
	void deleteByUserId(@Param("uid") Long user_id);
	
	Optional<Device> findBySerial(String serial);

}
