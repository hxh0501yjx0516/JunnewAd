package com.pancou.ad.iter;

import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Ec;
import com.pancou.ad.vo.UserRole;

@Entity
public class InterfaceAction extends BaseDispatchAction {
//	private static List xmlList = null;

	/**
	 * get xml
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		//媒介访问，屏蔽 数据保存按钮
		boolean	isMedia = false;
		String[] mpname = {"userId"};
		String[] mpvalue = {this.getUserSession(request).getUserId()+""};
		List<UserRole> ur = dao.findAll(UserRole.class, mpname, mpvalue);
		if (ur.get(0).getRoleId() == 11){
			isMedia = true;
		}
		request.setAttribute("isMedia", isMedia);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(cal.DAY_OF_YEAR, -2);
		String currentTime = sdf.format(cal.getTime());
		cal.add(cal.DAY_OF_YEAR, 3);
		String nextTime = sdf.format(cal.getTime());	
		
		//客户HTTP数据接口
		//http://www.zhiwo.com/cpc/query/panku?begin_time=20120620&end_time=20120620

		String url = "http://www.zhiwo.com/cpc/query/panku?";
		
		String beginTime;
		String endTime;
		if (!"".equals(this.getParameter("beginTime")) && !"".equals(this.getParameter("endTime"))) {
			beginTime = this.getParameter("beginTime");
			endTime = this.getParameter("endTime");
			url += "&begin_time="+beginTime.replace("-", "")+"&end_time="+endTime.replace("-", "");
		} else {
			beginTime = currentTime;
			endTime = nextTime;
			url += "&begin_time="+beginTime.replace("-", "")+"&end_time="+endTime.replace("-", "");
		}
		
		
		//获得客户接口数据
		List<Map<String,String>> qrylist = this.getEc(request, url);
		List<Ec> list = new ArrayList<Ec>();
		if (qrylist.get(0).isEmpty()) {
			//当前查询无数据
			Ec ec = new Ec();
			ec.setCustomerInfo("No data");
			list.add(ec);
			request.setAttribute("dataExists", "none");	//无数据，屏蔽提交按钮
		} else {
			boolean listLast = false;	//标记list循环是否已全部完成 
			
			for (int i = qrylist.size() - 1; i >= 0; i--) {
				double allAmount = 0;	//商品总额
				Map<String,String> parms = qrylist.get(i);
				
				Ec ec = new Ec();
				ec.setCustomerInfo("zhiwo");
				ec.setUrlFlag(parms.get("cpcid"));
				ec.setOrderId(parms.get("orderid"));
				ec.setProductName(parms.get("goodsname"));
				ec.setOrderStatus(parms.get("paystatus"));
				if (parms.get("goodsprice") != null) {
					ec.setAmount(parms.get("goodsprice"));
					
					String[] amountArray = parms.get("goodsprice").split("\\|\\|");
					for(int j = 0; j < amountArray.length; j++){
						allAmount += new Double(amountArray[j]);
					}
				}
				ec.setAllAmount(new Double(parms.get("amount")));
				ec.setAddTime(Timestamp.valueOf(parms.get("ordertime")));
				list.add(ec);
				
				//保存数据
				if ("save".equals(this.getParameter("flag"))) {
					try{
						String[] pname = {"customerInfo","orderId"};
						String[] pvalue = {"zhiwo",parms.get("orderid")};
						List liste = dao.findAll(Ec.class, pname, pvalue);
						if (liste.size() > 0) {
							Ec ecNew = (Ec)liste.get(0);
							ecNew.setOrderStatus(parms.get("paystatus"));
							dao.update(ecNew);
						} else {
							dao.save(ec);
						}
					} catch (Exception e) {
						e.printStackTrace();
						this.buildAjaxResult(request, FAILURE_STATUS, "保存失败，请重新操作！");
						return mapping.findForward("ajaxDone");
					}

					if (i == 0) {
						listLast = true;	//数据保存时，标记list循环已完部完成
					}
				}
			}
			
			//数据已全部保存，返回JSON
			if (listLast == true) {
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
				return mapping.findForward("ajaxDone");
			}
		}
		
		request.setAttribute("ec", list);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		return mapping.findForward("get");
}
	
	/**
	 * list		table: ec
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
		int numPerPage = 100;
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String sql = "select e.* from Ec e where 1=1";
		String condition = "";
		Map<String,Object> parms = new HashMap<String,Object>();
		if (!"".equals(this.getParameter("customerInfo"))) {
			condition += " and e.customerInfo like :customerinfo";
			parms.put("customerinfo", "%"+this.getParameter("customerInfo")+"%");
		}
		if (!"".equals(this.getParameter("urlFlag"))) {
			condition += " and e.urlFlag =:urlflag";
			parms.put("urlflag", this.getParameter("urlFlag"));
		}
		if (!"".equals(this.getParameter("beginTime")) && !"".equals(this.getParameter("endTime"))){
			condition += " and e.addTime >= :bt and e.addTime <= :et";
			parms.put("bt", DateFormat.getDateInstance().parse(this.getParameter("beginTime")));
			parms.put("et", DateFormat.getDateInstance().parse(this.getParameter("endTime")));
		}

		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(Ec.class, condition, parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sql+condition+" order by e.addTime desc", parms);
		List<Ec> list = new ArrayList<Ec>();
		for (Object object:qryList) {
			Object[] o = (Object[])object;
			Ec ec = new Ec();
			ec.setOrderId(o[0]+"");
			ec.setCustomerInfo(o[1]+"");
			ec.setUrlFlag(o[2]+"");
			ec.setOrderId(o[3]+"");
			ec.setProductName(o[4]+"");
			ec.setOrderStatus(o[5]+"");
			ec.setAmount(o[6]+"");
			ec.setAllAmount(new Double(o[7]+""));
			ec.setAddTime(Timestamp.valueOf(o[8]+""));
			list.add(ec);
		}
		
		if (list != null) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "ec", list));
		}
		request.setAttribute("customerInfo", this.getParameter("customerInfo"));
		request.setAttribute("urlFlag", this.getParameter("urlFlag"));
		request.setAttribute("beginTime", this.getParameter("beginTime"));
		request.setAttribute("endTime", this.getParameter("endTime"));
		
		return mapping.findForward("list");
	}
	
	/**
	 * prolist		table: ec
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward proList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub

		String hql = "from Ec e where 1=1";
		String condition = "";
		Map<String,Object> parms = new HashMap<String,Object>();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");

		if (!"".equals(this.getParameter("customerInfo"))) {
			condition += " and e.customerInfo like :customerinfo";
			parms.put("customerinfo", "%"+this.getParameter("customerInfo")+"%");
		}
		if (!"".equals(this.getParameter("urlFlag"))) {
			condition += " and e.urlFlag =:urlflag";
			parms.put("urlflag", this.getParameter("urlFlag"));
		}
		if (!"".equals(this.getParameter("beginTime")) && !"".equals(this.getParameter("endTime"))){
			condition += " and e.addTime >= :bt and e.addTime <= :et ";
			parms.put("bt",DateFormat.getDateInstance().parse(this.getParameter("beginTime")));
			parms.put("et",DateFormat.getDateInstance().parse(this.getParameter("endTime")));
			
		}
		

		List<Ec> list = dao.findAll(hql, condition, parms);
		
		if (list.size() > 0){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++){
				Ec ec = (Ec)list.get(i);
				sb.append(ec.getProductName());
			}
			sb.setLength(sb.length() - 1);
			
			String[] proArray = sb.toString().split("\\|\\|");
			HashMap<String,Integer> proMap = new HashMap<String,Integer>();
			for (int i = 0; i < proArray.length; i++){
				if (!"".equals(proArray[i])){
					if (!proMap.containsKey(proArray[i])){
						proMap.put(proArray[i], 1);
					} else {
						int countNum = proMap.get(proArray[i]);
						proMap.remove(proArray[i]);
						proMap.put(proArray[i], countNum+1);
					}
				}
			}
			
			List<Ec> proList = new ArrayList<Ec>();
			
			Iterator it = proMap.keySet().iterator();
			
			while (it.hasNext()){
				String key = (String)it.next();
				int value = proMap.get(key);
				
				Ec ec = new Ec();
				ec.setProName(key);
				ec.setProNum(value);
				proList.add(ec);
			}
			
			request.setAttribute("proList", proList);
		}
		request.setAttribute("customerInfo", this.getParameter("customerInfo"));
		request.setAttribute("urlFlag", this.getParameter("urlFlag"));
		request.setAttribute("beginTime", this.getParameter("beginTime"));
		request.setAttribute("endTime", this.getParameter("endTime"));
		
		return mapping.findForward("proList");
	}

	/**
	 * Data To Excel	table:	ec
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			String fileName = "数据报表";
			OutputStream os = response.getOutputStream();
			response.reset();
			fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			
			List<String> headList = new ArrayList<String>();
			headList.add("编号");
			headList.add("客户信息");
			headList.add("链接标识");
			headList.add("订单号");
			headList.add("订单商品");
			headList.add("订单金额");
			headList.add("总额");
			headList.add("订单状态");
			headList.add("订单日期");
			
			String sql = "select e.* from Ec e where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();

			if (!"".equals(this.getParameter("customerInfo"))) {
				condition += " and e.customerInfo like :customerinfo";
				parms.put("customerinfo", "%"+this.getParameter("customerInfo")+"%");
			}
			if (!"".equals(this.getParameter("urlFlag"))) {
				condition += " and e.urlFlag =:urlflag";
				parms.put("urlflag", this.getParameter("urlFlag"));
			}
			if (!"".equals(this.getParameter("beginTime")) && !"".equals(this.getParameter("endTime"))){
				condition += " and e.addTime >= :bt and e.addTime <= :et ";
				parms.put("bt", this.getParameter("beginTime"));
				parms.put("et", this.getParameter("endTime"));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(Ec.class);
			
			List<Object[]> list = dao.findBySql(sql+condition, parms,cols);
			List<Object[]> datas = new ArrayList<Object[]>();
			for (Object object : list) {
				Object[] o = new Object[9];
				Ec ec = (Ec)object;
				o[0] = ec.getId();
				o[1] = ec.getCustomerInfo();
				o[2] = ec.getUrlFlag();
				o[3] = ec.getOrderId();
				o[4] = ec.getProductName();
				o[5] = ec.getAmount();
				o[6] = ec.getAllAmount();
				o[7] = ec.getOrderStatus();
				o[8] = ec.getAddTime();
				datas.add(o);
			}
			ExcelUtil.export(datas, headList, fileName, os);	
			this.buildAjaxResult(request, SUCCESS_STATUS, "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Data To Excel	table:	ec
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toExcel2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			String fileName = "单品成交";
			OutputStream os = response.getOutputStream();
			response.reset();
			fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			response.setContentType("application/msexcel;charset=UTF-8");
			
			List<String> headList = new ArrayList<String>();
			headList.add("单品名称");
			headList.add("成交数量");
			
			String hql = "from Ec e where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();

			if (!"".equals(this.getParameter("customerInfo"))) {
				condition += " and e.customerInfo like :customerinfo";
				parms.put("customerinfo", "%"+this.getParameter("customerInfo")+"%");
			}
			if (!"".equals(this.getParameter("urlFlag"))) {
				condition += " and e.urlFlag =:urlflag";
				parms.put("urlflag", this.getParameter("urlFlag"));
			}
			if (!"".equals(this.getParameter("beginTime")) && !"".equals(this.getParameter("endTime"))){
				condition += " and e.addTime >= :bt and e.addTime <= :et ";
				parms.put("bt", DateFormat.getDateInstance().parse(this.getParameter("beginTime")));
				parms.put("et", DateFormat.getDateInstance().parse(this.getParameter("endTime")));
			}
			
			List<Ec> list = dao.findAll(hql, condition, parms);
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < list.size(); i++){
				Ec ec = (Ec)list.get(i);
				sb.append(ec.getProductName());
			}
			sb.setLength(sb.length() - 1);
			
			String[] proArray = sb.toString().split("\\|\\|");
			HashMap<String,Integer> proMap = new HashMap<String,Integer>();
			for (int i = 0; i < proArray.length; i++){
				if (!"".equals(proArray[i])){
					if (!proMap.containsKey(proArray[i])){
						proMap.put(proArray[i], 1);
					} else {
						int countNum = proMap.get(proArray[i]);
						proMap.remove(proArray[i]);
						proMap.put(proArray[i], countNum+1);
					}
				}
			}
			
			List<Object[]> proList = new ArrayList<Object[]>();
			
			Iterator it = proMap.keySet().iterator();
			
			while (it.hasNext()){
				String key = (String)it.next();
				int value = proMap.get(key);
				
				Object[] o = new Object[2]; 
				o[0] = key;
				o[1] = value;
				proList.add(o);
			}
			
			ExcelUtil.export(proList, headList, fileName, os);	
			this.buildAjaxResult(request, SUCCESS_STATUS, "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
