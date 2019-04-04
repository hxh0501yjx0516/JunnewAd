package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 客服实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class Service implements Serializable{
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private int id;
	private String name; //昵称
	private int qq; //qq
	private int typeId; //类型0-媒介，1-销售，2-洽谈，3-客服，4-技术
	private int status; //状态0-显示，1-隐藏
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}

