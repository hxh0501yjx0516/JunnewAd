package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ShopWeb entity. @author MyEclipse Persistence Tools
 */

@Entity
public class ShopWeb implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopWebId;
	private String shopWebName;
	private String shopWebUrl;
	private String shopWebContact;
	private String shopWebTel;
	private String shopWebQq;
	private int shopWebStatus;
	private String addTime;

	// Constructors

	/** default constructor */
	public ShopWeb() {
	}

	/** full constructor */
	public ShopWeb(String shopWebName, String shopWebUrl,
			String shopWebContact, String shopWebTel, String shopWebQq,
			int shopWebStatus, String addTime) {
		this.shopWebName = shopWebName;
		this.shopWebUrl = shopWebUrl;
		this.shopWebContact = shopWebContact;
		this.shopWebTel = shopWebTel;
		this.shopWebQq = shopWebQq;
		this.shopWebStatus = shopWebStatus;
		this.addTime = addTime;
	}

	// Property accessors

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

	public String getShopWebContact() {
		return this.shopWebContact;
	}

	public void setShopWebContact(String shopWebContact) {
		this.shopWebContact = shopWebContact;
	}

	public String getShopWebTel() {
		return this.shopWebTel;
	}

	public void setShopWebTel(String shopWebTel) {
		this.shopWebTel = shopWebTel;
	}

	public String getShopWebQq() {
		return this.shopWebQq;
	}

	public void setShopWebQq(String shopWebQq) {
		this.shopWebQq = shopWebQq;
	}

	public int getShopWebStatus() {
		return this.shopWebStatus;
	}

	public void setShopWebStatus(int shopWebStatus) {
		this.shopWebStatus = shopWebStatus;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}