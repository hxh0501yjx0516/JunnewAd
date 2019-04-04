package com.pancou.ad.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.Users;

public class MediaAction extends BaseDispatchAction {
	/**
	 * 媒介--资料修改--Table:User
	 */
	public ActionForward editAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 判断登录
		Users user = (Users) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("logout");
		} else {
			// input信息，修改
			Users media = (Users) dao.findById(Users.class, user.getUserId());
			if ("editsave".equals(this.getParameter("flag"))) {
				// 修改
				try {
					media.setUsername(this.getParameter("name"));
					media.setRealname(this.getParameter("realname"));
					media.setTel(this.getParameter("tel"));
					media.setDept(this.getParameter("dep"));
					media.setTitle(this.getParameter("title"));
					media.setState(Integer.parseInt(this.getParameter("state")));
					dao.update(media);
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
					return mapping.findForward("ajaxDone");
				} catch (Exception e) {
					e.printStackTrace();
					this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
				}
			} else {
				// input 信息
				request.setAttribute("media", media);
				return mapping.findForward("editAction");
			}
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 媒介--投放列表--Table:ReadyBox
	 */
	public ActionForward readyBoxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 判断登录
		Users user = (Users) request.getSession().getAttribute("user");
		int pageNum = 0; // 当前页
		int numPerPage = 20;// 每页条数
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		int userId = user.getUserId();
		String adPlanName = this.getParameter("adPlanName");
		String adPlanCycleName = this.getParameter("adPlanCycleName");
		String adBoxName = this.getParameter("adBoxName");
		String urlName = this.getParameter("urlName");
		String webMasterName = this.getParameter("webMasterName");
		String sqlCount = "select count(r.readyBoxId) "
				+ " from ReadyBox as r left join adBox as b on r.adBoxId = b.adBoxId "
				+ " left join adCreative as c on r.adCreativeId = c.adCreativeId "
				+ " left join adPlanCycle as y on r.adPlanCycleId = y.adPlanCycleId "
				+ " left join adPlan as p on r.adPlanId = p.adPlanId "
				+ " left join url as u on r.urlId = u.urlId "
				+ " left join webMaster as w on r.webMasterId = w.webMasterId where 1=1 ";
		String sqlString = "select r.*, b.adBoxName adBoxName,c.adCreativeName adCreativeName,y.adPlanCycleName adPlanCycleName,p.adPlanName adPlanName,u.url urlName,w.webMasterName  webMasterName ,c.isDefault isDefault "
				+ " from ReadyBox as r left join adBox as b on r.adBoxId = b.adBoxId "
				+ " left join adCreative as c on r.adCreativeId = c.adCreativeId "
				+ " left join adPlanCycle as y on r.adPlanCycleId = y.adPlanCycleId "
				+ " left join adPlan as p on r.adPlanId = p.adPlanId "
				+ " left join url as u on r.urlId = u.urlId "
				+ " left join webMaster as w on r.webMasterId = w.webMasterId where 1=1  ";
		String condition = "";
		Map<String, Object> parms = new HashMap<String, Object>();
		if (adPlanName != null && !"".equals(adPlanName)) {
			condition += " and p.adPlanName like :adplanname";
			parms.put("adplanname", "%" + adPlanName + "%");
		}
		if (adPlanCycleName != null && !"".equals(adPlanCycleName)) {
			condition += " and y.adPlanCycleName like :adplancyclename";
			parms.put("adplancyclename", "%" + adPlanCycleName + "%");
		}
		if (adBoxName != null && !"".equals(adBoxName)) {
			condition += " and b.adBoxName like :adboxname";
			parms.put("adboxname", "%" + adBoxName + "%");
		}
		if (urlName != null && !"".equals(urlName)) {
			condition += " and u.url like :urlname";
			parms.put("urlname", "%" + urlName + "%");
		}
		if (webMasterName != null && !"".equals(webMasterName)) {
			condition += " and w.webMasterName like :webmastername";
			parms.put("webmastername", "%" + webMasterName + "%");
		}
		if (!"".equals(this.getParameter("readyboxstatus"))) {
			condition += " and r.readyBoxStatus=:readyboxstatus";
			parms.put("readyboxstatus",
					Integer.parseInt(this.getParameter("readyboxstatus")));
		}
		if (!"".equals(this.getParameter("isdefault"))) {
			condition += " and c.isDefault=:isdefault";
			parms.put("isdefault",
					Integer.parseInt(this.getParameter("isdefault")));
		}
		if ("is".equals(this.getParameter("ismedia"))) {
			condition += " and r.userId =:userid";
			parms.put("userid", userId);
		}

		System.out.println(this.getParameter("ismedia"));
		if (user.getUserGroup() != 0) {
			// 执行
			condition += " and p.userGroup =:userGroup";
			parms.put("userGroup", user.getUserGroup());
		} else {
			// 管理员
			if (!"".equals(this.getParameter("qryuserGroup"))) {
				condition += " and p.userGroup =:userGroup";
				parms.put("userGroup",
						Integer.parseInt(this.getParameter("qryuserGroup")));
			}
			this.getUserGroupList(request);
		}

		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(sqlCount + condition, parms));
		List<Object> cols = new ArrayList<Object>();
		cols.add(ReadyBox.class);
		cols.add("adBoxName");
		cols.add("adCreativeName");
		cols.add("adPlanCycleName");
		cols.add("adPlanName");
		cols.add("urlName");
		cols.add("webMasterName");
		cols.add("isDefault");
		List<Object[]> rb = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"), sqlString + condition
						+ " order by r.readyBoxId desc,r.adBoxId asc", parms,
				cols);
		List<ReadyBox> list = new ArrayList<ReadyBox>();

		for (int i = 0; i < rb.size(); i++) {
			Object[] object = rb.get(i);
			ReadyBox readybox = (ReadyBox) object[0];
			readybox.setAdBoxName(object[1] + "");
			readybox.setAdCreativeName(object[2] + "");
			readybox.setAdPlanCycleName(object[3] + "");
			readybox.setAdPlanName(object[4] + "");
			readybox.setUrlName(object[5] + "");
			readybox.setWebMasterName(object[6] + "");
			readybox.setIsDefault(Integer.parseInt(object[7] + ""));

			Map<String, Object> waveMap = this
					.getWave(readybox.getReadyBoxId());
			readybox.setWaveBeginTime(waveMap.get("beginTime").toString());
			readybox.setWaveBDTime(waveMap.get("bdTime").toString());
			readybox.setWaveState(Integer.parseInt(waveMap.get("waveState")
					.toString()));
			readybox.setWavePercent(Double.parseDouble(waveMap
					.get("wavePercent") + ""));
			list.add(readybox);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}

		request.setAttribute("adPlanName", adPlanName);
		request.setAttribute("adPlanCycleName", adPlanCycleName);
		request.setAttribute("adBoxName", adBoxName);
		request.setAttribute("urlName", urlName);
		request.setAttribute("webMasterName", webMasterName);
		request.setAttribute("readyboxstatus",
				this.getParameter("readyboxstatus"));
		request.setAttribute("isdefault", this.getParameter("isdefault"));
		request.setAttribute("qryuserGroup", this.getParameter("qryuserGroup"));
		return mapping.findForward("readyBoxList");
	}

	/**
	 * 媒介--投放列表--Table:ReadyBox
	 */
	public ActionForward readyBoxListToExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String fileName = "数据报表";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("站长");
			headerList.add("域名");
			headerList.add("广告位");
			headerList.add("广告计划");
			headerList.add("计划周期");
			headerList.add("广告创意");
			headerList.add("日期");
			// 判断登录
			Users user = (Users) request.getSession().getAttribute("user");
			int userId = user.getUserId();
			String adPlanName = this.getParameter("adPlanName");
			String adPlanCycleName = this.getParameter("adPlanCycleName");
			String adBoxName = this.getParameter("adBoxName");
			String urlName = this.getParameter("urlName");
			String webMasterName = this.getParameter("webMasterName");
			String sqlCount = "select count(r.readyBoxId) "
					+ " from ReadyBox as r left join adBox as b on r.adBoxId = b.adBoxId "
					+ " left join adCreative as c on r.adCreativeId = c.adCreativeId "
					+ " left join adPlanCycle as y on r.adPlanCycleId = y.adPlanCycleId "
					+ " left join adPlan as p on r.adPlanId = p.adPlanId "
					+ " left join url as u on r.urlId = u.urlId "
					+ " left join webMaster as w on r.webMasterId = w.webMasterId where 1=1 ";
			String sqlString = "select r.*, b.adBoxName adBoxName,c.adCreativeName adCreativeName,y.adPlanCycleName adPlanCycleName,p.adPlanName adPlanName,u.url urlName,w.webMasterName  webMasterName ,c.isDefault isDefault "
					+ " from ReadyBox as r left join adBox as b on r.adBoxId = b.adBoxId "
					+ " left join adCreative as c on r.adCreativeId = c.adCreativeId "
					+ " left join adPlanCycle as y on r.adPlanCycleId = y.adPlanCycleId "
					+ " left join adPlan as p on r.adPlanId = p.adPlanId "
					+ " left join url as u on r.urlId = u.urlId "
					+ " left join webMaster as w on r.webMasterId = w.webMasterId where 1=1  ";
			String condition = "";
			Map<String, Object> parms = new HashMap<String, Object>();
			if (adPlanName != null && !"".equals(adPlanName)) {
				condition += " and p.adPlanName like :adplanname";
				parms.put("adplanname", "%" + adPlanName + "%");
			}
			if (adPlanCycleName != null && !"".equals(adPlanCycleName)) {
				condition += " and y.adPlanCycleName like :adplancyclename";
				parms.put("adplancyclename", "%" + adPlanCycleName + "%");
			}
			if (adBoxName != null && !"".equals(adBoxName)) {
				condition += " and b.adBoxName like :adboxname";
				parms.put("adboxname", "%" + adBoxName + "%");
			}
			if (urlName != null && !"".equals(urlName)) {
				condition += " and u.url like :urlname";
				parms.put("urlname", "%" + urlName + "%");
			}
			if (webMasterName != null && !"".equals(webMasterName)) {
				condition += " and w.webMasterName like :webmastername";
				parms.put("webmastername", "%" + webMasterName + "%");
			}
			if (!"".equals(this.getParameter("readyboxstatus"))) {
				condition += " and r.readyBoxStatus=:readyboxstatus";
				parms.put("readyboxstatus",
						Integer.parseInt(this.getParameter("readyboxstatus")));
			}
			if (!"".equals(this.getParameter("isdefault"))) {
				condition += " and c.isDefault=:isdefault";
				parms.put("isdefault",
						Integer.parseInt(this.getParameter("isdefault")));
			}
			if ("is".equals(this.getParameter("ismedia"))) {
				condition += " and r.userId =:userid";
				parms.put("userid", userId);
			}

			if (user.getUserGroup() != 0) {
				// 执行
				condition += " and p.userGroup =:userGroup";
				parms.put("userGroup", user.getUserGroup());
			} else {
				// 管理员
				if (!"".equals(this.getParameter("qryuserGroup"))) {
					condition += " and p.userGroup =:userGroup";
					parms.put("userGroup",
							Integer.parseInt(this.getParameter("qryuserGroup")));
				}
				this.getUserGroupList(request);
			}
			List<Object> cols = new ArrayList<Object>();
			cols.add(ReadyBox.class);
			cols.add("adBoxName");
			cols.add("adCreativeName");
			cols.add("adPlanCycleName");
			cols.add("adPlanName");
			cols.add("urlName");
			cols.add("webMasterName");
			cols.add("isDefault");
			List<Object[]> rb = dao.findBySql(sqlString + condition
					+ " order by r.readyBoxId desc,r.adBoxId asc", parms, cols);
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			for (int i = 0; i < rb.size(); i++) {
				Object[] object = rb.get(i);
				Object[] objects = new Object[8];
				ReadyBox readybox = (ReadyBox) object[0];
				readybox.setAdBoxName(object[1] + "");
				readybox.setAdCreativeName(object[2] + "");
				readybox.setAdPlanCycleName(object[3] + "");
				readybox.setAdPlanName(object[4] + "");
				readybox.setUrlName(object[5] + "");
				readybox.setWebMasterName(object[6] + "");
				readybox.setIsDefault(Integer.parseInt(object[7] + ""));
				// Map<String,Object> waveMap =
				// this.getWave(readybox.getReadyBoxId());
				// readybox.setWaveBeginTime(waveMap.get("beginTime").toString());
				// readybox.setWaveBDTime(waveMap.get("bdTime").toString());
				// readybox.setWaveState(Integer.parseInt(waveMap.get("waveState").toString()));
				// readybox.setWavePercent(Double.parseDouble(waveMap.get("wavePercent")+""));
				objects[0] = readybox.getReadyBoxId();
				objects[1] = readybox.getWebMasterName();
				objects[2] = readybox.getUrlName();
				objects[3] = readybox.getAdBoxName();
				objects[4] = readybox.getAdPlanName();
				objects[5] = readybox.getAdPlanCycleName();
				objects[6] = readybox.getAdCreativeName();
				objects[7] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(readybox.getAddTime()),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				datas.add(objects);
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 媒介--数据报表--Table:ReportCount
	 */
	public ActionForward reportCountList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 数据报表
		String search = this.getParameter("search");

		int pageNum = 0; // 当前页
		int numPerPage = 20;// 每页条数
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}

		// 媒介ID
		Users media = (Users) request.getSession().getAttribute("user");

		// 获取查询
		String beginTime = this.getParameter("beginTime");
		String endTime = this.getParameter("endTime");

		// 数据汇总
		StringBuffer sqlSum = new StringBuffer();
		sqlSum.append("select sum(r.realCount) realCountAll,sum(r.realMoney) realMoneyAll,sum(r.count) countAll,"
				+ " sum(r.money) moneyAll from ReportCount r where r.userId = "
				+ media.getUserId());

		if (!"".equals(beginTime) && !"".equals(endTime)) {
			sqlSum.append(" and r.reportTime >= '" + beginTime
					+ "' and r.reportTime <='" + endTime + "'");
		} else {
			sqlSum.append(" and datediff(day,getdate(),r.reportTime) = 0");
		}
		if (!"".equals(this.getParameter("paytype"))) {
			sqlSum.append(" and r.reportStatus ="
					+ Integer.parseInt(this.getParameter("paytype")));
		}

		if (!"".equals(this.getParameter("webmastername"))) {
			sqlSum.append(" and r.webMasterName like '%"
					+ this.getParameter("webmastername") + "%'");
		}

		if (!"".equals(this.getParameter("adplanname"))) {

			String[] ad = this.getParameter("adplanname").split(" ");
			sqlSum.append("and (");
			for (String name : ad) {
				sqlSum.append("r.adplanName like '%" + name + "%' or ");
			}

			sqlSum.setLength(sqlSum.length() - 3);
			sqlSum.append(")");

		}
		System.out.println(sqlSum);

		List<ReportCount> listCount = dao.getSum(sqlSum.toString());

		if (listCount != null) {
			request.setAttribute("listSum", listCount);
		}

		// 数据详细
		String sqlString = "select {r.*} from ReportCount r where 1=1";
		String condition = " and r.userId = " + media.getUserId();

		List<Object> cols = new ArrayList<Object>();
		cols.add(ReportCount.class);

		Map<String, Object> parms = new HashMap<String, Object>();
		if (!"".equals(beginTime) && !"".equals(endTime)) {
			condition += " and r.reportTime >=:bt and r.reportTime <=:et";
			parms.put("bt", beginTime);
			parms.put("et", endTime);
		} else {
			condition += " and datediff(day,getdate(),r.reportTime) = 0";
		}
		if (!"".equals(this.getParameter("paytype"))) {
			condition += " and r.reportStatus =:paytype";
			parms.put("paytype", Integer.parseInt(this.getParameter("paytype")));
		}

		if (!"".equals(this.getParameter("webmastername"))) {
			condition += " and r.webMasterName like :webmastername";
			parms.put("webmastername", "%" + this.getParameter("webmastername")
					+ "%");
		}

		if (!"".equals(this.getParameter("adplanname"))) {

			String[] ad = this.getParameter("adplanname").split(" ");
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			for (int i = 0; i < ad.length; i++) {
				sb.append("r.adplanName like '%" + ad[i] + "%'");
				if (i != ad.length - 1) {
					sb.append(" or ");
				}
			}

			sb.append(")");

			condition += sb.toString();

		}

		System.out.println("**-----:" + condition);
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(ReportCount.class, condition, parms));
		List<ReportCount> list = dao.findBySql(pageMap.get("first"),
				pageMap.get("size"), sqlString + condition, parms, cols);

		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}

		request.setAttribute("begintime", beginTime);
		request.setAttribute("endtime", endTime);
		request.setAttribute("paytype", this.getParameter("paytype"));
		request.setAttribute("adplanname", this.getParameter("adplanname"));
		request.setAttribute("webmastername",
				this.getParameter("webmastername"));
		return mapping.findForward("reportCountList");
	}

	/**
	 * 媒介--导出报表--Table:ReportCount
	 */
	@SuppressWarnings("unchecked")
	public ActionForward reportCountExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String fileName = "数据报表";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

			List<String> headerList = new ArrayList<String>();
			headerList.add("编号");
			headerList.add("站长");
			headerList.add("域名");
			headerList.add("广告计划");
			headerList.add("周期");
			headerList.add("广告位");
			headerList.add("创意");
			headerList.add("站长有效值");
			headerList.add("站长佣金");
			headerList.add("客户返回值");
			headerList.add("应收佣金");
			headerList.add("利润");
			headerList.add("利润率");
			headerList.add("日期");
			Users media = (Users) request.getSession().getAttribute("user");

			String sqlString = "from ReportCount r where r.userId = "
					+ media.getUserId() + " and r.reportStatus >= 0";
			String condition = " ";
			Map<String, Object> parms = new HashMap<String, Object>();
			if (!"".equals(this.getParameter("begintime"))
					&& !"".equals(this.getParameter("endtime"))) {
				condition = " and r.reportTime >=:bt and r.reportTime <=:et";
				parms.put("bt", this.getParameter("begintime"));
				parms.put("et", this.getParameter("endtime"));
			} else {
				condition = " and datediff(day,getdate(),r.reportTime) = 0";
			}
			if (!"".equals(this.getParameter("paytype"))) {
				condition += " and r.reportStatus =:paytype";
				parms.put("paytype",
						Integer.parseInt(this.getParameter("paytype")));
			}
			if (!"".equals(this.getParameter("adplanname"))) {
				condition += " and r.adplanName like :adplanname";
				parms.put("adplanname", "%" + this.getParameter("adplanname")
						+ "%");
			}
			if (!"".equals(this.getParameter("webmastername"))) {
				condition += " and r.webMasterName like :webmastername";
				parms.put("webmastername",
						"%" + this.getParameter("webmastername") + "%");
			}

			List<ReportCount> list = dao.findAll(sqlString, condition, parms);
			List<Object[]> datas = new ArrayList<Object[]>();
			DecimalFormat df = new DecimalFormat("##,###.###");
			for (ReportCount reportCount : list) {
				Object[] object = new Object[14];
				object[0] = reportCount.getReportId();
				object[1] = reportCount.getWebMasterName();
				object[2] = reportCount.getUrlName();
				object[3] = reportCount.getAdplanName();
				object[4] = reportCount.getAdplanCycleName();
				object[5] = reportCount.getAdBoxName();
				object[6] = reportCount.getAdCreativeName();
				object[7] = df.format(reportCount.getRealCount());
				object[8] = "￥" + df.format(reportCount.getRealMoney());
				object[9] = df.format(reportCount.getCount());
				object[10] = "￥" + df.format(reportCount.getMoney());
				object[11] = "￥"
						+ df.format(reportCount.getMoney()
								- reportCount.getRealMoney());
				object[12] = df.format((reportCount.getMoney() - reportCount
						.getRealMoney()) / reportCount.getMoney() * 100)
						+ "%";
				object[13] = DatetimeHandle.formatDate(
						DatetimeHandle.parseDate(reportCount.getReportTime()),
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				datas.add(object);
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
