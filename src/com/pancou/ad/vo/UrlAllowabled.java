package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * �������ʵ�� 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class UrlAllowabled implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int urlId;
	private String urlName; 
	private String url; 
	private int userId; 
	private String userName; 
	private String addTime;

	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	
	public String getUrlName(){
		return urlName;
	}
	public void setUrlName(String urlName){
		this.urlName = urlName;
	}
	
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getAddTime(){
		return addTime;
	}
	public void setAddTime(String addTime){
		this.addTime = addTime;
	}
	
}

