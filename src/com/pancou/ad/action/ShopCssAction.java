package com.pancou.ad.action;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Shop;
import com.pancou.ad.vo.ShopIndex;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;


/**
 * 
 * @author Administrator
 *
 */
@Entity
public class ShopCssAction extends BaseDispatchAction {
	
	
	/**
	 * CSS���Ʋ����鿴
	 * @param mapping
	 * @param form
	 * @param resquest
	 * @param response
	 * @return
	 */
	public ActionForward countList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("countList");
	}
}
