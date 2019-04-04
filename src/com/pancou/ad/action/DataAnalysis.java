package com.pancou.ad.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.dao.AnalysisDataDao;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.Excel;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Users;

public class DataAnalysis extends BaseDispatchAction {

	AnalysisDataDao asdao = new AnalysisDataDao();
	SimpleDateFormat sdfn = new SimpleDateFormat("yyyy-MM-dd");
	private String now = sdfn.format(new Date());
	private static final String ENCODED = "UTF-8", DECODE = "ISO8859-1";

	/**
	 * 数据分析首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ActionForward mainAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");

		// 数据来源列表(*****************数据来源列表*********************)
		Users users = (Users) request.getSession().getAttribute("user");
		// List slist = asdao.findSourceList(0, 5, now, now, "", "", "",
		// "","",users,request.getParameter("qryuserGroup")); // 查询当天
		// List<HashMap<String,Object>> sourcelist = new
		// ArrayList<HashMap<String,Object>>();
		// if (slist != null && !"".equals(slist)) {
		// Iterator results = slist.iterator();
		//
		// while (results.hasNext()) {
		// Object[] rows = (Object[]) results.next();
		// HashMap<String, Object> hash = new HashMap<String, Object>();
		// String cycn = (String)rows[0];
		// String apn = (String)rows[1];
		// String url = (String)rows[2];
		// String wmn = (String)rows[3];
		// String usern = (String)rows[4];
		// String source = (String)rows[5];
		// Date addtime = (Date)rows[6];
		// hash.put("cycn", cycn);// 广告计划周期
		// hash.put("apn", apn); // 广告计划
		// hash.put("url", url);
		// hash.put("wmn", wmn); // 站长
		// hash.put("usern", usern);// 媒介
		// hash.put("source", URLDecoder.decode(source));// 域名
		// hash.put("addt", addtime);
		// sourcelist.add(hash);
		// }
		// }
		// request.setAttribute("sourcelist", sourcelist);

		// 汇总显示数据总量(**************数据总量,今日流量*****************)
		List listsum = asdao.findSumAdBoxC(false, 0, 0,
				sdf2.format(new Date()), "", "", "", "", "", users,
				request.getParameter("qryuserGroup"));
		request.setAttribute("list", listsum); // 1、数据统计：汇总显示数据总量
		// 汇总显示数据总量(**************数据总量, 24小时流量趋势*****************)
		List listsumA = asdao.findSumAdBoxCA(users,
				request.getParameter("qryuserGroup"));
		request.setAttribute("listA", listsumA); // 1、数据统计：汇总显示数据总量

		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		StringBuffer sb = new StringBuffer("<graph caption='")
				.append("日期分析' subcaption='");
		sb.append(" ' numdivlines='9' lineThickness='2' showValues='0' anchorRadius='3' anchorBgAlpha='50' showAlternateVGridColor='1' ");
		sb.append(" numVisiblePlot='12' animation='0'>");

		int minValue = -1;
		sb.append("<categories >");
		// sb.append("<category label='00:00' />");
		// sb.append("<category label='01:00' />");
		// sb.append("<category label='02:00' />");
		// sb.append("<category label='03:00' />");
		// sb.append("<category label='04:00' />");
		// sb.append("<category label='05:00' />");
		// sb.append("<category label='06:00' />");
		// sb.append("<category label='07:00' />");
		// sb.append("<category label='08:00' />");
		// sb.append("<category label='09:00' />");
		// sb.append("<category label='10:00' />");
		// sb.append("<category label='11:00' />");
		// sb.append("<category label='12:00' />");
		// sb.append("<category label='13:00' />");
		// sb.append("<category label='14:00' />");
		// sb.append("<category label='15:00' />");
		// sb.append("<category label='16:00' />");
		// sb.append("<category label='17:00' />");
		// sb.append("<category label='18:00' />");
		// sb.append("<category label='19:00' />");
		// sb.append("<category label='20:00' />");
		// sb.append("<category label='21:00' />");
		// sb.append("<category label='22:00' />");
		// sb.append("<category label='23:00' />");

		// sb.append("</categories >");

		// 曲线图，当天
		@SuppressWarnings("unused")
		int bro = 0, broT = 0, pv = 0, uv = 0, ip = 0, strDate = 0;
		if (listsum != null && !"".equals(listsum)) {
			Iterator results = listsum.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				HashMap<String, Object> hash = new HashMap<String, Object>();
				bro = (Integer) rows[0];
				broT = (Integer) rows[1];
				pv = (Integer) rows[2];
				uv = (Integer) rows[3];
				ip = (Integer) rows[4];
				strDate = (Integer) rows[5];

				sb.append("<category name='").append(strDate).append(":00' />");

				hash.put("mtdate", sdfn.format(strDate));
				hash.put("pv", pv);
				hash.put("uv", uv);
				hash.put("ip", ip);
				all.add(hash);
			}
		}
		sb.append("</categories >");

		// ========= pv ==========
		sb.append("<dataset seriesName='PV' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("pv"))
					.append("'/>");
		}
		sb.append("</dataset>");

		// ========= uv ===========
		sb.append("<dataset seriesName='UV' color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("uv"))
					.append("'/>");
		}
		sb.append("</dataset>");

		// ======== ip ============
		sb.append("<dataset seriesName='IP' color='2AD62A' anchorBorderColor='2AD62A' anchorBgColor='2AD62A'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("ip"))
					.append("'/>");
		}
		sb.append("</dataset>");

		sb.append("</graph>");
		String dataXML = sb.toString();
		dataXML = dataXML.replaceAll("Y_MIN", minValue + "");
		request.setAttribute("dataXML", dataXML);
		// request.setAttribute("list", all);
		// request.setAttribute("list", listsum);

		// 按省份统计 饼图
		@SuppressWarnings("unused")
		int minValuepie = -1;
		List listpie = asdao.findPieData(sdf2.format(new Date()), users,
				request.getParameter("qryuserGroup"));
		@SuppressWarnings("unused")
		int piebro = 0, piebroT = 0, piepv = 0, pieuv = 0, pieip = 0;
		String piepro = null;
		StringBuffer sbpie = new StringBuffer(
				"<graph showNames='1'  decimalPrecision='0'>");
		if (listpie != null && !"".equals(listpie)) {
			Iterator results = listpie.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				@SuppressWarnings("unused")
				HashMap<String, Object> hash = new HashMap<String, Object>();
				piebro = (Integer) rows[0];
				piebroT = (Integer) rows[1];
				piepv = (Integer) rows[2];
				pieuv = (Integer) rows[3];
				pieip = (Integer) rows[4];
				piepro = (String) rows[5];

				sbpie.append("<set name='").append(piepro).append("' value='")
						.append(piepv).append("' /> ");
			}
		}
		sbpie.append("</graph>");
		String dataXMLpie = sbpie.toString();
		request.setAttribute("dataXMLpie", dataXMLpie);
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("main");
	}

	/**
	 * 数据来源——详细情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public ActionForward sourceDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Users users = (Users) request.getSession().getAttribute("user");
		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraWebmaster = request.getParameter("webmaster");
		if (paraWebmaster != null && !"".equals(paraWebmaster)) {
			paraWebmaster = URLDecoder.decode(paraWebmaster, ENCODED);
		}
		String paraUrl = request.getParameter("url");
		if (paraUrl != null && !"".equals(paraUrl)) {
			paraUrl = URLDecoder.decode(paraUrl, ENCODED);
		}
		String paraAdPlanCycleId = request.getParameter("adPlanCycleId");
		String paraAdPlanId = request.getParameter("adPlanId");
		String paraUserid = request.getParameter("userid");

		if ("".equals(addTimef) || addTimef == null) {
			addTimef = now;
		}
		if ("".equals(addTimet) || addTimet == null) {
			addTimet = now;
		}
		int pageNum = 0;// 页次
		int numPerPage = 20;// 每页显示条数
		if ("pager".equals(request.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}

		// 数据来源列表
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, asdao.findSourceCount(addTimef, addTimet,
						paraWebmaster, paraUrl, paraAdPlanCycleId,
						paraAdPlanId, paraUserid, users,
						request.getParameter("qryuserGroup")));
		List slist = asdao.findSourceList(pageMap.get("first"),
				pageMap.get("size"), addTimef, addTimet, paraWebmaster,
				paraUrl, paraAdPlanCycleId, paraAdPlanId, paraUserid, users,
				request.getParameter("qryuserGroup")); // 查询
		List<HashMap<String, Object>> sourcelist = new ArrayList<HashMap<String, Object>>();
		if (slist != null && !"".equals(slist)) {
			Iterator results = slist.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				HashMap<String, Object> hash = new HashMap<String, Object>();
				String cycn = (String) rows[0];
				String apn = (String) rows[1];
				String url = (String) rows[2];
				String wmn = (String) rows[3];
				String usern = (String) rows[4];
				String source = (String) rows[5];
				Date addtime = (Date) rows[6];
				hash.put("cycn", cycn);// 广告计划周期
				hash.put("apn", apn); // 广告计划
				hash.put("url", url);
				hash.put("wmn", wmn); // 站长
				hash.put("usern", usern);// 媒介

				hash.put("source", URLDecoder.decode(source)); // 域名
				hash.put("addt", addtime);
				sourcelist.add(hash);
			}
		}
		// request.setAttribute("sourcelist", sourcelist);
		if (sourcelist != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap,
					"sourcelist", sourcelist));
		}
		request.setAttribute("userlist",
				asdao.getUsersList(users, request.getParameter("qryuserGroup")));
		request.setAttribute(
				"adPlanCyclelist",
				asdao.getAdPlanCycleList(users,
						request.getParameter("qryuserGroup")));
		request.setAttribute("adPlanlist", asdao.getAdPlanList(users,
				request.getParameter("qryuserGroup")));

		request.setAttribute("addTimef", addTimef);
		request.setAttribute("addTimet", addTimet);
		request.setAttribute("webmaster", paraWebmaster);
		request.setAttribute("url", paraUrl);
		request.setAttribute("adPlanCycleId", paraAdPlanCycleId);
		request.setAttribute("adPlanId", paraAdPlanId);
		request.setAttribute("userid", paraUserid);
		// int cnt = 0;
		// if (!"".equals(sourcelist) && sourcelist != null) {
		// cnt = sourcelist.size();
		// }
		// 设置条数
		// request.setAttribute("totalCount", cnt);
		// request.setAttribute("totalCount", pageMap.get("rows"));
		// request.setAttribute("numPerPage", pageMap.get("first"));//每页显示条数
		// request.setAttribute("pageNum", pageMap.get("size"));
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("source");
	}

	/**
	 * 数据来源报表导出——详细情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward sourceDetailReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "来源报表";
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		// URLDecoder.decode(fileName ,"utf-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName + ".xls");// 设定输出文件头
		response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

		List<String> headerList = new ArrayList<String>();
		headerList.add("广告计划");
		headerList.add("计划周期");
		headerList.add("站长");
		headerList.add("域名");
		headerList.add("广告位");
		headerList.add("创意ID");
		headerList.add("创意名称");
		headerList.add("创意URL");
		headerList.add("来源地址");
		headerList.add("媒介");
		headerList.add("日期");

		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraWebmaster = request.getParameter("webmaster");
		if (paraWebmaster != null && !"".equals(paraWebmaster)) {
			paraWebmaster = URLDecoder.decode(paraWebmaster, ENCODED);
		}
		String paraUrl = request.getParameter("url");
		if (paraUrl != null && !"".equals(paraUrl)) {
			paraUrl = URLDecoder.decode(paraUrl, ENCODED);
		}
		String paraAdPlanCycleId = request.getParameter("adPlanCycleId");
		String paraAdPlanId = request.getParameter("adPlanId");
		String paraUserid = request.getParameter("userid");

		if ("".equals(addTimef) || addTimef == null) {
			addTimef = now;
		}
		if ("".equals(addTimet) || addTimet == null) {
			addTimet = now;
		}
		Users users = (Users) request.getSession().getAttribute("user");
		List slist = asdao.findSourceReportList(addTimef, addTimet,
				paraWebmaster, paraUrl, paraAdPlanCycleId, paraAdPlanId,
				paraUserid, users); // 查询

		List<Object[]> datas = new ArrayList<Object[]>();
		DecimalFormat df = new DecimalFormat("##,###.###");
		if (slist != null && !"".equals(slist)) {
			Iterator results = slist.iterator();
			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				String cycn = String.valueOf(rows[0]);//
				String apn = String.valueOf(rows[1]);
				String url = String.valueOf(rows[2]);
				String wmn = String.valueOf(rows[3]);
				String usern = String.valueOf(rows[4]);
				String source = String.valueOf(rows[5]);
				Date addtime = (Date) rows[6];

				// String cycleId = String.valueOf(rows[7]);
				// String adPlanId = String.valueOf(rows[8]);
				// String userId = String.valueOf(rows[9]);
				// String rbId = String.valueOf(rows[10]);
				String adboxName = String.valueOf(rows[11]);
				String acreativeId = String.valueOf(rows[12]);
				String acreativeName = String.valueOf(rows[13]);
				String acreativeUrl = String.valueOf(rows[14]);
				Object[] object = new Object[11];
				object[0] = apn;
				object[1] = cycn;
				object[2] = wmn;
				object[3] = url;
				object[4] = adboxName;
				object[5] = acreativeId;
				object[6] = acreativeName;
				object[7] = URLDecoder.decode(acreativeUrl, "utf-8");
				object[8] = URLDecoder.decode(source, "utf-8");
				object[9] = usern;
				// object[5]=source;
				object[10] = DatetimeHandle.formatDate(addtime,
						DatetimeHandle.SHORT_TIME_FORMAT_STRING);
				datas.add(object);
			}
		}
		ExcelUtil.export(datas, headerList, fileName, os);
		return mapping.findForward("source");
	}

	/**
	 * 总量——详细情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public ActionForward allDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");

		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraWebmaster = request.getParameter("webmaster");
		if (paraWebmaster != null && !"".equals(paraWebmaster)) {
			paraWebmaster = URLDecoder.decode(paraWebmaster, ENCODED);
		}
		String paraUrl = request.getParameter("url");
		if (paraUrl != null && !"".equals(paraUrl)) {
			paraUrl = URLDecoder.decode(paraUrl, ENCODED);
		}
		String paraAdPlanCycleId = request.getParameter("adPlanCycleId");
		String paraAdPlanId = request.getParameter("adPlanId");
		String paraUserid = request.getParameter("userid");
		String paraReadyBoxId = request.getParameter("readyBoxId");

		if ("".equals(addTimef) || addTimef == null) {
			addTimef = now;
		}
		if ("".equals(addTimet) || addTimet == null) {
			addTimet = now;
		}
		int pageNum = 0;// 页次
		int numPerPage = 50;// 每页显示条数
		int index = 0;// 当前记录索引
		if ("pager".equals(request.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			index = (pageNum - 1) * numPerPage;
		}
		int cnt = 0;
		// 汇总显示数据总量
		List listsum = null;
		Users users = (Users) request.getSession().getAttribute("user");
		if (addTimef.equals(addTimet)) {
			// 某一天数据
			listsum = asdao.findSumAdBoxC(true, index, numPerPage,
					sdf2.format(sdfn.parse(addTimef)), paraWebmaster, paraUrl,
					paraAdPlanCycleId, paraAdPlanId, paraUserid, users,
					request.getParameter("qryuserGroup"));
			cnt = asdao.findSumAdBoxCCount(sdf2.format(sdfn.parse(addTimef)),
					paraWebmaster, paraUrl, paraAdPlanCycleId, paraAdPlanId,
					paraUserid, users, request.getParameter("qryuserGroup"));

		} else {

			listsum = asdao.findDetailAll(index, numPerPage, addTimef,
					addTimet, paraWebmaster, paraUrl, paraAdPlanCycleId,
					paraAdPlanId, paraUserid, paraReadyBoxId, users,
					request.getParameter("qryuserGroup"));
			cnt = asdao
					.findDetailAllCount(addTimef, addTimet, paraWebmaster,
							paraUrl, paraAdPlanCycleId, paraAdPlanId,
							paraUserid, paraReadyBoxId, users,
							request.getParameter("qryuserGroup"));
		}
		// int cnt = asdao.findDetailAllCount(addTimef, addTimet,
		// paraWebmaster, paraUrl, paraAdPlanCycleId, paraAdPlanId, paraUserid);

		// if (!"".equals(listsum) && listsum != null) {
		// cnt = listsum.size();
		// }

		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		StringBuffer sb = new StringBuffer("<graph caption='")
				.append("日期分析' subcaption='");
		sb.append(" ' numdivlines='9' lineThickness='2' showValues='0' anchorRadius='3' anchorBgAlpha='50' showAlternateVGridColor='1' ");
		sb.append(" numVisiblePlot='12' animation='0'>");

		int minValue = -1;
		sb.append("<categories >");
		// 曲线图，当天
		@SuppressWarnings("unused")
		int bro = 0, broT = 0, pv = 0, uv = 0, ip = 0;
		if (listsum != null && !"".equals(listsum)) {
			Iterator results = listsum.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				HashMap<String, Object> hash = new HashMap<String, Object>();
				bro = (Integer) rows[0];
				broT = (Integer) rows[1];
				pv = (Integer) rows[2];
				uv = (Integer) rows[3];
				ip = (Integer) rows[4];
				if (!addTimef.equals(addTimet)) {
					Date strDate = (Date) rows[5];
					sb.append("<category name='").append(sdfn.format(strDate))
							.append("' />");
					hash.put("mtdate", sdfn.format(strDate));

				} else {
					// 某一天数据
					int strDate = (Integer) rows[5];
					sb.append("<category name='").append(strDate)
							.append(":00' />");
					hash.put("mtdate", strDate);
				}

				hash.put("pv", pv);
				hash.put("uv", uv);
				hash.put("ip", ip);
				all.add(hash);
			}
		}
		sb.append("</categories >");

		// ========= pv ==========
		sb.append("<dataset seriesName='PV' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("pv"))
					.append("'/>");
		}
		sb.append("</dataset>");

		// ========= uv ===========
		sb.append("<dataset seriesName='UV' color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("uv"))
					.append("'/>");
		}
		sb.append("</dataset>");

		// ======== ip ============
		sb.append("<dataset seriesName='IP' color='2AD62A' anchorBorderColor='2AD62A' anchorBgColor='2AD62A'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("ip"))
					.append("'/>");
		}
		sb.append("</dataset>");

		sb.append("</graph>");
		String dataXML = sb.toString();
		dataXML = dataXML.replaceAll("Y_MIN", minValue + "");
		request.setAttribute("dataXML", dataXML);

		@SuppressWarnings("unused")
		List<HashMap<String, Object>> sourcelist = new ArrayList<HashMap<String, Object>>();
		// if (slist != null && !"".equals(slist)) {
		// Iterator results = slist.iterator();
		//
		// while (results.hasNext()) {
		// Object[] rows = (Object[]) results.next();
		// HashMap<String, Object> hash = new HashMap<String, Object>();
		// String cycn = (String)rows[0];
		// String apn = (String)rows[1];
		// String url = (String)rows[2];
		// String wmn = (String)rows[3];
		// String usern = (String)rows[4];
		// String source = (String)rows[5];
		// Date addtime = (Date)rows[6];
		// hash.put("cycn", cycn);// 广告计划周期
		// hash.put("apn", apn); // 广告计划
		// hash.put("url", url); // 域名
		// hash.put("wmn", wmn); // 站长
		// hash.put("usern", usern);// 媒介
		// hash.put("source", source);
		// hash.put("addt", addtime);
		// sourcelist.add(hash);
		// }
		// }
		request.setAttribute("list", listsum);
		request.setAttribute("userlist",
				asdao.getUsersList(users, request.getParameter("qryuserGroup")));
		request.setAttribute(
				"adPlanCyclelist",
				asdao.getAdPlanCycleList(users,
						request.getParameter("qryuserGroup")));
		request.setAttribute("adPlanlist", asdao.getAdPlanList(users,
				request.getParameter("qryuserGroup")));

		request.setAttribute("addTimef", addTimef);
		request.setAttribute("addTimet", addTimet);
		request.setAttribute("webmaster", paraWebmaster);
		request.setAttribute("url", paraUrl);
		request.setAttribute("adPlanCycleId", paraAdPlanCycleId);
		request.setAttribute("adPlanId", paraAdPlanId);
		request.setAttribute("userid", paraUserid);

		// 设置条数
		request.setAttribute("totalCount", cnt);
		request.setAttribute("numPerPage", numPerPage);// 每页显示条数
		request.setAttribute("pageNum", request.getParameter("pageNum"));

		// request.setAttribute("userlist",
		// asdao.getUsersList(users,request.getParameter("qryuserGroup")));
		// request.setAttribute("adPlanCyclelist",
		// asdao.getAdPlanCycleList(users,request.getParameter("qryuserGroup")));
		// request.setAttribute("adPlanlist",
		// asdao.getAdPlanList(users,request.getParameter("qryuserGroup")));
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("all");
	}

	/**
	 * 饼图——详细情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "deprecation", "unchecked" })
	public ActionForward pieDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");

		int pageNum = 0;// 页次
		int numPerPage = 20;// 每页显示条数
		int index = 0;// 当前记录索引
		if ("pager".equals(request.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			index = (pageNum - 1) * numPerPage;
		}

		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraWebmaster = request.getParameter("webmaster");
		if (paraWebmaster != null && !"".equals(paraWebmaster)) {
			paraWebmaster = URLDecoder.decode(paraWebmaster, ENCODED);
			// paraWebmaster= new String(paraWebmaster.getBytes(DECODE),
			// ENCODED);
		}
		String paraUrl = request.getParameter("url");
		if (paraUrl != null && !"".equals(paraUrl)) {
			paraUrl = URLDecoder.decode(paraUrl, ENCODED);
			// paraUrl=new String(paraUrl.getBytes(DECODE), ENCODED);
		}
		String paraAdPlanCycleId = request.getParameter("adPlanCycleId");
		String paraAdPlanId = request.getParameter("adPlanId");
		String paraUserid = request.getParameter("userid");

		if ("".equals(addTimef) || addTimef == null) {
			addTimef = sdfn.format(new Date());
		}
		if ("".equals(addTimet) || addTimet == null) {
			addTimet = sdfn.format(new Date());
		}
		int interval = this.getBetweenDays(addTimef, addTimet);

		// 按省份统计 饼图
		@SuppressWarnings("unused")
		int minValuepie = -1;
		List listpie = null;
		@SuppressWarnings("unused")
		String tab = null;
		// List alllist = new ArrayList();
		@SuppressWarnings("unused")
		Date afterTime = null;
		// if (addTimef.equals(addTimet)) {
		// // 显示当天数据
		// listpie = asdao.findPieData(sdf2.format(new Date()));
		//
		// } else {
		// 一段时间
		Users users = (Users) request.getSession().getAttribute("user");
		listpie = asdao.findPieDetailData(addTimef, addTimet, interval, index,
				numPerPage, paraWebmaster, paraUrl, paraAdPlanCycleId,
				paraAdPlanId, paraUserid, users,
				request.getParameter("qryuserGroup"));
		// }

		request.setAttribute("list", listpie);

		@SuppressWarnings("unused")
		int piebro = 0, piebroT = 0, piepv = 0, pieuv = 0, pieip = 0;
		String piepro = null;
		StringBuffer sbpie = new StringBuffer(
				"<graph showNames='1'  decimalPrecision='0'>");
		if (listpie != null && !"".equals(listpie)) {
			Iterator results = listpie.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				@SuppressWarnings("unused")
				HashMap<String, Object> hash = new HashMap<String, Object>();
				piebro = (Integer) rows[0];
				piebroT = (Integer) rows[1];
				piepv = (Integer) rows[2];
				pieuv = (Integer) rows[3];
				pieip = (Integer) rows[4];
				piepro = (String) rows[5];
				if (piepro == null) {
					piepro = "其他";
				}

				sbpie.append("<set name='").append(piepro).append("' value='")
						.append(piepv).append("' /> ");
			}
		}
		sbpie.append("</graph>");
		String dataXMLp = sbpie.toString();
		request.setAttribute("dataXMLp", dataXMLp);

		request.setAttribute("addTimef", addTimef);
		request.setAttribute("addTimet", addTimet);
		request.setAttribute("webmaster", paraWebmaster);
		request.setAttribute("url", paraUrl);
		request.setAttribute("adPlanCycleId", paraAdPlanCycleId);
		request.setAttribute("adPlanId", paraAdPlanId);
		request.setAttribute("userid", paraUserid);

		int cnt = 0;
		cnt = asdao.findPieDetailDataCount(addTimef, addTimet, interval, index,
				numPerPage, paraWebmaster, paraUrl, paraAdPlanCycleId,
				paraAdPlanId, paraUserid, users,
				request.getParameter("qryuserGroup"));
		// if (!"".equals(listpie) && listpie != null) {
		// cnt = listpie.size();
		// }
		// 设置条数
		request.setAttribute("totalCount", cnt);
		request.setAttribute("numPerPage", numPerPage);// 每页显示条数
		request.setAttribute("pageNum", request.getParameter("pageNum"));

		request.setAttribute("userlist",
				asdao.getUsersList(users, request.getParameter("qryuserGroup")));
		request.setAttribute(
				"adPlanCyclelist",
				asdao.getAdPlanCycleList(users,
						request.getParameter("qryuserGroup")));
		request.setAttribute("adPlanlist", asdao.getAdPlanList(users,
				request.getParameter("qryuserGroup")));
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("pie");
	}

	/**
	 * 来路域名--饼图——详细情况
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "deprecation", "unchecked" })
	public ActionForward pieDomainDetailAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");

		int pageNum = 0;// 页次
		int numPerPage = 20;// 每页显示条数
		int index = 0;// 当前记录索引
		if ("pager".equals(request.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			index = (pageNum - 1) * numPerPage;
		}

		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraWebmaster = request.getParameter("webmaster");
		if (paraWebmaster != null && !"".equals(paraWebmaster)) {
			paraWebmaster = URLDecoder.decode(paraWebmaster, ENCODED);
			// paraWebmaster= new String(paraWebmaster.getBytes(DECODE),
			// ENCODED);
		}
		String paraUrl = request.getParameter("url");
		if (paraUrl != null && !"".equals(paraUrl)) {
			paraUrl = URLDecoder.decode(paraUrl, ENCODED);
			// paraUrl=new String(paraUrl.getBytes(DECODE), ENCODED);
		}
		String paraDomain = request.getParameter("domain");
		if ("".equals(paraDomain) || paraDomain == null) {
			paraDomain = "";
		} else {
			paraDomain = paraDomain.trim();
		}
		String paraIsAccept = request.getParameter("isaccept");
		int pIsAccept = -1;
		if (!"".equals(paraIsAccept) && paraIsAccept != null) {
			pIsAccept = Integer.parseInt(paraIsAccept);
		}

		if ("".equals(addTimef) || addTimef == null) {
			addTimef = sdfn.format(new Date());
		}
		if ("".equals(addTimet) || addTimet == null) {
			addTimet = sdfn.format(new Date());
		}
		int interval = this.getBetweenDays(addTimef, addTimet);
		Users users = (Users) request.getSession().getAttribute("user");
		// 按域名统计 饼图
		@SuppressWarnings("unused")
		int minValuepie = -1;
		List listpie = null;
		@SuppressWarnings("unused")
		String tab = null;
		// List alllist = new ArrayList();
		@SuppressWarnings("unused")
		Date afterTime = null;
		// if (addTimef.equals(addTimet)) {
		// // 显示当天数据
		List listpieByHour = asdao.findPieDomainByHour(addTimef, addTimet,
				interval, index, numPerPage, pIsAccept, paraDomain, false,
				users, request.getParameter("qryuserGroup"));
		//
		// } else {
		// 一段时间
		listpie = asdao.findPieDomainData(addTimef, addTimet, interval, index,
				numPerPage, pIsAccept, paraDomain, true, users,
				request.getParameter("qryuserGroup"));
		// }

		// if (!"".equals(paraDomain) && paraDomain != null) {
		// if (addTimef.equals(addTimet)) {
		List<HashMap<String, Object>> all = new ArrayList<HashMap<String, Object>>();
		StringBuffer sb = new StringBuffer("<graph caption='")
				.append("来路域名小时分析' subcaption='");
		sb.append(" ' numdivlines='9' lineThickness='2' showValues='0' anchorRadius='3' anchorBgAlpha='50' showAlternateVGridColor='1' ");
		sb.append(" numVisiblePlot='12' animation='0'>");

		int minValue = -1;
		sb.append("<categories >");
		// 曲线图，当天
		@SuppressWarnings("unused")
		int hour = 0, broT = 0, pv = 0, uv = 0, ip = 0;

		if (listpieByHour != null && !"".equals(listpieByHour)) {
			Iterator results = listpieByHour.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				HashMap<String, Object> hash = new HashMap<String, Object>();
				// broT = (Integer) rows[1];
				// pv = (Integer) rows[2];
				uv = (Integer) rows[2];
				// ip = (Integer) rows[4];
				if (!addTimef.equals(addTimet)) {
					String strDate = (String) rows[0];
					sb.append("<category name='").append(strDate)
							.append("' />");
					hash.put("mtdate", strDate);

				} else {
					// 某一天数据
					hour = (Integer) rows[0];
					sb.append("<category name='").append(hour)
							.append(":00' />");
					hash.put("mtdate", hour);
				}

				hash.put("pv", pv);
				hash.put("uv", uv);
				hash.put("ip", ip);
				all.add(hash);
			}
		}
		sb.append("</categories >");

		// ========= uv ===========
		sb.append("<dataset seriesName='")
				.append(paraDomain + " UV'")
				.append(" color='F1683C' anchorBorderColor='F1683C' anchorBgColor='F1683C'>");
		for (int i = 0; i < all.size(); i++) {
			sb.append("<set value='").append(all.get(i).get("uv"))
					.append("'/>");
		}
		sb.append("</dataset>");

		sb.append("</graph>");
		String dataXML = sb.toString();
		dataXML = dataXML.replaceAll("Y_MIN", minValue + "");
		request.setAttribute("dataXMLDomainLine", dataXML);

		// }
		@SuppressWarnings("unused")
		int piebroT = 0, piepv = 0, pieuv = 0, pieip = 0;
		String domain = null;
		String piepro = null;
		StringBuffer sbpie = new StringBuffer(
				"<graph showNames='1'  decimalPrecision='0'>");
		if (listpie != null && !"".equals(listpie)) {
			Iterator results = listpie.iterator();

			while (results.hasNext()) {
				Object[] rows = (Object[]) results.next();
				@SuppressWarnings("unused")
				HashMap<String, Object> hash = new HashMap<String, Object>();
				domain = (String) rows[0];
				// piebroT = (Integer) rows[1];
				piepv = (Integer) rows[1];
				pieuv = (Integer) rows[2];
				pieip = (Integer) rows[3];
				// piepro = (String)rows[5];
				if (domain == null || "".equals(domain)) {
					domain = "其他";
				}

				sbpie.append("<set name='").append(domain).append("' value='")
						.append(pieuv).append("' /> ");
			}
		}
		sbpie.append("</graph>");

		String dataXMLp = sbpie.toString();
		request.setAttribute("dataXMLp", dataXMLp);

		request.setAttribute("list", listpie);

		request.setAttribute("addTimef", addTimef);
		request.setAttribute("addTimet", addTimet);
		request.setAttribute("webmaster", paraWebmaster);
		request.setAttribute("url", paraUrl);
		request.setAttribute("domain", paraDomain);
		request.setAttribute("isaccept", pIsAccept);
		// request.setAttribute("adPlanCycleId", paraAdPlanCycleId);
		// request.setAttribute("adPlanId", paraAdPlanId);
		// request.setAttribute("userid", paraUserid);

		int cnt = 0;
		List cntlist = asdao.findPieDomainData(addTimef, addTimet, interval,
				index, numPerPage, pIsAccept, paraDomain, false, users,
				request.getParameter("qryuserGroup"));
		if (!"".equals(cntlist) && cntlist != null) {
			cnt = cntlist.size();
		}
		// 设置条数
		request.setAttribute("totalCount", cnt);
		request.setAttribute("numPerPage", numPerPage);// 每页显示条数
		request.setAttribute("pageNum", request.getParameter("pageNum"));

		request.setAttribute("userlist",
				asdao.getUsersList(users, request.getParameter("qryuserGroup")));
		request.setAttribute(
				"adPlanCyclelist",
				asdao.getAdPlanCycleList(users,
						request.getParameter("qryuserGroup")));
		request.setAttribute("adPlanlist", asdao.getAdPlanList(users,
				request.getParameter("qryuserGroup")));
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("pied");
	}

	/**
	 * 导出Excel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downLoadExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String addTimef = request.getParameter("addTimef");
		String addTimet = request.getParameter("addTimet");
		String paraIsAccept = request.getParameter("isaccept");
		int pIsAccept = -1;
		if (!"".equals(paraIsAccept) && paraIsAccept != null) {
			pIsAccept = Integer.parseInt(paraIsAccept);
		}
		String paraDomain = request.getParameter("paraDomain");

		try {
			Excel excel = new Excel();
			// 生成EXCEL的SHEET页

			WritableSheet sheet = excel.getSheet("Domain", response, "Domain"
					+ sdfn.format(new Date()));

			int intFontSize = 10; // 字体大小
			int intFontColor = 3; // 字体颜色，黑色
			int intCellBkColor = 62; // 背景颜色，白色
			int intCellBkColorTitle = 63; // 背景颜色，白色
			int black = 3; // 黑色
			int green = 51; // 绿色
			// 设定单元格属性
			WritableCellFormat cellFormat = excel.setCellStyle(intFontSize,
					intFontColor, intCellBkColor, false, true, "left");
			// 设置标题属性
			WritableCellFormat cellFormatTitle = excel.setCellStyle(
					intFontSize, intFontColor, intCellBkColorTitle, false,
					true, "left");
			// 设定单元格属性(货币格式)
			WritableCellFormat cellFormatPrice = excel.setCellStylePrice(
					intFontSize, intFontColor, intCellBkColor, false, true,
					"left");

			// 设置TItle
			excel.outExecute(sheet, 0, 0, "日 期", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 1, "小 时", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 2, "域 名", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 3, "PV", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 4, "UV", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 5, "IP", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 6, "省 份", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 7, "城 市", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 8, "周 期", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 9, "计 划", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 10, "站 长", 0, cellFormatTitle);
			excel.outExecute(sheet, 0, 11, "创 意", 0, cellFormatTitle);

			// 设置excel列宽
			sheet.setColumnView(0, 12);// 日期
			sheet.setColumnView(1, 10);// 小时
			sheet.setColumnView(2, 15);// 域名
			sheet.setColumnView(3, 10);// PV
			sheet.setColumnView(4, 10);// UV
			sheet.setColumnView(5, 10);// IP
			sheet.setColumnView(6, 10);// 省份
			sheet.setColumnView(7, 10);// 城市
			sheet.setColumnView(8, 15);// 周期
			sheet.setColumnView(9, 20);// 计划
			sheet.setColumnView(10, 15);// 站长
			sheet.setColumnView(11, 15);// 创意
			// 查询
			// List<CusSer> cusser = (List<CusSer>) cdao.getCusSer(beg, end,
			// sid, cpid, mobile, state, CusSer.class,0,0, "excel");
			int interval = this.getBetweenDays(addTimef, addTimet);
			Users users = (Users) request.getSession().getAttribute("user");
			// 一段时间
			List listpie = asdao.findDomainExcelData(addTimef, addTimet,
					interval, pIsAccept, paraDomain, users,
					request.getParameter("qryuserGroup"));
			int s = 0;
			// 设置excel输出内容
			if (listpie.size() > 0) {
				Iterator ite = (Iterator) listpie.iterator();
				while (ite.hasNext()) {
					Object[] obj = (Object[]) ite.next();
					java.sql.Timestamp countTime = (java.sql.Timestamp) obj[0];
					int hour = (Integer) obj[1];
					String domain = (String) obj[2];
					int pv = (Integer) obj[3];
					int uv = (Integer) obj[4];
					int ip = (Integer) obj[5];
					String pro = (String) obj[6];
					String city = (String) obj[7];
					String adplancycle = (String) obj[8];
					String plan = (String) obj[9];
					String webmaster = (String) obj[10];
					String adcreative = (String) obj[11];

					// 输出“时间”
					excel.outExecute(sheet, s + 1, 0, String.valueOf(countTime)
							.substring(0, 10), 0, cellFormat);
					excel.outExecute(sheet, s + 1, 1, String.valueOf(hour), 1,
							cellFormat);
					excel.outExecute(sheet, s + 1, 2, domain, 0, cellFormat);
					excel.outExecute(sheet, s + 1, 3, String.valueOf(pv), 1,
							cellFormat);
					excel.outExecute(sheet, s + 1, 4, String.valueOf(uv), 1,
							cellFormat);
					excel.outExecute(sheet, s + 1, 5, String.valueOf(ip), 1,
							cellFormat);
					excel.outExecute(sheet, s + 1, 6, pro, 0, cellFormat);
					excel.outExecute(sheet, s + 1, 7, city, 0, cellFormat);
					excel.outExecute(sheet, s + 1, 8, adplancycle, 0,
							cellFormat);
					excel.outExecute(sheet, s + 1, 9, plan, 0, cellFormat);
					excel.outExecute(sheet, s + 1, 10, webmaster, 0, cellFormat);
					excel.outExecute(sheet, s + 1, 11, adcreative, 0,
							cellFormat);
					s++;
				}

			}
			// 保存文件
			excel.saveWorkBook();
			// 关闭文件
			excel.closeWorkBook();
			System.out.println("文件写入完毕，请到相关目录查看");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		request.setAttribute("current", request.getParameter("current"));
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		request.setAttribute("everyCount", request.getParameter("everyCount"));
		request.setAttribute("qryuserGroup",
				request.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
		return mapping.findForward("list");
	}

	// =======
	// >>>>>>> .r5171
	/**
	 * 取得两个时间段的时间间隔 return t2 与t1的间隔天数 throws ParseException
	 * 如果输入的日期格式不是0000-00-00 格式抛出异常
	 */
	public static int getBetweenDays(String t1, String t2) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(t1);
			d2 = format.parse(t2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}

}
