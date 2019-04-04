package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ShopSource entity. @author MyEclipse Persistence Tools
 */

@Entity
public class ShopSource implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopSourceId;
	private int shopSourceType;
	private int shopWebId;
	private String shopWebName;
	private String shopWebUrl;
	private int shopFclassId;
	private String shopFclassName;
	private int shopSclassId;
	private String shopSclassName;
	private String shopSource;
	private String addTime;

	// Constructors

	/** default constructor */
	public ShopSource() {
	}

	/** full constructor */
	public ShopSource(int shopSourceType, int shopWebId,
			String shopWebName, String shopWebUrl, int shopFclassId,
			String shopFclassName, int shopSclassId, String shopSclassName,
			String shopSource, String addTime) {
		this.shopSourceType = shopSourceType;
		this.shopWebId = shopWebId;
		this.shopWebName = shopWebName;
		this.shopWebUrl = shopWebUrl;
		this.shopFclassId = shopFclassId;
		this.shopFclassName = shopFclassName;
		this.shopSclassId = shopSclassId;
		this.shopSclassName = shopSclassName;
		this.shopSource = shopSource;
		this.addTime = addTime;
	}

	// Property accessors

	public int getShopSourceId() {
		return this.shopSourceId;
	}

	public void setShopSourceId(int shopSourceId) {
		this.shopSourceId = shopSourceId;
	}

	public int getShopSourceType() {
		return this.shopSourceType;
	}

	public void setShopSourceType(int shopSourceType) {
		this.shopSourceType = shopSourceType;
	}

	public int getShopWebId() {
		return this.shopWebId;
	}

	public void setShopWebId(int shopWebId) {
		this.shopWebId = shopWebId;
	}

	public String getShopWebName() {
		return this.shopWebName;
	}

	public void setShopWebName(String shopWebName) {
		this.shopWebName = shopWebName;
	}

	public String getShopWebUrl() {
		return this.shopWebUrl;
	}

	public void setShopWebUrl(String shopWebUrl) {
		this.shopWebUrl = shopWebUrl;
	}

	public int getShopFclassId() {
		return this.shopFclassId;
	}

	public void setShopFclassId(int shopFclassId) {
		this.shopFclassId = shopFclassId;
	}

	public String getShopFclassName() {
		return this.shopFclassName;
	}

	public void setShopFclassName(String shopFclassName) {
		this.shopFclassName = shopFclassName;
	}

	public int getShopSclassId() {
		return this.shopSclassId;
	}

	public void setShopSclassId(int shopSclassId) {
		this.shopSclassId = shopSclassId;
	}

	public String getShopSclassName() {
		return this.shopSclassName;
	}

	public void setShopSclassName(String shopSclassName) {
		this.shopSclassName = shopSclassName;
	}

	public String getShopSource() {
		return this.shopSource;
	}

	public void setShopSource(String shopSource) {
		this.shopSource = shopSource;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}