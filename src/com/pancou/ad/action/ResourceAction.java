package com.pancou.ad.action;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.vo.Resource;
import com.pancou.ad.vo.Users;


/**
 * ��Դ����ģ��
 * 
 * @author sg
 * 
 */
@Entity
public class ResourceAction extends DispatchAction {
	@ManyToOne
	PlatDao dao = new PlatDao();

	/**
	 * ת����Դ�б�ҳ��
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
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		int pageNum = 0;// ҳ��
		int numPerPage = 20;//ÿҳ��ʾ����
		int index = 0;//��ǰ��¼����
		if ("pager".equals(request.getParameter("flag"))) {
			// ��ҳ
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			index = (pageNum - 1) * numPerPage;
		}
		String forwardName = "list";
		// ��ʼ���б�
		List<Resource> l = dao.findAll(Resource.class, index, numPerPage, "name", "");
		request.setAttribute("list", l);
		// ��ɾ��Ȩ������
		/*
		 * int roleId = user.getRole().getRoleId(); String resourceIdStr =
		 * request.getParameter("resourceId"); if(resourceIdStr!=null) { try {
		 * int resourceId = Integer.parseInt(resourceIdStr); //��ѯ��Ӧ�Ĳ���Ȩ��
		 * RoleResource roleAndRes=
		 * DaoFactory.getRoleResourceDaoInstance().findResourcePurview(roleId,
		 * resourceId);
		 * 
		 * request.setAttribute("purview", roleAndRes); } catch(Exception e) {
		 * request.setAttribute("message","ҳ���������ϵ����Ա!" ); return
		 * mapping.findForward("error"); } }
		 */
		// ��������
		request.setAttribute("totalCount", dao.getCount(Resource.class,"name", ""));
		request.setAttribute("numPerPage", numPerPage);//ÿҳ��ʾ����
		request.setAttribute("qryname", request.getParameter("qryname"));//��ѯ����
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return mapping.findForward(forwardName);
	}

	/**
	 * �����Դ
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		if ("ainit".equals(request.getParameter("flag"))) {
			// ��ʼ�����ҳ��
			String[] pname=new String[1];
			String[] pvalue=new String[1];
			pname[0]="resourceType";
			pvalue[0]="0";
			List<Resource> l = dao.findAll(Resource.class, pname, pvalue);
			request.setAttribute("resourceList", l);
			return mapping.findForward("add");

		} else {
			String forwardName = "";
			String resourceName = request.getParameter("name");
			String resourceModual = request.getParameter("modual");
			String resourceTypeStr = request.getParameter("type");
			String resourceUrl = request.getParameter("url");
			String resourcePidStr = request.getParameter("pid");
			String displayorder = request.getParameter("displayorder");
	
			int resourceType = Integer.parseInt(resourceTypeStr);
			int resourcePid = 0;
			if (!"".equals(resourcePidStr)) {
				resourcePid = Integer.parseInt(resourcePidStr);
			}
			if(resourceType == 0){
				resourcePid = 0;
			}
			Resource r = new Resource();
			r.setResourceModual(resourceModual);
			r.setResourceName(resourceName);
			r.setResourcePid(resourcePid);
			r.setResourceType(resourceType);
			r.setResourceUrl(resourceUrl);
			r.setDisplayorder(Integer.parseInt(displayorder));
			
			try {
				dao.save(r);
				this.list(mapping, form, request, response);
				request.setAttribute("statusCode", 200);
				request.setAttribute("message", "�����ɹ���");
				request.setAttribute("navTabId","��Դ����");
				request.setAttribute("callbackType","closeCurrent");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "����ʧ�ܣ�");

			}
//			request.setAttribute("callbackType", "forward");
//			request.setAttribute("forwardUrl", "resource.do?action=list");
			request.setAttribute("resourceId", request.getParameter("resourceId"));
			return mapping.findForward("ajaxDone");
		
		}
	}

	/**
	 * �޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward editAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}		
		if("einit".equals(request.getParameter("flag"))) {
			// �޸�ҳ���ʼ��
			int pagesize = dao.getCount(Resource.class, "name", "");
			Resource r = (Resource) dao.findById(Resource.class, Integer.parseInt(request.getParameter("value")));
			request.setAttribute("r", r);
			
			String[] pname=new String[1];
			String[] pvalue=new String[1];
			pname[0]="resourceType";
			pvalue[0]="0";
			List<Resource> l = dao.findAll(Resource.class, pname, pvalue);
			request.setAttribute("resourceList", l);
			
			return mapping.findForward("edit");			
		}

		String forwardName = "";
		String resIdStr = request.getParameter("id");
		String resourceName = request.getParameter("name");
		String resourceModual = request.getParameter("modual");
		String resourceTypeStr = request.getParameter("type");
		String resourceUrl = request.getParameter("url");
		String resourcePidStr = request.getParameter("pid");
		String displayorder = request.getParameter("displayorder");

		int resId = Integer.parseInt(resIdStr);
		int resourceType = Integer.parseInt(resourceTypeStr);
		int resourcePid = 0;
		if (!"".equals(resourcePidStr)) {
			resourcePid = Integer.parseInt(resourcePidStr);
		}
		if(resourceType == 0){
			resourcePid = 0;
		}
		Resource res = new Resource();
		
		res.setResourceId(resId);
		res.setResourceModual(resourceModual);
		res.setResourceName(resourceName);
		res.setResourcePid(resourcePid);
		res.setResourceType(resourceType);
		res.setResourcePid(resourcePid);
		res.setResourceUrl(resourceUrl);
		res.setDisplayorder(Integer.parseInt(displayorder));
		try {
			dao.update(res);
			this.list(mapping, form, request, response);
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","��Դ����");
			request.setAttribute("callbackType","closeCurrent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "����ʧ�ܣ�");

		}
//		request.setAttribute("callbackType", "forward");
//		request.setAttribute("forwardUrl", "resource.do?action=list");
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return mapping.findForward("ajaxDone");
	}

	/**
	 * ɾ����Դ
	 */
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}

		String resIdStr = request.getParameter("value");
				
		try {
			int id = Integer.parseInt(resIdStr);
			Resource rrlist = (Resource) dao.findById(Resource.class, id);
			dao.delete(rrlist);
			this.list(mapping, form, request, response);
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "����ʧ�ܣ�");

		}
		request.setAttribute("callbackType", "forward");
		request.setAttribute("forwardUrl", "resource.do?action=list");
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return mapping.findForward("ajaxDone");
	
	}
}
