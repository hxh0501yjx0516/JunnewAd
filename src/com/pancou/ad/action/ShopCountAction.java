package com.pancou.ad.action;

import javax.persistence.Entity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.ShopCount;
import com.pancou.ad.vo.ShopSource;

@Entity
public class ShopCountAction extends BaseDispatchAction  {
	
	/**
	 * ���ͳ��	table:ShopCount
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	public ActionForward countList(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			//��ҳ
			int pageNum = 0;
			int numPerPage = 20;
			if ("pager".equals(this.getParameter("flag"))){
				pageNum	= Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			
			String strsql = "select s.* from shopcount s where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(this.getParameter("shopWebId"))){
				condition += " and s.shopWebId =:shopwebid";
				parms.put("shopwebid", Integer.parseInt(this.getParameter("shopWebId")));
			}
			if (!"".equals(this.getParameter("shopfclassid"))){
				condition += " and s.shopFclassId =:shopfclassid";
				parms.put("shopfclassid", Integer.parseInt(this.getParameter("shopfclassid")));
			}
			if (!"".equals(this.getParameter("shopsclassid"))){
				condition += " and s.shopSclassId =:shopsclassid";
				parms.put("shopsclassid", Integer.parseInt(this.getParameter("shopsclassid")));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(ShopCount.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(ShopCount.class, condition, parms));
			List<ShopCount> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql + condition, parms, cols);
			if (list != null && list.size() > 0){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "shopCountList", list));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		this.getShopWebList(request);
		this.getShopFclassList(request);
		this.getShopSclassList(request);
		request.setAttribute("shopwebid", this.getParameter("shopwebid"));
		request.setAttribute("shopfclassid", this.getParameter("shopfclassid"));
		request.setAttribute("shopsclassid", this.getParameter("shopsclassid"));
		return mapping.findForward("countList");
	}
	
	
	/**
	 * ��Դ��ַ	table:ShopSource
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sourceList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			//��ҳ
			int pageNum = 0;
			int numPerPage = 20;
			if ("pager".equals(this.getParameter("flag"))){
				pageNum	= Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			String strsql = "select s.* from shopSource s where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(this.getParameter("shopWebId"))){
				condition += " and s.shopWebId =:shopwebid";
				parms.put("shopwebid", Integer.parseInt(this.getParameter("shopWebId")));
			}
			if (!"".equals(this.getParameter("shopfclassid"))){
				condition += " and s.shopFclassId =:shopfclassid";
				parms.put("shopfclassid", Integer.parseInt(this.getParameter("shopfclassid")));
			}
			if (!"".equals(this.getParameter("shopsclassid"))){
				condition += " and s.shopSclassId =:shopsclassid";
				parms.put("shopsclassid", Integer.parseInt(this.getParameter("shopsclassid")));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(ShopSource.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(ShopSource.class, condition, parms));
			List<ShopSource> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql + condition, parms, cols);
			if (list != null && list.size() > 0){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "shopSourceList", list));
			}
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		this.getShopWebList(request);
		this.getShopFclassList(request);
		this.getShopSclassList(request);
		request.setAttribute("shopwebid", this.getParameter("shopwebid"));
		request.setAttribute("shopfclassid", this.getParameter("shopfclassid"));
		request.setAttribute("shopsclassid", this.getParameter("shopsclassid"));
		return mapping.findForward("sourceList");
	}
	
}
