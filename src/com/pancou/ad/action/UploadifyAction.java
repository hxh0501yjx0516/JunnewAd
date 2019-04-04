package com.pancou.ad.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.tools.ant.taskdefs.Delete;

import com.pancou.ad.form.AdCreativeForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.DeCompressUtil;
import com.pancou.ad.util.DeleteFileUtil;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.InCompressUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ShowDirTree;
import com.pancou.ad.vo.FileInstance;
import com.pancou.ad.vo.FileInstanceDir;
import com.pancou.ad.vo.Users;

@Entity
public class UploadifyAction extends BaseDispatchAction{
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = ShowDirTree.showTree("");
		request.setAttribute("treeList", list);
		List<Map<String, Object>> fileList = ShowDirTree.getSubFiles("");
		request.setAttribute("fileList", fileList);
		return mapping.findForward("list");
	}
	/**
	 * 依据目录查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> fileList = ShowDirTree.getSubFiles(this.getParameter("filePath"));
		request.setAttribute("fileList", fileList);
		request.setAttribute("filePath", this.getParameter("filePath"));
		return mapping.findForward("dir");
	}
	/**
	 *文件上传
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if("save".equals(this.getParameter("flag"))){
			try {
			AdCreativeForm adPlanForm = (AdCreativeForm)form;
			FormFile[] formFile = null;
	        //  得到所有的文件请求元素
			String dateTime = this.getParameter("dateTime");
	        Hashtable files = adPlanForm.getMultipartRequestHandler()
	                .getFileElements();
	        if (files != null && files.size() > 0)
	        {
	            //初始化FormFile
	            formFile = new FormFile[files.size()];
	            // 得到files的keys
	            Enumeration enums = files.keys();
	            String fileKey = null;
	            int i = 0;
	            // 遍历枚举
	           
	            while (enums.hasMoreElements())
	            {
	                // ȡ��key
	                fileKey = (String) (enums.nextElement());
	                // ��ʼ��ÿһ��FormFile(�ӿ�)
	                formFile[i] = (FormFile) files.get(fileKey);
	                // �ֱ��ϴ�
	                String path = "";
		            String subPath = "";
	                if(formFile !=null && !"".equals(formFile[i].getFileName())){
	                	if(!"".equals(this.getParameter("hidpath"))){
	                		subPath = this.getParameter("hidpath");
//	                		System.out.println("Configure.get('upload.img.url')--------------------------="+Configure.get("upload.img.url")+File.separator);
	                		//Configure.get("upload.img.url")
//	                		System.out.println("subPath-------------------------"+subPath);
	                		subPath = subPath.replace(Configure.get("upload.img.url")+File.separator, "");
	                	}else{
	                		subPath = FileManager.UPLOAD_AE_PATH+File.separator+dateTime;
	                	}
//	                	System.out.println("subPath--------------------------="+subPath);
	                	path = FileManager.uploadFile(request, formFile[i],subPath, true);
	                	if(!"".equals(path)){
	                		if(path.substring(path.lastIndexOf(".")+1).equals("zip") || path.substring(path.lastIndexOf(".")+1).equals("rar")){
	                			DeCompressUtil.deCompress(Configure.get("upload.img.url")+path,
	    	                			Configure.get("upload.img.url")+File.separator+FileManager.UPLOAD_PATH+File.separator+subPath);
	                		}
	                	}
	    			}
	                i++;
	            }
	            this.buildAjaxResult(request, this.SUCCESS_STATUS, "操作成功！",this.CALLBACKTYPE_CLOSECURRENT,"上传文件管理");
	        }else{
	        this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
	        }
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "操作失败！");
			}
			return mapping.findForward("ajaxDone");
			}
			
			request.setAttribute("filePath", this.getParameter("filePath"));
			request.setAttribute("target", this.getParameter("target"));
			return mapping.findForward("add");
	}
	/**
	 *下载文件
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String zipFileName ="";
		try {
		        BufferedInputStream bis = null;
		        BufferedOutputStream bos = null;
		        OutputStream fos = null;
		        InputStream fis = null;
		        String name =this.getParameter("fileName");
		      //����Ǵӷ�������ȡ����������ϵͳ�ľ��·��������  String filepath = servlet.getServletContext().getRealPath("/" + path);
//		        String  filepath=request.getSession().getServletContext().getRealPath("/")+"upload"+fileInstance.getPath();
		        String filepath = this.getParameter("filePath");
		        
//		        System.out.println("�ļ�·��"+filepath);
		        File uploadFile = new File(filepath);
		        if(uploadFile.isDirectory()){
		        	 String inputFileName = filepath;
		        	 name = filepath.substring(filepath.lastIndexOf(File.separator)+1);
//		        	 FileManager.createDir(Configure.get("upload.img.url")+File.separator+FileManager.UPLOAD_PATH+ File.separator+"temp");
		   		  	 zipFileName = inputFileName+".rar"; 
		   		  try {
		   			InCompressUtil.zip(inputFileName,zipFileName);
		   			uploadFile = new File(zipFileName);
		   		} catch (Exception e) {
		   			// TODO Auto-generated catch block
		   			e.printStackTrace();
		   		}
		        }
		        fis = new FileInputStream(uploadFile);
		        bis = new BufferedInputStream(fis);
		        fos = response.getOutputStream();
		        bos = new BufferedOutputStream(fos);
		        response.setHeader("Content-disposition",
		                           "attachment;filename=" +
		                           URLEncoder.encode(name+".rar", "utf-8"));
		        int bytesRead = 0;
		        byte[] buffer = new byte[8192];
		        while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
		            bos.write(buffer, 0, bytesRead);
		        }
		        bos.flush();
		        fis.close();
		        bis.close();
		        fos.close();
		        bos.close();
		        if(!"".equals(zipFileName)){
					DeleteFileUtil.delete(zipFileName);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
	/**
	 * 删除上传文件
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			String filePath = "";
			if("".equals(this.getParameter("filePath"))){
				filePath = Configure.get("upload.img.url")+File.separator+FileManager.UPLOAD_PATH+File.separator+FileManager.UPLOAD_AE_PATH;
			}else{
				filePath = this.getParameter("filePath");
			}
			boolean bool = DeleteFileUtil.delete(filePath);
			if(bool){
				this.buildAjaxResult(request, SUCCESS_STATUS, "操作成功！");
			}
			request.setAttribute(CALLBACKTYPE_REL,this.getParameter("rel"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "操作失败！");
		}
		return mapping.findForward("ajaxDone");
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userId =  request.getParameter("jsessionid");
		if(null != userId && !"".equals(userId)){
			Users users = (Users)request.getSession().getAttribute("user");
			if(users==null) {
				Users users2 = (Users)dao.findById(Users.class, Integer.parseInt(userId));
				request.getSession().setAttribute("user",users2);
			}
		}
		return super.execute(mapping, form, request, response);
	}

}
