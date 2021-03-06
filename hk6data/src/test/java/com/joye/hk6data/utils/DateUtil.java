package com.joye.hk6data.utils;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @类简介：
 * @author 来源陈贵伴 teacher版本
 * @2015年7月28日
 */
public class DateUtil {
	private static String LOG_TAG = "DateUtil";
	private static String defaultDatePattern = null;
	private static String timePattern = "HH:mm";
	public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";
	private static Calendar cale = Calendar.getInstance();
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// ~ Methods
	// ================================================================

	public DateUtil() {
	}

	public static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	/**
	 * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 */
	public static String getDateTime() {
		try {
			return sdf2.format(cale.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 */
	public static String getDateTime(String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(cale.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
	 */
	public static String getDate() {
		try {
			return sdf.format(cale.getTime());
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
	 */
	public static String getTime() {
		String temp = " ";
		try {
			temp += sdf1.format(cale.getTime());
			return temp;
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getTime():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 统计时开始日期的默认值
	 */
	public static String getStartDate() {
		try {
			return getYear() + "-01-01";
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getStartDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 统计时结束日期的默认值
	 */
	public static String getEndDate() {
		try {
			return getDate();
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getEndDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的年份
	 */
	public static String getYear() {
		try {
			return String.valueOf(cale.get(Calendar.YEAR));
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getYear():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的月份
	 */
	public static String getMonth() {
		try {
			java.text.DecimalFormat df = new java.text.DecimalFormat();
			df.applyPattern("00;00");
			return df.format((cale.get(Calendar.MONTH) + 1));
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getMonth():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器在当前月中天数
	 */
	public static String getDay() {
		try {
			return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getDay():" + e.getMessage());
			return "";
		}
	}



	/**
	 * 比较两个日期相差的天数
	 * 
	 */
	public static int getMargin(String date1, String date2) {
		int margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf.parse(date1, pos);
			Date dt2 = sdf.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (int) (l / (24 * 60 * 60 * 1000));
			return margin;
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的天数
	 */
	public static double getDoubleMargin(String date1, String date2) {
		double margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf2.parse(date1, pos);
			Date dt2 = sdf2.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (l / (24 * 60 * 60 * 1000.00));
			return margin;
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int getMonthMargin(String date1, String date2) {
		int margin;
		try {
			margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
			margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-")) - Integer.parseInt(date1
					.substring(4, 7).replaceAll("-0", "-")));
			return margin;
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 返回日期加X天后的日期
	 */
	public static String addDay(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.DATE, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.addDay():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X月后的日期
	 */
	public static String addMonth(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.MONTH, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.addMonth():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X年后的日期
	 */
	public static String addYear(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.YEAR, i);
			return sdf.format(gCal.getTime());
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.addYear():" + e.toString());
			return "";
		}
	}

	/**
	 * 返回某年某月中的最大天
	 */
	public static int getMaxDay(String year, String month) {
		int day = 0;
		try {
			int iyear = Integer.parseInt(year);
			int imonth = Integer.parseInt(month);
			if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12) {
				day = 31;
			} else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
				day = 30;
			} else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
				day = 29;
			} else {
				day = 28;
			}
			return day;
		} catch (Exception e) {
			Log.e(LOG_TAG, "DateUtil.getMonthDay():" + e.toString());
			return 1;
		}
	}

	/**
	 * 格式化日期
	 */
	@SuppressWarnings("static-access")
	public String rollDate(String orgDate, int Type, int Span) {
		try {
			String temp = "";
			int iyear, imonth, iday;
			int iPos = 0;
			char seperater = '-';
			if (orgDate == null || orgDate.length() < 6) {
				return "";
			}

			iPos = orgDate.indexOf(seperater);
			if (iPos > 0) {
				iyear = Integer.parseInt(orgDate.substring(0, iPos));
				temp = orgDate.substring(iPos + 1);
			} else {
				iyear = Integer.parseInt(orgDate.substring(0, 4));
				temp = orgDate.substring(4);
			}

			iPos = temp.indexOf(seperater);
			if (iPos > 0) {
				imonth = Integer.parseInt(temp.substring(0, iPos));
				temp = temp.substring(iPos + 1);
			} else {
				imonth = Integer.parseInt(temp.substring(0, 2));
				temp = temp.substring(2);
			}

			imonth--;
			if (imonth < 0 || imonth > 11) {
				imonth = 0;
			}

			iday = Integer.parseInt(temp);
			if (iday < 1 || iday > 31)
				iday = 1;

			Calendar orgcale = Calendar.getInstance();
			orgcale.set(iyear, imonth, iday);
			temp = this.rollDate(orgcale, Type, Span);
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	public static String rollDate(Calendar cal, int Type, int Span) {
		try {
			String temp = "";
			Calendar rolcale;
			rolcale = cal;
			rolcale.add(Type, Span);
			temp = sdf.format(rolcale.getTime());
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * 返回默认的日期格式
	 * 
	 */
	public static synchronized String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";
		return defaultDatePattern;
	}

	/**
	 * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 取得给定日期的时间字符串，格式为当前默认时间格式
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * 取得当前时间的Calendar日历对象
	 */
	public Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	/**
	 * 取得指定时间的Calendar日历对象
	 */
	public static Calendar getCalendarday(String date) throws ParseException {
		Date today = convertStringToDate(date);
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	/**
	 * 取得指定时间的Calendar日历对象
	 */
	public static Calendar getCalendardayYYYYMM(String date) throws ParseException {
		Date today = convertStringToDate1(date, "yyyy-MM");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate1(todayAsString, "yyyy-MM"));
		return cal;
	}

	/**
	 * 将日期类转换成指定格式的字符串形式
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			Log.e(LOG_TAG, "aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将指定的日期转换成默认格式的字符串形式
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 将指定的日期转换成指定格式的字符串形式
	 */
	public static final String convertDateToString(String aMask, Date aDate) {
		return getDateTime(aMask, aDate);
	}

	/**
	 * 将日期字符串按指定格式转换成日期类型
	 * 
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		if(StringUtils.isBlank(strDate)){
			return  new Date();
		}
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			Log.e(LOG_TAG, "ParseException: " + pe);
			throw pe;
		}
		return (date);
	}

	/**
	 * 将日期字符串按默认格式转换成日期类型
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {

			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 将日期字符串按默认格式转换成日期类型
	 * 
	 */
	public static Date convertStringToDate1(String strDate, String amask) throws ParseException {
		Date aDate = null;

		try {

			aDate = convertStringToDate(amask, strDate);
		} catch (ParseException pe) {

			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 字符串日期格式转换格式
	 * 
	 */
	public static String convertDateStringFormat(String strDate, String fromFormat, String toFormat) {
		String date = null;
		try {
			Date aDate = convertStringToDate(fromFormat, strDate);
			date = convertDateToString(toFormat, aDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回一个JAVA简单类型的日期字符串
	 */
	public static String getSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		String NDateTime = formatter.format(new Date());
		return NDateTime;
	}

	/**
	 * 将两个字符串格式的日期进行比较
	 * 
	 * @param last
	 *            要比较的第一个日期字符串
	 * @param now
	 *            要比较的第二个日期格式字符串
	 * @return true(last 在now 日期之前),false(last 在now 日期之后)
	 */
	public static boolean compareTo(String last, String now) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date temp1 = formatter.parse(last);
			Date temp2 = formatter.parse(now);
			if (temp1.after(temp2))
				return false;
			else if (temp1.before(temp2))
				return true;
		} catch (ParseException e) {
			Log.e(LOG_TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * 将两个字符串格式的日期进行比较
	 * 
	 * @param last
	 *            要比较的第一个日期
	 * @param now
	 *            要比较的第二个日期
	 * @return true(last 在now 日期之前),false(last 在now 日期之后)
	 */
	public static boolean compagreTo(Date last, Date now) {
		if (last.after(now))
			return false;
		else if (last.before(now))
			return true;
		return false;
	}

	// protected Object convertToDate(Class type, Object value) {
	// DateFormat df = new SimpleDateFormat(TS_FORMAT);
	// if (value instanceof String) {
	// try {
	// if (StringUtils.isEmpty(value.toString())) {
	// return null;
	// }
	// return df.parse((String) value);
	// } catch (Exception pe) {
	// throw new ConversionException(
	// "Error converting String to Timestamp");
	// }
	// }
	//
	// throw new ConversionException("Could not convert "
	// + value.getClass().getName() + " to " + type.getName());
	// }

	/**
	 * 为查询日期添加最小时间
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static Date addStartTime(Date param) {
		Date date = param;
		try {
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			return date;
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 为查询日期添加最大时间
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static Date addEndTime(Date param) {
		Date date = param;
		try {
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(0);
			return date;
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 返回指定年份中指定月份的天数
	 * 
	 * @param year 年份
	 * @param month 月份
	 * @return 指定月的总天数
	 */
	public static String getMonthLastDay(int year, int month) {
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return day[1][month] + "";
		} else {
			return day[0][month] + "";
		}
	}

	/**
	 * 取得当前时间的日戳
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp() {
		Date date = new Date();
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	/**
	 * 取得指定时间的日戳
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp(Date date) {
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	/**
	 * 取得指定时间的天
	 * 
	 */
	public static int getDaOfMonth(Date date) {
		cale.setTime(date);
		return cale.get(Calendar.DATE);
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dateString
	 * @return 当前日期是星期几
	 */
	public static int getWeekOfDate(String dateString) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(convertStringToDate(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static int getWeekOfDate(){
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param wfmt
	 *            格式：yyyy-mm-dd
	 * @param dateStr
	 *            时间
	 * @return
	 */
	public static String getWeek(String wfmt, String dateStr) {
		Date d = null;
		try {
			d = convertStringToDate(wfmt, dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getWeekOfDate(d);
	}

	public final static String[] monthArr = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };// 月份

	/**
	 * 返回当月所有日期
	 * 
	 * @return
	 */
	public static double[] getCurrentMonthDatesDoubleArray() {
		String currentDate = DateUtil.getDate();
		return getMonthDatesDoubleArray(currentDate);
	}

	/**
	 * 返回指定日期当月所有日期
	 * 
	 * @return
	 */
	public static double[] getMonthDatesDoubleArray(String dateString) {
		int maxDay = DateUtil.getMaxDay(dateString.substring(0, 4), dateString.substring(5, 7));

		double[] dates = new double[maxDay];
		for (int i = 0; i < maxDay; i++) {
			dates[i] = (i + 1);
		}

		return dates;
	}

	public static Map<String, String> getDayBetweenTowDay(String date1, String date2)
	{
		String beginDate=date1;
		Map<String, String> map =new HashMap<String, String>();
		map.put(beginDate, beginDate);
		if ( daysBetween(date1, date2) > 0) {
			do{
				beginDate=addDateToString(beginDate, 1);
				map.put(beginDate, beginDate);
			}while(!beginDate.equals(date2));
		}


		return map;
	}
	/**
	 * 计算两个日期相差天数可跨年
	 */
	public static int daysBetween(String date1,String date2)
	{
		try {
			Calendar cal = Calendar.getInstance();

			cal.setTime(convertStringToDate(date1));

			long time1 = cal.getTimeInMillis();
			cal.setTime(convertStringToDate(date2));
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {

		}
		return 0;
	}
	public static String addDateToString(String date, int amount) {
		try {

			if (date == null || "".equals(date.trim())) {
				return "";
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(convertStringToDate(date));
			calendar.add(Calendar.DATE, amount);
			return getDate(calendar.getTime());
		}catch (ParseException e){
			return "";
		}
	}
	/**
	 * 返回当月所有日期
	 * 
	 * @return
	 */
	public static String[] getCurrentMonthDatesStringArray() {
		String currentDate = DateUtil.getDate();
		int maxDay = DateUtil.getMaxDay(currentDate.substring(0, 4), currentDate.substring(5, 7));

		String[] dates = new String[maxDay];
		for (int i = 0; i < maxDay; i++) {
			String DateTemp = i < 10 ? ("0" + (i + 1)) : ("" + (i + 1));
			dates[i] = DateTemp;
		}

		return dates;
	}

	/**
	 * 取本周7天的第一天（周一的日期）
	 */
	public static String getNowWeekBeginDate() {
		return findWeekStartDate(getDate());
	}

	/**
	 * 本周最后一天
	 * 
	 * @return
	 */
	public static String getNowWeekEndDate() {
		return findWeekEndDate(getDate());
	}

	/**
	 * 下周第一天
	 * 
	 * @param baseDateStr
	 * @return
	 */
	public static String getNextWeekStartDate(String baseDateStr) {
		return findWeekStartDate(addDay(baseDateStr, 7));
	}

	/**
	 * 下周最后一天
	 * 
	 * @param baseDateStr
	 * @return
	 */
	public static String getNextWeekEndDate(String baseDateStr) {
		return findWeekEndDate(addDay(baseDateStr, 7));
	}

	/**
	 * 上一周第一天
	 * 
	 * @param baseDateStr
	 * @return
	 */
	public static String getPreWeekStartDate(String baseDateStr) {
		return findWeekStartDate(addDay(baseDateStr, -7));
	}

	/**
	 * 上一周最后一天
	 * 
	 * @param baseDateStr
	 * @return
	 */
	public static String getPreWeekEndDate(String baseDateStr) {
		return findWeekEndDate(addDay(baseDateStr, -7));
	}

	/**
	 * 指定日期的当周的第一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String findWeekStartDate(String dateStr) {
		// int mondayPlus;
		// Calendar cd = Calendar.getInstance();
		// try {
		// cd.setTime(convertStringToDate(dateStr));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		// int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; //
		// 因为按中国礼拜一作为第一天所以这里减1
		// if (dayOfWeek == 1) {
		// mondayPlus = 0;
		// } else {
		// mondayPlus = 1 - dayOfWeek;
		// }
		// GregorianCalendar currentDate = new GregorianCalendar();
		// currentDate.setTime(cd.getTime());
		// currentDate.add(GregorianCalendar.DATE, mondayPlus);
		// Date monday = currentDate.getTime();
		//
		// return sdf.format(monday);
		int mondayPlus;
		Calendar cd = Calendar.getInstance(Locale.CHINA);
		try {
			cd.setTime(convertStringToDate(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int day = cd.get(Calendar.DAY_OF_WEEK) ;
		int dayOfWeek = -1 ;
		if(day==1){
			dayOfWeek = 7 ;
		}else {
			dayOfWeek =  day - 1; // 因为按中国礼拜一作为第一天所以这里减1
		}
		if (dayOfWeek == 1) {
			mondayPlus = 0;
		} else {
			mondayPlus = 1 - dayOfWeek;
		}

		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(cd.getTime());
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		return sdf.format(monday);
	}

	/**
	 * 指定日期的当周的最后一天
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String findWeekEndDate(String dateStr) {
		String firstDate = findWeekStartDate(dateStr);
		return addDay(firstDate, 6);
	}

	/**
	 * 把基于yyyy-MM-dd HH:mm:ss格式的日期转换成指定的格式
	 */
	public static String formatDate(String strDate, String toFormat) {
		try {
			return getDateTime(toFormat, convertStringToDate("yyyy-MM-dd HH:mm:ss", strDate));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 这个月第一天
	 * @param year 年 份
	 * @param month 月份
	 * @return
	 */
	public static String getFisrtDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());

		return firstDayOfMonth;
	}
	/**
	 * 这个月最后一天
	 * @param year 年份
	 * @param month 月份呢
	 * @return
	 */

	public static String getLastDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.DAY_OF_MONTH,1);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最小天数
		int lastDay= cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		//设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDayOfMonth = sdf.format(cal.getTime());

		return firstDayOfMonth;
	}

	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static int getCurrentMonth(){
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	public static int getCurrentDay(){
		return Calendar.getInstance().get(Calendar.DATE);
	}

}
