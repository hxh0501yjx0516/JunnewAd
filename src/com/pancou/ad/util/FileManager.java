package com.pancou.ad.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

@Entity
public class FileManager {
	
	public static final int BUFFER_SIZE = 1024;
	public static final String UPLOAD_PATH = "img";
	public static final String UPLOAD_CMS_PATH = "cms";
	public static final String UPLOAD_SNAPSHOT_PATH = "snapshot";
	public static final String UPLOAD_ADPLAN_PATH = "adplan";
	public static final String UPLOAD_SHOP_PATH = "shop";
	public static final String UPLOAD_AE_PATH = "ae";
	/**
	 * ��������ϴ��ļ� 
	 * @param request
	 * @param formFile �ϴ��ļ�FormFile
	 * @param subDir �ϴ��ļ�Ŀ¼���粻��д�򱣴����Զ����ͼƬ��Ŀ¼�£�
	 * @return �ϴ��ļ��ķ�����·��
	 * @throws Exception
	 */
	public static String uploadFile(HttpServletRequest request,FormFile formFile,String subDir,boolean bool) throws Exception{
		try {
			Calendar cal = Calendar.getInstance();
			String systemUrl = request.getSession().getServletContext().getRealPath("/")+"upload";
			int year = cal.get(Calendar.YEAR );
			int month = cal.get(Calendar.MONTH )+1;
			String realPath = "";
			String addPath ="";
			if(null!=subDir && !"".equals(subDir)){
				//���dir�����ļ��ָ����β���Զ�����ļ��ָ���     
		        if(!subDir.endsWith(File.separator)){
		        	subDir = subDir+File.separator;     
		        }
				addPath = File.separator+UPLOAD_PATH+File.separator+subDir;
				if("".equals(Configure.get("upload.img.url"))){
					realPath =systemUrl +addPath;
				}else{
					realPath =Configure.get("upload.img.url") +addPath;
				}
			}else{
				 addPath = File.separator+UPLOAD_PATH+ File.separator+year+File.separator+month;
				 if("".equals(Configure.get("upload.img.url"))){
						realPath =systemUrl +addPath;
					}else{
						realPath =Configure.get("upload.img.url") +addPath;
					}
			}
			String outFileName = formFile.getFileName();
			createDir(realPath);
			String sux = outFileName.substring(outFileName.lastIndexOf("."));
			String realName = bool?outFileName:File.separator+System.currentTimeMillis()+sux;
			BufferedInputStream bis = new BufferedInputStream(formFile.getInputStream());
			FileOutputStream fos=new FileOutputStream(realPath+realName);
			
			byte[] b=new byte[BUFFER_SIZE];
			while(bis.read(b)!=-1)
			{
				fos.write(b);
				fos.flush();
			}
			bis.close();
			fos.close();
			return addPath+realName;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void createDir(String dirPath) throws Exception {
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	//�ж��ǲ���һ��ͼƬ�ļ�����
	public static boolean isPhoto(String fileName) {
	        if (fileName.equals(""))
	            return false;
	        if ((fileName.toLowerCase().endsWith(".jpeg")) || (fileName.toLowerCase().endsWith(".jpg")) || (fileName.toLowerCase().endsWith(".gif")) || (fileName.toLowerCase().endsWith(".png")) || (fileName.toLowerCase().endsWith(".bmp")) || (fileName.toLowerCase().endsWith(".swf")))
	            return true;
	        else
	            return false;
	    }
}
