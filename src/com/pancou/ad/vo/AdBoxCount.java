package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * AdBoxCount entity. @author MyEclipse Persistence Tools
 */

@Entity
public class AdBoxCount implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int adBoxCountId;
	private int readyBoxId;
	private int browse;
	private int browseTrue;
	private int pv;
	private int uv;
	private int ip;
	private String adBoxCountTime;
	
	private String webMasterName;
	private String userName;
	private String url;
	private String adPlanName;
	private String adPlanCycleName;
	
	private int allBrowse;
	private int allBrowseTrue;
	private int allPv;
	private int allUv;
	private int allIp;
	


	// Property accessors

	public int getAdBoxCountId() {
		return this.adBoxCountId;
	}

	public void setAdBoxCountId(int adBoxCountId) {
		this.adBoxCountId = adBoxCountId;
	}

	public int getReadyBoxId() {
		return this.readyBoxId;
	}

	public void setReadyBoxId(int readyBoxId) {
		this.readyBoxId = readyBoxId;
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

	public String getAdBoxCountTime() {
		return this.adBoxCountTime;
	}

	public void setAdBoxCountTime(String adBoxCountTime) {
		this.adBoxCountTime = adBoxCountTime;
	}
	
	public String getWebMasterName() {
		return this.webMasterName;
	}

	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getAdPlanName() {
		return this.adPlanName;
	}

	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}
	
	public String getAdPlanCycleName() {
		return this.adPlanCycleName;
	}

	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}
	
	public int getAllBrowse() {
		return this.allBrowse;
	}

	public void setAllBrowse(int allBrowse) {
		this.allBrowse = allBrowse;
	}
	
	public int getAllBrowseTrue() {
		return this.allBrowseTrue;
	}

	public void setAllBrowseTrue(int allBrowseTrue) {
		this.allBrowseTrue = allBrowseTrue;
	}
	
	public int getAllPv() {
		return this.allPv;
	}

	public void setAllPv(int allPv) {
		this.allPv = allPv;
	}
	
	public int getAllUv() {
		return this.allUv;
	}

	public void setAllUv(int allUv) {
		this.allUv = allUv;
	}
	
	public int getAllIp() {
		return this.allIp;
	}

	public void setAllIp(int allIp) {
		this.allIp = allIp;
	}



}