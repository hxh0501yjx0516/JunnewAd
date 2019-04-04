package com.pancou.ad.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.vo.AdBoxInfo;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdCreativeType;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.AdSize;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.Models;
import com.pancou.ad.vo.Operation;
import com.pancou.ad.vo.PayType;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ShopFclass;
import com.pancou.ad.vo.ShopSclass;
import com.pancou.ad.vo.ShopWeb;
import com.pancou.ad.vo.UserGroup;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.ViewAdBoxCount;
import com.pancou.ad.vo.WebMaster;

@Entity
public class BaseDispatchAction extends DispatchAction {

	private Map<String, Object> parms;
	protected static final int SUCCESS_STATUS = 200;
	protected static final int FAILURE_STATUS = 300;
	protected static final String CALLBACKTYPE_CLOSECURRENT = "closeCurrent";
	protected static final String CALLBACKTYPE_FORWARD = "forward";
	protected static final String CALLBACKTYPE_REL = "rel";
	@ManyToOne
	protected PlatDao dao = new PlatDao();
	private String forwardUrl;

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// System.out.println("BaseDispatchAction start!!!");
		Users users = (Users) request.getSession().getAttribute("user");
		if (users == null || users.getUserId() == 5) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("logout");
		}
		// System.out.println("request.getRemoteAddr=="+request.getRemoteAddr());
		parms = WebUtils.getParametersStartingWith(request, "");
		return super.execute(mapping, form, request, response);
	}

	/**
	 * 获得所有参数
	 * 
	 * @return
	 */
	protected String getParameter(String name) {
		if (parms.get(name) == null) {
			return "";
		}
		return parms.get(name).toString().trim();
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得所有KEY值
	 * 
	 * @param prefix
	 *            所传参数前缀
	 * @return 带有相同前缀的参数集合
	 */
	protected List<String> getKeys(String prefix) {
		Set<String> keys = parms.keySet();
		List<String> keyList = new ArrayList<String>();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (null != prefix && !"".equals(prefix)) {
				if (key.indexOf(prefix) != -1) {
					keyList.add(key);
				}
			} else {
				keyList.add(key);
			}
		}
		return keyList;
	}

	/**
	 * 移除参数
	 * 
	 * @return
	 */
	protected void removeParameter(String name) {
		parms.put(name, "");
	}

	/**
	 * 获得有效状态用户列表，页面获取用户集合为userslist (媒介)
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getUsersList(HttpServletRequest request) {
		// String[] stateArr={"state"};
		// String[] valueArr={"0"};
		// List<Users> userslist = dao.findAll(Users.class,stateArr,valueArr);
		String condition = "where u.userId = ur.userId and ur.roleId =:roleId and u.state = :state ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", 2);
		params.put("state", 0);
		if (this.getUserSession(request).getUserGroup() != 0) {
			condition += " and u.userGroup =:userGroup";
			params.put("userGroup", this.getUserSession(request).getUserGroup());
		}
		List<Users> userslist = dao.findAll(
				"select u from Users u,UserRole ur ", condition, params);
		request.setAttribute("userslist", userslist);
	}

	/**
	 * 获得有效状态用户列表，页面获取用户集合为userslist (销售)
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getXSList(HttpServletRequest request) {
		// String[] stateArr={"state"};
		// String[] valueArr={"0"};
		// List<Users> userslist = dao.findAll(Users.class,stateArr,valueArr);
		// String condition =
		// "where u.userId = ur.userId and (ur.roleId =:roleId or u.userId =:userId) and u.state = :state ";
		String condition = "where u.userId = ur.userId and (ur.roleId =:roleId) and u.state = :state ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", 5);
		// params.put("userId", 12);
		params.put("state", 0);
		if (this.getUserSession(request).getUserGroup() != 0) {
			condition += " and u.userGroup =:userGroup";
			params.put("userGroup", this.getUserSession(request).getUserGroup());
		}
		List<Users> xslist = dao.findAll("select u from Users u,UserRole ur ",
				condition, params);
		request.setAttribute("xslist", xslist);
	}

	/**
	 * 获得有效状态客户列表，页面获取用户集合为customerList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getCustomerList(HttpServletRequest request) {
		String condition = "where c.customerStatus =:state ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", 0);
		if (this.getUserSession(request).getUserGroup() != 0) {
			condition += " and c.userGroup =:userGroup";
			params.put("userGroup", this.getUserSession(request).getUserGroup());
		}
		List<Customer> customerList = dao.findAll("select c from Customer c ",
				condition, params);
		request.setAttribute("customerList", customerList);
	}

	/**
	 * 
	 * @param request
	 */
	protected void getWebMasterList(HttpServletRequest request) {
		String condition = "where w.webMasterStatus = 0 and w.userId <> 0";
		Map<String, Object> params = new HashMap<String, Object>();
		List<WebMaster> webMasterList = dao.findAll("from WebMaster w ",
				condition, params);
		request.setAttribute("webMasterList", webMasterList);
	}

	/**
	 * 获得有效状态广告计划列表，页面获取集合为adPlanList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getAdPlanList(HttpServletRequest request) {
		String[] stateArr = new String[2];
		String[] valueArr = new String[2];
		stateArr[0] = "adPlanStatus";
		valueArr[0] = "0";
		Users user = (Users) request.getSession().getAttribute("user");
		if (user.getUserGroup() != 0) {
			stateArr[1] = "userGroup";
			valueArr[1] = user.getUserGroup() + "";
		}
		List<AdPlan> adPlanList = dao.findAll(AdPlan.class, stateArr, valueArr);
		request.setAttribute("adPlanList", adPlanList);
	}

	/**
	 * 广告计划周期信息 ,页面获取集合为adPlanCycleList
	 * 
	 * @return
	 */
	public void getAdPlanCycleList(HttpServletRequest request) {
		String condition = "";
		Map<String, Object> params = new HashMap<String, Object>();
		Users user = (Users) request.getSession().getAttribute("user");
		if (user.getUserGroup() != 0) {
			condition = " and a.userGroup=:userGroup";
			params.put("userGroup", user.getUserGroup());
		}
		List<AdPlanCycle> adPlanCycleList = dao
				.findAll(
						"select ac from AdPlanCycle ac,AdPlan a where ac.adPlanId = a.adPlanId ",
						condition, params);
		System.out.println(adPlanCycleList.size());
		request.setAttribute("adPlanCycleList", adPlanCycleList);
	}

	/**
	 * 获得有效状态尺寸列表，页面获取集合为adSizeList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getAdSizeList(HttpServletRequest request) {
		String[] names = new String[1];
		String[] values = new String[1];
		names[0] = "adSizeStatus";
		values[0] = "0";
		String orderSql = " order by adWidth asc";
		List adSizeList = dao.findAllOrder(AdSize.class, names, values,
				orderSql);
		request.setAttribute("adSizeList", adSizeList);
	}

	/**
	 * 获得有效状态广告模式列表，页面获取集合为modeList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getModelList(HttpServletRequest request) {
		String[] names = { "modelStatus" };
		String[] valueArrs = { "0" };
		List modelList = dao.findAll(Models.class, names, valueArrs);
		request.setAttribute("modelList", modelList);
	}

	/**
	 * 获得支付模式，页面获取集合为payTypeList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getPayTypeList(HttpServletRequest request) {
		String[] names = new String[0];
		String[] valueArrs = new String[0];
		List payTypeList = dao.findAll(PayType.class, names, valueArrs);
		request.setAttribute("payTypeList", payTypeList);
	}

	/**
	 * 获得有效状态广告规格列表，页面获取集合为adBoxInfoList
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	protected void getAdBoxInfoList(HttpServletRequest request) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("adBoxInfoStatus", "0");
		String sql = "select ai.*,m.modelName,s.adWidth,s.adHeight from adboxInfo ai left join models m on ai.modelId = m.modelId left join adSize s on "
				+ " ai.adSizeId = s.adSizeId where ai.adBoxInfoStatus=:adBoxInfoStatus order by ai.adBoxInfoId desc";
		List qryList = dao.findBySql(0, 2000, sql, parms);
		List<AdBoxInfo> list = new ArrayList<AdBoxInfo>();
		for (int i = 0; i < qryList.size(); i++) {
			Object[] object = (Object[]) qryList.get(i);
			AdBoxInfo adBoxInfo = new AdBoxInfo();
			adBoxInfo.setAdBoxInfoId(Integer.parseInt(object[0] + ""));
			adBoxInfo.setAdBoxInfoName(object[1] + "");
			adBoxInfo.setAdSizeId(Integer.parseInt(object[2] + ""));
			adBoxInfo.setModelId(Integer.parseInt(object[3] + ""));
			adBoxInfo.setAdBoxInfoRemarks(object[4] + "");
			adBoxInfo.setAdBoxInfoStatus(Integer.parseInt(object[5] + ""));
			adBoxInfo.setAddTime(object[6] + "");
			adBoxInfo.setModelName(object[7] + "");
			adBoxInfo.setAdSizeName(object[8] + "x" + object[9]);
			list.add(adBoxInfo);
		}
		request.setAttribute("adBoxInfoList", list);
	}

	/**
	 * 关闭开关时修改投放列表中非默认创意的状态并加入到缓存
	 * 
	 * @param parms
	 */
	@SuppressWarnings("unchecked")
	protected void updateReadyBox(HttpServletRequest request,
			Map<String, Object> parms) {
		String sql = "update ReadyBox set readyBoxStatus = 1 where ";
		String condition = "";
		String cacheSql = "from ReadyBox where ";
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> cacheParms = new HashMap<String, Object>();
		// 修改投放列表中非默认创意状态
		if (parms.get("readyPlanId") != null
				&& !"".equals(parms.get("readyPlanId"))) {
			// 站长关联计划状态锁定，锁定投放列表中创意不为默认并且状态为正常的创意
			condition = " adPlanId=:adPlanId and webMasterId=:webMasterId and adCreativeId  in(select rb.adCreativeId "
					+ "from ReadyBox rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and rb.readyBoxStatus =0 and ac.isDefault =0 and rb.adPlanId=:adPlanId1 and rb.webMasterId=:webMasterId1)";
			map.put("adPlanId", parms.get("adPlanId"));
			map.put("webMasterId", parms.get("webMasterId"));
			map.put("adPlanId1", parms.get("adPlanId"));
			map.put("webMasterId1", parms.get("webMasterId"));
			// 查找需加入缓存投放列表
			cacheSql += " webMasterId =:webMasterId and adPlanId=:adPlanId";
			cacheParms.put("webMasterId", parms.get("webMasterId"));
			cacheParms.put("adPlanId", parms.get("adPlanId"));

		} else if (parms.get("webMasterId") != null
				&& !"".equals(parms.get("webMasterId"))) {
			condition = " webMasterId=:webMasterId and adCreativeId  in(select rb.adCreativeId "
					+ "from ReadyBox rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and rb.readyBoxStatus =0 and ac.isDefault =0 and rb.webMasterId=:webMasterId1)";
			map.put("webMasterId", parms.get("webMasterId"));
			map.put("webMasterId1", parms.get("webMasterId"));

			// 查找需加入缓存投放列表
			cacheSql += " webMasterId =:webMasterId ";
			cacheParms.put("webMasterId", parms.get("webMasterId"));

		} else if (parms.get("urlId") != null && !"".equals(parms.get("urlId"))) {
			condition = " urlId=:urlId and adCreativeId  in(select rb.adCreativeId "
					+ "from ReadyBox rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and rb.readyBoxStatus =0 and ac.isDefault =0 and rb.urlId=:urlId1)";
			map.put("urlId", parms.get("urlId"));
			map.put("urlId1", parms.get("urlId"));

			// 查找需加入缓存投放列表
			cacheSql += " urlId=:urlId  ";
			cacheParms.put("urlId", parms.get("urlId"));
		} else if (parms.get("adBoxId") != null
				&& !"".equals(parms.get("adBoxId"))) {
			condition = " adBoxId=:adBoxId and adCreativeId  in(select rb.adCreativeId "
					+ "from ReadyBox rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and rb.readyBoxStatus =0 and ac.isDefault =0 and rb.adBoxId=:adBoxId1)";
			map.put("adBoxId", parms.get("adBoxId"));
			map.put("adBoxId1", parms.get("adBoxId"));
			// 查找需加入缓存投放列表
			cacheSql += " adBoxId=:adBoxId  ";
			cacheParms.put("adBoxId", parms.get("adBoxId"));
		} else if (parms.get("adPlanId") != null
				&& !"".equals(parms.get("adPlanId"))) {
			condition = " adPlanId=:adPlanId and adCreativeId  in(select rb.adCreativeId "
					+ "from ReadyBox rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and rb.readyBoxStatus =0 and ac.isDefault =0 and rb.adPlanId=:adPlanId1)";
			map.put("adPlanId", parms.get("adPlanId"));
			map.put("adPlanId1", parms.get("adPlanId"));
			// 查找需加入缓存投放列表
			cacheSql += " adPlanId=:adPlanId  ";
			cacheParms.put("adPlanId", parms.get("adPlanId"));
		} else if (parms.get("adPlanCycleId") != null
				&& !"".equals(parms.get("adPlanCycleId"))) {
			sql = "update ReadyBox set readyBoxStatus = 2 where ";
			condition = " adPlanCycleId=:adPlanCycleId ";
			// "and adCreativeId in(select rb.adCreativeId from ReadyBox
			// rb,AdCreative ac where rb.adCreativeId = ac.adCreativeId and
			// rb.readyBoxStatus =0 and ac.isDefault =0 and
			// rb.adPlanCycleId=:adPlanCycleId1)
			map.put("adPlanCycleId", parms.get("adPlanCycleId"));
			// map.put("adPlanCycleId1", parms.get("adPlanCycleId"));
			// 查找需加入缓存投放列表
			cacheSql += " adPlanCycleId=:adPlanCycleId  ";
			cacheParms.put("adPlanCycleId", parms.get("adPlanCycleId"));
		} else if (parms.get("adCreativeId") != null
				&& !"".equals(parms.get("adCreativeId"))) {
			AdCreative adCreative = (AdCreative) dao.findById(AdCreative.class,
					Integer.parseInt(parms.get("adCreativeId") + ""));
			if (adCreative.getIsDefault() == 1) {// 默认创意
				// sql = "update ReadyBox set readyBoxStatus = 0 where ";
				// parms.put("remarks","修改创意ID为"+parms.get("adCreativeId")+"的状态,该创意为默认创意，投放列表0-状态未变");
				condition = " 1=0 and";
			}
			condition += " adCreativeId=:adCreativeId and readyBoxStatus = 0 ";
			map.put("adCreativeId", parms.get("adCreativeId"));
			// 查找需加入缓存投放列表
			cacheSql += " adCreativeId=:adCreativeId ";
			cacheParms.put("adCreativeId", parms.get("adCreativeId"));
		}
		int count = dao.doSql(sql + condition, map);
		if (count > 0) {
			// 查找需加入缓存投放列表
			List<ReadyBox> readyList = dao.findAll(cacheSql, "", cacheParms);
			for (ReadyBox readyBox : readyList) {
				ReadyBoxUtil rb = new ReadyBoxUtil();
				rb.saveToMemCached(readyBox); // add by yu
			}
			Users users = (Users) request.getSession().getAttribute("user");
			Operation operation = new Operation();
			operation.setAddTime(DatetimeHandle.formatCurrentDate());
			operation.setUserId(users.getUserId());
			operation.setUserName(users.getUsername());
			operation.setOperationSource(parms.get("operationSource") + "");
			operation
					.setRemarks(users.getUsername() + "于"
							+ DatetimeHandle.formatCurrentDate()
							+ parms.get("remarks"));
			dao.save(operation);
		}

	}

	/**
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message) {
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("message", message);
	}

	/**
	 * 如果callbackType值为forward，则要先设置好跳转路径forwardUrl
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 * @param callbackType
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message, String callbackType) {
		buildAjaxResult(request, statusCode, message);
		request.setAttribute("callbackType", callbackType);
		if (CALLBACKTYPE_FORWARD.equals(callbackType)) {
			request.setAttribute("forwardUrl", forwardUrl);
		}
	}

	/**
	 * 
	 * @param request
	 * @param statusCode
	 * @param message
	 * @param callbackType
	 * @param navTabId
	 */
	protected void buildAjaxResult(HttpServletRequest request, int statusCode,
			String message, String callbackType, String navTabId) {
		buildAjaxResult(request, statusCode, message, callbackType);
		request.setAttribute("navTabId", navTabId);
	}

	/**
	 * 
	 * @param request
	 * @param parms
	 *            分页参数map集合
	 */
	protected void buildPageResult(HttpServletRequest request,
			Map<String, Object> parms) {
		Set<String> keys = parms.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = parms.get(key);
			request.setAttribute(key, value);
		}

	}

	// *以下为SHOP管理使用
	/**
	 * SHOP父栏目列表
	 * 
	 * @param request
	 */
	protected void getShopFclassList(HttpServletRequest request) {
		List<ShopFclass> list = dao.findAll(ShopFclass.class);
		request.setAttribute("shopFclassList", list);
	}

	/**
	 * SHOP子栏目列表
	 * 
	 * @param request
	 */
	protected void getShopSclassList(HttpServletRequest request) {
		List<ShopSclass> list = dao.findAll(ShopSclass.class);
		request.setAttribute("shopSclassList", list);
	}

	protected void getShopWebList(HttpServletRequest request) {
		List<ShopWeb> list = dao.findAll(ShopWeb.class);
		request.setAttribute("shopWebList", list);
	}

	/**
	 * 返回根据readyboxid获取相关投放的 定义日期范围内的 波动对比情况
	 * 
	 * @param readyBoxId
	 * @return
	 */
	public Map<String, Object> getWave(int readyBoxId) {
		Map<String, Object> waveMap = new HashMap<String, Object>();

		// int readyBoxId = 1842;
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		long time = 1000 * 3600 * 24;

		// 起始日期=结束日期-9
		String beginTime = DatetimeHandle.formatDate(d.getTime() - time * 11,
				"yyyy-MM-dd");
		// 结束日期=当前日期-2
		String endTime = DatetimeHandle.formatDate(d.getTime() - time * 2,
				"yyyy-MM-dd");
		// 需查看波动的日期=当前日期-1
		String bdTime = DatetimeHandle.formatDate(d.getTime() - time * 1,
				"yyyy-MM-dd");

		waveMap.put("beginTime", beginTime);
		waveMap.put("bdTime", bdTime);

		// System.out.println("开始日期:"+beginTime);
		// System.out.println("结束日期:"+endTime);
		// System.out.println("波动日期:"+bdTime);
		// System.out.println("");

		PlatDao dao = new PlatDao();
		// 取得起始日期到结束日期内readyboxid的ip数据列表
		String sqlstr = "from ViewAdBoxCount v where 1=1";

		Map<String, Object> parms = new HashMap<String, Object>();
		String condition = " and v.readyBoxId =:readyboxid and v.adBoxCountTime >=:bt and v.adBoxCountTime <=:et";
		parms.put("readyboxid", readyBoxId);
		parms.put("bt", beginTime);
		parms.put("et", endTime);
		List<ViewAdBoxCount> list = dao.findAll(sqlstr, condition, parms);

		// 无可用于计算的历史数据，返回波动状态为正常
		if (list.size() < 1) {
			waveMap.put("waveState", "0");
			waveMap.put("wavePercent", "0");
			return waveMap;
		}

		// 第一次遍历，取得起始日期内IP总和：sumIp
		int sumIp = 0; // 总和
		int ipAvg = 0; // 平均值
		Iterator<ViewAdBoxCount> it = list.iterator();
		while (it.hasNext()) {
			ViewAdBoxCount vc = it.next();
			sumIp += vc.getIp();
		}
		if (list.size() == 0) {
			ipAvg = sumIp;
		} else {
			ipAvg = sumIp / list.size();
		}
		// System.out.println("首次SUMIP:"+sumIp);
		// System.out.println("首次平均值:"+ipAvg);
		// System.out.println("");

		// 第二次遍历，如果ip大于sumip平均值的0.5倍，或小于sumip平均值
		// 的2倍，则ip可用于新ip平均值的计算。其它的IP抛弃，不参与计算
		int sumIpNew = 0; // 可参与计算的IP总和
		int dayNum = 0; // 可参与计算的IP的天数
		int ipAvgNew = 0; // 可参与计算的IP的平均值，用于与需查看波动当天的IP做对比
		Iterator<ViewAdBoxCount> itNew = list.iterator();
		while (itNew.hasNext()) {
			ViewAdBoxCount vcNew = itNew.next();
			if (vcNew.getIp() >= ipAvg / 2 && vcNew.getIp() <= ipAvg * 2) {
				sumIpNew += vcNew.getIp();
				dayNum += 1;
			}
		}
		if (dayNum == 0) {
			ipAvgNew = sumIpNew;
		} else {
			ipAvgNew = sumIpNew / dayNum;
		}

		// System.out.println("首次平均值/2:"+ipAvg/2);
		// System.out.println("首次平均值*2:"+ipAvg*2);
		// System.out.println("");
		// System.out.println("二次SUMIP:"+sumIpNew);
		// System.out.println("二次计算天数:"+dayNum);
		// System.out.println("二次平均值:"+ipAvgNew);

		// 取得波动日期的readyboxid的ip数据
		String sqlstrBD = "from ViewAdBoxCount v where 1=1";

		Map<String, Object> parmsBD = new HashMap<String, Object>();
		String conditionBD = " and v.readyBoxId =:readyboxid and v.adBoxCountTime =:bdt";
		parmsBD.put("readyboxid", readyBoxId);
		parmsBD.put("bdt", bdTime);
		List<ViewAdBoxCount> listBD = dao.findAll(sqlstrBD, conditionBD,
				parmsBD);

		int sumIpBD = 0; // 波动日ip数据
		Iterator<ViewAdBoxCount> itBD = listBD.iterator();
		while (itBD.hasNext()) {
			ViewAdBoxCount vc = itBD.next();
			sumIpBD += vc.getIp();
		}
		// System.out.println("波动日SUMIP:"+sumIpBD);
		// System.out.println("波动日天数:"+listBD.size());

		// 无可用于查看的波动数据，返回波动状态为正常
		if (sumIpBD == 0) {
			waveMap.put("waveState", "0");
			waveMap.put("wavePercent", "0");
			return waveMap;
		}

		// 波动日ip数据,起始日期内可用IP的平均值比较，以返回波动情况
		float x = 30; // 波动系数 30%

		// System.out.println("波动参照值下线："+(ipAvgNew * (1-x/100)));
		// System.out.println("波动参照值上线："+(ipAvgNew * (1+x/100)));

		DecimalFormat format = new DecimalFormat("0.00");
		double wavePercent = (double) sumIpBD / ipAvgNew;
		// if (wavePercent >= 1){
		// wavePercent = wavePercent - 1;
		// }
		// else if (wavePercent < 1){
		// wavePercent = 1 - wavePercent;
		// }
		// System.out.println("波动比例：" +format.format(wavePercent)+"");

		// 波动日数据骤减，小于起始日期内可用IP的平均值的1/5倍，或者大于平均值的5倍，或者起始日期内无可用IP的平均值，暂定判断为投放已停止。返回波动状态为正常
		if (sumIpBD < ipAvgNew / 5 || sumIpBD > ipAvgNew * 5) {
			waveMap.put("waveState", "0");
			waveMap.put("wavePercent", "0");
			return waveMap; // 正常波动
		} else if (ipAvgNew == 0) {
			waveMap.put("waveState", "0");
			return waveMap; // 正常波动
		} else {
			// 起始日期内可用IP的平均值的0.7倍与1.3倍之间，暂定为正常波动区间。返回波动状态
			if (sumIpBD >= ipAvgNew * (1 - x / 100)
					&& sumIpBD <= ipAvgNew * (1 + x / 100)) {
				waveMap.put("waveState", "0");
				waveMap.put("wavePercent", format.format(wavePercent));
				return waveMap; // 正常波动
			} else {
				waveMap.put("waveState", "1");
				waveMap.put("wavePercent", format.format(wavePercent));
				return waveMap; // 非正常波动
			}
		}
	}

	/**
	 * 获得用户组
	 */
	protected void getUserGroupList(HttpServletRequest request) {
		String[] statusName = { "UserGroupStatus" };
		String[] statusValue = { "0" };
		List list = dao.findAll(UserGroup.class, statusName, statusValue);
		request.setAttribute("userGroupList", list);
	}

	/**
	 * 获得当前登陆用户session
	 * 
	 * @param request
	 */
	protected Users getUserSession(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		return user;
	}

	/**
	 * 获是创意类型表中的父级类型列表
	 * 
	 * @param request
	 */
	protected void getAdCreativeTypeFather(HttpServletRequest request) {
		String sql = "select a.adCreativeTypeId,a.adCreativeTypeName from AdCreativeType a where a.adCreativeTypeTid =:adCreativeTypeTid";
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("adCreativeTypeTid", 0);

		List qrylist = dao.findBySql(sql, parms);
		ArrayList<AdCreativeType> list = new ArrayList<AdCreativeType>();
		for (Object object : qrylist) {
			Object[] o = (Object[]) object;
			AdCreativeType adCreativeType = new AdCreativeType();
			adCreativeType.setAdCreativeTypeId(Integer.parseInt(o[0] + ""));
			adCreativeType.setAdCreativeTypeName(o[1] + "");
			list.add(adCreativeType);
		}
		request.setAttribute("adCreativeTypeFather", list);
	}

	/**
	 * 获取HTTP接口数据
	 * 
	 * @param request
	 * @param url
	 * @return List<Map<String,String>>
	 */
	protected List getEc(HttpServletRequest request, String url) {
		try {
			List<Map> list = new ArrayList<Map>();

			SAXReader reader = new SAXReader();
			Document doc = reader.read(url);
			Element root = doc.getRootElement(); // 父节点 xml-body

			Iterator root_it = root.elementIterator();
			while (root_it.hasNext()) {

				Element father = (Element) root_it.next(); // 二级主节点 ec
				Iterator father_it = father.elementIterator();

				Map<String, String> parms = new HashMap<String, String>();

				while (father_it.hasNext()) {
					Element children = (Element) father_it.next(); // 三级子节点
					parms.put(children.getName(), children.getText());
				}
				list.add(parms);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 当前登陆用户权限ID
	 * 
	 * @param request
	 * @return
	 */
	protected int getUserRoleId(HttpServletRequest request) {
		Users users = (Users) this.getUserSession(request);
		String[] pname = { "userId" };
		String[] pvalue = { users.getUserId() + "" };
		List<UserRole> urList = dao.findAll(UserRole.class, pname, pvalue);
		UserRole userRole = urList.get(0);
		return userRole.getRoleId();
	}

}
