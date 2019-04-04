package com.pancou.ad.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.pancou.ad.util.MD5;
import com.pancou.ad.vo.Role;
import com.pancou.ad.vo.UserGroup;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;
import com.pancou.ad.util.BaseDispatchAction;

/**
 * �û���Ϣ����action by sg 2010-07-08
 * @author 
 *
 */
@Entity
public class UsersAction extends DispatchAction{
	@ManyToOne
	PlatDao dao = new PlatDao();
	/**
	 * ת���û���Ϣ�б�ҳ��
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
		List<Users> al = dao.findAll(Users.class, index, numPerPage, "name", "");
		
		if (al != null ) {
			request.setAttribute("list", al);
		}	
		// ��������
		request.setAttribute("totalCount", dao.getCount(Users.class, "name", ""));
		request.setAttribute("numPerPage", numPerPage);//ÿҳ��ʾ����
		request.setAttribute("qryname", request.getParameter("qryname"));//��ѯ����
		request.setAttribute("pageNum", request.getParameter("pageNum"));
		return mapping.findForward("list");
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
		Users users = (Users)request.getSession().getAttribute("user");
		if(users==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		Users user = (Users) dao.findById(Users.class, Integer.parseInt(request.getParameter("value")));
		dao.delete(user);
		// �б��ʼ��
		this.list(mapping, form, request, response);
		request.setAttribute("statusCode", 200);
		request.setAttribute("message", "�����ɹ���");
//		request.setAttribute("navTabId","�û�����");
//		request.setAttribute("callbackType","closeCurrent");
		request.setAttribute("callbackType", "forward");
		request.setAttribute("forwardUrl", "users.do?action=list");
		return mapping.findForward("ajaxDone");
	}
	
	/**
	 * ���ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Users users = (Users)request.getSession().getAttribute("user");
		if(users==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		if ("save".equals(request.getParameter("flag"))) {
			// ���		
			String[] pname = new String[1];
			String[] pvalue = new String[1];
			pname[0] = "username";
			pvalue[0] = request.getParameter("name");
			List userslist = dao.findAll(Users.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// ���û����Ѵ���
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "���û����Ѵ���!");
				request.setAttribute("callbackType", "closeCurrent");				
				return mapping.findForward("ajaxDone");		
			}
			String newpwd = request.getParameter("pwd");
			String rnewpwd = request.getParameter("pwd1");
			if (! newpwd.equals(rnewpwd)) {
				// �����������벻һ��
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "�����������벻һ��!");
				request.setAttribute("callbackType", "closeCurrent");				
				return mapping.findForward("ajaxDone");
			}
			
			
			Users user = new Users();
			user.setDept(request.getParameter("dep"));
			user.setLastip(request.getParameter("ip"));
			user.setLastlogtime(request.getParameter("time"));
			user.setPwd(MD5.MD5Encode(request.getParameter("pwd")));
			user.setRealname(request.getParameter("realname"));
			user.setState(Integer.parseInt(request.getParameter("state")));
			user.setTel(request.getParameter("tel"));
			user.setTitle(request.getParameter("title"));
			user.setUsername(request.getParameter("name"));
			user.setUserGroup(Integer.parseInt(request.getParameter("usergroup")));
			// ����
			try {
				dao.save(user);
				this.list(mapping, form, request, response);
				request.setAttribute("statusCode", 200);
				request.setAttribute("message", "�����ɹ���");
				request.setAttribute("navTabId","�û�����");
				request.setAttribute("callbackType","closeCurrent");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "����ʧ�ܣ�");

			}
			request.setAttribute("value", request.getParameter("value"));
			return mapping.findForward("ajaxDone");
		}
		
		//�û���list
		String[] statusName = {"UserGroupStatus"};
		String[] statusValue = {"0"};
		List list = dao.findAll(UserGroup.class,statusName,statusValue);
		request.setAttribute("userGroupList", list);
		return mapping.findForward("add");
		
	}
	
	/**
	 * �޸�
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Users users = (Users)request.getSession().getAttribute("user");
		if(users==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		Users olduser = (Users) dao.findById(Users.class, Integer.parseInt(request.getParameter("value")));

		if ("edit".equals(request.getParameter("flag"))) {
			//�û���	
			
			Users user = new Users();
			user.setUserId(Integer.parseInt(request.getParameter("value")));
			user.setDept(request.getParameter("dep"));
			user.setLastip(request.getParameter("ip"));
			user.setLastlogtime(request.getParameter("time"));
			user.setPwd(olduser.getPwd());
			user.setRealname(request.getParameter("realname"));
			user.setState(Integer.parseInt(request.getParameter("state")));
			user.setTel(request.getParameter("tel"));
			user.setTitle(request.getParameter("title"));
			user.setUsername(request.getParameter("name"));
			user.setUserGroup(Integer.parseInt(request.getParameter("usergroup")));
			// �޸�
			dao.update(user);
			try {
				this.list(mapping, form, request, response);
				request.setAttribute("statusCode", 200);
				request.setAttribute("message", "�����ɹ���");
				request.setAttribute("navTabId","�û�����");
				request.setAttribute("callbackType","closeCurrent");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "����ʧ�ܣ�");

			}
//			request.setAttribute("callbackType", "forward");
//			request.setAttribute("forwardUrl", "users.do?action=list");
			return mapping.findForward("ajaxDone");
		} else {
			
			request.setAttribute("user", olduser);
		}
		
		//�û���list
		String[] statusName = {"UserGroupStatus"};
		String[] statusValue = {"0"};
		List list = dao.findAll(UserGroup.class,statusName,statusValue);
		request.setAttribute("userGroupList", list);
		return mapping.findForward("edit");
		
	}
	/**
	 * �����ɫ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userRoleAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		// ��õ�½�û���Ϣ
		String[] pname = new String[1];//��ѯ����
		String[] pvalue = new String[1];//����ֵ
		
		if ("save".equals(request.getParameter("flag"))) {
			pname[0] = "userId";//���б�����
			pvalue[0] = request.getParameter("userid");//��user_roleҳ����userid����
			List rrall= dao.findAll(UserRole.class, pname, pvalue);
			for (int i = 0; i < rrall.size(); i ++) {
				// ɾ��ý�ɫ���е���Դ
				dao.delete(rrall.get(i));
			}
			String[] arr = request.getParameterValues("chk");
			for (int i = 0; i < arr.length; i++) {
				UserRole ur = new UserRole();
				ur.setRoleId(Integer.parseInt(arr[i]));
				ur.setUserId(Integer.parseInt(request.getParameter("userid")));
				dao.save(ur);
			}
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","�û�����");
			request.setAttribute("callbackType","closeCurrent");
//			request.setAttribute("callbackType", "forward");
//			request.setAttribute("forwardUrl", "users.do?action=list");
			return mapping.findForward("ajaxDone");
		}
		int count = dao.getCount(Role.class, "", "");
		List<Role> role = dao.findAll(Role.class, 0, count, "", "");
		request.setAttribute("role", role);
		
		pname[0] = "userId";
		pvalue[0] = request.getParameter("value");//��user_listҳ����userid����
		//���userid���ɫ
		List<UserRole> ur = dao.findAll(UserRole.class, pname, pvalue);
		List<HashMap<String ,Object>> listur = new ArrayList<HashMap<String ,Object>>();

		for (Role r : role) {
			HashMap hs = new HashMap();
			for (UserRole userrole : ur) {
				if (r.getRoleId() == userrole.getRoleId()) {
					hs.put("flag", 0);					
				}				
			}
			hs.put("roleId", r.getRoleId());
			hs.put("roleName", r.getRoleName());
			listur.add(hs);
		}
		
		request.setAttribute("listur", listur);
		request.setAttribute("userid", request.getParameter("value"));
		return mapping.findForward("ur");
		
	}
	
	/**
	 * �޸������û�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Users user = (Users)request.getSession().getAttribute("user");
		if(user==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		String[] pname = new String[1];
		String[] pvalue = new String[1];
		pname[0] = "userId";
		pvalue[0] = request.getParameter("userid");
		List list = dao.findAll(Users.class, pname, pvalue);
		if ("einit".equals(request.getParameter("flag"))) {
			if (list != null && list.size() > 0) {
				Users users = (Users) list.get(0);
				request.setAttribute("users", users);				
			}
			return mapping.findForward("editpwd");
		} else {
			String newpwd = request.getParameter("pwd");
			String rnewpwd = request.getParameter("pwd2");
			if (! newpwd.equals(rnewpwd)) {
				// �����������벻һ��
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "�����������벻һ��!");
				request.setAttribute("callbackType", "closeCurrent");				
				return mapping.findForward("ajaxDone");
			}
			if (list != null && list.size() > 0) {
				Users newusers = (Users) list.get(0);
				newusers.setPwd(MD5.MD5Encode(request.getParameter("pwd")));
				dao.update(newusers);
			}
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","�û�����");
			request.setAttribute("callbackType","closeCurrent");
//			request.setAttribute("callbackType", "forward");
//			request.setAttribute("forwardUrl", "users.do?action=list");
			return mapping.findForward("ajaxDone");
		}
		
		
		
	}
	
	/**
	 * �޸��Լ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		Users users = (Users)request.getSession().getAttribute("user");
		if(users==null) {//�Ƿ���¼
			request.setAttribute("mes", "�����µ�¼!");
			return mapping.findForward("logout") ;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newpwd = request.getParameter("newpwd");
		String rnewpwd = request.getParameter("rnewpwd");
		if ("save".equals(request.getParameter("flag"))){
			Users user = (Users)request.getSession().getAttribute("user");
			if (!user.getPwd().equals(MD5.MD5Encode(request.getParameter("oldpwd")))) {
				// �������������
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "���������!");
				request.setAttribute("callbackType", "closeCurrent");				
				return mapping.findForward("ajaxDone");		
			} else if (! newpwd.equals(rnewpwd)) {
				// �����������벻һ��
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "�����������벻һ��!");
				request.setAttribute("callbackType", "closeCurrent");				
				return mapping.findForward("ajaxDone");
			}
			Users newuser = new Users();
			// �û�ID
			newuser.setUserId(user.getUserId());
			// ����
			newuser.setPwd(MD5.MD5Encode(request.getParameter("newpwd")));
			// ����
			newuser.setDept(user.getDept());
			// ����¼ip
			newuser.setLastip(user.getLastip());
			// ����޸�ʱ��
			newuser.setLastlogtime(sdf.format(new Date()));
			// ��ʵ����
			newuser.setRealname(user.getRealname());
			// ״̬
			newuser.setState(user.getState());
			// �绰
			newuser.setTel(user.getTel());
			// ְ��
			newuser.setTitle(user.getTitle());
			// �û���
			newuser.setUsername(user.getUsername());
			
			dao.update(newuser);			
			request.setAttribute("statusCode", 200);
			request.setAttribute("message", "�����ɹ���");
			request.setAttribute("navTabId","�û�����");
			request.setAttribute("callbackType","closeCurrent");
			return mapping.findForward("ajaxDone");
		}
		
		return mapping.findForward("pwd");		
	}
}
