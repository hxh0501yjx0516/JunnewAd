package com.pancou.ad.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
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
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.ViewAdBoxCount;


@Entity
public class ReportAction extends BaseDispatchAction{
	
	/**
	 * 数据--数据统计--table:adBoxCount
	 * @param mapping
	 * @param form
	 * @param reqeust
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward adBoxCountList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
				
		int pageNum = 0;
		int numPerPage = 20;
		if ("pager".equals(request.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		
		String beginTime = this.getParameter("begintime");
		String endTime = this.getParameter("endtime");
		
		String sqlSum = "select sum(v.BrowseC) allBrowse,sum(v.BrowseTrue) allBrowseTrue,sum(v.PV) allPv,sum(v.UV) allUv,sum(v.IP) allIp"+
						  " From ViewAdBoxCount v where 1 = 1";	
		
		if (!"".equals(beginTime) && !"".equals(endTime)){
			sqlSum += " and v.AdBoxCountTime >= '"+beginTime+"' and v.AdBoxCountTime <= '"+endTime+"'";
		} else {
			sqlSum += " and DateDiff(day,getdate(),v.AdBoxCountTime) = 0";
		}
		if(!"".equals(this.getParameter("adplanname"))){
			sqlSum += " and v.AdPlanName like '%"+this.getParameter("adplanname")+"%'";
		}
		if(!"".equals(this.getParameter("adplanid"))){
			sqlSum += " and v.AdPlanId = "+Integer.parseInt(this.getParameter("adplanid"));
		}
		if(!"".equals(this.getParameter("username"))){
			sqlSum += " and v.UserId = "+this.getParameter("username");
		}
		if(!"".equals(this.getParameter("isdefault"))){
			sqlSum += " and v.isDefault = "+Integer.parseInt(this.getParameter("isdefault"));
		}
		if(!"".equals(this.getParameter("webmastername"))){
			sqlSum += " and v.webMasterName like '%"+this.getParameter("webmastername")+"%'";
		}
		//System.out.println(sqlSum);
		Users users = (Users)request.getSession().getAttribute("user");
		if(users.getUserGroup()!=0){
			sqlSum += " and v.userGroup ="+users.getUserGroup();
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				sqlSum += " and v.userGroup ="+Integer.parseInt(this.getParameter("qryuserGroup"));
			}
		}
		List<ViewAdBoxCount> listCount = dao.getSum(sqlSum); 
		
		if(listCount != null){
			request.setAttribute("listSum",listCount);
		}
		
		String sqlString = "select v.*,apc.discount cusDiscount,apc.showType showCusType from ViewAdBoxCount v left join adPlanCycle apc on v.adPlanCycleId = apc.adPlanCycleId where 1 = 1";
		String sqlCount = "select count(v.AdBoxCountId) from ViewAdBoxCount v where 1 = 1";
		String condition = "";
		Map<String,Object> parms = new HashMap<String,Object>();	
		if (!"".equals(beginTime) && !"".equals(endTime)){
			condition += " and v.AdBoxCountTime >= :bt and v.AdBoxCountTime <= :et ";
			parms.put("bt", beginTime);
			parms.put("et", endTime); 
		} else {										
			condition += " and DateDiff(day,getdate(),v.AdBoxCountTime) = 0";
		}
		if(!"".equals(this.getParameter("adplanname"))){
			condition += " and v.AdPlanName like :adplanname";
			parms.put("adplanname","%"+this.getParameter("adplanname")+"%");
		}
		if(!"".equals(this.getParameter("adplanid"))){
			condition += " and v.AdPlanId = :adplanid";
			parms.put("adplanid",Integer.parseInt(this.getParameter("adplanid")));
		}
		if(!"".equals(this.getParameter("username"))){
			condition += " and v.UserId =:userid";
			parms.put("userid",Integer.parseInt(this.getParameter("username")));
		}
		if(!"".equals(this.getParameter("isdefault"))){
			condition += " and v.isDefault =:isdefault";
			parms.put("isdefault", Integer.parseInt(this.getParameter("isdefault")));
		}
		if (!"".equals(this.getParameter("webmastername"))){
			condition += " and v.webMasterName like :webmastername";
			parms.put("webmastername", "%"+this.getParameter("webmastername")+"%");
		}
		
		if(users.getUserGroup()!=0){
			condition += " and v.userGroup =:userGroup";
			parms.put("userGroup", users.getUserGroup());
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				condition += " and v.userGroup =:userGroup";
				parms.put("userGroup", Integer.parseInt(this.getParameter("qryuserGroup")));
			}
		}
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(sqlCount+condition, parms));
		condition += " order by v.PV desc";
		List v = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sqlString+condition, parms);
		List<ViewAdBoxCount> list = new ArrayList<ViewAdBoxCount>();
		for(int i = 0; i < v.size(); i++){
			Object[] object = (Object[])v.get(i);
			ViewAdBoxCount viewAdBoxCount = new ViewAdBoxCount();
			viewAdBoxCount.setAdBoxCountId(Integer.parseInt(object[0]+""));
			viewAdBoxCount.setReadyBoxId(Integer.parseInt(object[1]+""));
			viewAdBoxCount.setBrowseC(Integer.parseInt(object[2]+""));
			viewAdBoxCount.setBrowseTrue(Integer.parseInt(object[3]+""));
			viewAdBoxCount.setPv(Integer.parseInt(object[4]+""));
			viewAdBoxCount.setUv(Integer.parseInt(object[5]+""));
			viewAdBoxCount.setIp(Integer.parseInt(object[6]+""));
			viewAdBoxCount.setAdBoxCountTime(object[7]+"");
			viewAdBoxCount.setAdPlanId(Integer.parseInt(object[8]+""));
			viewAdBoxCount.setAdPlanName(object[9]+"");
			viewAdBoxCount.setAdPlanCycleId(Integer.parseInt(object[10]+""));
			viewAdBoxCount.setAdPlanCycleName(object[11]+"");
			viewAdBoxCount.setUrlId(Integer.parseInt(object[12]+""));
			viewAdBoxCount.setUrl(object[13]+"");
			viewAdBoxCount.setUserId(Integer.parseInt(object[14]+""));
			viewAdBoxCount.setUserName(object[15]+"");
			viewAdBoxCount.setWebMasterId(Integer.parseInt(object[16]+""));
			viewAdBoxCount.setWebMasterName(object[17]+"");
			viewAdBoxCount.setAdCreativeId(Integer.parseInt(object[18]+""));
			viewAdBoxCount.setAdCreativeName(object[19]+"");
			viewAdBoxCount.setAdCreativeImg(object[20]+"");
			viewAdBoxCount.setIsDefault(Integer.parseInt(object[21]+""));
			viewAdBoxCount.setDiscount(Integer.parseInt(object[22]+""));
			viewAdBoxCount.setShowType(Integer.parseInt(object[23]+""));
			viewAdBoxCount.setRealname(object[24]+"");
			viewAdBoxCount.setCusDiscount(Integer.parseInt(object[26]+""));
			list.add(viewAdBoxCount);
		}
	
		if (list != null){
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		
		this.getAdPlanList(request);	
		this.getUsersList(request);		
		request.setAttribute("begintime", beginTime);
		request.setAttribute("endtime",endTime);
		request.setAttribute("resource", this.getParameter("resource"));
		request.setAttribute("qryname", this.getParameter("qryname"));
		request.setAttribute("qrystate", this.getParameter("qrystate"));
		request.setAttribute("qryadplan", this.getParameter("adplanname"));
		request.setAttribute("qryadplanid", this.getParameter("adplanid"));
		request.setAttribute("qryuser", this.getParameter("username")); 
		request.setAttribute("isdefault",this.getParameter("isdefault"));
		request.setAttribute("webmastername", this.getParameter("webmastername"));
		request.setAttribute("qryuserGroup",this.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("adBoxCountList");
		
		

	}
	/**
	 * 数据核对管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward verifyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;
		int numPerPage = 50;
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		List<ReportCount> list = new ArrayList<ReportCount>();
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		Users users = (Users)request.getSession().getAttribute("user");
		StringBuffer sqlSum = new StringBuffer();
		sqlSum.append("select sum(r.count) countC,sum(r.money) moneyC,sum(r.realCount) countall,sum(realMoney) moneyall from ReportCount r, AdPlan ap  where r.reportStatus <> 2 and r.adPlanId = ap.adPlanId ");
			if(!"".equals(this.getParameter("qryadplan")) ){
				sqlSum.append(" and r.adplanId = "+Integer.parseInt(this.getParameter("qryadplan")));
			}
			if(!"".equals(this.getParameter("qryadplanname")) ){
				sqlSum.append(" and r.adplanName like '%"+this.getParameter("qryadplanname")+"%'");
			}
			if(!"".equals(this.getParameter("qryadplanCycle")) ){
				sqlSum.append(" and r.adplanCycleId = "+Integer.parseInt(this.getParameter("qryadplanCycle")));
			}
			if(!"".equals(this.getParameter("qryadplancyclename")) ){
				sqlSum.append(" and r.adplanCycleName like '%"+this.getParameter("qryadplancyclename")+"%'");
			}
			if(!"".equals(this.getParameter("webmaster"))){
				sqlSum.append(" and r.webMasterName like '%"+this.getParameter("webmaster")+"%'");
			}
			if(!"".equals(this.getParameter("qrycustomer"))){
				sqlSum.append(" and ap.customerId ="+Integer.parseInt(this.getParameter("qrycustomer")));
			}
		if(!"".equals(this.getParameter("qrybegintime")) && !"".equals(this.getParameter("qryendtime")) ){
			if(!"".equals(this.getParameter("qrybegintime")) ){
				sqlSum.append(" and r.reportTime >= '"+this.getParameter("qrybegintime")+"'");
			}	
			if(!"".equals(this.getParameter("qryendtime")) ){
				sqlSum.append(" and r.reportTime <= '"+this.getParameter("qryendtime")+"'");
			}
		}else{
			sqlSum.append(" and DATEDIFF(day,r.reportTime, GETDATE()) = 0");
		}
//		if(users.getUserGroup()!=0){
//			sqlSum.append(" and ap.userGroup = "+users.getUserGroup());
//		}else{
//			if(!"".equals(this.getParameter("qryuserGroup"))){
//				sqlSum.append(" and ap.userGroup = "+Integer.parseInt(this.getParameter("qryuserGroup")));
//			}
//		}
		
		List<ReportCount> listCount = dao.getSum(sqlSum.toString()); 
		
		if(listCount != null){
			request.setAttribute("listSum",listCount);
		}
		
		
		
		String sql = "select r from ReportCount r,AdPlan ap  where r.adplanId = ap.adPlanId and ";
		String uploadCount = "select count(r.reportId) from ReportCount r,AdPlan ap  where r.adplanId = ap.adPlanId and r.reportStatus = 0 and ";
		Map<String,Object> parms=new HashMap<String, Object>();
		String condition = " r.reportStatus <> :reportState";
		parms.put("reportState",2);
		
			if(!"".equals(this.getParameter("qryadplan")) ){
				condition += " and r.adplanId = :qryadplan ";
				parms.put("qryadplan",Integer.parseInt(this.getParameter("qryadplan")));
			}
			if(!"".equals(this.getParameter("qryadplanname")) ){
				condition += " and r.adplanName like :qryadplanname ";
				parms.put("qryadplanname","%"+this.getParameter("qryadplanname")+"%");
			}
			if(!"".equals(this.getParameter("qryadplanCycle")) ){
				condition += " and r.adplanCycleId = :qryadplanCycle ";
				parms.put("qryadplanCycle",Integer.parseInt(this.getParameter("qryadplanCycle")));
			}
			if(!"".equals(this.getParameter("qryadplancyclename")) ){
				condition += " and r.adplanCycleName like :qryadplancyclename";
				parms.put("qryadplancyclename", "%"+this.getParameter("qryadplancyclename")+"%");
			}
			if(!"".equals(this.getParameter("webmaster"))){
				condition += " and r.webMasterName like :webmastername";
				parms.put("webmastername", "%"+this.getParameter("webmaster")+"%");
			}
			if (!"".equals(this.getParameter("qrycustomer"))){
				condition += " and ap.customerId = :customerid";
				parms.put("customerid", Integer.parseInt(this.getParameter("qrycustomer")));
			}
		if(!"".equals(this.getParameter("qrybegintime")) && !"".equals(this.getParameter("qryendtime")) ){
			if(!"".equals(this.getParameter("qrybegintime")) ){
				condition += " and r.reportTime >= :qrybegintime ";
				parms.put("qrybegintime", this.getParameter("qrybegintime"));
			}	
			if(!"".equals(this.getParameter("qryendtime")) ){
				condition += " and r.reportTime <= :qryendtime ";
				parms.put("qryendtime",this.getParameter("qryendtime"));
			}
		}else{
			condition += " and DATEDIFF(day,r.reportTime, GETDATE()) = 0";
		}
//		if(users.getUserGroup()!=0){
//			condition += " and ap.userGroup =:userGroup";
//			parms.put("userGroup", users.getUserGroup());
//		}else{
//			if(!"".equals(this.getParameter("qryuserGroup"))){
//				condition +=" and ap.userGroup =:userGroup ";
//				parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
//			}
//			
//		}

		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount("select count(r.reportId) from ReportCount r left join adPlan ap on r.adPlanId = ap.adPlanId where "+condition,parms));
		list = dao.findAll(pageMap.get("first"), pageMap.get("size"),sql,condition+" order by r.reportStatus asc,r.reportId desc",parms);
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		int count = 0;
		count = dao.getCount(uploadCount+condition,parms);
		this.getAdPlanList(request);
		this.getAdPlanCycleList(request);
		request.setAttribute("qryadplan", this.getParameter("qryadplan"));
		request.setAttribute("qryadplanname", this.getParameter("qryadplanname"));
		request.setAttribute("qryadplanCycle", this.getParameter("qryadplanCycle"));
		request.setAttribute("qryadplancyclename", this.getParameter("qryadplancyclename"));
		request.setAttribute("qrybegintime", this.getParameter("qrybegintime"));
		request.setAttribute("qryendtime", this.getParameter("qryendtime"));
		request.setAttribute("webmaster", this.getParameter("webmaster"));
		request.setAttribute("uploadCount", count);
		request.setAttribute("qryuserGroup", request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		this.getCustomerList(request);
		return mapping.findForward("verifyList");
	}
	/**
	 * 数据核对修改
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward verifyEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
				List<String> keys = this.getKeys("hidRealCount");
				String realCount = null;
				String realMoney = null;
//				int flag = 0;
				if(keys.size() >0){
					for (String key : keys) {
						String[] arr = key.split("_");
						String id = arr[1];
						ReportCount reportCount = (ReportCount)dao.findById(ReportCount.class, Integer.parseInt(id));
						realCount = this.getParameter(key);
						int oldRealCount = reportCount.getRealCount();
						realMoney = this.getParameter("hidRealMoney_"+id);
						if(realCount.indexOf(",")!=-1 || realCount.indexOf("，")!=-1){
							realCount = this.getParameter(key).replaceAll(",","").replaceAll("，","");
						}
						if(realMoney.indexOf(",")!=-1 || realMoney.indexOf("，")!=-1){
							realMoney = this.getParameter(key).replaceAll(",","").replaceAll("，","");
						}
						reportCount.setRealCount(Integer.parseInt(realCount));
						reportCount.setRealMoney(Float.parseFloat(realMoney));
						reportCount.setReportStatus(1);
						
							dao.update(reportCount);
					}
						this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！","","数据核对");
					
				}else{
					this.buildAjaxResult(request, SUCCESS_STATUS, "暂无可核对数据！");
				}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
	/**
	 * 数据核对导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward verifyReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = "数据核对报表";
		Users users = (Users)request.getSession().getAttribute("user");
		  OutputStream os = response.getOutputStream();
		  response.reset();
		  fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
//		  URLDecoder.decode(fileName ,"utf-8");
		  response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		  response.setContentType("application/msexcel;charset=UTF-8");
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");          
			headerList.add("日期");          
			headerList.add("媒介");          
			headerList.add("域名");          
			headerList.add("网站主");        
		    headerList.add("广告计划");      
		    headerList.add("计划周期");      
		    headerList.add("显示(PV)");      
		    headerList.add("显示(UV)");      
		    headerList.add("点击(PV)");      
		    headerList.add("点击(UV)");      
		    headerList.add("返回量");        
		    headerList.add("应收");          
		    headerList.add("有效值");        
		    headerList.add("有效佣金");      
	    	headerList.add("利润");          
	    	headerList.add("利润率");
//	    	headerList.add("所属客户");
		String sql = "select r from ReportCount r,AdPlan ap where ap.adPlanId = r.adplanId and ";
		Map<String,Object> parms=new HashMap<String, Object>();
		String condition = " r.reportStatus <> :reportState";
		parms.put("reportState",2);
		if(!"".equals(this.getParameter("qryadplan")) ){
			condition += " and r.adplanId = :qryadplan ";
			parms.put("qryadplan",Integer.parseInt(this.getParameter("qryadplan")));
		}
		if(!"".equals(this.getParameter("qryadplanname")) ){
			condition += " and r.adplanName like :qryadplanname ";
			parms.put("qryadplanname","%"+this.getParameter("qryadplanname")+"%");
		}
		if(!"".equals(this.getParameter("qryadplanCycle")) ){
			condition += " and r.adplanCycleId = :qryadplanCycle ";
			parms.put("qryadplanCycle",Integer.parseInt(this.getParameter("qryadplanCycle")));
		}
		if(!"".equals(this.getParameter("webmaster"))){
			condition += " and r.webMasterName like :webmastername";
			parms.put("webmastername", "%"+this.getParameter("webmaster")+"%");
		}
		if(!"".equals(this.getParameter("qrybegintime")) ){
			condition += " and r.reportTime >= :qrybegintime ";
			parms.put("qrybegintime", this.getParameter("qrybegintime"));
		}	
		if(!"".equals(this.getParameter("qryendtime")) ){
			condition += " and r.reportTime <= :qryendtime ";
			parms.put("qryendtime",this.getParameter("qryendtime"));
		}
//		if (!"".equals(this.getParameter("qrycustomer"))){
//			condition += " and ap.customerId = :customerid";
//			parms.put("customerid", Integer.parseInt(this.getParameter("qrycustomer")));
//		}
		if(users.getUserGroup()!=0){
			condition += " and ap.userGroup =:userGroup";
			parms.put("userGroup", users.getUserGroup());
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				condition +=" and ap.userGroup =:userGroup ";
				parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
			}
		}
		List<ReportCount> list = dao.findAll(sql,condition+" order by r.reportId desc",parms);
		List<Object[]> datas = new ArrayList<Object[]>();
		DecimalFormat df = new DecimalFormat("##,###.###");
		for(ReportCount reportCount:list){
			Object[] object = new Object[17];
			object[0]=reportCount.getReportId();
			object[1]=DatetimeHandle.formatDate(DatetimeHandle.parseDate(reportCount.getReportTime()),DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			object[2]=reportCount.getUserName();
			object[3]=reportCount.getUrlName();
			object[4]=reportCount.getWebMasterName();
			object[5]=reportCount.getAdplanName();
			object[6]=reportCount.getAdplanCycleName();
			object[7]=df.format(reportCount.getBrowse());
			object[8]=df.format(reportCount.getBrowseTrue());
			object[9]=df.format(reportCount.getPv());
			object[10]=df.format(reportCount.getUv());
			object[11]=df.format(reportCount.getCount());
			object[12]="￥"+df.format(reportCount.getMoney());
			object[13]=df.format(reportCount.getRealCount());
			object[14]="￥"+df.format(reportCount.getRealMoney());
			object[15]="￥"+df.format(reportCount.getMoney()-reportCount.getRealMoney());
			object[16]=df.format((reportCount.getMoney()-reportCount.getRealMoney())/reportCount.getMoney()*100)+"%";
//			object[16]=df.format((reportCount.getMoney()-reportCount.getRealMoney())/reportCount.getMoney()*100)+"%";
			datas.add(object);
		}
		ExcelUtil.export(datas,headerList,fileName,os);
		return null;
	}

	/**
	 * 数据执行报表列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward dataReportList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;
		int numPerPage = 20;
		
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		StringBuffer sqlSum = new StringBuffer();
		sqlSum.append("select sum(r.count) countC,sum(r.money) moneyC,sum(r.realCount) countall,sum(realMoney) moneyall from ReportCount r,AdPlan ap where r.adPlanId = ap.adPlanId ");	
		
		if(!"".equals(this.getParameter("qrybegintime")) && !"".equals(this.getParameter("qryendtime")) ){
			if(!"".equals(this.getParameter("qryadplan")) ){
				sqlSum.append(" and r.adplanName like '%"+this.getParameter("qryadplan")+"%'");
			}
			if(!"".equals(this.getParameter("qrywebmaster"))){
				sqlSum.append(" and r.webMasterName like '%"+this.getParameter("qrywebmaster")+"%'");
			}
			if(!"".equals(this.getParameter("qrysales"))){
				sqlSum.append(" and r.userId = "+Integer.parseInt(this.getParameter("qrysales")));
			}
			
			if(!"".equals(this.getParameter("qrybegintime")) ){
				sqlSum.append(" and r.reportTime >= '"+this.getParameter("qrybegintime")+"'");
			}	
			if(!"".equals(this.getParameter("qryendtime")) ){
				sqlSum.append(" and r.reportTime <= '"+this.getParameter("qryendtime")+"'");
			}
		}else{
			sqlSum.append(" and DATEDIFF(day,r.reportTime, GETDATE()) = 0");
		}
		if(!"".equals(this.getParameter("paytype"))){
			sqlSum.append(" and r.reportStatus = "+Integer.parseInt(this.getParameter("paytype")));
		}
		Users users = (Users)request.getSession().getAttribute("user");
		if(users.getUserGroup()!=0){
			sqlSum.append(" and ap.userGroup = "+users.getUserGroup());
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				sqlSum.append(" and ap.userGroup = "+Integer.parseInt(this.getParameter("qryuserGroup")));
			}
		}
		List<ReportCount> listCount = dao.getSum(sqlSum.toString()); 
		
		if(listCount != null){
			request.setAttribute("listSum",listCount);
		}
		
		String sql ="select r from ReportCount r,AdPlan ap where r.adplanId = ap.adPlanId  ";
		String condition = "";
		Map<String,Object> parms=new HashMap<String, Object>();
		if(!"".equals(this.getParameter("qrybegintime")) && !"".equals(this.getParameter("qryendtime")) ){
		if(!"".equals(this.getParameter("qrywebmaster")) ){
			condition += " and r.webMasterName like :qrywebmaster ";
			parms.put("qrywebmaster", "%"+this.getParameter("qrywebmaster")+"%");
		}
		if(!"".equals(this.getParameter("qryadplan")) ){
			condition += " and r.adplanName like :qryadplan ";
			parms.put("qryadplan", "%"+this.getParameter("qryadplan")+"%");
		}
		if(!"".equals(this.getParameter("qrybegintime")) ){
			condition += " and r.reportTime >= :qrybegintime ";
			parms.put("qrybegintime", this.getParameter("qrybegintime"));
		}
		if(!"".equals(this.getParameter("qryendtime")) ){
			condition += " and r.reportTime <= :qryendtime ";
			parms.put("qryendtime",this.getParameter("qryendtime"));
		}
		if(!"".equals(this.getParameter("qrysales")) && 0!=Integer.parseInt(this.getParameter("qrysales"))){
			condition += " and r.userId = :qrysales ";
			parms.put("qrysales",Integer.parseInt(this.getParameter("qrysales")));
		}
		}else{
			condition += " and DATEDIFF(day,r.reportTime, GETDATE()) = 0";
		}
		if (!"".equals(this.getParameter("paytype"))){
			condition += " and r.reportStatus =:paytype";
			parms.put("paytype",Integer.parseInt(this.getParameter("paytype")));
		}
		if(users.getUserGroup()!=0){
			condition += " and ap.userGroup =:userGroup";
			parms.put("userGroup", users.getUserGroup());
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				condition +=" and ap.userGroup =:userGroup ";
				parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
			}
		}
		
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount("select count(r.reportId) from ReportCount r,AdPlan ap where r.adplanId = ap.adPlanId "+condition,parms));
		List<ReportCount> list = dao.findAll(pageMap.get("first"), pageMap.get("size"), sql, condition+" order by r.reportId desc", parms);
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		this.getUsersList(request);
		request.setAttribute("qrywebmaster", this.getParameter("qrywebmaster"));
		request.setAttribute("qryadplan", this.getParameter("qryadplan"));
		request.setAttribute("qrybegintime", this.getParameter("qrybegintime"));
		request.setAttribute("qryendtime", this.getParameter("qryendtime"));
		request.setAttribute("qrysales", this.getParameter("qrysales"));
		request.setAttribute("paytype", this.getParameter("paytype"));
		request.setAttribute("qryuserGroup", request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		
		//最低单价权限赋予总监及管理员
		int roleId = this.getUserRoleId(request);
		request.setAttribute("roleId", roleId);
		
		return mapping.findForward("dataReportList");
	}
	/**
	 * 数据执行报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward dataReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Users users = (Users)request.getSession().getAttribute("user");
		String fileName = "数据报表";
		  OutputStream os = response.getOutputStream();
		  response.reset();
		  fileName = new String(fileName.getBytes("GBK"),"ISO8859-1"); 
		  response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
		  response.setContentType("application/msexcel;charset=UTF-8");
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");          
			headerList.add("网站主");       
			headerList.add("所属媒介");   
		    headerList.add("广告计划");      
		    headerList.add("周期");   
		    headerList.add("返回量");        
		    headerList.add("应收");          
		    headerList.add("有效值");        
		     headerList.add("有效佣金");    
		     headerList.add("日期");  
	    	String sql ="select r from ReportCount r,AdPlan ap where r.adplanId = ap.adPlanId   ";
			String condition = "";//" and r.reportStatus = 2 ";
			Map<String,Object> parms=new HashMap<String, Object>();
			if(!"".equals(this.getParameter("qrywebmaster")) ){
				condition += " and r.webMasterName like :qrywebmaster ";
				parms.put("qrywebmaster", "%"+this.getParameter("qrywebmaster")+"%");
			}
			if(!"".equals(this.getParameter("qryadplan")) ){
				condition += " and r.adplanName like :qryadplan ";
				parms.put("qryadplan", "%"+this.getParameter("qryadplan")+"%");
			}
			if(!"".equals(this.getParameter("qrybegintime")) ){
				condition += " and r.reportTime >= :qrybegintime ";
				parms.put("qrybegintime", this.getParameter("qrybegintime"));
			}
			if(!"".equals(this.getParameter("qryendtime")) ){
				condition += " and r.reportTime <= :qryendtime ";
				parms.put("qryendtime",this.getParameter("qryendtime"));
			}
			if(!"".equals(this.getParameter("qrysales")) && 0!=Integer.parseInt(this.getParameter("qrysales"))){
				condition += " and r.userId = :qrysales ";
				parms.put("qrysales",Integer.parseInt(this.getParameter("qrysales")));
			}
			if (!"".equals(this.getParameter("paytype"))){
				condition += " and r.reportStatus =:paytype";
				parms.put("paytype",Integer.parseInt(this.getParameter("paytype")));
			}
			if(users.getUserGroup()!=0){
				condition += " and ap.userGroup =:userGroup";
				parms.put("userGroup", users.getUserGroup());
			}else{
				if(!"".equals(this.getParameter("qryuserGroup"))){
					condition +=" and ap.userGroup =:userGroup ";
					parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
				}
			}
			List<ReportCount> list = dao.findAll(sql, condition+" order by r.reportId desc", parms);
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			for(ReportCount reportCount : list){
			Object[] object = new Object[10];
			object[0]=reportCount.getReportId();
			object[1]=reportCount.getWebMasterName();
			object[2]=reportCount.getUserName();
			object[3]=reportCount.getAdplanName();
			object[4]=reportCount.getAdplanCycleName();
			object[5]=df.format(reportCount.getCount());
			object[6]=df.format(reportCount.getMoney());
			object[7]=df.format(reportCount.getRealCount());
			object[8]=df.format(reportCount.getRealMoney());
			object[9]=DatetimeHandle.formatDate(DatetimeHandle.parseDate(reportCount.getReportTime()),DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			datas.add(object); 
		}
		ExcelUtil.export(datas,headerList,fileName,os);
		return null;
	}
	
	/**
	 * 数据统计--导出报表--Table:ReportCount (ViewAdBoxCount)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward adBoxCountToExcel(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try {
			Users users = (Users)request.getSession().getAttribute("user");
			String fileName = "数据报表";
			  OutputStream os = response.getOutputStream();
			  response.reset();
			  fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
			  response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			  response.setContentType("application/msexcel;charset=UTF-8");

				List<String> headerList = new ArrayList<String>();
				headerList.add("ReadyBoxId");      
				headerList.add("站长");
				headerList.add("媒介");  
				headerList.add("域名");
				headerList.add("AdPlanId"); 
				headerList.add("计划");  
			    headerList.add("周期	");         
			    headerList.add("创意");
			    headerList.add("链接"); 
			    headerList.add("支付类型");
			    headerList.add("显示（PV	）");             
			    headerList.add("显示（UV）");             
			    headerList.add("点击（PV）");  
			    headerList.add("点击（UV）");
			    headerList.add("点击（IP）");
			    headerList.add("点击率");
			    headerList.add("扣量");
			    headerList.add("站长查看列");
			    headerList.add("站长单价");
			    headerList.add("日期"); 
			    
			    
			    String sqlString = "select v.*,apc.discount cusDiscount,apc.showType showCusType from ViewAdBoxCount v left join adPlanCycle apc on v.adPlanCycleId = apc.adPlanCycleId  where 1 = 1";
				String condition = " ";
				Map<String,Object> parms = new HashMap<String,Object>();
				if (!"".equals(this.getParameter("begintime")) && !"".equals(this.getParameter("endtime"))){
					condition += " and v.adBoxCountTime >= :bt and v.adBoxCountTime <= :et ";
					parms.put("bt", this.getParameter("begintime"));
					parms.put("et", this.getParameter("endtime")); 
				} else {										
					condition += " and DateDiff(day,getdate(),v.adBoxCountTime) = 0";
				}
				if(!"".equals(this.getParameter("adplanname"))){
					condition += " and v.adPlanName like :adplanname";
					parms.put("adplanname","%"+this.getParameter("adplanname")+"%");
				}
				if(!"".equals(this.getParameter("adplanid"))){
					condition += " and v.adPlanId =:adplanid";
					parms.put("adplanid",Integer.parseInt(this.getParameter("adplanid")));
				}
				if(!"".equals(this.getParameter("username"))){
					condition += " and v.userId =:userid";
					parms.put("userid",Integer.parseInt(this.getParameter("username")));
				}
				if(!"".equals(this.getParameter("isdefault"))){
					condition += " and v.isDefault =:isdefault";
					parms.put("isdefault", Integer.parseInt(this.getParameter("isdefault")));
				}
				if (!"".equals(this.getParameter("webmastername"))){
					condition += " and v.webMasterName like :webmastername";
					parms.put("webmastername", "%"+this.getParameter("webmastername")+"%");
				}
				if(users.getUserGroup()!=0){
					condition += " and v.userGroup =:userGroup";
					parms.put("userGroup", users.getUserGroup());
				}else{
					if(!"".equals(this.getParameter("qryuserGroup"))){
						condition +=" and v.userGroup =:userGroup ";
						parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
					}
				}
			    condition += " order by v.userId";
			    
			    List<Object> cols = new ArrayList<Object>();
			    cols.add(ViewAdBoxCount.class);
			    cols.add("cusDiscount");
			    cols.add("showCusType");
				List<Object[]> list = dao.findBySql(sqlString+condition, parms,cols);
				List<Object[]> datas = new ArrayList<Object[]>();
				DecimalFormat df = new DecimalFormat("0.00");
				for(Object[] objects : list){
					Object[] object = new Object[20];
					ViewAdBoxCount viewAdBoxCount = (ViewAdBoxCount)objects[0];
					object[0]=viewAdBoxCount.getReadyBoxId();
					object[1]=viewAdBoxCount.getWebMasterName();
					object[2]=viewAdBoxCount.getRealname();
					object[3]=viewAdBoxCount.getUrl();
					object[4]=viewAdBoxCount.getAdPlanId();
					object[5]=viewAdBoxCount.getAdPlanName();
					object[6]=viewAdBoxCount.getAdPlanCycleName();
					object[7]=viewAdBoxCount.getAdCreativeName();
					object[8]=viewAdBoxCount.getAdCreativeUrl();
					object[9]=viewAdBoxCount.getPayTypeName();
					object[10]=viewAdBoxCount.getBrowseC();
					object[11]=viewAdBoxCount.getBrowseTrue();
					object[12]=viewAdBoxCount.getPv();
					object[13]=viewAdBoxCount.getUv();
					object[14]=viewAdBoxCount.getIp();
					if (viewAdBoxCount.getBrowseTrue() == 0){
						object[15] = "";
					} else {
						double hitPercent = (double)viewAdBoxCount.getIp()/viewAdBoxCount.getBrowseTrue()*100;
						object[15]=df.format(hitPercent)+"%";
					}				
					object[16]=viewAdBoxCount.getDiscount()+"%";
					if (viewAdBoxCount.getShowType() == 0){
						if (viewAdBoxCount.getDiscount() == 0){
							object[17]=viewAdBoxCount.getPv();
						} else {
							object[17]=viewAdBoxCount.getPv() * (100-viewAdBoxCount.getDiscount())/100;
						}
						
					}
					if (viewAdBoxCount.getShowType() == 1){
						if (viewAdBoxCount.getDiscount() == 0){
							object[17]=viewAdBoxCount.getUv();
						} else {
							object[17]=viewAdBoxCount.getUv()* (100-viewAdBoxCount.getDiscount())/100;
						}
						
					}
					if (viewAdBoxCount.getShowType() == 2){
						if (viewAdBoxCount.getDiscount() == 0){
							object[17]=viewAdBoxCount.getIp();
						} else {
							object[17]=viewAdBoxCount.getIp() * (100-viewAdBoxCount.getDiscount())/100;
						}
						
					}
					object[18]=viewAdBoxCount.getWebMasterPrice();
					object[19]=DatetimeHandle.formatDate(DatetimeHandle.parseDate(viewAdBoxCount.getAdBoxCountTime()),DatetimeHandle.SHORT_TIME_FORMAT_STRING);
					datas.add(object);
				}
				ExcelUtil.export(datas,headerList,fileName,os);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
		}
}