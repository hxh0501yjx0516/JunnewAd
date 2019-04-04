package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.MD5;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.Users;

public class CustomerAction extends BaseDispatchAction {
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageNum = 0;// 页次
		int numPerPage = 20;// 每页显示条数
		if ("pager".equals(this.getParameter("flag"))) {
			// 分页
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		String condition = "";
		Map<String, Object> parms = new HashMap<String, Object>();
		Users users = (Users) request.getSession().getAttribute("user");
		if (!"".equals(this.getParameter("resource"))
				&& !"customer".equals(this.getParameter("resource"))) {
			// 销售查看
			condition += " where c.userid =:userId ";
			parms.put("userId", users.getUserId());
			request.setAttribute("resource", this.getParameter("resource"));
		} else {
			// 执行和管理员查看
			if (users.getUserGroup() != 0) {
				// 执行权限
				condition += " where c.usergroup =:usergroup ";
				parms.put("usergroup", users.getUserGroup());
			} else {
				if (!"".equals(this.getParameter("qryuserGroup"))) {
					condition += " where c.userGroup =:userGroup ";
					parms.put("userGroup",
							Integer.parseInt(this.getParameter("qryuserGroup")));
				}
			}
			request.setAttribute("resource", "customer");
		}
		Map<String, Integer> pageMap = PagingHandle.getPagingParams(pageNum,
				numPerPage, dao.getCount("select count(*) from Customer c "
						+ condition, parms));
		String sql = "select c.*,u.realName realName,counts=(select count(*) from adplan where customerId = c.customerId),cycleCount=(select count(*) from adPlan a  right join adplanCycle ac on ac.adplanId = a.adplanid where a.customerId = c.customerId) "
				+ "from customer c "
				+ "left join users u on c.userid = u.userid ";
		List<Object> cols = new ArrayList<Object>();
		cols.add(Customer.class);
		cols.add("realName");
		cols.add("counts");
		cols.add("cycleCount");
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),
				sql + condition + " order by c.customerId desc", parms, cols);
		List<Customer> list = new ArrayList<Customer>();
		for (int i = 0; i < qryList.size(); i++) {
			Object[] object = (Object[]) qryList.get(i);
			Customer customer = (Customer) object[0];
			customer.setUserName(object[1] + "");
			customer.setPlanCount(Integer.parseInt(object[2] + ""));
			customer.setPlanCycleCount(Integer.parseInt(object[3] + ""));
			list.add(customer);
		}
		if (list != null) {
			this.buildPageResult(request,
					PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("userGroup", users.getUserGroup());
		request.setAttribute("qryuserGroup", this.getParameter("qryuserGroup"));
		this.getUserGroupList(request);
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
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if ("save".equals(this.getParameter("flag"))) {
			// 添加
			String[] pname = new String[1];
			String[] pvalue = new String[1];
			pname[0] = "customerName";
			pvalue[0] = this.getParameter("name");
			List userslist = dao.findAll(Customer.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// 该用户名已存在
				this.buildAjaxResult(request, FAILURE_STATUS, "该用户名已存在!",
						CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			Users users = (Users) request.getSession().getAttribute("user");
			Customer customer = new Customer();
			customer.setCustomerName(this.getParameter("name"));
			customer.setCustomerPassword(MD5.MD5Encode(this
					.getParameter("password")));
			customer.setCustomerContactName(this.getParameter("contactName"));
			customer.setCustomerContactMobile(this
					.getParameter("contactMobile"));
			customer.setCustomerAddress(this.getParameter("contactAddress"));
			customer.setCustomerPost(this.getParameter("post"));
			customer.setCustomerUrl(this.getParameter("url"));
			customer.setUserId(Integer.parseInt(this.getParameter("medium")));
			customer.setCustomerStatus(Integer.parseInt(this
					.getParameter("state")));
			customer.setAddTime(DatetimeHandle.formatCurrentDate());
			// customer.setUserGroup(users.getUserGroup());//添加用户组权限
			customer.setUserGroup(1);

			// 20160510　新增收款人
			customer.setPayee(this.getParameter("payee"));

			try {
				dao.save(customer);
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！",
						CALLBACKTYPE_CLOSECURRENT, "客户列表");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
		}
		this.getXSList(request); // 销售列表
		return mapping.findForward("add");
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
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Customer customer = (Customer) dao.findById(Customer.class,
					Integer.parseInt(this.getParameter("cid")));
			if ("editState".equals(this.getParameter("flag"))) {
				customer.setCustomerStatus(Integer.parseInt(this
						.getParameter("state")));
				if (dao.update(customer))
					this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			} else if ("edit".equals(this.getParameter("flag"))) {
				this.getXSList(request);
				request.setAttribute("customer", customer);
				return mapping.findForward("edit");
			} else {
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "customerName";
				pvalue[0] = this.getParameter("name");
				List userslist = dao.findAll(Customer.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if (Integer.parseInt(this.getParameter("cid")) != ((Customer) userslist
							.get(0)).getCustomerId()) {
						// 该用户名已存在
						this.buildAjaxResult(request, FAILURE_STATUS,
								"该用户名已存在!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				String[] pname1 = new String[2];
				String[] pvalue1 = new String[2];
				pname1[0] = "customerId";
				pvalue1[0] = this.getParameter("cid");
				pname1[1] = "customerPassword";
				pvalue1[1] = this.getParameter("password");
				List webList = dao.findAll(Customer.class, pname1, pvalue1);
				if (webList != null && webList.size() <= 0) {
					customer.setCustomerPassword(MD5.MD5Encode(this
							.getParameter("password")));
				}
				customer.setCustomerName(this.getParameter("name"));
				customer.setCustomerContactName(this
						.getParameter("contactName"));
				customer.setCustomerContactMobile(this
						.getParameter("contactMobile"));
				customer.setCustomerAddress(this.getParameter("contactAddress"));
				customer.setCustomerPost(this.getParameter("post"));
				customer.setCustomerUrl(this.getParameter("url"));
				customer.setUserId(Integer.parseInt(this.getParameter("medium")));
				customer.setCustomerStatus(Integer.parseInt(this
						.getParameter("state")));

				// 20160510　新增收款人
				customer.setPayee(this.getParameter("payee"));

				if (dao.update(customer))
					this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！",
							CALLBACKTYPE_CLOSECURRENT, "客户列表");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
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
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Customer customer = (Customer) dao.findById(Customer.class,
					Integer.parseInt(this.getParameter("cid")));
			dao.delete(customer);
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "操作成功！");
			// request.setAttribute("navTabId","customerList");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
}
