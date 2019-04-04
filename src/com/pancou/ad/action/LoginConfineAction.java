package com.pancou.ad.action;

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
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdSize;
import com.pancou.ad.vo.LoginIp;
import com.pancou.ad.vo.UrlAllowabled;
import com.pancou.ad.vo.Users;

@Entity
public class LoginConfineAction extends BaseDispatchAction{
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
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		if(!"".equals(this.getParameter("qrybegintime"))){
			condition += " and l.addTime >= :qrybegintime";
			parms.put("qrybegintime", this.getParameter("qrybegintime"));
		}if(!"".equals(this.getParameter("qryendtime"))){
			condition += " and l.addTime <= :qryendtime";
			parms.put("qryendtime", this.getParameter("qryendtime")+" 23:59:59");
		}if(!"".equals(this.getParameter("qryIp"))){
			condition += " and l.loginIp like :qryIp";
			parms.put("qryIp", "%"+this.getParameter("qryIp")+"%");
		}if(!"".equals(this.getParameter("qrytype"))){
			condition += " and l.loginType = :qrytype";
			parms.put("qrytype", Integer.parseInt(this.getParameter("qrytype")));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(LoginIp.class,condition,parms));
		List list = dao.findAll(pageMap.get("first"), pageMap.get("size"),"from LoginIp l where 1=1 ",condition,parms);
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("qrybegintime",this.getParameter("qrybegintime"));
		request.setAttribute("qryendtime",this.getParameter("qryendtime"));
		request.setAttribute("qryIp",this.getParameter("qryIp"));
		request.setAttribute("qrytype",this.getParameter("qrytype"));
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
//			String[] pname = {"loginIp"};
//			String[] pvalue = {this.getParameter("qryIp")};
			List<LoginIp> userslist = dao.findAll(LoginIp.class);
			if (userslist != null && userslist.size() > 0) {
				for(LoginIp loginIp :userslist){
					if(this.getParameter("qryIp").matches(loginIp.getLoginIp())){
						this.buildAjaxResult(request, FAILURE_STATUS, "��IP��ַ��IP���Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
			}
			Users users = (Users)request.getSession().getAttribute("user");
			LoginIp loginIp = new LoginIp();
			loginIp.setLoginName(this.getParameter("qryLoginName"));
			loginIp.setLoginIp(this.getParameter("qryIp"));
			loginIp.setLoginType(Integer.parseInt(this.getParameter("qryType")));
			loginIp.setUserId(users.getUserId());
			loginIp.setUserName(users.getRealname());
			loginIp.setAddTime(DatetimeHandle.formatCurrentDate());
			try {
				dao.save(loginIp);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"��½����");
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
			LoginIp loginIp = (LoginIp)dao.findById(LoginIp.class, Integer.parseInt(this.getParameter("uid")));
			 if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("loginIp", loginIp);
				return mapping.findForward("edit");
			}else{
//				String[] pname = new String[1];
//				String[] pvalue = new String[1];
//				pname[0] = "loginIp";
//				pvalue[0] = this.getParameter("qryIp");
				List<LoginIp> userslist = dao.findAll(LoginIp.class);
				if (userslist != null && userslist.size() > 0) {
					for(LoginIp loginIps :userslist){
						if(!this.getParameter("uid").equals(loginIps.getLoginId()+"") && this.getParameter("qryIp").matches(loginIps.getLoginIp())){
							this.buildAjaxResult(request, FAILURE_STATUS, "��IP��ַ��IP���Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
							return mapping.findForward("ajaxDone");
						}
					}
				}
				Users users = (Users)request.getSession().getAttribute("user");
				loginIp.setLoginName(this.getParameter("qryLoginName")); 
				loginIp.setLoginIp(this.getParameter("qryIp"));
				loginIp.setLoginType(Integer.parseInt(this.getParameter("qryType")));
				loginIp.setUserId(users.getUserId());
				loginIp.setUserName(users.getRealname());
				if(dao.update(loginIp))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"��½����");
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
