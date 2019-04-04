package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Shop entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Shop implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopId;
	private int shopFclassId;
	private int shopSclassId;
	private String shopName;
	private String shopUrl;
	private double shopPrice;
	private double shopNewPrice;
	private String shopImg;
	private String shopRemarks;
	private String addTime;

	// Constructors

	/** default constructor */
	public Shop() {
	}

	/** full constructor */
	public Shop(String shopName, String shopUrl, double shopPrice,
			double shopNewPrice, String shopImg, String shopRemarks,
			String addTime) {
		this.shopName = shopName;
		this.shopFclassId = shopFclassId;
		this.shopSclassId = shopSclassId;
		this.shopUrl = shopUrl;
		this.shopPrice = shopPrice;
		this.shopNewPrice = shopNewPrice;
		this.shopImg = shopImg;
		this.shopRemarks = shopRemarks;
		this.addTime = addTime;
	}

	// Property accessors

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getShopFclassId(){
		return this.shopFclassId;
	}
	
	public void setShopFclassId(int shopFclassId){
		this.shopFclassId = shopFclassId;
	}

	public int getShopSclassId(){
		return this.shopSclassId;
	}
	
	public void setShopSclassId(int shopSclassId){
		this.shopSclassId = shopSclassId;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopUrl() {
		return this.shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public double getShopPrice() {
		return this.shopPrice;
	}

	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public double getShopNewPrice() {
		return this.shopNewPrice;
	}

	public void setShopNewPrice(double shopNewPrice) {
		this.shopNewPrice = shopNewPrice;
	}

	public String getShopImg() {
		return this.shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	public String getShopRemarks() {
		return this.shopRemarks;
	}

	public void setShopRemarks(String shopRemarks) {
		this.shopRemarks = shopRemarks;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}