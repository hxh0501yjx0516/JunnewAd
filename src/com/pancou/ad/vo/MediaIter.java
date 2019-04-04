package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * MediaIter entity. @author MyEclipse Persistence Tools
 */

@Entity
public class MediaIter implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer mediaIterId;
	private Integer userId;
	private String userName;
	private Integer webMasterId;
	private String webMasterName;
	private String mediaFlag;
	private String urlFlag;
	private Integer customerId;
	private String customerName;
	private String customerFlag;

	// Constructors

	/** default constructor */
	public MediaIter() {
	}

	/** full constructor */
	public MediaIter(Integer userId, String userName, Integer webMasterId,
			String webMasterName, String mediaFlag, String urlFlag,
			Integer customerId, String customerName, String customerFlag) {
		this.userId = userId;
		this.userName = userName;
		this.webMasterId = webMasterId;
		this.webMasterName = webMasterName;
		this.mediaFlag = mediaFlag;
		this.urlFlag = urlFlag;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerFlag = customerFlag;
	}

	// Property accessors

	public Integer getMediaIterId() {
		return this.mediaIterId;
	}

	public void setMediaIterId(Integer mediaIterId) {
		this.mediaIterId = mediaIterId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getWebMasterId() {
		return this.webMasterId;
	}

	public void setWebMasterId(Integer webMasterId) {
		this.webMasterId = webMasterId;
	}

	public String getWebMasterName() {
		return this.webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}

	public String getMediaFlag() {
		return this.mediaFlag;
	}

	public void setMediaFlag(String mediaFlag) {
		this.mediaFlag = mediaFlag;
	}

	public String getUrlFlag() {
		return this.urlFlag;
	}

	public void setUrlFlag(String urlFlag) {
		this.urlFlag = urlFlag;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerFlag() {
		return this.customerFlag;
	}

	public void setCustomerFlag(String customerFlag) {
		this.customerFlag = customerFlag;
	}

}