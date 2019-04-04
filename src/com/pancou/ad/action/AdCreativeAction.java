package com.pancou.ad.action;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.apache.struts.upload.FormFile;

import com.pancou.ad.form.AdCreativeForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ReadyBoxUtil;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdCreativeLog;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.UrlAllowabled;
import com.pancou.ad.vo.Users;

@Entity
public class AdCreativeAction extends BaseDispatchAction{
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 50;//每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sql ="select a.*,ap.adPlanName adPlanName,apc.adPlanCycleName adPlanCycleName,ads.adWidth adWidth,ads.adHeight adHeight from adCreative a " +
				"left join adPlan ap on a.adPlanId = ap.adPlanId " +
				"left join adPlanCycle apc on a.adPlanCycleId = apc.adPlanCycleId " +
				"left join adSize ads on a.adSizeId = ads.adSizeId where ";
		Map<String,Object> parms=new HashMap<String, Object>();
		String condition = " a.adPlanCycleId = :adPlanCycleId ";
		parms.put("adPlanCycleId",Integer.parseInt(this.getParameter("adPlanCycleId")));
		if(!"".equals(this.getParameter("qrydefault")) && 0!=Integer.parseInt(this.getParameter("qrydefault"))){
			condition += " and a.isDefault = :default ";
			parms.put("default", Integer.parseInt(this.getParameter("qrydefault")));
		}
		List<Object> cols = new ArrayList<Object>();
		cols.add(AdCreative.class);
		cols.add("adPlanName");
		cols.add("adPlanCycleName");
		cols.add("adWidth");
		cols.add("adHeight");
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdCreative.class," and "+condition,parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by a.adCreativeId desc",parms,cols);
		List<AdCreative> list = new ArrayList<AdCreative>();
		String url = Configure.get("img.url");
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			AdCreative adCreative = (AdCreative)object[0];
			String imgUrl = adCreative.getAdCreativeImg();
			if(null!=imgUrl && !"".equals(imgUrl)){
				adCreative.setAdCreativeImg(url+imgUrl);
			}
			adCreative.setAdPlanName(object[1]+"");
			adCreative.setAdPlanCycleName(object[2]+"");
			adCreative.setAdSizeName(object[3]+"x"+object[4]);
			list.add(adCreative);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		} 
		request.setAttribute("qrydefault", this.getParameter("qrydefault"));
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		request.setAttribute("adPlanCycleId", this.getParameter("adPlanCycleId"));
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			try {
			// 添加
				AdCreativeForm adCreativeForm = (AdCreativeForm)form;
				FormFile formFile = adCreativeForm.getFormFile();
				String path = "";
				String webadd = "";
				int isP4p = Integer.parseInt(this.getParameter("isP4p"));
				webadd = this.getParameter("adCreativeUrl").replace("#", "");
				if(isP4p == 0){//不是p4p
					webadd = this.getParameter("adCreativeUrl1");
					if(!webadd.startsWith("http://")){
						webadd = "http://"+webadd;
					}
				}
					AdCreativeLog adCreativeLog = new AdCreativeLog();
					adCreativeLog.setIp(this.getIpAddr(request));
					String path_str = request.getServletPath();  
					String param_str=request.getQueryString();  
					if(request.getQueryString() != null){  
					    path_str+= "?"+ param_str;  
					}
					adCreativeLog.setUrl(path_str);
					adCreativeLog.setLoginName(((Users)request.getSession().getAttribute("user")).getUsername());
					adCreativeLog.setStrSql(webadd);
					adCreativeLog.setProcTime(DatetimeHandle.formatCurrentDate());
					if(isP4p == 0){//不是p4p，正常创意判断创意白名单中是否存在
					/**判断创意路径是否合法（是否在白名单中存在）**********************start**/
						
//						String[] pname = new String[1];
//						String[] pvalue = new String[1];
//						pname[0] = "url";
//						pvalue[0] = getSubUrl(webadd);
//						
//						
//						List<UrlAllowabled> allowList = dao.findAll(UrlAllowabled.class, pname, pvalue);
//						if(allowList == null || allowList.size()==0){
//							System.out.println("#############判断创意路径#######################");
//							System.out.println("adCreativeUrl1 =="+this.getParameter("adCreativeUrl1"));
//							System.out.println("webadd表单提交数据 =="+webadd);
//							System.out.println("getSubUrl(webadd)截取后数据 =="+getSubUrl(webadd));
//							System.out.println("#############判断创意路径#######################");
//							this.buildAjaxResult(request, FAILURE_STATUS, "创意路径非法！");
//							adCreativeLog.setMemo("添加创意(创意路径非法)");
//							dao.save(adCreativeLog);
//							return mapping.findForward("upload");
//						}
					/**************************end*******************/
					}
					adCreativeLog.setMemo("添加创意");
					dao.save(adCreativeLog);
					
				if(formFile !=null && !"".equals(formFile.getFileName())){
					if(!FileManager.isPhoto(formFile.getFileName())){
					this.buildAjaxResult(request, FAILURE_STATUS, "上传文件类型应为jpeg|jpg|gif|png|swf！");
					return mapping.findForward("upload");
				}
				path = FileManager.uploadFile(request,formFile,"",false);
				if("".equals(path)){
					this.buildAjaxResult(request, FAILURE_STATUS, "文件上传失败！");
					return mapping.findForward("upload");
				}
				}
			AdCreative adCreative = new AdCreative();
			adCreative.setAdPlanId(Integer.parseInt(this.getParameter("adPlanId")));
			adCreative.setAdPlanCycleId(Integer.parseInt(this.getParameter("adPlanCycleId")));
			adCreative.setAdCreativeName(this.getParameter("adCreativeName"));
			adCreative.setAdSizeId(Integer.parseInt(this.getParameter("adSizeId")));
			adCreative.setAdCreativeImg(path);
			if(isP4p == 0){//不是p4p，正常创意判断创意白名单中是否存在
			adCreative.setAdCreativeUrl(webadd);
			}else{
			adCreative.setHtmlCode(webadd);
			}
			adCreative.setIsP4p(isP4p);
			adCreative.setAdCreativeStatus(Integer.parseInt(this.getParameter("adCreativeState")));
			adCreative.setAddTime(DatetimeHandle.formatCurrentDate());
			adCreative.setIsDefault(Integer.parseInt(this.getParameter("isDefault")));
			dao.save(adCreative);
			this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"广告创意列表");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("upload");
		}
		this.getAdSizeList(request);
		if(!"".equals(this.getParameter("adPlanId"))){
			AdPlan adPlan = (AdPlan)dao.findById(AdPlan.class, Integer.parseInt(this.getParameter("adPlanId")));
			request.setAttribute("adPlanName",adPlan.getAdPlanName());
		}if(!"".equals(this.getParameter("adPlanCycleId"))){
			AdPlanCycle cycle = (AdPlanCycle)dao.findById(AdPlanCycle.class,Integer.parseInt(this.getParameter("adPlanCycleId")));
			if(cycle.getAdPlanIsParameter() == 0){
				//传参
				request.setAttribute("adPlanCycleUrl",cycle.getAdPlanCycleUrl());
				
			}
		}
		request.setAttribute("adPlanId", this.getParameter("adPlanId"));
		request.setAttribute("adPlanCycleId", this.getParameter("adPlanCycleId"));
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
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			AdCreative adCreative = (AdCreative)dao.findById(AdCreative.class, Integer.parseInt(this.getParameter("adCreativeId")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("adCreativeId",adCreative.getAdCreativeId());
			params.put("remarks","修改广告创意ID为"+this.getParameter("adCreativeId")+"的状态,广告创意状态由0-正常变为1-锁定");
			params.put("operationSource","/adcreative.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				adCreative.setAdCreativeStatus(Integer.parseInt(this.getParameter("adCreativeState")));
				if(Integer.parseInt(this.getParameter("adCreativeState")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				if(dao.update(adCreative))
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			}else if ("editFlag".equals(this.getParameter("flag"))) {
				adCreative.setIsDefault(Integer.parseInt(this.getParameter("defaultFlag")));
				if(dao.update(adCreative))
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			}else if("edit".equals(this.getParameter("flag"))) {
				this.getAdSizeList(request);
				if(!"".equals(adCreative.getAdPlanId())){
					AdPlan adPlan = (AdPlan)dao.findById(AdPlan.class, adCreative.getAdPlanId());
					request.setAttribute("adPlanName",adPlan.getAdPlanName());
				}
				String url = Configure.get("img.url");
				if(null!=adCreative.getAdCreativeImg() && !"".equals(adCreative.getAdCreativeImg())){
				adCreative.setAdCreativeImg(url+adCreative.getAdCreativeImg());
				}
				request.setAttribute("adCreative",adCreative);
				return mapping.findForward("edit");
			}else{
				AdCreativeForm adCreativeForm = (AdCreativeForm)form;
				FormFile formFile = adCreativeForm.getFormFile();
				String path = "";
				int isP4p = Integer.parseInt(this.getParameter("isP4p"));
				String webadd = this.getParameter("adCreativeUrl").replace("#", "");
				if(isP4p == 0){//不是p4p
					if(!webadd.startsWith("http://")){
						webadd = "http://"+webadd;
					}
				}
					AdCreativeLog adCreativeLog = new AdCreativeLog();
					adCreativeLog.setIp(this.getIpAddr(request));
					String path_str = request.getServletPath();  
					String param_str=request.getQueryString();  
					if(request.getQueryString() != null){  
					    path_str+= "?"+ param_str;  
					}  
					adCreativeLog.setUrl(path_str);
					adCreativeLog.setLoginName(((Users)request.getSession().getAttribute("user")).getUsername());
					adCreativeLog.setStrSql(webadd);
					adCreativeLog.setProcTime(DatetimeHandle.formatCurrentDate());
					if(isP4p == 0){//不是p4p
					/***判断创意路径是否合法（是否在白名单中存在）****/
//					String[] pname = new String[1];
//					String[] pvalue = new String[1];
//					pname[0] = "url";
//					pvalue[0] = getSubUrl(webadd);
//					List<UrlAllowabled> allowList = dao.findAll(UrlAllowabled.class, pname, pvalue);
//					if(allowList == null || allowList.size()==0){
//						this.buildAjaxResult(request, FAILURE_STATUS, "创意路径非法！");
//						adCreativeLog.setMemo("修改创意(创意路径非法)");
//						dao.save(adCreativeLog);
//						return mapping.findForward("upload");
//					}
					/*******************end***************************************/
					adCreative.setAdCreativeUrl(webadd);
					}else{
						adCreative.setHtmlCode(webadd);
					}
					adCreative.setIsP4p(isP4p);
					adCreativeLog.setMemo("修改创意");
					dao.save(adCreativeLog);
				if(formFile !=null && !"".equals(formFile.getFileName())){
//					if(!FileManager.isPhoto(formFile.getFileName())){
//						this.buildAjaxResult(request, FAILURE_STATUS, "上传文件类型应为jpeg|jpg|gif|png！");
//						return mapping.findForward("upload");
//					}
					path = FileManager.uploadFile(request,formFile,"",false);
					if("".equals(path)){
						this.buildAjaxResult(request, FAILURE_STATUS, "文件上传失败！");
						return mapping.findForward("upload");
					}
					adCreative.setAdCreativeImg(path);
				}
			adCreative.setAdCreativeName(this.getParameter("adCreativeName"));
			adCreative.setAdSizeId(Integer.parseInt(this.getParameter("adSizeId")));
			
			adCreative.setAdCreativeStatus(Integer.parseInt(this.getParameter("adCreativeState")));
			adCreative.setIsDefault(Integer.parseInt(this.getParameter("isDefault")));
				if(dao.update(adCreative)){
				//#############################start####################################
				//更新投放列表中的创意图片和路径
				String imgUrl ="";
//				System.out.println("更新后图片路径"+adCreative.getAdCreativeImg());
				if(adCreative.getAdCreativeImg() !=null && !"".equals(adCreative.getAdCreativeImg())){
					imgUrl =  Configure.get("img.url")+adCreative.getAdCreativeImg();
				}
				String[] pname1 = new String[1];
				String[] pvalue1 = new String[1];
				pname1[0] = "adCreativeId";
				pvalue1[0] = adCreative.getAdCreativeId()+"";
				List<ReadyBox> readyBoxList = dao.findAll(ReadyBox.class, pname1, pvalue1);
				for(ReadyBox readyBox:readyBoxList){
					readyBox.setAdCreativeImg(imgUrl);
					readyBox.setIsP4p(adCreative.getIsP4p());
					if(isP4p == 0){//不是p4p
						readyBox.setAdCreativeUrl(adCreative.getAdCreativeUrl());
						}else{
							readyBox.setHtmlCode(adCreative.getHtmlCode());
						}
					if(dao.update(readyBox)){
						if(Integer.parseInt(this.getParameter("adCreativeState")) != 1){
//							System.out.println("创意未锁定时添加缓存");
							ReadyBoxUtil rb=new ReadyBoxUtil();
							rb.saveToMemCached(readyBox);  //add by yu
						}
					}
				}
				//#############################end####################################
				}
				if(Integer.parseInt(this.getParameter("adCreativeState")) == 1){//锁定
//					System.out.println("创意锁定时添加缓存");
					this.updateReadyBox(request,params);
				}
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"广告创意列表");
				return mapping.findForward("upload");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
		
	}
	private String getSubUrl(String url){
		url = url.indexOf("?") != -1 ? url.substring(0,url.indexOf("?")):url.substring(4).lastIndexOf("http://") !=-1?url.substring(0,url.lastIndexOf("http://")):url;
		if(url.lastIndexOf(".com.cn") != -1){
			url = url.substring(0,url.lastIndexOf(".com.cn")+7);
		}else if(url.lastIndexOf(".net.cn") != -1){
			url = url.substring(0,url.lastIndexOf(".net.cn")+7);
		}else if(url.lastIndexOf(".gov.cn") != -1){
			url = url.substring(0,url.lastIndexOf(".gov.cn")+7);
		}else if(url.lastIndexOf(".org.cn") != -1){
			url = url.substring(0,url.lastIndexOf(".org.cn")+7);
		}else if(url.lastIndexOf(".com") != -1){
			url = url.substring(0,url.lastIndexOf(".com")+4);
		}else if(url.lastIndexOf(".cn") != -1){
			url = url.substring(0,url.lastIndexOf(".cn")+3);
		}else if(url.lastIndexOf(".net") != -1){
			url = url.substring(0,url.lastIndexOf(".net")+4);
		}else if(url.lastIndexOf(".mobi") != -1){
			url = url.substring(0,url.lastIndexOf(".mobi")+5);
		}else if(url.lastIndexOf(".so") != -1){
			url = url.substring(0,url.lastIndexOf(".com")+3);
		}else if(url.lastIndexOf(".net") != -1){
			url = url.substring(0,url.lastIndexOf(".net")+4);
		}else if(url.lastIndexOf(".org") != -1){
			url = url.substring(0,url.lastIndexOf(".org")+4);
		}else if(url.lastIndexOf(".name") != -1){
			url = url.substring(0,url.lastIndexOf(".name")+5);
		}else if(url.lastIndexOf(".me") != -1){
			url = url.substring(0,url.lastIndexOf(".me")+3);
		}else if(url.lastIndexOf(".co") != -1){
			url = url.substring(0,url.lastIndexOf(".co")+3);
		}else if(url.lastIndexOf(".tel") != -1){
			url = url.substring(0,url.lastIndexOf(".tel")+4);
		}else if(url.lastIndexOf(".info") != -1){
			url = url.substring(0,url.lastIndexOf(".info")+5);
		}else if(url.lastIndexOf(".biz") != -1){
			url = url.substring(0,url.lastIndexOf(".biz")+4);
		}else if(url.lastIndexOf(".cc") != -1){
			url = url.substring(0,url.lastIndexOf(".cc")+3);
		}else if(url.lastIndexOf(".tv") != -1){
			url = url.substring(0,url.lastIndexOf(".tv")+3);
		}else if(url.lastIndexOf(".hk") != -1){
			url = url.substring(0,url.lastIndexOf(".hk")+3);
		}else if(url.lastIndexOf(".asia") != -1){
			url = url.substring(0,url.lastIndexOf(".asia")+5);
		}else if(url.lastIndexOf(".公司") != -1){
			url = url.substring(0,url.lastIndexOf(".公司")+3);
		}else if(url.lastIndexOf(".网络") != -1){
			url = url.substring(0,url.lastIndexOf(".网络")+3);
		}else if(url.lastIndexOf(".中国") != -1){
			url = url.substring(0,url.lastIndexOf(".中国")+3);
		}
		return url;
	}
	public static void main(String[] args){
		System.out.println(URLDecoder.decode("chufa-%C2%99%2F"));
	}
}
