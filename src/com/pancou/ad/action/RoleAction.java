package com.pancou.ad.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.pancou.ad.vo.Role;
import com.pancou.ad.vo.RoleResource;
import com.pancou.ad.vo.Users;

@Entity
public class RoleAction extends DispatchAction {
	@ManyToOne
	PlatDao dao = new PlatDao();
	/**
	 * ת����ɫ�б�ҳ��
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
		
		String forwardName = "list";
		int pageNum = 0;// ҳ��
		int numPerPage = 20;//ÿҳ��ʾ����
		int index = 0;//��ǰ��¼����
		if ("pager".equals(request.getParameter("flag"))) {
			// ��ҳ
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
			index = (pageNum - 1) * numPerPage;
		}
		// ҳ���ʼ��
		List<Role> roleList = dao.findAll(Role.class, index, numPerPage, "name", "");
		request.setAttribute("roleList", roleList);
		//��ɾ��Ȩ������
		String resourceIdStr = request.getParameter("resourceId");
//		if(resourceIdStr!=null) {
//			try {
//				int resourceId = Integer.parseInt(resourceIdStr);
//				//��ѯ��Ӧ�Ĳ���Ȩ��
//				//RoleResource roleAndRes= DaoFactory.getRoleResourceDaoInstance().findResourcePurview(roleId, resourceId);
//				RoleResource roleAndRes= DaoFactory.getRoleResourceDaoInstance().findResourcePurviewByUser(user.getUser().getUserId(), resourceId);
//				request.setAttribute("purview", roleAndRes);
//			} catch(Exception e) {
//				request.setAttribute("message","ҳ���������ϵ����Ա!" );
//				return mapping.findForward("error");
//			}
//		} 
		
		// ��������
		request.setAttribute("totalCount", dao.getCount(Role.class, "name", ""));
		request.setAttribute("numPerPage", numPerPage);//ÿҳ��ʾ����
		request.setAttribute("qryname", request.getParameter("qryname"));//��ѯ����
		request.setAttribute("pageNum", request.getParameter("pageNum"));

		request.setAttribute("resourceId", resourceIdStr);
		return mapping.findForward(forwardName);
	}
	
	/**
	 * ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		// ���id��Ӧ�����
		Role role = (Role) dao.findById(Role.class, Integer.parseInt(request.getParameter("value")));
		// ɾ��		
		try {
			dao.delete(role);
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
		request.setAttribute("forwardUrl", "role.do?action=list");
		return mapping.findForward("ajaxDone");
	}
	/**
	 * ��ӽ�ɫ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
			return mapping.findForward("add");
		}
		
		String forwardName = "ok";
		
		String roleName = request.getParameter("name"); 
		String memo = request.getParameter("memo");
		String stateStr = request.getParameter("state");
		
		int state = Integer.parseInt(stateStr);
		Role r = new Role();
		r.setMemo(memo);
		r.setRoleName(roleName);
		r.setState(state);
		// ����		
		try {
			dao.save(r);
			this.list(mapping, form, request, response);
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","��ɫ����");
			request.setAttribute("callbackType","closeCurrent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "����ʧ�ܣ�");
		}
//		request.setAttribute("callbackType", "forward");
//		request.setAttribute("forwardUrl", "role.do?action=list");
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return mapping.findForward("ajaxDone");
	}
	
	/**
	 * �޸Ľ�ɫ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}		
		if ("einit".equals(request.getParameter("flag"))) {
			// �޸�ҳ���ʼ��
			Role role = (Role) dao.findById(Role.class, Integer.parseInt(request.getParameter("value")));
			request.setAttribute("r", role);
			return mapping.findForward("edit");
		}
		
		String forwardName = "ok";
		String idStr = request.getParameter("id");
		String roleName = request.getParameter("name");
		String memo = request.getParameter("memo");
		String stateStr = request.getParameter("state");
		int id = Integer.parseInt(idStr);
		int state = Integer.parseInt(stateStr);
		Role r = new Role();
		r.setRoleId(id);
		r.setMemo(memo);
		r.setRoleName(roleName);
		r.setState(state);
		// �޸�
		try {
			dao.update(r);
			this.list(mapping, form, request, response);
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","��ɫ����");
			request.setAttribute("callbackType","closeCurrent");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "����ʧ�ܣ�");
		}
//		request.setAttribute("callbackType", "forward");
//		request.setAttribute("forwardUrl", "role.do?action=list");
		request.setAttribute("resourceId", request.getParameter("resourceId"));
		return mapping.findForward("ajaxDone");
	}
	
	/**
	 * Ȩ�޷��� new
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward roleResAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		String[] pname = new String[1];//��ѯ����
		String[] pvalue = new String[1];//����ֵ
		// �����Դ
		if ("save".equals(request.getParameter("flag"))) {
			pname[0] = "roleId";
			pvalue[0] = request.getParameter("roleid");//��res_listҳ����roleid
			List rrall= dao.findAll(RoleResource.class, pname, pvalue);
			// ����		
			try {
				for (int i = 0; i < rrall.size(); i ++) {
					// ɾ��ý�ɫ���е���Դ
					dao.delete(rrall.get(i));
				}
				String[] arr = request.getParameterValues("chk");
				for (int i = 0; i < arr.length; i++) {
					RoleResource rr = new RoleResource();
					rr.setResId(Integer.parseInt(arr[i]));
					rr.setRoleId(Integer.parseInt(request.getParameter("roleid")));
					// �����ѡ�е���Դ
					dao.save(rr);
				}
				this.list(mapping, form, request, response);
				request.setAttribute("statusCode", 200);
				request.setAttribute("message", "�����ɹ���");
				request.setAttribute("navTabId","��ɫ����");
				request.setAttribute("callbackType","closeCurrent");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "����ʧ�ܣ�");
			}
//			request.setAttribute("callbackType", "forward");
//			request.setAttribute("forwardUrl", "role.do?action=list");
			request.setAttribute("resourceId", request.getParameter("resourceId"));
			return mapping.findForward("ajaxDone");
		}
		
		// ȡ��������Դ
		int count = dao.getCount(Resource.class, "", "");
		List<Resource> res = dao.findAll(Resource.class, 0, count, "", "");
		request.setAttribute("res", res);
		// ���ѡ�еĲ˵�
		pname[0] = "roleId";
		pvalue[0] = request.getParameter("value");//��role_listҳ����roleid
		List<RoleResource> rr = dao.findAll(RoleResource.class, pname, pvalue);
		
		// �Ӳ˵�
		List<HashMap<String ,Object>> listur = new ArrayList<HashMap<String ,Object>>();
		// ���˵�
		ArrayList<HashMap<String, Object>> menu = new ArrayList<HashMap<String, Object>>();
		
		// ��ʼ��
		for (Resource r : res) {
			HashMap<String, Object> hs = new HashMap<String, Object>();
			for (RoleResource roleres : rr) {
				if (r.getResourceId() == roleres.getResId()) {
					// ��ʼ��ѡ��״̬
					hs.put("flag", 0);					
				}				
			}
			if(r.getResourcePid() == 0) {
				// ���˵�
				hs.put("pid", r.getResourcePid());
				hs.put("id", r.getResourceId());
				hs.put("resId", r.getResourceId());
				hs.put("resName", r.getResourceName());
				menu.add(hs);
			} else {
				// �Ӳ˵�
				hs.put("pid", r.getResourcePid());
				hs.put("resId", r.getResourceId());
				hs.put("resName", r.getResourceName());
				listur.add(hs);
			}
		}
		request.setAttribute("menu", menu);//���˵���ʼ��
		request.setAttribute("listur", listur);//�Ӳ˵���ʼ��
		request.setAttribute("roleid", request.getParameter("value"));//��ɫid
		return mapping.findForward("res");
		
	}
}
