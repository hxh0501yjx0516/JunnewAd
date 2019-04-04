package com.pancou.ad.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;


@Entity
public class Common extends DispatchAction{
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * �ֻ������֤
	 * @param listM
	 * @return
	 */
	public static boolean checkMobileList(String mobile) //�ֻ�����ʽ��֤
	{
		boolean blRet=false;
		Matcher m = null;
		/** 
		Pattern mobileRegExp = Pattern
				.compile( "^(13[0-9]|15[0-9]|18[8|9])\\d{8}$" );
		m = mobileRegExp.matcher(mobile);*/
		// ��֤11λ����
		Pattern mobileRegExp = Pattern.compile( "^\\d{11}$" );
		m = mobileRegExp.matcher(mobile);
		
		if (!m.matches()) {
			blRet = false;
//			System.out.println(mobile+"error");
		} else {
			blRet = true;			
		}
		return blRet;
		//return true;
	}
	
	/**
	 * �ֻ������֤
	 * @param listM
	 * @return
	 */
	public static boolean checkMobileList(String[] mobile) //�ֻ�����ʽ��֤
	{
		boolean blRet=false;
		Matcher m = null;
		 
//		Pattern mobileRegExp = Pattern
//				.compile( "^(13[0-9]|15[0-9]|18[8|9])\\d{8}$" );
		// ��֤11����
		Pattern mobileRegExp = Pattern.compile( "^\\d{11}$" );
		
		for(int i = 0; i < mobile.length; i ++){
			m = mobileRegExp.matcher(mobile[i]);			
			if (!m.matches()) {
				blRet = false;
				return blRet;
			} 
		}
		blRet = true;
		return blRet;
//		return true;
	}
	


	
	/**
	* ɾ����ļ�
	* @param fileName Ҫɾ����ļ����ļ���
	* @return �����ļ�ɾ��ɹ�����true�����򷵻�false
	*/
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// ����ļ�·�����Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
//				System.out.println("ɾ����ļ�" + fileName + "�ɹ���");
				return true;
			} else {
				System.out.println("ɾ����ļ�" + fileName + "ʧ�ܣ�");
				return false;
			}
		} else {
			System.out.println("ɾ����ļ�ʧ�ܣ�" + fileName + "�����ڣ�");
			return false;
		}
		
	}


	public static void main(String[] args) {
		Common com = new Common();
		String[] list = new String[3];
		list[0]="13236675300";
		list[1]="13136675300";
		list[2]="13323667530";
		System.out.println(com.checkMobileList(list));
		
	}
}
