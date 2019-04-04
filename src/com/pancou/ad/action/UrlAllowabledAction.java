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
import com.pancou.ad.vo.AdSize;
import com.pancou.ad.vo.UrlAllowabled;
import com.pancou.ad.vo.Users;

@Entity
public class UrlAllowabledAction extends BaseDispatchAction{
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
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(UrlAllowabled.class, "",""));
		List list = dao.findAllBySql(UrlAllowabled.class,pageMap.get("first"), pageMap.get("size")," 1=1 order by urlId desc");
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
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
			String[] pname = {"url"};
			String[] pvalue = {this.getParameter("urlAddress")};
			List userslist = dao.findAll(UrlAllowabled.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// ���óߴ��Ѵ���
				this.buildAjaxResult(request, FAILURE_STATUS, "�õ�ַ�Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			Users users = (Users)request.getSession().getAttribute("user");
			UrlAllowabled urlAllow = new UrlAllowabled();
			urlAllow.setUrlName(this.getParameter("urlName"));
			String webadd = this.getParameter("urlAddress");
			if(!webadd.startsWith("http://")){
				webadd = "http://"+webadd;
			}
			urlAllow.setUrl(webadd);
			urlAllow.setUserId(users.getUserId());
			urlAllow.setUserName(users.getRealname());
			urlAllow.setAddTime(DatetimeHandle.formatCurrentDate());
			try {
				dao.save(urlAllow);
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
			UrlAllowabled urlAllow = (UrlAllowabled)dao.findById(UrlAllowabled.class, Integer.parseInt(this.getParameter("uid")));
			 if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("urlAllow", urlAllow);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "url";
				pvalue[0] = this.getParameter("urlAddress");
				List userslist = dao.findAll(UrlAllowabled.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("uid")) != ((UrlAllowabled)userslist.get(0)).getUrlId()){
						// ���û����Ѵ���
						this.buildAjaxResult(request, FAILURE_STATUS, "�õ�ַ�Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				Users users = (Users)request.getSession().getAttribute("user");
				urlAllow.setUrlName(this.getParameter("urlName"));
				String webadd = this.getParameter("urlAddress");
				if(!webadd.startsWith("http://")){
					webadd = "http://"+webadd;
				}
				urlAllow.setUrl(webadd);
				urlAllow.setUserId(users.getUserId());
				urlAllow.setUserName(users.getRealname());
//				urlAllow.setAddTime(DatetimeHandle.formatCurrentDate());
				if(dao.update(urlAllow))
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
			UrlAllowabled urlAllow = (UrlAllowabled)dao.findById(UrlAllowabled.class, Integer.parseInt(this.getParameter("uid")));
			dao.delete(urlAllow);
			this.buildAjaxResult(request,SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
