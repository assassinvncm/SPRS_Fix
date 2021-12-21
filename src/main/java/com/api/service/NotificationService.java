package com.api.service;

import java.util.List;

import com.api.dto.AdminPushNotifcationRequest;
import com.api.dto.NotificationDto;
import com.api.dto.PagingResponse;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Notification;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.api.entity.User;
import com.google.firebase.messaging.BatchResponse;

public interface NotificationService {

	/**
	 * get quantity of notification that un check
	 * @param user_id
	 * @return
	 */
	int getQuantityUncheckNotification(Long user_id);
	
	/**
	 * 
	 * @param user_id
	 */
	void updateStatusCheckAll(Long user_id);

	/**
	 * get list notification by user id
	 * 
	 * @return list<Notification>
	 */
	PagingResponse<NotificationDto> getNotificationByUser(Long uId, int pageIndex, int pageSize);
	
	/**
	 * get notification by notification id
	 * 
	 * @return list<Notification>
	 */
	NotificationDto getNotificationById(Long notification_id);

	/**
	 * save list notification
	 */
	Notification saveNotification(Notification notifications);

	/**
	 * update status notification
	 * 
	 * @param notiId
	 * @param status
	 */
	void updateStatusNotification(Long notiId, String status);
	
	/**
	 * 
	 * @param object
	 * @param pushNotificationRequest
	 */
	void PushNotificationJob(Object object, PushNotificationRequest pushNotificationRequest);

	/**
	 * push notification to device when user create new relief Point
	 * @param rp
	 * @param message
	 */
	void sendPnsToDeviceWhenCreateReliefPoint(ReliefPoint rp, String message);
	
	/**
	 * push notification to device when SOS be open 
	 * @param user
	 * @param message
	 */
	void sendPnsToDeviceWhenOpenSOS(User user, String message);
	
	/**
	 * push notification to device that user subscribe store
	 * @param store
	 * @param message
	 */
	void sendPnsToDeviceSubcribeStore(Store store, String message);
	
	/**
	 * admin system send notification 
	 * @param admPsn
	 * @param admin
	 */
	void adminSendNotification(AdminPushNotifcationRequest admPsn, User admin);
}
