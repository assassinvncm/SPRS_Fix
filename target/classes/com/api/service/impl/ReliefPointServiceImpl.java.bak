package com.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.dto.AddressDto;
import com.api.dto.ImageDto;
import com.api.dto.NotificationDto;
import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Image;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Request;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.ReliefPointRepository;
import com.api.service.AddressService;
import com.api.service.AmazonClient;
import com.api.service.NotificationService;
import com.api.service.ReliefPointService;
import com.common.utils.DateUtils;
import com.exception.AppException;

@Service
public class ReliefPointServiceImpl implements ReliefPointService {

	@Autowired
	ReliefPointRepository reliefPointRepository;

	@Autowired
	MapStructMapper mapStructMapper;

	@Autowired
	AddressService addressService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	private AmazonClient amazonClient;

	@Override
	public ReliefPointDto getReliefPointById(Long id) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.getById(id);
		ReliefPointDto rpDto = mapStructMapper.reliefPointToreliefPointDto(rp);
		
		//convert dto to entity
		UserDto uDto = new UserDto();
		User u = rp.getUser_rp();
		uDto.setId(u.getId());
		uDto.setAddress(mapStructMapper.addressToAddressDto(u.getAddress()));
		uDto.setPhone(u.getPhone());
		uDto.setFull_name(u.getFull_name());
		
		rpDto.setUser_rp(uDto);
		return rpDto;
	}

	@Override
	public ReliefPoint getReliefPointByUser(User user) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<ReliefPoint> getReliefPointByLocation(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReliefPointDto> getReliefPointByArea(AddressDto addressDto) {
		// TODO Auto-generated method stub
		List<ReliefPoint> rp = reliefPointRepository.findReliefPointByArea(addressDto.getId());

		return null;
	}

	@Override
	public ReliefPoint createReliefPoint(ReliefPointDto reliefPointDto) {
		// TODO Auto-generated method stub
		ReliefPoint reliefPoint = mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto);
		List<ReliefInformation> lstRIfor = reliefPoint.getReliefInformations().stream().map(rf -> {
			rf.setReliefPoint(reliefPoint);
			return rf;
		}).collect(Collectors.toList());
		reliefPoint.setReliefInformations(lstRIfor);
		Address address = addressService.mapAddress(reliefPointDto.getAddress());
		reliefPoint.setAddress(address);
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		reliefPoint.setStatus(true);
		reliefPoint.setCreate_time(DateUtils.getCurrentSqlDate());
		ReliefPoint rp = reliefPointRepository.save(reliefPoint);
		
		
		
//		Request req = createRequestRegister("request to create store", "Create Store", user);
//		userRepository.save(user);
		
		
		notificationService.sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
		return rp;
	}

	@Override
	public ReliefPoint updateReliefPoint(ReliefPointDto reliefPointDto) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.getById(reliefPointDto.getId());
		if (null == rp) {
			throw new AppException(402, "Relief point is not Found!");
		}
		if (reliefPointDto.getAddress().getId() == 0) {
			throw new AppException(402, "Id of Address is not Found!");
		}
		reliefPointDto.getReliefInformations().forEach((rpIf) -> {
			if(rpIf.getItem().getId() == 0) {
				throw new AppException(402, "Id of Item is not Found!");
			}
		});

		//BeanUtils.copyProperties(rp, reliefPoint);
		ReliefPoint reliefPoint = mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto);
		List<ReliefInformation> lstRIfor = reliefPoint.getReliefInformations().stream().map(rf -> {
			rf.setReliefPoint(reliefPoint);
			return rf;
		}).collect(Collectors.toList());
		
		Address address = addressService.mapAddress(reliefPointDto.getAddress());
		reliefPoint.setReliefInformations(lstRIfor);
		reliefPoint.setAddress(address);
		reliefPoint.setStatus(rp.getStatus());
		reliefPoint.setModified_date(DateUtils.getCurrentSqlDate());
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		
//		List<ReliefInformation> lstReliefInfor = reliefPointDto.getReliefInformations().stream().map(reliefInforDto -> {
//			ReliefInformation reliefInfor = new ReliefInformation();
//			reliefInfor.setId(reliefInforDto.getId());
//			reliefInfor.setItem(mapStructMapper.itemDtoToItem(reliefInforDto.getItem()));
//			reliefInfor.setQuantity(reliefInforDto.getQuantity());
//			reliefInfor.setReliefPoint(rp);
//			return reliefInfor;
//		}).collect(Collectors.toList());
//		
//		rp.setName(reliefPointDto.getName());
//		rp.setDescription(reliefPointDto.getDescription());
//		rp.setReliefInformations(lstReliefInfor);
//		rp.setAddress(address);
//		rp.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
//		rp.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
//		rp.setModified_date(DateUtils.getCurrentSqlDate());
		return reliefPointRepository.saveAndFlush(reliefPoint);
	}

	@Override
	public List<ReliefPointDto> getAllReliefPoint() {
		// TODO Auto-generated method stub
		List<ReliefPoint> lstReliefPoint = reliefPointRepository.findAll();
		return mapStructMapper.lstReliefPointToreliefPointDto(lstReliefPoint);
	}

	@Override
	public ReliefPointDto getReliefPointByIdAndUser(Long rpId, Long uId) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.findByIdAndUser(rpId, uId)
				.orElseThrow(() -> new AppException(402, "Reliefpoint not exist"));

		return mapStructMapper.reliefPointToreliefPointDto(rp);
	}

	@Override
	public List<ReliefPointDto> getReliefPoints(Long uId, ReliefPointFilterDto reliefPointFilterDto) {
		// TODO Auto-generated method stub
		List<ReliefPoint> reliefPoints = reliefPointRepository.findByTypeOrStatus(uId, reliefPointFilterDto);
		
		return mapStructMapper.lstReliefPointToreliefPointDto(reliefPoints);
	}
	
//	@Override
//	public PagingResponse<ReliefPointDto> getReliefPoints_v2(Long uId, ReliefPointFilterDto reliefPointFilterDto) {
//		// TODO Auto-generated method stub
//		String  typeSort = null;
//		if(reliefPointFilterDto.getSort() == null || reliefPointFilterDto.getSort()) {
//			typeSort = "desc";
//		}else {
//			typeSort = "asc";
//		}
//
//		Pageable pageable = PageRequest.of(reliefPointFilterDto.getPageIndex() - 1, reliefPointFilterDto.getPageSize());
//		
//		Page<ReliefPoint> reliefPoints = reliefPointRepository.findByTypeOrStatys_v2(uId, reliefPointFilterDto.getTypes(),reliefPointFilterDto.getStatus(),typeSort,pageable);
//		PagingResponse<ReliefPointDto> pagingResonpne = new PagingResponse<ReliefPointDto>();
//		pagingResonpne.setObject(mapStructMapper.lstReliefPointStreamToReliefPointDto(reliefPoints.get()));
//		pagingResonpne.setTotalPage(reliefPoints.getTotalPages());
//		pagingResonpne.setTotalRecord(reliefPoints.getSize());
//		return pagingResonpne;
//	}

	@Override
	@Transactional
	public ReliefPoint updateStatusReliefPoint(Long rId, Boolean status) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.findById(rId)
				.orElseThrow(() -> new AppException(402, "ReliefPoint not exist"));
		rp.setStatus(status);
		return reliefPointRepository.save(rp);
	}

	@Override
	@Transactional
	public void deleteReliefPointById(Long rId) {
		// TODO Auto-generated method stub
		reliefPointRepository.deleteById(rId);
	}

	@Override
	public ReliefPoint uploadReliefImg(ImageDto image) {
		ReliefPoint rp = reliefPointRepository.getById(image.getId());
		if(null == rp) {
			throw new AppException(402,"Relief point is not Found!");
		}
		String img_url = amazonClient.uploadFile(image);
		rp.setImages(new Image(img_url));
//		st.getLstImage().add(new Image(st, img_url));
		
		return reliefPointRepository.save(rp);
	}

}
