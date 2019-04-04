package com.pancou.ad.action;

import java.io.PrintWriter;
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
import com.pancou.ad.util.json.JsonWriter;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Users;


@Entity
public class UtilAction extends BaseDispatchAction{
	@SuppressWarnings("unchecked")
	public ActionForward getAdPlanCycleList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		 response.setContentType("application/json;charset=UTF-8");  
	     response.setCharacterEncoding("UTF-8");  
	     Users users = (Users)request.getSession().getAttribute("user");
		String sql ="select ac from AdPlanCycle ac,AdPlan a where ac.adPlanId = a.adPlanId ";
		String condition = "";
		Map<String,Object> parms = new HashMap<String, Object>();
		if(!"".equals(this.getParameter("adPlanId"))){
			condition = "and ac.adPlanId = :adPlanId ";
			parms.put("adPlanId",Integer.parseInt(this.getParameter("adPlanId")));
		} 
//		if(users.getUserGroup()!=0){
//			//执行
//			condition += " and a.userGroup =:userGroup";
//			parms.put("userGroup", users.getUserGroup());
//		}else{
//			//管理员
//			if(null!=this.getParameter("qryuserGroup")&&!"".equals(this.getParameter("qryuserGroup"))){
//				//执行
//				condition += " and a.userGroup =:userGroup";
//				parms.put("userGroup", Integer.parseInt(this.getParameter("qryuserGroup")));
//			}
//		}
		List<AdPlanCycle> adPlanCycleList = dao.findAll(sql,condition, parms);
			JsonWriter writer = new JsonWriter ();
			String result = writer.write(adPlanCycleList);
			PrintWriter pw = response.getWriter();  
	        pw.write(result);  
	        pw.flush(); 
		return null;
	}
}
