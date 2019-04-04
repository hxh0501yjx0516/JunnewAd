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
import com.pancou.ad.util.MD5;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.ViewAdBoxCount;
import com.pancou.ad.vo.WebMaster;

@Entity
public class WebMasterAction extends BaseDispatchAction{
	
	/**
	 * 站长列表	table: WebMaster
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 20;//每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sql = "select w.*,u.realName,planCount=(select count(*) from readyplan where webMasterId = w.webMasterId),urlCount=(select count(*) from url where webMasterId = w.webMasterId) " +
				"from webmaster w " +
				"left join users u on w.userid = u.userid where 1=1 ";
		Map<String,Object> parms = new HashMap<String, Object>();
		String condition = "";
		if(!"".equals(this.getParameter("qryname"))){
			condition +=" and w.webMasterName like :name ";
			parms.put("name","%"+this.getParameter("qryname")+"%");
		}
		if(!"".equals(this.getParameter("qrystate"))){
			condition +=" and w.webMasterStatus =:state ";
			parms.put("state",Integer.parseInt(this.getParameter("qrystate")));
		}
		if("0".equals(this.getParameter("qryuserid"))){	//站长是否分配了媒介
			condition += " and w.userId = 0";
		} else {
			condition += " and w.userId <> 0";
		}
		if (!"".equals(this.getParameter("userId"))){
			condition += " and w.userId = :userId";
			parms.put("userId", Integer.parseInt(this.getParameter("userId")));
		}
		
		/**
		 *媒介管理--站长列表
		 */
		Users user = (Users)request.getSession().getAttribute("user");
		if("media".equals(this.getParameter("searchtype"))){
			if("0".equals(this.getParameter("qryuserid"))){	//站长是否分配了媒介
				condition += " and w.userId = 0";
			} else {
				condition += " and w.userId =:userid";
				parms.put("userid", user.getUserId());
			}
		}
		if(user.getUserGroup()!=0){
			condition += " and w.userGroup =:userGroup";
			parms.put("userGroup", user.getUserGroup());
		}else{
			if(!"".equals(this.getParameter("qryuserGroup"))){
				condition +=" and w.userGroup =:userGroup ";
				parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
			}
			this.getUserGroupList(request);
		}
		
		
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(WebMaster.class, condition,parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by addTime desc, planCount desc",parms);
		List<WebMaster> list = new ArrayList<WebMaster>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			WebMaster webMaster = new WebMaster();
			webMaster.setWebMasterId(Integer.parseInt(object[0]+""));
			webMaster.setWebMasterName(object[1]+"");
			webMaster.setWebMasterPassWord(object[2]+"");
			webMaster.setWebMasterContactName(object[3]+"");
			webMaster.setWebMasterCard(object[4]+"");
			webMaster.setWebMasterAddress(object[5]+"");
			webMaster.setWebMasterPost(object[6]+"");
			webMaster.setWebMasterMobile(object[7]+"");
			webMaster.setWebMasterQQ(object[8]+"");
			webMaster.setWebMasterBank(object[9]+"");
			webMaster.setWebMasterBankName(object[10]+"");
			webMaster.setWebMasterBankNumber(object[11]+"");
			webMaster.setWebMasterStatus(Integer.parseInt(object[12]+""));
			webMaster.setAddTime(object[13]+"");
			webMaster.setUserId(Integer.parseInt(object[14]+""));
			webMaster.setUserName(object[16]+"");
			webMaster.setPlanCount(Integer.parseInt(object[17]+""));
			webMaster.setUrlCount(Integer.parseInt(object[18]+""));
			list.add(webMaster);
		}
		if (list != null ) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("qryname",this.getParameter("qryname"));
		request.setAttribute("qrystate",this.getParameter("qrystate"));
		request.setAttribute("qryuserid",this.getParameter("qryuserid"));
		request.setAttribute("searchtype", this.getParameter("searchtype"));
		request.setAttribute("userGroup", user.getUserGroup());
		request.setAttribute("qryuserGroup", this.getParameter("qryuserGroup"));
		this.getUsersList(request);
		request.setAttribute("userId", this.getParameter("userId"));
		return mapping.findForward("list");
	}
	/**
	 * 添加页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			// 添加
			String[] pname = new String[1];
			String[] pvalue = new String[1];
			pname[0] = "webmasterName";
			pvalue[0] =this.getParameter("name");
			List userslist = dao.findAll(WebMaster.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// 该用户名已存在
				this.buildAjaxResult(request, this.FAILURE_STATUS, "该用户名已存在!","closeCurrent");
				return mapping.findForward("ajaxDone");
			}
			Users users = (Users)request.getSession().getAttribute("user");
			WebMaster webMaster = new WebMaster();
			webMaster.setWebMasterName(this.getParameter("name"));
			webMaster.setWebMasterPassWord(MD5.MD5Encode(this.getParameter("password")));
			webMaster.setWebMasterContactName(this.getParameter("contactName"));
			webMaster.setWebMasterCard(this.getParameter("card"));
			webMaster.setWebMasterAddress(this.getParameter("address"));
			webMaster.setWebMasterPost(this.getParameter("post"));
			webMaster.setWebMasterMobile( this.getParameter("phone"));
			webMaster.setWebMasterQQ(this.getParameter("qq"));
			webMaster.setWebMasterBank(this.getParameter("bankAddress").trim().replace(" ", ""));
			webMaster.setWebMasterBankName( this.getParameter("bankName").trim().replace(" ", ""));
			webMaster.setWebMasterBankNumber( this.getParameter("bankNumber").trim().replace(" ", ""));
			webMaster.setWebMasterStatus(Integer.parseInt(this.getParameter("state")));
			webMaster.setUserId(Integer.parseInt(this.getParameter("medium")));
			webMaster.setAddTime(DatetimeHandle.formatCurrentDate());
			webMaster.setUserGroup(users.getUserGroup());//添加用户组
			try {
				dao.save(webMaster);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！","closeCurrent","站长列表");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getUsersList(request);
		request.setAttribute("addTime",DatetimeHandle.formatCurrentDate());
		request.setAttribute("lastLoginTime",DatetimeHandle.formatCurrentDate());
		request.setAttribute("lastLoginIp",request.getLocalAddr());
		return mapping.findForward("add");
	}
	/**
	 * 修改页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			WebMaster webMaster = (WebMaster)dao.findById(WebMaster.class, Integer.parseInt(this.getParameter("cid")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("webMasterId", webMaster.getWebMasterId());
			params.put("remarks","修改站长ID为"+this.getParameter("cid")+"的状态,站长状态由0-正常变为1-锁定");
			params.put("operationSource","/webmaster.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				webMaster.setWebMasterStatus(Integer.parseInt(this.getParameter("state")));
				dao.update(webMaster);
				if(Integer.parseInt(this.getParameter("state")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
			}else if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("webMaster",webMaster);
				this.getUsersList(request);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "webMasterName";
				pvalue[0] = this.getParameter("name");
				List userslist = dao.findAll(WebMaster.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("cid")) != ((WebMaster)userslist.get(0)).getWebMasterId()){
					// 该用户名已存在
						this.buildAjaxResult(request, this.FAILURE_STATUS, "该用户名已存在!");
					return mapping.findForward("ajaxDone");
					}
				}
				String[] pname1 = new String[2];
				String[] pvalue1 = new String[2];
				pname1[0] = "webmasterId";
				pvalue1[0] =this.getParameter("cid");
				pname1[1] = "webMasterPassWord";
				pvalue1[1] =this.getParameter("password");
				List webList = dao.findAll(WebMaster.class, pname1, pvalue1);
				if (webList != null && webList.size() <= 0) {
					webMaster.setWebMasterPassWord(MD5.MD5Encode(this.getParameter("password")));
				}
				webMaster.setWebMasterName(this.getParameter("name"));
				webMaster.setWebMasterContactName(this.getParameter("contactName"));
				webMaster.setWebMasterCard(this.getParameter("card"));
				webMaster.setWebMasterAddress(this.getParameter("address"));
				webMaster.setWebMasterPost(this.getParameter("post"));
				webMaster.setWebMasterMobile(this.getParameter("phone"));
				webMaster.setWebMasterQQ(this.getParameter("qq"));
				webMaster.setWebMasterBank(this.getParameter("bankAddress").trim().replace(" ", ""));
				webMaster.setWebMasterBankName(this.getParameter("bankName").trim().replace(" ", ""));
				webMaster.setWebMasterBankNumber(this.getParameter("bankNumber").trim().replace(" ", ""));
				webMaster.setUserId(Integer.parseInt(this.getParameter("medium")));
				webMaster.setWebMasterStatus(Integer.parseInt(this.getParameter("state")));
				dao.update(webMaster);
				if(Integer.parseInt(this.getParameter("state")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！","closeCurrent","站长列表");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
	/**
	 * 删除页面
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			WebMaster webMaster = (WebMaster)dao.findById(Customer.class, Integer.parseInt(this.getParameter("cid")));
			dao.delete(webMaster);
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
	/**
	 * 站长列表导出  table:webmaster
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward webmasterToExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//System.out.println(this.getParameter("qryname"));
		try {
			String fileName = "站长列表";
			  OutputStream os = response.getOutputStream();//取得输出流
			  response.reset();//清空输出流
			  fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
//			  URLDecoder.decode(fileName ,"utf-8");
			  response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");//设定输出文件头
			  response.setContentType("application/msexcel;charset=UTF-8");//定义输出类型


				List<String> headerList = new ArrayList<String>();
				headerList.add("编号");      
				headerList.add("站长");
				headerList.add("联系人");  
				headerList.add("身份证");
				headerList.add("地址"); 
				headerList.add("邮编");  
			    headerList.add("电话");         
			    headerList.add("QQ/MSN");           
			    headerList.add("开户银行");             
			    headerList.add("开户名称");             
			    headerList.add("银行帐号");
			    headerList.add("添加时间");
			    
			    
			    String sqlString = "from WebMaster w where 1 = 1";
				String condition = " ";
				Map<String,Object> parms = new HashMap<String,Object>();
				if (!"".equals(this.getParameter("qryname"))){
					condition +=" and w.webMasterName like :name ";
					parms.put("name","%"+this.getParameter("qryname")+"%");
				}
				if (!"".equals(this.getParameter("qrystate"))){
					condition += " and webMasterStatus=:qrystate";
					parms.put("qrystate",Integer.parseInt(this.getParameter("qrystate")));
				}
				if("0".equals(this.getParameter("qryuserid"))){	//站长是否分配了媒介
					condition += " and w.userId = 0";
				} else {
					condition += " and w.userId <> 0";
				}
				
				/**
				 *媒介管理--站长列表
				 */
				Users user = (Users)request.getSession().getAttribute("user");
				if("media".equals(this.getParameter("searchtype"))){
					if("0".equals(this.getParameter("qryuserid"))){	//站长是否分配了媒介
						condition += " and w.userId = 0";
					} else {
						condition += " and w.userId =:userid";
						parms.put("userid", user.getUserId());
					}
				}
				if(user.getUserGroup()!=0){
					condition += " and w.userGroup =:userGroup";
					parms.put("userGroup", user.getUserGroup());
				}else{
					//管理员
					if(!"".equals(this.getParameter("qryuserGroup"))){
						condition +=" and w.userGroup =:userGroup ";
						parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
					}
				}
				condition += " order by webMasterId";				

				List<WebMaster> list = dao.findAll(sqlString, condition, parms);
				List<Object[]> datas = new ArrayList<Object[]>();
				DecimalFormat df = new DecimalFormat("##,###.###");
				for(WebMaster webMaster : list){
					Object[] object = new Object[12];
					object[0]=webMaster.getWebMasterId();
					object[1]=webMaster.getWebMasterName();
					object[2]=webMaster.getWebMasterContactName();
					object[3]=webMaster.getWebMasterCard();
					object[4]=webMaster.getWebMasterAddress();
					object[5]=webMaster.getWebMasterPost();
					object[6]=webMaster.getWebMasterMobile();
					object[7]=webMaster.getWebMasterQQ();
					object[8]=webMaster.getWebMasterBank();
					object[9]=webMaster.getWebMasterBankName();
					object[10]=webMaster.getWebMasterBankNumber();
					object[11]=DatetimeHandle.formatDate(DatetimeHandle.parseDate(webMaster.getAddTime()),DatetimeHandle.SHORT_TIME_FORMAT_STRING);
					datas.add(object);
				}
				ExcelUtil.export(datas,headerList,fileName,os);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
		}
	
}
