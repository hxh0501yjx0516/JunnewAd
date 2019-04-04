package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * AdCreative entity. @author MyEclipse Persistence Tools
 */

@Entity
@SuppressWarnings("serial")
public class AdCreative implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer adCreativeId;
	private Integer adPlanId;
	private Integer adPlanCycleId;
	private String adCreativeName;
	private Integer adSizeId;
	private String adCreativeImg;
	private String adCreativeUrl;
	private Integer adCreativeStatus;
	private String addTime;
	private Integer isDefault;
	
	private String adSizeName;
	private String adPlanName;
	private String adPlanCycleName;
	private String adBoxInfoName;
	private String htmlCode;//p4p�ֶ�
	private int 	isP4p;

	// Constructors

	/** default constructor */
	public AdCreative() {
	}

	// Property accessors

	public Integer getAdCreativeId() {
		return this.adCreativeId;
	}

	public String getAdBoxInfoName() {
		return adBoxInfoName;
	}

	public void setAdBoxInfoName(String adBoxInfoName) {
		this.adBoxInfoName = adBoxInfoName;
	}

	public void setAdCreativeId(Integer adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public Integer getAdPlanId() {
		return this.adPlanId;
	}

	public void setAdPlanId(Integer adPlanId) {
		this.adPlanId = adPlanId;
	}

	public Integer getAdPlanCycleId() {
		return this.adPlanCycleId;
	}

	public void setAdPlanCycleId(Integer adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	public String getAdCreativeName() {
		return this.adCreativeName;
	}

	public void setAdCreativeName(String adCreativeName) {
		this.adCreativeName = adCreativeName;
	}

	public Integer getAdSizeId() {
		return this.adSizeId;
	}

	public void setAdSizeId(Integer adSizeId) {
		this.adSizeId = adSizeId;
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

	public Integer getAdCreativeStatus() {
		return this.adCreativeStatus;
	}

	public void setAdCreativeStatus(Integer adCreativeStatus) {
		this.adCreativeStatus = adCreativeStatus;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getAdSizeName() {
		return adSizeName;
	}

	public void setAdSizeName(String adSizeName) {
		this.adSizeName = adSizeName;
	}

	public String getAdPlanName() {
		return adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}

	public String getAdPlanCycleName() {
		return adPlanCycleName;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
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

}