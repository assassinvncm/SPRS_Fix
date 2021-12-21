package com.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.jobrunr.scheduling.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.dto.AdminPushNotifcationRequest;
import com.api.dto.NotificationDto;
import com.api.dto.PagingResponse;
import com.api.dto.PushNotificationRequest;
import com.api.dto.SubscriptionRequest;
import com.api.entity.Device;
import com.api.entity.Notification;
import com.api.entity.ReliefPoint;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.mapper.proc_mapper.ProcedureMapper;
import com.api.repositories.DeviceRepository;
import com.api.repositories.NotificationRepository;
import com.api.repositories.custom.DeviceRepositoryCustom;
import com.api.service.DeviceService;
import com.api.service.NotificationService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.ultils.Constants;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${app.firebase-config}")
	private String firebaseConfig;

	private FirebaseApp firebaseApp;

	@Autowired
	DeviceService deviceService;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	DeviceRepositoryCustom deviceRepositoryCustom;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	MapStructMapper mapStructMapper;

	@Autowired
	ProcedureMapper procedureMapper;

	@Autowired
	private JobScheduler jobScheduler;

	@PostConstruct
	private void initialize() {
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(
							GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream()))
					.build();

			if (FirebaseApp.getApps().isEmpty()) {
				this.firebaseApp = FirebaseApp.initializeApp(options);
			} else {
				this.firebaseApp = FirebaseApp.getInstance();
			}
		} catch (IOException e) {
			log.error("Create FirebaseApp Error", e);
		}
	}

	public void subscribeToTopic(SubscriptionRequest subscriptionRequest) {

		try {
			FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequest.getTokens(),
					subscriptionRequest.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase subscribe to topic fail", e);
		}
	}

	public void unsubscribeFromTopic(SubscriptionRequest subscriptionRequest) {
		try {
			FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequest.getTokens(),
					subscriptionRequest.getTopicName());
		} catch (FirebaseMessagingException e) {
			log.error("Firebase unsubscribe from topic fail", e);
		}
	}

	public String sendPnsToDevice(PushNotificationRequest pushNotificationRequest) {
		Message message = Message.builder().setToken(pushNotificationRequest.getTarget().get(0))
				.setNotification(new com.google.firebase.messaging.Notification(pushNotificationRequest.getTitle(),
						pushNotificationRequest.getBody()))
				.putAllData(pushNotificationRequest.getData()).build();

		String response = null;
		try {
			response = FirebaseMessaging.getInstance(firebaseApp).send(message);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
		}

		return response;
	}

	public BatchResponse sendPnsToDevices(PushNotificationRequest pushNotificationRequest) {
		// List<TokenDevice> tokenDevices =
		// tokenDeviceRepository.findByIdIn(pushNotificationRequestModel.getUids());
		// List<String> tokenDevices = deviceService.getDeviceTokenByCity(null);
		if (pushNotificationRequest.getTarget().isEmpty()) {
			log.info("Not have target token, Token is empty");
			return null;
		}
		MulticastMessage multicastMessage = MulticastMessage.builder().addAllTokens(pushNotificationRequest.getTarget())
				.setNotification(new com.google.firebase.messaging.Notification(pushNotificationRequest.getTitle(),
						pushNotificationRequest.getBody()))
				.putAllData(pushNotificationRequest.getData()).build();

		BatchResponse response = null;
		try {
			response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(multicastMessage);
		} catch (FirebaseMessagingException e) {
			log.error("Fail to send firebase notification", e);
			// throw new AppException(501, "Send Message is fail!");
		}

		return response;

	}

	public String sendPnsToTopic(PushNotificationRequest pushNotificationRequestModel) {
//		Message message = Message.builder().setTopic(pushNotificationRequestModel.getTarget())
//				.setNotification(com.google.firebase.messaging.Notification.builder()
//						.setTitle(pushNotificationRequestModel.getTitle())
//						.setBody(pushNotificationRequestModel.getBody()).build())
//				.putData("content", pushNotificationRequestModel.getTitle())
//				.putData("body", pushNotificationRequestModel.getBody()).build();

		String response = null;
//		try {
//			FirebaseMessaging.getInstance().send(message);
//		} catch (FirebaseMessagingException e) {
//			log.error("Fail to send firebase notification", e);
//		}

		return response;
	}

	@Override
	public void sendPnsToDeviceSubcribeStore(Store store, String message) {
		// TODO Auto-generated method stub
		
		log.info("START save notification to user that subcribe STORE ");
		List<Device> lstDevice = deviceService.getDeviceTokenByStoreId(store.getId());
		List<Notification> notifications = new ArrayList<Notification>();
		List<String> lstToken = new ArrayList<String>();

		List<User> user = new ArrayList<User>();
		for (Device d : lstDevice) {
			user.add(d.getUser());
			lstToken.add(d.getToken());
		}

		if (lstToken.isEmpty()) {
			return;
		}
		// set notification
		Notification notification = new Notification();
		// notification.setTitle(store.getName());
		notification.setMessage(message);
		notification.setStore(store);
		notification.setType(Constants.NOTIFICATION_TYPE_STORE);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());
		notification.setReceiver(user);
		// save notification
		Notification notificationRes = this.saveNotification(notification);
		log.info("END save notification to user that subcribe STORE ");
		log.info("START send notification to user that subcribe STORE ");
		// set data push notification
		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle(store.getName());
		pushNotificationRequest.setBody(message);
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", String.valueOf(notificationRes.getId()));
		data.put("type", notificationRes.getType());
		data.put("sender", String.valueOf(store.getId()));
		pushNotificationRequest.setData(data);

		this.sendPnsToDevices(pushNotificationRequest);
		log.info("end send notification to user that subcribe STORE ");
	}

	@Override
	public void sendPnsToDeviceWhenCreateReliefPoint(ReliefPoint rp, String message) {
		
		log.info("START save notification to user that subcribe RELIEFPOINT ");
		List<Device> lstDevice = deviceService.getDeviceTokenByCity(rp.getUsers().getId(),
				rp.getAddress().getSubDistrict().getDistrict().getCity().getId());

		List<String> lstToken = new ArrayList<String>();
		List<User> user = new ArrayList<User>();
		
		for (Device d : lstDevice) {
			user.add(d.getUser());
			lstToken.add(d.getToken());
		}
		if (lstToken.isEmpty()) {
			return;
		}
		
		// set notification
		Notification notification = new Notification();
		// notification.setTitle(rp.getName());
		notification.setMessage(message);
		notification.setReliefPoint(rp);
		notification.setType(Constants.NOTIFICATION_TYPE_RELIEFPOINT);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());
		notification.setReceiver(user);
		// save notification
		Notification notificationRes = this.saveNotification(notification);
		log.info("END save notification to user that subcribe RELIEFPOINT ");
		log.info("START send notification to user that subcribe RELIEFPOINT ");
		// set data push notification
		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		pushNotificationRequest.setTitle(rp.getName());
		pushNotificationRequest.setBody(message);
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", String.valueOf(notificationRes.getId()));
		data.put("type", notificationRes.getType());
		data.put("sender", String.valueOf(rp.getId()));
		pushNotificationRequest.setData(data);
		
		// send push notification to device
		this.sendPnsToDevices(pushNotificationRequest);
		log.info("END send notification to user that subcribe RELIEFPOINT ");
	}
	
	@Override
	public void sendPnsToDeviceWhenOpenSOS(User user, String message) {
		SOS sos = user.getUser_sos();
		log.info("START save notification to user that subcribe RELIEFPOINT ");
		List<Device> lstDevice = deviceService.getDeviceTokenByCity(user.getId(),
				sos.getAddress().getSubDistrict().getDistrict().getCity().getId());

		List<String> lstToken = new ArrayList<String>();
		List<User> lstUser = new ArrayList<User>();

		for (Device d : lstDevice) {
			lstUser.add(d.getUser());
			lstToken.add(d.getToken());
		}
		if (lstToken.isEmpty()) {
			return;
		}

		// set notification
		Notification notification = new Notification();
		notification.setTitle(user.getFull_name());
		notification.setMessage(message);
		notification.setSos(sos);
		notification.setType(Constants.NOTIFICATION_TYPE_SOS);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());
		notification.setReceiver(lstUser);
		// save notification
		Notification notificationRes = this.saveNotification(notification);
		log.info("END save notification to user that subcribe RELIEFPOINT ");
		log.info("START sent notification to user that subcribe RELIEFPOINT ");
		// set data push notification
		PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
		pushNotificationRequest.setTarget(lstToken);
		// pushNotificationRequest.setTitle(SOS.getName());
		pushNotificationRequest.setBody(message);
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", String.valueOf(notificationRes.getId()));
		data.put("type", notificationRes.getType());
		// data.put("sender", String.valueOf(rp.getId()));
		pushNotificationRequest.setData(data);

		// send push notification to device
		this.sendPnsToDevices(pushNotificationRequest);
		log.info("END sent notification to user that subcribe RELIEFPOINT ");
	}

	@Override
	public Notification saveNotification(Notification notificaitons) {
		return notificationRepository.saveAndFlush(notificaitons);
	}

	@Override
	public PagingResponse<NotificationDto> getNotificationByUser(Long uId, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by("create_time").descending();
		Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, sort);
		Page<Notification> lstNotification = notificationRepository.getNotifications(uId, pageable);

		PagingResponse<NotificationDto> pagingResonpne = new PagingResponse<NotificationDto>();
		pagingResonpne.setObject(mapStructMapper.lstNotificationToNotificationDto(lstNotification.get()));
		pagingResonpne.setTotalPage(lstNotification.getTotalPages());
		pagingResonpne.setTotalRecord(lstNotification.getTotalElements());
		return pagingResonpne;
	}

	@Override
	public NotificationDto getNotificationById(Long notification_id) {
		// TODO Auto-generated method stub
		Notification notification = notificationRepository.getById(notification_id);
		return mapStructMapper.notificationToNotificationDto(notification);
	}

	@Override
	public void updateStatusNotification(Long notiId, String status) {
		// TODO Auto-generated method stub
		Notification noti = notificationRepository.findById(notiId)
				.orElseThrow(() -> new AppException(403, "Notification not exst"));
		noti.setStatus(status);
		notificationRepository.save(noti);
	}

	@Override
	public void PushNotificationJob(Object object, PushNotificationRequest pushNotificationRequest) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getQuantityUncheckNotification(Long user_id) {
		// TODO Auto-generated method stub
		int quantity = notificationRepository.getQuantityNotificationsByStatus(user_id,
				Constants.NOTIFICATION_STATUS_UNCHECK);
		return quantity;
	}

	@Override
	public void updateStatusCheckAll(Long user_id) {
		// TODO Auto-generated method stub
		notificationRepository.updateUnCheckStatusByUserId(user_id, Constants.NOTIFICATION_STATUS_CHECK);
	}

	@Override
	public void adminSendNotification(AdminPushNotifcationRequest admPsn, User admin) {
		// TODO Auto-generated method stub
		List<Object[]> lstDeviceObj = deviceRepositoryCustom.getDeviceByAreasAndGroup(admPsn.getGroupUsers(),
				admPsn.getSubdistrict_id(), admPsn.getDistrict_id(), admPsn.getCity_id());
		List<Device> lstDevice = procedureMapper.getDevice(lstDeviceObj);

		// set notification
		Notification notification = new Notification();
		notification.setTitle(admPsn.getTitle());
		notification.setMessage(admPsn.getMessage());
		notification.setSender(admin);
		notification.setType(Constants.NOTIFICATION_TYPE_ADMIN);
		notification.setStatus(Constants.NOTIFICATION_STATUS_UNCHECK);
		notification.setCreate_time(DateUtils.getCurrentSqlDate());

		List<String> lstToken = new ArrayList<String>();
		List<User> users = new ArrayList<User>();
		List<PushNotificationRequest> lstPushNotificationRequest = new ArrayList<PushNotificationRequest>();
		for (int i = 0; i < lstDevice.size(); i++) {
			users.add(lstDevice.get(i).getUser());

			// add token to list
			lstToken.add(lstDevice.get(i).getToken());

			int count = i + 1;
			// split batch to send notification 500/1request
			if (count % 10 == 0 || i == lstDevice.size() - 1) {
				PushNotificationRequest pushNotificationRequest = new PushNotificationRequest();
				pushNotificationRequest.setTarget(lstToken);
				lstPushNotificationRequest.add(pushNotificationRequest);
				lstToken = new ArrayList<String>();
			}

		}
		if (lstDevice.isEmpty()) {
			return;
		}
		notification.setReceiver(users);
		// save notification
		Notification notificationRes = this.saveNotification(notification);

		// set data push notification
		lstPushNotificationRequest = lstPushNotificationRequest.stream().map((noti) -> {
			noti.setTitle(admPsn.getTitle());
			noti.setBody(admPsn.getMessage());
			Map<String, String> data = new HashMap<String, String>();
			data.put("id", String.valueOf(notificationRes.getId()));
			data.put("type", notificationRes.getType());
			data.put("sender", String.valueOf(admin.getId()));
			noti.setData(data);
			return noti;
		}).collect(Collectors.toList());

		jobScheduler.enqueue(lstPushNotificationRequest.stream(), (pushnotification) -> {
			sendPnsToDevices(pushnotification);
		});

		// send push notification to device
		// this.sendPnsToDevices(pushNotificationRequest);

	}

}
