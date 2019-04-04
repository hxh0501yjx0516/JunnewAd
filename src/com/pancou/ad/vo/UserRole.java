package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * UserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
public class UserRole implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue
	private Integer id;
	private int userId;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public UserRole() {
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}