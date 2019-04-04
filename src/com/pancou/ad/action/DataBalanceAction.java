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
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.CycleMoneyReceipt;
import com.pancou.ad.vo.Pay;
import com.pancou.ad.vo.ReportCount;

@Entity
public class DataBalanceAction extends BaseDispatchAction {
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;
		int numPerPage = 20;
		int countSum = 0;
		float moneySum = 0;
		float moneyRebateSum = 0.0F;
		int realCountSum = 0;
		float realMoneySum = 0;
		String beginTime = this.getParameter("qrybegintime");
		String endTime = this.getParameter("qryendtime");
		List<ReportCount> list = new ArrayList<ReportCount>();
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		if (!"".equals(this.getParameter("qrybegintime"))
				&& !"".equals(this.getParameter("qryendtime"))) {

			String sqlCount = "select count(r.reportId) from reportCount r inner join adPlan ug on r.adPlanId = ug.adPlanId,("
					+ "select max(reportId) as maxs "
					+ "from ReportCount where reportStatus = :reportState ";

			String sql = "select {r.*},r1.*,ug.AdplanRebate,c.Payee from reportCount r inner join adPlan ug on r.adPlanId = ug.adPlanId "
					+ "right join Customer c on ug.CustomerId = c.CustomerId,("
					+ "select sum(count) as counts,sum(money) as moneys,sum(realcount) as realcounts,sum(realmoney) as realmoneys,max(reportId) as maxs "
					+ "from ReportCount where reportStatus = :reportState ";
			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = "";
			parms.put("reportState", 1);
			if (!"".equals(this.getParameter("qrywebmaster"))) {
				condition += " and webMasterName like :qrywebmaster ";
				parms.put("qrywebmaster",
						"%" + this.getParameter("qrywebmaster") + "%");
			}

			if (!"".equals(this.getParameter("qryadplan"))) {

				String[] ad = this.getParameter("qryadplan").split(" ");
				condition += "and (";
				for (String name : ad) {
					condition += "adplanName like '%" + name + "%' or ";
				}

				condition = condition.substring(0, condition.length() - 3);
				condition += ")";
			}

			if (!"".equals(this.getParameter("qryadplancycle"))) {

				String[] cycle = this.getParameter("qryadplancycle").split(" ");
				condition += "and (";
				for (String name : cycle) {
					condition += "adplanCycleName like '%" + name + "%' or ";
				}

				condition = condition.substring(0, condition.length() - 3);
				condition += ")";
			}

			// if (!"".equals(this.getParameter("qryadplan"))) {
			//
			// String[] ad = this.getParameter("qryadplan").split(" ");
			// StringBuffer sb = new StringBuffer();
			// sb.append(" and (");
			// for (int i = 0; i < ad.length; i++) {
			// sb.append("adPlanName like '%" + ad[i] + "%'");
			// if (i != ad.length - 1) {
			// sb.append(" or ");
			// }
			// }
			//
			// sb.append(")");
			//
			// condition += sb.toString();
			//
			// }

			if (!"".equals(this.getParameter("qrybegintime"))) {
				condition += " and reportTime >= :qrybegintime ";
				parms.put("qrybegintime", this.getParameter("qrybegintime"));
			}
			if (!"".equals(this.getParameter("qryendtime"))) {
				condition += " and reportTime <= :qryendtime ";
				parms.put("qryendtime", this.getParameter("qryendtime"));
			}
			if (!"".equals(this.getParameter("qrysales"))
					&& 0 != Integer.parseInt(this.getParameter("qrysales"))) {
				condition += " and userId = :qrysales ";
				parms.put("qrysales",
						Integer.parseInt(this.getParameter("qrysales")));
			}
			condition += " group by adplanid,webmasterid) r1 where r1.maxs = r.reportid";

			System.out.println(sql + condition);

			// 当前登陆用户usergroup
			int userGroup = this.getUserSession(request).getUserGroup();
			if (userGroup != 0) {
				condition += " and ug.userGroup = " + userGroup;
			} else {
				if (!"".equals(this.getParameter("currentusergroup"))) {
					if (Integer.parseInt(this.getParameter("currentusergroup")) != 0) {
						condition += " and ug.userGroup = "
								+ Integer.parseInt(this
										.getParameter("currentusergroup"));
					}
				}
			}

			List<Object> cols = new ArrayList<Object>();
			cols.add(ReportCount.class);
			cols.add("counts");
			cols.add("moneys");
			cols.add("realcounts");
			cols.add("realmoneys");
			cols.add("adPlanRebate");
			cols.add("payee");
			pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
					dao.getCount(sqlCount + condition, parms));

			String sqlSum = "select sum(r1.counts) counts,sum(r1.moneys) moneys,sum(r1.realcounts) realCounts,sum(r1.realmoneys) realMoneys from reportCount r "
					+ "inner join adPlan ug on r.adPlanId = ug.adPlanId,("
					+ "select sum(count) as counts,sum(money) as moneys,sum(realcount) as realcounts,sum(realmoney) as realmoneys,max(reportId) as maxs "
					+ "from ReportCount where reportStatus = :reportState ";
			List<Object[]> sumList = dao.findBySql(sqlSum + condition, parms);
			for (Object[] object : sumList) {
				if (object[0] != null) {
					countSum = Integer.parseInt(object[0] + "");
					moneySum = Float.parseFloat(object[1] + "");
					realCountSum = Integer.parseInt(object[2] + "");
					realMoneySum = Float.parseFloat(object[3] + "");
				}
			}
			List qryList = dao.findBySql(pageMap.get("first"),
					pageMap.get("size"), sql + condition
							+ " order by r.reportId desc", parms, cols);
			for (int i = 0; i < qryList.size(); i++) {
				Object[] object = (Object[]) qryList.get(i);
				ReportCount reportCount = (ReportCount) object[0];
				reportCount.setCount(Integer.parseInt(object[1] + ""));
				reportCount.setMoney(Float.parseFloat(object[2] + ""));
				reportCount.setRealCount(Integer.parseInt(object[3] + ""));
				reportCount.setRealMoney(Float.parseFloat(object[4] + ""));
				reportCount.setAdPlanRebate(Integer.parseInt(object[5] + ""));
				reportCount.setPayee(object[6] + "");

				reportCount.setMoneyRebate(reportCount.getMoney()
						* (100 - reportCount.getAdPlanRebate()) / 100);

				reportCount.setProfit(reportCount.getMoneyRebate()
						- reportCount.getRealMoney());
				reportCount.setProfitRate(reportCount.getProfit()
						/ reportCount.getMoneyRebate() * 100);

				list.add(reportCount);
				moneyRebateSum += reportCount.getMoneyRebate();
			}

		} else {
			pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, 0);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}
		this.getUsersList(request);

		request.setAttribute("countSum", countSum);
		request.setAttribute("moneySum", moneySum);
		request.setAttribute("moneyRebateSum", moneyRebateSum);
		request.setAttribute("realCountSum", realCountSum);
		request.setAttribute("realMoneySum", realMoneySum);

		request.setAttribute("reportState", this.getParameter("reportState"));
		request.setAttribute("qrywebmaster", this.getParameter("qrywebmaster"));
		request.setAttribute("qryadplan", this.getParameter("qryadplan"));
		request.setAttribute("qryadplancycle",
				this.getParameter("qryadplancycle"));
		request.setAttribute("qrybegintime", beginTime);
		request.setAttribute("qryendtime", endTime);
		request.setAttribute("qrysales", this.getParameter("qrysales"));

		request.setAttribute("usergroup", this.getUserSession(request)
				.getUserGroup());
		request.setAttribute("currentusergroup",
				this.getParameter("currentusergroup"));
		this.getUserGroupList(request);
		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	/**
	 * 详细列表
	 */
	public ActionForward details(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;
		int numPerPage = 20;
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sql = "select {r.*} from reportCount r where  ";
		String condition = "";
		Map<String, Object> parms = new HashMap<String, Object>();
		if (!"".equals(this.getParameter("payId"))) {
			condition = " r.payId = :payId and r.reportStatus = :reportState ";
			parms.put("payId", Integer.parseInt(this.getParameter("payId")));
			parms.put("reportState", 2);
			request.setAttribute("payId", this.getParameter("payId"));
		} else {
			condition = " r.adplanId = :adPlanId and r.webMasterId = :webMasterId and r.reportStatus = :reportState and r.reportTime >= :startTime and r.reportTime<= :endTime ";
			parms.put("adPlanId",
					Integer.parseInt(this.getParameter("adPlanId")));
			parms.put("webMasterId",
					Integer.parseInt(this.getParameter("webMasterId")));
			parms.put("startTime", this.getParameter("beginTime"));
			parms.put("endTime", this.getParameter("endTime"));
			parms.put("reportState", 1);
			request.setAttribute("adPlanId", this.getParameter("adPlanId"));
			request.setAttribute("webMasterId",
					this.getParameter("webMasterId"));
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(ReportCount.class);
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage,
				dao.getCount(ReportCount.class, " and " + condition, parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),
				sql + condition + " order by r.reportId desc", parms, cols);
		List<ReportCount> list = new ArrayList<ReportCount>();
		for (int i = 0; i < qryList.size(); i++) {
			ReportCount reportCount = (ReportCount) qryList.get(i);
			list.add(reportCount);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}
		return mapping.findForward("details");
	}

	/**
	 * 结算页面
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward balance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			ReportCount reportCount = (ReportCount) dao.findById(
					ReportCount.class,
					Integer.parseInt(this.getParameter("reportId")));
			Pay pay = new Pay();
			pay.setAdPlanId(reportCount.getAdplanId());
			pay.setAdPlanName(reportCount.getAdplanName());
			pay.setAdPlanCycleId(reportCount.getAdplanCycleId());
			pay.setAdPlanCycleName(reportCount.getAdplanCycleName());
			pay.setWebMasterId(reportCount.getWebMasterId());
			pay.setWebMasterName(reportCount.getWebMasterName());
			pay.setUserId(reportCount.getUserId());
			pay.setUserName(reportCount.getUserName());
			String count = this.getParameter("count");
			String money = this.getParameter("money");
			String moneyRebate = this.getParameter("moneyRebate");
			String rebate = this.getParameter("rebate");
			String realCount = this.getParameter("realCount");
			String realMoney = this.getParameter("realMoney");
			String trueMoney = this.getParameter("trueMoney");
			String taxNum = this.getParameter("taxNum");
			String payee = this.getParameter("payee");
			if (count.indexOf(",") != -1 || count.indexOf("，") != -1) {
				count = count.replace(",", "").replace("，", "");
			}
			if (money.indexOf(",") != -1 || money.indexOf("，") != -1) {
				money = money.replace(",", "").replace("，", "");
			}
			if (realCount.indexOf(",") != -1 || realCount.indexOf("，") != -1) {
				realCount = realCount.replace(",", "").replace("，", "");
			}
			if (realMoney.indexOf(",") != -1 || realMoney.indexOf("，") != -1) {
				realMoney = realMoney.replace(",", "").replace("，", "");
			}
			if (trueMoney.indexOf(",") != -1 || trueMoney.indexOf("，") != -1) {
				trueMoney = trueMoney.replace(",", "").replace("，", "");
			}
			pay.setCount(Integer.parseInt(count));
			pay.setMoney(Double.parseDouble(moneyRebate));
			pay.setRealCount(Integer.parseInt(realCount));
			pay.setRealMoney(Double.parseDouble(realMoney));
			pay.setTrueMoney(Double.parseDouble(trueMoney));
			pay.setRemarks(this.getParameter("remark"));
			pay.setPayBeginTime(this.getParameter("beginTime"));
			pay.setPayEndTime(this.getParameter("endTime"));
			pay.setPayAddTime(DatetimeHandle.formatCurrentDate());
			pay.setTaxNum(Double.parseDouble(taxNum));
			pay.setFinanceFlag(0);
			pay.setFinanceMark("已核对，待审核");
			pay.setPayCompanyType(0);
			pay.setPayCompany("");

			// 20160510新增
			pay.setMoneyBeforeRebate(Double.parseDouble(money));
			pay.setMoneyReceipt(0);
			pay.setRebate(Integer.parseInt(rebate));
			pay.setPayee(payee);

			int payId = dao.save(pay);
			String sql = "update ReportCount set reportStatus = :state,payId=:payId where adplanId =:adplanId and webMasterId = :webMasterId and reportStatus = 1 "
					+ "and reportTime between :startTime and :endTime";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("state", 2);
			parms.put("payId", payId);
			parms.put("adplanId", reportCount.getAdplanId());
			parms.put("webMasterId", reportCount.getWebMasterId());
			parms.put("startTime", this.getParameter("beginTime"));
			parms.put("endTime", this.getParameter("endTime"));
			dao.doSql(sql, parms);
			this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 结算报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward reportList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;
		int numPerPage = 20;
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sqlCount = "select count(*) from pay p inner join adPlan ug on p.adPlanId = ug.adPlanId where 1=1";
		String sql = "select * from pay p inner join adPlan ug on p.adPlanId = ug.adPlanId where 1=1 ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		if (!"".equals(this.getParameter("qrywebmaster"))) {
			condition += " and p.webMasterName like :qrywebmaster ";
			parms.put("qrywebmaster", "%" + this.getParameter("qrywebmaster")
					+ "%");
		}
		if (!"".equals(this.getParameter("qryadplan"))) {
			condition += " and p.adPlanName like :qryadplan ";
			parms.put("qryadplan", "%" + this.getParameter("qryadplan") + "%");
		}
		if (!"".equals(this.getParameter("qryadplancycle"))) {

			String[] cycle = this.getParameter("qryadplancycle").split(" ");
			condition += "and (";
			for (String name : cycle) {
				condition += "p.adplanCycleName like '%" + name + "%' or ";
			}

			condition = condition.substring(0, condition.length() - 3);
			condition += ")";
		}
		if (!"".equals(this.getParameter("remarks"))) {
			condition += " and p.remarks like :remarks ";
			parms.put("remarks", "%" + this.getParameter("remarks") + "%");
		}
		if (!"".equals(this.getParameter("qrybegintime"))) {
			condition += " and p.payBeginTime <= :beginTime and p.payEndTime >= :endTime";
			parms.put("beginTime", this.getParameter("qrybegintime"));
			parms.put("endTime", this.getParameter("qrybegintime"));
			// condition +=
			// " and convert(varchar(10),p.payAddTime,120) = :addtime";
			// parms.put("addtime", this.getParameter("qrybegintime"));
		}
		if (!"".equals(this.getParameter("qrysales"))
				&& 0 != Integer.parseInt(this.getParameter("qrysales"))) {
			condition += " and p.userId = :qrysales ";
			parms.put("qrysales",
					Integer.parseInt(this.getParameter("qrysales")));
		}
		if (!"".equals(this.getParameter("financeflag"))) {
			condition += " and p.financeFlag = :financeflag";
			parms.put("financeflag",
					Integer.parseInt(this.getParameter("financeflag")));
		}

		// 当前登陆用户usergroup
		// int userGroup = this.getUserSession(request).getUserGroup();
		// if(userGroup != 0){
		// condition+=" and ug.userGroup = "+userGroup;
		// } else {
		// if (!"".equals(this.getParameter("currentusergroup"))){
		// if (Integer.parseInt(this.getParameter("currentusergroup")) != 0){
		// condition+=" and ug.userGroup = "+Integer.parseInt(this.getParameter("currentusergroup"));
		// }
		// }
		// }
		List<Object> cols = new ArrayList<Object>();
		cols.add(Pay.class);
		// pageMap = PagingHandle.getPagingParams(pageNum,
		// numPerPage,dao.getCount(Pay.class,condition,parms));
		pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,
				dao.getCount(sqlCount + condition, parms));
		long countSum = 0;
		double moneySum = 0;
		double moneyBeforeRebateSum = 0;
		long realCountSum = 0;
		double realMoneySum = 0;
		double trueMoneySum = 0;
		// String sqlSum
		// ="select sum(count) counts,sum(money) moneys,sum(realcount) realCounts,sum(realmoney) realMoneys,sum(trueMoney) trueMoneys from pay p"
		// +
		// " inner join adPlan ug on p.adPlanId = ug.adPlanId where 1=1 " ;
		String sqlSum = "select sum(cast(count as bigint)) counts,sum(moneyBeforeRebate) moneyBeforeRebate,sum(money) moneys,sum(cast(realcount as bigint)) realCounts,sum(realmoney) realMoneys,sum(trueMoney) trueMoneys from pay p"
				+ " inner join adPlan ug on p.adPlanId = ug.adPlanId where 1=1 ";

		// if(userGroup != 0){
		// sqlSum+=" and ug.userGroup = "+userGroup;
		// } else {
		// if (!"".equals(this.getParameter("currentusergroup"))){
		// if (Integer.parseInt(this.getParameter("currentusergroup")) != 0){
		// sqlSum+=" and ug.userGroup = "+Integer.parseInt(this.getParameter("currentusergroup"));
		// }
		// }
		// }
		List<Object[]> sumList = dao.findBySql(sqlSum + condition, parms);
		for (Object[] object : sumList) {
			if (null != object[0]) {
				countSum = Long.parseLong(object[0] + "");
				moneyBeforeRebateSum = Double.parseDouble(object[1] + "");
				moneySum = Double.parseDouble(object[2] + "");
				realCountSum = Long.parseLong(object[3] + "");
				realMoneySum = Double.parseDouble(object[4] + "");
				trueMoneySum = Double.parseDouble(object[5] + "");
			}
		}
		List<Pay> list = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"),
				sql + condition + " order by p.payId desc", parms, cols);
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}
		this.getUsersList(request);
		request.setAttribute("countSum", countSum);
		request.setAttribute("moneyBeforeRebateSum", moneyBeforeRebateSum);
		request.setAttribute("moneySum", moneySum);
		request.setAttribute("realCountSum", realCountSum);
		request.setAttribute("realMoneySum", realMoneySum);
		request.setAttribute("trueMoneySum", trueMoneySum);

		request.setAttribute("qrywebmaster", this.getParameter("qrywebmaster"));
		request.setAttribute("qryadplan", this.getParameter("qryadplan"));
		request.setAttribute("qryadplancycle",
				this.getParameter("qryadplancycle"));
		request.setAttribute("remarks", this.getParameter("remarks"));
		request.setAttribute("qrybegintime", this.getParameter("qrybegintime"));
		request.setAttribute("qrysales", this.getParameter("qrysales"));
		request.setAttribute("financeflag", this.getParameter("financeflag"));

		request.setAttribute("usergroup", this.getUserSession(request)
				.getUserGroup());
		request.setAttribute("currentusergroup",
				this.getParameter("currentusergroup"));
		this.getUserGroupList(request);
		return mapping.findForward("reportlist");
	}

	/**
	 * 结算报表修改
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Pay pay = (Pay) dao.findById(Pay.class,
					Integer.parseInt(this.getParameter("payId")));
			String trueMoney = this.getParameter("trueMoney");
			if (trueMoney.indexOf(",") != -1 || trueMoney.indexOf("，") != -1) {
				trueMoney = trueMoney.replace(",", "").replace("，", "");
			}
			pay.setTrueMoney(Double.parseDouble(trueMoney));
			pay.setRemarks(this.getParameter("remark"));
			dao.update(pay);
			this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 结算报表导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward report(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String fileName = "结算报表";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型
			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("开始时间");
			headerList.add("结束时间");
			headerList.add("网站主");
			headerList.add("媒介");
			headerList.add("广告名称");
			headerList.add("返回值");
			headerList.add("应收");
			headerList.add("利润");
			headerList.add("利润率");
			headerList.add("有效值");
			headerList.add("有效佣金");
			headerList.add("实际支付佣金");
			headerList.add("备注");
			headerList.add("税点");
			headerList.add("结算状态");
			headerList.add("结算备注");
			headerList.add("支付公司");
			headerList.add("公司备注");

			int userGroup = this.getUserSession(request).getUserGroup();// 当前登陆用户usergroup
			String sql = "select p.* from Pay p inner join adPlan ug on p.adPlanId = ug.adPlanId where 1=1 ";
			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = "";
			if (!"".equals(this.getParameter("qrywebmaster"))) {
				condition += " and p.webMasterName like :qrywebmaster ";
				parms.put("qrywebmaster",
						"%" + this.getParameter("qrywebmaster") + "%");
			}
			if (!"".equals(this.getParameter("qryadplan"))) {
				condition += " and p.adPlanName like :qryadplan ";
				parms.put("qryadplan", "%" + this.getParameter("qryadplan")
						+ "%");
			}
			if (!"".equals(this.getParameter("qrybegintime"))) {
				condition += " and p.payBeginTime <= :beginTime and p.payEndTime >= :endTime";
				parms.put("beginTime", this.getParameter("qrybegintime"));
				parms.put("endTime", this.getParameter("qrybegintime"));
			}
			if (!"".equals(this.getParameter("qrysales"))
					&& 0 != Integer.parseInt(this.getParameter("qrysales"))) {
				condition += " and p.userId = :qrysales ";
				parms.put("qrysales",
						Integer.parseInt(this.getParameter("qrysales")));
			}
			if (!"".equals(this.getParameter("financeflag"))) {
				condition += " and p.financeFlag = :financeflag";
				parms.put("financeflag",
						Integer.parseInt(this.getParameter("financeflag")));
			}

			if (userGroup != 0) {
				condition += " and ug.userGroup =:userGroup ";
				parms.put("userGroup", userGroup);
			} else {
				if (!"".equals(this.getParameter("currentusergroup"))) {
					if (Integer.parseInt(this.getParameter("currentusergroup")) != 0) {
						condition += " and ug.userGroup =:userGroup ";
						parms.put("userGroup", Integer.parseInt(this
								.getParameter("currentusergroup")));
					}
				}
			}

			List<Object[]> list = dao.findBySql(sql + condition, parms);
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			for (Object[] pay : list) {
				Object[] object = new Object[19];
				object[0] = pay[0] + "";
				object[1] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(pay[15] + ""),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				object[2] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(pay[16] + ""),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				object[3] = pay[6] + "";
				object[4] = pay[8] + "";
				object[5] = pay[2] + "";
				object[6] = df.format(pay[9]);
				object[7] = "￥" + df.format(pay[10]);
				object[8] = "￥"
						+ df.format(Float.parseFloat(pay[10] + "")
								- Float.parseFloat(pay[12] + ""));
				object[9] = df.format((Float.parseFloat(pay[10] + "") - Float
						.parseFloat(pay[12] + ""))
						/ Float.parseFloat(pay[10] + "") * 100) + "%";
				object[10] = df.format(Integer.parseInt(pay[11] + ""));
				object[11] = "￥" + df.format(pay[12]);
				object[12] = "￥" + df.format(pay[13]);
				object[13] = pay[14] + "";
				object[14] = pay[18] + "";
				if (Integer.parseInt(pay[19] + "") == 0) {
					object[15] = "待审核";
				} else if (Integer.parseInt(pay[19] + "") == 1) {
					object[15] = "已审核，待支付";
				} else if (Integer.parseInt(pay[19] + "") == 2) {
					object[15] = "支付成功";
				} else if (Integer.parseInt(pay[19] + "") == 3) {
					object[15] = "未支付成功";
				} else {
					object[15] = "未知";
				}
				object[16] = pay[20] + "";
				if (Integer.parseInt(pay[21] + "") == 1) {
					object[17] = "盘酷科技";
				} else if (Integer.parseInt(pay[21] + "") == 2) {
					object[17] = "环宇移通";
				} else if (Integer.parseInt(pay[21] + "") == 3) {
					object[17] = "中润无限";
				} else {
					object[17] = "未知";
				}
				object[18] = pay[22] + "";
				datas.add(object);
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看回款信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward moneyReceiptState(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int cycleId = Integer.parseInt(this.getParameter("adPlanCycleId"));

		AdPlanCycle cycle = (AdPlanCycle) dao.findById(AdPlanCycle.class,
				cycleId);

		String hql = "from CycleMoneyReceipt c where c.adPlanCycleId = :cycleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cycleId", cycleId);
		List<CycleMoneyReceipt> receipts = dao.findAll(hql, "", params);

		request.setAttribute("adPlanCycle", cycle);
		request.setAttribute("receipts", receipts);
		return mapping.findForward("moneyReceiptState");
	}

	/**
	 * 新增回款信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addMoneyReceipt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String date = this.getParameter("receiptDate");
		double receiptMoney = Double.parseDouble(this
				.getParameter("receiptMoney"));
		String remarks = this.getParameter("remarks");
		int cycleId = Integer.parseInt(this.getParameter("adPlanCycleId"));

		CycleMoneyReceipt receipt = new CycleMoneyReceipt();
		receipt.setAdPlanCycleId(cycleId);
		receipt.setReceiptDate(date);
		receipt.setReceiptMoney(receiptMoney);
		receipt.setRemarks(remarks);

		try {
			dao.save(receipt);

			this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			return mapping.findForward("ajaxDone");
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
			return null;
		}
	}
}
