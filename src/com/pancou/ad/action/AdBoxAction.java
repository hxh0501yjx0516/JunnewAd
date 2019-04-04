package com.pancou.ad.action;

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
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ReadyBoxUtil;
import com.pancou.ad.vo.AdBox;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.UrlAddress;

@Entity
public class AdBoxAction extends BaseDispatchAction{
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
		String sql ="select a.*,ai.adboxInfoName,s.adWidth,s.adHeight,m.modelName,u.url,w.webMasterName " +
					"from adBox a " +
					"left join adBoxInfo ai on a.adBoxInfoId = ai.adBoxInfoId " +
					"left join url u on a.urlId = u.urlId " +
					"left join webmaster w on a.webMasterId = w.webMasterId " +
					"left join adSize s on ai.adSizeId = s.adSizeId " +
					"left join models m on ai.modelId = m.modelId where ";
		Map<String,Object> parms=new HashMap<String, Object>();
		String condition = " a.adBoxFlag = :state";
		parms.put("state",1);
		if(!"".equals(this.getParameter("urlAddressId")) && 0!=Integer.parseInt(this.getParameter("urlAddressId"))){
			condition += " and a.urlId = :urlAddressId ";
			parms.put("urlAddressId", Integer.parseInt(this.getParameter("urlAddressId")));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdBox.class," and "+condition,parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by a.adBoxId desc",parms);
		List<AdBox> list = new ArrayList<AdBox>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			AdBox adBox = new AdBox();
			adBox.setAdBoxId(Integer.parseInt(object[0]+""));
			adBox.setAdBoxName(object[1]+"");
			adBox.setAdBoxInfoId(Integer.parseInt(object[2]+""));
			adBox.setWebMasterId(Integer.parseInt(object[3]+""));
			adBox.setUrlId(Integer.parseInt(object[4]+""));
			adBox.setAdPlanId(Integer.parseInt(object[5]+""));
			adBox.setAdPlanCycleId(Integer.parseInt(object[6]+""));
			adBox.setAdBoxStatus(Integer.parseInt(object[7]+""));
			adBox.setAdBoxFlag(Integer.parseInt(object[8]+""));
			adBox.setAddTime(object[9]+"");
			adBox.setIsURLBand(Integer.parseInt((object[10]+"")));
			adBox.setAdBoxInfoName(object[11]+"");
			adBox.setSizeName(object[12]+"x"+object[13]);
			adBox.setModelName(object[14]+"");
			adBox.setUrlName(object[15]+"");
			adBox.setWebMasterName(object[16]+"");
			list.add(adBox);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		} 
		String strWebMasterId = this.getParameter("webMasterId");
		if("".equals(strWebMasterId)){
			UrlAddress urlAddress = (UrlAddress)dao.findById(UrlAddress.class, Integer.parseInt(this.getParameter("urlAddressId")));
			strWebMasterId = urlAddress.getWebMasterId()+"";
		}
		request.setAttribute("webMasterId",strWebMasterId);
		request.setAttribute("urlAddressId", this.getParameter("urlAddressId"));
		return mapping.findForward("list");
	}
	@SuppressWarnings("unchecked")
	/**
	 * 默认广告位
	 */
	public ActionForward defaultList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 20;//每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String sql ="select a.*,ai.adboxInfoName,s.adWidth,s.adHeight,m.modelName " +
					"from adBox a " +
					"left join adBoxInfo ai on a.adBoxInfoId = ai.adBoxInfoId " +
					"left join adSize s on ai.adSizeId = s.adSizeId " +
					"left join models m on ai.modelId = m.modelId where  ";
		String condition = " a.adBoxFlag = :state ";
		Map<String,Object> parms=new HashMap<String, Object>();
		parms.put("state",0);
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(AdBox.class," and "+condition,parms));
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by a.adBoxId desc",parms);
		List<AdBox> list = new ArrayList<AdBox>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			AdBox adBox = new AdBox();
			adBox.setAdBoxId(Integer.parseInt(object[0]+""));
			adBox.setAdBoxName(object[1]+"");
			adBox.setAdBoxInfoId(Integer.parseInt(object[2]+""));
			adBox.setWebMasterId(Integer.parseInt(object[3]+""));
			adBox.setUrlId(Integer.parseInt(object[4]+""));
			adBox.setAdPlanId(Integer.parseInt(object[5]+""));
			adBox.setAdPlanCycleId(Integer.parseInt(object[6]+""));
			adBox.setAdBoxStatus(Integer.parseInt(object[7]+""));
			adBox.setAdBoxFlag(Integer.parseInt(object[8]+""));
			adBox.setAddTime(object[9]+"");
			adBox.setIsURLBand(Integer.parseInt((object[10]+"")));
			adBox.setAdBoxInfoName(object[11]+"");
			adBox.setSizeName(object[12]+"x"+object[13]);
			adBox.setModelName(object[14]+"");
			list.add(adBox);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		return mapping.findForward("defaultList");
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
			AdBox adBox = new AdBox();
			if(!"".equals(this.getParameter("urlAddressId"))){
				adBox.setWebMasterId(Integer.parseInt(this.getParameter("webMasterId")));
				adBox.setUrlId(Integer.parseInt(this.getParameter("urlAddressId")));
				adBox.setAdBoxFlag(1);
				adBox.setIsURLBand(Integer.parseInt(this.getParameter("urlBandFlag")));
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"adBoxList");
			}else{
				adBox.setAdBoxFlag(0);
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"默认广告位");
			}
			adBox.setAdBoxName(this.getParameter("adBoxName"));
			adBox.setAdBoxInfoId(Integer.parseInt(this.getParameter("adBoxInfoId")));
			adBox.setAdBoxStatus(Integer.parseInt(this.getParameter("adBoxState")));
			adBox.setAddTime(DatetimeHandle.formatCurrentDate());
			dao.save(adBox);
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getAdBoxInfoList(request);
		request.setAttribute("webMasterId", this.getParameter("webMasterId"));
		request.setAttribute("urlAddressId", this.getParameter("urlAddressId"));
		if(!"".equals(this.getParameter("urlAddressId")) && 0!=Integer.parseInt(this.getParameter("urlAddressId"))){
			UrlAddress urlAddress = (UrlAddress)dao.findById(UrlAddress.class, Integer.parseInt(this.getParameter("urlAddressId")));
			request.setAttribute("urlAddressName",urlAddress.getUrl());
		}
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
			AdBox adBox = (AdBox)dao.findById(AdBox.class, Integer.parseInt(this.getParameter("adBoxId")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("adBoxId", adBox.getAdBoxId());
			params.put("remarks","修改广告位ID为"+this.getParameter("adBoxId")+"的状态,广告位状态由0-正常变为1-锁定");
			params.put("operationSource","/adbox.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				adBox.setAdBoxStatus(Integer.parseInt(this.getParameter("adBoxState")));
				if(Integer.parseInt(this.getParameter("adBoxState")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				if(dao.update(adBox))
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			}else if ("editFlag".equals(this.getParameter("flag"))) {
				//域名绑定
				System.out.println("修改前的广告位域名绑定值="+adBox.getIsURLBand());
				adBox.setIsURLBand(Integer.parseInt(this.getParameter("urlBandFlag")));
				dao.update(adBox);
				//#############################start####################################
				//更新readyBox广告位域名绑定和域名列表
				String urlList = "";
				System.out.println("修改后的广告位域名绑定值="+adBox.getIsURLBand());
				if(adBox.getIsURLBand() == 1){
					//绑定域名,查询该网站主有效域名
					String[] pname = new String[2];
					String[] pvalue = new String[2];
					pname[0] = "webMasterId";
					pname[1] = "urlStatus";
					pvalue[0] = adBox.getWebMasterId()+"";
					pvalue[1] = "1";
					List<UrlAddress> urlAddressList = dao.findAll(UrlAddress.class, pname, pvalue);
					for(int i=0;i<urlAddressList.size();i++){
						UrlAddress urlAddress = urlAddressList.get(i);
						if(i == urlAddressList.size()-1){
							urlList += urlAddress.getUrl();
						}else{
							urlList += urlAddress.getUrl()+"|";
						}
					}
				}
				String[] pname1 = new String[1];
				String[] pvalue1 = new String[1];
				pname1[0] = "adBoxId";
				pvalue1[0] = adBox.getAdBoxId()+"";
				List<ReadyBox> readyBoxList = dao.findAll(ReadyBox.class, pname1, pvalue1);
				for(ReadyBox readyBox:readyBoxList){
					readyBox.setIsUrlBand(adBox.getIsURLBand());
					readyBox.setUrlList(urlList);
					if(dao.update(readyBox))
						{
						ReadyBoxUtil rb=new ReadyBoxUtil();
						rb.saveToMemCached(readyBox);  //add by yu
						}
				}
				//#############################end####################################
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			}else if("edit".equals(this.getParameter("flag"))) {
				this.getAdBoxInfoList(request);
				if(adBox.getAdBoxFlag() == 1){
					//正常广告位
					UrlAddress urlAddress = (UrlAddress)dao.findById(UrlAddress.class,adBox.getUrlId());
					request.setAttribute("urlAddressName",urlAddress.getUrl());
				}
				request.setAttribute("adBox",adBox);
				return mapping.findForward("edit");
			}else{
				if(adBox.getAdBoxFlag() == 1){//正常广告位
					adBox.setIsURLBand(Integer.parseInt(this.getParameter("urlBandFlag")));
					this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"adBoxList");
				//#############################start####################################
				//更新readyBox广告位域名绑定和域名列表
				String urlList = "";
				if(Integer.parseInt(this.getParameter("urlBandFlag")) == 1){
					//绑定域名,查询该网站主有效域名
					String[] pname = new String[2];
					String[] pvalue = new String[2];
					pname[0] = "webMasterId";
					pname[1] = "urlStatus";
					pvalue[0] = adBox.getWebMasterId()+"";
					pvalue[1] = "1";
					List<UrlAddress> urlAddressList = dao.findAll(UrlAddress.class, pname, pvalue);
					for(int i=0;i<urlAddressList.size();i++){
						UrlAddress urlAddress = urlAddressList.get(i);
						if(i == urlAddressList.size()-1){
							urlList += urlAddress.getUrl();
						}else{
							urlList += urlAddress.getUrl()+"|";
						}
					}
				}
				String[] pname1 = new String[1];
				String[] pvalue1 = new String[1];
				pname1[0] = "adBoxId";
				pvalue1[0] = adBox.getAdBoxId()+"";
				List<ReadyBox> readyBoxList = dao.findAll(ReadyBox.class, pname1, pvalue1);
				for(ReadyBox readyBox:readyBoxList){
					readyBox.setIsUrlBand(Integer.parseInt(this.getParameter("urlBandFlag")));
					readyBox.setUrlList(urlList);
					if(dao.update(readyBox)){
						if(Integer.parseInt(this.getParameter("adBoxState")) != 1){
							//1.绑定改变，状态不为锁定
							//2.状态为锁定，投放列表该广告位创意不为锁定
							//3.状态为锁定，投放列表该广告位创意已经为锁定，此时没有添加缓存
							ReadyBoxUtil rb=new ReadyBoxUtil();
							rb.saveToMemCached(readyBox);  //add by yu
						}
					}
				}
				//#############################end####################################
				if(Integer.parseInt(this.getParameter("adBoxState")) == 1){//锁定
					this.updateReadyBox(request,params);
				}
				}else{
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！", CALLBACKTYPE_CLOSECURRENT,"默认广告位");
				}
				adBox.setAdBoxName(this.getParameter("adBoxName"));
				adBox.setAdBoxInfoId(Integer.parseInt(this.getParameter("adBoxInfoId")));
				adBox.setAdBoxStatus(Integer.parseInt(this.getParameter("adBoxState")));
				if(!dao.update(adBox))
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
}
