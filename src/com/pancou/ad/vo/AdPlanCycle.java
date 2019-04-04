package com.pancou.ad.vo;

import java.io.Serializable;
import java.util.List;

import com.pancou.ad.domain.RealDayFlow;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 广告计划实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class AdPlanCycle implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int adPlanCycleId;
	private int adPlanId; //计划ID
	private String adPlanCycleName; //计划周期名称
	private String beginTime; //开始时间
	private String endTime; //结束时间
	private float customerPrice; //客户单价
	private float customerAllPrice; //预算
	private float webMasterPrice; //媒体最低单价
	private int adPlanIsParameter;//是否传参
	private int adPlanCycleStatus;//状态
	private String addTime;//添加时间
	private String adPlanCycleUrl;
	private String dateString;
	private String dataString;
	private int disCount;
	private int showType; //显示类型：1-uv;2-pv;3-ip
	
	private String adPlanName;
	private int creativeCount;
	
	private int flowList;
	private int realFlowList;
	@OneToMany
	private List<RealDayFlow> dayFlow;
	public int getCreativeCount() {
		return creativeCount;
	}
	public void setCreativeCount(int creativeCount) {
		this.creativeCount = creativeCount;
	}
	public String getAdPlanName() {
		return adPlanName;
	}
	public void setAdPlanName(String adPlanName) {
		this.adPlanName = adPlanName;
	}
	public int getAdPlanCycleId() {
		return adPlanCycleId;
	}
	public void setAdPlanCycleId(int adPlanCycleId) {
		this.adPlanCycleId = adPlanCycleId;
	}
	public int getAdPlanId() {
		return adPlanId;
	}
	public void setAdPlanId(int adPlanId) {
		this.adPlanId = adPlanId;
	}
	public String getAdPlanCycleName() {
		return adPlanCycleName;
	}
	public void setAdPlanCycleName(String adPlanCycleName) {
		this.adPlanCycleName = adPlanCycleName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public float getCustomerPrice() {
		return customerPrice;
	}
	public void setCustomerPrice(float customerPrice) {
		this.customerPrice = customerPrice;
	}
	public float getCustomerAllPrice() {
		return customerAllPrice;
	}
	public void setCustomerAllPrice(float customerAllPrice) {
		this.customerAllPrice = customerAllPrice;
	}
	public float getWebMasterPrice() {
		return webMasterPrice;
	}
	public void setWebMasterPrice(float webMasterPrice) {
		this.webMasterPrice = webMasterPrice;
	}
	public int getAdPlanIsParameter() {
		return adPlanIsParameter;
	}
	public void setAdPlanIsParameter(int adPlanIsParameter) {
		this.adPlanIsParameter = adPlanIsParameter;
	}
	public int getAdPlanCycleStatus() {
		return adPlanCycleStatus;
	}
	public void setAdPlanCycleStatus(int adPlanCycleStatus) {
		this.adPlanCycleStatus = adPlanCycleStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getAdPlanCycleUrl() {
		return adPlanCycleUrl;
	}
	public void setAdPlanCycleUrl(String adPlanCycleUrl) {
		this.adPlanCycleUrl = adPlanCycleUrl;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public int getDisCount() {
		return disCount;
	}
	public void setDisCount(int disCount) {
		this.disCount = disCount;
	}
	public int getShowType() {
		return showType;
	}
	public void setShowType(int showType) {
		this.showType = showType;
	}
	public int getFlowList() {
		return flowList;
	}
	public void setFlowList(int flowList) {
		this.flowList = flowList;
	}
	public int getRealFlowList() {
		return realFlowList;
	}
	public void setRealFlowList(int realFlowList) {
		this.realFlowList = realFlowList;
	}
	public List<RealDayFlow> getDayFlow() {
		return dayFlow;
	}
	public void setDayFlow(List<RealDayFlow> dayFlow) {
		this.dayFlow = dayFlow;
	}

}

