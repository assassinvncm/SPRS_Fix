package com.ultils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.api.entity.Permission;
import com.api.entity.ReliefPoint;
import com.api.entity.Store;
import com.jwt.config.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class Ultilities {
	
	public static String getCurrentDateStr(String formatDate) {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		return f.format(d);
	}
	
	public static Date getCurrentDate(String formatDate) {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		try {
			return f.parse(f.format(d));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isExistedIn(String origin, String[] match) {
		boolean check = false;
		for (String string : match) {
			if(string.equals(origin)) {
				check = true;
			}
		}
		return check;
	}
	
	public static Timestamp toSqlDate(Date d) {
        java.sql.Timestamp sql = new java.sql.Timestamp(d.getTime());
        return sql;
	}
	
	public static List<String> getLabelReport(List<String> arrListNumber) {
		List<String> arrTemp = new ArrayList<String>();
	    for (int i = 0; i < arrListNumber.size(); i++) {
	        if (!arrTemp.contains(arrListNumber.get(i))) {
	            arrTemp.add(arrListNumber.get(i));
	        }
	    }
	    arrTemp.sort(Comparator.naturalOrder());
	    return arrTemp;
	}
	
	public static List<Permission> removeDuplicatePermission(List<Permission> arrListNumber) {
		List<Permission> arrTemp = new ArrayList<Permission>();
	    for (int i = 0; i < arrListNumber.size(); i++) {
	        if (!arrTemp.contains(arrListNumber.get(i))) {
	            arrTemp.add(arrListNumber.get(i));
	        }
	    }
	    return arrTemp;
	}
	
	public static boolean checkExistIn(int value, int[] inputValues) {
		boolean isExist = false;
		for (int i = 0; i < inputValues.length; i++) {
			if(value == inputValues[i])
				isExist = true;
		}
		return isExist;
	}
	
	public static int getStatusRelief(ReliefPoint rp) {
		Date currDate = getCurrentDate("yyyy-MM-dd hh:mm:ss");
		Timestamp open_time = rp.getOpen_time();
		Timestamp close_time = rp.getClose_time();
		int compare_open = open_time.compareTo(currDate); //0: equals, > 0 greater, <0 less than
		int compare_close = close_time.compareTo(currDate);
		if(compare_open > 0) {
			return 2;
		}else if(compare_open <= 0 && compare_close >= 0) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public static int getStatusStore(Store st) {
		Date d = new Date();
		Date userDate;
		try {
			SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
			Date open_time = parser.parse(st.getOpen_time().toString());
			Date close_time = parser.parse(st.getClose_time().toString());
			userDate = parser.parse(parser.format(d));
			
			if (userDate.after(open_time) && userDate.before(close_time)) {
		        return 0;
		    }else {
		    	return 1;
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
