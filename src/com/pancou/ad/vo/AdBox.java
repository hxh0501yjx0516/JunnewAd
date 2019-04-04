package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ���λʵ�� 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class AdBox implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int adBoxId;
	private String adBoxName; //���λ���
	private int adBoxInfoId; //���ID
	private int webMasterId; //վ��ID
	private int urlId; //����ID
	private int adPlanId; //�ƻ�ID
	private int adPlanCycleId; //�ƻ�����ID
	private int adBoxStatus; //״̬
	private int adBoxFlag; //��ʶ��0-Ĭ�Ϲ��λ��1-��ͨ���λ
	private String addTime; //���ʱ��
	private int isURLBand;//
	
	
	private String adBoxInfoName;
	private String modelName;
	private String sizeName;
	private String webMasterName;
	private String urlName;
	
	public int getIsURLBand() {
		return isURLBand;
	}
	public void setIsURLBand(int isURLBand) {
		this.isURLBand = isURLBand;
	}
	public String getAdBoxInfoName() {
		return adBoxInfoName;
	}
	public void setAdBoxInfoName(String adBoxInfoName) {
		this.adBoxInfoName = adBoxInfoName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getWebMasterName() {
		return webMasterName;
	}
	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public int getAdBoxId() {
		return adBoxId;
	}
	public void setAdBoxId(int adBoxId) {
		this.adBoxId = adBoxId;
	}
	public String getAdBoxName() {
		return adBoxName;
	}
	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}
	public int getAdBoxInfoId() {
		return adBoxInfoId;
	}
	public void setAdBoxInfoId(int adBoxInfoId) {
		this.adBoxInfoId = adBoxInfoId;
	}
	public int getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public int getAdPlanId() {
		return adPlanId;
	}
	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}
	public int getAdPlanCycleId() {
		return adPlanCycleId;
	}
	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}
	public int getAdBoxStatus() {
		return adBoxStatus;
	}
	public void setAdBoxStatus(int adBoxStatus) {
		this.adBoxStatus = adBoxStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getAdBoxFlag() {
		return adBoxFlag;
	}
	public void setAdBoxFlag(int adBoxFlag) {
		this.adBoxFlag = adBoxFlag;
	}

}

