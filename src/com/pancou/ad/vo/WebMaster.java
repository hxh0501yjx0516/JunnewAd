package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 网站主实体 
 * @author tmb
 *
 */
@Entity
public class WebMaster implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int webMasterId;
	private String webMasterName; //网站主名
	private String webMasterPassWord; //密码
	private String webMasterContactName; //联系人
	private String webMasterCard; //联系人身份证
	private String webMasterAddress; //联系地址
	private String webMasterPost; //邮编
	private String webMasterMobile; //手机
	private String webMasterQQ ;  //QQ
	private String webMasterBank; //开户银行
	private String webMasterBankName; //开户名称
	private String webMasterBankNumber ;  //开户帐号
	private int webMasterStatus;//状态
	private String addTime; //添加时间
	private int userId;
	private int userGroup;
	
	private String userName;
	private int planCount;
	private int urlCount;
	public int getUrlCount() {
		return urlCount;
	}
	public void setUrlCount(int urlCount) {
		this.urlCount = urlCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPlanCount() {
		return planCount;
	}
	public void setPlanCount(int planCount) {
		this.planCount = planCount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getWebMasterId() {
		return webMasterId;
	}
	public void setWebMasterId(int webMasterId) {
		this.webMasterId = webMasterId;
	}
	public String getWebMasterName() {
		return webMasterName;
	}
	public void setWebMasterName(String webMasterName) {
		this.webMasterName = webMasterName;
	}
	public String getWebMasterPassWord() {
		return webMasterPassWord;
	}
	public void setWebMasterPassWord(String webMasterPassWord) {
		this.webMasterPassWord = webMasterPassWord;
	}
	public String getWebMasterContactName() {
		return webMasterContactName;
	}
	public void setWebMasterContactName(String webMasterContactName) {
		this.webMasterContactName = webMasterContactName;
	}
	public String getWebMasterCard() {
		return webMasterCard;
	}
	public void setWebMasterCard(String webMasterCard) {
		this.webMasterCard = webMasterCard;
	}
	public String getWebMasterAddress() {
		return webMasterAddress;
	}
	public void setWebMasterAddress(String webMasterAddress) {
		this.webMasterAddress = webMasterAddress;
	}
	public String getWebMasterPost() {
		return webMasterPost;
	}
	public void setWebMasterPost(String webMasterPost) {
		this.webMasterPost = webMasterPost;
	}
	public String getWebMasterMobile() {
		return webMasterMobile;
	}
	public void setWebMasterMobile(String webMasterMobile) {
		this.webMasterMobile = webMasterMobile;
	}
	public String getWebMasterQQ() {
		return webMasterQQ;
	}
	public void setWebMasterQQ(String webMasterQQ) {
		this.webMasterQQ = webMasterQQ;
	}
	public String getWebMasterBank() {
		return webMasterBank;
	}
	public void setWebMasterBank(String webMasterBank) {
		this.webMasterBank = webMasterBank;
	}
	public String getWebMasterBankName() {
		return webMasterBankName;
	}
	public void setWebMasterBankName(String webMasterBankName) {
		this.webMasterBankName = webMasterBankName;
	}
	public String getWebMasterBankNumber() {
		return webMasterBankNumber;
	}
	public void setWebMasterBankNumber(String webMasterBankNumber) {
		this.webMasterBankNumber = webMasterBankNumber;
	}
	public int getWebMasterStatus() {
		return webMasterStatus;
	}
	public void setWebMasterStatus(int webMasterStatus) {
		this.webMasterStatus = webMasterStatus;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

}

