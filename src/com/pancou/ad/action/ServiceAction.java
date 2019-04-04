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
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.Service;

@Entity
public class ServiceAction extends BaseDispatchAction{
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
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(Service.class, "",""));
		List list = dao.findAll(Service.class,pageMap.get("first"), pageMap.get("size"));
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
			String[] pname = new String[1];
			String[] pvalue = new String[1];
			pname[0] = "qq";
			pvalue[0] = this.getParameter("qq");
			List userslist = dao.findAll(Service.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// ���û����Ѵ���
				this.buildAjaxResult(request, FAILURE_STATUS, "��QQ�Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			Service service = new Service();
			service.setName(this.getParameter("name"));
			service.setQq(Integer.parseInt(this.getParameter("qq")));
			service.setTypeId(Integer.parseInt(this.getParameter("typeId")));
			service.setStatus(Integer.parseInt(this.getParameter("state")));
			try {
				dao.save(service);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"�ͷ��б�");
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
			Service service = (Service)dao.findById(Service.class, Integer.parseInt(this.getParameter("sid")));
			if ("editState".equals(this.getParameter("flag"))) {
				service.setStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(service))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("service", service);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "qq";
				pvalue[0] = this.getParameter("qq");
				List userslist = dao.findAll(Service.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("sid")) != ((Service)userslist.get(0)).getId()){
						// ���û����Ѵ���
						this.buildAjaxResult(request, FAILURE_STATUS, "��QQ�Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				service.setName(this.getParameter("name"));
				service.setQq(Integer.parseInt(this.getParameter("qq")));
				service.setTypeId(Integer.parseInt(this.getParameter("typeId")));
				service.setStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(service))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"�ͷ��б�");
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
			Service service = (Service)dao.findById(Service.class, Integer.parseInt(this.getParameter("sid")));
			dao.delete(service);
			this.buildAjaxResult(request,SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
