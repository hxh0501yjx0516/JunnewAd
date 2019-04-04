package com.pancou.ad.action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pancou.ad.util.Configure;
import com.pancou.ad.util.FileManager;

@Entity
public class SnapshotServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SnapshotServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String path = Configure.get("upload.img.url") + File.separator
				+ FileManager.UPLOAD_SNAPSHOT_PATH + File.separator + year
				+ File.separator + month + File.separator + day;
		File file1 = new File(path);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		// �ļ����ϴ�����
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload fileload = new ServletFileUpload(factory);

				// ��������ļ��ߴ磬������4MB
				fileload.setSizeMax(4194304);
				List<FileItem> files = fileload.parseRequest(request);
				Iterator<FileItem> iterator = files.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						// System.out.println("�?����Ϊ: " + name + "ֵΪ: " +
						// value);
					} else {
						// ��û���ļ�����ļ������·��
						String filename = item.getName();
						if (filename != null) {
							File file = new File(filename);
							// �����ļ�����
							// String sux =
							// file.getName().substring(file.getName().lastIndexOf("."));
							// String realName =
							// File.separator+System.currentTimeMillis()+sux;
							File filetoserver = new File(path, file.getName());
							item.write(filetoserver);
							// System.out.println("�ļ� " +
							// filetoserver.getName() + " �ϴ��ɹ�!!");
						}
					}
				}
			} catch (Exception e2) {
				e2.getStackTrace();
			}
		}
	}

	/**
	 * �ڷ������˻�ȡ���͹���������
	 * 
	 * @param request
	 * @return
	 */
	public String receiveContent(HttpServletRequest request) {
		int a = 0;
		byte[] b = new byte[4096];
		String result = "";
		try {
			ServletInputStream sis = request.getInputStream();
			int line = sis.readLine(b, 0, b.length);
			while (line != -1) {
				result = result + new String(b, 0, line);
				System.out.println(result);
				line = sis.readLine(b, 0, b.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
