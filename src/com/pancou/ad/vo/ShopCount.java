package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ShopCount entity. @author MyEclipse Persistence Tools
 */

@Entity
public class ShopCount implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopCountId;
	private int shopCountType;
	private int shopWebId;
	private String shopWebName;
	private String shopWebUrl;
	private int shopFclassId;
	private String shopFclassName;
	private int shopSclassId;
	private String shopSclassName;
	private int shopId;
	private String shopName;
	private int shopPv;
	private int shopUv;
	private int shopHit;
	private int shopHit2;
	private String addTime;

	// Constructors

	/** default constructor */
	public ShopCount() {
	}

	/** full constructor */
	public ShopCount(int shopCountType, int shopWebId,
			String shopWebName, String shopWebUrl, int shopFclassId,
			String shopFclassName, int shopSclassId, String shopSclassName,
			int shopId, String shopName, int shopPv, int shopUv,
			int shopHit, int shopHit2, String addTime) {
		this.shopCountType = shopCountType;
		this.shopWebId = shopWebId;
		this.shopWebName = shopWebName;
		this.shopWebUrl = shopWebUrl;
		this.shopFclassId = shopFclassId;
		this.shopFclassName = shopFclassName;
		this.shopSclassId = shopSclassId;
		this.shopSclassName = shopSclassName;
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopPv = shopPv;
		this.shopUv = shopUv;
		this.shopHit = shopHit;
		this.shopHit2 = shopHit2;
		this.addTime = addTime;
	}

	// Property accessors

	public int getShopCountId() {
		return this.shopCountId;
	}

	public void setShopCountId(int shopCountId) {
		this.shopCountId = shopCountId;
	}

	public int getShopCountType() {
		return this.shopCountType;
	}

	public void setShopCountType(int shopCountType) {
		this.shopCountType = shopCountType;
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

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopPv() {
		return this.shopPv;
	}

	public void setShopPv(int shopPv) {
		this.shopPv = shopPv;
	}

	public int getShopUv() {
		return this.shopUv;
	}

	public void setShopUv(int shopUv) {
		this.shopUv = shopUv;
	}

	public int getShopHit() {
		return this.shopHit;
	}

	public void setShopHit(int shopHit) {
		this.shopHit = shopHit;
	}

	public int getShopHit2() {
		return this.shopHit2;
	}

	public void setShopHit2(int shopHit2) {
		this.shopHit2 = shopHit2;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}