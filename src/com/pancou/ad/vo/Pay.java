package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Pay entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Pay implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer payId; // 0
	private Integer adPlanId;
	private String adPlanName;
	private Integer adPlanCycleId;
	private String adPlanCycleName;
	private Integer webMasterId;
	private String webMasterName;
	private Integer userId;
	private String userName;
	private Integer count;
	private Double money; // 　返点后应收
	private Integer realCount;
	private Double realMoney;
	private Double trueMoney;
	private String remarks;
	private String payBeginTime;
	private String payEndTime;
	private String payAddTime;
	private Double taxNum; // 18th
	private Integer financeFlag;
	private String financeMark;
	private Integer payCompanyType;
	private String payCompany;

	// 20160510 新增
	private Double moneyBeforeRebate; // 返点前应收
	private int moneyReceipt; // 是否已收款
	private int rebate; // 客户返点值
	private String payee; // 收款人

	// Constructors

	/** default constructor */
	public Pay() {
	}

	/** full constructor */
	public Pay(Integer adPlanId, String adPlanName, Integer adPlanCycleId,
			String adPlanCycleName, Integer webMasterId, String webMasterName,
			Integer userId, String userName, Integer count, Double money,
			Integer realCount, Double realMoney, Double trueMoney,
			String remarks, String payBeginTime, String payEndTime,
			String payAddTime, Double taxNum, Integer financeFlag,
			String financeMark, Integer payCompanyType, String payCompany) {
		this.adPlanId = adPlanId;
		this.adPlanName = adPlanName;
		this.adPlanCycleId = adPlanCycleId;
		this.adPlanCycleName = adPlanCycleName;
		this.webMasterId = webMasterId;
		this.webMasterName = webMasterName;
		this.userId = userId;
		this.userName = userName;
		this.count = count;
		this.money = money;
		this.realCount = realCount;
		this.realMoney = realMoney;
		this.trueMoney = trueMoney;
		this.remarks = remarks;
		this.payBeginTime = payBeginTime;
		this.payEndTime = payEndTime;
		this.payAddTime = payAddTime;
		this.taxNum = taxNum;
		this.financeFlag = financeFlag;
		this.financeMark = financeMark;
		this.payCompanyType = payCompanyType;
		this.payCompany = payCompany;
	}

	// Property accessors

	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public Integer getAdPlanId() {
		return this.adPlanId;
	}

	public void setAdPlanId(Integer adPlanId) {
		this.adPlanId = adPlanId;
	}

	public String getAdPlanName() {
		return this.adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public Integer getAdPlanCycleId() {
		return this.adPlanCycleId;
	}

	public void setAdPlanCycleId(Integer adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	public String getAdPlanCycleName() {
		return this.adPlanCycleName;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getRealCount() {
		return this.realCount;
	}

	public void setRealCount(Integer realCount) {
		this.realCount = realCount;
	}

	public Double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public Double getTrueMoney() {
		return this.trueMoney;
	}

	public void setTrueMoney(Double trueMoney) {
		this.trueMoney = trueMoney;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPayBeginTime() {
		return this.payBeginTime;
	}

	public void setPayBeginTime(String payBeginTime) {
		this.payBeginTime = payBeginTime;
	}

	public String getPayEndTime() {
		return this.payEndTime;
	}

	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}

	public String getPayAddTime() {
		return this.payAddTime;
	}

	public void setPayAddTime(String payAddTime) {
		this.payAddTime = payAddTime;
	}

	public Double getTaxNum() {
		return this.taxNum;
	}

	public void setTaxNum(Double taxNum) {
		this.taxNum = taxNum;
	}

	public Integer getFinanceFlag() {
		return this.financeFlag;
	}

	public void setFinanceFlag(Integer financeFlag) {
		this.financeFlag = financeFlag;
	}

	public String getFinanceMark() {
		return this.financeMark;
	}

	public void setFinanceMark(String financeMark) {
		this.financeMark = financeMark;
	}

	public Integer getPayCompanyType() {
		return this.payCompanyType;
	}

	public void setPayCompanyType(Integer payCompanyType) {
		this.payCompanyType = payCompanyType;
	}

	public String getPayCompany() {
		return this.payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public Double getMoneyBeforeRebate() {
		return moneyBeforeRebate;
	}

	public void setMoneyBeforeRebate(Double moneyBeforeRebate) {
		this.moneyBeforeRebate = moneyBeforeRebate;
	}

	public int getMoneyReceipt() {
		return moneyReceipt;
	}

	public void setMoneyReceipt(int moneyReceipt) {
		this.moneyReceipt = moneyReceipt;
	}

	public int getRebate() {
		return rebate;
	}

	public void setRebate(int rebate) {
		this.rebate = rebate;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

}