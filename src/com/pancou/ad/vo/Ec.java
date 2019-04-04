package com.pancou.ad.vo;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Ec entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Ec implements java.io.Serializable {

	// Fields

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProNum() {
		return proNum;
	}

	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}

	@Id
	@GeneratedValue
	private Integer id;
	private String customerInfo;
	private String urlFlag;
	private String orderId;
	private String productName;
	private String orderStatus;
	private String amount;
	private Double allAmount;
	private Timestamp addTime;
	
	private String proName;
	private Integer proNum;
	// Constructors

	/** default constructor */
	public Ec() {
	}

	/** full constructor */
	public Ec(String customerInfo, String urlFlag, String orderId,
			String productName, String orderStatus, String amount,
			Double allAmount, Timestamp addTime,String proName,Integer proNum) {
		this.customerInfo = customerInfo;
		this.urlFlag = urlFlag;
		this.orderId = orderId;
		this.productName = productName;
		this.orderStatus = orderStatus;
		this.amount = amount;
		this.allAmount = allAmount;
		this.addTime = addTime;
		this.productName = proName;
		this.proNum = proNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getUrlFlag() {
		return this.urlFlag;
	}

	public void setUrlFlag(String urlFlag) {
		this.urlFlag = urlFlag;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Double getAllAmount() {
		return this.allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}