package com.pancou.ad.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ReportCount entity. @author MyEclipse Persistence Tools
 */

@Entity
@SuppressWarnings("serial")
public class ReportCount implements Serializable {

	private int reportId;
	private int readyBoxId;
	private String reportTime;
	private int adplanId;
	private String adplanName;
	private int adplanCycleId;
	private String adplanCycleName;
	private int adBoxId;
	private String adBoxName;
	private int adCreativeId;
	private String adCreativeName;
	private String adCreativeImg;
	private String adCreativeUrl;
	private int webMasterId;
	private String webMasterName;
	private int urlId;
	private String urlName;
	private int userId;
	private String userName;
	private int browse;
	private int browseTrue;
	private int pv;
	private int uv;
	private int ip;
	private int payType;
	private float webMasterPrice;
	private float discount;
	private int count;
	private int countTo;
	private float money;
	private int realCount;
	private float realMoney;
	private int reportStatus;

	private String remarks;

	// 20160510　新增
	private int adPlanRebate; // 客户返点
	private float moneyRebate; // 实收
	private String payee; // 收款人

	@Id
	@GeneratedValue
	private int payId;
	private String addTime;

	private float profit;
	private float profitRate;

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(float profitRate) {
		this.profitRate = profitRate;
	}

	public int getAdplanId() {
		return adplanId;
	}

	public void setAdplanId(int adplanId) {
		this.adplanId = adplanId;
	}

	public int getReportId() {
		return this.reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getReadyBoxId() {
		return readyBoxId;
	}

	public void setReadyBoxId(int readyBoxId) {
		this.readyBoxId = readyBoxId;
	}

	public String getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getAdplanName() {
		return this.adplanName;
	}

	public void setAdplanName(String adplanName) {
		this.adplanName = adplanName;
	}

	public int getAdplanCycleId() {
		return this.adplanCycleId;
	}

	public void setAdplanCycleId(int adplanCycleId) {
		this.adplanCycleId = adplanCycleId;
	}

	public String getAdplanCycleName() {
		return this.adplanCycleName;
	}

	public void setAdplanCycleName(String adplanCycleName) {
		this.adplanCycleName = adplanCycleName;
	}

	public int getAdBoxId() {
		return this.adBoxId;
	}

	public void setAdBoxId(int adBoxId) {
		this.adBoxId = adBoxId;
	}

	public String getAdBoxName() {
		return this.adBoxName;
	}

	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}

	public int getAdCreativeId() {
		return this.adCreativeId;
	}

	public void setAdCreativeId(int adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public String getAdCreativeName() {
		return this.adCreativeName;
	}

	public void setAdCreativeName(String adCreativeName) {
		this.adCreativeName = adCreativeName;
	}

	public String getAdCreativeImg() {
		return this.adCreativeImg;
	}

	public void setAdCreativeImg(String adCreativeImg) {
		this.adCreativeImg = adCreativeImg;
	}

	public String getAdCreativeUrl() {
		return this.adCreativeUrl;
	}

	public void setAdCreativeUrl(String adCreativeUrl) {
		this.adCreativeUrl = adCreativeUrl;
	}

	public int getWebMasterId() {
		return this.webMasterId;
	}

	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}

	public String getWebMasterName() {
		return this.webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUrlId() {
		return this.urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getUrlName() {
		return this.urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public int getBrowse() {
		return this.browse;
	}

	public void setBrowse(int browse) {
		this.browse = browse;
	}

	public int getBrowseTrue() {
		return this.browseTrue;
	}

	public void setBrowseTrue(int browseTrue) {
		this.browseTrue = browseTrue;
	}

	public int getPv() {
		return this.pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return this.uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getIp() {
		return this.ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public int getPayType() {
		return this.payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public float getWebMasterPrice() {
		return this.webMasterPrice;
	}

	public void setWebMasterPrice(float webMasterPrice) {
		this.webMasterPrice = webMasterPrice;
	}

	public float getDiscount() {
		return this.discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountTo() {
		return this.countTo;
	}

	public void setCountTo(int countTo) {
		this.countTo = countTo;
	}

	public float getMoney() {
		return this.money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getRealCount() {
		return this.realCount;
	}

	public void setRealCount(int realCount) {
		this.realCount = realCount;
	}

	public float getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(float realMoney) {
		this.realMoney = realMoney;
	}

	public int getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getPayId() {
		return this.payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getAdPlanRebate() {
		return adPlanRebate;
	}

	public void setAdPlanRebate(int adPlanRebate) {
		this.adPlanRebate = adPlanRebate;
	}

	public Float getMoneyRebate() {
		return this.moneyRebate;
	}

	public void setMoneyRebate(float moneyRebate) {
		this.moneyRebate = moneyRebate;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

}