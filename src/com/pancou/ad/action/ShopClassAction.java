package com.pancou.ad.action;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.vo.ShopFclass;
import com.pancou.ad.vo.ShopSclass;
import com.pancou.ad.vo.Users;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.DatetimeHandle;


import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;



@Entity
public class ShopClassAction extends BaseDispatchAction {
	
	
	/**
	 * ����Ŀ�б�	table: ShopFClass
	 * @param mapping
	 * @param form
	 * @param resquest
	 * @param response
	 * @return
	 */
	public ActionForward fClassList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			int pageNum = 0;// ҳ��
			int numPerPage = 20;//ÿҳ��ʾ����
			if ("pager".equals(this.getParameter("flag"))) {
				// ��ҳ
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			String sql = "select sf.* from shopFClass sf where 1 = 1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(ShopFclass.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(ShopFclass.class, condition, parms));
			List<ShopFclass> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sql+condition, parms, cols);
			
			if (list != null){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("fClassList");
	}
	
	
	/**
	 * ����Ŀ���	table: ShopFClass
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fClassAdd(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String flag = this.getParameter("flag");
			if ("save".equals(flag)){
				String[] pname = {"shopfclassname"};
				String[] pvalue = {this.getParameter("shopfclassname")};
				
				List<ShopFclass> sf = dao.findAll(ShopFclass.class, pname, pvalue);
				if (sf.size() > 0) {
					this.buildAjaxResult(request, this.FAILURE_STATUS, "����Ѵ���","closeCurrent");
					return mapping.findForward("ajaxDone");
				} else {
					ShopFclass shopfclass = new ShopFclass();
					shopfclass.setShopFclassName(this.getParameter("shopfclassname"));
					shopfclass.setAddTime(DatetimeHandle.formatCurrentDate());
					dao.save(shopfclass);
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "��ӳɹ�","closeCurrent","��Ӹ���Ŀ");
					return mapping.findForward("ajaxDone"); 
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
		}
		
		return mapping.findForward("fClassAdd");
	}
	
	
	/**
	 * ����Ŀ�༭	table:ShopFClass
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fClassEdit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String flag = this.getParameter("flag");
			int shopFclassId = Integer.parseInt(this.getParameter("shopfclassid"));
			String shopFclassName = this.getParameter("shopfclassname");
			
			ShopFclass sf = (ShopFclass)dao.findById(ShopFclass.class, shopFclassId);
			if ("edit".equals(flag)){
				sf.setShopFclassName(shopFclassName);
				dao.update(sf);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�","closeCurrent","�༭����Ŀ");
				return mapping.findForward("ajaxDone");
			} else {
				request.setAttribute("shopfclass", sf);
				return mapping.findForward("fClassEdit");
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return mapping.findForward("ajaxDone");
		
	}
	
	/**
	 * ����Ŀ�б� 	talbe:ShopSClass
	 * @param form
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sClassList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			int pageNum = 0;// ҳ��
			int numPerPage = 20;//ÿҳ��ʾ����
			if ("pager".equals(this.getParameter("flag"))) {
				// ��ҳ
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			String strsql = "select s.*,sf.shopFclassName from ShopSClass s left join ShopFClass sf on s.shopFclassId = sf.shopFclassId where 1= 1";
			String condition = "";
			
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(this.getParameter("shopsclassname"))){
				condition += " and s.shopSclassName like :shopsclassname";
				parms.put("shopsclassname", "%"+this.getParameter("shopsclassname")+"%");
			}
			if (!"".equals(this.getParameter("shopfclassid"))){
				condition += " and s.shopFclassId = :shopfclassid";
				parms.put("shopfclassid", Integer.parseInt(this.getParameter("shopfclassid")));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(ShopSclass.class);
			cols.add("shopFclassName");
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(ShopSclass.class, condition, parms));
			List qrylist = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql + condition, parms, cols);
			List<ShopSclass> list = new ArrayList<ShopSclass>();
			
			for(int i = 0; i < qrylist.size(); i++){
				Object[] obj = (Object[])qrylist.get(i);
				ShopSclass shopSclass = (ShopSclass)obj[0];
				shopSclass.setShopFclassName(obj[1]+"");
				list.add(shopSclass);
			}
			
			if (list != null){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "shopsclasslist", list));
			}	
			
		} catch (Exception e){
			e.printStackTrace();
		}
		this.getShopFclassList(request);
		request.setAttribute("shopsclassname", this.getParameter("shopsclassname"));
		request.setAttribute("shopfclassid", this.getParameter("shopfclassid"));
		return mapping.findForward("sClassList");
	}
	
	/**
	 * ����Ŀ���	table: ShopSClass
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sClassAdd(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		String flag = this.getParameter("flag");
		if ("save".equals(flag)){
			try{
				String[] pname = {"shopsclassname"};
				String[] pvalue = {this.getParameter("shopsclassname")};
				List<ShopSclass> ss = dao.findAll(ShopSclass.class, pname, pvalue);
				
				if (ss.size() > 0){
					this.buildAjaxResult(request, this.FAILURE_STATUS, "����Ѵ���");
				} else {
					ShopSclass shopSClass = new ShopSclass();
					shopSClass.setShopFclassId(Integer.parseInt(this.getParameter("shopfclassid")));
					shopSClass.setShopSclassName(this.getParameter("shopsclassname"));
					shopSClass.setAddTime(DatetimeHandle.formatCurrentDate());
					dao.save(shopSClass);
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "��ӳɹ�", "closeCurrent");
					return mapping.findForward("ajaxDone");
				}	
			} catch (Exception e){
				e.printStackTrace();
			}
		}	
		this.getShopFclassList(request);
		return mapping.findForward("sClassAdd");
	}
	
	
	/**
	 * ����Ŀ�༭	table:ShopSClass
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sClassEdit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int shopSclassId = Integer.parseInt(this.getParameter("shopsclassid"));
		ShopSclass shopSclassList = (ShopSclass)dao.findById(ShopSclass.class, shopSclassId);
		
		try{
			if(!"save".equals(this.getParameter("flag"))){
				request.setAttribute("shopsclasslist", shopSclassList);
			} else {
				shopSclassList.setShopFclassId(Integer.parseInt(this.getParameter("shopfclassid")));
				shopSclassList.setShopSclassName(this.getParameter("shopsclassname"));
				shopSclassList.setAddTime(DatetimeHandle.formatCurrentDate());
				dao.update(shopSclassList);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�", "closeCurrent");
				return mapping.findForward("sClassList");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		this.getShopFclassList(request);
		return mapping.findForward("sClassEdit");
	}
}
