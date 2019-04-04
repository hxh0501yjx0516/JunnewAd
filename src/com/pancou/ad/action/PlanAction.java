package com.pancou.ad.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.pancou.ad.form.AdPlanForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;

public class PlanAction extends BaseDispatchAction {
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 100;// 每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}

		int userGroup = this.getUserSession(request).getUserGroup();

		String sql = "select a.*, cycleCount=(select count(*) from adPlanCycle pc where pc.adPlanId = a.adPlanId),c.customerName customerName from adPlan a left join customer c on a.customerId = c.customerId where 1=1 ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		if (!"".equals(this.getParameter("qryname"))) {
			condition += " and a.adPlanName like :name ";
			parms.put("name", "%" + this.getParameter("qryname") + "%");
		}
		if (!"".equals(this.getParameter("qrystate"))) {
			condition += " and a.adPlanStatus =:state ";
			parms.put("state", Integer.parseInt(this.getParameter("qrystate")));
		}
		if (!"".equals(this.getParameter("qrycustomer"))) {
			condition += " and a.customerId =:customerId ";
			parms.put("customerId",
					Integer.parseInt(this.getParameter("qrycustomer")));
		}
		// if(userGroup != 0){
		// condition += " and a.userGroup =:usergroup";
		// parms.put("usergroup", userGroup);
		// } else {
		// if (!"".equals(this.getParameter("currentusergroup"))){
		// if (Integer.parseInt(this.getParameter("currentusergroup")) != 0){
		// condition += " and a.userGroup =:usergroup";
		// parms.put("usergroup",
		// Integer.parseInt(this.getParameter("currentusergroup")));
		// }
		// }
		// }

		List<Object> cols = new ArrayList<Object>();
		cols.add(AdPlan.class);
		cols.add("cycleCount");
		cols.add("customerName");
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(AdPlan.class, condition, parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),
				sql + condition + " order by a.adPlanId desc", parms, cols);
		List<AdPlan> list = new ArrayList<AdPlan>();
		for (int i = 0; i < qryList.size(); i++) {
			Object[] object = (Object[]) qryList.get(i);
			AdPlan adPlan = (AdPlan) object[0];
			adPlan.setCycleCount(Integer.parseInt(object[1] + ""));
			adPlan.setCustomerName(object[2] + "");
			list.add(adPlan);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}
		this.getCustomerList(request);
		request.setAttribute("resource", this.getParameter("resource"));
		request.setAttribute("qryname", this.getParameter("qryname"));
		request.setAttribute("qrystate", this.getParameter("qrystate"));
		request.setAttribute("qrycustomer", this.getParameter("qrycustomer"));
		request.setAttribute("usergroup", this.getUserSession(request)
				.getUserGroup());
		request.setAttribute("currentusergroup",
				this.getParameter("currentusergroup"));
		this.getUserGroupList(request);
		return mapping.findForward("list");
	}

	/**
	 * 添加页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if ("save".equals(this.getParameter("flag"))) {
			try {
				// 添加
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "adPlanName";
				pvalue[0] = this.getParameter("name");
				List userslist = dao.findAll(AdPlan.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					// 该用户名已存在
					this.buildAjaxResult(request, this.FAILURE_STATUS,
							"该广告计划已存在!", this.CALLBACKTYPE_CLOSECURRENT);
					return mapping.findForward("ajaxDone");
				}
				AdPlan adPlan = new AdPlan();
				AdPlanForm adPlanForm = (AdPlanForm) form;
				FormFile formFile = adPlanForm.getFormFile();
				String path = "";
				if (formFile != null && !"".equals(formFile.getFileName())) {
					if (!FileManager.isPhoto(formFile.getFileName())) {
						this.buildAjaxResult(request, FAILURE_STATUS,
								"上传文件类型应为jpeg|jpg|gif|png|swf！");
						return mapping.findForward("upload");
					}
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;
					path = FileManager.uploadFile(request, formFile,
							FileManager.UPLOAD_ADPLAN_PATH + File.separator
									+ year + File.separator + month, false);
					adPlan.setAdPlanImg(path);
					if ("".equals(path)) {
						this.buildAjaxResult(request, FAILURE_STATUS, "文件上传失败！");
						return mapping.findForward("upload");
					}
				}

				adPlan.setAdPlanName(this.getParameter("name"));
				adPlan.setCustomerId(Integer.parseInt(this
						.getParameter("customerId")));
				adPlan.setAdPlanStatus(Integer.parseInt(this
						.getParameter("adPlanState")));
				adPlan.setAdPlanFlag(Integer.parseInt(this
						.getParameter("adPlanFlag")));
				adPlan.setAddTime(DatetimeHandle.formatCurrentDate());
				adPlan.setUserGroup(1);

				// 20160510 新增　客户返点
				adPlan.setAdPlanRebate(Integer.parseInt(this
						.getParameter("adPlanRebate")));

				int adPlanId = dao.save(adPlan);
				AdPlanCycle adPlanCycle = new AdPlanCycle();
				adPlanCycle.setAdPlanCycleName(this
						.getParameter("adPlanCycleName"));
				adPlanCycle.setBeginTime(this.getParameter("beginTime"));
				adPlanCycle.setEndTime(this.getParameter("endTime"));
				adPlanCycle.setShowType(Integer.parseInt(this
						.getParameter("showType")));
				adPlanCycle.setCustomerPrice(Float.parseFloat(this
						.getParameter("customerPrice")));
				adPlanCycle.setCustomerAllPrice(Float.parseFloat(this
						.getParameter("customerAllPrice")));
				adPlanCycle.setWebMasterPrice(Float.parseFloat(this
						.getParameter("webMasterPrice")));
				adPlanCycle.setAdPlanIsParameter(Integer.parseInt(this
						.getParameter("isParameter")));
				adPlanCycle.setAdPlanCycleStatus(Integer.parseInt(this
						.getParameter("adPlanCycleState")));
				adPlanCycle.setAdPlanId(adPlanId);
				adPlanCycle.setAddTime(DatetimeHandle.formatCurrentDate());
				List<String> keys = this.getKeys("dateTime_");
				Collections.sort(keys);
				String date = "";
				String data = "";
				for (int i = 0; i < keys.size(); i++) {
					String key = keys.get(i);
					String[] arr = key.split("_");
					String number = arr[1];
					if (i == keys.size() - 1) {
						date += this.getParameter(key);
						data += this.getParameter("flow_" + number);
					} else {
						date += this.getParameter(key) + "|";
						data += this.getParameter("flow_" + number) + "|";
					}
				}
				if (!"".equals(date)) {
					adPlanCycle.setDateString(date);
					adPlanCycle.setDataString(data);
				}
				if (!"".equals(this.getParameter("adPlanCycleUrl"))) {
					String webadd = this.getParameter("adPlanCycleUrl");
					// if(!webadd.startsWith("http://")&&
					// !webadd.startsWith("www.")){
					// webadd = "http://www."+webadd;
					// }else if(!webadd.startsWith("http://")){
					// webadd = "http://"+webadd;
					// }else if(webadd.startsWith("http://") &&
					// webadd.indexOf("www.") == -1){
					// //有http:// ,没有www.
					// String pruffex = webadd.substring(0,7);
					// String suffex = webadd.substring(7);
					// webadd = pruffex+"www."+suffex;
					// }
					adPlanCycle.setAdPlanCycleUrl(webadd);
				}
				adPlanCycle.setDisCount(Integer.parseInt(this
						.getParameter("disCount")));

				dao.save(adPlanCycle);
				String callSql = "{call Create_AdplanReport(?)}";
				Map<Integer, Object> callParms = new HashMap<Integer, Object>();
				callParms.put(0, adPlanId);
				dao.doCall(callSql, callParms);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",
						this.CALLBACKTYPE_CLOSECURRENT, "广告计划");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("upload");
		}
		this.getCustomerList(request);
		return mapping.findForward("add");
	}

	/**
	 * 修改页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			AdPlan adPlan = (AdPlan) dao.findById(AdPlan.class,
					Integer.parseInt(this.getParameter("adPlanId")));

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("adPlanId", adPlan.getAdPlanId());
			params.put("remarks", "修改广告计划ID为" + this.getParameter("adPlanId")
					+ "的状态,广告计划状态由0-正常变为1-锁定");
			params.put("operationSource",
					"/adplan.do?action=" + this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				adPlan.setAdPlanStatus(Integer.parseInt(this
						.getParameter("adPlanState")));
				if (Integer.parseInt(this.getParameter("adPlanState")) == 1) {// 锁定
					this.updateReadyBox(request, params);
				}
				dao.update(adPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			} else if ("editFlag".equals(this.getParameter("flag"))) {
				adPlan.setAdPlanFlag(Integer.parseInt(this
						.getParameter("adPlanFlag")));
				dao.update(adPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			} else if ("edit".equals(this.getParameter("flag"))) {
				this.getCustomerList(request);
				String url = Configure.get("img.url") + adPlan.getAdPlanImg();
				adPlan.setAdPlanImg(url);
				request.setAttribute("adPlan", adPlan);
				return mapping.findForward("edit");
			} else {
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "adPlanName";
				pvalue[0] = this.getParameter("name");
				List userslist = dao.findAll(AdPlan.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if (Integer.parseInt(this.getParameter("adPlanId")) != ((AdPlan) userslist
							.get(0)).getAdPlanId()) {
						// 该用户名已存在
						this.buildAjaxResult(request, this.FAILURE_STATUS,
								"该广告计划已存在!");
						return mapping.findForward("upload");
					}
				}
				AdPlanForm adPlanForm = (AdPlanForm) form;
				FormFile formFile = adPlanForm.getFormFile();
				String path = "";
				if (formFile != null && !"".equals(formFile.getFileName())) {
					if (!FileManager.isPhoto(formFile.getFileName())) {
						this.buildAjaxResult(request, FAILURE_STATUS,
								"上传文件类型应为jpeg|jpg|gif|png|swf！");
						return mapping.findForward("upload");
					}
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH) + 1;
					path = FileManager.uploadFile(request, formFile,
							FileManager.UPLOAD_ADPLAN_PATH + File.separator
									+ year + File.separator + month, false);
					adPlan.setAdPlanImg(path);
					if ("".equals(path)) {
						this.buildAjaxResult(request, FAILURE_STATUS, "文件上传失败！");
						return mapping.findForward("upload");
					}
				}
				adPlan.setAdPlanName(this.getParameter("name"));
				adPlan.setCustomerId(Integer.parseInt(this
						.getParameter("customerId")));
				adPlan.setAdPlanStatus(Integer.parseInt(this
						.getParameter("adPlanState")));
				adPlan.setAdPlanFlag(Integer.parseInt(this
						.getParameter("adPlanFlag")));
				if (Integer.parseInt(this.getParameter("adPlanState")) == 1) {// 锁定
					this.updateReadyBox(request, params);
				}

				// 20160510 新增　客户返点
				adPlan.setAdPlanRebate(Integer.parseInt(this
						.getParameter("adPlanRebate")));

				dao.update(adPlan);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",
						this.CALLBACKTYPE_CLOSECURRENT, "广告计划");
				return mapping.findForward("upload");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 删除页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			AdPlan adPlan = (AdPlan) dao.findById(AdPlan.class,
					Integer.parseInt(this.getParameter("adPlanId")));
			dao.delete(adPlan);
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
}
