package com.pancou.ad.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;

/**
 * ʱ�������
 * 
 * @author tmb
 * @version 1.10
 */
@Entity
public class DatetimeHandle {

	/**���ڸ�ʽ��"yyyy-MM-dd HH:mm:ss"*/
	public final static String LONG_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	
	/**���ڸ�ʽ��"yyyy-MM-dd"*/
	public final static String SHORT_TIME_FORMAT_STRING = "yyyy-MM-dd";
	
	/**
	 * ��õ�ǰʱ��ĸ�ʽ���ַ�
	 * 
	 * @see LONG_TIME_FORMAT_STRING
	 * @return java.lang.String
	 */
	public static String formatCurrentDate () {
		return new SimpleDateFormat (LONG_TIME_FORMAT_STRING).format(new Date ());
	}
	
	/**
	 * ��ָ��ʱ�䰴format����ĸ�ʽת��
	 * 
	 * @param longDate long ָ��ʱ��ĺ�����
	 * @param format java.lang.String ��ʽ���ַ�
	 * @return java.lang.String
	 */
	public static String formatDate(long longDate,String format) {
		return formatDate(new Date(longDate), format);
	}
	/**
	 * ��ָ��ʱ�䰴format����ĸ�ʽת��
	 * 
	 * @param date java.util.Date ָ��ʱ��
	 * @param format java.lang.String ��ʽ���ַ�
	 * @return java.lang.String
	 */
	public static String formatDate(Date date,String format) {
		if (date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * ��ָ��ʱ�䰴format����ĸ�ʽת��
	 * 
	 * @param date java.util.Date ָ��ʱ��
	 * @param format java.lang.String ��ʽ���ַ�
	 * @return java.lang.String
	 */
	public static String formatDate(Date date) {
		if (date==null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		return sdf.format(date);
	}
	/**
	 * ��õ�ǰϵͳʱ�������
	 * 
	 * @return long
	 */
	public static long getCurrentDate()
	{
		return System.currentTimeMillis();
	}
	/**
	 * ��ָ����ϸ�ʽ��ʱ���ַ�"yyyy-MM-dd HH:mm:ss"�ĸ�ʽת��Ϊ������
	 * 
	 * @param date java.lang.String ָ��ʱ��
	 * @see LONG_TIME_FORMAT_STRING
	 * @return long
	 */
	public static long parseDate(String date) throws ParseException
	{
		if(date==null || date.equals(""))
		{
			return 0L;
		}
		SimpleDateFormat df = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		Date d=df.parse(date);
		return d.getTime();
	}
	/**
	 * ��ָ����ϸ�ʽ��ʱ���ַ�format����ĸ�ʽת��Ϊ������
	 * 
	 * @param date java.lang.String ָ��ʱ��
	 * @param format java.lang.String ��ʽ���ַ�
	 * @return long
	 */
	public static long parseDate(String date, String format) throws ParseException
	{
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d=df.parse(date);
		return d.getTime();
	}
	/**
	 * ��ָ��ʱ��������ʽ��Ϊ"yyyy-MM-dd HH:mm:ss"�ĸ�ʽ���ַ�
	 * 
	 * @param longDate long ָ��ʱ��ĺ�����
	 * @see LONG_TIME_FORMAT_STRING
	 * @return java.lang.String
	 */
	public static String formatDate(Long longDate) throws ParseException
	{
		if(longDate==null)
		{
			return "";
		}
		Date date=new Date(longDate);
		SimpleDateFormat df = new SimpleDateFormat(LONG_TIME_FORMAT_STRING);
		return df.format(date);
	}
	
	/** �������ʱ���־��ȡ */
	public static String hourFlag (String time) {
		return time.substring(0,13); 
	}
	
	/** �������ʱ���־��ȡ */
	public static String dayFlag (String time) {
		return time.substring(0,10); 
	}
	
	/** �������ʱ���־��ȡ */
	public static String monthFlag (String time) {
		return time.substring(0,7); 
	}
	
	/** �������ʱ���־��ȡ */
	public static String yearFlag (String time) {
		return time.substring(0,4); 
	}
	
	public static List<Calendar> getTimeDivList(Calendar startCal, Calendar endCal, int addTimeField,
			int addStep){
		List<Calendar> _return = new ArrayList<Calendar> ();
		for (Calendar loopCal = startCal; !loopCal.after(endCal) ; loopCal.add(addTimeField,addStep)) {
			_return.add((Calendar)loopCal.clone());
		}
		return _return;
	}
	
	/**
	 * 日历控件的短日期字符格式化
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public static String formatCalTime(String dateTime) throws Exception {
		return new SimpleDateFormat(LONG_TIME_FORMAT_STRING).format(new SimpleDateFormat(SHORT_TIME_FORMAT_STRING).parse(dateTime));
	}
	
	/**
	 * 当前日期长字符串格式化
	 * @return
	 */
	public static String formatCurrentTime(){
		return new SimpleDateFormat(LONG_TIME_FORMAT_STRING).format(Calendar.getInstance().getTime());
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException{
		Calendar stCal = Calendar.getInstance();
		stCal.setTimeInMillis(DatetimeHandle.parseDate("2009-01", "yyyy-MM"));
		Calendar edCal = Calendar.getInstance();
		edCal.setTimeInMillis(DatetimeHandle.parseDate("2009-12", "yyyy-MM"));
		List<Calendar> list = getTimeDivList(stCal,edCal,Calendar.MONTH,1);
		
		Iterator<Calendar> ite = list.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next().getTime().toLocaleString());
		}
	}
}
