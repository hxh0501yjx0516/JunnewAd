package com.pancou.ad.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;

@Entity
public class FileUploadUtil {
	
	public static String fileUpload(HttpServletRequest request,FormFile formFile,String subPath) throws Exception{
		try{	
			//RealPath example
			//UPLOAD_PATH/upload/fanli/img/adplan/aaa.jpg
			String fullPath = ""; 
			String sqlPath = "";
			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH);
			
			if (!"".equals(subPath) && subPath !=null){
				if (!subPath.endsWith(File.separator)){
					subPath += File.separator;
				}
				fullPath = File.separator + subPath + year + File.separator + month;
				sqlPath = File.separator + subPath + year + File.separator + month;
			} else {
				System.out.println("不确定的上传子路径！");
			}
			
			String realPath = request.getSession().getServletContext().getRealPath(fullPath);
			mkDir(realPath);
			
			String fileName = formFile.getFileName();
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = File.separator + System.currentTimeMillis() + suffixName;
			InputStream fileStream = formFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(realPath+newFileName);
			byte[] b = new byte[1024];
			while (fileStream.read(b) != -1){
				fos.write(b);
				fos.flush();
			}
			fileStream.close();
			fos.close();
			
			return realPath + newFileName;
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public static void mkDir(String dirPath){
		File file = new File(dirPath);
		if (!file.exists()){
			file.mkdirs();
		}
	}
	
}
