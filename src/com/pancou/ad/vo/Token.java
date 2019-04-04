package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Token implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer tokenId;
	private String tokenSerial;
	private String tokenDigest;
	private Integer userId;
	private String addTime;

	// Constructors

	/** default constructor */
	public Token() {
	}

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenSerial() {
		return tokenSerial;
	}

	public void setTokenSerial(String tokenSerial) {
		this.tokenSerial = tokenSerial;
	}

	public String getTokenDigest() {
		return tokenDigest;
	}

	public void setTokenDigest(String tokenDigest) {
		this.tokenDigest = tokenDigest;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


}