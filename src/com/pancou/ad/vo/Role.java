package com.pancou.ad.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 角色实体 
 * @author lp 2009-11-3
 *
 */
@Entity
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 373951047155376406L;
	@Id
	@GeneratedValue
	private int roleId;
	private String roleName; //角色名
	private String memo; //备注
	private int state ;  //状态
	/*
	private Set<Users> user = new HashSet<Users>();
	private Set<Resource> resource = new HashSet<Resource>();*/
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}

