package com.pancou.ad.util.ipparse;

import javax.persistence.Entity;


@Entity
public class IPtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testIp();
	}
	 public static void testIp(){
         //ָ��������ݿ���ļ��������ļ��� 
	 IPSeeker ip=new IPSeeker("qqwry.dat","e:/qqwry");
	  //����IP 119.161.167.82 	 ������ �����׿Ƶ�Ѷ���޹�˾ ������ �й��
	 System.out.println(ip.getIPLocation("114.250.47.33").getCountry()+":"+ip.getIPLocation("114.250.47.33").getArea());
	 }
	 
	}
