package com.pancou.ad.iter;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.MediaIter;

@Entity
public class MediaInterface extends BaseDispatchAction {

	/**
	 * Media Iterface List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		int pageNum = 0;
		int numPerPage = 20;
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
	
		String sql = "select m.* from MediaIter m where 1=1";
		String condition = "";
		Map<String,Object> parms = new HashMap<String,Object>();
		

		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(MediaIter.class, condition, parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sql+condition+" order by m.MediaIterId desc", parms);
		List<MediaIter> list = new ArrayList<MediaIter>();
		for (Object object:qryList) {
			Object[] o = (Object[])object;
			MediaIter mi = new MediaIter();
			mi.setMediaIterId(Integer.parseInt(o[0]+""));
			mi.setUserId(Integer.parseInt(o[1]+""));
			mi.setUserName(o[2]+"");
			mi.setWebMasterId(Integer.parseInt(o[3]+""));
			mi.setWebMasterName(o[4]+"");
			mi.setMediaFlag(o[5]+"");
			mi.setUrlFlag(o[6]+"");
			mi.setCustomerId(Integer.parseInt(o[7]+""));
			mi.setCustomerName(o[8]+"");
			mi.setCustomerFlag(o[9]+"");
			list.add(mi);
		}
		
		if (list != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "mediaIterList", list));
		}
		
		return mapping.findForward("list");
	}

	/**
	 * Media Iterface Add
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		if ("save".equals(this.getParameter("flag"))){
			//save entity
			String userId = "";
			String userName = "";
			String webMasterId = "";
			String webMasterName = "";
			String customerId = "";
			String customerName = "";
			String mediaFlag = this.getParameter("mediaFlag");
			String urlFlag = this.getParameter("urlFlag");
			String customerFlag = this.getParameter("customerFlag");
			
			if (!"0".equals(this.getParameter("user"))){
				userId = this.getParameter("user").split("--")[0];
				userName = this.getParameter("user").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择媒介！");
			}
			
			if (!"0".equals(this.getParameter("webMaster"))){
				webMasterId = this.getParameter("webMaster").split("--")[0];
				webMasterName = this.getParameter("webMaster").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择渠道！");
			}
			
			if (!"0".equals(this.getParameter("customer"))){
				customerId = this.getParameter("customer").split("--")[0];
				customerName = this.getParameter("customer").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择渠道！");
			}
			
			MediaIter mi = new MediaIter();
			mi.setUserId(Integer.parseInt(userId));
			mi.setUserName(userName);
			mi.setWebMasterId(Integer.parseInt(webMasterId));
			mi.setWebMasterName(webMasterName);
			mi.setMediaFlag(mediaFlag);
			mi.setUrlFlag(urlFlag);
			mi.setCustomerId(Integer.parseInt(customerId));
			mi.setCustomerName(customerName);
			mi.setCustomerFlag(customerFlag);
			try {
				dao.save(mi);
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT, "渠道接口");
			} catch (Exception e){
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！请重新操作");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getUsersList(request);
		this.getWebMasterList(request);
		this.getCustomerList(request);
		return mapping.findForward("add");
	}

	/**
	 * Media Iterface Edit
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		MediaIter mi = (MediaIter)dao.findById(MediaIter.class, Integer.parseInt(request.getParameter("mediaIterId")));
		if (!"save".equals(this.getParameter("flag"))){
			//init page
			request.setAttribute("mediaIterList", mi);
		} else {
			//update entity
			String userId = "";
			String userName = "";
			String webMasterId = "";
			String webMasterName = "";
			String customerId = "";
			String customerName = "";
			String mediaFlag = this.getParameter("mediaFlag");
			String urlFlag = this.getParameter("urlFlag");
			String customerFlag = this.getParameter("customerFlag");
			
			if (!"0".equals(this.getParameter("user"))){
				userId = this.getParameter("user").split("--")[0];
				userName = this.getParameter("user").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择媒介！");
			}
			
			if (!"0".equals(this.getParameter("webMaster"))){
				webMasterId = this.getParameter("webMaster").split("--")[0];
				webMasterName = this.getParameter("webMaster").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择渠道！");
			}
			
			if (!"0".equals(this.getParameter("customer"))){
				customerId = this.getParameter("customer").split("--")[0];
				customerName = this.getParameter("customer").split("--")[1];
			} else {
				this.buildAjaxResult(request, FAILURE_STATUS, "请选择渠道！");
			}
			
			mi.setUserId(Integer.parseInt(userId));
			mi.setUserName(userName);
			mi.setWebMasterId(Integer.parseInt(webMasterId));
			mi.setWebMasterName(webMasterName);
			mi.setMediaFlag(mediaFlag);
			mi.setUrlFlag(urlFlag);
			mi.setCustomerId(Integer.parseInt(customerId));
			mi.setCustomerName(customerName);
			mi.setCustomerFlag(customerFlag);
			try {
				dao.update(mi);
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT, "渠道接口");
			} catch (Exception e){
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！请重新操作");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getUsersList(request);
		this.getWebMasterList(request);
		this.getCustomerList(request);
		return mapping.findForward("edit");
	}
	
	/**
	 * get http code
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		int mediaIterId = Integer.parseInt(request.getParameter("mediaIterId"));
		MediaIter mi = (MediaIter)dao.findById(MediaIter.class, mediaIterId);
		String mediaFlag = mi.getMediaFlag();
		String customerFlag = mi.getCustomerFlag();
		String urlFlag = mi.getUrlFlag();
		
		StringBuffer httpUrl = new StringBuffer();
		httpUrl.append("http://iter.junnew.com/MediaCps/?");
		httpUrl.append("media=").append(mediaFlag);
		httpUrl.append("&customer=").append(customerFlag);
//		httpUrl.append("&extends=").append(urlFlag);
		httpUrl.append("&beginTime=2012-02-02").append("&endTime=2012-02-10");
		
		request.setAttribute("httpUrl", httpUrl.toString());
		return mapping.findForward("getCode");
	}
	
	public void downLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String filePath = request.getSession().getServletContext().getRealPath("/")+"/doc/盘酷-骏易传媒数据返回接口说明.docx";
		File file = new File(filePath);
		if (!file.exists()){
			response.sendError(404, "File not found!");
			return;
		}
		
		//设置输出格式
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(file.getName(),"utf-8"));
		
		//流数据
		FileInputStream inputStream = new FileInputStream(file);
		OutputStream fos = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while((len = inputStream.read(b)) > 0){
			fos.write(b, 0, len);
			fos.flush();
		}
		
		inputStream.close();
		fos.close();
		
	}
}
