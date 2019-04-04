package com.pancou.ad.util.ipseeker;

/** 
 * <pre> 
 * �?��IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP 
 * </pre> 
 */  
public class IPEntry {  
    public String beginIp;  
    public String endIp;  
    public String country;  
    public String area;  
      
    /** 
     * 构�?函数 
     */  
    public IPEntry() {  
        beginIp = endIp = country = area = "";  
    }  
}  