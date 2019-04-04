package com.pancou.ad.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
 * Ͷ����
 * 
*/
@Entity
public class ReadyBox {
	/**	 * ���	 */
	@Id
	@GeneratedValue
	private int ReadyBoxId=0;
	/**	 * ���λid	 */
	private int AdBoxId=0;
	/**	 * ����id	 */
	private int AdCreativeId=0;
	/**	 * �ƻ�����id	 */
	private int AdPlanCycleId=0;
	/**	 * ���ƻ�id	 */
	private int AdPlanId=0;
	/**	 * ����id	 */
	private int UrlId=0;
	/**	 * վ��id	 */
	private int WebMasterId=0;
	/**	 * ����ģʽ	 */
	private int PayTypeId=0;
	/**	 * վ������	 */
	private Float WebMasterPrice=0F;
	/**	 * ����	 */
	private int Discount=0;	
	/**	 * ����������	 */
	private String ShowIpString="";	
	/**	 * Ŀ��url	 */
	private String AdCreativeUrl="";	
	/**	 * ������������	 */
	private int ShowIp=0;		
	/**	 * �����Ƿ���,��ReadyBoxStatus������	 */
	private int IsValid=0;		
	/**	 * �����ֲ��ȼ�	 */
	private int AdCreativeLevel=0;	
	/**	 * ���������	 */
	private String AreaFix="";		
	/**	 * Ͷ���Ƿ���Ч	 */
	private int ReadyBoxStatus=0;	
	/**	 * ����ͼƬ��ַ	 */
	private String AdCreativeImg="";	
	/**	 * ���λ���	 */
	private int AdWidth=0;	
	/**	 * ���λ�߶�	 */
	private int AdHeight=0;	
	/**	 * ģ���ļ����	 */
	private String ModelJS="";	
	/**	 * �Ƿ������	 */
	private int isURLBand=0;	
	
	/**	 * UserId ý��id	 */
	private int UserId=0;
	
	/**	 * ��Ч�����б� ���������	 */
	private String URLList="";
	
	
	/**	 ������Ƿ��Ǻ�	 */
	public Boolean isURLFix(String strurl) {
		if("".equals(strurl) || strurl==null  )
			return true;
		Boolean isfix=false;
		if(this.getisURLBand()==0){ //����
			return true;
		}
		else{ //���
			String strFixURL=this.getURLList();
			if( strFixURL.indexOf(strurl)>-1 )
				return true;
		}
		return isfix;
	}
	

	
	/**	 * @return the URLList	 */
	public String getURLList() {
		return URLList;
	}
	/**	 * @param URLList 	 */
	public void setURLList(String sURLList) {
		this.URLList = sURLList;
	}
	
	/**	 * @return the UserId	 */
	public int getUserId() {
		return UserId;
	}
	/**	 * @param UserId 	 */
	public void setUserId(int iUserId) {
		this.UserId = iUserId;
	}
	/**	 * @return the isURLBand	 */
	public int getisURLBand() {
		return isURLBand;
	}
	/**	 * @param isURLBand 	 */
	public void setisURLBand(int iisURLBand) {
		this.isURLBand = iisURLBand;
	}
	
	/**	 * @return the ModelJS	 */
	public String getModelJS() {
		return ModelJS;
	}
	/**	 * @param ModelJS 	 */
	public void setModelJS(String sModelJS) {
		this.ModelJS = sModelJS;
	}
	/**	 * @return the AdCreativeImg	 */
	public String getAdCreativeImg() {
		return AdCreativeImg;
	}
	/**	 * @param AdCreativeImg 	 */
	public void setAdCreativeImg(String sAdCreativeImg) {
		this.AdCreativeImg = sAdCreativeImg;
	}
	/**	 * @return the AdWidth	 */
	public int getAdWidth() {
		return AdWidth;
	}
	/**	 * @param AdWidth 	 */
	public void setAdWidth(int AdWidth) {
		this.AdWidth = AdWidth;
	}
	/**	 * @return the AdHeight	 */
	public int getAdHeight() {
		return AdHeight;
	}
	/**	 * @param ReadyBoxStatus 	 */
	public void setAdHeight(int iAdHeight) {
		this.AdHeight = iAdHeight;
	}
	/**	 * @return the ReadyBoxStatus	 */
	public int getReadyBoxStatus() {
		return ReadyBoxStatus;
	}
	/**	 * @param ReadyBoxStatus 	 */
	public void setReadyBoxStatus(int iReadyBoxStatus) {
		this.ReadyBoxStatus = iReadyBoxStatus;
	}
	/**	 * @return the AreaFix	 */
	public String getAreaFix() {
		return AreaFix;
	}
	/**	 * @param AreaFix 	 */
	public void setAreaFix(String sAreaFix) {
		this.AreaFix = sAreaFix;
	}
	
	/**	 * @return the AdCreativeLevel	 */
	public int getAdCreativeLevel() {
		return AdCreativeLevel;
	}
	/**	 * @param AdCreativeLevel 	 */
	public void setAdCreativeLevel(int iAdCreativeLevel) {
		this.AdCreativeLevel = iAdCreativeLevel;
	}
	
	/**	 * @return the IsValid	 */
	public int getIsValid() {
		return IsValid;
	}
	/**	 * @param IsValid 	 */
	public void setIsValid(int iIsValid) {
		this.IsValid = iIsValid;
	}
	
	/**	 * @return the ShowIp	 */
	public int getShowIp() {
		return ShowIp;
	}
	/**	 * @param ShowIp 	 */
	public void setShowIp(int iShowIp) {
		this.ShowIp = iShowIp;
	}
	
	/**	 * @return the ShowIpString	 */
	public String getShowIpString() {
		return ShowIpString;
	}
	/**	 * @param ShowIpString 	 */
	public void setShowIpString(String sShowIpString) {
		this.ShowIpString = sShowIpString;
	}
	
	
	
	/**	 * @return the AdCreativeUrl	 */
	public String getAdCreativeUrl() {
		return AdCreativeUrl;
	}
	/**	 * @param AdCreativeUrl 	 */
	public void setAdCreativeUrl(String sAdCreativeUrl) {
		this.AdCreativeUrl = sAdCreativeUrl;
	}
	
	/**	 * @return the Discount	 */
	public int getDiscount() {
		return Discount;
	}
	/**	 * @param Discount 	 */
	public void setDiscount(int iDiscount) {
		this.Discount = iDiscount;
	}
	
	/**	 * @return the WebMasterPrice	 */
	public Float getWebMasterPrice() {
		return WebMasterPrice;
	}
	/**	 * @param WebMasterPrice 	 */
	public void setWebMasterPrice(Float fWebMasterPrice) {
		this.WebMasterPrice = fWebMasterPrice;
	}
	
	/**	 * @return the PayTypeId	 */
	public int getPayTypeId() {
		return PayTypeId;
	}
	/**	 * @param PayTypeId 	 */
	public void setPayTypeId(int iPayTypeId) {
		this.PayTypeId = iPayTypeId;
	}
	
	
	/**	 * @return the WebMasterId	 */
	public int getWebMasterId() {
		return WebMasterId;
	}
	/**	 * @param AdPlanId 	 */
	public void setWebMasterId(int iWebMasterId) {
		this.WebMasterId = iWebMasterId;
	}
	
	/**	 * @return the AdPlanId	 */
	public int getUrlId() {
		return UrlId;
	}
	/**	 * @param AdPlanId 	 */
	public void setUrlId(int iUrlId) {
		this.UrlId = iUrlId;
	}
	
	/**	 * @return the AdPlanId	 */
	public int getAdPlanId() {
		return AdPlanId;
	}
	/**	 * @param AdPlanId 	 */
	public void setAdPlanId(int iAdPlanId) {
		this.AdPlanId = iAdPlanId;
	}
	
	/**	 * @return the AdPlanCycleId	 */
	public int getAdPlanCycleId() {
		return AdPlanCycleId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setAdPlanCycleId(int iAdPlanCycleId) {
		this.AdPlanCycleId = iAdPlanCycleId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getAdCreativeId() {
		return AdCreativeId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setAdCreativeId(int iAdCreativeId) {
		this.AdCreativeId = iAdCreativeId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getReadyBoxId() {
		return ReadyBoxId;
	}
	/**	 * @param ReadyBoxId 	 */
	public void setReadyBoxId(int iReadyBoxId) {
		this.ReadyBoxId = iReadyBoxId;
	}
	
	/**	 * @return the ReadyBoxId	 */
	public int getAdBoxId() {
		return AdBoxId;
	}
	/**	 * @param AdBoxId 	 */
	public void setAdBoxId(int iAdBoxId) {
		this.AdBoxId = iAdBoxId;
	}
	
}
