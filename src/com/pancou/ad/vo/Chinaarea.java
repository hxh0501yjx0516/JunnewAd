package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Chinaarea entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Chinaarea implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer cid;
	private Integer pid;
	private String p;
	private String c;

	// Constructors

	/** default constructor */
	public Chinaarea() {
	}

	/** full constructor */
	public Chinaarea(Integer pid, String p, String c) {
		this.pid = pid;
		this.p = p;
		this.c = c;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getP() {
		return this.p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

}