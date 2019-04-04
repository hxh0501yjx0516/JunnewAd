package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * AdCreativeType entity. @author MyEclipse Persistence Tools
 */

@Entity
public class AdCreativeType implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private int adCreativeTypeId;
	private String adCreativeTypeName;
	private int adCreativeTypeTid;
	private int adCreativeTypeMid;
	private String adCreativeTypeMname;

	// Constructors

	/** default constructor */
	public AdCreativeType() {
	}

	/** full constructor */
	public AdCreativeType(String adCreativeTypeName, int adCreativeTypeTid,
			int adCreativeTypeMid, String adCreativeTypeMname) {
		this.adCreativeTypeName = adCreativeTypeName;
		this.adCreativeTypeTid = adCreativeTypeTid;
		this.adCreativeTypeMid = adCreativeTypeMid;
		this.adCreativeTypeMname = adCreativeTypeMname;
	}

	// Property accessors

	public int getAdCreativeTypeId() {
		return this.adCreativeTypeId;
	}

	public void setAdCreativeTypeId(int adCreativeTypeId) {
		this.adCreativeTypeId = adCreativeTypeId;
	}

	public String getAdCreativeTypeName() {
		return this.adCreativeTypeName;
	}

	public void setAdCreativeTypeName(String adCreativeTypeName) {
		this.adCreativeTypeName = adCreativeTypeName;
	}

	public int getAdCreativeTypeTid() {
		return this.adCreativeTypeTid;
	}

	public void setAdCreativeTypeTid(int adCreativeTypeTid) {
		this.adCreativeTypeTid = adCreativeTypeTid;
	}

	public int getAdCreativeTypeMid() {
		return this.adCreativeTypeMid;
	}

	public void setAdCreativeTypeMid(int adCreativeTypeMid) {
		this.adCreativeTypeMid = adCreativeTypeMid;
	}

	public String getAdCreativeTypeMname() {
		return this.adCreativeTypeMname;
	}

	public void setAdCreativeTypeMname(String adCreativeTypeMname) {
		this.adCreativeTypeMname = adCreativeTypeMname;
	}

}