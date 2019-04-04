package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 新闻分类实体 
 * @author tmb
 *
 */
@Entity
@SuppressWarnings("serial")
public class CmsClass implements Serializable{
	
	@Id
	@GeneratedValue
	private int cmsClassId;
	private String cmsClassName; //类别名称
	public int getCmsClassId() {
		return cmsClassId;
	}
	public void setCmsClassId(int cmsClassId) {
		this.cmsClassId = cmsClassId;
	}
	public String getCmsClassName() {
		return cmsClassName;
	}
	public void setCmsClassName(String cmsClassName) {
		this.cmsClassName = cmsClassName;
	}

}

