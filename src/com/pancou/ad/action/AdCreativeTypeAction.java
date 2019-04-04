package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdCreativeType;
import com.pancou.ad.vo.Models;

@Entity
public class AdCreativeTypeAction extends BaseDispatchAction {

	/**
	 * AdCretiveType List		Table : AdCreativeType
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		int pageNum = 0;
		int numPerPage = 20;
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		
		String sql = "select a.* from AdCreativeType a where 1=1";
		String condition = "";
		Map<String,Object> parms = new 	HashMap<String,Object>();
		if (!"".equals(this.getParameter("adCreativeTypeMid"))) {
			condition += " and a.adCreativeTypeId =:adCreativeTypeMid or a.adCreativeTypeMid =:adCreativeTypeMid";
			parms.put("adCreativeTypeMid", Integer.parseInt(this.getParameter("adCreativeTypeMid")));
		}
		
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(AdCreativeType.class, condition, parms));
		List qrylist = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sql + condition, parms);
		List<AdCreativeType> list = new ArrayList<AdCreativeType>();
		for (Object object:qrylist) {
			Object[] o = (Object[])object;
			AdCreativeType adCreativeType = new AdCreativeType();
			adCreativeType.setAdCreativeTypeId(Integer.parseInt(o[0]+""));
			adCreativeType.setAdCreativeTypeName(o[1]+"");
			adCreativeType.setAdCreativeTypeTid(Integer.parseInt(o[2]+""));
			adCreativeType.setAdCreativeTypeMid(Integer.parseInt(o[3]+""));
			adCreativeType.setAdCreativeTypeMname(o[4]+"");
			list.add(adCreativeType);		
		}
		
		if (list != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
		}
		
		request.setAttribute("adCreativeTypeName", this.getParameter("adCreativeTypeName"));
		request.setAttribute("adCreativeTypeMid", this.getParameter("adCreativeTypeMid"));
		//父类型列表
		this.getAdCreativeTypeFather(request);
		return mapping.findForward("list");
	}

	/**
	 * add AdCreaTiveType	Table: AdCreativeType
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		if ("save".equals(this.getParameter("flag"))) {
			String[] adname = {"adCreativeTypeName"};
			String[] advalue = {this.getParameter("adCreativeTypeName")};
			List adlist = dao.findAll(AdCreativeType.class, adname, advalue);
			if (adlist != null && adlist.size() > 0) {
				this.buildAjaxResult(request, this.FAILURE_STATUS, "创意类型已存在");
				return mapping.findForward("ajaxDone");
			}
			
			AdCreativeType adCreativeType = new AdCreativeType();
			adCreativeType.setAdCreativeTypeName(this.getParameter("adCreativeTypeName"));
			adCreativeType.setAdCreativeTypeTid(Integer.parseInt(this.getParameter("adCreativeTypeTid")));

			if (!"0".equals(this.getParameter("adCreativeTypeMid"))) {
				adCreativeType.setAdCreativeTypeMid(Integer.parseInt(this.getParameter("adCreativeTypeMid").split("--")[0]));
				adCreativeType.setAdCreativeTypeMname(this.getParameter("adCreativeTypeMid").split("--")[1]);
			} else {
				adCreativeType.setAdCreativeTypeMid(0);
				adCreativeType.setAdCreativeTypeMname("");
			}
			try {
				dao.save(adCreativeType);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功", "closeCurrent", "创意类型");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward("ajaxDone");
		}
		
		//父类型列表
		this.getAdCreativeTypeFather(request);
		return mapping.findForward("add");
	}

	/**
	 * edit AdCreaTiveType	Table: AdCreativeType
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//父类型列表
		this.getAdCreativeTypeFather(request);
		
		AdCreativeType adCreativeType = (AdCreativeType)dao.findById(AdCreativeType.class, Integer.parseInt(this.getParameter("adCreativeTypeId")));
		
		if (!"save".equals(this.getParameter("flag"))) {
			request.setAttribute("adCreativeType", adCreativeType);
			return mapping.findForward("edit");
		} else {
			adCreativeType.setAdCreativeTypeName(this.getParameter("adCreativeTypeName"));
			adCreativeType.setAdCreativeTypeTid(Integer.parseInt(this.getParameter("adCreativeTypeTid")));
			if (this.getParameter("adCreativeTypeMid") != "0") {
				adCreativeType.setAdCreativeTypeMid(Integer.parseInt(this.getParameter("adCreativeTypeMid").split("--")[0]));
				adCreativeType.setAdCreativeTypeMname(this.getParameter("adCreativeTypeMid").split("--")[1]);
			} else {
				adCreativeType.setAdCreativeTypeMid(0);
				adCreativeType.setAdCreativeTypeMname("");
			}
			try {
				dao.update(adCreativeType);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！", this.CALLBACKTYPE_CLOSECURRENT, "创意类型");
				return mapping.findForward("ajaxDone");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败，请重试！");
			}
		}
		
		return mapping.findForward("list");
	}
	
}
