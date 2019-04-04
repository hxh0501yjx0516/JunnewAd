package com.pancou.ad.util.ipparse;

import javax.persistence.Entity;

@Entity
public class IPEntry {
	 public String beginIp;   
	    public String endIp;   
	    public String country;   
	    public String area;   
	       
	    /**  
	     * ���캯��  
	     */   
	    public IPEntry() {   
	        beginIp = endIp = country = area = "";   
	    }   
}
