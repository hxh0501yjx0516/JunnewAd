package com.pancou.ad.vo;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * InterfaceTaoXie entity. @author MyEclipse Persistence Tools
 */

@Entity
public class InterfaceTaoXie implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer id;
	private String flag;
	private String orderNumber;
	private String product;
	private Double productAmount;
	private Integer productNumber;
	private Double postAmount;
	private Double productAmount2;
	private Double allAmount;
	private String orderType;
	private String orderState;
	private Timestamp orderTime;

	// Constructors

	/** default constructor */
	public InterfaceTaoXie() {
	}

	/** full constructor */
	public InterfaceTaoXie(String flag, String orderNumber, String product,
			Double productAmount, Integer productNumber, Double postAmount,
			Double productAmount2, Double allAmount, String orderType,
			String orderState, Timestamp orderTime) {
		this.flag = flag;
		this.orderNumber = orderNumber;
		this.product = product;
		this.productAmount = productAmount;
		this.productNumber = productNumber;
		this.postAmount = postAmount;
		this.productAmount2 = productAmount2;
		this.allAmount = allAmount;
		this.orderType = orderType;
		this.orderState = orderState;
		this.orderTime = orderTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getProductAmount() {
		return this.productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Integer getProductNumber() {
		return this.productNumber;
	}

	public void setProductNumber(Integer productNumber) {
		this.productNumber = productNumber;
	}

	public Double getPostAmount() {
		return this.postAmount;
	}

	public void setPostAmount(Double postAmount) {
		this.postAmount = postAmount;
	}

	public Double getProductAmount2() {
		return this.productAmount2;
	}

	public void setProductAmount2(Double productAmount2) {
		this.productAmount2 = productAmount2;
	}

	public Double getAllAmount() {
		return this.allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Timestamp getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

}