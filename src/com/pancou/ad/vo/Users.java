package com.pancou.ad.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * userʵ����
 * @author 
 *
 */
@Entity
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2129786655339161827L;
	private int userId ;
	private String username; //�û���
	private String pwd;
	private String realname; //��ʵ��
	private String dept; //����
	private String title; //ְ��
	private String tel ;  //�绰
	private String lastlogtime;  //����¼��ʱ��
	private String lastip ;  //���ʵ�ip
	private int state ; //1 ��Ч
	@Id
	@GeneratedValue
	private int sid; 
	private int userGroup;
	

	public int getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}
	//private Set<Role> userRoles = new HashSet<Role>();
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLastlogtime() {
		return lastlogtime;
	}
	public void setLastlogtime(String lastlogtime) {
		this.lastlogtime = lastlogtime;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
