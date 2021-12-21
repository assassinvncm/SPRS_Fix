package com.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import javax.persistence.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.controller.UserController;
import com.api.dto.GrantAccessDto;
import com.api.dto.GroupDto;
import com.api.dto.ImageDto;
import com.api.dto.PagingResponse;
import com.api.dto.SPRSResponse;
import com.api.dto.SearchFilterDto;
import com.api.dto.StoreDto;
import com.api.dto.SubcribeDto;
import com.api.dto.UpdatePasswordDto;
import com.api.dto.UserDto;
import com.api.entity.Address;
import com.api.entity.Group;
import com.api.entity.Image;
import com.api.entity.Organization;
import com.api.entity.Request;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.entity.User;
import com.api.mapper.MapStructMapper;
import com.api.repositories.GroupRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.RequestRepository;
import com.api.repositories.StoreRepository;
import com.api.repositories.UserRepository;
import com.api.service.AddressService;
import com.api.service.AmazonClient;
import com.api.service.SOSService;
import com.api.service.UserService;
import com.common.utils.DateUtils;
import com.exception.AppException;
import com.exception.ProcException;
import com.jwt.config.JwtTokenUtil;
import org.springframework.data.domain.Sort;
import com.ultils.Constants;
import com.ultils.Ultilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class UserSerivceImpl implements UserService {

	public static Logger logger = LoggerFactory.getLogger(UserSerivceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StoreRepository storeRepo;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	RequestRepository requestRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	AddressService addressService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	MapStructMapper mapStructMapper;
	
	@Autowired
	SOSService sosServ;
	
	@Autowired
	private AmazonClient amazonClient;

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		logger.info("Start get all User");
		List<User> lst = null;
		try {
			lst = userRepository.findAll();
		} catch (Exception e) {
			logger.info("Error get all User: " + e.getMessage());
			throw new AppException(501, "Error when get user");
		}
		logger.info("End get all User");
		return lst;
	}

	@Override
	public UserDto getUserbyToken(String requestTokenHeader) {
		// TODO Auto-generated method stub
		logger.info("Start get User");

		String username = jwtTokenUtil.getUserNameByToken(requestTokenHeader);
		
		User user = Optional.ofNullable(userRepository.findByUsername(username))
				.orElseThrow(() -> new AppException(501, "Error when query to get user"));
		logger.info("End get User");
		
		//mapper
		UserDto userDto= mapStructMapper.userToUserDto(user);
		userDto.setGroups_user(mapStructMapper.lstGroupToGroupDto(user.getGroups_user()));
		userDto.setAddress(mapStructMapper.addressToAddressDto(user.getAddress()));
		userDto.setOrganization(mapStructMapper.organizationToOrganizationDto(user.getOrganization()));
		userDto.setPassword(user.getPassword());

		userDto.setImages(user.getImages());
//		if(user.getImages()!=null) {
//			user.getImages().setImg_url(user.getImages().getImg_url());
//			userDto.setImages(user.getImages());
//		}
		//userDto.setRequest();
		return userDto;
	}

	@Override
	public User getNativeUserbyToken(String requestTokenHeader) {
		// TODO Auto-generated method stub
		logger.info("Start get native User");

		String username = jwtTokenUtil.getUserNameByToken(requestTokenHeader);
		
		User user = Optional.ofNullable(userRepository.findByUsername(username))
				.orElseThrow(() -> new AppException(501, "Error when query to get user"));
		
		User uRs = userRepository.getById(user.getId());
		
		logger.info("End get native User");
		return uRs;
	}
	
	@Override
	public User getUserbyTokenAuth(String requestTokenHeader) {
		// TODO Auto-generated method stub
		logger.info("Start get User");

		String username = jwtTokenUtil.getUserNameByToken(requestTokenHeader);
		
		User user = Optional.ofNullable(userRepository.findByUsername(username))
				.orElseThrow(() -> new AppException(501, "Error when query to get user"));
		logger.info("End get User");
		
		return user;
	}


	@Transactional
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(user.getUsername());
		boolean checkGr = true;
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(user.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}

		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
			if (group.getLevel() == 0) {
				checkGr = false;
			}
		}

		user.setIsActive(checkGr);
//		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		if (checkRqUser(user)) {
			//createRequestAccept("Account accepting", "Account accepting", user);
		}
		logger.info("End save User");
		return user;
	}

	@Transactional
	@Override
	public void registerUser_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save User");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}
		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		// User u = userRepository.getByPhone(user.getPhone());

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);
		user.setIsActive(true);
		user.setStatus(Constants.USER_STATUS_ACTIVE);
//		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUser_sos(new SOS(1, address,1));
		userRepository.save(user);
//		sosServ.createSOS(user);
		logger.info("End save User");
		// return user;
	}

	@Transactional
	@Override
	public void registerOrganization_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save Organization");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}
		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		Address addressOrg = addressService.mapAddress(userDto.getOrganization().getAddress());
		user.setAddress(address);
		user.getOrganization().setAddress(addressOrg);
		user.getOrganization().setCreate_time(DateUtils.getCurrentSqlDate());
		//user.setIsActive(false);
		user.setStatus(Constants.USER_STATUS_UNACTIVE);
//		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// user.setGroups_user(lstTem);G
		Request req = createRequestRegister("Request to register", null, user);

		userRepository.save(user);
		logger.info("End save Organization");
		logger.info("Start save Request");
		requestRepository.save(req);
		logger.info("End save Request");
		// return user;
	}

	@Transactional
	@Override
	public void registerOrganizationUser_v2(UserDto userDto, User admin) {
		logger.info("Start save Organizational User");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
//		List<GroupDto> lstTem = userDto.getGroups_user();
//		for (GroupDto groupDto : lstTem) {
//			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
//			if (grTemp.isEmpty()) {
//				throw new AppException(403, "Group is not exist!");
//			}
//		}

		Organization organization = organizationRepository.findById(admin.getOrganization().getId())
				.orElseThrow(() -> new AppException(403, "organization is not exist!"));
		// chưa check admin organization phải tồn tại

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		Address addressOrg = addressService.mapAddress(mapStructMapper.addressToAddressDto(address));
		user.setAddress(address);
//		user.getOrganization().setAddress(addressOrg);
		user.setOrganization(organization);

		user.setIsActive(true);
		user.setStatus(Constants.USER_STATUS_ACTIVE);
		user.setCreate_time(DateUtils.getCurrentSqlDate());
		//user.create_by(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Group g = groupRepository.findByCode(Constants.ORG_USER_PER_CODE);
		List<Group> user_gr = new ArrayList<Group>();
		user_gr.add(g);
		user.setGroups_user(user_gr);
		userRepository.save(user);
		logger.info("End save Organization user");
//		logger.info("Start save request");
//		Request req = createRequestRegister("request to register", null, user);
//		requestRepository.save(req);
//		logger.info("End save Request");
		// return user;
	}

	@Override
	public void registerStoreUser_v2(UserDto userDto) {
		// TODO Auto-generated method stub
		logger.info("Start save Own Store");
		User u = userRepository.findByUsername(userDto.getUsername());
		if (u != null) {
			throw new AppException(403, "Username is existed!");
		}

		if (userRepository.findByPhone(userDto.getPhone()).isPresent()) {
			throw new AppException(403, "Phone is exsit!");
		}
		List<GroupDto> lstTem = userDto.getGroups_user();
		for (GroupDto groupDto : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(groupDto.getId());
			if (grTemp.isEmpty()) {
				throw new AppException(403, "Group is not exist!");
			}
		}

		User user = modelMapper.map(userDto, User.class);
		Address address = addressService.mapAddress(userDto.getAddress());
		user.setAddress(address);

		user.setIsActive(false);
		user.setStatus(Constants.USER_STATUS_UNACTIVE);
//		user.setCreate_time(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUser_sos(new SOS(1, address,1));
		// user.setGroups_user(lstTem);G
		Request req = createRequestRegister("request to create store", "Create Store", user);
		userRepository.save(user);
		logger.info("Start save Request");
		requestRepository.save(req);
		logger.info("End save Request");
		// return user;
	}

//	public boolean checkRq_v2(User user) {
//		
//	}

	public Request createRequestRegister(String message, String type, User u) {
		logger.info("Start create request type: ");
		Request req = new Request();
		req.setUser(u);
		req.setMessage(message);
		req.setType(type);
		req.setStatus(Constants.REQUEST_STATUS_UNCHECK);
		req.setTimestamp(Ultilities.toSqlDate(Ultilities.getCurrentDate("dd/MM/yyyy")));
		// đang hardcode (nên xem, check quyền tạo)
		Group gOrg = groupRepository.findByCode(Constants.ORG_ADMIN_PER_CODE);
		if (u.getGroups_user().get(0).getId() == gOrg.getId()) {
			req.setOrganization(u.getOrganization());
		} else {
			Group g = groupRepository.findByCode(Constants.SYSTEM_ADMIN_PER_CODE);
			// sai set id. ID phải là id của admin
//			g.setId(10);
			req.setGroup(g);
		}

		return req;
	}

	public boolean checkRqUser(User user) {
		List<Group> lstTem = user.getGroups_user();
		for (Group group : lstTem) {
			Optional<Group> grTemp = groupRepository.findById(group.getId());
			if (grTemp.isEmpty()) {
				return false;
			} else {
				if (group.getLevel() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("null")
	@Override
	public String getUsernameByPhone(String phone) {
		if(phone !=null || !phone.equals("")) {
			phone = "0"+phone.substring(3);
			Optional<User> u = userRepository.findByPhone(phone);
			if(!u.isEmpty()) {
				return u.get().getUsername();
			}else {
				throw new AppException(404, "Phone number not Found");
			}
		}else {
			throw new AppException(404, "Phone number is undefined");
		}
	}

	@Override
	public User getUserByPhone(String phone) {
		User uRs = new User();
		if(phone !=null || !phone.equals("")) {
			phone = "0"+phone.substring(3);
			Optional<User> u = userRepository.findByPhone(phone);
			if(!u.isEmpty()) {
				uRs = u.get();
			}else {
				throw new AppException(404, "Số điện thoại không tồn tại trong hệ thống");
			}
		}else {
			throw new AppException(404, "Số điện thoại không được để trống");
		}
		return uRs;
	}

	@Override
	public boolean checkRegisUser(String phone, String username) {
		if(phone !=null || !phone.equals("")) {
			phone = "0"+phone.substring(3);
			Optional<User> u = userRepository.findByPhone(phone);
			User ucheck = userRepository.findByUsername(username);
			if(!u.isEmpty()||ucheck!=null) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new AppException(404, "Số điện thoại không được để trống");
		}
	}

	@Override
	public void updatePassword(UserDto userDto, UpdatePasswordDto updatePasswordDto) {
		// TODO Auto-generated method stub
		String encNewPass = passwordEncoder.encode(updatePasswordDto.getNewPassword());
		if(!passwordEncoder.matches(updatePasswordDto.getOldPassword(), userDto.getPassword())) {
			throw new AppException(402,"Mật khẩu không chính xác");
		}
		if(encNewPass.equals(userDto.getPassword())) {
			throw new AppException(402,"Mật khẩu không được giống mật khẩu cũ");
		}
		userRepository.updateUser(userDto.getId(), encNewPass);
	}

	@Override
	public void updateUser(UserDto userDto,UserDto bean) {
		// TODO Auto-generated method stub
		
		User u = userRepository.getById(userDto.getId());
		
//		if(userDto.getId() != bean.getId()) {
//			throw new AppException(403,"User not valid");
//		}
		
		u.setFull_name(bean.getFull_name());
		u.setAddress(mapStructMapper.addressDtoToAddress(bean.getAddress()));
		u.setDob(bean.getDob());
		u.setOrganization(mapStructMapper.organizationDtoToOrganization(bean.getOrganization()));

		userRepository.save(u);
		
	}

	@Override
	public String generatePassword(int len) {
		System.out.println("Generating password using random() : ");

		// A strong password has Cap_chars, Lower_chars,
		// numeric value and symbols. So we are using all of
		// them to generate our password
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
//		String symbols = "!@#$%^&*_=+-/.?<>)";

		String values = Capital_chars + Small_chars + numbers;

		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		System.out.print("Your new password is : "+String.valueOf(password)+" ");
		return String.valueOf(password);
	}
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User u = userRepository.findByUsername(username);
		// u.getGroups_user();
		return u;
	}

	@Override
	public SubcribeDto subcribeStore(SubcribeDto s) {
		User u = userRepository.getById(s.getUser_id());
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		Store store = storeRepo.getById(s.getStore_id());
		if(store==null) {
			throw new AppException(403, "Store is not existed!");
		}
		u.getUser_store().add(store);
		userRepository.save(u);
		return s;
	}

	@Override
	public SubcribeDto unSubcribeStore(SubcribeDto s) {
		User u = userRepository.getById(s.getUser_id());
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		Store store = storeRepo.getById(s.getStore_id());
		if(store==null) {
			throw new AppException(403, "Store is not existed!");
		}
		u.getUser_store().remove(store);
		userRepository.save(u);
		return s;
	}

	@Override
	public SubcribeDto getListSubcribe(Long id) {
		User u = userRepository.getById(id);
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		SubcribeDto sdto = new SubcribeDto();
		sdto.setStoreSubcribe(mapStructMapper.lstStoreToStoreDto(u.getUser_store()));
		return sdto;
	}

	@Override
	public GrantAccessDto grantGroup(GrantAccessDto gdto) {
		String native_rs = userRepository.grantGroup(gdto.getSource_id(), gdto.getTarget_id());
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
	public GrantAccessDto unGrantGroup(GrantAccessDto gdto) {
		String native_rs = userRepository.ungrantGroup(gdto.getSource_id(), gdto.getTarget_id());
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
	public GrantAccessDto grantPermission(GrantAccessDto gdto) {
		String native_rs = userRepository.grantPermission(gdto.getSource_id(), gdto.getTarget_id());
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
	public GrantAccessDto unGrantPermission(GrantAccessDto gdto) {
		String native_rs = userRepository.ungrantPermission(gdto.getSource_id(), gdto.getTarget_id());
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
	public Map<String, Object> getUsernameLike(SearchFilterDto sft) {
		List<UserDto> lstUserRs = new ArrayList<UserDto>();
		Sort sortable = null;
	    if (sft.getSort()) {
	    	sortable = Sort.by("username").descending();
	    }else {
	    	sortable = Sort.by("username").descending();
	    }
	    Pageable pageable = PageRequest.of(sft.getPageIndex(), sft.getPageSize(), sortable);
	    Page<User> pageUser = userRepository.searchByNameLike(sft.getSearch(), pageable);
	    lstUserRs = mapStructMapper.lstUserToUserDto(pageUser.getContent());
	    Map<String, Object> response = new HashMap<>();
        response.put("users", lstUserRs);
        response.put("currentPage", pageUser.getNumber());
        response.put("totalItems", pageUser.getTotalElements());
        response.put("totalPages", pageUser.getTotalPages());
		return response;
	}

	@Override
	public Map<String, Object> getOwnOrganizeUser(UserDto u, SearchFilterDto filter) {
		List<UserDto> lstUsrRs = new ArrayList<UserDto>();
		Sort sortable = null;
	    if (filter.getSort()) {
	      sortable = Sort.by("username").descending();
	    }else {
		      sortable = Sort.by("username").ascending();
	    }
	    Pageable pageable = PageRequest.of(filter.getPageIndex(), filter.getPageSize(),sortable);
		Page<User> lstRs = userRepository.getOwnOrganizeUser(u.getOrganization().getId(), u.getId(), filter.getSearch(), pageable);
	    lstUsrRs = mapStructMapper.lstUserToUserDto(lstRs.getContent());
	    Map<String, Object> response = new HashMap<>();
        response.put("users", lstUsrRs);
        response.put("currentPage", lstRs.getNumber());
        response.put("totalItems", lstRs.getTotalElements());
        response.put("totalPages", lstRs.getTotalPages());
		return response;
	}

	@Override
	public User unActiveOrganizeUser(Long id) {
		User u = userRepository.getById(id);
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		u.setIsActive(false);
		//u.setStatus(Constants.USER_STATUS_UNACTIVE);
		return userRepository.saveAndFlush(u);
	}

	@Override
	public User activeOrganizeUser(Long id) {
		User u = userRepository.getById(id);
		if(u==null) {
			throw new AppException(403, "User is not existed!");
		}
		u.setIsActive(true);
		//u.setStatus(Constants.USER_STATUS_ACTIVE);
		return userRepository.saveAndFlush(u);
	}

	@Override
	public User uploadUserImg(ImageDto image) {

		// TODO Auto-generated method stub
		User u = userRepository.getById(image.getId());
		if(null == u) {
			throw new AppException(402,"User is not Found!");
		}
		String img_url = amazonClient.uploadFile(image);
		u.setImages(new Image(img_url));
//		st.getLstImage().add(new Image(st, img_url));
		
		return userRepository.save(u);
	}

	@Override
	public void banUser(Long user_id) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findUserByIdAndStatus(user_id,Constants.USER_STATUS_ACTIVE).orElseThrow(() -> new AppException(403,"Account is not exist"));
		List<Group> lstGroup = user.getGroups_user();
		
		boolean flag = true;
		//loop to find user is Admin of Organization
		for(Group g : lstGroup) {
			if(g.getCode().equals(Constants.ORG_ADMIN_PER_CODE)) {
				userRepository.updateStatusUserByOrg(user.getOrganization().getId(),Constants.USER_STATUS_BANNED);
				flag = false;
				break;
			}
		}
		if(flag) {
			user.setStatus(Constants.USER_STATUS_BANNED);
			userRepository.save(user);
		}

	}
	
	@Override
	public void unbannedUser(Long user_id) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByIdAndStatus(user_id,Constants.USER_STATUS_BANNED).orElseThrow(() -> new AppException(403,"Account is not exist"));
		List<Group> lstGroup = user.getGroups_user();
		boolean flag = true;
		//loop to find user is Admin of Organization
		for(Group g : lstGroup) {
			if(g.getCode().equals(Constants.ORG_ADMIN_PER_CODE)) {
				userRepository.updateStatusUserByOrg(user.getOrganization().getId(),Constants.USER_STATUS_ACTIVE);
				flag = false;
				break;
			}
		}
		
		if(flag) {
			user.setStatus(Constants.USER_STATUS_ACTIVE);
			userRepository.save(user);
		}
	}

	@Override
	public PagingResponse<UserDto> getUserByAdmin(List<String> filterGroup,List<String> filterStatus, String searchStr,int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		if(filterGroup == null || filterGroup.isEmpty()) {
			filterGroup = new ArrayList<String>();
			filterGroup.add(Constants.ORG_ADMIN_PER_CODE);
			filterGroup.add(Constants.STORE_PER_CODE);
			filterGroup.add(Constants.USER_PER_CODE);
		}
		
		if(filterStatus == null || filterStatus.isEmpty()) {
			filterStatus = new ArrayList<String>();
			filterStatus.add(Constants.USER_STATUS_ACTIVE);
			filterStatus.add(Constants.USER_STATUS_BANNED);
//			filterStatus.add(Constants.USER_STATUS_REJECT);
//			filterStatus.add(Constants.USER_STATUS_UNACTIVE);
//			filterStatus.add(Constants.USER_STATUS_WAIT_REQUEST);
		}
		pageIndex = pageIndex - 1;
		
		Pageable page = PageRequest.of(pageIndex,pageSize);
		
		Page<User> pageUser = userRepository.getUserByGroup(Constants.ORG_USER_PER_CODE,filterGroup,filterStatus, searchStr, page);
		List<UserDto> userDto = mapStructMapper.lstBanUserToBanUserDto(pageUser.getContent());
		
		PagingResponse<UserDto> pagingRes = new PagingResponse<UserDto>();
		pagingRes.setObject(userDto);
		pagingRes.setTotalPage(pageUser.getTotalPages());
		pagingRes.setTotalRecord(pageUser.getTotalElements());
		return pagingRes;
	}


}
