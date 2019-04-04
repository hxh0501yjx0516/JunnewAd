package com.pancou.ad.vo;

import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileInstanceDir {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int year;
	private int month;
	private String createTime;
	private int pid;
	private List<Map<String,String>> subDir;
	public List<Map<String, String>> getSubDir() {
		return subDir;
	}
	public void setSubDir(List<Map<String, String>> subDir) {
		this.subDir = subDir;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	
	
	
	
	
}
