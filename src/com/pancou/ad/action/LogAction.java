package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.pancou.ad.util.ipparse.IPSeeker;
import com.pancou.ad.vo.Log;



@Entity
public class LogAction extends BaseDispatchAction {
	
	/**
	 * 登陆日志	table:Log
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		String begintime = this.getParameter("begintime");
		String endtime = this.getParameter("endtime");
		try{
			//分页
			int pageNum = 0;
			int numPerPage = 20;
			if ("pager".equals(this.getParameter("flag"))){
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			
			String strsql = "select l.* from log l where 1=1";
			String condition = "";
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(Log.class);
			
			Map<String,Object> parms = new HashMap<String,Object>(); 
			if (!"".equals(this.getParameter("begintime")) && !"".equals(this.getParameter("endtime"))){
				condition += " and l.logTime >= :bt and l.logTime <= :et";
				parms.put("bt", begintime);
				parms.put("et", endtime+" 23:59:59");
			} else {
				begintime = DatetimeHandle.formatDate(new Date(), DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				endtime = DatetimeHandle.formatDate(new Date(), DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				condition += " and datediff(day,getdate(),l.logTime) = 0";
			}
					
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum,numPerPage,dao.getCount(Log.class, condition, parms));
			List<Log> logList = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql+condition+" order by l.logtime desc", parms,cols);
			 IPSeeker ipSeeker=new IPSeeker("qqwry.dat",request.getSession().getServletContext().getRealPath("/"));
//			 System.out.println("***************       *******************************");
//			 System.out.println(request.getSession().getServletContext().getRealPath("/")); 
			 List<Log> list = new ArrayList<Log>(); 
			for(Log log :logList){ 
				//System.out.println(log.getLogIp().trim());
//				String addr = IPSeeker.getInstance().getAddress(log.getLogIp().trim());
				//String addr = ipSeeker.getCountry(log.getLogIp().trim())+" "+ipSeeker.getArea(log.getLogIp().trim());
				//log.setIpAddress(addr);
				list.add(log);
			}
			if (list != null && !"".equals(list)){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
			}
		
		} catch (Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("begintime", begintime);
		request.setAttribute("endtime", endtime);
		return mapping.findForward("list");
	}
}
