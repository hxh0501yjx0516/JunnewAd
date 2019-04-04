package com.pancou.ad.iter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pancou.ad.dao.PlatDao;
import com.pancou.ad.util.Http;
import com.pancou.ad.vo.InterfaceBeiqing;
import com.pancou.ad.vo.ViewReadyBox;

/**
 * uri:
 * http://ma.junnew.com:8090/ad/servlet/BeiqingServlet?btime=20140123&etime=
 * 20140126
 * 
 * @author Or
 * 
 */
public class BeiqingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlatDao dao = new PlatDao();
	private String interAddr = "http://sync.7676.com/cpsync/sync.php?dbid=087";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String btime = request.getParameter("btime");
		String etime = request.getParameter("etime");

		interAddr += "&btime=" + btime + "&etime=" + etime + "&action=";

		byte[] result = Http.requestServer(interAddr, null, Http.REQUEST_POST,
				Http.ENCODING_UTF8, null);
		String jsonString = new String(result);
		List<InterfaceBeiqing> list = InterfaceBeiqing.parseJson(jsonString);

		for (InterfaceBeiqing bq : list) {
			if (!"*_--_*".equals(bq.getGunion())) {
				ViewReadyBox readyBox = (ViewReadyBox) dao.findById(
						ViewReadyBox.class, Integer.parseInt(bq.getGunion()));
				if (readyBox != null) {
					bq.setWebMasterName(readyBox.getWebMasterName());
				} else {
					bq.setWebMasterName("");
				}
			}

			dao.save(bq);
		}
		out.write(btime + " -- " + etime + " :");
		out.write("北青入库" + list.size() + "条");
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
