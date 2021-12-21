package com.common.utils;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.exception.AppException;

public class DateUtils {

	// https://easycodeforall.com/java-date-utility-functions.html

	/**
	 * get current sql.date
	 * 
	 * @return current date
	 */
	public static Timestamp getCurrentSqlDate() {
		Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Timestamp convertJavaDateToSqlDate(java.util.Date date) {
		if (date == null)
			return null;
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date convertSqlDateToJavaDate(java.sql.Timestamp date) {
		if (date == null)
			return null;
		return new java.util.Date(date.getTime());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Time stringToTimeHHMM(String time) {
		if (time == null)
			return null;
	    
	    return Time.valueOf(LocalTime.parse(time));
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateAsStringInYYYYMMDD(java.util.Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateInyyyy_MM_ddHHmmss(java.util.Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static java.util.Date getDateFromStringInYYYYMMDD(String dateStr) {
		java.util.Date javaDate = null;
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			javaDate = formatter.parse(dateStr);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(405, "Format date happen error");
		}

		return javaDate;

	}

	public static java.util.Date getDateFromStringInYYYYMMDDHH(String dateStr) {
		java.util.Date javaDate = null;
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			javaDate = formatter.parse(dateStr);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException(405, "Format date happen error");
		}

		return javaDate;

	}

	public static String getDateYYYYMMDD(java.sql.Date sqlDate) {

		String rs = null;

		return rs;

	}

	public static String getDateAgo(String formatDate, int dateAgo) {
		java.util.Date today = new java.util.Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -dateAgo);
		java.util.Date dates = cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		String date = f.format(dates);
		return date;
	}

	public static String getMonthAgo(String formatDate, int monthAgo) {
		java.util.Date today = new java.util.Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -monthAgo);
		java.util.Date dates = cal.getTime();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		String date = f.format(dates);
		return date;
	}

	public static String getCurrentDate(String formatDate) {
		java.util.Date today = new java.util.Date();
		SimpleDateFormat f = new SimpleDateFormat(formatDate);
		String date = f.format(today);
		return date;
	}

	public static String getDatehhmm(java.sql.Date sqlDate) {

		String rs = null;

		return rs;

	}
	
	public static java.sql.Date concatToSqlDate(String date , String time) {

		String rs = date.concat(" ").concat(time);

		return null;

	}
	
	public static boolean isDatePast(final Date date, final String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);  
		String strDate = df.format(date);  
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate inputDate = LocalDate.parse(strDate, dtf);

		return inputDate.isBefore(localDate);
	}
}
