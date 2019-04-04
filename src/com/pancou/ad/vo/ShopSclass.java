package com.pancou.ad.vo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * ShopSclass entity. @author MyEclipse Persistence Tools
 */

@Entity
public class ShopSclass implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int shopSclassId;
	private String shopSclassName;
	private int shopFclassId;
	private String addTime;
	private String shopFclassName;
	
	@OneToMany
	private List<Shop> shopList ;

	// Constructors

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	/** default constructor */
	public ShopSclass() {
	}

	/** full constructor */
	public ShopSclass(String shopSclassName, int shopFclassId,
			String addTime) {
		this.shopSclassName = shopSclassName;
		this.shopFclassId = shopFclassId;
		this.addTime = addTime;
	}

	// Property accessors

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

	public int getShopFclassId() {
		return this.shopFclassId;
	}

	public void setShopFclassId(int shopFclassId) {
		this.shopFclassId = shopFclassId;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setShopFclassName(String shopFclassName){
		this.shopFclassName = shopFclassName;
	}
	public String getShopFclassName(){
		return shopFclassName;
	}
}