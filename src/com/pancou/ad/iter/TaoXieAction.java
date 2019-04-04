package com.pancou.ad.iter;

import java.sql.Timestamp;
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

import com.pancou.ad.form.TaoXieForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.ExcelUtilMe;
import com.pancou.ad.util.FileUploadUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.InterfaceTaoXie;

@Entity
public class TaoXieAction extends BaseDispatchAction {

	/**
	 * 淘鞋接口数据获取
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward get(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if ("save".equals(request.getParameter("flag"))){
			TaoXieForm taoXieForm = (TaoXieForm)form;
			FormFile formFile = taoXieForm.getFormFile();
			if (formFile != null){
				String filePath = FileUploadUtil.fileUpload(request, formFile, "upload");
				ArrayList<HashMap<Integer,String>> list = ExcelUtilMe.readExcel(filePath);
				HashMap<Integer,String> excelMap = null;
				for (int i = 0; i < list.size(); i++){
					excelMap = list.get(i);

					String flag = excelMap.get(0);
					String orderNumber = excelMap.get(1);
					String product = excelMap.get(2);
					Double productAmount = Double.parseDouble((excelMap.get(3)));
					int productNumber = Integer.parseInt(excelMap.get(4));
					Double postAmount = Double.parseDouble((excelMap.get(5)));
					Double productAmount2 = Double.parseDouble(excelMap.get(6));
					Double allAmount = Double.parseDouble(excelMap.get(7));
					String orderType = excelMap.get(8);
					String orderState = excelMap.get(9);
					Timestamp orderTime = Timestamp.valueOf(excelMap.get(10));
					
					//InterfaceTaoXie Entity
					InterfaceTaoXie tx = new InterfaceTaoXie();
					tx.setFlag(flag);
					tx.setOrderNumber(orderNumber);
					tx.setProduct(product);
					tx.setProductAmount(productAmount);
					tx.setProductNumber(productNumber);
					tx.setPostAmount(postAmount);
					tx.setProductAmount2(productAmount2);
					tx.setAllAmount(allAmount);
					tx.setOrderType(orderType);
					tx.setOrderState(orderState);
					tx.setOrderTime(orderTime);
					
					String[] pname = {"orderNumber"};
					String[] pvalue = {orderNumber};
					List tempList = dao.findAll(InterfaceTaoXie.class, pname, pvalue);
					if (tempList.size() > 0){
						InterfaceTaoXie txHaved = (InterfaceTaoXie)tempList.get(0);
						txHaved.setFlag(flag);
						//tx.setOrderNumber(orderNumber);
						txHaved.setProduct(product);
						txHaved.setProductAmount(productAmount);
						txHaved.setProductNumber(productNumber);
						txHaved.setPostAmount(postAmount);
						txHaved.setProductAmount2(productAmount2);
						txHaved.setAllAmount(allAmount);
						txHaved.setOrderType(orderType);
						txHaved.setOrderState(orderState);
						txHaved.setOrderTime(orderTime);
						try{
							dao.update(txHaved);
						} catch (Exception e){
							e.printStackTrace();
						}
						
						System.out.println("淘鞋数据上传：订单编号 " + orderNumber + " 记录已存在，已更新");
					} else {
						try{
							dao.save(tx);
						} catch (Exception e){
							e.printStackTrace();
						}
						System.out.println("淘鞋数据上传：订单编号 " + orderNumber + " 上传成功");
					}
				}
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
				return mapping.findForward("list");
			}
		}
		
		return mapping.findForward("get");
	}

	/**
	 * 淘鞋接口数据本地查询
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
		int pageNum = 0;
		int numPerPage = 100;
		if ("pager".equals(this.getParameter("flag"))) {
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		
		String hql = "from InterfaceTaoXie i where 1=1";
		String condition = "";
		String order = " order by i.id desc";
		Map<String,Object> parms = new HashMap<String,Object>();
		if (!"".equals(this.getParameter("orderNumber"))){
			condition += " and i.orderNumber = :orderNumber";
			parms.put("orderNumber", this.getParameter("orderNumber"));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(InterfaceTaoXie.class, condition, parms));
		List list = dao.findAll(pageMap.get("first"), pageMap.get("size"), hql, condition + order, parms);
		this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		
		request.setAttribute("orderNumber", this.getParameter("orderNumber"));
		return mapping.findForward("list");
	}
	
	

}
