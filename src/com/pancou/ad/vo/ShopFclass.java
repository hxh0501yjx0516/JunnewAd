package com.pancou.ad.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * ShopFclass entity. @author MyEclipse Persistence Tools
 */

@Entity
public class ShopFclass implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopFclassId;
	private String shopFclassName;
	private String addTime;
	
	@OneToMany
	private List<ShopSclass> shopSclassList;

	public int getShopFclassId() {
		return this.shopFclassId;
	}

	public List<ShopSclass> getShopSclassList() {
		return shopSclassList;
	}

	public void setShopSclassList(List<ShopSclass> shopSclassList) {
		this.shopSclassList = shopSclassList;
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

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

}