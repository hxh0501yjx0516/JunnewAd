package com.pancou.ad.domain;

import javax.persistence.Entity;

@Entity
public class RealDayFlow {
	private String dateTime;
	private int realFlow;
	private int preFlow;
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getRealFlow() {
		return realFlow;
	}
	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	public int getPreFlow() {
		return preFlow;
	}
	public void setPreFlow(int preFlow) {
		this.preFlow = preFlow;
	}
	
}
