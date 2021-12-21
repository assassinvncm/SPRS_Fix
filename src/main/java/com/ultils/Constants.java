package com.ultils;

public class Constants {
	public static final String NOTFOUND = "404";
	public static final String EXISTED = "403";
	public static final String SUCCESS = "200";
	public static final String FAILED = "102";
	public static final String SERVER_ERR = "501";
	public static final String OTP_MESSAGE = "Mã xác minh SPRS của bạn là ";
	public static final String[] NONE_AUTH = { "/utilities/**", "/swagger-ui.html", "/swagger-ui/**",
			"/sprs/api/address/**", "/v3/**", "/sprs/api/item", "/authenticate-web", "/authenticate-mobile",
			"/sprs/api/organization-manage/origanization", "/sprs/api/user", "/sprs/api/users_v2/user",
			"/sprs/api/forgotPassword", //"/sprs/api/reliefPoint-manage/get",
			"/sprs/api/organization-manage/origanization", "/sprs/api/user", "/sprs/api/users_v2/user",
			"/sprs/api/manage-map/*", "/sprs/api/users_v2/organizationlAdmin", "/sprs/api/users_V2/user","/sprs/api/users_v2/ownStore",
			"/sprs/api/users_v2/organizationalUser", "/sprs/api/group", "/sprs/api/group/{id}", "/sprs/api/generateOtp",
			"/sprs/api/validateOtp", "/sprs/api/groups-register-mobile", "/sprs/api/groups-register-web"
			,"/sprs/api/groups-all","/sprs/api/*/common/**", "/sprs/api/generateOtp-verify", "/sprs/api/validateOtp-verify"};// ,
	public static final String RESET_PASSWORD_MESSAGE = "Mật khẩu mới của bạn là "; // "/sprs/api/generateOtp",
	// "/sprs/api/validateOtp"
	// constants config otp
	public static final String ACCOUNT_SID = "ACaef9117a42d80b9837a3d4bc0acf8fe0";
	public static final String AUTH_TOKEN = "c6e87bd6445ab2f230c3d053f1174be5";
	public static final String FROM_NUMBER = "4128967877";

	// constants for request status
	public static final String REQUEST_STATUS_ACCEPT = "accept";
	public static final String REQUEST_STATUS_REJECT = "reject";
	public static final String REQUEST_STATUS_UNCHECK = "uncheck";
	//constants for user status
	public static final String USER_STATUS_BANNED = "Banned";
	public static final String USER_STATUS_ACTIVE = "Actived";
	public static final String USER_STATUS_WAIT_REQUEST = "Waiting Request";
	public static final String USER_STATUS_UNACTIVE = "un Active";
	public static final String USER_STATUS_REJECT = "Reject";
	// constants for request status
	public static final String IMAGE_URL = "https://image-bucket-sprs.s3.ap-southeast-1.amazonaws.com/";

	// constants notification type
	public static final String NOTIFICATION_TYPE_STORE = "st";
	public static final String NOTIFICATION_TYPE_RELIEFPOINT = "rp";
	public static final String NOTIFICATION_TYPE_ADMIN = "ad";
	public static final String NOTIFICATION_TYPE_SOS = "sos";
	
	// constants notification status
	public static final String NOTIFICATION_STATUS_UNCHECK = "uncheck";
	public static final String NOTIFICATION_STATUS_CHECK = "check";
	public static final String NOTIFICATION_STATUS_READ = "read";

	// constants map type point
	public static final String MAP_TYPE_STORE = "st";
	public static final String MAP_TYPE_RELIEFPOINT = "rp";
	public static final String MAP_TYPE_ORGANIZATION = "org";
	public static final String MAP_TYPE_SOS = "sos";
	
	//Constant for Group permission
	public static final String USER_PER_CODE = "PER_NORMU_ACEN";
	public static final String ORG_USER_PER_CODE = "PER_ORGU_ACEN";
	public static final String ORG_ADMIN_PER_CODE = "PER_ORGADM_ACEN";
	public static final String STORE_PER_CODE = "PER_STR_ACEN";
	public static final String SYSTEM_ADMIN_PER_CODE = "PER_SYSADM_ACEN";
	
	//Constant for status store
	public static final int STORE_STATUS_OPEN = 0;
	public static final int STORE_STATUS_CLOSED = 1;
	public static final int STORE_STATUS_TEM_ClOSED = 2;
	//Constant for status SOS
	public static final int SOS_STATUS_TURNON = 1;
	public static final int SOS_STATUS_TURNOF = 0;
}
