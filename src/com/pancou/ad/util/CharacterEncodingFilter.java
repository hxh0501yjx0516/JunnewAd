package com.pancou.ad.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.vo.LoginIp;

@Entity
public class CharacterEncodingFilter implements Filter {

	private String encoding = null;

	public void destroy() {
		// TODO Auto-generated method stub
		this.encoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {

			String clientEncoding = request.getCharacterEncoding();
			if (clientEncoding == null && encoding != null) {
				request.setCharacterEncoding(encoding);
			}
			
			HttpServletRequest request1 = (HttpServletRequest)request;
			String ip = request1.getHeader("x-forwarded-for");
			 if (ip == null){
				 ip = request1.getRemoteAddr();
			    }
//			 if(ip.matches("192.168.1.127")){
//				 ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//				 return ;
//			 }
			 PlatDao dao = new PlatDao();
			 Map<String,Object> parms = new HashMap<String, Object>();
			 parms.put("type", 0);
			 List<LoginIp> list = dao.findAll("from LoginIp where loginType = :type","",parms);
			 if(list !=null && list.size()>=0){
				 for(LoginIp loginIp:list){
					 if(ip.matches(loginIp.getLoginIp().trim())){
//						Thread.sleep(300000);
						 ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						 return ;
					 }
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = filterConfig.getInitParameter("encoding");
	}

}
