package com.pancou.ad.iter;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.Http;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.InterfaceBeiqing;
import com.pancou.ad.vo.ViewReadyBox;

public class BeiqingAction extends BaseDispatchAction {
	// 接口
	// http://sync.7676.com/cpsync/sync.php?dbid=087&btime=20121010&etime=20151010&action=
	// 参数：dbid合作方ID，贵方为087
	// btime:查询开始时间
	// etime：查询结束时间
	// action:结果类型 1注册 2充值
	// 其他说明：dbid不能为空，切为贵方ID，btime，etime也不能为空，不建议做较大时间段查询,action可为空，为空为充值和注册均有，如果
	// action为1那么amt值为0.00
	// 数据返回：json数据
	// 数据说明:uid注册用户ID,gid游戏ID,sid游戏区服ID,gunion贵方的子站ID,amt金额,action同参数说明,indbdate数据入库时间

	private PlatDao dao = new PlatDao();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String interAddr = "http://sync.7676.com/cpsync/sync.php?dbid=087";

	/**
	 * 当日数据接口
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

		// query
		Calendar cal = Calendar.getInstance();
		String btime = this.getParameter("btime");
		String etime = this.getParameter("etime");
		String actionFlag = this.getParameter("actionFlag");
		String webMasterName = this.getParameter("webMasterName");

		if ("".equals(btime)) {
			btime = sdf.format(cal.getTime());
		} else {
			btime = btime.replaceAll("-", "");
		}

		if ("".equals(etime)) {
			cal.add(Calendar.DATE, 1);
			etime = sdf.format(cal.getTime());
		} else {
			etime = etime.replaceAll("-", "");
		}
		interAddr += "&btime=" + btime + "&etime=" + etime + "&action="
				+ actionFlag;

		byte[] result = Http.requestServer(interAddr, null, Http.REQUEST_POST,
				Http.ENCODING_UTF8, null);
		String jsonString = new String(result);
		List<InterfaceBeiqing> list = InterfaceBeiqing.parseJson(jsonString);

		double allAmount = 0;
		int regCount = 0;
		int rechargeCount = 0;

		for (int i = 0; i < list.size(); i++) {
			InterfaceBeiqing bq = list.get(i);

			ViewReadyBox readyBox = (ViewReadyBox) dao.findById(
					ViewReadyBox.class, Integer.parseInt(bq.getGunion()));
			if (readyBox != null) {
				bq.setWebMasterName(readyBox.getWebMasterName());
			} else {
				bq.setWebMasterName("");
			}

			if (bq.getWebMasterName().contains(webMasterName)) {
				if (bq.getAction() == 2) {
					allAmount += bq.getAmt();
				}
				if (bq.getAction() == 1) {
					regCount++;
				} else if (bq.getAction() == 2) {
					rechargeCount++;
				}
			} else {
				list.remove(i);
				i--;
			}
		}

		request.setAttribute("list", list);
		request.setAttribute("allAmount", allAmount);
		request.setAttribute("count", list.size());
		request.setAttribute("regCount", regCount);
		request.setAttribute("rechargeCount", rechargeCount);
		request.setAttribute("btime", btime);
		request.setAttribute("etime", etime);
		request.setAttribute("actionFlag", actionFlag);
		request.setAttribute("webMasterName", webMasterName);
		request.getSession().setAttribute("beiqingJsonString", jsonString);

		return mapping.findForward("list");
	}

	/**
	 * 当日报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName = "北青数据";
			String webMasterName = this.getParameter("webMasterName");
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

			List<String> headerList = new ArrayList<String>();
			headerList.add("注册用户ID");
			headerList.add("游戏ID");
			headerList.add("游戏区服ID");
			headerList.add("渠道ID");
			headerList.add("渠道名称");
			headerList.add("金额");
			headerList.add("动作");
			headerList.add("日期");

			List<Object[]> datas = new ArrayList<Object[]>();
			String jsonString = request.getSession()
					.getAttribute("beiqingJsonString").toString();
			List<InterfaceBeiqing> list = InterfaceBeiqing
					.parseJson(jsonString);

			for (int i = 0; i < list.size(); i++) {
				InterfaceBeiqing bq = list.get(i);

				ViewReadyBox readyBox = (ViewReadyBox) dao.findById(
						ViewReadyBox.class, Integer.parseInt(bq.getGunion()));
				if (readyBox != null) {
					bq.setWebMasterName(readyBox.getWebMasterName());
				} else {
					bq.setWebMasterName("");
				}

				if (!bq.getWebMasterName().contains(webMasterName)) {
					list.remove(i);
					i--;
				}

				Object[] object = new Object[8];
				object[0] = bq.getUid();
				object[1] = bq.getGid();
				object[2] = bq.getSid();
				object[3] = bq.getGunion();
				object[4] = bq.getWebMasterName();
				object[5] = bq.getAmt();
				if (bq.getAction() == 1) {
					object[6] = "注册";
				} else if (bq.getAction() == 2) {
					object[6] = "充值";
				}
				object[7] = bq.getIndbdate();
				datas.add(object);
			}
			ExcelUtil.export(datas, headerList, fileName, os);
			request.getSession().setAttribute("beiqingJsonString", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * history query
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward history(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int pageNum = 0;
		int numPerPage = 20;
		if ("pager".equals(request.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}

		String btime = this.getParameter("btime");
		String etime = this.getParameter("etime");
		String actionFlag = this.getParameter("actionFlag");
		String webMasterName = this.getParameter("webMasterName");

		String hql = "from InterfaceBeiqing i where 1=1";
		String sql = "select count(*) from InterfaceBeiqing i where 1=1";
		String condition = "";
		Map<String, Object> parms = new HashMap<String, Object>();
		if (!"".equals(btime) && !"".equals(etime)) {
			condition += " and i.indbdate >= :btime and i.indbdate <= :etime";
			parms.put("btime", btime);
			parms.put("etime", etime);
		} else {
			condition += " and datediff(day,getdate(),i.indbdate) = -1";
		}
		if (!"".equals(actionFlag)) {
			condition += " and i.action = :actionFlag";
			parms.put("actionFlag", Integer.parseInt(actionFlag));
		}
		if (!"".equals(webMasterName)) {
			condition += " and i.webMasterName like :webMasterName";
			parms.put("webMasterName", "%" + webMasterName + "%");
		}

		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount(sql + condition, parms));
		List<InterfaceBeiqing> list = dao.findAll(pageMap.get("first"),
				pageMap.get("size"), hql, condition, parms);

		double allAmount = 0;
		int regCount = 0;
		int rechargeCount = 0;

		for (InterfaceBeiqing bq : list) {
			if (bq.getAction() == 2) {
				allAmount += bq.getAmt();
			}
			if (bq.getAction() == 1) {
				regCount++;
			} else if (bq.getAction() == 2) {
				rechargeCount++;
			}
		}

		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}

		request.setAttribute("btime", btime);
		request.setAttribute("etime", etime);
		request.setAttribute("actionFlag", actionFlag);
		request.setAttribute("webMasterName", webMasterName);

		request.setAttribute("allAmount", allAmount);
		request.setAttribute("count", list.size());
		request.setAttribute("regCount", regCount);
		request.setAttribute("rechargeCount", rechargeCount);
		return mapping.findForward("history");
	}

	/**
	 * history query report
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward historyToExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String fileName = "北青数据";
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
			// URLDecoder.decode(fileName ,"utf-8");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel;charset=UTF-8");// 定义输出类型

			List<String> headerList = new ArrayList<String>();
			List<Object[]> datas = new ArrayList<Object[]>();

			headerList.add("注册用户ID");
			headerList.add("游戏ID");
			headerList.add("游戏区服ID");
			headerList.add("渠道ID");
			headerList.add("渠道名称");
			headerList.add("金额");
			headerList.add("动作");
			headerList.add("日期");

			String btime = this.getParameter("btime");
			String etime = this.getParameter("etime");
			String webMasterName = this.getParameter("webMasterName");

			String hql = "from InterfaceBeiqing i where 1=1";
			String condition = "";
			Map<String, Object> parms = new HashMap<String, Object>();
			if (!"".equals(btime) && !"".equals(etime)) {
				condition += " and i.indbdate >= :btime and i.indbdate <= :etime";
				parms.put("btime", btime);
				parms.put("etime", etime);
			} else {
				condition += " and datediff(day,getdate(),i.indbdate) = -1";
			}
			if (!"".equals(webMasterName)) {
				condition += " and i.webMasterName like :webMasterName";
				parms.put("webMasterName", "%" + webMasterName + "%");
			}

			List<InterfaceBeiqing> list = dao.findAll(hql, condition, parms);

			for (InterfaceBeiqing bq : list) {

				Object[] object = new Object[8];
				object[0] = bq.getUid();
				object[1] = bq.getGid();
				object[2] = bq.getSid();
				object[3] = bq.getGunion();
				object[4] = bq.getWebMasterName();
				object[5] = bq.getAmt();
				if (bq.getAction() == 1) {
					object[6] = "注册";
				} else if (bq.getAction() == 2) {
					object[6] = "充值";
				}
				object[7] = bq.getIndbdate();
				datas.add(object);
			}
			ExcelUtil.export(datas, headerList, fileName, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}