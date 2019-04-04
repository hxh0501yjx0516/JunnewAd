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
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdSize;

@Entity
public class AdSizeAction extends BaseDispatchAction{
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
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdSize.class, "",""));
		List list = dao.findAll(AdSize.class,pageMap.get("first"), pageMap.get("size"));
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
			String[] pname = {"adwidth","adheight"};
			String[] pvalue = {this.getParameter("width"),this.getParameter("height")};
			List userslist = dao.findAll(AdSize.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// ���óߴ��Ѵ���
				this.buildAjaxResult(request, FAILURE_STATUS, "�óߴ��Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			AdSize adSize = new AdSize();
			adSize.setAdWidth(Integer.parseInt(this.getParameter("width")));
			adSize.setAdHeight(Integer.parseInt(this.getParameter("height")));
			adSize.setAdSizeStatus(Integer.parseInt(this.getParameter("state")));
			try {
				dao.save(adSize);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"���ߴ�");
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
			AdSize adSize = (AdSize)dao.findById(AdSize.class, Integer.parseInt(this.getParameter("sid")));
			if ("editState".equals(this.getParameter("flag"))) {
				adSize.setAdSizeStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(adSize))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("adSize", adSize);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[2];
				String[] pvalue = new String[2];
				pname[0] = "AdWidth";
				pvalue[0] = this.getParameter("width");
				pname[1] = "AdHeight";
				pvalue[1] = this.getParameter("height");
				List userslist = dao.findAll(AdSize.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("sid")) != ((AdSize)userslist.get(0)).getAdSizeId()){
						// ���û����Ѵ���
						this.buildAjaxResult(request, FAILURE_STATUS, "�óߴ��Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				adSize.setAdWidth(Integer.parseInt(this.getParameter("width")));
				adSize.setAdHeight(Integer.parseInt(this.getParameter("height")));
				adSize.setAdSizeStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(adSize))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"���ߴ�");
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
			AdSize adSize = (AdSize)dao.findById(AdSize.class, Integer.parseInt(this.getParameter("sid")));
			dao.delete(adSize);
			this.buildAjaxResult(request,SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
