package com.pancou.ad.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

@Entity
public class MemCachedUtil_wap3 {
	//public static Logger log = Logger.getLogger("mem");
	public static String[] serverlist = {Configure.get("memCached.server.third")};
	public static SockIOPool pool = SockIOPool.getInstance();
	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static {
		pool.setServers(serverlist);
		// pool.setWeights(new Integer[] { 3 });
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(100);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.initialize();
	}
	public static MemCachedClient mc = new MemCachedClient();
	static {
		mc.setCompressEnable(true);
		mc.setCompressThreshold(4096);
		mc.setDefaultEncoding("GBK");
		mc.setPrimitiveAsString(true);

	}

	public static synchronized boolean set(String key, Object value, Date expiry) {
		//System.out.println("5:set innter:"+key+",over,content is "+value.toString()+"\n");
		boolean bRet = false;
		//System.out.println("memcacheUtil----key:"+key);
		if (!mc.keyExists(key)) {
			if (expiry != null) {
				bRet = mc.add(key, value, expiry);
			} else {
				bRet = mc.add(key, value);
			}
			if (bRet) {
				//log.debug(String.format("%s add succ,expiry:%s!", key, sdf.format(expiry)));
				System.out.println(String.format("%s add succ", key));
			} else {
				System.out.println(String.format("%s ERROR: add fail!", key));

			}
		} else {

			if (expiry != null) {
				bRet = mc.replace(key, value, expiry);
			} else {
				bRet = mc.replace(key, value);
			}
			if (bRet) {
				System.out.println(String.format("%s replace succ!", key));
			} else {
				System.out.println(String.format("%s ERROR: replace fail!", key));
			}
		}
		return bRet;
	}

	public static synchronized Object get(String key) {
		return mc.get(key);
	}
	
	public static synchronized boolean remove(String key) {
		//System.out.println("2:remove key:"+key);
		return mc.delete(key);
	}

	public static boolean isExists(String key) {
		return mc.keyExists(key);
	}


	public static void main(String[] args) throws Exception {
		String key="24_36";
		//String s=(String)MemCachedUtil_45.get(key);
		System.out.println(MemCachedUtil_45.get(key));
		//System.out.println(MemCachedUtil_45.remove(key));
		//System.out.println(s);

	}
}
