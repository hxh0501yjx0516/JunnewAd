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
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.ShopWeb;




@Entity
public class ShopWebAction extends BaseDispatchAction {

	/**
	 * ����վ���б�	table:ShopWeb
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try{
			int pageNum = 0;
			int numPerPage = 20;
			if ("pager".equals(this.getParameter("flag"))){
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			String strsql = "select s.* from shopweb s where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(this.getParameter("shopwebname"))){
				condition += " and s.shopwebname like:shopwebname";
				parms.put("shopwebname", this.getParameter("shopwebname"));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(ShopWeb.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(ShopWeb.class, condition, parms));
			List<ShopWeb> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql + condition, parms, cols);
			
			if(list != null){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		request.setAttribute("shopwebname", this.getParameter("shopwebname"));
		return mapping.findForward("list");
	} 
	
	/**
	 * ����վ�����	table:ShopWeb
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward add(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			if ("save".equals(this.getParameter("flag"))){
				//��ȡ�?
				String shopWebName = this.getParameter("shopwebname");
				String shopWebUrl = this.getParameter("shopweburl");
				String shopWebContact = this.getParameter("shopwebcontact");
				String shopWebTel = this.getParameter("shopwebtel");
				String shopWebQq = this.getParameter("shopwebqq");
				int shopWebStatus = Integer.parseInt(this.getParameter("shopwebstatus"));
				
				String[] pname = new String[]{"shopWebName","shopWebUrl"};
				String[] pvalue = new String[]{shopWebName,shopWebUrl};
				List<ShopWeb> list = dao.findAll(ShopWeb.class, pname, pvalue);
				if (list.size() > 0){
					this.buildAjaxResult(request, this.FAILURE_STATUS, "��վ���Ѵ���","closeCurrent");
					return mapping.findForward("ajaxDone");
				} else {
					ShopWeb sw = new ShopWeb();
					sw.setShopWebName(shopWebName);
					sw.setShopWebUrl(shopWebUrl);
					sw.setShopWebContact(shopWebContact);
					sw.setShopWebTel(shopWebTel);
					sw.setShopWebQq(shopWebQq);
					sw.setShopWebStatus(shopWebStatus);
					sw.setAddTime(DatetimeHandle.formatCurrentDate());
					dao.save(sw);
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "��ӳɹ�",this.CALLBACKTYPE_CLOSECURRENT,"����վ��");
					return mapping.findForward("ajaxDone"); 
				}
				
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("add");
	}
	
	/**
	 * ����վ��༭	table:ShopWeb
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			ShopWeb sw = (ShopWeb)dao.findById(ShopWeb.class, Integer.parseInt(this.getParameter("shopwebid")));
			if ("save".equals(this.getParameter("flag"))){
				//��ȡ�?
				String shopWebName = this.getParameter("shopwebname");
				String shopWebUrl = this.getParameter("shopweburl");
				String shopWebContact = this.getParameter("shopwebcontact");
				String shopWebTel = this.getParameter("shopwebtel");
				String shopWebQq = this.getParameter("shopwebqq");
				int shopWebStatus = Integer.parseInt(this.getParameter("shopwebstatus"));
				
				//�������
				sw.setShopWebName(shopWebName);
				sw.setShopWebUrl(shopWebUrl);
				sw.setShopWebContact(shopWebContact);
				sw.setShopWebTel(shopWebTel);
				sw.setShopWebQq(shopWebQq);
				sw.setShopWebStatus(shopWebStatus);
				dao.update(sw);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�",this.CALLBACKTYPE_CLOSECURRENT,"����վ��");
				return mapping.findForward("ajaxDone");
			} else {
				request.setAttribute("shopWeb", sw);
				return mapping.findForward("edit");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}	
}
