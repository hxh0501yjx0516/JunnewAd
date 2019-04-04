package com.pancou.ad.vo;
import java.io.Serializable;
import java.lang.Comparable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 * RoleResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
public class RoleResource implements  Serializable, Comparable{

	// Fields

	@Id
	@GeneratedValue
	private Integer id;
	private Integer resId;
	private Integer roleId;
	private String modify;
	private String add;
	private String del;
	
	// Constructors

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}


	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	/** default constructor */
	public RoleResource() {
	}

	/** full constructor */
	public RoleResource(Integer resId, Integer roleId) {
		this.resId = resId;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getResId() {
		return this.resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	

	public int compareTo(Object o) {
		RoleResource other=(RoleResource)o;
		if(resId<other.resId)     
	           return 1;       
	       if(resId>other.resId)       
	           return -1;       
	       return 0;      
	}       

 }