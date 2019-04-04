package com.pancou.ad.action;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.CommHttpAllYes;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DESPlus;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.MD5;
import com.pancou.ad.util.json.JsonWriter;
import com.pancou.ad.util.token.HMAC_MD5;
import com.pancou.ad.vo.Log;
import com.pancou.ad.vo.LoginIp;
import com.pancou.ad.vo.Resource;
import com.pancou.ad.vo.Token;
import com.pancou.ad.vo.UserRole;
import com.pancou.ad.vo.Users;

public class LoginAction extends DispatchAction {
	PlatDao dao = new PlatDao();
	private static int loginCount = 0;
	private static Map<String, Integer> loginIpMap = new HashMap<String, Integer>();

	@SuppressWarnings("unchecked")
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IpAndUrl iu = new IpAndUrl();
		Log log = new Log();
		// 验证码
		String code = request.getParameter("code");
		String rand = "";
		Object randT = request.getSession().getAttribute("rand");
		if (rand != null)
			rand = randT + "";
		else
			return mapping.findForward("logout");

		String pwd = MD5.MD5Encode(request.getParameter("pwd"));
		if (code == null || code.equals("")) {
			// 判断验证码是否一致
			request.setAttribute("mes", "请输入验证码！");
			return mapping.findForward("logout");
		}
		if (!code.equals(rand)) {
			// 判断验证码是否一致
			request.setAttribute("mes", "验证码输入错误！");
			return mapping.findForward("logout");
		}
		String[] puser = new String[1];
		puser[0] = "username";
		// puser[1] = "state";
		String[] puv = new String[1];
		puv[0] = request.getParameter("name");
		// puv[1] = "0";
		// 根据用户名查userid
		List<Users> userlist = dao.findAll(Users.class, puser, puv);
		if (userlist.size() > 0) {
			// --------------用户存在
			Users users = (Users) userlist.get(0);
			// 登录日志
			log.setLogUserType(users.getTitle());
			log.setLogUserName(users.getUsername());
			log.setLogRealName(users.getRealname());
			log.setLogSource(iu.getUrlAddress(request));
			log.setLogIp(iu.getRemoteIP(request));
			log.setLogTime(DatetimeHandle.formatCurrentDate());
			if (userlist.get(0).getState() == 0) {
				// ********账号正常
				if (!pwd.equals(userlist.get(0).getPwd())) {
					// 用户名和密码不一致，记录登陆次数
					log.setLogUserFlag(1);// 0为正常登陆，1为登陆密码错误记录,2为用户名不存在记录,3为锁定账户,4usbkey验证失败,5usbkey验证成功
					if (users.getSid() == 2) {
						users.setSid(0);
						users.setState(1);// 锁定
						request.setAttribute("mes", "用户名或密码错误-错误代码：0Fx001");

						// ********如果账号锁定且IP不在白名单内则将ip地址加入黑名单
						iu.lockUser(iu.getRemoteIP(request), "账号锁定时添加", dao);
					} else {
						request.setAttribute("mes", "用户名或密码错误！当日还可以登陆"
								+ (2 - users.getSid()) + "次");
						users.setSid(users.getSid() + 1);
					}
					log.setLogPassword(request.getParameter("pwd"));
					// 判断本地登录
					if (iu.decideLocal(iu.getRemoteIP(request)))
						dao.save(log);
					dao.update(users);
					return mapping.findForward("logout");
				}
			} else {
				// ********账号已锁定
				request.setAttribute("mes", "用户名或密码错误-错误代码：0Fx003");
				log.setLogPassword(request.getParameter("pwd"));
				log.setLogUserFlag(3);// 0为正常登陆，1为登陆密码错误记录,2为用户名不存在记录,3为锁定账户,4usbkey验证失败,5usbkey验证成功
				// 判断本地登录
				if (iu.decideLocal(iu.getRemoteIP(request)))
					dao.save(log);
				return mapping.findForward("logout");
			}
		} else {
			// ---------------用户不存在
			if (loginIpMap.containsKey(iu.getRemoteIP(request))) {
				loginIpMap.put(iu.getRemoteIP(request),
						loginIpMap.get(iu.getRemoteIP(request)) + 1);
			} else {
				loginIpMap.put(iu.getRemoteIP(request), loginCount);
			}
			if (loginIpMap.get(iu.getRemoteIP(request)) == 2) {
				// ********如果账号锁定且IP不在白名单内则将ip地址加入黑名单
				iu.lockUser(iu.getRemoteIP(request), "用户不存在时添加", dao);
				loginIpMap.remove(iu.getRemoteIP(request));
			}
			request.setAttribute("mes", "用户名或密码错误-错误代码：0Fx002");
			log.setLogUserFlag(2);// 0为正常登陆，1为登陆密码错误记录,2为用户名不存在记录,3为锁定账户,4usbkey验证失败,5usbkey验证成功
			log.setLogUserName(request.getParameter("name"));
			log.setLogPassword(request.getParameter("pwd"));
			log.setLogSource(iu.getUrlAddress(request));
			log.setLogIp(iu.getRemoteIP(request));
			log.setLogTime(DatetimeHandle.formatCurrentDate());
			// 判断本地登录
			if (iu.decideLocal(iu.getRemoteIP(request)))
				dao.save(log);
			return mapping.findForward("logout");
		}
		int logId = 0;
		if (userlist.size() > 0) {

			Users user = userlist.get(0);// 该用户所有信息

			DESPlus des = new DESPlus("superSpider");
			log.setLogPassword(des.encrypt(request.getParameter("pwd")));
			log.setLogUserFlag(0);// 0为正常登陆，1为登陆密码错误记录,2为用户名不存在记录,3为锁定账户,4usbkey验证失败,5usbkey验证成功,6短信未通过验证，7短信验证成功
			// 判断本地登录
			if (iu.decideLocal(iu.getRemoteIP(request)))
				logId = dao.save(log);

			// **************************************************************
			// 登陆成功，清空错误登陆次数
			user.setSid(0);
			dao.update(user);
			// 登陆成功后清除ip地址
			if (loginIpMap.containsKey(iu.getRemoteIP(request)))
				loginIpMap.remove(iu.getRemoteIP(request));
			// **************************************************************
			request.getSession().setAttribute("user", user);
			/* 用户验证通过，ip地址在白名单中存在则不验证手机短信和usbkey直接进去系统 */

			// true 黑白名单都不验证短信和USB
			boolean bool = true;
			if (!iu.decideLocal(iu.getRemoteIP(request))) {
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("/login.do?action=validate");
				return actionForward;
			} else {
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "loginType";
				pvalue[0] = "1";// 白名单
				List<LoginIp> loginList = dao.findAll(LoginIp.class, pname,
						pvalue);
				for (LoginIp loginIp : loginList) {
					if (iu.getRemoteIP(request).matches(loginIp.getLoginIp())) {
						// 在白名单
						bool = true;
						break;
					}
				}
				if (bool) {
					log.setLogUserFlag(8);// 白名单内部登陆
					dao.update(log);
					ActionForward actionForward = new ActionForward();
					actionForward.setPath("/login.do?action=validate");
					return actionForward;
				}
			}

			/* 用户验证通过，进入短信验证和usbkey验证 */
			String telephone = user.getTel();
			if (telephone != null && !"".equals(telephone)) {
				telephone = telephone.trim().substring(0, 3) + "****"
						+ telephone.trim().substring(7);
			}
			request.setAttribute("telephone", telephone);
		}
		Random r = new Random();
		int re = 0;
		int number = 0;
		while (true) {
			re = r.nextInt(99999999);
			if (re > 10000000) {
				number = re;
				break;
			}
		}
		request.setAttribute("logId", logId);
		request.setAttribute("serialNumber", number);
		request.getSession().removeAttribute("smsvalidate");
		return mapping.findForward("success");
	}

	public ActionForward sendSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("smsvalidate");
		Users users = (Users) request.getSession().getAttribute("user");
		CommHttpAllYes commHttp = new CommHttpAllYes();
		String sendNo = request.getParameter("serialNumber");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Random r = new Random();
		int re = 0;
		int number = 0;
		while (true) {
			re = r.nextInt(999999);
			if (re > 100000) {
				number = re;
				break;
			}
		}
		String tel = users.getTel();
		String content = "编号" + sendNo + ",短信验证码" + number + "。于"
				+ DatetimeHandle.formatCurrentDate() + "登陆骏易传媒后台。";
		content = URLEncoder.encode(content, "utf-8");
		JsonWriter writer = new JsonWriter();
		String result = "";
		try {
			Properties proper = commHttp.send(Configure.get("server.sms.url")
					+ "?tel=" + tel + "&content=" + content);
			if (Integer.parseInt(proper.get("result").toString()) == 0) {
				System.out.println(DatetimeHandle.formatCurrentDate()
						+ "---短信接口调用成功,电话号码：" + tel + "，编号：" + sendNo
						+ "已发送---");
				map.put(Integer.parseInt(sendNo), number);
				request.getSession().setAttribute("smsvalidate", map);
			}
			result = writer.write(proper.get("result").toString());
		} catch (Exception e) {
			System.out.println(DatetimeHandle.formatCurrentDate()
					+ "---短信接口未打开,电话号码：" + tel + "，编号：" + sendNo + "，连接失败---");
			result = writer.write("1");
		}
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		return null;
	}

	@SuppressWarnings("unchecked")
	public ActionForward validateSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String telephone = request.getParameter("telephone");
		String serialNumber = request.getParameter("serialNumber");
		String verifyNumber = request.getParameter("verifyNumber").trim();
		String hidSelectType = request.getParameter("hidSelectType");
		// System.out.println("logId================="+request.getParameter("logId"));
		String logId = request.getParameter("logId") == null
				|| "".equals(request.getParameter("logId")) ? "0" : request
				.getParameter("logId");
		request.setAttribute("hidSelectType", hidSelectType);
		request.setAttribute("logId", logId);
		request.setAttribute("telephone", telephone);
		request.setAttribute("serialNumber", serialNumber);
		Users users = (Users) request.getSession().getAttribute("user");
		if (users == null) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("logout");
		}
		HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) request
				.getSession().getAttribute("smsvalidate");
		if (map == null || map.isEmpty()) {
			request.setAttribute("mes", "请先获取验证码!");
			return mapping.findForward("success");
		}
		IpAndUrl iu = new IpAndUrl();
		Log log = (Log) dao.findById(Log.class, Integer.parseInt(logId));
		// Set keys = map.keySet();
		// Iterator<Integer> it = keys.iterator();
		// while(it.hasNext()){
		// Integer key = it.next();
		// System.out.println(map.get(key));
		// }
		int mverify = map.get(Integer.parseInt(serialNumber));
		if (!map.containsKey(Integer.parseInt(serialNumber))
				|| Integer.parseInt(verifyNumber) != mverify) {
			// 验证未通过
			if (iu.decideLocal(iu.getRemoteIP(request))) {
				log.setLogUserFlag(6);
				dao.update(log);
			}
			request.setAttribute("mes", "验证码错误！");
			return mapping.findForward("success");
		}
		if (iu.decideLocal(iu.getRemoteIP(request))) {
			log.setLogUserFlag(7);
			dao.update(log);
		}
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("/login.do?action=validate");
		return actionForward;
	}

	@SuppressWarnings("unchecked")
	public ActionForward validateKey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Users users = (Users) request.getSession().getAttribute("user");
		if (users == null) {// 非法登录
			request.setAttribute("mes", "请重新登录!");
			return mapping.findForward("logout");
		}
		// ***********************************************************************
		String Randata = (String) request.getSession().getAttribute(
				"RandomData");
		IpAndUrl iu = new IpAndUrl();
		String SerNo = request.getParameter("SN_SERAL");
		String ClientDigest = request.getParameter("Digest");
		String logId = request.getParameter("logId") == null
				|| "".equals(request.getParameter("logId")) ? "0" : request
				.getParameter("logId");
		String SerNoTxt = "";
		String Pwd = "";

		boolean bool = false;
		List<Token> tokenList = dao.findAll(Token.class);
		for (Token token : tokenList) {
			Pwd = token.getTokenDigest();
			SerNoTxt = token.getTokenSerial();
			HMAC_MD5 hm = new HMAC_MD5(Pwd.getBytes());
			hm.clear();
			hm.addData(Randata.getBytes());

			byte digest[];
			digest = hm.sign();
			String ss = hm.toString();

			if (ClientDigest.equals(ss) && SerNo.equals(SerNoTxt)) {
				// 验证通过
				bool = true;
				break;
			}
		}
		Log log = (Log) dao.findById(Log.class, Integer.parseInt(logId));
		if (!bool) {
			// 验证未通过
			if (iu.decideLocal(iu.getRemoteIP(request))) {
				log.setLogUserFlag(4);
				dao.update(log);
			}
			request.getSession().removeAttribute("user");
			request.setAttribute("mes", "用户名或密码错误-错误代码：0Fx004");
			return mapping.findForward("logout");
		}
		if (iu.decideLocal(iu.getRemoteIP(request))) {
			log.setLogUserFlag(5);
			dao.update(log);
		}
		// ***********************************************************************
		ActionForward actionForward = new ActionForward();
		actionForward.setPath("/login.do?action=validate");
		return actionForward;
	}

	@SuppressWarnings("unchecked")
	public ActionForward validate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList childal = new ArrayList();// 菜单
		ArrayList menual = new ArrayList();// 子菜单
		String[] pname = new String[1];
		pname[0] = "username";
		String[] pvalue = new String[1];
		pvalue[0] = request.getParameter("name");
		Users user = (Users) request.getSession().getAttribute("user");
		pname[0] = "userid";
		pvalue[0] = String.valueOf(user.getUserId());// 用户ID

		// 根据userid查roleid
		List<UserRole> listur = dao.findAll(UserRole.class, pname, pvalue);
		for (int i = 0; i < listur.size(); i++) {
			UserRole ur = listur.get(i);

			// 根据roleid查resourceid
			pname[0] = "roleid";
			pvalue[0] = String.valueOf(ur.getRoleId());// 角色ID

			Map<String, Object> parms = new HashMap<String, Object>();
			parms.put("roleid", ur.getRoleId());
			List<Object> cols = new ArrayList<Object>();
			cols.add(Resource.class);
			List<Resource> resList = dao
					.findBySql(
							"select r.* from resource r left join role_resource rr on r.resid = rr.resourceid where rr.roleid=:roleid order by r.displayorder ",
							parms, cols);

			if (resList != null && resList.size() > 0) {
				for (Resource res : resList) {
					if (0 == res.getResourcePid()) {// 菜单
						HashMap menu = new HashMap();
						menu.put("resname", res.getResourceName());// 资源名
						menu.put("id", res.getResourceId());// id
						menu.put("disorder", res.getDisplayorder());// displayorder
						menual.add(new Menu(menu, res.getDisplayorder()));
					} else {
						// 子菜单
						HashMap childmenu = new HashMap();
						childmenu.put("resname", res.getResourceName());// 资源名
						childmenu.put("resurl", res.getResourceUrl());// 资源url
						childmenu.put("pid", res.getResourcePid());// pid
						childmenu.put("disorder", res.getDisplayorder());// displayorder
						// childal.add(res.get(0).getDisplayorder(),childmenu);
						childal.add(new Menu(childmenu, res.getDisplayorder()));
					}
				}
			}
		}
		// 按顺序显示 菜单
		ArrayList alm = new ArrayList();
		for (int i = 0; i < menual.size(); i++) {
			Menu m = (Menu) (menual.get(i));
			alm.add(m.getHm());
		}
		ArrayList almc = new ArrayList();
		for (int i = 0; i < childal.size(); i++) {
			Menu m = (Menu) (childal.get(i));
			almc.add(m.getHm());
		}

		request.setAttribute("mlist", alm);
		request.setAttribute("cmlist", almc);
		return mapping.findForward("validate");
	}
}

@SuppressWarnings("unchecked")
class Mycomparat implements Comparator {

	public int compare(Object o1, Object o2) {
		Menu m1 = (Menu) o1;
		Menu m2 = (Menu) o2;
		if (m1.disorder < m2.disorder)
			return 1;
		else
			return 0;
	}

}

@SuppressWarnings("unchecked")
class Menu {

	HashMap hm = new HashMap();
	int disorder;

	public Menu(HashMap hm, int disorder) {
		this.hm = hm;
		this.disorder = disorder;
	}

	public HashMap getHm() {
		return hm;
	}

	public void setHm(HashMap hm) {
		this.hm = hm;
	}

	public int getDisorder() {
		return disorder;
	}

	public void setDisorder(int disorder) {
		this.disorder = disorder;
	}

}

class IpAndUrl {
	/**
	 * getUrlAddress
	 * 
	 * @param request
	 * @return
	 */
	public String getUrlAddress(HttpServletRequest request) {
		return request.getHeader("Referer");
	}

	/**
	 * getRemoteIP
	 */
	public String getRemoteIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public boolean decideLocal(String ip) {
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public void lockUser(String strIp, String mes, PlatDao dao) {
		boolean bool = true;
		String[] pname = new String[1];
		String[] pvalue = new String[1];
		pname[0] = "loginType";
		pvalue[0] = "1";// 白名单
		List<LoginIp> loginList = dao.findAll(LoginIp.class, pname, pvalue);
		for (LoginIp loginIp : loginList) {
			if (strIp.matches(loginIp.getLoginIp())) {
				// 在白名单
				bool = false;
				break;
			}
		}
		if (bool) {
			LoginIp loginIp = new LoginIp();
			loginIp.setLoginName(mes);
			loginIp.setLoginIp(strIp);
			loginIp.setLoginType(0);
			loginIp.setUserId(0);
			loginIp.setAddTime(DatetimeHandle.formatCurrentDate());
			dao.save(loginIp);
		}
	}
}
