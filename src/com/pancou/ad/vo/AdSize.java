package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * AdSize entity. @author MyEclipse Persistence Tools
 */

@Entity
public class AdSize implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer adSizeId;
	private Integer adWidth;
	private Integer adHeight;
	private Integer adSizeStatus;

	// Constructors

	/** default constructor */
	public AdSize() {
	}

	/** minimal constructor */
	public AdSize(Integer adSizeId) {
		this.adSizeId = adSizeId;
	}

	/** full constructor */
	public AdSize(Integer adSizeId, Integer adWidth, Integer adHeight,
			Integer adSizeStatus) {
		this.adSizeId = adSizeId;
		this.adWidth = adWidth;
		this.adHeight = adHeight;
		this.adSizeStatus = adSizeStatus;
	}

	// Property accessors

	public Integer getAdSizeId() {
		return this.adSizeId;
	}

	public void setAdSizeId(Integer adSizeId) {
		this.adSizeId = adSizeId;
	}

	public Integer getAdWidth() {
		return this.adWidth;
	}

	public void setAdWidth(Integer adWidth) {
		this.adWidth = adWidth;
	}

	public Integer getAdHeight() {
		return this.adHeight;
	}

	public void setAdHeight(Integer adHeight) {
		this.adHeight = adHeight;
	}

	public Integer getAdSizeStatus() {
		return this.adSizeStatus;
	}

	public void setAdSizeStatus(Integer adSizeStatus) {
		this.adSizeStatus = adSizeStatus;
	}

}