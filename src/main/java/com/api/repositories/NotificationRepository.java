package com.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query(value = "SELECT nr from Notification nr INNER JOIN nr.receivers u where u.id = :uId")
	Page<Notification> getNotifications(@RequestParam("uId") Long uId, Pageable pageable);

	@Query(value = "SELECT count(nr) from Notification nr INNER JOIN nr.receivers u where u.id = :uId and nr.status = :status")
	int getQuantityNotificationsByStatus(@RequestParam("uId") Long uId,@RequestParam("status") String status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE sprs_v1.sprs_notification nr \r\n"
			+ "	INNER JOIN sprs_v1.sprs_user_receiver_notification u  ON nr.id = u.notification_id\r\n"
			+ "SET nr.status = :status \r\n"
			+ "where u.reveiver_id = :uId AND nr.status = 'uncheck'",nativeQuery = true)
	int updateUnCheckStatusByUserId(@RequestParam("uId") Long uId,@RequestParam("status") String status);
}
