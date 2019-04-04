package com.pancou.ad.action;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.CmsClass;
import com.pancou.ad.vo.Customer;

@Entity
public class CmsClassAction extends BaseDispatchAction{
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// ҳ��
		int numPerPage = 20;//ÿҳ��ʾ����
		if ("pager".equals(this.getParameter("flag"))) {
			// ��ҳ
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(CmsClass.class, "",""));
		List qryList = dao.findAll(CmsClass.class,pageMap.get("first"), pageMap.get("size"));
		if (qryList != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", qryList));
		}
		return mapping.findForward("list");
	}
	/**
	 * ���ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			// ���
			String[] pname = new String[1];
			String[] pvalue = new String[1];
			pname[0] = "cmsClassName";
			pvalue[0] = this.getParameter("name");
			List userslist = dao.findAll(CmsClass.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// ���û����Ѵ���
				this.buildAjaxResult(request, FAILURE_STATUS, "�������Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			CmsClass cmsClass = new CmsClass();
			cmsClass.setCmsClassName(this.getParameter("name"));
			try {
				dao.save(cmsClass);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"�������");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("ajaxDone");
		}
		return mapping.findForward("add");
	}
	/**
	 * �޸�ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			CmsClass cmsClass = (CmsClass)dao.findById(CmsClass.class, Integer.parseInt(this.getParameter("cid")));
			if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("cmsClass", cmsClass);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "cmsClassName";
				pvalue[0] = this.getParameter("name");
				List userslist = dao.findAll(CmsClass.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("cid")) != ((CmsClass)userslist.get(0)).getCmsClassId()){
						// ���û����Ѵ���
						this.buildAjaxResult(request, FAILURE_STATUS, "�������Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				cmsClass.setCmsClassName(this.getParameter("name"));
				if(dao.update(cmsClass))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"�������");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
	/**
	 * ɾ��ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			CmsClass cmsClass = (CmsClass)dao.findById(CmsClass.class, Integer.parseInt(this.getParameter("cid")));
			dao.delete(cmsClass);
			this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
