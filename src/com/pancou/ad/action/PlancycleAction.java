package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.domain.RealDayFlow;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ReadyBoxUtil;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.ReadyBox;

public class PlancycleAction extends BaseDispatchAction{
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 20;//每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sql = "";
		String condition = "";
		Map<String,Object> parms = new HashMap<String, Object>();
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		if("".equals(this.getParameter("customerId"))){
		    sql = "select a.*,ap.adPlanName,creativeCount=(select count(*) from adCreative where AdPlanCycleId = a.AdPlanCycleId) from adPlanCycle a " +
				"left join adPlan ap on a.adPlanId = ap.adPlanId where ";
		    condition = " a.adPlanId = :adPlanId ";
		parms.put("adPlanId",Integer.parseInt(this.getParameter("adPlanId")));
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdPlanCycle.class," and "+condition,parms));
		}else{
			sql = "select a.*,ap.adPlanName adPlanName,creativeCount=(select count(*) from adCreative where AdPlanCycleId = a.adPlanCycleId) from adPlanCycle a " +
			"left join adPlan ap on a.adPlanId = ap.adPlanId left join customer c on ap.customerId = c.customerId where ";
			String sql2=" adPlanCycle a  " +
						"left join adPlan ap on a.adPlanId = ap.adPlanId left join customer c on ap.customerId = c.customerId ";
			condition = " c.customerId = :customerId ";
			parms.put("customerId",Integer.parseInt(this.getParameter("customerId")));
			String[] names=new String[1];
			String[] values=new String[1];
			names[0]="c.customerId";
			values[0]=this.getParameter("customerId");
			pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.countByPara(sql2,names,values));
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(AdPlanCycle.class);
		cols.add("adPlanName");
		cols.add("creativeCount");
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+"order by a.adPlanCycleId desc",parms,cols);
		List<AdPlanCycle> list = new ArrayList<AdPlanCycle>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			AdPlanCycle adPlanCycle =(AdPlanCycle)object[0];
			adPlanCycle.setAdPlanName(object[1]+"");
			adPlanCycle.setCreativeCount(Integer.parseInt(object[2]+""));
			list.add(adPlanCycle);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		request.setAttribute("resource", this.getParameter("resource"));
		request.setAttribute("customerId", this.getParameter("customerId"));
		return mapping.findForward("list");
	}
	/**
	 * 添加页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			try {
			// 添加
			AdPlanCycle adPlanCycle = new AdPlanCycle();
			adPlanCycle.setAdPlanCycleName(this.getParameter("adPlanCycleName"));
			adPlanCycle.setBeginTime(this.getParameter("beginTime"));
			adPlanCycle.setEndTime(this.getParameter("endTime"));
			adPlanCycle.setShowType(Integer.parseInt(this.getParameter("showType")));
			adPlanCycle.setCustomerPrice(Float.parseFloat(this.getParameter("customerPrice")));
			adPlanCycle.setCustomerAllPrice(Float.parseFloat(this.getParameter("customerAllPrice")));
			adPlanCycle.setWebMasterPrice(Float.parseFloat(this.getParameter("webMasterPrice")));
			adPlanCycle.setAdPlanIsParameter(Integer.parseInt(this.getParameter("isParameter")));
			adPlanCycle.setAdPlanCycleStatus(Integer.parseInt(this.getParameter("adPlanCycleState")));
			adPlanCycle.setAdPlanId(Integer.parseInt(this.getParameter("adPlanId")));
			adPlanCycle.setAddTime(DatetimeHandle.formatCurrentDate());
			List<String> keys = this.getKeys("dateTime_");
			 Collections.sort(keys); 
			String date = "";
			String data = "";
			for(int i=0;i<keys.size();i++){
				String key = keys.get(i);
				String[] arr = key.split("_");
				String number = arr[1];
				if(i == keys.size() - 1){
					date += this.getParameter(key);
					data +=this.getParameter("flow_"+number);
				}else{
					date += this.getParameter(key)+"|";
					data +=this.getParameter("flow_"+number)+"|";
				}
			}
			if(!"".equals(date)){
				adPlanCycle.setDateString(date);
				adPlanCycle.setDataString(data);
			}
			if(!"".equals(this.getParameter("adPlanCycleUrl"))){
				String webadd = this.getParameter("adPlanCycleUrl");
//				if(!webadd.startsWith("http://")&& !webadd.startsWith("www.")){
//					webadd = "http://www."+webadd;
//				}else if(!webadd.startsWith("http://")){
//					webadd = "http://"+webadd;
//				}else if(webadd.startsWith("http://") && webadd.indexOf("www.") == -1){
//					//有http:// ,没有www.
//					String pruffex = webadd.substring(0,7);
//					String suffex = webadd.substring(7);
//					webadd = pruffex+"www."+suffex;
//				}
				adPlanCycle.setAdPlanCycleUrl(webadd);
			}
			adPlanCycle.setDisCount(Integer.parseInt(this.getParameter("disCount")));
			
				dao.save(adPlanCycle);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",this.CALLBACKTYPE_CLOSECURRENT,"planCycleList");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
		}
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		AdPlan adPlan = (AdPlan)dao.findById(AdPlan.class,Integer.parseInt(this.getParameter("adPlanId")));
		request.setAttribute("adPlanName",adPlan.getAdPlanName());
		return mapping.findForward("add");
	}
	/**
	 * 修改页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			//最低单价权限赋予总监及管理员
			boolean role = false;
			int roleId = this.getUserRoleId(request);
			if (roleId == 20 || roleId == 10){
				role = true;
			}
			request.setAttribute("role", role);
			
			AdPlanCycle adPlanCycle = (AdPlanCycle)dao.findById(AdPlanCycle.class, Integer.parseInt(this.getParameter("adPlanCycleId")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("adPlanCycleId",adPlanCycle.getAdPlanCycleId());
			params.put("remarks","修改广告周期ID为"+this.getParameter("adPlanCycleId")+"的状态,广告周期状态由0-正常变为2-锁定");
			params.put("operationSource","/plancycle.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				adPlanCycle.setAdPlanCycleStatus(Integer.parseInt(this.getParameter("adPlanCycleStatus")));
				if(Integer.parseInt(this.getParameter("adPlanCycleStatus")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				dao.update(adPlanCycle);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			}else if ("editFlag".equals(this.getParameter("flag"))) {
				adPlanCycle.setAdPlanIsParameter(Integer.parseInt(this.getParameter("adPlanCycleFlag")));
				dao.update(adPlanCycle);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			}else if("edit".equals(this.getParameter("flag"))) {
				AdPlan adPlan = (AdPlan)dao.findById(AdPlan.class,adPlanCycle.getAdPlanId());
				request.setAttribute("adPlanName",adPlan.getAdPlanName());
				request.setAttribute("adPlanCycle",adPlanCycle);
				return mapping.findForward("edit");
			}else{
				adPlanCycle.setAdPlanCycleName(this.getParameter("adPlanCycleName"));
				adPlanCycle.setBeginTime(this.getParameter("beginTime"));
				adPlanCycle.setEndTime(this.getParameter("endTime"));
				adPlanCycle.setShowType(Integer.parseInt(this.getParameter("showType")));
				adPlanCycle.setCustomerPrice(Float.parseFloat(this.getParameter("customerPrice")));
				adPlanCycle.setCustomerAllPrice(Float.parseFloat(this.getParameter("customerAllPrice")));
				adPlanCycle.setWebMasterPrice(Float.parseFloat(this.getParameter("webMasterPrice")));
				adPlanCycle.setAdPlanIsParameter(Integer.parseInt(this.getParameter("isParameter")));
				adPlanCycle.setAdPlanCycleStatus(Integer.parseInt(this.getParameter("adPlanCycleState")));
				if(Integer.parseInt(this.getParameter("adPlanCycleState")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				List<String> keys = this.getKeys("dateTime_");
				 Collections.sort(keys); 
				String date = "";
				String data = "";
				for(int i=0;i<keys.size();i++){
					String key = keys.get(i);
					String[] arr = key.split("_");
					String number = arr[1];
					if(i == keys.size() - 1){
						date += this.getParameter(key);
						data +=this.getParameter("flow_"+number);
					}else{
						date += this.getParameter(key)+"|";
						data +=this.getParameter("flow_"+number)+"|";
					}
				}
				if(!"".equals(date)){
					adPlanCycle.setDateString(date);
					adPlanCycle.setDataString(data);
				}
				if(!"".equals(this.getParameter("adPlanCycleUrl"))){
					String webadd = this.getParameter("adPlanCycleUrl");
//					if(!webadd.startsWith("http://")&& !webadd.startsWith("www.")){
//						webadd = "http://www."+webadd;
//					}else if(!webadd.startsWith("http://")){
//						webadd = "http://"+webadd;
//					}else if(webadd.startsWith("http://") && webadd.indexOf("www.") == -1){
//						//有http:// ,没有www.
//						String pruffex = webadd.substring(0,7);
//						String suffex = webadd.substring(7);
//						webadd = pruffex+"www."+suffex;
//					}
					adPlanCycle.setAdPlanCycleUrl(webadd);
				}
				adPlanCycle.setDisCount(Integer.parseInt(this.getParameter("disCount")));
				if(dao.update(adPlanCycle)){
					if(adPlanCycle.getAdPlanIsParameter()==0){
					//传参连接
					//#############################如果为传参连接则修改创意地址和投放列表创意地址####################################
					Map<String,Object> parms = new HashMap<String, Object>();
					parms.put("adPlanCycleId",adPlanCycle.getAdPlanCycleId());
					String sql = "select rb.* from readyBox rb left join adCreative ac on rb.adCreativeId=ac.adCreativeId " +
							"where ac.isDefault=0 and rb.adPlanCycleId=:adPlanCycleId ";
					List<Object> cols = new ArrayList<Object>();
					cols.add(ReadyBox.class);
					List<ReadyBox> readyBoxList = dao.findBySql(sql, parms,cols);
					List<AdCreative> adCreativeList = dao.findAll("from AdCreative ","where isDefault=0 and adPlanCycleId=:adPlanCycleId ", parms);
					for(ReadyBox readyBox :readyBoxList){
						readyBox.setAdCreativeUrl(adPlanCycle.getAdPlanCycleUrl());
						if(dao.update(readyBox)){
							if(Integer.parseInt(this.getParameter("adPlanCycleState")) != 1){
								ReadyBoxUtil rb=new ReadyBoxUtil();
								rb.saveToMemCached(readyBox);  //add by yu
							}
						}
					}
					for(AdCreative adCreative:adCreativeList){
						adCreative.setAdCreativeUrl(adPlanCycle.getAdPlanCycleUrl());
						dao.update(adCreative);
					}
					//#############################end####################################
					}
				}
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",this.CALLBACKTYPE_CLOSECURRENT,"planCycleList");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		
		return mapping.findForward("ajaxDone");
	}
	/**
	 * 详细页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
				AdPlanCycle adPlanCycle = (AdPlanCycle)dao.findById(AdPlanCycle.class, Integer.parseInt(this.getParameter("adPlanCycleId")));
				int disCount = 0;
//				if("".equals(this.getParameter("flag"))){
//					disCount = adPlanCycle.getDisCount()/100;
//				}
				String condition = " rb.adPlanCycleId =:adPlanCycleId ";
				Map<String,Object> parms = new HashMap<String, Object>();
				parms.put("adPlanCycleId", Integer.parseInt(this.getParameter("adPlanCycleId")));
				//获取实际数量
				List<Object[]> realList = dao.findBySql("select sum(pv) PV,sum(uv) UV,sum(ip) IP from AdBoxCount abc left join ReadyBox rb on abc.readyBoxId = rb.readyBoxId where "+condition, parms);
				if(realList !=null && realList.size()>0){
				switch(adPlanCycle.getShowType()){
				case 1:
					//UV
					if(null !=realList.get(0)[1]){
						adPlanCycle.setRealFlowList(Integer.parseInt(realList.get(0)[1]+"")-(Integer.parseInt(realList.get(0)[1]+"")*disCount));
					}
					break;
				case 2:
					//PV
					if(null !=realList.get(0)[0]){
					adPlanCycle.setRealFlowList(Integer.parseInt(realList.get(0)[0]+"")-(Integer.parseInt(realList.get(0)[0]+"")*disCount));
					}
					break;
				case 3:
					//UV
					if(null !=realList.get(0)[2]){
					adPlanCycle.setRealFlowList(Integer.parseInt(realList.get(0)[2]+"")-(Integer.parseInt(realList.get(0)[2]+"")*disCount));
					}
					break;
				}
				}
				// 周期总量和每日流量
				if(adPlanCycle != null){
					int dataList = 0; 
					List<RealDayFlow> list = new ArrayList<RealDayFlow>();
					String dateString = adPlanCycle.getDateString();
					String dataString = adPlanCycle.getDataString();
					if(dateString !=null){
					String[] dateArr = dateString.split("\\|");
					String[] dataArr = dataString.split("\\|");
					for(int i=0;i<dateArr.length;i++){
						dataList += Integer.parseInt(dataArr[i]);
						parms.put("startTime", dateArr[i]+" 00:00:00");
						parms.put("endTime", dateArr[i]+" 23:59:59");
						List<Object[]> realDayList = dao.findBySql("select sum(pv) PV,sum(uv) UV,sum(ip) IP " +
								"from adBoxCount abc " +
								"left join readyBox rb on abc.readyBoxId = rb.readyBoxId " +
								"where rb.adPlanCycleId = :adPlanCycleId and abc.adBoxCountTime between :startTime and :endTime " +
								"group by convert(varchar(100),abc.adBoxCountTime,102) ",parms);
						RealDayFlow flow = new RealDayFlow();
						flow.setDateTime(dateArr[i]);
						//周期定义数量
						flow.setPreFlow((Integer.parseInt(dataArr[i])-Integer.parseInt(dataArr[i])*disCount));
						if(realDayList !=null && realDayList.size()>0){
						//获取实际数量
						switch(adPlanCycle.getShowType()){
						case 1:
							//UV
							if(null !=realDayList.get(0)[1]){
							flow.setRealFlow(Integer.parseInt(realDayList.get(0)[1]+"")-(Integer.parseInt(realDayList.get(0)[1]+"")*disCount));
							}
							break;
						case 2:
							//PV
							if(null !=realDayList.get(0)[0]){
							flow.setRealFlow(Integer.parseInt(realDayList.get(0)[0]+"")-(Integer.parseInt(realDayList.get(0)[0]+"")*disCount));
							}
							break;
						case 3:
							//IP
							if(null !=realDayList.get(0)[2]){
							flow.setRealFlow(Integer.parseInt(realDayList.get(0)[2]+"")-(Integer.parseInt(realDayList.get(0)[2]+"")*disCount));
							}
							break;
						}
						}
						list.add(flow);
					} 
					}
					adPlanCycle.setFlowList(dataList-dataList*disCount);
					adPlanCycle.setDayFlow(list);
				}
				request.setAttribute("adPlanCycle",adPlanCycle);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("detail");
	}
}
