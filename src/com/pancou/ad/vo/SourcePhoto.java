package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * SourcePhoto entity. @author MyEclipse Persistence Tools
 */

@Entity
@SuppressWarnings("serial")
public class SourcePhoto implements java.io.Serializable {


	@Id
	@GeneratedValue
	private Integer id;
	private Integer adBoxId;
	private Integer adCreativeId;
	private Integer readyBoxId;
	private Integer webMasterId;
	private Integer urlId;
	private String source;
	private Integer sourType;
	private String photoName;
	private String insertTime;
	private String path;

	private String adCreativeName;
	private String webMasterName;
	private String url;
	private String adBoxName;
	private String adPlanCycleName;
	private String adPlanName;

	public SourcePhoto() {
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdBoxId() {
		return this.adBoxId;
	}

	public void setAdBoxId(Integer adBoxId) {
		this.adBoxId = adBoxId;
	}

	public Integer getAdCreativeId() {
		return this.adCreativeId;
	}

	public void setAdCreativeId(Integer adCreativeId) {
		this.adCreativeId = adCreativeId;
	}

	public Integer getReadyBoxId() {
		return this.readyBoxId;
	}

	public void setReadyBoxId(Integer readyBoxId) {
		this.readyBoxId = readyBoxId;
	}

	public Integer getWebMasterId() {
		return this.webMasterId;
	}

	public void setWebMasterId(Integer webMasterId) {
		this.webMasterId = webMasterId;
	}

	public Integer getUrlId() {
		return this.urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSourType() {
		return this.sourType;
	}

	public void setSourType(Integer sourType) {
		this.sourType = sourType;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}


	public String getInsertTime() {
		return insertTime;
	}


	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}



	public String getAdCreativeName() {
		return adCreativeName;
	}



	public void setAdCreativeName(String adCreativeName) {
		this.adCreativeName = adCreativeName;
	}



	public String getWebMasterName() {
		return webMasterName;
	}



	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getAdBoxName() {
		return adBoxName;
	}



	public void setAdBoxName(String adBoxName) {
		this.adBoxName = adBoxName;
	}



	public String getAdPlanCycleName() {
		return adPlanCycleName;
	}



	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}



	public String getAdPlanName() {
		return adPlanName;
	}



	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}


}