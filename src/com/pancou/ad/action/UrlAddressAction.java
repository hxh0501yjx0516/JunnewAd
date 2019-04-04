package com.pancou.ad.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.util.AlexaSeeker;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.ExcelUtil;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.util.ReadyBoxUtil;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.UrlRefuse;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.WebMaster;



@Entity
public class UrlAddressAction extends BaseDispatchAction{
	
	/**
	 * �����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
		
		//�б���Ϣ
		String sql = "select u.*,(select count(adboxid) from adbox where urlid = u.urlid)  boxCount,w.userId userId from url u left join webmaster w on u.webmasterId = w.webmasterId  where 1=1 ";
		String condition = "";
		Map<String,Object> parms=new HashMap<String, Object>();
		if(!"".equals(this.getParameter("qryurl"))){
			condition += " and u.url like :url ";
			parms.put("url","%"+this.getParameter("qryurl")+"%");
		}
		if(!"".equals(this.getParameter("qrywebmaster"))){
			condition += " and u.webMasterName like :webMasterName ";
			parms.put("webMasterName", "%"+this.getParameter("qrywebmaster")+"%");
		}else if(!"".equals(this.getParameter("webMasterId")) && 0!=Integer.parseInt(this.getParameter("webMasterId"))){
			condition += " and u.webMasterId = :webMasterId ";
			parms.put("webMasterId", Integer.parseInt(this.getParameter("webMasterId")));
			request.setAttribute("webMasterId", this.getParameter("webMasterId"));
		}
		 Users users = (Users)request.getSession().getAttribute("user");
		if(users.getUserGroup()!=0){
			//ִ��
			condition += " and w.userGroup =:userGroup";
			parms.put("userGroup", users.getUserGroup());
		}else{
			//����Ա
			if(!"".equals(this.getParameter("qryuserGroup"))){
				condition +=" and w.userGroup =:userGroup ";
				parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
			}
			this.getUserGroupList(request);
		}
		Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount("select count(*) from url u left join webmaster w on u.webmasterId = w.webmasterId  where 1=1 "+condition,parms));
		List<Object> cols = new ArrayList<Object>();
		cols.add(UrlAddress.class);
		cols.add("boxCount");
		cols.add("userId");
		List qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql+condition+" order by u.urlId desc",parms,cols);
		List<UrlAddress> list = new ArrayList<UrlAddress>();
		for(int i=0;i<qryList.size();i++){
			Object[] object = (Object[])qryList.get(i);
			UrlAddress urlAddress = (UrlAddress)object[0];
			urlAddress.setAdBoxCount(Integer.parseInt(object[1]+""));
			urlAddress.setUserId(Integer.parseInt(object[2]+""));
			list.add(urlAddress);
		}
		if (list != null ) {
			this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
		}
		
		request.setAttribute("qryurl",this.getParameter("qryurl"));
		request.setAttribute("qryuserGroup",this.getParameter("qryuserGroup"));
		request.setAttribute("qrywebmaster",this.getParameter("qrywebmaster"));
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
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		if ("save".equals(this.getParameter("flag"))) {
			// ���
			String[] pname =new String[1];
			String[] pvalue =new String[1];
			pname[0] = "url"; 
			String webadd = this.getParameter("url");
			if(!"".equals(webadd)){
			if(webadd.startsWith("www.")){
				webadd = webadd.replaceFirst("www.","");
			}else if(webadd.startsWith("http://www.")){
				webadd = webadd.replaceFirst("http://www.","");
			}else if(webadd.startsWith("http://")){
				webadd = webadd.replaceFirst("http://","");
			}
			}
			pvalue[0] = webadd;
			List userslist = dao.findAll(UrlAddress.class, pname, pvalue);
			if (userslist != null && userslist.size() > 0) {
				// �������Ѵ���
				this.buildAjaxResult(request, FAILURE_STATUS, "�������Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
				return mapping.findForward("ajaxDone");
			}
			UrlAddress urlAddress = new UrlAddress();
			urlAddress.setWebMasterId(Integer.parseInt(this.getParameter("webMasterId")));
			urlAddress.setWebMasterName(this.getParameter("webMasterName"));
			urlAddress.setUrlName(this.getParameter("urlName"));
			if(!"".equals(this.getParameter("url"))){
				urlAddress.setUrl(webadd);
			}
			urlAddress.setUrlICP(this.getParameter("urlIcp"));
			urlAddress.setUrlDayIp(this.getParameter("urlDayIp"));
			
			AlexaSeeker alexaSeeker = new AlexaSeeker();
			String alexa = alexaSeeker.getValues("POPULARITY", "TEXT",this.getParameter("url"));
			if(alexa != null && !"".equals(alexa)){
				urlAddress.setUrlAlexa(Integer.parseInt(alexa));
			}else{
				urlAddress.setUrlAlexa(0);
			}
			urlAddress.setUrlArea(this.getParameter("urlArea"));
			urlAddress.setUrlTypeId(Integer.parseInt(this.getParameter("urlTypeId")));
			urlAddress.setUrlTypeName(this.getParameter("urlTypeName"));
			if(!"".equals(this.getParameter("urlStatus"))){
				urlAddress.setUrlStatus(Integer.parseInt(this.getParameter("urlStatus")));
			}else{
				urlAddress.setUrlStatus(0);
			}
			urlAddress.setAddTime(DatetimeHandle.formatCurrentDate());
			try {
				dao.save(urlAddress);
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���", CALLBACKTYPE_CLOSECURRENT,"�����б�");
			} catch (Exception e) {
				e.printStackTrace();
				this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
			}
			return mapping.findForward("ajaxDone");
		}
//		List webMasterList = dao.findAll(WebMaster.class,new String[0],new String[0]);
//		request.setAttribute("webMasterList",webMasterList);
		
		request.setAttribute("webMasterId",this.getParameter("webMasterId"));
		if(!"".equals(this.getParameter("webMasterId")) && 0!=Integer.parseInt(this.getParameter("webMasterId"))){
			WebMaster webMaster = (WebMaster)dao.findById(WebMaster.class, Integer.parseInt(this.getParameter("webMasterId")));
			request.setAttribute("webMasterName",webMaster.getWebMasterName());
		}
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
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try {
			UrlAddress urlAddress = (UrlAddress)dao.findById(UrlAddress.class, Integer.parseInt(this.getParameter("urlId")));
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("urlId", urlAddress.getUrlId());
			params.put("remarks","�޸�����IDΪ"+this.getParameter("urlId")+"��״̬,����״̬��1-���Ϊ2-��");
			params.put("operationSource","/urladdress.do?action="+this.getParameter("action"));
			if ("editState".equals(this.getParameter("flag"))) {
				urlAddress.setUrlStatus(Integer.parseInt(this.getParameter("urlStatus")));
				if(Integer.parseInt(this.getParameter("urlStatus")) == 2){//��
					this.updateReadyBox(request,params);
				}
				if(dao.update(urlAddress))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���");
			}else if("edit".equals(this.getParameter("flag"))) {
				request.setAttribute("urlAddress",urlAddress);
				return mapping.findForward("edit");
			}else{
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "url";
				String webadd = this.getParameter("url");
				if(!"".equals(webadd)){
					if(webadd.startsWith("www.")){
						webadd = webadd.replaceFirst("www.","");
					}else if(webadd.startsWith("http://www.")){
						webadd = webadd.replaceFirst("http://www.","");
					}else if(webadd.startsWith("http://")){
						webadd = webadd.replaceFirst("http://","");
					}
					}
				pvalue[0] = webadd;
				List userslist = dao.findAll(UrlAddress.class, pname, pvalue);
				if (userslist != null && userslist.size() > 0) {
					if(Integer.parseInt(this.getParameter("urlId")) != ((UrlAddress)userslist.get(0)).getUrlId()){
						// �������Ѵ���
						this.buildAjaxResult(request, FAILURE_STATUS, "�������Ѵ���!", CALLBACKTYPE_CLOSECURRENT);
						return mapping.findForward("ajaxDone");
					}
				}
				urlAddress.setUrlName(this.getParameter("urlName"));
				if(!"".equals(webadd)){
				urlAddress.setUrl(webadd);
				}
				urlAddress.setUrlICP(this.getParameter("urlIcp"));
				urlAddress.setUrlDayIp(this.getParameter("urlDayIp"));
				
				AlexaSeeker alexaSeeker = new AlexaSeeker();
				String alexa = alexaSeeker.getValues("POPULARITY", "TEXT",this.getParameter("url"));
				if(alexa != null && !"".equals(alexa)){
					urlAddress.setUrlAlexa(Integer.parseInt(alexa));
				}else{
					urlAddress.setUrlAlexa(0);
				}
				urlAddress.setUrlArea(this.getParameter("urlArea"));
				urlAddress.setUrlTypeId(Integer.parseInt(this.getParameter("urlTypeId")));
				urlAddress.setUrlTypeName(this.getParameter("urlTypeName"));
				urlAddress.setUrlStatus(Integer.parseInt(this.getParameter("urlStatus")));
				if(Integer.parseInt(this.getParameter("urlStatus")) == 2){//��
					this.updateReadyBox(request,params);
				}
				if(dao.update(urlAddress))
				this.buildAjaxResult(request, SUCCESS_STATUS, "�����ɹ���",CALLBACKTYPE_CLOSECURRENT,"�����б�");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			this.buildAjaxResult(request, FAILURE_STATUS, "����ʧ�ܣ�");
		}
		return mapping.findForward("ajaxDone");
	}
	
	/**
	 * �����б?��	table:url
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward urlToExcel(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		//System.out.println(this.getParameter("begintime"));
		try {
			String fileName = "�����б�";
			  OutputStream os = response.getOutputStream();//ȡ�������
			  response.reset();//��������
			  fileName = new String(fileName.getBytes("GBK"),"ISO8859-1");
//			  URLDecoder.decode(fileName ,"utf-8");
			  response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");//�趨����ļ�ͷ
			  response.setContentType("application/msexcel;charset=UTF-8");//�����������


				List<String> headerList = new ArrayList<String>();
				headerList.add("���");      
				headerList.add("վ�����");
				headerList.add("վ��");  
				headerList.add("��վ���");
				headerList.add("����"); 
				headerList.add("ICP");  
			    headerList.add("��IP");         
			    headerList.add("Aleax");           
			    headerList.add("����");             
			    headerList.add("��վ����");             
			    headerList.add("���ʱ��");
			    
			    
			    String sqlString = "select u from UrlAddress u,WebMaster w where u.webMasterId = w.webMasterId ";
				String condition = " ";
				Map<String,Object> parms = new HashMap<String,Object>();
				
				if(!"".equals(this.getParameter("qryurl"))){
					condition += " and u.url like:qryurl";
					parms.put("qryurl","%"+this.getParameter("qryurl")+"%");
				}
				if(!"".equals(this.getParameter("qrywebmaster"))){
					condition += " and u.webMasterName like:qrywebmaster";
					parms.put("qrywebmaster", "%"+this.getParameter("qrywebmaster")+"%");
				}
				 Users users = (Users)request.getSession().getAttribute("user");
					if(users.getUserGroup()!=0){
						//ִ��
						condition += " and w.userGroup =:userGroup";
						parms.put("userGroup", users.getUserGroup());
					}else{
						//����Ա
						if(!"".equals(this.getParameter("qryuserGroup"))){
							condition +=" and w.userGroup =:userGroup ";
							parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
						}
					}
			    condition += " order by u.urlId";
			    

				List<UrlAddress> list = dao.findAll(sqlString, condition, parms);
				List<Object[]> datas = new ArrayList<Object[]>();
				//DecimalFormat df = new DecimalFormat("##,###.###");
				for(UrlAddress urlAddress : list){
					Object[] object = new Object[11];
					object[0]=urlAddress.getUrlId();
					object[1]=urlAddress.getWebMasterId();
					object[2]=urlAddress.getWebMasterName();
					object[3]=urlAddress.getUrlName();
					object[4]=urlAddress.getUrl();
					object[5]=urlAddress.getUrlICP();
					object[6]=urlAddress.getUrlDayIp();
					object[7]=urlAddress.getUrlAlexa();
					object[8]=urlAddress.getUrlArea();
					object[9]=urlAddress.getUrlTypeName();
					object[10]=DatetimeHandle.formatDate(DatetimeHandle.parseDate(urlAddress.getAddTime()),DatetimeHandle.SHORT_TIME_FORMAT_STRING);
					datas.add(object);
				}
				ExcelUtil.export(datas,headerList,fileName,os);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
		}
	
	/**
	 * ��������б�	tables: UrlRefuse
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refuseList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try{
			int pageNum = 0;// ҳ��
			int numPerPage = 20;//ÿҳ��ʾ����
			if ("pager".equals(this.getParameter("flag"))) {
				// ��ҳ
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			
			String sqlstr = "select u.* from urlrefuse u where 1=1";
			String condition = "";
			
			Map<String,Object> parms = new HashMap<String,Object>();
			if (!"".equals(this.getParameter("qryurl"))){
				condition += " and u.url like :qryurl";
				parms.put("qryurl", "%"+this.getParameter("qryurl")+"%");
			}
			
			List<Object> cols = new ArrayList<Object>();
			cols.add(UrlRefuse.class);
			
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage, dao.getCount(UrlRefuse.class, condition, parms));
			List<UrlRefuse> list = dao.findBySql(pageMap.get("first"), pageMap.get("size"), sqlstr+condition+" order by u.urlid desc", parms, cols);
			if (list != null){
				this.buildPageResult(request, PagingHandle.getResultMap(pageMap, "list", list));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("qryurl", this.getParameter("qryurl"));
		return mapping.findForward("refuseList");
	}
	
	/**
	 * ����������	tables: UrlRefuse
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refuseAdd(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try{
			if ("save".equals(this.getParameter("flag"))){
				String[] pname = new String[1];
				String[] pvalue = new String[1];
				pname[0] = "url";
				pvalue[0] = this.getParameter("url");
				List<UrlRefuse> list = dao.findAll(UrlRefuse.class, pname, pvalue);
				if (list != null && list.size() > 0){
					this.buildAjaxResult(request, this.FAILURE_STATUS, "�����ַ�Ѵ���", this.CALLBACKTYPE_CLOSECURRENT);
					return mapping.findForward("ajaxDone");
				}
				
				Users user = (Users)request.getSession().getAttribute("user");
				
				UrlRefuse ur = new UrlRefuse();
				ur.setUrlName(this.getParameter("urlname"));
				ur.setUrl(this.getParameter("url"));
				ur.setUserId(user.getUserId());
				ur.setUserName(user.getUsername());
				ur.setAddTime(DatetimeHandle.formatCurrentDate());
				dao.save(ur);
				ReadyBoxUtil rb = new ReadyBoxUtil();
				rb.save2MemCached("blackurl");
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "��ӳɹ�", this.CALLBACKTYPE_CLOSECURRENT,"�������");
				return mapping.findForward("ajaxDone");
			}			
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("refuseAdd");
	}
	
	/**
	 * �޸��������	tables: UrlRefuse
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refuseEdit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try{
			UrlRefuse ur = (UrlRefuse)dao.findById(UrlRefuse.class, Integer.parseInt(this.getParameter("urlid")));
			if ("save".equals(this.getParameter("flag"))){
				Users user = (Users)request.getSession().getAttribute("user");
				ur.setUrlName(this.getParameter("urlname"));
				ur.setUrl(this.getParameter("url"));
				ur.setUserId(user.getUserId());
				ur.setUserName(user.getUsername());
				dao.update(ur);
				ReadyBoxUtil rb = new ReadyBoxUtil();
				rb.save2MemCached("blackurl");
				this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�", this.CALLBACKTYPE_CLOSECURRENT,"�������");
				return mapping.findForward("ajaxDone");
			} else {
				request.setAttribute("urlrefuse", ur);
				request.setAttribute("urlid", this.getParameter("urlid"));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("refuseEdit");
	}
	
	/**
	 * ɾ���������	tables: UrlRefuse
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refuseDel(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		try{
			UrlRefuse ur = (UrlRefuse)dao.findById(UrlRefuse.class, Integer.parseInt(this.getParameter("urlid")));
			dao.delete(ur);
			ReadyBoxUtil rb = new ReadyBoxUtil();
			rb.save2MemCached("blackurl");
			this.buildAjaxResult(request, this.SUCCESS_STATUS, "�����ɹ�");
		} catch (Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("ajaxDone");
	}
	
	
	public static void main(String[] args) throws Exception{
		try{
			int urlid = 39;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
