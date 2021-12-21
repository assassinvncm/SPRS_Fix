package com.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.controller.UserController;
import com.api.dto.AddressDto;
import com.api.dto.DeviceDto;
import com.api.entity.Address;
import com.api.entity.Device;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.DeviceRepository;
import com.api.service.AddressService;
import com.api.service.DeviceService;
import com.api.service.NotificationService;
import com.exception.AppException;

@Service
public class DeviceServiceImpl implements DeviceService {

	public static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	MapStructMapper mapStructMapper;

	@Autowired
	AddressService addressService;

	@Autowired
	NotificationService notificationService;

	@Transactional
	@Override
	public DeviceDto insertDevice(User user, DeviceDto deviceDto) {
		// TODO Auto-generated method stub
		// if(checkUserLoginAnotherDevice(deviceDto.getUser().getId(),deviceDto.getSerial()))
		// {
		// send notification
		// notificationService.sendPnsToDevice(null);
		// delete device in db by userId

		// }

		deleteDeviceByUserId(user.getId());
		logger.info("Start Delete Device By Serial");
		deviceRepository.deleteBySerial(deviceDto.getSerial());
		logger.info("END Delete Device By Serial");
		// insert db
		Device device = mapStructMapper.deviceDtoToDevice(deviceDto);
		Address address = null;
		if (deviceDto.getAddress() != null) {
			address = addressService.mapAddress(deviceDto.getAddress());
		}
		device.setAddress(address);
		device.setUser(user);
		logger.info("Start Save Device");
		Device responseDevice = deviceRepository.save(device);
		logger.info("END Save Device");
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Transactional
	@Override
	public DeviceDto updateDevice(DeviceDto deviceDto) {
		// TODO Auto-generated method stub

		deviceRepository.findById(deviceDto.getId()).orElseThrow(() -> new AppException(403, "Device not exist"));

		Device device = mapStructMapper.deviceDtoToDevice(deviceDto);
		Address address = addressService.mapAddress(deviceDto.getAddress());
		device.setAddress(address);
		Device responseDevice = deviceRepository.save(device);
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Override
	public DeviceDto updateDeviceAddress(String serial, AddressDto addressDto) {
		// TODO Auto-generated method stub
//		if (addressDto.getId() == 0) {
//			throw new AppException(402, "address id not found");
//		}
		Device d = deviceRepository.findBySerial(serial).orElseThrow(() -> new AppException(403, "User not have device "));
		Address address = addressService.mapAddress(addressDto);
		d.setAddress(address);
		Device  deviceRes= deviceRepository.saveAndFlush(d);
		return mapStructMapper.deviceToDeviceDto(deviceRes);
	}

	@Override
	public DeviceDto updateDeviceToken(Long device_Id, String token) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(device_Id)
				.orElseThrow(() -> new AppException(403, "Device not exist"));
		device.setToken(token);
		Device responseDevice = deviceRepository.save(device);
		return mapStructMapper.deviceToDeviceDto(responseDevice);
	}

	@Override
	public Device getDeviceTokenByUserId(Long uId) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(uId).orElseThrow(() -> new AppException(403, "User Id not exist"));
		return device;
	}

	@Override
	public List<Device> getDeviceTokenByStoreId(Long sId) {
		// TODO Auto-generated method stub
		return deviceRepository.findTokenUserByStore(sId);
	}

	@Override
	public List<Device> getDeviceTokenByCity(Long u_id, Long city_id) {
		// TODO Auto-generated method stub
		return deviceRepository.findTokenUserByCityId(u_id, city_id);
	}

	@Override
	public List<String> getDeviceTokenByDistrict(Long district_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getDeviceTokenBySubDistrict(Long subDistrict_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteDeviceByUserId(Long uId) {
		// TODO Auto-generated method stub
		Device d = deviceRepository.findDeviceByUserId(uId);
		if (d != null) {
			logger.info("Start Delete Device By User Id");
			deviceRepository.delete(d);
			logger.info("END Delete Device By User Id");
		}

	}

	@Override
	public void deleteDeviceByUserIdAndSeri(Long uId, String seriNumber) {
		// TODO Auto-generated method stub
		Device d = deviceRepository.findDeviceByUserIdAndSerial(uId, seriNumber);
		if (d != null) {
			logger.info("Start Delete Device By User Id");
			deviceRepository.delete(d);
			logger.info("END Delete Device By User Id");
		} else {
			logger.info("Device login another device, not delete device");
		}

	}

	@Override
	public void deleteDeviceToken(Long device_id) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findById(device_id).orElseThrow(() -> new AppException(402,"Device not exist"));
		device.setToken(null);
		Device responseDevice = deviceRepository.save(device);
	}

	public boolean checkUserLoginAnotherDevice(Long uId, String serialNum) {
		// TODO Auto-generated method stub
		Device device = deviceRepository.findDeviceByUserId(uId);
		if (device == null) {
			return false;
		}
		if (device.getSerial().equalsIgnoreCase(serialNum)) {
			return false;
		}
		return true;
	}

}
