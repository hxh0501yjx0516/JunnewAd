package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.Collections;
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
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.ReadyBoxUtil;
import com.pancou.ad.vo.AdBox;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Chinaarea;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;

@Entity
public class ReadyBoxAction extends BaseDispatchAction {

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查询投放列表
		String sql = "select r.*,ap.adPlanName adPlanName,ac.adCreativeName adCreativeName,ab.adBoxName adBoxName,abi.adBoxInfoName adBoxInfoName,ac.isDefault isDefault,apc.adPlanCycleName adPlanCycleName "
				+ "from ReadyBox r "
				+ "left join AdPlan ap on r.adPlanId = ap.adPlanId "
				+ "left join AdPlanCycle apc on r.adPlanCycleId = apc.adPlanCycleId "
				+ "left join AdCreative ac on r.adCreativeId = ac.adCreativeId "
				+ "left join AdBox ab on r.adBoxId = ab.adBoxId "
				+ "left join AdBoxInfo abi on ab.adBoxInfoId = abi.adBoxInfoId where ";
		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = "";
		condition += " r.adBoxId =:adBoxId";
		parms.put("adBoxId", Integer.parseInt(this.getParameter("adBoxId")));
		List<Object> cols = new ArrayList<Object>();
		cols.add(ReadyBox.class);
		cols.add("adPlanName");
		cols.add("adCreativeName");
		cols.add("adBoxName");
		cols.add("adBoxInfoName");
		cols.add("isDefault");
		cols.add("adPlanCycleName");
		List qryList = dao
				.findBySql(
						sql
								+ condition
								+ " order by ac.isDefault,r.readyBoxStatus asc,r.adCreativeLevel desc",
						parms, cols);
		List<ReadyBox> list = new ArrayList<ReadyBox>();
		String getCodeFlag = "";
		if (qryList.size() > 0) {
			for (int i = 0; i < qryList.size(); i++) {
				Object[] object = (Object[]) qryList.get(i);
				ReadyBox readyBox = (ReadyBox) object[0];
				readyBox.setAdPlanName(object[1] + "");
				readyBox.setAdCreativeName(object[2] + "");
				readyBox.setAdBoxName(object[3] + "");
				readyBox.setAdBoxInfoName(object[4] + "");
				readyBox.setIsDefault(Integer.parseInt(object[5] + ""));
				readyBox.setAdPlanCycleName(object[6] + "");
				if (Integer.parseInt(object[5] + "") == 1) {// 默认创意
					request.setAttribute("defaultFlag",
							readyBox.getAdCreativeId());
				}
				if (readyBox.getAreaFix() == null
						|| "".equals(readyBox.getAreaFix())) {
					getCodeFlag = "isNull";
				}
				list.add(readyBox);
			}
		} else {
			getCodeFlag = "isNull";
		}
		// 查询未投放列表，包括正常创意和默认创意
		String sqlCreative = "select ac.*,ap.adPlanName adPlanName,abi.adBoxInfoName adBoxInfoName,ads.adWidth adWidth,ads.adHeight adHeight,apc.adPlanCycleName adPlanCycleName  "
				+ "from AdCreative ac "
				+ "left join AdPlanCycle apc on ac.adPlanCycleId = apc.adPlanCycleId "
				+ "left join AdPlan ap on apc.adPlanId = ap.adPlanId "
				+ "left join ReadyPlan rp on ap.adPlanId = rp.adPlanId "
				+ "left join AdBox ab on ab.webmasterId = rp.webmasterId "
				+ "left join AdBoxInfo abi on ab.adBoxInfoId = abi.adBoxInfoId "
				+ "left join AdSize ads on ads.adSizeId = abi.adSizeId "
				+ "where ab.adBoxId =:adBoxId and  abi.adSizeId = ac.adSizeId and rp.readyPlanStatus = 0 and "
				+ "ap.adPlanStatus = 0 and apc.adPlanCycleStatus = 0 and ac.adCreativeStatus = 0 and ac.isDefault <>2 "
				+ "and ac.adCreativeId not in(select adCreativeId from ReadyBox rb where rb.adBoxId = ab.adBoxId and ac.isDefault = 0)";
		List<Object> cols1 = new ArrayList<Object>();
		cols1.add(AdCreative.class);
		cols1.add("adPlanName");
		cols1.add("adBoxInfoName");
		cols1.add("adWidth");
		cols1.add("adHeight");
		cols1.add("adPlanCycleName");
		List creativeList = dao.findBySql(sqlCreative
				+ " order by ac.isDefault desc, ac.adCreativeId desc", parms,
				cols1);
		List<AdCreative> adCreativeList = new ArrayList<AdCreative>();
		for (int i = 0; i < creativeList.size(); i++) {
			Object[] object = (Object[]) creativeList.get(i);
			AdCreative adCreative = (AdCreative) object[0];
			adCreative.setAdPlanName(object[1] + "");
			adCreative.setAdBoxInfoName(object[2] + "");
			adCreative.setAdSizeName(object[3] + "x" + object[4]);
			adCreative.setAdPlanCycleName(object[5] + "");
			adCreativeList.add(adCreative);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("readyBoxList", list);
		result.put("adCreativeList", adCreativeList);
		request.setAttribute("getCodeFlag", getCodeFlag);
		this.buildPageResult(request, result);
		request.setAttribute("adBoxId", this.getParameter("adBoxId"));
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
		try {
			// 添加
			String adBoxId = this.getParameter("adBoxId");
			String sql = "select ab.isUrlBand isUrlBand,m.modelJs modelJs,ads.adWidth adWidth,ads.adHeight adHeight,w.userId userId,ab.webMasterId webMasterId,ab.urlId urlId  "
					+ "from AdBox ab "
					+ "left join AdBoxInfo abi on ab.adBoxInfoId = abi.adBoxInfoId "
					+ "left join Models m on abi.modelId = m.modelId "
					+ "left join AdSize ads on abi.adSizeId = ads.adSizeId "
					+ "left join WebMaster w on ab.webMasterId = w.webMasterId "
					+ "where ";
			Map<String, Object> parms = new HashMap<String, Object>();
			String condition = " ab.adBoxId = :adBoxId";
			parms.put("adBoxId", adBoxId);
			List<Object> cols = new ArrayList<Object>();
			cols.add("isUrlBand");
			cols.add("modelJs");
			cols.add("adWidth");
			cols.add("adHeight");
			cols.add("userId");
			cols.add("webMasterId");
			cols.add("urlId");
			int isUrlBand = 0;
			String modelJs = "";
			int adWidth = 0;
			int adHeight = 0;
			int userId = 0;
			int webMasterId = 0;
			String urlList = "";
			int urlId = 0;
			// 查询广告位属性（所属媒介、尺寸、模版JS、域名绑定）
			List adBoxList = dao.findBySql(sql + condition, parms, cols);
			if (null != adBoxList && adBoxList.size() > 0) {
				Object[] object = (Object[]) adBoxList.get(0);
				isUrlBand = Integer.parseInt(object[0] + "");
				modelJs = object[1] + "";
				adWidth = Integer.parseInt(object[2] + "");
				adHeight = Integer.parseInt(object[3] + "");
				userId = Integer.parseInt(object[4] + "");
				webMasterId = Integer.parseInt(object[5] + "");
				urlId = Integer.parseInt(object[6] + "");
				if (isUrlBand == 1) {// 绑定域名,查询该网站主有效域名
					String[] pname = new String[2];
					String[] pvalue = new String[2];
					pname[0] = "webMasterId";
					pname[1] = "urlStatus";
					pvalue[0] = webMasterId + "";
					pvalue[1] = "1";
					List<UrlAddress> urlAddressList = dao.findAll(
							UrlAddress.class, pname, pvalue);
					for (int i = 0; i < urlAddressList.size(); i++) {
						UrlAddress urlAddress = urlAddressList.get(i);
						if (i == urlAddressList.size() - 1) {
							urlList += urlAddress.getUrl();
						} else {
							urlList += urlAddress.getUrl() + "|";
						}
					}
				}
			}
			String strUrl = Configure.get("img.url");
			String ids = this.getParameter("ids");
			String defaultId = this.getParameter("defaultIds");
			if (!"".equals(defaultId)) {
				// 添加默认广告创意，判断投放列表中是否已有，如果没有直接添加，如果存在判断是否和已有默认创意相同，如果不同则替换
				String sqlReadyBoxId = "select readyBoxId,rb.adCreativeId from ReadyBox rb left join AdCreative a on rb.adCreativeId = a.adCreativeId where rb.adBoxId = :adBoxId and a.isDefault = 1";
				List<Object[]> readyBoxIds = dao
						.findBySql(sqlReadyBoxId, parms);
				if (readyBoxIds != null) {
					if (readyBoxIds.size() > 0) {
						// 投放列表中存在默认广告创意
						if (!defaultId.equals(readyBoxIds.get(0)[1] + "")) {
							// 添加默认广告创意不是投放列表中的默认创意，更换默认创意
							dao.doSql("delete from ReadyBox where readyBoxId = "
									+ readyBoxIds.get(0)[0]);
							AdCreative adCreative = (AdCreative) dao.findById(
									AdCreative.class,
									Integer.parseInt(defaultId));// 查询默认创意信息
							AdPlanCycle adPlanCycle = (AdPlanCycle) dao
									.findById(AdPlanCycle.class,
											adCreative.getAdPlanCycleId());// 查询周期实例中媒体最低单价
							ReadyBox readyBox = new ReadyBox();
							readyBox.setAdBoxId(Integer.parseInt(this
									.getParameter("adBoxId")));
							readyBox.setAdCreativeId(adCreative
									.getAdCreativeId());
							readyBox.setAdPlanCycleId(adCreative
									.getAdPlanCycleId());
							readyBox.setAdPlanId(adCreative.getAdPlanId());
							readyBox.setUrlId(urlId);
							readyBox.setWebMasterId(webMasterId);
							readyBox.setShowType(2);
							readyBox.setReadyBoxStatus(0);
							readyBox.setAddTime(DatetimeHandle
									.formatCurrentDate());
							readyBox.setAdCreativeUrl(adCreative
									.getAdCreativeUrl());
							if (adCreative.getAdCreativeImg() != null
									&& !"".equals(adCreative.getAdCreativeImg())) {
								readyBox.setAdCreativeImg(strUrl
										+ adCreative.getAdCreativeImg());
							}
							readyBox.setModelJS(modelJs);
							readyBox.setAdWidth(adWidth);
							readyBox.setAdHeight(adHeight);
							readyBox.setUserId(userId);
							readyBox.setIsUrlBand(isUrlBand);
							readyBox.setAdCreativeLevel(0);
							if (isUrlBand == 1) {
								readyBox.setUrlList(urlList);
							}
							readyBox.setWebMasterPrice(adPlanCycle
									.getWebMasterPrice());
							dao.save(readyBox);
							// ReadyBoxUtil.saveToMemCached(readyBox); //add by
							// yu
						}
					} else {
						// 投放列表中不存在默认广告创意，直接添加默认创意
						AdCreative adCreative = (AdCreative) dao.findById(
								AdCreative.class, Integer.parseInt(defaultId));// 查询默认创意信息
						AdPlanCycle adPlanCycle = (AdPlanCycle) dao.findById(
								AdPlanCycle.class,
								adCreative.getAdPlanCycleId());// 查询周期实例中媒体最低单价
						ReadyBox readyBox = new ReadyBox();
						readyBox.setAdBoxId(Integer.parseInt(this
								.getParameter("adBoxId")));
						readyBox.setAdCreativeId(adCreative.getAdCreativeId());
						readyBox.setAdPlanCycleId(adCreative.getAdPlanCycleId());
						readyBox.setAdPlanId(adCreative.getAdPlanId());
						readyBox.setUrlId(urlId);
						readyBox.setWebMasterId(webMasterId);
						readyBox.setShowType(2);
						readyBox.setReadyBoxStatus(0);
						readyBox.setAddTime(DatetimeHandle.formatCurrentDate());
						readyBox.setAdCreativeUrl(adCreative.getAdCreativeUrl());
						if (adCreative.getAdCreativeImg() != null
								&& !"".equals(adCreative.getAdCreativeImg())) {
							readyBox.setAdCreativeImg(strUrl
									+ adCreative.getAdCreativeImg());
						}
						readyBox.setModelJS(modelJs);
						readyBox.setAdWidth(adWidth);
						readyBox.setAdHeight(adHeight);
						readyBox.setUserId(userId);
						readyBox.setIsUrlBand(isUrlBand);
						readyBox.setAdCreativeLevel(0);
						if (isUrlBand == 1) {
							readyBox.setUrlList(urlList);
						}
						readyBox.setWebMasterPrice(adPlanCycle
								.getWebMasterPrice());
						dao.save(readyBox);
						// ReadyBoxUtil.saveToMemCached(readyBox); //add by yu
					}

				}
			}
			if (!"".equals(ids)) {
				// 添加普通广告创意
				String[] adCreativeIds = ids.split(",");
				for (String id : adCreativeIds) {
					AdCreative adCreative = (AdCreative) dao.findById(
							AdCreative.class, Integer.parseInt(id));// 查询创意信息
					AdPlanCycle adPlanCycle = (AdPlanCycle) dao.findById(
							AdPlanCycle.class, adCreative.getAdPlanCycleId());// 查询周期实例中媒体最低单价
					ReadyBox readyBox = new ReadyBox();
					readyBox.setAdBoxId(Integer.parseInt(this
							.getParameter("adBoxId")));
					readyBox.setAdCreativeId(adCreative.getAdCreativeId());
					readyBox.setAdPlanCycleId(adCreative.getAdPlanCycleId());
					readyBox.setAdPlanId(adCreative.getAdPlanId());
					readyBox.setUrlId(urlId);
					readyBox.setShowType(2);
					readyBox.setWebMasterId(webMasterId);
					readyBox.setReadyBoxStatus(1);
					readyBox.setAddTime(DatetimeHandle.formatCurrentDate());
					readyBox.setAdCreativeUrl(adCreative.getAdCreativeUrl());
					if (adCreative.getAdCreativeImg() != null
							&& !"".equals(adCreative.getAdCreativeImg())) {
						readyBox.setAdCreativeImg(strUrl
								+ adCreative.getAdCreativeImg());
					}
					readyBox.setModelJS(modelJs);
					readyBox.setAdWidth(adWidth);
					readyBox.setAdHeight(adHeight);
					readyBox.setUserId(userId);
					readyBox.setIsUrlBand(isUrlBand);
					readyBox.setAdCreativeLevel(99); // 99 2012.12.13
					if (isUrlBand == 1) {
						readyBox.setUrlList(urlList);
					}
					readyBox.setWebMasterPrice(adPlanCycle.getWebMasterPrice());
					dao.save(readyBox);
					// ReadyBoxUtil.saveToMemCached(readyBox);
				}
			}
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！", "",
					"getCode");
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
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
			ReadyBox readyBox = (ReadyBox) dao.findById(ReadyBox.class,
					Integer.parseInt(this.getParameter("readyBoxId")));
			if ("edit".equals(this.getParameter("flag"))) {
				// 修改跳转
				if ("0".equals(this.getParameter("isDefault"))) {
					// 正常创意
					String strsql = "select pid from Chinaarea group by pid ";
					Map<String, Object> parms = new HashMap<String, Object>();
					List<Object> cols = new ArrayList<Object>();
					List areaList = dao.findBySql(strsql, parms, cols);
					Map<String, List<Chinaarea>> map = new HashMap<String, List<Chinaarea>>();
					for (int i = 0; i < areaList.size(); i++) {
						String pid = areaList.get(i) + "";
						String[] names = new String[1];
						String[] values = new String[1];
						names[0] = "pid";
						values[0] = pid;
						List<Chinaarea> list = dao.findAll(Chinaarea.class,
								names, values);
						map.put(pid, list);
					}
					request.setAttribute("allCitys", map);
				}
				this.getPayTypeList(request);
				request.setAttribute("readyBox", readyBox);
				request.setAttribute("isDefault",
						this.getParameter("isDefault"));
				AdCreative adCreative = (AdCreative) dao.findById(
						AdCreative.class,
						Integer.parseInt(this.getParameter("adCreativeId")));
				request.setAttribute("adCreative", adCreative);

				// 媒体最低单价权限赋予总监role
				Users users = (Users) request.getSession().getAttribute("user");
				String[] pname = { "userId" };
				String[] pvalue = { users.getUserId() + "" };
				List<UserRole> urList = dao.findAll(UserRole.class, pname,
						pvalue);
				UserRole userRole = urList.get(0);
				request.setAttribute("roleId", userRole.getRoleId());

				return mapping.findForward("edit");
			} else {
				// 执行修改
				if ("0".equals(this.getParameter("isDefault"))) {
					// 普通创意
					List<String> dateList = this.getKeys("dateTime");
					Collections.sort(dateList);
					String showIpString = "";
					for (String s : dateList) {
						String[] arr = s.split("_");
						String number = arr[1];
						String dateTime = this.getParameter(s);
						String newDate = dateTime.replace("-", "");
						String showIp = this.getParameter("flow_" + number);
						showIpString += showIp + "," + newDate + "|";
					}
					if (!showIpString.equals("")) {
						readyBox.setShowIpString(showIpString.substring(0,
								showIpString.length() - 1));
					}
					if (!"".equals(this.getParameter("areaList"))) {
						readyBox.setAreaFix(this.getParameter("areaList"));
					} else {
						readyBox.setAreaFix("0");
					}

					readyBox.setReadyBoxStatus(Integer.parseInt(this
							.getParameter("readyBoxState")));
					// readyBox.setAdCreativeLevel(100-Integer.parseInt(this.getParameter("creativeLevel")));
					// // 2012.12.13
					readyBox.setAdCreativeLevel(Integer.parseInt(this
							.getParameter("creativeLevel")));
				} else if ("1".equals(this.getParameter("isDefault"))) {
					// 默认创意
					readyBox.setAdCreativeLevel(0);
					readyBox.setAreaFix("0");
				}
				// #############################start####################################
				// 更新投放列表中的创意图片和路径
				String strUrl = Configure.get("img.url");
				AdCreative adCreative = (AdCreative) dao.findById(
						AdCreative.class, readyBox.getAdCreativeId());
				String imgUrl = "";
				if (adCreative.getAdCreativeImg() != null
						&& !"".equals(adCreative.getAdCreativeImg())) {
					imgUrl = strUrl + adCreative.getAdCreativeImg();
				}
				readyBox.setAdCreativeImg(imgUrl);
				if (adCreative.getIsP4p() == 0) {
					readyBox.setAdCreativeUrl(adCreative.getAdCreativeUrl());
				} else {
					readyBox.setHtmlCode(adCreative.getHtmlCode());
				}
				readyBox.setIsP4p(adCreative.getIsP4p());
				// 更新readyBox广告位域名绑定和域名列表
				AdBox adbox = (AdBox) dao.findById(AdBox.class,
						readyBox.getAdBoxId());
				String urlList = "";
				if (adbox.getIsURLBand() == 1) {// 绑定域名,查询该网站主有效域名
					String[] pname = new String[2];
					String[] pvalue = new String[2];
					pname[0] = "webMasterId";
					pname[1] = "urlStatus";
					pvalue[0] = readyBox.getWebMasterId() + "";
					pvalue[1] = "1";
					List<UrlAddress> urlAddressList = dao.findAll(
							UrlAddress.class, pname, pvalue);
					for (int i = 0; i < urlAddressList.size(); i++) {
						UrlAddress urlAddress = urlAddressList.get(i);
						if (i == urlAddressList.size() - 1) {
							urlList += urlAddress.getUrl();
						} else {
							urlList += urlAddress.getUrl() + "|";
						}
					}
				}
				readyBox.setIsUrlBand(adbox.getIsURLBand());
				readyBox.setUrlList(urlList);
				// #############################end####################################
				readyBox.setPayTypeId(Integer.parseInt(this
						.getParameter("payType")));
				readyBox.setWebMasterPrice(Float.parseFloat(this
						.getParameter("webMasterPrice")));
				readyBox.setDiscount(Integer.parseInt(this
						.getParameter("disCount")));
				readyBox.setShowType(Integer.parseInt(this
						.getParameter("showType")));
				dao.update(readyBox);

				// 级联更新reportCount,重新计算媒体单价及有效佣金
				String[] pname = { "readyBoxId" };
				String[] pvalue = { this.getParameter("readyBoxId") + "" };
				List<ReportCount> rcList = dao.findAll(ReportCount.class,
						pname, pvalue);
				if (rcList.size() > 0) {
					ReportCount reportCount = rcList.get(0);
					reportCount.setWebMasterPrice(Float.parseFloat(this
							.getParameter("webMasterPrice")));
					reportCount.setRealMoney(Float.parseFloat(this
							.getParameter("webMasterPrice"))
							* reportCount.getRealCount());
					dao.update(reportCount);
				}

				ReadyBoxUtil rb = new ReadyBoxUtil();
				rb.saveToMemCached(readyBox); // add by yu
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",
						"closeCurrent", "getCode");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
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
			ReadyBox readyBox = (ReadyBox) dao.findById(ReadyBox.class,
					Integer.parseInt(this.getParameter("readyBoxId")));
			readyBox.setReadyBoxStatus(2);
			// 状态变为废弃
			dao.update(readyBox);

			ReadyBoxUtil rb = new ReadyBoxUtil();
			rb.saveToMemCached(readyBox); // add by yu
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}

	/**
	 * 获取代码
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward getcode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String adBoxId = this.getParameter("adBoxId");
			String condition = " and a.adBoxId=:adBoxId ";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("adBoxId", Integer.parseInt(adBoxId));
			// 获得广告投放类型（图片、点击、弹窗）
			List<Integer> list = dao
					.findAll(
							"select m.modelFlag from AdBox a,AdBoxInfo ai,Models m where a.adBoxInfoId = ai.adBoxInfoId and ai.modelId=m.modelId ",
							condition, parms);
			if (list != null && list.size() > 0) {
				String url = Configure.get("server.url");
				String ip = Configure.get("server.ip");
				// 获得广告投放类型（图片、点击、弹窗）
				int modelFlag = list.get(0);
				String codeAddress = "";
				switch (modelFlag) {
				case 0:
					// 纯点击
					codeAddress = url + "/ViewHit/hitSimpleNew?bid=" + adBoxId;
					codeAddress += "\r\n\r\n" + ip
							+ "/ViewHit/hitSimpleNew?bid=" + adBoxId;
					break;
				case 1:
					// 弹窗
					codeAddress = "<script src='" + url
							+ "/ViewHit/js_open?bid=" + adBoxId + "'></script>";
					codeAddress += "\r\n\r\n" + "<script src='" + ip
							+ "/ViewHit/js_open?bid=" + adBoxId + "'></script>";
					break;
				case 2:
					// 图片
					codeAddress = "<script src='" + url + "/ViewHit/js_view?p="
							+ adBoxId + "'></script>";
					codeAddress += "\r\n\r\n" + "<script src='" + ip
							+ "/ViewHit/js_view?p=" + adBoxId + "'></script>";
					break;
				}
				request.setAttribute("codeAddress", codeAddress);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			} else {
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("getcode");
	}

	/**
	 * 获取代码
	 * 
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward getCodeNewTest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String adBoxId = this.getParameter("adBoxId");
			String condition = " and a.adBoxId=:adBoxId ";
			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("adBoxId", Integer.parseInt(adBoxId));
			// 获得广告投放类型（图片、点击、弹窗）
			List<Integer> list = dao
					.findAll(
							"select m.modelFlag from AdBox a,AdBoxInfo ai,Models m where a.adBoxInfoId = ai.adBoxInfoId and ai.modelId=m.modelId ",
							condition, parms);
			if (list != null && list.size() > 0) {
				String url = Configure.get("server.url");
				// 获得广告投放类型（图片、点击、弹窗）
				int modelFlag = list.get(0);
				String codeAddress = "";
				switch (modelFlag) {
				case 0:
					// 纯点击
					// codeAddress =
					// url+"/ViewHit/hitSimpleNew?bid="+adBoxId;3167
					codeAddress = url + "/ViewHit/hitSimpleWeight?bid="
							+ adBoxId;
					break;
				case 1:
					// 弹窗
					codeAddress = "<script src='" + url
							+ "/ViewHit/js_open?bid=" + adBoxId + "'></script>";
					break;
				case 2:
					// 图片
					codeAddress = "<script src='" + url + "/ViewHit/js_view?p="
							+ adBoxId + "'></script>";
					break;
				}
				request.setAttribute("codeAddress", codeAddress);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			} else {
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("getcode");
	}
}
