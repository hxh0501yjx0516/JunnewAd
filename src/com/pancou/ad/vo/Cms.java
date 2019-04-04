package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 新闻实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class Cms implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int cmsId;
	private int cmsClassId; //分类ID
	private String cmsName; //标题
	private String cmsContent; //内容
	private String cmsImg; //图片
	private int cmsFlag; //标识
	private int cmsStatus; //状态
	private String addTime; //添加时间
	
	private String cmsClassName;
	public String getCmsClassName() {
		return cmsClassName;
	}
	public void setCmsClassName(String cmsClassName) {
		this.cmsClassName = cmsClassName;
	}
	public int getCmsId() {
		return cmsId;
	}
	public void setCmsId(int cmsId) {
		this.cmsId = cmsId;
	}
	public int getCmsClassId() {
		return cmsClassId;
	}
	public void setCmsClassId(int cmsClassId) {
		this.cmsClassId = cmsClassId;
	}
	public String getCmsName() {
		return cmsName;
	}
	public void setCmsName(String cmsName) {
		this.cmsName = cmsName;
	}
	public String getCmsContent() {
		return cmsContent;
	}
	public void setCmsContent(String cmsContent) {
		this.cmsContent = cmsContent;
	}
	public String getCmsImg() {
		return cmsImg;
	}
	public void setCmsImg(String cmsImg) {
		this.cmsImg = cmsImg;
	}
	public int getCmsFlag() {
		return cmsFlag;
	}
	public void setCmsFlag(int cmsFlag) {
		this.cmsFlag = cmsFlag;
	}
	public int getCmsStatus() {
		return cmsStatus;
	}
	public void setCmsStatus(int cmsStatus) {
		this.cmsStatus = cmsStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}

