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
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.WebMaster;

@Entity
public class ReadyPlanAction extends BaseDispatchAction{
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
		
		String sql = "select * from readyPlan r where ";
		Map<String,Object> parms = new HashMap<String, Object>();
		String condition = " r.webMasterId = :webMasterId ";
		parms.put("webMasterId",Integer.parseInt(this.getParameter("webMasterId")));
		if(!"".equals(this.getParameter("qryname"))){
			condition +=" and r.adPlanName like :name ";
			parms.put("name","%"+this.getParameter("qryname")+"%");
		}
		if(!"".equals(this.getParameter("qrystate"))){
			condition +=" and readyPlanStatus =:state ";
			parms.put("state",Integer.parseInt(this.getParameter("qrystate")));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(ReadyPlan.class, " and "+condition,parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by r.readyPlanId desc",parms);
		List<ReadyPlan> list = new ArrayList<ReadyPlan>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			ReadyPlan readyPlan = new ReadyPlan();
			readyPlan.setReadyPlanId(Integer.parseInt(object[0]+""));
			readyPlan.setAdPlanId(Integer.parseInt(object[1]+""));
			readyPlan.setAdPlanName(object[2]+"");
			readyPlan.setWebMasterId(Integer.parseInt(object[3]+""));
			readyPlan.setWebMasterName(object[4]+"");
			readyPlan.setReadyPlanStatus(Integer.parseInt(object[5]+""));
			readyPlan.setAddTime(object[6]+"");
			list.add(readyPlan);
		}
		if (list != null ) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("webMasterId", this.getParameter("webMasterId"));
		request.setAttribute("qryname", this.getParameter("qryname"));
		request.setAttribute("qrystate", this.getParameter("qrystate"));
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
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			// ���
			ReadyPlan readyPlan = new ReadyPlan();
			AdPlan adPlan=(AdPlan)dao.findById(AdPlan.class, Integer.parseInt(this.getParameter("adPlanId").split("\\-")[0]));
			String adPlanName = "";
			if(adPlan != null){
				adPlanName = adPlan.getAdPlanName();
			}
			readyPlan.setAdPlanName(adPlanName);
			readyPlan.setAdPlanId(Integer.parseInt(this.getParameter("adPlanId").split("\\-")[0]));
			readyPlan.setWebMasterId(Integer.parseInt(this.getParameter("webMasterId")));
			WebMaster webMaster = (WebMaster)dao.findById(WebMaster.class, Integer.parseInt(this.getParameter("webMasterId")));
			readyPlan.setWebMasterName(webMaster.getWebMasterName());
			readyPlan.setReadyPlanStatus(Integer.parseInt(this.getParameter("readyPlanState")));
			readyPlan.setAddTime(DatetimeHandle.formatCurrentDate());
			try {
				dao.save(readyPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���",this.CALLBACKTYPE_CLOSECURRENT,"���ƻ��б�");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("ajaxDone");
		}
		Map<String,Object> parms = new HashMap<String, Object>();
		parms.put("webMasterId", Integer.parseInt(this.getParameter("webMasterId")));
		List<Object> cols = new ArrayList<Object>();
		cols.add(AdPlan.class);
		String sql = "select * from adPlan a where a.adPlanId not in (select rp.adPlanId from readyPlan rp where rp.webMasterId = :webMasterId)";
		if (this.getUserSession(request).getUserGroup() != 0){
			sql += " and a.userGroup = "+this.getUserSession(request).getUserGroup();
		}
		List<AdPlan> adPlanList = dao.findBySql(sql, parms,cols);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < adPlanList.size(); i++){
			sb.append(adPlanList.get(i).getAdPlanId()).append("-").append(adPlanList.get(i).getAdPlanName()).append("|");
		}
		sb.setLength(sb.length() - 1);
		request.setAttribute("planString", sb.toString());
		request.setAttribute("webMasterId", this.getParameter("webMasterId"));
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
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			ReadyPlan readyPlan = (ReadyPlan)dao.findById(ReadyPlan.class, Integer.parseInt(this.getParameter("readyPlanId")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("readyPlanId",readyPlan.getReadyPlanId());
			params.put("webMasterId",readyPlan.getWebMasterId());
			params.put("adPlanId",readyPlan.getAdPlanId());
			params.put("remarks","�޸�վ���������ƻ�IDΪ"+this.getParameter("readyPlanId")+"��״̬,״̬��0-���Ϊ1-��");
			params.put("operationSource","/readyplan.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				readyPlan.setReadyPlanStatus(Integer.parseInt(this.getParameter("readyPlanState")));
				if(Integer.parseInt(this.getParameter("readyPlanState")) == 1){//��
					this.updateReadyBox(request,params);
				}
				dao.update(readyPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				Map<String,Object> parms = new HashMap<String, Object>();
				parms.put("webMasterId",readyPlan.getWebMasterId());
				parms.put("adPlanId", readyPlan.getAdPlanId());
				List<Object> cols = new ArrayList<Object>();
				cols.add(AdPlan.class);
				List<AdPlan> adPlanList = dao.findBySql("select * from adPlan a where a.adPlanId not in (select rp.adPlanId from readyPlan rp where rp.webMasterId = :webMasterId and rp.adPlanId <>:adPlanId)", parms,cols);
				request.setAttribute("adPlanList", adPlanList);
				request.setAttribute("readyPlan",readyPlan);
				return mapping.findForward("edit");
			}else{
				readyPlan.setAdPlanId(Integer.parseInt(this.getParameter("adPlanId")));
				AdPlan adPlan=(AdPlan)dao.findById(AdPlan.class, Integer.parseInt(this.getParameter("adPlanId")));
				String adPlanName = "";
				if(adPlan != null){
					adPlanName = adPlan.getAdPlanName();
				}
				readyPlan.setAdPlanName(adPlanName);
				readyPlan.setReadyPlanStatus(Integer.parseInt(this.getParameter("readyPlanState")));
				if(Integer.parseInt(this.getParameter("readyPlanState")) == 1){//��
					this.updateReadyBox(request,params);
				}
				dao.update(readyPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���",this.CALLBACKTYPE_CLOSECURRENT,"���ƻ��б�");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
