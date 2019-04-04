package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.actions.DispatchAction;

//import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.PagingHandle;
//import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.vo.Models;



@Entity
public class AdModelAction extends BaseDispatchAction {
	//PlatDao dao = new PlatDao();

	//Action list()
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
	
		
		
		int pageNum = 0; //当前页
		int numPerPage = 20;//每页条数
		
		
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
				
		
		String searchNameValue = this.getParameter("modelName");
		String searchJsValue = this.getParameter("modelJs");
		String sqlString = "select m.* from models as m where 1 = 1 ";
		String condition = "";
		
		
		Map<String,Object> parms = new HashMap<String,Object>();
		if (searchNameValue != null && !"".equals(searchNameValue)){
			condition += " and m.modelName like :modelName";
			parms.put("modelName","%"+searchNameValue+"%");
		}
		if (searchJsValue != null && !"".equals(searchJsValue)){
			condition += " and m.modelJs like :modelJs";
			parms.put("modelJs","%"+searchJsValue+"%");
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(Models.class);
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum,numPerPage,dao.getCount(Models.class, condition, parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sqlString+condition+" order by m.modelId asc",parms,cols);
		List<Models> list = new ArrayList<Models>();
		for(int i=0;i<qryList.size();i++){
			Models models =(Models)qryList.get(i);
			list.add(models);
		}
		if (list != null ) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
		}
		
		request.setAttribute("modelName",searchNameValue);
		request.setAttribute("modelJs",searchJsValue);
		return mapping.findForward("list");
		
	}
	
	/**
	 * 添加模板
	 * 
	 */
	public ActionForward add(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		if ("save".equals(this.getParameter("flag"))){
			//添加
			String[] pname = new String[1];
			String[] pvalue = new String[2];
			pname[0] = "modelname";
			pvalue[0] = this.getParameter("admodelname");
			
			List admodelList = dao.findAll(Models.class,pname,pvalue);
			//模板是否存在
			if (admodelList != null && admodelList.size() > 0){
				this.buildAjaxResult(request, this.FAILURE_STATUS, "模板已存在!","closeCurrent");
				return mapping.findForward("ajaxDone");
			}
			//获取参数
			Models models = new Models();
			models.setModelName(this.getParameter("admodelname"));
			models.setModelJs(this.getParameter("admodeljs"));
			models.setModelFlag(Integer.parseInt(this.getParameter("modelFlag")));
			models.setModelStatus(Integer.parseInt(this.getParameter("state")));
			try{
				dao.save(models);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！","closeCurrent","广告模式");
			} catch (Exception e){
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
		}
		
		return mapping.findForward("add");
	}
	
	/**
	 * 修改模板
	 * 
	 */
	public ActionForward editAction(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try{
			Models models = (Models)dao.findById(Models.class, Integer.parseInt(this.getParameter("modelId")));
			if ("modistate".equals(this.getParameter("flag"))){
				models.setModelStatus(Integer.parseInt(this.getParameter("state")));
				dao.update(models);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			} else if ("edit".equals(this.getParameter("flag"))){
			
			
				models.setModelName(this.getParameter("admodelname"));
				models.setModelJs(this.getParameter("admodeljs"));
				models.setModelFlag(Integer.parseInt(this.getParameter("modelFlag")));
				models.setModelStatus(Integer.parseInt(this.getParameter("state")));
				dao.update(models);	
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！", this.CALLBACKTYPE_CLOSECURRENT, "广告模式");
				
			} else {
				request.setAttribute("models", models);
				return mapping.findForward("edit");
			}
		} catch(Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		
		return mapping.findForward("ajaxDone");
	}
	
	
	/**
	 * 删除模板
	 */
	public ActionForward delAction(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		//删除
		try{
			Models models = (Models)dao.findById(Models.class, Integer.parseInt(this.getParameter("value")));
			dao.delete(models);
			this.buildAjaxResult(request,this.SUCCESS_STATUS,"操作成功！");
		} catch (Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request,this.FAILURE_STATUS,"操作失败！");
		}		
		return mapping.findForward("ajaxDone");
	}
		
		
		
		
		
		

}
