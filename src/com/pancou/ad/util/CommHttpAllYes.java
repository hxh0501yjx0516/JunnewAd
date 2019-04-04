package com.pancou.ad.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.persistence.Entity;

/**
 * <p>
 * Title: 通过http协议和外部通讯的类
 * </p>
 * <p>
 * Description: 通过GET方法将参数传给外部系统,返回系统想要的结果 key=value<回车换行>key=value<回车换行> ...
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
@Entity
public class CommHttpAllYes {

	public synchronized Properties send(String strUrl) {
		Properties prop = new Properties();
		String strurl = strUrl;

		URL url = null;
		HttpURLConnection urlConn = null;
		byte[] buf = new byte[1024 * 4];

		try {
			url = new URL(strurl);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		InputStream bis = null;
		String result = "";
		long starttime, endtime;
		starttime = System.currentTimeMillis();
		try {
			System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
			System.setProperty("sun.net.client.defaultReadTimeout", "60000");

			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.getResponseCode();

			bis = urlConn.getInputStream();
			int len = 0;
			StringBuffer sb = new StringBuffer();
			while ((len = bis.read(buf, 0, buf.length)) != -1) {
				String str = new String(buf, 0, len);
				if (str != null && !str.trim().equals(""))
					sb.append(str.trim());
			}
			endtime = System.currentTimeMillis();

			result = sb.toString().toLowerCase();
			if (result.indexOf("result:true") != -1
					|| result.indexOf("ok") != -1) {
				prop.put("result", "0");
				prop.put("desc", "成功");
			} else {
				prop.put("result", "1");
				prop.put("desc", "失败");
			}
			// log.info(strurl + "\tresp: " +
			// result+"\t"+(endtime-starttime)+" ms");

		} catch (Exception e) {
			endtime = System.currentTimeMillis();
			// log.error(strurl + "\tresp: " +
			// e.getMessage()+"\t"+(endtime-starttime)+" ms");
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bis != null)
					bis.close();
				bis = null;
			} catch (Exception e) {
			}
			try {
				if (urlConn != null)
					urlConn.disconnect();
				urlConn = null;
			} catch (Exception e) {
			}

		}
		return prop;
	}

	private String getProp(Properties prop) {
		String strRet = "";
		if (prop != null) {
			Enumeration keys = prop.keys();
			while (keys.hasMoreElements()) {
				String tmpKey = (String) keys.nextElement();
				String tmpValue = (String) prop.get(tmpKey);
				strRet += " " + tmpKey + "=" + tmpValue;
			}
		}
		return strRet;
	}

	public static void main(String[] args) {
		CommHttpAllYes comm = new CommHttpAllYes();
		String url = "http://localhost:8080/webtest/httptest";
		String param = "termid=123123";
		comm.send(url);
	}

}
