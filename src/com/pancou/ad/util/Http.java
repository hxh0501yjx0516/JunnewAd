package com.pancou.ad.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HttpUrlConnection 工具类
 * 
 * @author Administrator
 * 
 */
public class Http {
	private static final String TAG = "TAG";

	private static final int CONNECTION_TIMEOUT = 10 * 1000;
	private static final int CONNECTION_READTIME = 10 * 1000;

	public static final String REQUEST_GET = "GET";
	public static final String REQUEST_POST = "POST";

	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_ISO8859_1 = "ISO8859-1";

	private static final String SESSIONID = "";

	/**
	 * 私有默认构造方法
	 */
	private Http() {
	}

	/**
	 * 请求远程连接，并取得返回信息
	 * 
	 * @param requestURI
	 * @param paramsMap
	 * @param requestMode
	 * @param encoding
	 * @return new byte[1] 网络异常
	 */
	public static byte[] requestServer(String requestURI,
			Map<String, Object> paramsMap, String requestMode, String encoding,
			Map<String, String> headersMap) {

		// requestParams 处理
		String paramsString = "";
		if (paramsMap != null && !paramsMap.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			Iterator<String> it = paramsMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = paramsMap.get(key) + "";
				try {
					sb.append(key).append("=")
							.append(URLEncoder.encode(value, encoding))
							.append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			paramsString = sb.deleteCharAt(sb.length() - 1).toString();
		}

		// requestURI 请求
		URL url = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		BufferedInputStream in = null;
		try {
			url = new URL(requestURI);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMode);
			connection.setConnectTimeout(CONNECTION_TIMEOUT);
			connection.setReadTimeout(CONNECTION_READTIME);
			connection.setDoInput(true);

			if (headersMap != null && !headersMap.isEmpty()) {
				for (Map.Entry<String, String> entry : headersMap.entrySet()) {
					connection.setRequestProperty(entry.getKey(),
							entry.getValue());
				}
			}

			if (requestMode == Http.REQUEST_POST) {
				connection.setDoOutput(true);
				out = connection.getOutputStream();
				out.write(paramsString.getBytes());
			}

			connection.connect();

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedInputStream(connection.getInputStream());
				return getByteArrayFromStream(in);
			} else {
				System.out.println("responseCode: " + responseCode);
			}
		} catch (IOException e) {
			System.out.println("connection.connect()  failed");
			return new byte[1];
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}

		return new byte[0];
	}

	/**
	 * 获取输入流字节数组
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static byte[] getByteArrayFromStream(InputStream in)
			throws IOException {
		int length = in.available();
		ByteArrayOutputStream out = new ByteArrayOutputStream(
				length > 1024 ? length : 1024);

		byte[] buf = new byte[1024];
		int len = -1;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.flush();

		return out.toByteArray();
	}

	/**
	 * 请求远程连接，以POST方式提交数据，数据类型为 multipart/form-data
	 * 
	 * @param requestURI
	 * @param paramsMap
	 * @param fileData
	 * @param suffix
	 *            eg: .png
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String postFormData(String requestURI,
			Map<String, String> paramsMap, byte[] fileData, String suffix,
			String encoding) throws Exception {

		String enterNewline = "\r\n";
		String fix = "--";
		String boundary = "######";
		// 标示表单提交是有文本和文件类型
		String MULTIPART_FORM_DATA = "multipart/form-data";

		URL url = new URL(requestURI);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(REQUEST_POST);
		// 设置HTTP请求的头文件
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection
				.setRequestProperty(
						"Accept",
						"image/gif, image/png,image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash,"
								+ "application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint,*/*");
		connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
		connection.setRequestProperty("Charset", encoding);
		connection.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
				+ ";boundary=" + boundary);
		connection.addRequestProperty("Cookie", "ASP.NET_SessionId="
				+ SESSIONID);
		DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
		Set<String> keySet = paramsMap.keySet();
		Iterator<String> it = keySet.iterator();

		while (it.hasNext()) {
			String key = it.next();
			Object value = paramsMap.get(key);
			ds.writeBytes(fix + boundary + enterNewline);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\"" + key
					+ "\"" + enterNewline);
			ds.writeBytes(enterNewline);
			ds.write(value.toString().getBytes(encoding));
			ds.writeBytes(enterNewline);
		}

		// 完成文件提交的功能
		if (fileData != null && fileData.length > 0) {
			ds.writeBytes(fix + boundary + enterNewline);
			ds.writeBytes("Content-Disposition: form-data; " + "name=\""
					+ "form1" + "\"" + "; filename=\""
					+ System.currentTimeMillis() + ".png" + "\"" + enterNewline);
			ds.writeBytes(enterNewline);
			ds.write(fileData);
			ds.writeBytes(enterNewline);
		}
		ds.writeBytes(fix + boundary + fix + enterNewline);
		ds.flush();
		ds.close();
		return changeInputStream(connection.getInputStream(), encoding);
	}

	/**
	 * 将一个输入流转换为字符串
	 * 
	 * @param in
	 * @param encoding
	 * @return String
	 */
	private static String changeInputStream(InputStream in, String encoding) {
		byte[] buf = new byte[1024];
		int len = 0;

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			return new String(out.toByteArray(), encoding);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return "";
	}
}
