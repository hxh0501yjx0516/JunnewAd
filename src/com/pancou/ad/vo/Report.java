package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Report100 entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
public class Report implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer reportId;
	private String reportTime;
	private String adplanName;
	private Integer adplanCycleId;
	private String adplanCycleName;
	private Integer adBoxId;
	private String adBoxName;
	private Integer adCreativeId;
	private String adCreativeName;
	private String adCreativeImg;
	private String adCreativeUrl;
	private Integer webMasterId;
	private String webMasterName;
	private Integer urlId;
	private String urlName;
	private Integer userId;
	private String userName;
	private Integer browse;
	private Integer browseTrue;
	private Integer pv;
	private Integer uv;
	private Integer ip;
	private Integer payType;
	private Float webMasterPrice;
	private Float discount;
	private Integer count;
	private Integer countTo;
	private Float money;
	private Integer realCount;
	private Float realMoney;
	private Integer reportStatus;
	private Integer payId;
	private String addTime;

	// Constructors

	/** default constructor */
	public Report() {
	}

	/** minimal constructor */
	public Report(Integer reportId) {
		this.reportId = reportId;
	}

	/** full constructor */
	public Report(Integer reportId, String reportTime, String adplanName,
			Integer adplanCycleId, String adplanCycleName, Integer adBoxId,
			String adBoxName, Integer adCreativeId, String adCreativeName,
			String adCreativeImg, String adCreativeUrl, Integer webMasterId,
			String webMasterName, Integer urlId, String urlName,
			Integer userId, String userName, Integer browse,
			Integer browseTrue, Integer pv, Integer uv, Integer ip,
			Integer payType, Float webMasterPrice, Float discount,
			Integer count, Integer countTo, Float money, Integer realCount,
			Float realMoney, Integer reportStatus, Integer payId, String addTime) {
		this.reportId = reportId;
		this.reportTime = reportTime;
		this.adplanName = adplanName;
		this.adplanCycleId = adplanCycleId;
		this.adplanCycleName = adplanCycleName;
		this.adBoxId = adBoxId;
		this.adBoxName = adBoxName;
		this.adCreativeId = adCreativeId;
		this.adCreativeName = adCreativeName;
		this.adCreativeImg = adCreativeImg;
		this.adCreativeUrl = adCreativeUrl;
		this.webMasterId = webMasterId;
		this.webMasterName = webMasterName;
		this.urlId = urlId;
		this.urlName = urlName;
		this.userId = userId;
		this.userName = userName;
		this.browse = browse;
		this.browseTrue = browseTrue;
		this.pv = pv;
		this.uv = uv;
		this.ip = ip;
		this.payType = payType;
		this.webMasterPrice = webMasterPrice;
		this.discount = discount;
		this.count = count;
		this.countTo = countTo;
		this.money = money;
		this.realCount = realCount;
		this.realMoney = realMoney;
		this.reportStatus = reportStatus;
		this.payId = payId;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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

	public Integer getAdplanCycleId() {
		return this.adplanCycleId;
	}

	public void setAdplanCycleId(Integer adplanCycleId) {
		this.adplanCycleId = adplanCycleId;
	}

	public String getAdplanCycleName() {
		return this.adplanCycleName;
	}

	public void setAdplanCycleName(String adplanCycleName) {
		this.adplanCycleName = adplanCycleName;
	}

	public Integer getAdBoxId() {
		return this.adBoxId;
	}

	public void setAdBoxId(Integer adBoxId) {
		this.adBoxId = adBoxId;
	}

	public String getAdBoxName() {
		return this.adBoxName;
	}

	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}

	public Integer getAdCreativeId() {
		return this.adCreativeId;
	}

	public void setAdCreativeId(Integer adCreativeId) {
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

	public Integer getUrlId() {
		return this.urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

	public String getUrlName() {
		return this.urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
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

	public Integer getBrowse() {
		return this.browse;
	}

	public void setBrowse(Integer browse) {
		this.browse = browse;
	}

	public Integer getBrowseTrue() {
		return this.browseTrue;
	}

	public void setBrowseTrue(Integer browseTrue) {
		this.browseTrue = browseTrue;
	}

	public Integer getPv() {
		return this.pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getUv() {
		return this.uv;
	}

	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Integer getIp() {
		return this.ip;
	}

	public void setIp(Integer ip) {
		this.ip = ip;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Float getWebMasterPrice() {
		return this.webMasterPrice;
	}

	public void setWebMasterPrice(Float webMasterPrice) {
		this.webMasterPrice = webMasterPrice;
	}

	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCountTo() {
		return this.countTo;
	}

	public void setCountTo(Integer countTo) {
		this.countTo = countTo;
	}

	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Integer getRealCount() {
		return this.realCount;
	}

	public void setRealCount(Integer realCount) {
		this.realCount = realCount;
	}

	public Float getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Float realMoney) {
		this.realMoney = realMoney;
	}

	public Integer getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}