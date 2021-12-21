package com.api.mapper;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import com.api.dto.AddressDto;
import com.api.dto.CityDto;
import com.api.dto.DeviceDto;
import com.api.dto.DistrictDto;
import com.api.dto.GroupDto;
import com.api.dto.ItemDto;
import com.api.dto.NotificationDto;
import com.api.dto.OrganizationDto;
import com.api.dto.PermissionChildrenDto;
import com.api.dto.PermissionDto;
import com.api.dto.ReliefInformationDto;
import com.api.dto.ReliefPointDto;
import com.api.dto.RequestDto;
import com.api.dto.SOSDto;
import com.api.dto.StoreCategoryDto;
import com.api.dto.StoreDto;
import com.api.dto.SubDistrictDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.City;
import com.api.entity.Device;
import com.api.entity.District;
import com.api.entity.Group;
import com.api.entity.Image;
import com.api.entity.Item;
import com.api.entity.Notification;
import com.api.entity.Organization;
import com.api.entity.Permission;
import com.api.entity.ReliefInformation;
import com.api.entity.ReliefPoint;
import com.api.entity.Request;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.StoreCategory;
import com.api.entity.SubDistrict;
import com.api.entity.User;
import com.common.utils.DateUtils;
import com.ultils.Constants;

@Component
public class MapStructMapperImpl implements MapStructMapper {

	@Override
	public AddressDto addressToAddressDto(Address address) {
		// TODO Auto-generated method stub
		if (address == null) {
			return null;
		}

		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1(address.getAddressLine());
		addressDto.setId(address.getId());
		addressDto.setGPS_lati(address.getGPS_Lati());
		addressDto.setGPS_long(address.getGPS_Long());
		SubDistrict subdistrictDto = address.getSubDistrict();
		District district = address.getSubDistrict().getDistrict();
		City city = address.getSubDistrict().getDistrict().getCity();
		addressDto.setSubDistrict(new SubDistrictDto(subdistrictDto.getId(), subdistrictDto.getCode(),
				subdistrictDto.getName(), null, null));
		addressDto.setDistrict(new DistrictDto(district.getId(), district.getCode(), district.getName(), null, null));
		addressDto.setCity(new CityDto(city.getId(), city.getCode(), city.getName()));
		return addressDto;
	}

	@Override
	public Address addressDtoToAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub

		if (addressDto == null) {
			return null;
		}

		Address address = new Address();
		address.setAddressLine(addressDto.getAddressLine1());
		address.setId(addressDto.getId());
		address.setGPS_Lati(addressDto.getGPS_lati());
		address.setGPS_Long(addressDto.getGPS_long());

		return address;
	}

	@Override
	public GroupDto groupToGroupDto(Group group) {
		// TODO Auto-generated method stub

		if (group == null) {
			return null;
		}

		GroupDto groupDto = new GroupDto();
		groupDto.setId(group.getId());
		groupDto.setName(group.getName());
		groupDto.setLevel(group.getLevel());

		return groupDto;
	}

	@Override
	public UserDto userToUserDto_forGet(User user) {
		// TODO Auto-generated method stub

		if (user == null) {
			return null;
		}
		UserDto udto = new UserDto();
		udto.setId(user.getId());
		udto.setUsername(user.getUsername());
		udto.setPhone(user.getPhone());
		udto.setAddress(addressToAddressDto(user.getAddress()));
		udto.setFull_name(user.getFull_name());
		udto.setDob(user.getDob());
		udto.setStatus(user.getStatus());
		return udto;
	}

	@Override
	public List<GroupDto> lstGroupToGroupDto(List<Group> lstGroup) {
		// TODO Auto-generated method stub

		if (lstGroup == null) {
			return null;
		}

		List<GroupDto> lstGroupDto = lstGroup.stream().map(group -> {
			return groupToGroupDto(group);
		}).collect(Collectors.toList());

		return lstGroupDto;
	}

	@Override
	public List<UserDto> lstUserToUserDto(List<User> lstUser) {
		// TODO Auto-generated method stub

		if (lstUser == null) {
			return null;
		}

		List<UserDto> lstUserDto = lstUser.stream().map(user -> {
			return userToUserDto_forGet(user);
		}).collect(Collectors.toList());
		
		return lstUserDto;
	}

	@Override
	public OrganizationDto organizationToOrganizationDto(Organization organization) {
		// TODO Auto-generated method stub
		if (organization == null) {
			return null;
		}

		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setId(organization.getId());
		organizationDto.setName(organization.getName());
		organizationDto.setFounded(organization.getFounded());

		return organizationDto;
	}

	@Override
	public Group groupDtoToGroup(GroupDto groupDto) {
		// TODO Auto-generated method stub
		if (groupDto == null) {
			return null;
		}

		Group group = new Group();
		group.setId(groupDto.getId());
		group.setName(group.getName());
		return group;
	}

	@Override
	public Organization organizationDtoToOrganization(OrganizationDto orgDto) {
		// TODO Auto-generated method stub
		if (orgDto == null) {
			return null;
		}
		Organization org = new Organization();
		org.setAddress(addressDtoToAddress(orgDto.getAddress()));
		org.setDescription(orgDto.getDescription());
		org.setFounded(org.getFounded());
		org.setId(orgDto.getId());
		org.setName(orgDto.getName());

		return org;
	}

	@Override
	public UserDto userToUserDto(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return null;
		}

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setPhone(user.getPhone());
		userDto.setUsername(user.getUsername());
		userDto.setFull_name(user.getFull_name());
		userDto.setDob(user.getDob());
		userDto.setCreate_time(null);
		userDto.setPassword(user.getPassword());
		userDto.setIsActive(user.getIsActive());
//		userDto.setImages(user.getImages());

//		if(user.getImages()!=null) {
//			user.getImages().setImg_url(Constants.IMAGE_URL+user.getImages().getImg_url());
//			userDto.setImages(user.getImages());
//		}
		return userDto;
	}

	@Override
	public UserDto userToUserDto_getUController(User user) {
		// TODO Auto-generated method stub
		if (user == null) {
			return null;
		}

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setPhone(user.getPhone());
		userDto.setUsername(user.getUsername());
		userDto.setFull_name(user.getFull_name());
		userDto.setDob(user.getDob());
		userDto.setCreate_time(null);
		userDto.setPassword(user.getPassword());
		userDto.setIsActive(user.getIsActive());
		userDto.setOrganization(organizationToOrganizationDto(user.getOrganization()));
		userDto.setImages(user.getImages());
		userDto.setGroups_user(lstGroupToGroupDto(user.getGroups_user()));
		userDto.setAddress(addressToAddressDto(user.getAddress()));

//		if(user.getImages()!=null) {
//			user.getImages().setImg_url(Constants.IMAGE_URL+user.getImages().getImg_url());
//			userDto.setImages(user.getImages());
//		}
		return userDto;
	}

	@Override
	public User userDtoToUser(UserDto userDto) {
		// TODO Auto-generated method stub
		if (userDto == null) {
			return null;
		}
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setPhone(userDto.getPhone());
		user.setFull_name(userDto.getFull_name());
		user.setDob(userDto.getDob());
		user.setPassword(userDto.getPassword());
		user.setIsActive(userDto.getIsActive());
		user.setAddress(addressDtoToAddress(userDto.getAddress()));
		return user;
	}

	@Override
	public RequestDto requestToRequestDto(Request request) {
		// TODO Auto-generated method stub
		if (request == null) {
			return null;
		}

		RequestDto requestDto = new RequestDto();
		requestDto.setId(request.getId());
		requestDto.setStatus(request.getStatus());
		requestDto.setMessage(request.getMessage());
		requestDto.setTimestamp(DateUtils.convertSqlDateToJavaDate(request.getTimestamp()));
		requestDto.setType(request.getType());
		UserDto userDto = userToUserDto(request.getUser());
		userDto.setGroups_user(lstGroupToGroupDto(request.getUser().getGroups_user()));
		userDto.setAddress(addressToAddressDto(request.getUser().getAddress()));
		userDto.setOrganization(organizationToOrganizationDto(request.getUser().getOrganization()));
		requestDto.setUser(userDto);

		GroupDto groupDto = new GroupDto();
		requestDto.setGroup(groupToGroupDto(request.getGroup()));
		requestDto.setOrganization(organizationToOrganizationDto(request.getOrganization()));
		return requestDto;
	}

	@Override
	public List<RequestDto> lstRequestToRequestDto(List<Request> lstRequests) {
		// TODO Auto-generated method stub
		if (lstRequests == null) {
			return null;
		}
		List<RequestDto> lstRequestDto = lstRequests.stream().map(request -> {
			return requestToRequestDto(request);
		}).collect(Collectors.toList());

		return lstRequestDto;
	}

	@Override
	public ItemDto itemToItemDto(Item item) {
		// TODO Auto-generated method stub
		if (item == null) {
			return null;
		}
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setName(item.getName());
		itemDto.setUnit(item.getUnit());
		itemDto.setDescription(item.getDescription());
		return itemDto;
	}

	@Override
	public StoreCategoryDto cateToCateDto(StoreCategory cate) {
		// TODO Auto-generated method stub
		if (cate == null) {
			return null;
		}
		StoreCategoryDto cateDto = new StoreCategoryDto();
		cateDto.setId(cate.getId());
		cateDto.setName(cate.getName());
		return cateDto;
	}

	@Override
	public StoreCategory cateDtoToCate(StoreCategoryDto cateDto) {
		// TODO Auto-generated method stub
		if (cateDto == null) {
			return null;
		}
		StoreCategory cate = new StoreCategory();
		cate.setId(cateDto.getId());
		cate.setName(cateDto.getName());
		return cate;
	}

	@Override
	public Item itemDtoToItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		if (itemDto == null) {
			return null;
		}
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		item.setUnit(itemDto.getUnit());
		item.setDescription(itemDto.getDescription());
		return item;
	}

	@Override
	public List<ItemDto> lstitemToItemDto(List<Item> lstItem) {
		// TODO Auto-generated method stub
		if (lstItem == null) {
			return null;
		}

		List<ItemDto> lstItemDto = lstItem.stream().map(item -> {
			return itemToItemDto(item);
		}).collect(Collectors.toList());
		return lstItemDto;
	}

	@Override
	public List<StoreCategoryDto> lstStoreCateToStoreCateDto(List<StoreCategory> lstCate) {
		// TODO Auto-generated method stub
		if (lstCate == null) {
			return null;
		}

		List<StoreCategoryDto> lstCateDto = lstCate.stream().map(cate -> {
			return cateToCateDto(cate);
		}).collect(Collectors.toList());
		return lstCateDto;
	}

	@Override
	public List<Item> lstItemDtoToItem(List<ItemDto> lstItemDto) {
		// TODO Auto-generated method stub
		if (lstItemDto == null) {
			return null;
		}

		List<Item> lstItem = lstItemDto.stream().map(itemDto -> {
			return itemDtoToItem(itemDto);
		}).collect(Collectors.toList());
		return lstItem;
	}

	@Override
	public ReliefPointDto reliefPointToreliefPointDto(ReliefPoint reliefPoint) {
		// TODO Auto-generated method stub
		if (reliefPoint == null) {
			return null;
		}
		ReliefPointDto reliefPointDto = new ReliefPointDto();
		reliefPointDto.setId(reliefPoint.getId());
		reliefPointDto.setName(reliefPoint.getName());
		// reliefPointDto.setClose_time(reliefPoint.getClose_time());
		reliefPointDto.setDescription(reliefPoint.getDescription());
		reliefPointDto.setAddress(addressToAddressDto(reliefPoint.getAddress()));
		reliefPointDto.setStatus(reliefPoint.getStatus());
		Date create_time = DateUtils.convertSqlDateToJavaDate(reliefPoint.getCreate_time());
		Date modified_date = DateUtils.convertSqlDateToJavaDate(reliefPoint.getModified_date());
		Date open_time = DateUtils.convertSqlDateToJavaDate(reliefPoint.getOpen_time());
		Date close_time = DateUtils.convertSqlDateToJavaDate(reliefPoint.getClose_time());
		reliefPointDto.setCreate_time(DateUtils.getDateInyyyy_MM_ddHHmmss(create_time));
		reliefPointDto.setModified_date(DateUtils.getDateInyyyy_MM_ddHHmmss(modified_date));
		reliefPointDto.setOpen_time(open_time);
		reliefPointDto.setClose_time(close_time);

		List<ReliefInformationDto> rpDto = reliefPoint.getReliefInformations().stream().map(rpInfor -> {
			return reliefInforToReliefInforDto(rpInfor);
		}).collect(Collectors.toList());

		List<UserDto> lstURP = reliefPoint.getRelief_user().stream().map(
			u -> {return userToUserDto(u);}
		).collect(Collectors.toList());
		reliefPointDto.setLstUser_rp(lstURP);
		reliefPointDto.setReliefInformations(rpDto);
		reliefPointDto.setImages(reliefPoint.getImages());

//		if(reliefPoint.getImages()!=null) {
//			reliefPoint.getImages().setImg_url(Constants.IMAGE_URL+reliefPoint.getImages().getImg_url());
//			reliefPointDto.setImages(reliefPoint.getImages());
//		}
//		reliefPointDto.setUser_rp(userToUserDto(reliefPoint.getUser_rp()));

		return reliefPointDto;
	}

	@Override
	public StoreDto storeToStoreDTO(Store store) {
		// TODO Auto-generated method stub
		if (store == null) {
			return null;
		}
		StoreDto storeDto = new StoreDto();
		storeDto.setId(store.getId());
		storeDto.setName(store.getName());
		storeDto.setStatus(store.getStatus());
		storeDto.setClose_time(store.getClose_time().toString().substring(0, 5));
		storeDto.setOpen_time(store.getOpen_time().toString().substring(0, 5));
		storeDto.setDescription(store.getDescription());
		storeDto.setAddress(addressToAddressDto(store.getLocation()));
		storeDto.setStoreDetail(lstStoreCateToStoreCateDto(store.getStore_category()));
		storeDto.setUser_st(userToUserDto(store.getUsers()));
//		if(store.getImages()!=null) {
//			store.getImages().setImg_url(Constants.IMAGE_URL+store.getImages().getImg_url());
//			storeDto.setImages(store.getImages());
//		}
		storeDto.setImages(store.getImages());
		return storeDto;
	}

	@Override
	public ReliefPoint reliefPointDtoToreliefPoint(ReliefPointDto reliefPointDto) {
		// TODO Auto-generated method stub

		if (reliefPointDto == null) {
			return null;
		}
		ReliefPoint reliefPoint = new ReliefPoint();
		reliefPoint.setId(reliefPointDto.getId());
		reliefPoint.setName(reliefPointDto.getName());
		// reliefPoint.setClose_time(reliefPointDto.getClose_time());
		reliefPoint.setDescription(reliefPointDto.getDescription());
		reliefPoint.setAddress(addressDtoToAddress(reliefPointDto.getAddress()));
		List<ReliefInformationDto> lstReliefInforDto = reliefPointDto.getReliefInformations();
		List<ReliefInformation> lstReliefInfor = lstReliefInforDto.stream().map(reliefInforDto -> {
			ReliefInformation reliefInfor = new ReliefInformation();
			reliefInfor.setId(reliefInforDto.getId());
			reliefInfor.setItem(itemDtoToItem(reliefInforDto.getItem()));
			reliefInfor.setQuantity(reliefInforDto.getQuantity());
			return reliefInfor;
		}).collect(Collectors.toList());
		reliefPoint.setReliefInformations(lstReliefInfor);
		reliefPoint.setUser_rp(userDtoToUser(reliefPointDto.getUser_rp()));

		return reliefPoint;
	}

	@Override
	public List<Group> lstGroupDtoToGroup(List<GroupDto> lstGroupDto) {
		// TODO Auto-generated method stub
		if (lstGroupDto == null) {
			return null;
		}
		List<Group> lstGroup = lstGroupDto.stream().map(groupDto -> {
			return groupDtoToGroup(groupDto);
		}).collect(Collectors.toList());

		return lstGroup;
	}

	@Override
	public List<ReliefPointDto> lstReliefPointToreliefPointDto(List<ReliefPoint> lstReliefPoint) {
		// TODO Auto-generated method stub
		if (lstReliefPoint == null) {
			return null;
		}
		List<ReliefPointDto> lstRp = lstReliefPoint.stream().map(reliefPoint -> {
			return reliefPointToreliefPointDto(reliefPoint);
		}).collect(Collectors.toList());
		return lstRp;
	}

	@Override
	public ReliefInformationDto reliefInforToReliefInforDto(ReliefInformation reliefInfor) {
		// TODO Auto-generated method stub
		if (reliefInfor == null) {
			return null;
		}

		ReliefInformationDto reliefInforDto = new ReliefInformationDto();
		ItemDto itemDto = new ItemDto();
		itemDto.setId(reliefInfor.getItem().getId());
		itemDto.setName(reliefInfor.getItem().getName());
		itemDto.setUnit(reliefInfor.getItem().getUnit());
		itemDto.setDescription(reliefInfor.getItem().getDescription());

		reliefInforDto.setId(reliefInfor.getId());
		reliefInforDto.setItem(itemDto);
		reliefInforDto.setQuantity(reliefInfor.getQuantity());

		return reliefInforDto;
	}

	@Override
	public ReliefInformation reliefInforDtoToReliefInfor(ReliefInformationDto reliefInforDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StoreDto> lstStoreToStoreDto(List<Store> lststore) {
		// TODO Auto-generated method stub
		if (lststore == null) {
			return null;
		}
		List<StoreDto> lstSt = lststore.stream().map(store -> {
			return storeToStoreDTO(store);
		}).collect(Collectors.toList());
		return lstSt;
	}

	@Override
	public Store storeDtoToStore(StoreDto dto) {
		Store rs = new Store();
		if (dto == null) {
			return null;
		}
		rs.setId(dto.getId());
		rs.setName(dto.getName());
		rs.setOpen_time(DateUtils.stringToTimeHHMM(dto.getOpen_time()));
		rs.setClose_time(DateUtils.stringToTimeHHMM(dto.getClose_time()));
		rs.setDescription(dto.getDescription());
		rs.setLocation(addressDtoToAddress(dto.getAddress()));
		List<StoreCategoryDto> lstStoreDetailDto = dto.getStoreDetail();
		List<StoreCategory> lstStoreDetail = lstStoreDetailDto.stream().map(storeDetailDto -> {
			StoreCategory storeDetail = new StoreCategory();
			storeDetail.setId(storeDetailDto.getId());
			storeDetail.setName(storeDetailDto.getName());
			return storeDetail;
		}).collect(Collectors.toList());
		rs.setStore_category(lstStoreDetail);
		rs.setUsers(userDtoToUser(dto.getUser_st()));
//		List<Image> lstImgDto = dto.getLstImage();
//		List<Image> lstImg = lstImgDto.stream().map(imgDto -> {
//			Image img = new Image();
//			imgDto.setId(imgDto.getId());
//			return img;
//		}).collect(Collectors.toList());
//		rs.setLstImage(lstImg);

		return rs;
	}

	@Override
	public List<StoreCategory> lstStoreCateDtoToStoreCate(List<StoreCategoryDto> lstCateDto) {
		if (lstCateDto == null) {
			return null;
		}

		List<StoreCategory> lstCate = lstCateDto.stream().map(cate -> {
			return cateDtoToCate(cate);
		}).collect(Collectors.toList());
		return lstCate;
	}

	@Override
	public DeviceDto deviceToDeviceDto(Device device) {
		// TODO Auto-generated method stub
		if (device == null) {
			return null;
		}

		DeviceDto deviceDto = new DeviceDto();
		deviceDto.setId(device.getId());
		deviceDto.setToken(device.getToken());
		deviceDto.setSerial(device.getSerial());
		deviceDto.setAddress(addressToAddressDto(device.getAddress()));
		deviceDto.setUser(null);

		return deviceDto;
	}

	@Override
	public Device deviceDtoToDevice(DeviceDto deviceDto) {
		// TODO Auto-generated method stub
		if (deviceDto == null) {
			return null;
		}

		Device device = new Device();
		device.setId(deviceDto.getId());
		device.setToken(deviceDto.getToken());
		device.setSerial(deviceDto.getSerial());
		device.setUser(userDtoToUser(deviceDto.getUser()));
		return device;
	}

	public SOSDto SOSToSOSDto(SOS sos) {
		SOSDto rs = new SOSDto();
		rs.setId(sos.getId());
		rs.setDescription(sos.getDescription());
		rs.setAddress(addressToAddressDto(sos.getAddress()));
		rs.setLevel(sos.getLevel());
		rs.setStatus(sos.getStatus());
		return rs;
	}

	@Override
	public SOS SOSDtoToSOS(SOSDto sosDto) {
		SOS rs = new SOS();
		rs.setId(sosDto.getId());
		rs.setDescription(sosDto.getDescription());
		rs.setAddress(addressDtoToAddress(sosDto.getAddress()));
		rs.setLevel(sosDto.getLevel());
		rs.setStatus(sosDto.getStatus());
		return rs;
	}

	@Override
	public NotificationDto notificationToNotificationDto(Notification notification) {
		// TODO Auto-generated method stub
		if(notification == null) {
			return null;
		}
		NotificationDto notiDto = new NotificationDto();
		notiDto.setId(notification.getId());
		notiDto.setMessage(notification.getMessage());
		notiDto.setStatus(notification.getStatus());
		notiDto.setType(notification.getType());
		Date creatimeDate = DateUtils.convertSqlDateToJavaDate(notification.getCreate_time());
		notiDto.setCreate_time(creatimeDate);
		
		if(notiDto.getType().equalsIgnoreCase(Constants.NOTIFICATION_TYPE_STORE)) {
			notiDto.setSender(storeToStoreDTO(notification.getStore()));
		}else if(notiDto.getType().equalsIgnoreCase(Constants.NOTIFICATION_TYPE_RELIEFPOINT)) {
			notiDto.setSender(reliefPointToreliefPointDto(notification.getReliefPoint()));
		}else {
			notiDto.setSender(userToUserDto(notification.getSender()));
		}

		return notiDto;
	}

	@Override
	public List<NotificationDto> lstNotificationToNotificationDto(Stream<Notification> lstNotificaiton) {
		// TODO Auto-generated method stub
		if(lstNotificaiton == null) {
			return null;
		}
		List<NotificationDto> lstNotiDto = new ArrayList<NotificationDto>();
		
		lstNotiDto = lstNotificaiton.map((noti)->{
			return  notificationToNotificationDto(noti);
		}).collect(Collectors.toList());
		
		return lstNotiDto;
	}

	@Override
	public PermissionDto permisisonToPermisionDto(Permission per) {
		if (per == null) {
			return null;
		}

		PermissionDto perDto = new PermissionDto();
		perDto.setId(per.getId());
		perDto.setName(per.getName());
		perDto.setCode(per.getCode());
		perDto.setTo(per.getTo_page());
		perDto.setIcon(per.getIcon_name());
		return perDto;
	}

	@Override
	public List<PermissionDto> lstPermissionToPermissionDto(List<Permission> lstPermission) {
		if (lstPermission == null) {
			return null;
		}

		List<PermissionDto> lstPermissionDto = lstPermission.stream().map(permission -> {
			return permisisonToPermisionDto(permission);
		}).collect(Collectors.toList());

		return lstPermissionDto;
	}
	
	

	@Override
	public List<PermissionDto> lstPermissionToLstGrantAccess(List<Permission> per) {
		int lenght = getLenghtTree(per);
		List<PermissionDto> lstRs = new ArrayList<PermissionDto>();
		List<Permission> lstRoot = new ArrayList<Permission>();
		List<Permission> lstLeaf = new ArrayList<Permission>();
		for (Permission permission : per) {
			if(permission.getNode_index() == 1) {
				lstRoot.add(permission);
				lstRs.add(permisisonToPermisionDto(permission));
			}
		}
		for (Permission permission : per) {
			if(permission.getNode_index() == 2) {
				lstLeaf.add(permission);
			}
		}
		for(int i = 0; i<lstRoot.size();i++) {
			for (Permission permission2 : lstLeaf) {
				if(lstRoot.get(i).getNode_to() == permission2.getNode_from()) {
					lstRs.get(i).getChildren().add(new PermissionChildrenDto(permission2.getName(),permission2.getTo_page(),permission2.getIcon_name()));
				}
			}
		}
//		for (Permission permission : lstRoot) {
//		}
//		for(int i = lenght; i> 0;i--) {
//			List<Permission> lstRoot = new ArrayList<Permission>();
//			for (Permission permission : per) {
//				if(permission.getNode_from() == i) {
//					lstRoot.add(permission);
//				}
//			}
//			lstRs = lstRoot;
//			for (Permission permission : lstRoot) {
//				List<Permission> lstLeaf = new ArrayList<Permission>();
//				for (Permission permission2 : per) {
//					if(permission.getNode_to() == 0) {
//						if(permission.getNode_to() == permission2.getNode_from()) {
//							lstLeaf.add(permission2);
//							permission.setChildren(lstLeaf);
//						}
//					}
//				}
//			}
//			if(i == lenght) {
//				lstRs = lstRoot;
//			}
//		}
		return lstRs;
	}
	
	private int getLenghtTree(List<Permission> per) {
		int lenght = 0;
		for (Permission permission : per) {
			if(permission.getNode_index() > lenght) {
				lenght = permission.getNode_index();
			}
		}
		return lenght;
	}

	@Override
	public List<ReliefPointDto> lstReliefPointStreamToReliefPointDto(Stream<ReliefPoint> lstReliefPoint) {
		// TODO Auto-generated method stub
		if(lstReliefPoint == null) {
			return null;
		}
		List<ReliefPointDto> lstReliefPointDto = new ArrayList<ReliefPointDto>();
		
		lstReliefPointDto = lstReliefPoint.map((rp)->{
			return  reliefPointToreliefPointDto(rp);
		}).collect(Collectors.toList());
		return lstReliefPointDto;
	}

	@Override
	public List<UserDto> lstBanUserToBanUserDto(List<User> lstUser) {
		// TODO Auto-generated method stub
		if(lstUser == null) {
			return null;
		}
		List<UserDto>  lstUserDto = new ArrayList<UserDto>();
		for(User user : lstUser) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setPhone(user.getPhone());
			userDto.setUsername(user.getUsername());
			userDto.setFull_name(user.getFull_name());
			userDto.setDob(user.getDob());
			userDto.setCreate_time(null);
			userDto.setPassword(user.getPassword());
			userDto.setIsActive(user.getIsActive());
			userDto.setStatus(user.getStatus());
			userDto.setAddress(addressToAddressDto(user.getAddress()));
			lstUserDto.add(userDto);
		}
		
		return lstUserDto;
	}
	
//	private List<PermissionDto> addChildrenToParent(Permission p, List<Permission> pl){
//		List<PermissionDto> lstPerDto = new ArrayList<PermissionDto>();
//		for (Permission permission : pl) {
//			if(p.getNode_from().equals(permission.getNode_to())) {
//				
//			}
//		}
//		return lstPerDto;
//	}
//	
//	private PermissionDto perToPerDto(Permission p) {
//		PermissionDto pRs = new PermissionDto();
//		pRs.setName(p.getName());
//		pRs.setIcon(p.getIcon_name());
//		pRs.setTo(p.getTo_page());
//		return pRs;
//	}
}
