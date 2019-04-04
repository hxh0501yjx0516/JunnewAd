package com.pancou.ad.action;

import java.util.ArrayList;
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
import com.pancou.ad.vo.AdBoxInfo;

@Entity
public class AdBoxInfoAction extends BaseDispatchAction{
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
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdBoxInfo.class, "",""));
		String sql = "select ai.*,m.modelName,s.adWidth,s.adHeight from adboxInfo ai left join models m on ai.modelId = m.modelId left join adSize s on " +
				" ai.adSizeId = s.adSizeId  order by ai.adBoxInfoId desc";
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql);
		List<AdBoxInfo> list = new ArrayList<AdBoxInfo>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			AdBoxInfo adBoxInfo = new AdBoxInfo();
			adBoxInfo.setAdBoxInfoId(Integer.parseInt(object[0]+""));
			adBoxInfo.setAdBoxInfoName(object[1]+"");
			adBoxInfo.setAdSizeId(Integer.parseInt(object[2]+""));
			adBoxInfo.setModelId(Integer.parseInt(object[3]+""));
			adBoxInfo.setAdBoxInfoRemarks(object[4]+"");
			adBoxInfo.setAdBoxInfoStatus(Integer.parseInt(object[5]+""));
			adBoxInfo.setAddTime(object[6]+"");
			adBoxInfo.setModelName(object[7]+"");
			adBoxInfo.setAdSizeName(object[8]+"x"+object[9]);
			list.add(adBoxInfo);
		}
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			try {
			// ���
			AdBoxInfo adBoxInfo = new AdBoxInfo();
			adBoxInfo.setAdBoxInfoName(this.getParameter("adBoxInfoName"));
			adBoxInfo.setAdSizeId(Integer.parseInt(this.getParameter("adSizeId")));
			adBoxInfo.setModelId(Integer.parseInt(this.getParameter("modelId")));
			adBoxInfo.setAdBoxInfoRemarks(this.getParameter("adBoxInfoRemarks"));
			adBoxInfo.setAdBoxInfoStatus(Integer.parseInt(this.getParameter("adBoxInfoState")));
			adBoxInfo.setAddTime(DatetimeHandle.formatCurrentDate());
			
				dao.save(adBoxInfo);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"���λ���");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getModelList(request);
		this.getAdSizeList(request);
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
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			AdBoxInfo adBoxInfo = (AdBoxInfo)dao.findById(AdBoxInfo.class, Integer.parseInt(this.getParameter("cid")));
			if ("editState".equals(this.getParameter("flag"))) {
				adBoxInfo.setAdBoxInfoStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(adBoxInfo))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				this.getModelList(request);
				this.getAdSizeList(request);
				request.setAttribute("adBoxInfo", adBoxInfo);
				return mapping.findForward("edit");
			}else{
				adBoxInfo.setAdBoxInfoName(this.getParameter("adBoxInfoName"));
				adBoxInfo.setAdSizeId(Integer.parseInt(this.getParameter("adSizeId")));
				adBoxInfo.setModelId(Integer.parseInt(this.getParameter("modelId")));
				adBoxInfo.setAdBoxInfoRemarks(this.getParameter("adBoxInfoRemarks"));
				adBoxInfo.setAdBoxInfoStatus(Integer.parseInt(this.getParameter("adBoxInfoState")));
				if(dao.update(adBoxInfo))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"���λ���");
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
			AdBoxInfo adBoxInfo = (AdBoxInfo)dao.findById(AdBoxInfo.class, Integer.parseInt(this.getParameter("cid")));
			dao.delete(adBoxInfo);
			this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
