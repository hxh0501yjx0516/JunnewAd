package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * 投放列表
 * 
 */

@SuppressWarnings("serial")
public class ReadyBox implements Serializable {
	private int readyBoxId; // 投放ID
	private int adBoxId; // 广告位ID
	private String adBoxName;
	private int adCreativeId; // 创意ID
	private String adCreativeName;
	private int adPlanCycleId; // 周期ID
	private String adPlanCycleName;
	private int adPlanId; // 广告计划ID
	private String adPlanName;
	private int urlId; // 域名ID
	private String urlName;
	private int webMasterId; // 站长ID
	private String webMasterName;
	private int payTypeId; // 支付类型ID
	private float webMasterPrice; // 站长单价
	private int discount; // 扣量（以整数操作，如扣量25%，表单值就是25）0-100的数字
	private int showType; // 站长查看列 0-PV 1-UV 2-IP
	private String showIpString; // 日流量控制 日流量控制：如
									// 200,20110501|300,20110502|100,20110503(流量，日期)
									// 日期格式yyyyMMdd
	private int showIp; // 当日流量控制
	private String adCreativeUrl; // 目标地址
	private int isValid; // 是否到量 0-1
	private String adCreativeImg; // 创意图片地址
	private int adWidth; // 宽
	private int adHeight; // 高
	private String modelJS; // 广告模板文件名称
	private int adCreativeLevel; // 创意轮播等级（>=0 并且
									// <100，即0-99）默认创意的等级不能让用户设定，程序自动赋值为0
	private String areaFix; // 地区定向默认为0（全部地区）。比如黑龙江12，哈尔滨101；定位到黑龙江哈尔滨，就用：12.101.多个地区用|分割。如果定位到黑龙江整个省，就用12.*。定位到北京和黑龙江哈尔滨，用：1.*|12.101
	private int readyBoxStatus; // 状态
	private String addTime; // 添加时间
	private int userId; // 媒介ID
	private int isUrlBand;
	private String urlList;
	private String htmlCode;// p4p字段
	private int isP4p;

	private String adBoxInfoName;
	private int isDefault;
	private int waveState; // 波动状态
	private String waveBeginTime; // 波动历史开始日期
	private String waveBDTime; // 波动历史结束日期
	private double wavePercent; // 波动比例

	public double getWavePercent() {
		return wavePercent;
	}

	public void setWavePercent(double wavePercent) {
		this.wavePercent = wavePercent;
	}

	public int getWaveState() {
		return waveState;
	}

	public void setWaveState(int waveState) {
		this.waveState = waveState;
	}

	public String getWaveBeginTime() {
		return waveBeginTime;
	}

	public void setWaveBeginTime(String waveBeginTime) {
		this.waveBeginTime = waveBeginTime;
	}

	public String getWaveBDTime() {
		return waveBDTime;
	}

	public void setWaveBDTime(String waveBDTime) {
		this.waveBDTime = waveBDTime;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public String getAdBoxInfoName() {
		return adBoxInfoName;
	}

	public void setAdBoxInfoName(String adBoxInfoName) {
		this.adBoxInfoName = adBoxInfoName;
	}

	public void setReadyBoxId(int readyBoxId) {
		this.readyBoxId = readyBoxId;
	}

	public int getReadyBoxId() {
		return readyBoxId;
	}

	public void setAdBoxId(int adBoxId) {
		this.adBoxId = adBoxId;
	}

	public int getAdBoxId() {
		return adBoxId;
	}

	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}

	public String getAdBoxName() {
		return adBoxName;
	}

	public void setAdCreativeId(int adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public int getAdCreativeId() {
		return adCreativeId;
	}

	public void setAdCreativeName(String adCreativeName) {
		this.adCreativeName = adCreativeName;
	}

	public String getAdCreativeName() {
		return adCreativeName;
	}

	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	public int getAdPlanCycleId() {
		return adPlanCycleId;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}

	public String getAdPlanCycleName() {
		return adPlanCycleName;
	}

	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}

	public int getAdPlanId() {
		return adPlanId;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public String getAdPlanName() {
		return adPlanName;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}

	public int getWebMasterId() {
		return webMasterId;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}

	public String getWebMasterName() {
		return webMasterName;
	}

	public void setPayTypeId(int payTypeId) {
		this.payTypeId = payTypeId;
	}

	public int getPayTypeId() {
		return payTypeId;
	}

	public void setWebMasterPrice(float webMasterPrice) {
		this.webMasterPrice = webMasterPrice;
	}

	public float getWebMasterPrice() {
		return webMasterPrice;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public int getShowType() {
		return showType;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setShowIpString(String showIpString) {
		this.showIpString = showIpString;
	}

	public String getShowIpString() {
		return showIpString;
	}

	public void setShowIp(int showIp) {
		this.showIp = showIp;
	}

	public int getShowIp() {
		return showIp;
	}

	public void setAdCreativeUrl(String adCreativeUrl) {
		this.adCreativeUrl = adCreativeUrl;
	}

	public String getAdCreativeUrl() {
		return adCreativeUrl;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setAdCreativeImg(String adCreativeImg) {
		this.adCreativeImg = adCreativeImg;
	}

	public String getAdCreativeImg() {
		return adCreativeImg;
	}

	public void setAdWidth(int adWidth) {
		this.adWidth = adWidth;
	}

	public int getAdWidth() {
		return adWidth;
	}

	public void setAdHeight(int adHeight) {
		this.adHeight = adHeight;
	}

	public int getAdHeight() {
		return adHeight;
	}

	public void setModelJS(String modelJS) {
		this.modelJS = modelJS;
	}

	public String getModelJS() {
		return modelJS;
	}

	public void setAdCreativeLevel(int adCreativeLevel) {
		this.adCreativeLevel = adCreativeLevel;
	}

	public int getAdCreativeLevel() {
		return adCreativeLevel;
	}

	public void setAreaFix(String areaFix) {
		this.areaFix = areaFix;
	}

	public String getAreaFix() {
		return areaFix;
	}

	public void setReadyBoxStatus(int readyBoxStatus) {
		this.readyBoxStatus = readyBoxStatus;
	}

	public int getReadyBoxStatus() {
		return readyBoxStatus;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public int getIsUrlBand() {
		return isUrlBand;
	}

	public void setIsUrlBand(int isUrlBand) {
		this.isUrlBand = isUrlBand;
	}

	public String getUrlList() {
		return urlList;
	}

	public void setUrlList(String urlList) {
		this.urlList = urlList;
	}

	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public int getIsP4p() {
		return isP4p;
	}

	public void setIsP4p(int isP4p) {
		this.isP4p = isP4p;
	}

	@Override
	public String toString() {
		return "ReadyBox [readyBoxId=" + readyBoxId + ", adBoxId=" + adBoxId
				+ ", adBoxName=" + adBoxName + ", adCreativeId=" + adCreativeId
				+ ", adCreativeName=" + adCreativeName + ", adPlanCycleId="
				+ adPlanCycleId + ", adPlanCycleName=" + adPlanCycleName
				+ ", adPlanId=" + adPlanId + ", adPlanName=" + adPlanName
				+ ", urlId=" + urlId + ", urlName=" + urlName
				+ ", webMasterId=" + webMasterId + ", webMasterName="
				+ webMasterName + ", payTypeId=" + payTypeId
				+ ", webMasterPrice=" + webMasterPrice + ", discount="
				+ discount + ", showType=" + showType + ", showIpString="
				+ showIpString + ", showIp=" + showIp + ", adCreativeUrl="
				+ adCreativeUrl + ", isValid=" + isValid + ", adCreativeImg="
				+ adCreativeImg + ", adWidth=" + adWidth + ", adHeight="
				+ adHeight + ", modelJS=" + modelJS + ", adCreativeLevel="
				+ adCreativeLevel + ", areaFix=" + areaFix
				+ ", readyBoxStatus=" + readyBoxStatus + ", addTime=" + addTime
				+ ", userId=" + userId + ", isUrlBand=" + isUrlBand
				+ ", urlList=" + urlList + ", htmlCode=" + htmlCode
				+ ", isP4p=" + isP4p + ", adBoxInfoName=" + adBoxInfoName
				+ ", isDefault=" + isDefault + ", waveState=" + waveState
				+ ", waveBeginTime=" + waveBeginTime + ", waveBDTime="
				+ waveBDTime + ", wavePercent=" + wavePercent + "]";
	}

}
