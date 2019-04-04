package com.pancou.ad.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.vo.Shop;
import com.pancou.ad.vo.ShopFclass;
import com.pancou.ad.vo.ShopSclass;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * 
 * @author Administrator
 *
 */
@Entity
public class ShopTemplete{
	
	private static Configuration cfg = null;
	/**
	 * ���ģ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void createTemplete(HttpServletRequest request,PlatDao dao){
		try {
			List<ShopFclass> fClassList = dao.findAll(ShopFclass.class);
			for(ShopFclass fClass : fClassList){
				Map<String,Object> parms = new HashMap<String, Object>();
				parms.put("fClassId", fClass.getShopFclassId());
				List<ShopSclass> sClassList = dao.findAll("from ShopSclass ", " where shopFclassId =:fClassId order by shopSclassId ", parms);
				fClass.setShopSclassList(sClassList);
				for(ShopSclass sClass : sClassList){
					Map<String,Object> parms1 = new HashMap<String, Object>();
					parms1.put("sClassId", sClass.getShopSclassId());
					List<Shop> shopList = dao.findAll("from Shop ", " where shopSclassId =:sClassId order by shopId ", parms1);
//					System.out.println("С��ID��"+sClass.getShopSclassId()+"---------------------С��Name:"+sClass.getShopSclassName());
					for(Shop shop:shopList){
						shop.setShopImg(Configure.get("img.url")+shop.getShopImg());
//						System.out.println("����ID��"+shop.getShopId()+"����Name:"+shop.getShopName()+";����Url"+shop.getShopUrl());
					}
					
					sClass.setShopList(shopList);
				}
			}
			 //�������  
			String realPath = request.getSession().getServletContext().getRealPath("/");
	        String dir = realPath+"/index.htm";
			String templetePath = Configure.get("shop.extend.templete.path");
        	if(!"".equals(templetePath.trim())){
	        	dir = templetePath.trim();
	        }
            PrintWriter out   = new PrintWriter( 
            		new BufferedWriter( 
            		new FileWriter(dir)  
                    )
                );
			initConfiguration(request);
			process(fClassList,"shop_templete",out);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ʼ��FreeMarker����
	 * @param request
	 */
	private static void initConfiguration (HttpServletRequest request) {
		if (cfg == null) {
	        //��ʼ��FreeMarker����
	        //����һ��Configurationʵ��
	        cfg = new Configuration();
	        //����FreeMarker��ģ���ļ�λ��
	        try {
		        String realPath = request.getSession().getServletContext().getRealPath("/");
		        String dir = realPath + File.separator + "userdata" + File.separator + "shop_template" + File.separator;
				cfg.setDirectoryForTemplateLoading(new File(dir));
				cfg.clearTemplateCache();
		        cfg.setEncoding(Locale.CHINESE, "UTF-8");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
	/**
	 * ���htm
	 * @param fClassList
	 * @param tempName
	 * @param osw
	 * @throws Exception
	 */
	public void process(List<ShopFclass> fClassList,String tempName,Writer osw)throws Exception
	{
		Template t = cfg.getTemplate(tempName+".ftl");
		
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("fClassList", fClassList);
        param.put("starUrl", Configure.get("shop.extend.statistics.path"));
        t.setEncoding("UTF-8");
        t.process(param, osw);
	}
}
