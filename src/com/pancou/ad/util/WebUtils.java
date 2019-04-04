package com.pancou.ad.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.servlet.ServletRequest;
@Entity
public class WebUtils {
	
	private static final String BEFORE_ENCODE="ISO8859-1";
	private static final String AFTER_ENCODE="GBK";
	private static final String UTF_ENCODE="UTF-8";
	@SuppressWarnings("unchecked")
	public static HashMap getParametersStartingWith(ServletRequest request, String prefix) {
		Enumeration paramNames = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				//
				if(values != null) {
					String encodedVal = "";
					int iLen = values.length;
					for(int i = 0; i < iLen; i ++) {
//						if(Charset.forName(BEFORE_ENCODE).newEncoder().canEncode(values[i])){
//							encodedVal= encode(values[i],AFTER_ENCODE,BEFORE_ENCODE);
//						}else if(Charset.forName(AFTER_ENCODE).newEncoder().canEncode(values[i])){
//							encodedVal= encode(values[i],UTF_ENCODE,AFTER_ENCODE);
//							
//						}else if(Charset.forName(UTF_ENCODE).newEncoder().canEncode(values[i])){
//							encodedVal= encode(values[i],AFTER_ENCODE,UTF_ENCODE);
//						}else{ 
//						//���ı���
//						encodedVal = values[i];
//						}
						try {
							if(i == iLen-1){
								encodedVal += URLDecoder.decode(values[i] ,UTF_ENCODE);
							}else{
								encodedVal += URLDecoder.decode(values[i] ,UTF_ENCODE)+",";
							}
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					params.put(unprefixed, encodedVal.trim());
				}
			}
		}
		return params;
	}
	
	public static String getParameter(ServletRequest request, String name) {
		String value = request.getParameter(name);
		value = encode(value,AFTER_ENCODE,BEFORE_ENCODE);
		return value;
	}
	/**
	 * encode
	 * 
	 * @param pStr
	 * @param newCode
	 * @param oldCode
	 * @return
	 */
	public static String encode(String pStr, String newCode, String oldCode) {
		try {
			if(pStr == null) {
				return "";
			}
			else {
				pStr = new String(pStr.getBytes(oldCode),newCode );
			}
		}
		catch (Exception e) {
		}
		return pStr;
	}
	
}