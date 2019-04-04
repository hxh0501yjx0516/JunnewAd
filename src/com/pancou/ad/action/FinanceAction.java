package com.pancou.ad.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.vo.Pay;

@Entity
public class FinanceAction extends BaseDispatchAction {

	/**
	 * 结算审核list
	 * 
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
		String sqlCount = "select count(*) from pay p inner join adPlan ug on p.adPlanId = ug.adPlanId where p.financeFlag in (0,3)";
		String sql = "select * from pay p inner join adPlan ug on p.adPlanId = ug.adPlanId where p.financeFlag in (0,3)";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		if (!"".equals(this.getParameter("financeflag"))) {
			condition += " and p.financeFlag = :financeflag";
			parms.put("financeflag",
					Integer.parseInt(this.getParameter("financeflag")));
		}

		List<Object> cols = new ArrayList<Object>();
		cols.add(Pay.class);

		long countSum = 0;
		double moneySum = 0;
		double moneyBeforeRebateSum = 0;
		long realCountSum = 0;
		double realMoneySum = 0;
		double trueMoneySum = 0;
		String sqlSum = "select sum(cast(count as bigint)) counts,sum(moneyBeforeRebate) moneyBeforeRebate,sum(money) moneys,sum(cast(realcount as bigint)) realCounts,sum(realmoney) realMoneys,sum(trueMoney) trueMoneys from pay p"
				+ " inner join adPlan ug on p.adPlanId = ug.adPlanId where p.financeFlag in (0,3)";

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
		List<Pay> list = dao.findBySql(sql + condition
				+ " order by p.payId,p.taxNum desc", parms, cols);
		if (list != null) {
			request.setAttribute("list", list);
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
		request.setAttribute("qrybegintime", this.getParameter("qrybegintime"));
		request.setAttribute("userid",
				this.getParameter("userid").replace(",", ""));
		request.setAttribute("financeflag", this.getParameter("financeflag"));

		request.setAttribute("usergroup", this.getUserSession(request)
				.getUserGroup());
		request.setAttribute("currentusergroup",
				this.getParameter("currentusergroup"));
		this.getUserGroupList(request);
		return mapping.findForward("list");
	}

	/**
	 * review
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward review(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String updateSql = "update Pay p set p.financeFlag = 1,p.financeMark = '已审核，待支付'"
				+ ",p.payCompanyType = 1,p.payCompany = '盘酷科技' where p.payId in ("
				+ this.getParameter("ids") + ")";
		try {
			dao.doSql(updateSql);
			// 10秒后请求接口
			new Timer().schedule(new TimerTask() {
				public void run() {
					try {
						FinanceAction.URLReader();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, 10 * 1000);

			new Timer().schedule(new TimerTask() {
				public void run() {
					try {
						FinanceAction.URLReader();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, 60 * 1000);
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 调用 财务中心平台接口
	 * 
	 * @throws IOException
	 */
	public static void URLReader() throws IOException {
		URL url = new URL("http://124.65.160.238:8080/Interface/?companyId=1");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println("review sync:" + inputLine);
		in.close();
	}
}
