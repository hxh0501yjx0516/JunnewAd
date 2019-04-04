package com.pancou.ad.vo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Log implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int logId;
	private String logUserType;
	private String logUserName;
	private String logRealName;
	private String logSource;
	private String logIp;
	private String logTime;
	private int logUserFlag;
	
	private String logPassword;
	private String ipAddress;


	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}

	public String getLogRealName() {
		return logRealName;
	}

	public void setLogRealName(String logRealName) {
		this.logRealName = logRealName;
	}
	
	public int getLogUserFlag() {
		return logUserFlag;
	}

	public void setLogUserFlag(int logUserFlag) {
		this.logUserFlag = logUserFlag;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getLogUserType() {
		return this.logUserType;
	}

	public void setLogUserType(String logUserType) {
		this.logUserType = logUserType;
	}

	public String getLogUserName() {
		return this.logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	public String getLogSource() {
		return this.logSource;
	}

	public void setLogSource(String logSource) {
		this.logSource = logSource;
	}

	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

}