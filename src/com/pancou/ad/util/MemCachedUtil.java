package com.pancou.ad.util;

import javax.persistence.Entity;


@Entity
public class MemCachedUtil {
	/*
	 * remove��Ⱥ���������κ�һ̨����mem��ͬһ��key
	 * */
	public static synchronized boolean removeAll(String key) {
		System.out.println("removeAll(String key):"+key);
		boolean b1=MemCachedUtil_110.remove(key);
		try {
		      Thread.currentThread().sleep(9000L);
		      System.out.println("sleep 9'");
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }	
		boolean b2=MemCachedUtil_45.remove(key);
		
		
		
		System.out.println("110mem update:key("+key+")"+b1);
		System.out.println("45mem update:key("+key+")"+b2);
		return (b1 && b2);
	}
	public static void main(String[] args) throws Exception {
		String key="24_36";
		System.out.println(MemCachedUtil.removeAll(key));

	}
}
