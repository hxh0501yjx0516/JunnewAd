package com.pancou.ad.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.pancou.ad.form.AdCreativeForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.FileInstance;
import com.pancou.ad.vo.FileInstanceDir;
import com.pancou.ad.vo.Users;

@Entity
public class UploadifyAction_bak extends BaseDispatchAction{
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageNum = 0;// ҳ��
		int numPerPage = 20;//ÿҳ��ʾ����
		if ("pager".equals(this.getParameter("flag"))) {
			// ��ҳ
			pageNum = Integer.parseInt(this.getParameter("pageNum"));
			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
		}
		Map<String,Object> parms = new HashMap<String, Object>();
		String condition = "";
		String sql = "select year y,month m from fileinstanceDir group by year,month order by year desc,month desc";
		List<Object> cols = new ArrayList<Object>();
		cols.add("y");
		cols.add("m");
		List<Object[]> ymList = dao.findBySql(sql+condition, parms,cols);
		List<Map<String,String>> dateList = new ArrayList<Map<String,String>>();
		for(int i=0;i<ymList.size();i++){
			Object[] object = (Object[])ymList.get(i);
			Map<String,String> ymMap = new HashMap<String, String>();
			ymMap.put("year",  object[0]+"");
			ymMap.put("month",  object[1]+"");
			dateList.add(ymMap);
		}
		request.setAttribute("dateList", dateList);
		String sql1 = "from FileInstanceDir d where d.pid = 0 ";
		List<FileInstanceDir> dirList = dao.findAll(sql1,condition+" order by id desc", parms);
		for(FileInstanceDir dir:dirList){
			parms.put("pid", dir.getId());
			List<FileInstanceDir> subDirList = dao.findAll("from FileInstanceDir d where d.pid=:pid",condition+" order by d.id desc", parms);
			List<Map<String, String>> subNameList = new ArrayList<Map<String,String>>();
			for(FileInstanceDir subDir:subDirList){
				Map<String,String> subNameMap = new HashMap<String, String>();
				subNameMap.put("id",subDir.getId()+"");
				subNameMap.put("name",subDir.getName());
				subNameList.add(subNameMap);
			}
			dir.setSubDir(subNameList);
		}
		parms.clear();
		request.setAttribute("dirList", dirList);
		
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(FileInstance.class, condition+" and f.status = 0 ",parms));
		String sql2 = "select f.*,d1.name dirName from FileInstance f left join FileInstanceDir d on f.type = d.id left join FileInstanceDir d1 on d.pid = d1.id where f.status = 0 ";
		cols.clear();
		cols.add(FileInstance.class);
		cols.add("dirName");
		List<Object[]>  fileList= dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql2+condition+" order by f.id desc",parms,cols);
		List<FileInstance> instanceList = new ArrayList<FileInstance>();
		for(int i=0;i<fileList.size();i++){
			Object[] object = (Object[])fileList.get(i);
			FileInstance fileInstance = (FileInstance)object[0];
			fileInstance.setDirName(object[1]+"");
			instanceList.add(fileInstance);
		}
		if (instanceList != null ) {
			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "instanceList", instanceList));
			
		}
		return mapping.findForward("list");
	}
	/**
	 * ����ʱ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward dir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int pageNum = 0;// ҳ��
//		int numPerPage = 20;//ÿҳ��ʾ����
//		if ("pager".equals(this.getParameter("flag"))) {
//			// ��ҳ
//			pageNum = Integer.parseInt(this.getParameter("pageNum"));
//			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
//		}
		Map<String,Object> parms = new HashMap<String, Object>();
		String condition = " and d.year =:year and d.month = :month ";
		parms.put("year", Integer.parseInt(this.getParameter("year")));
		parms.put("month", Integer.parseInt(this.getParameter("month")));
		
		List<Object> cols = new ArrayList<Object>();
		//����ϴ��ļ��б�
//		String sqlCount = "select count(f.id) from FileInstance f left join FileInstanceDir d on f.type = d.id where f.status = 0 ";
//		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(sqlCount+condition, parms));
		String sql2 = "select f.*,d1.name dirName from FileInstance f " +
				"left join FileInstanceDir d on f.type = d.id " +
				"left join FileInstanceDir d1 on d.pid = d1.id" +
				" where f.status = 0 ";
		cols.add(FileInstance.class);
		cols.add("dirName");
//		List<Object[]>  fileList= dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql2+condition+" order by f.id desc",parms,cols);
		List<Object[]>  fileList= dao.findBySql(sql2+condition+" order by f.id desc",parms,cols);
		List<FileInstance> instanceList = new ArrayList<FileInstance>();
		for(int i=0;i<fileList.size();i++){
			Object[] object = (Object[])fileList.get(i);
			FileInstance fileInstance = (FileInstance)object[0];
			fileInstance.setDirName(object[1]+"");
			instanceList.add(fileInstance);
		}
		if (instanceList != null ) {
//			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "instanceList", instanceList));
			request.setAttribute("instanceList", instanceList);
		}
		//����ϴ�������Ŀ�б�
		String sql1 = "from FileInstanceDir d where d.pid = 0 ";
		List<FileInstanceDir> dirList = dao.findAll(sql1,condition+" order by id desc", parms);
		for(FileInstanceDir dir:dirList){
			parms.put("pid", dir.getId());
			List<FileInstanceDir> subDirList = dao.findAll("from FileInstanceDir d where d.pid=:pid",condition+" order by id desc", parms);
			List<Map<String, String>> subNameList = new ArrayList<Map<String,String>>();
			for(FileInstanceDir subDir:subDirList){
				Map<String,String> subNameMap = new HashMap<String, String>();
				subNameMap.put("id",subDir.getId()+"");
				subNameMap.put("name",subDir.getName());
				subNameList.add(subNameMap);
			}
			dir.setSubDir(subNameList);
		}
		request.setAttribute("dirList", dirList);
		
		request.setAttribute("qryYear", this.getParameter("year"));
		request.setAttribute("qryMonth", this.getParameter("month"));
		return mapping.findForward("dir");
	}
	/**
	 * ������Ŀ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward details(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int pageNum = 0;// ҳ��
//		int numPerPage = 20;//ÿҳ��ʾ����
//		if ("pager".equals(this.getParameter("flag"))) {
//			// ��ҳ
//			pageNum = Integer.parseInt(this.getParameter("pageNum"));
//			numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
//		}
		Map<String,Object> parms = new HashMap<String, Object>();
		String condition = "";
		String dirId = "";
		if(!"".equals(this.getParameter("subDirId"))){
			dirId = this.getParameter("subDirId");
			condition = " and d.id = :dirId ";
		}else{
			dirId = this.getParameter("dirId");
			condition = " and d.pid = :dirId ";
		}
//		FileInstanceDir fileInstanceDir = (FileInstanceDir)dao.findById(FileInstanceDir.class, Integer.parseInt(this.getParameter("dirId")));
		parms.put("dirId", Integer.parseInt(dirId));
		String sql2 = "select f.*,d1.name dirName from FileInstance f " +
				"left join FileInstanceDir d on f.type = d.id " +
				"left join FileInstanceDir d1 on d1.id = d.pid " +
				" where f.status = 0 ";
		List<Object> cols = new ArrayList<Object>();
		cols.add(FileInstance.class);
		cols.add("dirName");
//		List<Object[]>  fileList= dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql2+condition+" order by f.id desc",parms,cols);
		List<Object[]>  fileList= dao.findBySql(sql2+condition+" order by f.id desc",parms,cols);
		List<FileInstance> instanceList = new ArrayList<FileInstance>();
		for(int i=0;i<fileList.size();i++){
			Object[] object = (Object[])fileList.get(i);
			FileInstance fileInstance = (FileInstance)object[0];
			fileInstance.setDirName(object[1]+"");
//			fileInstance.setDirName(fileInstanceDir.getName());
			instanceList.add(fileInstance);
		}
		if (instanceList != null ) {
//			this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "instanceList", instanceList));
			request.setAttribute("fileInstanceList", instanceList);
		}
		request.setAttribute("dirId", this.getParameter("dirId"));
		return mapping.findForward("details");
	}
	/**
	 * �ļ��ϴ�
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
	        // �õ����е��ļ�����Ԫ��
			String dirName = this.getParameter("uploadfolder");
			String dateTime = this.getParameter("dateTime");
			String subName = this.getParameter("subName");
	        Hashtable files = adPlanForm.getMultipartRequestHandler()
	                .getFileElements();
	        if (files != null && files.size() > 0)
	        {
	            // ��ʼ��FormFile
	            formFile = new FormFile[files.size()];
	            // �õ�files��keys
	            Enumeration enums = files.keys();
	            String fileKey = null;
	            int i = 0;
	            // ����ö��
	            String path = "";
	            int year =0;
	            int month = 0;
				int dirId = 0;
//				 if(!"".equals(this.getParameter("dirId"))){
//					 dirId = Integer.parseInt(this.getParameter("dirId"));
//				 }
					 //����Ŀ¼�ϴ��ļ�
//	            if(!"".equals(this.getParameter("year"))){
//	            	year = Integer.parseInt(this.getParameter("year"));
//	            	month = Integer.parseInt(this.getParameter("month"));
//	            }else{
//	            	Calendar cal = Calendar.getInstance();
//					year= cal.get(Calendar.YEAR );
//					 month = cal.get(Calendar.MONTH )+1;
//	            }
				year = Integer.parseInt(dateTime.split("-")[0]);
				month = Integer.parseInt(dateTime.split("-")[1]);
				String[] pname = new String[3];
				String[] pvalue = new String[3];
				pname[0] = "name";
				pname[1] = "year";
				pname[2] = "month";
				pvalue[0] =dirName.trim();
				pvalue[1] = String.valueOf(year);
				pvalue[2] = String.valueOf(month).trim();
				List<FileInstanceDir> instanceList = dao.findAll(FileInstanceDir.class, pname, pvalue);
				if(instanceList != null && instanceList.size()>0){
					//��ѯ��ĿĿ¼�Ƿ����
					FileInstanceDir instanceDir = instanceList.get(0);
					dirId = instanceDir.getId();
					//��ѯ��Ŀ¼�Ƿ����
					String[] pname1 = new String[4];
					String[] pvalue1 = new String[4];
					pname1[0] = "pid";
					pname1[1] = "year";
					pname1[2] = "month";
					pname1[3] = "name";
					pvalue1[0] =String.valueOf(dirId);
					pvalue1[1] = String.valueOf(year);
					pvalue1[2] = String.valueOf(month).trim();
					pvalue1[3] = String.valueOf(subName).trim();
					List<FileInstanceDir> instanceList1 = dao.findAll(FileInstanceDir.class, pname1, pvalue1);
					if(instanceList1 != null && instanceList1.size()>0){
						FileInstanceDir instanceDir1 = instanceList1.get(0);
						dirId = instanceDir1.getId();
					}else{
						FileInstanceDir fileDir1 = new FileInstanceDir();
			        	fileDir1.setName(subName);
			        	fileDir1.setYear(year);
			        	fileDir1.setMonth(month);
			        	fileDir1.setPid(dirId);
			        	fileDir1.setCreateTime(DatetimeHandle.formatCurrentDate());
			        	dirId = dao.save(fileDir1);
					}
				}else{
					FileInstanceDir fileDir = new FileInstanceDir();
		        	fileDir.setName(dirName);
		        	fileDir.setYear(year);
		        	fileDir.setMonth(month);
		        	fileDir.setPid(0);
		        	fileDir.setCreateTime(DatetimeHandle.formatCurrentDate());
		        	dirId = dao.save(fileDir);
		        	
		        	FileInstanceDir fileDir1 = new FileInstanceDir();
		        	fileDir1.setName(subName);
		        	fileDir1.setYear(year);
		        	fileDir1.setMonth(month);
		        	fileDir1.setPid(dirId);
		        	fileDir1.setCreateTime(DatetimeHandle.formatCurrentDate());
		        	dirId = dao.save(fileDir1);
				}
	            while (enums.hasMoreElements())
	            {
	                // ȡ��key
	                fileKey = (String) (enums.nextElement());
	                // ��ʼ��ÿһ��FormFile(�ӿ�)
	                formFile[i] = (FormFile) files.get(fileKey);
	                // �ֱ��ϴ�
	                if(formFile !=null && !"".equals(formFile[i].getFileName())){
//	    				if(!FileManager.isPhoto(formFile[i].getFileName())){
//	    					this.buildAjaxResult(request, FAILURE_STATUS, "�ϴ��ļ�����ӦΪjpeg|jpg|gif|png|swf��");
//	    					return mapping.findForward("upload");
//	    				}
	    				path = FileManager.uploadFile(request,formFile[i],FileManager.UPLOAD_AE_PATH+ File.separator+year+File.separator+month+ File.separator+dirName+ File.separator+subName+ File.separator,true);
	    				if("".equals(path)){
	    					this.buildAjaxResult(request, FAILURE_STATUS, formFile[i].getFileName()+"�ļ��ϴ�ʧ�ܣ�");
	    					return mapping.findForward("ajaxDone");
	    				}
	    				String[] pn=new String[3];
	    				String[] pv = new String[3];
	    				pn[0] = "name";
	    				pn[1] = "type";
	    				pn[2] = "status";
	    				pv[0] = formFile[i].getFileName();
	    				pv[1] = String.valueOf(dirId);
	    				pv[2] = "0";
	    				List<FileInstance> fileInstanceList = dao.findAll(FileInstance.class,pn,pv);
	    				if(fileInstanceList!=null && fileInstanceList.size()>0){
	    					//��ֵ�����
	    				}else{
	    					FileInstance fileInstance = new FileInstance();
		    				fileInstance.setName(formFile[i].getFileName());
		    				fileInstance.setPath(path);
		    				fileInstance.setWebPath(Configure.get("img.url")+path);
		    				fileInstance.setType(dirId);
		    				fileInstance.setCreateTime(DatetimeHandle.formatCurrentDate());
		    				fileInstance.setStatus(0);
		    				fileInstance.setUserId(((Users)request.getSession().getAttribute("user")).getUserId());
		    				fileInstance.setUserName(((Users)request.getSession().getAttribute("user")).getUsername());
		    				dao.save(fileInstance);
	    				}
	    			}
	                i++;
	            }
	            this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ���",this.CALLBACKTYPE_CLOSECURRENT,"�ϴ��ļ�����");
	        }else{
	        this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
	        }
	        response.getWriter().write("");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, this.FAILURE_STATUS, "����ʧ�ܣ�");
			}
			
			return mapping.findForward("ajaxDone");
			}
			request.setAttribute("year", this.getParameter("year"));
			request.setAttribute("month", this.getParameter("month"));
			if( !"".equals(this.getParameter("dirId"))){
				FileInstanceDir fileInstanceDir=(FileInstanceDir)dao.findById(FileInstanceDir.class,Integer.parseInt(this.getParameter("dirId")));
				request.setAttribute("dirName", fileInstanceDir.getName());
				
				request.setAttribute("year", fileInstanceDir.getYear());
				request.setAttribute("month", fileInstanceDir.getMonth());
			}
			request.setAttribute("dirId", this.getParameter("dirId"));
			return mapping.findForward("add");
	}
	/**
	 * �����ļ�
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			FileInstance fileInstance = (FileInstance)dao.findById(FileInstance.class, Integer.parseInt(this.getParameter("instanceId")));
		        BufferedInputStream bis = null;
		        BufferedOutputStream bos = null;
		        OutputStream fos = null;
		        InputStream fis = null;
		      //����Ǵӷ�������ȡ����������ϵͳ�ľ��·��������  String filepath = servlet.getServletContext().getRealPath("/" + path);
//		        String  filepath=request.getSession().getServletContext().getRealPath("/")+"upload"+fileInstance.getPath();
		        String filepath = Configure.get("upload.img.url")+fileInstance.getPath();
//		        System.out.println("�ļ�·��"+filepath);
		        File uploadFile = new File(filepath);
		        fis = new FileInputStream(uploadFile);
		        bis = new BufferedInputStream(fis);
		        fos = response.getOutputStream();
		        bos = new BufferedOutputStream(fos);
		        response.setHeader("Content-disposition",
		                           "attachment;filename=" +
		                           URLEncoder.encode(fileInstance.getName(), "utf-8"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ɾ���ϴ��ļ�
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			FileInstance fileInstance = (FileInstance)dao.findById(FileInstance.class, Integer.parseInt(this.getParameter("instanceId")));
			fileInstance.setStatus(1);
			dao.update(fileInstance);
			this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			request.setAttribute(CALLBACKTYPE_REL,this.getParameter("rel"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
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
