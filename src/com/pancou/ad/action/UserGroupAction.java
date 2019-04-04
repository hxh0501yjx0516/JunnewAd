package com.pancou.ad.action;

import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.vo.UserGroup;


@Entity
public class UserGroupAction extends BaseDispatchAction{
	/**
	 * list table:UserGroup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;
		int numPerPage = 20;				
		if ("paper".equals(this.getParameter("flag"))){
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		
		List<UserGroup> list = dao.findAll(UserGroup.class);	
		if (list != null && list.size() > 0){
			request.setAttribute("list", list);
		}
		
		//�޸�״̬
		if("editStatus".equals(this.getParameter("flag"))){
			UserGroup ug = (UserGroup)dao.findById(UserGroup.class, Integer.parseInt(this.getParameter("id")));
			if (ug.getUserGroupStatus() == 0){
				ug.setUserGroupStatus(1);
			} else {
				ug.setUserGroupStatus(0);
			}
			dao.update(ug);
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�");
		}
		
		return mapping.findForward("list");
	}
	
	/**
	 * add	table:UserGroup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("save".equals(this.getParameter("flag"))){
			String[] pname = {"userGroupName"};
			String[] pvalue = {this.getParameter("usergroupname")};
			List<UserGroup> list = dao.findAll(UserGroup.class, pname, pvalue);
			if (list != null && list.size() > 0){
				this.buildAjaxResult(request,this.FAILURE_STATUS , "������Ѵ���", this.CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			
			UserGroup ug = new UserGroup();
			ug.setUserGroupName(this.getParameter("usergroupname"));
			ug.setUserGroupStatus(Integer.parseInt(this.getParameter("usergroupstatus")));
			try{
				dao.save(ug);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�",this.CALLBACKTYPE_CLOSECURRENT,"�޸��û���");
			} catch (Exception e){
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("ajaxDone");
			

		}
		return mapping.findForward("add");
	}
	
	/**
	 * edit table:UserGroup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			UserGroup ug = (UserGroup)dao.findById(UserGroup.class, Integer.parseInt(this.getParameter("id")));
			if ("edit".equals(this.getParameter("flag"))){
				request.setAttribute("usergroup", ug);
				return mapping.findForward("edit");
			} else if ("editsave".equals(this.getParameter("flag"))){
				ug.setUserGroupName(this.getParameter("usergroupname"));
				ug.setUserGroupStatus(Integer.parseInt(this.getParameter("usergroupstatus")));
				dao.update(ug);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�",this.CALLBACKTYPE_CLOSECURRENT,"�û���");
				return mapping.findForward("ajaxDone");
					
			}
		} catch (Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ��");
		}
				
		return mapping.findForward("ajaxDone");
	}

}
