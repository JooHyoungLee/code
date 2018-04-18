package kr.co.chahoo.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FormatUtils.class); 

	public static final NumberFormat NF1 = new DecimalFormat("0.#");
	public static final NumberFormat NF2 = new DecimalFormat("0.##");
	public static final NumberFormat NF3 = new DecimalFormat("0.###");

	public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat YYYY = new SimpleDateFormat("yyyy");
	public static final DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final DateFormat YYYY_MM_DD_HHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final DateFormat YYYY_MM_DD_T_HHMM = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
	public static final DateFormat YYYY_MM_DD_HHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat YYYY_MM_DD_HHMMSS_24 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final DateFormat YYYY_MM_DD_HHMMSS_SSS_24 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	public static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat YYYY_MM_DD_T_HHMMSS_Z = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'");
	public static final DateFormat YYYY_MM_DD_T = new SimpleDateFormat("yyyy-MM-dd\'T\'");
	public static final DateFormat YYMMDD = new SimpleDateFormat("yyMMdd");
	public static final DateFormat YYMMDDHHMMSS = new SimpleDateFormat("yyMMddHHmmss");

	public static final String todayToString_YYYYMMDD() {
		Date date = new Date();
		String now = YYYYMMDD.format(date);
		return now;
	}
	
	public static final String todayToString_YYYY() {
		Date date = new Date();
		String now = YYYY.format(date);
		return now;
	}

	public static final String todayToString_YYYY_MM_DD() {
		Date date = new Date();
		String now = YYYY_MM_DD.format(date);
		return now;
	}

	public static final String todayToString_YYMMDD() {
		Date date = new Date();
		String now = YYMMDD.format(date);
		return now;
	}

	public static final String todayToString_YYMMDDHHMMSS() {
		Date date = new Date();
		String now = YYMMDDHHMMSS.format(date);
		return now;
	}

	public static final String todayToString_YYYYMMDDHHMMSS() {
		Date date = new Date();
		String now = YYYYMMDDHHMMSS.format(date);
		return now;
	}

	public static final Timestamp todayToTimestamp() {
		Date date = Calendar.getInstance().getTime();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	public static final long todayToMilliseconds() {
		long time = todayToTimestamp().getTime();
		return time;
	}

	public static final String todayToESDate_YYYY_MM_DD_T() {
		Date date = Calendar.getInstance().getTime();
		String str = YYYY_MM_DD_T.format(date);
		return str;
	}

	public static final String todayToESDate() {
		Date date = Calendar.getInstance().getTime();
		String str = YYYY_MM_DD_T_HHMMSS_Z.format(date);
		return str;
	}

	public static final Date secondsToDatetime(long sec) {
		Date date = new Date(sec);
		return date;
	}

	public static final String secondsToString(long sec) {
		return secondsToString(sec, 0);
	}

	public static final Timestamp string_YYYY_MM_DD_HHMMSSToTimestamp(String str) {
		try {
			Date date = YYYY_MM_DD_HHMMSS.parse(str);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;			
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}		
	}
	
	
	public static final Timestamp string_YYYY_MM_DD_HHMMToTimestamp(String str) {
		try {
			Date date = YYYY_MM_DD_HHMM.parse(str);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;			
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}		
	}
	
	public static final Timestamp string_YYYY_MM_DD_T_HHMMToTimestamp(String str) {
		try {
			Date date = YYYY_MM_DD_T_HHMM.parse(str);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;			
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}		
	}

	public static final String string_YYYY_MM_DDToString_YYYYMMDD(String str) {
		try {
			Date date = YYYY_MM_DD.parse(str);
			String esd = YYYYMMDD.format(date);
			return esd;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String string_YYYY_MM_DDToESDate(String str) {
		Date date;
		try {
			date = YYYY_MM_DD.parse(str);
			String esd = YYYY_MM_DD_T_HHMMSS_Z.format(date);
			return esd;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String dateToESDate(Date date) {
		String esd = YYYY_MM_DD_T_HHMMSS_Z.format(date);
		return esd;
	}

	public static final String dateTo_YYYY_MM_DD_Str(Date date) {
		String esd = YYYY_MM_DD.format(date);
		return esd;
	}

	public static final Timestamp string_YYYY_MM_DDToTimestamp(String str) {
		try {
			Date date = YYYY_MM_DD.parse(str);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;			
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}		
	}
	
	public static final Timestamp string_YYYY_MM_DDToTimestampAddOneMonth(String str) {
		try {
			Date date = YYYY_MM_DD.parse(str);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);    
			
			Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
			
			return timestamp;			
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}		
	}

	public static final String secondsToString(long sec, int type) {
		String date;
		switch (type) {
		case 0:
			date = YYYY_MM_DD_HHMMSS.format(secondsToDatetime(sec).getTime()*1000);
			break;
		case 1:
			date = YYYYMMDD.format(secondsToDatetime(sec).getTime()*1000);
			break;
		case 2:
			date = YYYYMMDDHHMMSS.format(secondsToDatetime(sec).getTime()*1000);
			break;
		case 3:
			date = YYYY_MM_DD_HHMMSS.format(secondsToDatetime(sec).getTime()*1000);
			break;
		case 4:
			date = YYYY_MM_DD_HHMMSS_24.format(secondsToDatetime(sec).getTime()*1000);
			break;
		default:
			date = YYYY_MM_DD_HHMMSS.format(secondsToDatetime(sec).getTime()*1000);
			break;
		}		
		return date;
	}

	public static final String todayToEpochTimestamp() {
		Date dateType = Calendar.getInstance().getTime();
		String str = String.valueOf(dateType.getTime()/1000);
		return str;
	}

	public static final String stringToEpochTimestamp(String date, int type) throws ParseException {
		String temp;
		Date dateType;
		switch (type) {
		case 0:
			dateType = YYYY_MM_DD_HHMMSS.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		case 1:
			dateType = YYYYMMDD.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		case 2:
			dateType = YYYYMMDDHHMMSS.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		case 3:
			dateType = YYYY_MM_DD_HHMMSS.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		case 4:
			dateType = YYYY_MM_DD_HHMMSS_24.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		case 6:
			dateType = YYYY_MM_DD.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		default:
			dateType = YYYY_MM_DD_HHMMSS.parse(date);
			temp = String.valueOf(dateType.getTime()/1000);
			break;
		}		
		return temp;
	}

	public static final Date string_YYYY_MM_DDToDate(String str) {
		Date date = null;
		try {
			date = YYYY_MM_DD.parse(str);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
		return date;
	}

	public static final Date string_YYYYMMDDToDate(String str) {
		Date date = null;
		try {
			date = YYYYMMDD.parse(str);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
		return date;
	}

	public static final String timestampToString_YYYY_MM_DD(Timestamp ts) {
		String str = YYYY_MM_DD.format(ts);
		return str;
	}
	
	public static final String timestampToString_YYYY_MM_DD_HH_MM(Timestamp ts) {
		String str = YYYY_MM_DD_HHMM.format(ts);
		return str;
	}
	
	public static final String timestampToString_YYYY_MM_DD_T_HH_MM(Timestamp ts) {
		String str = YYYY_MM_DD_T_HHMM.format(ts);
		return str;
	}

	public static final String string_YYYY_MM_DDAddDateString_YYYY_MM_DD(String str, int i) {
		try {
			Date date = FormatUtils.YYYY_MM_DD.parse(str);
			long idate = ((long)(1000*60*60*24) * (long)(i-1));
			long tdate = date.getTime();
			Date dt = new Date(tdate + idate);
			String ret = YYYY_MM_DD.format(dt);
			return ret;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String string_YYYY_MM_DDSubstractDateString_YYYY_MM_DD(String str, int i) {
		try {
			Date date = FormatUtils.YYYY_MM_DD.parse(str);
			long idate = ((long)(1000*60*60*24) * (long)(i-1));
			long tdate = date.getTime();
			Date dt = new Date(tdate - idate);
			String ret = YYYY_MM_DD.format(dt);
			return ret;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String string_YYYY_MM_DDAddDateESDate_YYYY_MM_DD(String str, int i) {
		try {
			Date date = FormatUtils.YYYY_MM_DD.parse(str);
			long idate = ((long)(1000*60*60*24) * (long)(i-1));
			long tdate = date.getTime();
			Date dt = new Date(tdate + idate);
			String ret = YYYY_MM_DD.format(dt)+"T";
			return ret;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String string_YYYY_MM_DDSubstractDateESDate_YYYY_MM_DD(String str, int i) {
		try {
			Date date = FormatUtils.YYYY_MM_DD.parse(str);
			long idate = ((long)(1000*60*60*24) * i) ;
			long tdate = date.getTime();
			Date dt = new Date(tdate - idate);
			String ret = YYYY_MM_DD.format(dt)+"T";
			return ret;
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	public static final String ESDateToStringDatetime(String esDate) {
		String str = esDate.replace("T", " ").replace("Z", "").trim();
		return str;
	}

	public static final String ESDateToStringDate(String esDate) {
		int i = esDate.indexOf("T");
		if(i > 0) {
			String str = esDate.substring(0, i);
			return str;
		} else {
			return esDate;
		}
	}

	public static final boolean checkDate(String startDate, String endDate) {
		if(startDate == null || endDate == null) { 
			return false; 
		}

		Pattern p = Pattern.compile("^((19|20)\\d\\d)[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$");
		Matcher m1 = p.matcher(startDate);
		Matcher m2 = p.matcher(endDate);

		if(!m1.matches() || !m2.matches()) { 
			return false; 
		}

		return true;
	}

	public static final int age(Date dt) {

		//		timestampToString_YYYY_MM_DD(ts)
		//		Date toDate = Calendar.getInstance().getTime();

		int years = 0;
		int months = 0;
		@SuppressWarnings("unused")
		int days = 0;

		//create calendar object for birth day
		Calendar birthDay = Calendar.getInstance();
		birthDay.setTimeInMillis(dt.getTime());

		//create calendar object for current day
		long currentTime = System.currentTimeMillis();
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(currentTime);

		//Get difference between years
		years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		int currMonth = now.get(Calendar.MONTH) + 1;
		int birthMonth = birthDay.get(Calendar.MONTH) + 1;

		//Get difference between months
		months = currMonth - birthMonth;
		//if month difference is in negative then reduce years by one and calculate the number of months.
		if (months < 0) {
			years--;
			months = 12 - birthMonth + currMonth;
			if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
				months--;
			}
		} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			years--;
			months = 11;
		}

		//Calculate the days
		if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE)) {
			days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		} else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			int today = now.get(Calendar.DAY_OF_MONTH);
			now.add(Calendar.MONTH, -1);
			days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
		} else {
			days = 0;
			if (months == 12) {
				years++;
				months = 0;
			}
		}

		//Create new Age object 
		//	    return new Age(days, months, years);
		return years;
	}

	public static final boolean isNumber(String str) {
		if(str == null) { 
			return false; 
		}
		Pattern p = Pattern.compile("([\\p{Digit}]+)(([.]?)([\\p{Digit}]+))?");
		Matcher m = p.matcher(str);
		return m.matches();   
	}

	private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATEFORMAT = new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	public static final List<String> startDateToEndDateArray(String startDate, String endDate) {
		List<String> list = new LinkedList<String>();
		try {
			DateFormat df = THREAD_LOCAL_DATEFORMAT.get();
			Date sdate = df.parse(startDate);
			Date edate = df.parse(endDate);
			long sdlong = sdate.getTime();
			long edlong = edate.getTime();
			long stlong = sdlong;
			while(edlong > stlong) {
				stlong += (long)(1000*60*60*24);
				String ret = YYYY_MM_DD.format(stlong)+"T";
				list.add(ret);
			}
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
			return list;
		}
	}

	/**
	 * 파일 확장자 판별
	 * @param fileName
	 * @return
	 */
	public static final String getExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');

		if (-1 != dotPosition && fileName.length() - 1 > dotPosition) {
			return fileName.substring(dotPosition + 1);
		} else {
			return ".tmp";
		}
	}
	
	/**
	 * 파일이동
	 * @param inFileName
	 * @param outFileName
	 */
	public static void fileMove(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			fileDelete(inFileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 파일 삭제
	 * @param deleteFileName
	 */
	public static void fileDelete(String deleteFileName) {
		File I = new File(deleteFileName);
		I.delete();
	}
	
	/**
	 * 파일 존재우무 판별
	 * @param isLivefile
	 * @return
	 */
	public static Boolean fileIsLive(String isLivefile) {
		File f1 = new File(isLivefile);

		if(f1.exists())
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * MineType 반환
	 * @param filePath
	 * @return
	 */
	public static String fileMineType(String filePath)
	{
		String mimeType = null;
		try {
			File file = new File(filePath);
			Tika tika = new Tika();
			mimeType = tika.detect(file);
		} 
		catch (Exception e) {
			System.err.println("MineType 반환 실패");
		}
		System.err.println(mimeType);
		return mimeType;
	}
	
	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;

	public static String convertToStringRepresentation(final long value){
	    final long[] dividers = new long[] { T, G, M, K, 1 };
	    final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	    if(value < 1)
	        throw new IllegalArgumentException("Invalid file size: " + value);
	    String result = null;
	    for(int i = 0; i < dividers.length; i++){
	        final long divider = dividers[i];
	        if(value >= divider){
	            result = format(value, divider, units[i]);
	            break;
	        }
	    }
	    return result;
	}

	private static String format(final long value,
	    final long divider,
	    final String unit){
	    final double result =
	        divider > 1 ? (double) value / (double) divider : (double) value;
	    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}
}
