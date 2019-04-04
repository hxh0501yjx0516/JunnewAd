package com.pancou.ad.util;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * MemCached �д�ŵ�ReadyBox����
 * 
 */
@Entity
public class MemSMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	/**	 * ���	 */
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
	@Id
	@GeneratedValue
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
	/**	 * ����ͼƬsrc	 */
	private String AdCreativeImg="";
	/**	 * ����ģ���ļ����	 */
	private String ModelJS="";
	/**	 * ������	 */
	private int AdWidth=0;	
	/**	 * ����߶�	 */
	private int AdHeight=0;	
	/**	 * �Ƿ������	 */
	private int isURLBand=0;	
	/**	 * վ����Ч�����б?���������	 */
	private String URLList="";
	
	
	/**	 * @return the URLList	 */
	public String getURLList() {
		return URLList;
	}
	/**	 * @param URLList 	 */
	public void setURLList(String sURLList) {
		this.URLList = sURLList;
	}
	/**	 * @return the isURLBand	 */
	public int getisURLBand() {
		return isURLBand;
	}
	/**	 * @param isURLBand 	 */
	public void setisURLBand(int iisURLBand) {
		this.isURLBand = iisURLBand;
	}
	
	/**	 * @return the AdCreativeImg	 */
	public String getAdCreativeImg() {
		return AdCreativeImg;
	}
	/**	 * @param AdCreativeImg 	 */
	public void setAdCreativeImg(String sAdCreativeImg) {
		this.AdCreativeImg = sAdCreativeImg;
	}
	
	/**	 * @return the ModelJS	 */
	public String getModelJS() {
		return ModelJS;
	}
	/**	 * @param ModelJS 	 */
	public void setModelJS(String sModelJS) {
		this.ModelJS = sModelJS;
	}
	
	/**	 * @return the AdHeight	 */
	public int getAdHeight() {
		return AdHeight;
	}
	/**	 * @param AdHeight 	 */
	public void setAdHeight(int iAdHeight) {
		this.AdHeight = iAdHeight;
	}
	
	/**	 * @return the AdWidth	 */
	public int getAdWidth() {
		return AdWidth;
	}
	/**	 * @param AdWidth 	 */
	public void setAdWidth(int iAdWidth) {
		this.AdWidth = iAdWidth;
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
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(ReadyBoxId).append("#"); //���Կ�������ȡ
		sb.append(AdBoxId).append("#");		
		sb.append(AdCreativeId).append("#");		
		sb.append(AdPlanCycleId).append("#");		
		sb.append(AdPlanId).append("#");		
		sb.append(UrlId).append("#");		
		sb.append(WebMasterId).append("#");		
		sb.append(PayTypeId).append("#");		//���Կ�������ȡ
		sb.append(WebMasterPrice).append("#");	//���Կ�������ȡ
		sb.append(Discount).append("#");		
		sb.append(ShowIpString).append("#");	
		sb.append(AdCreativeUrl).append("#");		
		sb.append(ShowIp).append("#");		
		sb.append(IsValid).append("#");		
		sb.append(AdCreativeLevel).append("#");		
		sb.append(AreaFix).append("#");
		sb.append(ReadyBoxStatus).append("#");			
		sb.append(AdCreativeImg).append("#");	
		sb.append(AdWidth).append("#");	
		sb.append(AdHeight).append("#");	
		sb.append(ModelJS).append("#");	
		sb.append(isURLBand).append("#");
		sb.append(URLList).append("#");
		return sb.toString();
	}

	public synchronized static MemSMsg getMemSMsg(String body) throws Exception {
		MemSMsg memMsg = new MemSMsg();
		String[] arr = body.split("#");
		if (arr.length >= 10) {
			memMsg.setReadyBoxId(Integer.parseInt(arr[0]));
			memMsg.setAdBoxId(Integer.parseInt(arr[1]));
			memMsg.setAdCreativeId(Integer.parseInt(arr[2]));	
			memMsg.setAdPlanCycleId(Integer.parseInt(arr[3]));		
			memMsg.setAdPlanId(Integer.parseInt(arr[4]));		
			memMsg.setUrlId(Integer.parseInt(arr[5]));
			memMsg.setWebMasterId(Integer.parseInt(arr[6]));			
			memMsg.setPayTypeId(Integer.parseInt(arr[7]));
			memMsg.setWebMasterPrice(Float.parseFloat(arr[8]));
			memMsg.setDiscount(Integer.parseInt(arr[9]));			
			memMsg.setShowIpString(arr[10]);
			memMsg.setAdCreativeUrl(arr[11]);
			memMsg.setShowIp(Integer.parseInt(arr[12]));			
			memMsg.setIsValid(Integer.parseInt(arr[13]));
			memMsg.setAdCreativeLevel(Integer.parseInt(arr[14]));
			memMsg.setAreaFix(arr[15]);
			memMsg.setReadyBoxStatus(Integer.parseInt(arr[16]));
			memMsg.setAdCreativeImg(arr[17]);
			memMsg.setAdWidth(Integer.parseInt(arr[18]));
			memMsg.setAdHeight(Integer.parseInt(arr[19]));
			memMsg.setModelJS(arr[20]);
			memMsg.setisURLBand(Integer.parseInt(arr[21]));
			memMsg.setURLList(arr[22]);
			return memMsg;
		} else {
			throw new Exception("MemSMsg Format error:" + body);
		}
	}

	public static void main(String[] args) throws Exception {
		MemSMsg msg = new MemSMsg();

		msg.setAdBoxId(12);
		msg.setAdCreativeId(123);

		msg.setAdCreativeUrl("http://www.baidu.com");
		msg.setAreaFix("0");
	
		String str = msg.toString();
		System.out.println(str);
		msg = MemSMsg.getMemSMsg(str);
		str = msg.toString();
		System.out.println(str);

	}
}
