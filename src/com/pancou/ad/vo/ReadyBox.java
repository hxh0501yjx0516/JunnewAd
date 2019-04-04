package com.pancou.ad.vo;

import java.io.Serializable;

/**
 * Ͷ���б�
 * 
 */

@SuppressWarnings("serial")
public class ReadyBox implements Serializable {
	private int readyBoxId; // Ͷ��ID
	private int adBoxId; // ���λID
	private String adBoxName;
	private int adCreativeId; // ����ID
	private String adCreativeName;
	private int adPlanCycleId; // ����ID
	private String adPlanCycleName;
	private int adPlanId; // ���ƻ�ID
	private String adPlanName;
	private int urlId; // ����ID
	private String urlName;
	private int webMasterId; // վ��ID
	private String webMasterName;
	private int payTypeId; // ֧������ID
	private float webMasterPrice; // վ������
	private int discount; // �����������������������25%����ֵ����25��0-100������
	private int showType; // վ���鿴�� 0-PV 1-UV 2-IP
	private String showIpString; // ���������� ���������ƣ���
									// 200,20110501|300,20110502|100,20110503(����������)
									// ���ڸ�ʽyyyyMMdd
	private int showIp; // ������������
	private String adCreativeUrl; // Ŀ���ַ
	private int isValid; // �Ƿ��� 0-1
	private String adCreativeImg; // ����ͼƬ��ַ
	private int adWidth; // ��
	private int adHeight; // ��
	private String modelJS; // ���ģ���ļ�����
	private int adCreativeLevel; // �����ֲ��ȼ���>=0 ����
									// <100����0-99��Ĭ�ϴ���ĵȼ��������û��趨�������Զ���ֵΪ0
	private String areaFix; // ��������Ĭ��Ϊ0��ȫ�������������������12��������101����λ�������������������ã�12.101.���������|�ָ�����λ������������ʡ������12.*����λ�������ͺ��������������ã�1.*|12.101
	private int readyBoxStatus; // ״̬
	private String addTime; // ���ʱ��
	private int userId; // ý��ID
	private int isUrlBand;
	private String urlList;
	private String htmlCode;// p4p�ֶ�
	private int isP4p;

	private String adBoxInfoName;
	private int isDefault;
	private int waveState; // ����״̬
	private String waveBeginTime; // ������ʷ��ʼ����
	private String waveBDTime; // ������ʷ��������
	private double wavePercent; // ��������

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
