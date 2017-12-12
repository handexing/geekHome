package com.geekhome.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	// public static void main(String[] args) {
	// Calendar cal1 = Calendar.getInstance();
	//
	// try {
	// Thread.sleep(1000 * 60 * 2 - 1);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// Calendar cal2 = Calendar.getInstance();
	// int ret = compareSmsTime(cal2.getTimeInMillis(), cal1.getTimeInMillis())
	// ;
	// System.out.println("是否过期："+ret);
	// }
	

	/**
	 * 
	 * @param nowTime 当前时间
	 * @param beforeTime 对比时间
	 * @return -1 超过10分钟，1没超过
	 */
	public static int compareSmsTime(Long beforeTime) {
		final long time = 60 * 1000 * 10;
		Calendar cal = Calendar.getInstance();
		Long nowTime = cal.getTimeInMillis();
		long difference = nowTime - beforeTime;
		if (difference > time) {
			return -1;
		}
		return 1;
	}

	/**
	 * 
	 * @param nowTime 当前时间
	 * @param beforeTime 对比时间
	 * @return -1 超过10分钟，1没超过
	 */
	public static int compareSmsTime(Long nowTime, Long beforeTime) {
		final long time = 60 * 1000 * 10;
		System.out.println("nowTime:" + nowTime);
		System.out.println("beforeTime:" + beforeTime);
		long difference = nowTime - beforeTime;
		System.out.println("difference:" + difference);
		System.out.println("time:" + time);
		// 相差天数：
		// long day = difference / (3600 * 24 * 1000);
		// 相差小时：
		// long hour = difference / (3600 * 1000);
		// 相差分钟：
		// long minute = difference / (60 * 1000);
		// System.out.println("minute:" + minute);
		// 相差秒：
		// long second = difference / 1000;

		if (difference > time) {
			return -1;
		}
		return 1;
	}

	public static String getDateFormatStr(Date date) {
		if (date == null) {
			return new SimpleDateFormat(DEFAULT_FORMAT).format(new Date());
		}
		return new SimpleDateFormat(DEFAULT_FORMAT).format(date);
	}
	
	/**
	 * 日期转字符串
	 * 
	 * @param date 日期
	 * @param format 格式
	 * @return 格式化后的字符串
	 */
	public static String getDateFormatStr(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 字符串转格式化日期
	 * 
	 * @param dateStr 日期字符串
	 * @param format 格式
	 * @return 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (final ParseException e) {
			logger.error("", e);
			return null;
		}
	}
	
	 public static Date getWebsiteDatetime(){
	        try {
	            URL url = new URL("http://www.baidu.com");// 取得资源对象
	            URLConnection uc = url.openConnection();// 生成连接对象
	            uc.connect();// 发出连接
	            long ld = uc.getDate();// 读取网站日期时间
	            Date date = new Date(ld);// 转换为标准时间对象
//	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
//	            return sdf.format(date);
	            return date;
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
