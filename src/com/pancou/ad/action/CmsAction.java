package com.pancou.ad.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.pancou.ad.form.CmsForm;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.Cms;
import com.pancou.ad.vo.CmsClass;

@Entity
public class CmsAction extends BaseDispatchAction{
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
		if(!"".equals(this.getParameter("qrytype"))){
			condition +=" and c.cmsClassId =:classId ";
			parms.put("classId",Integer.parseInt(this.getParameter("qrytype")));
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(Cms.class, condition, parms));
		String sql = "select c.*,cc.cmsClassName from cms c left join cmsClass cc on c.cmsClassId = cc.cmsClassId where 1=1 ";
		List<Object> cols = new ArrayList<Object>();
		cols.add(Cms.class);
		cols.add("cmsClassName");
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by c.cmsId desc",parms,cols);
		List<Cms> list = new ArrayList<Cms>();
		String url = Configure.get("img.url");
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			Cms cms = (Cms)object[0];
			String imgUrl = cms.getCmsImg();
			cms.setCmsImg(url+imgUrl);
			cms.setCmsClassName(object[1]+"");
			list.add(cms);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		request.setAttribute("cmsClassId",this.getParameter("qrytype"));
		request.setAttribute("qrytype",this.getParameter("qrytype"));
		request.setAttribute("cmsClassList",dao.findAll(CmsClass.class));
		return mapping.findForward("list");
	}
	/**
	 * ���ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			try {
			CmsForm cmsForm = (CmsForm)form;
			FormFile formFile = cmsForm.getFormFile();
			String path = "";
			if(formFile !=null && !"".equals(formFile.getFileName())){
				if(!FileManager.isPhoto(formFile.getFileName())){
					this.buildAjaxResult(request, FAILURE_STATUS, "�ϴ��ļ�����ӦΪjpeg|jpg|gif|png��");
					return mapping.findForward("upload");
				}
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR );
				int month = cal.get(Calendar.MONTH )+1;
				path = FileManager.uploadFile(request,formFile,FileManager.UPLOAD_CMS_PATH+ File.separator+year+File.separator+month,false);
				if("".equals(path)){
					this.buildAjaxResult(request, FAILURE_STATUS, "�ļ��ϴ�ʧ�ܣ�");
					return mapping.findForward("upload");
				}
			}
			Cms cms = new Cms();
			cms.setCmsName(this.getParameter("name"));
			cms.setCmsContent(this.getParameter("content"));
			cms.setCmsImg(path);
			cms.setCmsClassId(Integer.parseInt(this.getParameter("cmsClassId")));
			cms.setCmsFlag(Integer.parseInt(this.getParameter("cmsFlag")));
			cms.setCmsStatus(Integer.parseInt(this.getParameter("state")));
			cms.setAddTime(DatetimeHandle.formatCurrentDate());
			dao.save(cms);
			this.buildAjaxResult(request,SUCCESS_STATUS,"�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"�����б�");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("upload");
		}
		request.setAttribute("cmsClassId",this.getParameter("cmsClassId"));
		request.setAttribute("cmsClassList",dao.findAll(CmsClass.class));
		return mapping.findForward("add");
	}
	/**
	 * �޸�ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			Cms cms = (Cms)dao.findById(Cms.class, Integer.parseInt(this.getParameter("cid")));
			if ("editState".equals(this.getParameter("flag"))) {
				cms.setCmsStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(cms))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if ("editFlag".equals(this.getParameter("flag"))) {
				cms.setCmsFlag(Integer.parseInt(this.getParameter("cmsFlag")));
				if(dao.update(cms))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("cmsClassList",dao.findAll(CmsClass.class));
				String url = Configure.get("img.url");
				cms.setCmsImg(url+cms.getCmsImg());
				request.setAttribute("cms", cms);
				return mapping.findForward("edit");
			}else{
				CmsForm cmsForm = (CmsForm)form;
				FormFile formFile = cmsForm.getFormFile();
				String path = "";
				if(formFile !=null && !"".equals(formFile.getFileName())){
					if(!FileManager.isPhoto(formFile.getFileName())){
						this.buildAjaxResult(request, FAILURE_STATUS, "�ϴ��ļ�����ӦΪjpeg|jpg|gif|png��");
						return mapping.findForward("upload");
					}
					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR );
					int month = cal.get(Calendar.MONTH )+1;
					path = FileManager.uploadFile(request,formFile,FileManager.UPLOAD_CMS_PATH+ File.separator+year+File.separator+month,false);
					if("".equals(path)){
						this.buildAjaxResult(request, FAILURE_STATUS, "�ļ��ϴ�ʧ�ܣ�");
						return mapping.findForward("upload");
					}
					cms.setCmsImg(path);
				}
				cms.setCmsName(this.getParameter("name"));
				cms.setCmsContent(this.getParameter("content"));
				cms.setCmsClassId(Integer.parseInt(this.getParameter("cmsClassId")));
				cms.setCmsFlag(Integer.parseInt(this.getParameter("cmsFlag")));
				cms.setCmsStatus(Integer.parseInt(this.getParameter("state")));
				if(dao.update(cms))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"�����б�");
				return mapping.findForward("upload");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
	/**
	 * ɾ��ҳ��
	 * @param mapping
	 * @param arg1
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			Cms cms = (Cms)dao.findById(Cms.class, Integer.parseInt(this.getParameter("cid")));
			dao.delete(cms);
			this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
}
