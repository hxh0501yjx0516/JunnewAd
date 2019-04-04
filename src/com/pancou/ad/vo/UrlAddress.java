package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 域名实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class UrlAddress implements Serializable{
	@Id
	@GeneratedValue
	private int urlId;
	private int webMasterId; //网站主id
	private String webMasterName; //网站主名称
	private String urlName; //网站名称
	private String url; //域名
	private String urlICP; //ICP
	private String urlDayIp; //日IP量
	private int urlAlexa; //Alexa排名
	private String urlArea; //所属地区
	private int urlTypeId; //类型ID
	private String urlTypeName; //类型名称
	private int urlStatus; //状态
	
	private String addTime; //添加时间
	private int adBoxCount; //广告位数量
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAdBoxCount() {
		return adBoxCount;
	}
	public void setAdBoxCount(int adBoxCount) {
		this.adBoxCount = adBoxCount;
	}
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public int getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}
	public String getWebMasterName() {
		return webMasterName;
	}
	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlICP() {
		return urlICP;
	}
	public void setUrlICP(String urlICP) {
		this.urlICP = urlICP;
	}
	public String getUrlDayIp() {
		return urlDayIp;
	}
	public void setUrlDayIp(String urlDayIp) {
		this.urlDayIp = urlDayIp;
	}
	public String getUrlArea() {
		return urlArea;
	}
	public void setUrlArea(String urlArea) {
		this.urlArea = urlArea;
	}
	public String getUrlTypeName() {
		return urlTypeName;
	}
	public void setUrlTypeName(String urlTypeName) {
		this.urlTypeName = urlTypeName;
	}
	public int getUrlStatus() {
		return urlStatus;
	}
	public void setUrlStatus(int urlStatus) {
		this.urlStatus = urlStatus;
	}
	public int getUrlAlexa() {
		return urlAlexa;
	}
	public void setUrlAlexa(int urlAlexa) {
		this.urlAlexa = urlAlexa;
	}
	public int getUrlTypeId() {
		return urlTypeId;
	}
	public void setUrlTypeId(int urlTypeId) {
		this.urlTypeId = urlTypeId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}

