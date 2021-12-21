package com.api.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.api.dto.GrantAccessDto;
import com.api.dto.ImageDto;
import com.api.dto.NotificationDto;
import com.api.dto.PagingResponse;
import com.api.dto.ReliefPointDto;
import com.api.dto.ReliefPointFilterDto;
import com.api.dto.SearchFilterDto;
import com.api.dto.StoreDto;
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
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.AmazonClient;
import com.api.service.NotificationService;
import com.api.service.ReliefPointService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import com.exception.ProcException;
import com.ultils.Constants;

@Service
public class ReliefPointServiceImpl implements ReliefPointService {

	@Autowired
	ReliefPointRepository reliefPointRepository;

	@Autowired
	MapStructMapper mapStructMapper;

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserRepository userRepo;
	
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
	public ReliefPointDto getReliefPointByIdORG(Long id) {
		// TODO Auto-generated method stub
		ReliefPoint rp = reliefPointRepository.getById(id);
		ReliefPointDto rpDto = mapStructMapper.reliefPointToreliefPointDto(rp);
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
	
	
	public boolean checkCreateRp(User user) {
		boolean rs = true;
		List<Group> lstGroup = user.getGroups_user();
		for(Group g: lstGroup) {
			if(g.getCode().equals(Constants.USER_PER_CODE)) {
				Timestamp sqlCurrentDate = DateUtils.getCurrentSqlDate();
				long total = reliefPointRepository.getTotalRpByTime(user.getId(), sqlCurrentDate);
				if(total == 2) {
					//throw new AppException(403,"Bạn chỉ được phép tạo tối đa 2 điểm cứu trợ");
					rs = false;
				}
			}
		}
		
		
		return rs;
	}

	@Override
	public ReliefPoint createReliefPointAdmin(ReliefPointDto reliefPointDto, User user) {
		// TODO Auto-generated method stub
		ReliefPoint reliefPoint = mapStructMapper.reliefPointDtoToreliefPoint(reliefPointDto);
		List<ReliefInformation> lstRIfor = reliefPoint.getReliefInformations().stream().map(rf -> {
			rf.setReliefPoint(reliefPoint);
			return rf;
		}).collect(Collectors.toList());
//		if(DateUtils.isDatePast((Date) reliefPointDto.getOpen_time(), "yyyy-MM-dd HH:mm")) {
//			throw new AppException(403,"Không được tạo sự kiện trong quá khứ");
//		}
		reliefPoint.setReliefInformations(lstRIfor);
		Address address = addressService.mapAddress(reliefPointDto.getAddress());
		reliefPoint.setAddress(address);
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		reliefPoint.setStatus(1);
		reliefPoint.setCreate_time(DateUtils.getCurrentSqlDate());
		reliefPoint.setOrganization(user.getOrganization());
		reliefPoint.setUsers(user);
		ReliefPoint rp = reliefPointRepository.save(reliefPoint);
		
//		notificationService.sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
		return rp;
	}

	@Override
	public ReliefPoint createReliefPoint(ReliefPointDto reliefPointDto, User user) {
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
		reliefPoint.setStatus(1);
		reliefPoint.setCreate_time(DateUtils.getCurrentSqlDate());
		reliefPoint.setUser_rp(user);
		ReliefPoint rp = reliefPointRepository.save(reliefPoint);
		
		
		
//		Request req = createRequestRegister("request to create store", "Create Store", user);
//		userRepository.save(user);
		
		
		notificationService.sendPnsToDeviceWhenCreateReliefPoint(rp,"Có một địa điểm cứu trợ được tạo gần bạn");
		return rp;
	}

	@Override
	public ReliefPoint updateReliefPointAdmin(ReliefPointDto reliefPointDto) {
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
		reliefPoint.setModified_date(DateUtils.getCurrentSqlDate());
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		reliefPoint.setDescription(reliefPointDto.getDescription());
		reliefPoint.setName(reliefPointDto.getName());
		reliefPoint.setStatus(rp.getStatus());
		reliefPoint.setImages(rp.getImages());
		reliefPoint.setUsers(rp.getUsers());
		reliefPoint.setOrganization(rp.getOrganization());
		reliefPoint.setRelief_user(rp.getRelief_user());
		
		return reliefPointRepository.saveAndFlush(reliefPoint);
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
		reliefPoint.setModified_date(DateUtils.getCurrentSqlDate());
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		reliefPoint.setDescription(reliefPointDto.getDescription());
		reliefPoint.setName(reliefPointDto.getName());
		reliefPoint.setStatus(rp.getStatus());
		reliefPoint.setImages(rp.getImages());
		reliefPoint.setUsers(rp.getUsers());
		reliefPoint.setOrganization(rp.getOrganization());
		//reliefPoint.setCreate_by();
		
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
	public ReliefPoint updateReliefPointORG(ReliefPointDto reliefPointDto) {
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
		reliefPoint.setModified_date(DateUtils.getCurrentSqlDate());
		reliefPoint.setOpen_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getOpen_time()));
		reliefPoint.setClose_time(DateUtils.convertJavaDateToSqlDate(reliefPointDto.getClose_time()));
		reliefPoint.setDescription(reliefPointDto.getDescription());
		reliefPoint.setName(reliefPointDto.getName());
		reliefPoint.setStatus(rp.getStatus());
		reliefPoint.setImages(rp.getImages());
		reliefPoint.setUsers(rp.getUsers());
		reliefPoint.setOrganization(rp.getOrganization());
		reliefPoint.setRelief_user(rp.getRelief_user());
		return reliefPointRepository.save(reliefPoint);
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

	@Override
	public List<ReliefPointDto> getEvent(Long uId,ReliefPointFilterDto reliefPointFilterDto) {
		// TODO Auto-generated method stub
		List<ReliefPoint> reliefPoints = reliefPointRepository.findByTypeOrStatusEv(uId, reliefPointFilterDto);
		return mapStructMapper.lstReliefPointToreliefPointDto(reliefPoints);
	}

	@Override
	public Map<String, Object> getReliefPointsAdmin(Long oId, SearchFilterDto filter) {
		// TODO Auto-generated method stub
		List<ReliefPointDto> lstRs = new ArrayList<ReliefPointDto>();
		Sort sortable = null;
	    if (filter.getSort()) {
	    	sortable = Sort.by("name").descending();
	    }else {
	    	sortable = Sort.by("name").ascending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize(), sortable);
		Page<ReliefPoint> pageStore = reliefPointRepository.getOwnOrgReliefPoint(oId, filter.getStatus_store(), filter.getSearch(), pageable);
		lstRs = mapStructMapper.lstReliefPointToreliefPointDto(pageStore.getContent());
	    Map<String, Object> response = new HashMap<>();
        response.put("reliefs", lstRs);
        response.put("currentPage", pageStore.getNumber());
        response.put("totalItems", pageStore.getTotalElements());
        response.put("totalPages", pageStore.getTotalPages());
		return response;
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
	public ReliefPoint updateStatusReliefPoint(Long rId, int status) {
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
	@Transactional
	public void deleteReliefPointEvent(Long rId) {
		// TODO Auto-generated method stub
		reliefPointRepository.deleteEvent(rId);
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

	@Override
	public GrantAccessDto assignRef(GrantAccessDto gdto) {
		String native_rs = reliefPointRepository.assignRef(gdto.getSource_id(), gdto.getTarget_id());
		ProcException pErr = new ProcException(native_rs);
		String status = pErr.getStatus();
		switch (status) {
		case "FAIL":
			throw new AppException(402,pErr.getErr_message());
		default:
			break;
		}
		return gdto;
	}

	@Override
	public GrantAccessDto unAssignRef(GrantAccessDto gdto) {
		String native_rs = reliefPointRepository.unAssignRef(gdto.getSource_id(), gdto.getTarget_id());
		ProcException pErr = new ProcException(native_rs);
		String status = pErr.getStatus();
		switch (status) {
		case "FAIL":
			throw new AppException(402,pErr.getErr_message());
		default:
			break;
		}
		return gdto;
	}

	@Override
	public List<User> getAllAssignUser(Long rp_id, String search) {
		Optional<ReliefPoint> rp = reliefPointRepository.findById(rp_id);
		if (rp.isEmpty()) {
			throw new AppException(403, "Relief point is not existed!");
		}
		List<User> lsRs = new ArrayList<User>();
		List<User> lsTemp = rp.get().getRelief_user();
		if(search != "") {
			lsTemp.forEach(u -> {
				if(u.getUsername().toLowerCase().contains(search.toLowerCase())) {
					lsRs.add(u);
				}
			});
			return lsRs;
		}else {
			return lsTemp;
		}
	}

	@Override
	public List<User> getAllUnassignUser(Long rp_id, String search) {
		List<User> lstRs = new ArrayList<User>();
		List<User> lsTemp = new ArrayList<User>();
		ReliefPoint rp = reliefPointRepository.getById(rp_id);
		if (rp == null) {
			throw new AppException(403, "Relief point is not existed!");
		}
		List<User> lstAssign = rp.getRelief_user();
		List<User> lstAll = userRepo.getUserInOrg(rp.getOrganization().getId());
		for (User u : lstAll) {
			int check = 1;
			for (User u2 : lstAssign) {
				if(u2.getId() == u.getId()) {
					check = 0;
				}
			}
			if(check == 1) {
				lsTemp.add(u);
			}
		}
		if(search != "") {
			lsTemp.forEach(u -> {
				if(u.getUsername().toLowerCase().contains(search.toLowerCase())) {
					lstRs.add(u);
				}
			});
			return lstRs;
		}else {
			return lsTemp;
		}
	}

}
