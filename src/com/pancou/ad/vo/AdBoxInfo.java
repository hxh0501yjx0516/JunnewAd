package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ���λ���ʵ��
 * @author tmb
 *
 */

@Entity
@SuppressWarnings("serial")
public class AdBoxInfo implements java.io.Serializable {


	@Id
	@GeneratedValue
	private int adBoxInfoId;
	private String adBoxInfoName;
	private int adSizeId;
	private int modelId;
	private String adBoxInfoRemarks;
	private int adBoxInfoStatus;
	private String addTime;
	
	private String modelName;
	private String adSizeName;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getAdSizeName() {
		return adSizeName;
	}
	public void setAdSizeName(String adSizeName) {
		this.adSizeName = adSizeName;
	}
	public int getAdBoxInfoId() {
		return adBoxInfoId;
	}
	public void setAdBoxInfoId(int adBoxInfoId) {
		this.adBoxInfoId = adBoxInfoId;
	}
	public String getAdBoxInfoName() {
		return adBoxInfoName;
	}
	public void setAdBoxInfoName(String adBoxInfoName) {
		this.adBoxInfoName = adBoxInfoName;
	}
	public int getAdSizeId() {
		return adSizeId;
	}
	public void setAdSizeId(int adSizeId) {
		this.adSizeId = adSizeId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getAdBoxInfoRemarks() {
		return adBoxInfoRemarks;
	}
	public void setAdBoxInfoRemarks(String adBoxInfoRemarks) {
		this.adBoxInfoRemarks = adBoxInfoRemarks;
	}
	public int getAdBoxInfoStatus() {
		return adBoxInfoStatus;
	}
	public void setAdBoxInfoStatus(int adBoxInfoStatus) {
		this.adBoxInfoStatus = adBoxInfoStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


}