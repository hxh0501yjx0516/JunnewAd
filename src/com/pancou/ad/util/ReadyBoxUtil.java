package com.pancou.ad.util;

import java.util.Properties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ReadyBoxUtil {
	@ManyToOne
	protected CommHttpAllYes httpSync = new CommHttpAllYes();

	public final Properties notifyPartner(String url) {
		Properties prop = null;
		try {
			prop = this.httpSync.send(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public void save2MemCached(String k) {
		String[] ips = { "tou.junnew.com" };

		String ports = "8090";
		for (String strip : ips) {
			String urlstr = "http://" + strip + ":" + ports
					+ "/ViewHit/mem_inter?key=" + k;
			System.out.println(strip + " mem update url:" + urlstr);

			Properties p = notifyPartner(urlstr);
			System.out.println(strip + " mem remove result:" + p.get("result"));
		}
		System.out.println("-------------------------------------------");
	}

	public void save2MemCached(com.pancou.ad.domain.ReadyBox readybox) {
		String key = readybox.getAdBoxId() + "_" + readybox.getAdCreativeId();

		save2MemCached(key);
	}

	public void saveToMemCached(com.pancou.ad.vo.ReadyBox readyboxvo) {
		System.out.println("-------------------------------------------");
		String key = readyboxvo.getAdBoxId() + "_"
				+ readyboxvo.getAdCreativeId();
		System.out.println(DatetimeHandle.formatCurrentDate()
				+ "            1:key:" + key
				+ ",enter function saveToMemCached");

		com.pancou.ad.domain.ReadyBox readybox = convert(readyboxvo);
		System.out.println("状态：" + readybox.getReadyBoxStatus());
		System.out.println("域名：" + readybox.getURLList());
		System.out.println("目标地址：" + readybox.getAdCreativeUrl());
		System.out.println("mem update:key(" + key + ")"
				+ MemCachedUtil_110.remove(key));
		save2MemCached(readybox);
	}

	public static com.pancou.ad.domain.ReadyBox convert(
			com.pancou.ad.vo.ReadyBox readybox_vo) {
		com.pancou.ad.domain.ReadyBox rb = new com.pancou.ad.domain.ReadyBox();
		rb.setAdBoxId(readybox_vo.getAdBoxId());
		rb.setAdCreativeId(readybox_vo.getAdCreativeId());
		rb.setAdCreativeImg(readybox_vo.getAdCreativeImg());
		rb.setAdCreativeLevel(readybox_vo.getAdCreativeLevel());
		rb.setAdCreativeUrl(readybox_vo.getAdCreativeUrl());
		rb.setAdHeight(readybox_vo.getAdHeight());
		rb.setAdPlanCycleId(readybox_vo.getAdPlanCycleId());
		rb.setAdPlanId(readybox_vo.getAdPlanId());
		rb.setAdWidth(readybox_vo.getAdWidth());
		rb.setAreaFix(readybox_vo.getAreaFix());
		rb.setDiscount(readybox_vo.getDiscount());
		rb.setisURLBand(readybox_vo.getIsUrlBand());
		rb.setIsValid(readybox_vo.getIsValid());
		rb.setModelJS(readybox_vo.getModelJS());
		rb.setPayTypeId(readybox_vo.getPayTypeId());
		rb.setReadyBoxId(readybox_vo.getReadyBoxId());
		rb.setReadyBoxStatus(readybox_vo.getReadyBoxStatus());
		rb.setShowIp(readybox_vo.getShowIp());
		rb.setShowIpString(readybox_vo.getShowIpString());
		rb.setUrlId(readybox_vo.getUrlId());
		rb.setURLList(readybox_vo.getUrlList());
		rb.setUserId(readybox_vo.getUserId());
		rb.setWebMasterId(readybox_vo.getWebMasterId());
		rb.setWebMasterPrice(Float.valueOf(readybox_vo.getWebMasterPrice()));
		return rb;
	}

	public static void main(String[] args) throws Exception {
		ReadyBoxUtil r = new ReadyBoxUtil();
		r.save2MemCached("60_342");
	}
}