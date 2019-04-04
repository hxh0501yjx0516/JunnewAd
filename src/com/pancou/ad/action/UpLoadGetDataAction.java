package com.pancou.ad.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pancou.ad.dao.Dao;
import com.pancou.ad.dao.UpLoadImportDataDao;
import com.pancou.ad.util.ReadExcel;
import com.pancou.ad.util.UploadBean;
import com.pancou.ad.vo.AdBox;
import com.pancou.ad.vo.AdBoxCount;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.WebMaster;

/**
 * 导入数据
 * 
 * @author Administrator
 */
@Entity
public class UpLoadGetDataAction extends DispatchAction {

	DecimalFormat df = new DecimalFormat("#.00");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	@ManyToOne
	UpLoadImportDataDao updao = new UpLoadImportDataDao();
	@ManyToOne
	Dao dao = new Dao();

	public ActionForward initAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("list");
	}

	/**
	 * 文件上传，向sendtask表插入数据
	 */
	@SuppressWarnings("unchecked")
	public ActionForward doAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// CpUser user = (CpUser) request.getSession().getAttribute("user");
		// if (user == null) {// 非法登录
		// request.setAttribute("mes", "请重新登录!");
		// return mapping.findForward("logout");
		// }

		// 从session中取文件名
		String filen = (String) request.getAttribute("filename");
		if (filen != null) {
			System.out.println("filename:%%%%%%%%%%%%%%%%" + filen);
			return mapping.findForward("ok");

		}
		String fileName = (String) request.getSession()
				.getAttribute("fileName");
		if (fileName == null || "".equals(fileName)) {
			// 判断文件是否上传
			// return mapping.findForward("er_file");
			request.setAttribute("statusCode", 300);
			request.setAttribute("message", "请先上传文件!");
			request.setAttribute("callbackType", "closeCurrent");
			return mapping.findForward("ajaxDone");
		}

		// ========================== start 读文件
		// ============================================
		ArrayList al = ReadExcel.readExcel(request, fileName);
		// String tablename = "report_";

		for (int i = 0; i < al.size(); i++) {
			ReportCount report = new ReportCount();

			HashMap<Integer, String> hs = (HashMap) al.get(i);
			String excelDate = hs.get(0); // excel中 “日期”
			String excelAdplanId = hs.get(1);// excel中 “广告计划ID”
			String excelReadyBoxId = hs.get(2); // excel中 “ReadyBoxId”
			String excelCount = hs.get(3); // excel中 “结算值”
			String excelCountTo = hs.get(4); // excel中 “考核值”
			Users suser = (Users) request.getSession().getAttribute("user");
			// ReadyBox 表
			ArrayList<ReadyBox> readyboxList = (ArrayList) updao.finbByID(
					Integer.parseInt(excelReadyBoxId), suser);
			ReadyBox rb = new ReadyBox();
			if (readyboxList != null && readyboxList.size() > 0) {
				rb = readyboxList.get(0);
			} else {
				// 数据库中不存在此数据，忽略并上传下一条数据
				// 分用户组后，检查上传数据是否属于该用户组内数据，不属于则不上传
				System.out.println("判断readyboxId是否存在（无投放上传）：" + (i + 1)
						+ "行----日期：" + excelDate + "----广告计划ID："
						+ excelAdplanId + "----ReadyBoxId:" + excelReadyBoxId
						+ " 数据在投放列表中不存在(创意ID：" + rb.getAdCreativeId()
						+ " ，广告位ID：" + rb.getAdBoxId() + ")");
				continue;
			}
			// ReportCount 表
			ArrayList<ReportCount> reportCountList = (ArrayList) updao
					.findReportCount(rb.getAdBoxId(), rb.getAdCreativeId(),
							excelDate);
			if (reportCountList != null && reportCountList.size() > 0) {
				// 数据库中存在此数据，忽略并上传下一条数据
				System.out.println("判断reportcount表中数据是否存在（重复上传）：" + (i + 1)
						+ "行----日期：" + excelDate + "----广告计划ID："
						+ excelAdplanId + "----ReadyBoxId:" + excelReadyBoxId
						+ " 数据已存在(创意ID：" + rb.getAdCreativeId() + " ，广告位ID："
						+ rb.getAdBoxId() + ")");
				continue;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// AdBoxCount 表
			ArrayList adBoxCountList = (ArrayList) updao.finbByIDAndTime(
					Integer.parseInt(excelReadyBoxId), excelDate);
			AdBoxCount adBoxCount = new AdBoxCount();
			if (adBoxCountList != null && adBoxCountList.size() > 0) {
				adBoxCount = (AdBoxCount) adBoxCountList.get(0);
			}

			// AdCreative 表
			ArrayList adCreativeList = (ArrayList) updao.finbByIDAdCreative(rb
					.getAdCreativeId());
			AdCreative adCreat = new AdCreative();

			if (adCreativeList != null && adCreativeList.size() > 0) {
				adCreat = (AdCreative) adCreativeList.get(0);
			}

			// AdBox 表
			ArrayList adBoxList = (ArrayList) updao.finbByIDAdBox(rb
					.getAdBoxId());
			AdBox adBox = new AdBox();
			if (adBoxList != null && adBoxList.size() > 0) {
				adBox = (AdBox) adBoxList.get(0);
			}

			// WebMaster表
			ArrayList webMasterList = (ArrayList) updao.finbByIDWebMaster(adBox
					.getWebMasterId());
			WebMaster webMaster = new WebMaster();
			if (webMasterList != null && webMasterList.size() > 0) {
				webMaster = (WebMaster) webMasterList.get(0);
			}

			// Url表
			ArrayList urlList = (ArrayList) updao.finbByIDUrl(adBox.getUrlId());
			UrlAddress urlAddress = new UrlAddress();
			if (urlList != null && urlList.size() > 0) {
				urlAddress = (UrlAddress) urlList.get(0);
			}

			// AdPlan表
			ArrayList adPlanList = (ArrayList) updao.finbByIDAdPlan(Integer
					.parseInt(excelAdplanId));
			AdPlan adPlan = new AdPlan();
			if (adPlanList != null && adPlanList.size() > 0) {
				adPlan = (AdPlan) adPlanList.get(0);
			}
			// System.out.println("AdplanId:"+Integer.parseInt(excelAdplanId));
			// AdPlanCount表
			ArrayList adPlanCList = (ArrayList) updao.finbByIDAdPlanC(rb
					.getAdPlanCycleId());// ReadyBoxId
			AdPlanCycle adPlanc = new AdPlanCycle();
			if (adPlanCList != null && adPlanCList.size() > 0) {
				adPlanc = (AdPlanCycle) adPlanCList.get(0);
			}

			// Users表
			ArrayList userList = (ArrayList) updao.finbByIDUsers(webMaster
					.getUserId());
			Users users = new Users();
			if (userList != null && userList.size() > 0) {
				users = (Users) userList.get(0);
			}

			int count = 0;
			if (excelCountTo != null && !"".equals(excelCount)) {
				count = Integer.parseInt(excelCount);
			}

			int discount = (rb.getDiscount()) / 100;

			report.setAdBoxId(rb.getAdBoxId());
			report.setReadyBoxId(rb.getReadyBoxId());
			report.setAdBoxName(adBox.getAdBoxName());
			report.setAdCreativeId(rb.getAdCreativeId());
			report.setAdCreativeImg(adCreat.getAdCreativeImg());
			report.setAdCreativeName(adCreat.getAdCreativeName());
			report.setAdCreativeUrl(adCreat.getAdCreativeUrl());
			report.setAddTime(sdf.format(new Date()));
			report.setAdplanCycleId(rb.getAdPlanCycleId());
			report.setAdplanCycleName(adPlanc.getAdPlanCycleName());
			report.setAdplanId(Integer.parseInt(excelAdplanId));
			report.setAdplanName(adPlan.getAdPlanName());
			report.setBrowse(adBoxCount.getBrowse());
			report.setBrowseTrue(adBoxCount.getBrowseTrue());
			report.setCount(count);

			report.setCountTo(Integer.parseInt(excelCountTo));
			report.setDiscount(rb.getDiscount());
			report.setIp(adBoxCount.getIp());
			if (rb.getPayTypeId() == 1) { // CPM
				report.setMoney(Float.parseFloat(df.format(count
						* adPlanc.getCustomerPrice() / 1000)));
			} else {
				report.setMoney(Float.parseFloat(df.format(count
						* adPlanc.getCustomerPrice())));
			}

			report.setPayId(0);
			report.setPayType(rb.getPayTypeId());
			report.setPv(adBoxCount.getPv());
			report.setRealCount(Integer.parseInt(excelCountTo)); // 有效值
																	// 等于考核植，AE自己确定
			if (rb.getPayTypeId() == 1) { // CPM
				report.setRealMoney(Float.parseFloat(df.format((rb
						.getWebMasterPrice())
						* Integer.parseInt(excelCountTo)
						/ 1000))); // 有效佣金 CPM
			} else {
				report.setRealMoney(Float.parseFloat(df.format((rb
						.getWebMasterPrice()) * Integer.parseInt(excelCountTo)))); // 有效佣金
			}
			report.setReportStatus(0);
			report.setReportTime(excelDate);// Excel中的时间
			report.setUserId(webMaster.getUserId());
			report.setUserName(users.getUsername());
			report.setUv(adBoxCount.getUv());
			report.setWebMasterId(adBox.getWebMasterId());
			report.setWebMasterName(webMaster.getWebMasterName());
			report.setWebMasterPrice(rb.getWebMasterPrice());

			report.setUrlName(urlAddress.getUrlName());
			report.setUrlId(urlAddress.getUrlId());

			String tablename = "report_" + excelAdplanId;// 表名
			try {
				// Report_XX
				updao.saveReport(report, tablename);
				// ReportCount表
				dao.save(report);

			} catch (Exception e) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", "数据入库失败!");
				request.setAttribute("callbackType", "closeCurrent");
				return mapping.findForward("ajaxDone");
			}
		}

		// 清空session中的 文件名
		request.getSession().setAttribute("fileName", "");
		// ========================== end 读文件
		// ============================================

		request.setAttribute("statusCode", 200);
		request.setAttribute("message", "数据导入成功!");
		request.setAttribute("callbackType", "closeCurrent");

		return mapping.findForward("ajaxDone");
		// return mapping.findForward("ok");

	}

	/**
	 * 上传文件（JSP上传）废弃
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadOld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// CpUser user = (CpUser)request.getSession().getAttribute("user");
		// if(user==null) {//非法登录
		// request.setAttribute("mes", "请重新登录!");
		// return mapping.findForward("logout") ;
		// }
		ServletInputStream in;// 输入流
		FileOutputStream fileOut;// 输出
		String content = request.getContentType();
		String remoteAddr = request.getRemoteAddr();// 客户端网络地址
		String serverName = request.getServerName();// 服务器名字
		String realPath = request.getRealPath(serverName);

		realPath = realPath.substring(0, realPath.lastIndexOf(File.separator));

		// 创建根路径保存文件
		String rootPath = realPath + File.separator + "upload" + File.separator;
		// 定义上传文件的最大字节数
		int maxSize = 1024 * 1024 * 5;
		try {
			if (content.indexOf("multipart/form-data") >= 0) {

				in = request.getInputStream();
				int formDataLength = request.getContentLength();
				if (formDataLength > maxSize) {
					// 最大5M
					request.setAttribute("sc", 300);
					request.setAttribute("msg", "文件最大只能上传5M！");
					return mapping.findForward("add_txt");
				}
				byte databyte[] = new byte[formDataLength];
				int byread = 0;
				int totalbyread = 0;
				while (totalbyread < formDataLength) {
					byread = in.read(databyte, totalbyread, formDataLength);
					totalbyread += byread;
				}
				String file = new String(databyte);

				// 取得上传文件名
				String saveFile = file
						.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,
						saveFile.indexOf("\""));

				if ("".equals(saveFile) || saveFile == null) {
					// 未选择上传文件
					request.setAttribute("sc", 300);
					request.setAttribute("msg", "请选择文件！");
					return mapping.findForward("add_txt");

				}

				int lastIndex = content.lastIndexOf("=");
				// 取得数据的分割字符串
				String boundary = content.substring(lastIndex + 1,
						content.length());
				String fileName = rootPath + sdf.format(new Date()) + saveFile;
				String type = saveFile.substring(saveFile.indexOf("."),
						saveFile.length());
				if (!".xls".equals(type) && !".xlsx".equals(type)) {

					request.setAttribute("sc", 300);
					request.setAttribute("msg", "只能上传.xls 或 .xlsx 格式文件！");
					return mapping.findForward("add_txt");
				}

				int pos;
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				// 取得文件数据的开始位置
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;

				// 检查上传文件是否存在
				File checkFile = new File(fileName);
				if (checkFile.exists()) {
					System.out.println("该文件已存在");
				}
				File checkDir = new File(rootPath);
				if (checkDir.exists()) {
					checkDir.mkdir();
				}

				// 输出文件的写出类
				fileOut = new FileOutputStream(fileName);
				fileOut.write(databyte, startPos, (endPos - startPos));
				fileOut.close();
				request.setAttribute("fileName", fileName);
				request.getSession().setAttribute("fileName", fileName);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		request.setAttribute("mes", "文件上传成功！");
		request.setAttribute("sc", 200);
		request.setAttribute("msg", "上传成功！");
		return mapping.findForward("list");

	}

	/**
	 * 下载模板
	 * 
	 * @param filePath
	 * @param response
	 * @param isOnLine
	 * @throws Exception
	 */
	public void downLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String filePath = request.getRealPath("/") + "/down/" + "download.xls";// 取当前系统路径
		boolean isOnLine = false;
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (isOnLine) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition",
					"inline; filename=" + f.getName());
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ f.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

	/**
	 * 上传文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadBean mb = new UploadBean();

		String filePath = request.getRealPath("/") + "upload" + File.separator;// 取当前系统路径

		mb.setObjectPath(filePath);
		mb.setSourceFile(request);
		String[] filename = new String[10];
		String filepath = mb.getObjectPath();
		filename = mb.getObjectFileName();
		// System.out.println(filename[0]);
		// 保存文件路径+名
		request.getSession().setAttribute("fileName", filepath + filename[0]);

		request.setAttribute("filename", filename[0]);
		request.setAttribute("mes", "文件上传成功！");
		request.setAttribute("sc", 200);
		request.setAttribute("msg", "上传成功！");
		return mapping.findForward("list");
	}

}
