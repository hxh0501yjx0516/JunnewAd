package com.pancou.ad.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pancou.ad.dao.AnalysisDataDao;
import com.pancou.ad.util.BaseDispatchAction;
import com.pancou.ad.util.Configure;
import com.pancou.ad.util.DatetimeHandle;
import com.pancou.ad.util.FileManager;
import com.pancou.ad.util.PagingHandle;
import com.pancou.ad.vo.SourcePhoto;
import com.pancou.ad.vo.Users;

@Entity
public class SnapshotAction extends BaseDispatchAction{
	
	private static final String ENCODED = "UTF-8";
	/**
	 * 
	 * �����б�
	 */
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		String addTimef;
		String addTimet;
		try {
			int pageNum = 0;// ҳ��
			int numPerPage = 20;//ÿҳ��ʾ����
			if ("pager".equals(this.getParameter("flag"))) {
				// ��ҳ
				pageNum = Integer.parseInt(this.getParameter("pageNum"));
				numPerPage = Integer.parseInt(this.getParameter("numPerPage"));
			}
			Users users = (Users)request.getSession().getAttribute("user");
			String condition = "";
			Map<String,Object> parms=new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer();
			StringBuffer sqlCount = new StringBuffer();
			
			sqlCount.append("select count(t.id) from (");
			sqlCount.append(" select s.*,ac.adCreativeName adCreativeName,w.webMasterName webMasterName,url.url url,a.adBoxName adBoxName,apc.adPlanCycleName adPlanCycleName,ap.adPlanName adPlanName, ");
			sqlCount.append(" u.userId userId,ap.adPlanId adPlanId,apc.adPlanCycleId adPlanCycleId,ap.userGroup userGroup ");
			sqlCount.append(" from SourcePhoto s ");
			sqlCount.append(" left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId  ");
			sqlCount.append(" left join AdCreative ac on r.adCreativeId = ac.adCreativeId ");
			sqlCount.append(" left join webMaster w on r.webMasterId = w.webMasterId ");
			sqlCount.append(" left join Url url on r.UrlId = url.UrlId ");
			sqlCount.append(" left join Users u on r.userId = u.userId ");
			sqlCount.append(" left join AdPlan ap on r.adPlanId = ap.adPlanId ");
			sqlCount.append(" left join AdPlanCycle apc on r.adPlanCycleId = apc.adPlanCycleId ");
			sqlCount.append(" left join adBox a on  r.adBoxId= a.adBoxId ");
			sqlCount.append(" ) t where convert(varchar(10), t.insertTime,23) between :addTimef and :addTimet");
			
			sql.append("select * from (");
			sql.append(" select s.*,ac.adCreativeName adCreativeName,w.webMasterName webMasterName,url.url url,a.adBoxName adBoxName,apc.adPlanCycleName adPlanCycleName,ap.adPlanName adPlanName, ");
			sql.append(" u.userId userId,ap.adPlanId adPlanId,apc.adPlanCycleId adPlanCycleId,ap.userGroup userGroup ");
			sql.append(" from SourcePhoto s ");
			sql.append(" left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId  ");
			sql.append(" left join AdCreative ac on r.adCreativeId = ac.adCreativeId ");
			sql.append(" left join webMaster w on r.webMasterId = w.webMasterId ");
			sql.append(" left join Url url on r.UrlId = url.UrlId ");
			sql.append(" left join Users u on r.userId = u.userId ");
			sql.append(" left join AdPlan ap on r.adPlanId = ap.adPlanId ");
			sql.append(" left join AdPlanCycle apc on r.adPlanCycleId = apc.adPlanCycleId ");
			sql.append(" left join adBox a on  r.adBoxId= a.adBoxId ");
			sql.append(" ) t where convert(varchar(10), t.insertTime,23) between :addTimef and :addTimet");
			
			addTimef = this.getParameter("addTimef");
			addTimet = this.getParameter("addTimet");
			if("".equals(this.getParameter("addTimef"))){
				addTimef = DatetimeHandle.formatDate(new Date(), DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			}if("".equals(this.getParameter("addTimet"))){
				addTimet = DatetimeHandle.formatDate(new Date(), DatetimeHandle.SHORT_TIME_FORMAT_STRING);
			}if(!"".equals(this.getParameter("webmaster"))){
				condition = " and t.webMastername like :webMasterName";
				parms.put("webMasterName","%"+URLDecoder.decode(this.getParameter("webmaster"),ENCODED)+"%");
			}if(!"".equals(this.getParameter("url"))){
				condition += " and t.source like :url ";
				parms.put("url","%"+URLDecoder.decode(this.getParameter("url"),ENCODED)+"%");
			}if(!"".equals(this.getParameter("adPlanId"))){
				condition += " and t.adPlanId =:adPlanId ";
				parms.put("adPlanId",Integer.parseInt(this.getParameter("adPlanId")));
			}if(!"".equals(this.getParameter("adPlanCycleId"))){
				condition += " and t.adPlanCycleId = :adPlanCycleId ";
				parms.put("adPlanCycleId",Integer.parseInt(this.getParameter("adPlanCycleId")));
			}if(!"".equals(this.getParameter("userid"))){
				condition += " and t.userId = :userId ";
				parms.put("userId",Integer.parseInt(this.getParameter("userid")));
			}
			if(users.getUserGroup()!=0){
				//ִ��
				condition += " and t.userGroup =:userGroup";
				parms.put("userGroup", users.getUserGroup());
			}else{
				//����Ա
				if(!"".equals(this.getParameter("qryuserGroup"))){
					condition +=" and t.userGroup =:userGroup ";
					parms.put("userGroup",Integer.parseInt(this.getParameter("qryuserGroup")));
				}
				this.getUserGroupList(request);
			}
			parms.put("addTimef", addTimef);
			parms.put("addTimet", addTimet);
			Map<String,Integer> pageMap = PagingHandle.getPagingParams(pageNum, numPerPage,dao.getCount(sqlCount.toString()+condition,parms));
			List<Object> cols = new ArrayList<Object>();
			cols.add(SourcePhoto.class);
			cols.add("adCreativeName");
			cols.add("webMasterName");
			cols.add("url");
			cols.add("adBoxName");
			cols.add("adPlanCycleName");
			cols.add("adPlanName");
			List<Object[]> qryList = dao.findBySql(pageMap.get("first"), pageMap.get("size"),sql.toString()+condition,parms,cols);
			List<SourcePhoto> list = new ArrayList<SourcePhoto>();
			String path = Configure.get("img.url");
			for(int i=0;i<qryList.size();i++){
				Object[] object = (Object[])qryList.get(i);
				SourcePhoto sourcePhoto = (SourcePhoto)object[0];
				sourcePhoto.setAdCreativeName(object[1]+"");
				sourcePhoto.setWebMasterName(object[2]+"");
				sourcePhoto.setUrl(object[3]+"");
				sourcePhoto.setAdBoxName(object[4]+"");
				sourcePhoto.setAdPlanCycleName(object[5]+"");
				sourcePhoto.setAdPlanName(object[6]+"");
				list.add(sourcePhoto);
			}
			if (list != null ) {
				this.buildPageResult(request,PagingHandle.getResultMap(pageMap, "list", list));
			}
			
			AnalysisDataDao asdao = new AnalysisDataDao();
			request.setAttribute("userlist", asdao.getUsersList(users,request.getParameter("qryuserGroup")));
			request.setAttribute("adPlanCyclelist", asdao.getAdPlanCycleList(users,request.getParameter("qryuserGroup")));
			request.setAttribute("adPlanlist", asdao.getAdPlanList(users,request.getParameter("qryuserGroup")));
			
			request.setAttribute("path", path);
			request.setAttribute("addTimef", addTimef);
			request.setAttribute("addTimet", addTimet);
			request.setAttribute("userid", this.getParameter("userid"));
			request.setAttribute("webmaster", this.getParameter("webmaster"));
			request.setAttribute("url", this.getParameter("url"));
			request.setAttribute("adPlanId", this.getParameter("adPlanId"));
			request.setAttribute("adPlanCycleId", this.getParameter("adPlanCycleId"));
			request.setAttribute("qryuserGroup", request.getParameter("qryuserGroup"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}
}
