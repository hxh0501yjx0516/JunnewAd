package com.pancou.ad.action;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.pancou.ad.form.ShopForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ShopTemplete;
import com.pancou.ad.vo.Shop;
import com.pancou.ad.vo.ShopFclass;
import com.pancou.ad.vo.ShopIndex;
import com.pancou.ad.vo.ShopSclass;


/**
 * 
 * @author Administrator
 *
 */
@Entity
public class ShopAction extends BaseDispatchAction {
	
	
	/**
	 * �༭�ƹ�ҳ��ַ	table: ShopIndex
	 * @param mapping
	 * @param form
	 * @param resquest
	 * @param response
	 * @return
	 */
	public ActionForward editShopIndex(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			int shopIndexId = 1; 	//��ǰ�༭ID ����һ��
			ShopIndex si = (ShopIndex)dao.findById(ShopIndex.class, shopIndexId);
			if(!"edit".equals(this.getParameter("flag"))){
				request.setAttribute("shopindex",si);
				return mapping.findForward("editShopIndex");
			}else{
				String shopIndexName = this.getParameter("shopindexname");
				String shopIndexUrl = this.getParameter("shopindexurl");
				si.setShopIndexName(shopIndexName);
				si.setShopIndexUrl(shopIndexUrl);
				dao.update(si);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���", "closeCurrent");
			}
		}catch (Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
	
	/**
	 * �����б�	table:Shop
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			//��ҳ
			int pageNum = 0;
			int numPerPage = 20;
			if ("pager".equals(this.getParameter("flag"))) {
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			//��ȡ��ѯ
			String shopName = this.getParameter("shopname");
			
			//��ѯ
			String strsql = "select s.* from shop s where 1=1";
			String condition = "";
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(shopName)){
				condition += " and s.shopName like :shopname";
				parms.put("shopname", "%"+shopName+"%");
			}
			if (!"".equals(this.getParameter("shopfclassid"))){
				condition += " and s.shopFclassId =:shopfclassid";
				parms.put("shopfclassid", Integer.parseInt(this.getParameter("shopfclassid")));
			}
			if (!"".equals(this.getParameter("shopsclassid"))){
				condition += " and s.shopSclassId =:shopsclassid";
				parms.put("shopsclassid", Integer.parseInt(this.getParameter("shopsclassid")));
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(Shop.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(Shop.class, condition, parms));
			List<Shop> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), strsql + condition, parms, cols);
			
			if (list != null){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		this.getShopFclassList(request);
		this.getShopSclassList(request);
		request.setAttribute("shopname", this.getParameter("shopname"));
		request.setAttribute("shopfclassid", this.getParameter("shopfclassid"));
		request.setAttribute("shopsclassid", this.getParameter("shopsclassid"));
		return mapping.findForward("list");
	}
	
	/**
	 * ������� 	table:Shop
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward add(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			if ("save".equals(this.getParameter("flag"))){
				//��ȡ�?
				String shopName = this.getParameter("shopname");
				String shopUrl = this.getParameter("shopurl");
				String fClassId = this.getParameter("shopfclassid");
				String sClassId = this.getParameter("shopsclassid");
				float shopPrice = Float.parseFloat(this.getParameter("shopprice"));
				float shopNewPrice = Float.parseFloat(this.getParameter("shopnewprice"));
				String shopRemarks = this.getParameter("shopremarks");
				String addTime = DatetimeHandle.formatCurrentDate();
				
				String[] pname = {"shopName","shopFclassId","shopSclassId"};
				String[] pvalue = {shopName,fClassId,sClassId};
				List<Shop> list = dao.findAll(Shop.class, pname, pvalue);
				if (list != null & list.size() > 0){
					this.buildAjaxResult(request, this.FAILURE_STATUS, "�����Ѵ���");
					return mapping.findForward("upload");
				}
					Shop shop = new Shop();
					ShopForm shopForm = (ShopForm)form;
					FormFile formFile = shopForm.getFormFile();
					String path = "";
					if(formFile !=null && !"".equals(formFile.getFileName())){
						if(!FileManager.isPhoto(formFile.getFileName())){
							this.buildAjaxResult(request, FAILURE_STATUS, "�ϴ��ļ�����ӦΪjpeg|jpg|gif|png|swf��");
							return mapping.findForward("upload");
						}
						Calendar cal = Calendar.getInstance();
						int year = cal.get(Calendar.YEAR );
						int month = cal.get(Calendar.MONTH )+1;
						path = FileManager.uploadFile(request,formFile,FileManager.UPLOAD_SHOP_PATH+ File.separator+year+File.separator+month,false);
						shop.setShopImg(path);
						if("".equals(path)){
							this.buildAjaxResult(request, FAILURE_STATUS, "�ļ��ϴ�ʧ�ܣ�");
							return mapping.findForward("upload");
						}
					}
					shop.setShopFclassId(Integer.parseInt(fClassId));
					shop.setShopSclassId(Integer.parseInt(sClassId));
					shop.setShopName(shopName);
					shop.setShopUrl(shopUrl);
					shop.setShopPrice(shopPrice);
					shop.setShopNewPrice(shopNewPrice);
					shop.setShopRemarks(shopRemarks);
					shop.setAddTime(addTime);
					dao.save(shop);
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "��ӳɹ�",this.CALLBACKTYPE_CLOSECURRENT,"�����б�");
					return mapping.findForward("upload");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		this.getShopFclassList(request);
		this.getShopSclassList(request);
		return mapping.findForward("add");
	}
	
	/**
	 * ���ݱ༭	table:Shop
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward edit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			int shopId = Integer.parseInt(this.getParameter("shopid"));	
			Shop shop = (Shop)dao.findById(Shop.class, shopId);
			
			String source = this.getParameter("source");
			if ("save".equals(this.getParameter("flag"))){
				//��ȡ�?
				String shopName = this.getParameter("shopname");
				String shopUrl = this.getParameter("shopurl");
				String fClassId = this.getParameter("shopfclassid");
				String sClassId = this.getParameter("shopsclassid");
				float shopPrice = Float.parseFloat(this.getParameter("shopprice"));
				float shopNewPrice = Float.parseFloat(this.getParameter("shopnewprice"));
				String shopRemarks = this.getParameter("shopremarks");
				String addTime = DatetimeHandle.formatCurrentDate();
				String[] pname = {"shopName","shopFclassId","shopSclassId"};
				String[] pvalue = {shopName,fClassId,sClassId};
				List<Shop> list = dao.findAll(Shop.class, pname, pvalue);
				if (list != null & list.size() > 0){
					if(list.get(0).getShopId() != shopId){
					this.buildAjaxResult(request, this.FAILURE_STATUS, "�����Ѵ���");
					return mapping.findForward("upload");
				}
				}
					ShopForm shopForm = (ShopForm)form;
					FormFile formFile = shopForm.getFormFile();
					String path = "";
					if(formFile !=null && !"".equals(formFile.getFileName())){
						if(!FileManager.isPhoto(formFile.getFileName())){
							this.buildAjaxResult(request, FAILURE_STATUS, "�ϴ��ļ�����ӦΪjpeg|jpg|gif|png|swf��");
							return mapping.findForward("upload");
						}
						Calendar cal = Calendar.getInstance();
						int year = cal.get(Calendar.YEAR );
						int month = cal.get(Calendar.MONTH )+1;
						path = FileManager.uploadFile(request,formFile,FileManager.UPLOAD_SHOP_PATH+ File.separator+year+File.separator+month,false);
						shop.setShopImg(path);
						if("".equals(path)){
							this.buildAjaxResult(request, FAILURE_STATUS, "�ļ��ϴ�ʧ�ܣ�");
							return mapping.findForward("upload");
						}
					}
				shop.setShopFclassId(Integer.parseInt(fClassId));
				shop.setShopSclassId(Integer.parseInt(sClassId));
				shop.setShopName(shopName);
				shop.setShopUrl(shopUrl);
				shop.setShopPrice(shopPrice);
				shop.setShopNewPrice(shopNewPrice);
				shop.setShopRemarks(shopRemarks);
				shop.setAddTime(addTime);
				dao.update(shop);
				if("view".equals(source)){
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�",this.CALLBACKTYPE_CLOSECURRENT,"���ӻ��༭");
				}else{
					this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�",this.CALLBACKTYPE_CLOSECURRENT,"�����б�");
				}
				return mapping.findForward("upload");
			} else {
				if(null != shop.getShopImg() && !"".equals(shop.getShopImg())){
					shop.setShopImg(Configure.get("img.url")+shop.getShopImg());
				}
				request.setAttribute("source", source);
				request.setAttribute("shop", shop);
			}
			
		} catch (Exception e){
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
			return mapping.findForward("upload");
		}
		this.getShopFclassList(request);
		this.getShopSclassList(request);
		return mapping.findForward("edit");
	}
	/**
	 * ��ȡ����
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<ShopFclass> fClassList = dao.findAll(ShopFclass.class);
			for(ShopFclass fClass : fClassList){
				Map<String,Object> parms = new HashMap<String, Object>();
				parms.put("fClassId", fClass.getShopFclassId());
				List<ShopSclass> sClassList = dao.findAll("from ShopSclass ", " where shopFclassId =:fClassId order by shopSclassId ", parms);
				fClass.setShopSclassList(sClassList);
				for(ShopSclass sClass : sClassList){
					Map<String,Object> parms1 = new HashMap<String, Object>();
					parms1.put("sClassId", sClass.getShopSclassId());
					List<Shop> shopList = dao.findAll("from Shop ", " where shopSclassId =:sClassId order by shopId ", parms1);
					for(Shop shop:shopList){
						shop.setShopImg(Configure.get("img.url")+shop.getShopImg());
					}
					sClass.setShopList(shopList);
				}
			}
			request.setAttribute("fClassList", fClassList);
		}catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("view");
	}
	/**
	 * ��ȡ����
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	public ActionForward getcode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
				ShopTemplete templete = new ShopTemplete();
				//����html��̬ҳģ��
				templete.createTemplete(request, dao);
			    String uid = this.getParameter("shopwebid");
				String url = Configure.get("shop.extend.js");
				String codeAddress = "<script id='junnew' src='"+url+"?mid="+uid+"'></script>";
				request.setAttribute("codeAddress",codeAddress);
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���");
		}catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("getcode");
	}
}
