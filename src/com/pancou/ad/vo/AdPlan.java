package com.pancou.ad.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 广告计划实体
 * 
 * @author tmb
 * 
 */
@Entity
@SuppressWarnings("serial")
public class AdPlan implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int adPlanId;
	private int customerId;
	private String adPlanName;
	private int adPlanStatus;
	private int adPlanFlag;
	private String addTime;
	private String adPlanImg;
	private int cycleCount;
	private String customerName;
	private int userGroup;

	// 　20160510 新增　客户返点
	private int adPlanRebate;

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	public String getAdPlanImg() {
		return adPlanImg;
	}

	public void setAdPlanImg(String adPlanImg) {
		this.adPlanImg = adPlanImg;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCycleCount() {
		return cycleCount;
	}

	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}

	public int getAdPlanId() {
		return adPlanId;
	}

	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAdPlanName() {
		return adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public int getAdPlanStatus() {
		return adPlanStatus;
	}

	public void setAdPlanStatus(int adPlanStatus) {
		this.adPlanStatus = adPlanStatus;
	}

	public int getAdPlanFlag() {
		return adPlanFlag;
	}

	public void setAdPlanFlag(int adPlanFlag) {
		this.adPlanFlag = adPlanFlag;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getAdPlanRebate() {
		return adPlanRebate;
	}

	public void setAdPlanRebate(int rebate) {
		this.adPlanRebate = rebate;
	}
}
