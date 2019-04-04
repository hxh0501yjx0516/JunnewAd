package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * UserGroup entity. @author MyEclipse Persistence Tools
 */

@Entity
public class UserGroup implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer id;
	private String userGroupName;
	private Integer userGroupStatus;

	// Constructors

	/** default constructor */
	public UserGroup() {
	}

	/** full constructor */
	public UserGroup(String userGroupName, Integer userGroupStatus) {
		this.userGroupName = userGroupName;
		this.userGroupStatus = userGroupStatus;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserGroupName() {
		return this.userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public Integer getUserGroupStatus() {
		return this.userGroupStatus;
	}

	public void setUserGroupStatus(Integer userGroupStatus) {
		this.userGroupStatus = userGroupStatus;
	}

}