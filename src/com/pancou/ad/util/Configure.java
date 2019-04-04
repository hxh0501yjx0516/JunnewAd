package com.pancou.ad.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.persistence.Entity;


@Entity
public final class Configure {
	
	private static Properties props = new Properties ();
	
	private static String chineseCode(String value) throws UnsupportedEncodingException {
		return new String (value.getBytes("ISO-8859-1"), "UTF-8");
	}
	
	static {
		InputStream in = Configure.class.getClassLoader().getResourceAsStream("configure.properties");
		try {
			props.load(in);
			in.close();
			
			System.out.println("init Configure by configure.properties");
			Iterator<Object> iter = props.keySet().iterator();
			while (iter.hasNext()) {
				String name = (String) iter.next();
				//System.out.println(name+"="+chineseCode(props.getProperty(name)));
			}
			//System.out.println("init Configure over.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static final String get(String key) {
		try {
			
			String rs=chineseCode(props.getProperty(key, ""));
			return rs;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static final List<String> getProperties(String key) {
		List<String> _result = new ArrayList<String>();
		Iterator<Object> iter = props.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			if (name.startsWith(key))
				try {
					_result.add(chineseCode(props.getProperty(name)));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		}
		return _result;
	}
	public static final List<String> getLastProperties(String key) {
		List<String> _result = new ArrayList<String>();
		Iterator<Object> iter = props.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			if (name.startsWith(key))
				try {
					_result.add(chineseCode(name.substring(name.lastIndexOf(".")+1).trim()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		}
		return _result;
	}
	public static void main(String[] args) throws ClassNotFoundException {
//		Class.forName("com.suplus.environment.Configure");
		getLastProperties("system.table");
	}
}
