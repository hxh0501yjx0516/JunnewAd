package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 广告计划与媒体对应实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class ReadyPlan implements Serializable{
	@Id
	@GeneratedValue
	private int readyPlanId;
	private int adPlanId; //广告计划ID
	private String adPlanName; //计划名称
	private int webMasterId; //站长ID
	private String webMasterName; //站长名称
	private int readyPlanStatus;//状态
	private String addTime; //添加时间
	public int getReadyPlanId() {
		return readyPlanId;
	}
	public void setReadyPlanId(int readyPlanId) {
		this.readyPlanId = readyPlanId;
	}
	public int getAdPlanId() {
		return adPlanId;
	}
	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}
	public String getAdPlanName() {
		return adPlanName;
	}
	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
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
	public int getReadyPlanStatus() {
		return readyPlanStatus;
	}
	public void setReadyPlanStatus(int readyPlanStatus) {
		this.readyPlanStatus = readyPlanStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}

