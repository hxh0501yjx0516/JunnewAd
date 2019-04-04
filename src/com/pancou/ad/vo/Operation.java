package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Operation entity. @author MyEclipse Persistence Tools
 */

@Entity
@SuppressWarnings("serial")
public class Operation implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer operationId;
	private Integer userId;
	private String remarks;
	private String operationSource;
	private String userName;
	private String addTime;

	// Constructors

	/** default constructor */
	public Operation() {
	}


	// Property accessors

	public Integer getOperationId() {
		return this.operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOperationSource() {
		return this.operationSource;
	}

	public void setOperationSource(String operationSource) {
		this.operationSource = operationSource;
	}


	public String getAddTime() {
		return addTime;
	}


	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


}