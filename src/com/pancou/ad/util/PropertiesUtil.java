package com.pancou.ad.util;

import java.io.InputStream;
import java.util.Properties;
import javax.persistence.Entity;

@Entity
public final class PropertiesUtil {
	private static String propertiesName = "Config.properties";
	private static Properties pro = new Properties();
	
	public static final String getProperties(String key){
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName); 
		try {
			pro.load(in);
			in.close();
			return (String)pro.getProperty(key);
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

}
