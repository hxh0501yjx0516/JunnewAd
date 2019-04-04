package com.pancou.ad.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * resourceʵ�� lp 2009-11-3
 * @author 
 *
 */
@Entity
public class Resource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3141859632641291451L;
	@Id
	@GeneratedValue
	private int resourceId ; //��Դid
	private String resourceName; //��Դ���
	private String resourceUrl ; 
	private String resourceModual ; //ģ�����
	private int resourceType ; //1-ģ�� 0-ҳ�� 2�˵�
	private int resourcePid ; //���˵�id
	private int displayorder;
	@OneToMany
	private Set<Role> roleResources = new HashSet<Role>();
	
	private int resourcePidName;
	public int getResourcePidName() {
		return resourcePidName;
	}
	public void setResourcePidName(int resourcePidName) {
		this.resourcePidName = resourcePidName;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceModual() {
		return resourceModual;
	}
	public void setResourceModual(String resourceModual) {
		this.resourceModual = resourceModual;
	}
	public int getResourceType() {
		return resourceType;
	}
	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}
	public int getResourcePid() {
		return resourcePid;
	}
	public void setResourcePid(int resourcePid) {
		this.resourcePid = resourcePid;
	}
	public Set<Role> getRoleResources() {
		return roleResources;
	}
	public void setRoleResources(Set<Role> roleResources) {
		this.roleResources = roleResources;
	}
	public int getDisplayorder() {
		return displayorder;
	}
	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
