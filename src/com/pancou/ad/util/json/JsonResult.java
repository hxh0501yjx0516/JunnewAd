package com.pancou.ad.util.json;

import javax.persistence.Entity;

@Entity
public class JsonResult {
	
	private String result;
	private Object data;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
