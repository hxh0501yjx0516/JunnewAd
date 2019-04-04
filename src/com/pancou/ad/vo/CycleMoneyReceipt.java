package com.pancou.ad.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 客户回款
 * 
 * @author 31506
 * 
 */
@Entity
@SuppressWarnings("serial")
public class CycleMoneyReceipt implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int id;
	private String receiptDate;
	private Double receiptMoney;
	private String remarks;
	private int adPlanCycleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Double getReceiptMoney() {
		return receiptMoney;
	}

	public void setReceiptMoney(Double receiptMoney) {
		this.receiptMoney = receiptMoney;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getAdPlanCycleId() {
		return adPlanCycleId;
	}

	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}

	@Override
	public String toString() {
		return "CycleMoneyReceipt [id=" + id + ", receiptDate=" + receiptDate
				+ ", receiptMoney=" + receiptMoney + ", remarks=" + remarks
				+ ", adPlanCycleId=" + adPlanCycleId + "]";
	}

}
