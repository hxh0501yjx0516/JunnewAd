package com.pancou.ad.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 广告主实体
 * 
 * @author tmb
 * 
 */
@Entity
public class Customer implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int customerId;
	private String customerName; // 广告主名
	private String customerPassword;
	private String customerType; // 客户行业类型
	private String customerContactName; // 联系人
	private String customerContactMobile; // 联系人电话
	private String customerAddress; // 联系地址
	private String customerPost; // 邮编
	private String customerUrl; // 网址
	private int customerStatus; // 状态
	private String addTime; // 添加时间
	private int userId;
	private String userName;
	private String payee; // 收款人
	private int planCount;
	private int planCycleCount;

	private int userGroup;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPlanCount() {
		return planCount;
	}

	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}

	public int getPlanCycleCount() {
		return planCycleCount;
	}

	public void setPlanCycleCount(int planCycleCount) {
		this.planCycleCount = planCycleCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerContactName() {
		return customerContactName;
	}

	public void setCustomerContactName(String customerContactName) {
		this.customerContactName = customerContactName;
	}

	public String getCustomerContactMobile() {
		return customerContactMobile;
	}

	public void setCustomerContactMobile(String customerContactMobile) {
		this.customerContactMobile = customerContactMobile;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPost() {
		return customerPost;
	}

	public void setCustomerPost(String customerPost) {
		this.customerPost = customerPost;
	}

	public String getCustomerUrl() {
		return customerUrl;
	}

	public void setCustomerUrl(String customerUrl) {
		this.customerUrl = customerUrl;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public int getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}
}
