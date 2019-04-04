package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * ��ɫʵ�� 
 * @author lp 2009-11-3
 *
 */
@Entity
public class ShopIndex implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int shopIndexId;
	private String shopIndexName;
	private String shopIndexUrl;
	
	public void setShopIndexId(int shopIndexId){
		this.shopIndexId = shopIndexId;
	}
	public int getShopIndexId(){
		return shopIndexId;
	}
	
	public void setShopIndexName(String shopIndexName){
		this.shopIndexName = shopIndexName;
	}
	public String getShopIndexName(){
		return shopIndexName;
	}
	
	public void setShopIndexUrl(String shopIndexUrl){
		this.shopIndexUrl = shopIndexUrl;
	}
	public String getShopIndexUrl(){
		return shopIndexUrl;
	}
}

