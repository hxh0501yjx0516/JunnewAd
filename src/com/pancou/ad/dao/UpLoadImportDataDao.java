package com.pancou.ad.dao;

import hib.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

@Entity
public class UpLoadImportDataDao {

	/** ���Session */
	public synchronized Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/** ReportCount �� */
	public List<ReportCount> findReportCount(int adBoxId, int adCreativeId,
			String reportTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("from ReportCount where adBoxId=:adBoxId and adCreativeId = :adCreativeId and reportTime=:reportTime");
		List<ReportCount> l = new ArrayList<ReportCount>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("adBoxId", adBoxId);
			qry.setInteger("adCreativeId", adCreativeId);
			qry.setString("reportTime", reportTime);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** ReadyBox �� */
	public List<ReadyBox> finbByID(int readyBoxId, Users users) {
		StringBuffer sql = new StringBuffer();
		sql.append("select s FROM ReadyBox as s,AdPlan ap where s.readyBoxId=:readyBoxId and s.adPlanId = ap.adPlanId ");
		if (users.getUserGroup() != 0) {
			// ִ��
			sql.append(" and ap.userGroup = :userGroup");
		}
		List<ReadyBox> l = new ArrayList<ReadyBox>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("readyBoxId", readyBoxId);
			if (users.getUserGroup() != 0) {
				// ִ��
				qry.setInteger("userGroup", users.getUserGroup());
			}
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** AdCreative �� */
	public List<AdCreative> finbByIDAdCreative(int AdCreativeId) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM AdCreative as s where s.adCreativeId=:AdCreativeId");
		List<AdCreative> l = new ArrayList<AdCreative>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("AdCreativeId", AdCreativeId);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** AdBox�� */
	public List<AdBox> finbByIDAdBox(int AdBoxID) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM AdBox as s where s.adBoxId=:AdBoxID");
		List<AdBox> l = new ArrayList<AdBox>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("AdBoxID", AdBoxID);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** WebMaster �� */
	public List<WebMaster> finbByIDWebMaster(int WebMasterId) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM WebMaster as s where s.webMasterId=:WebMasterId");
		List<WebMaster> l = new ArrayList<WebMaster>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("WebMasterId", WebMasterId);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** Url �� */
	public List<UrlAddress> finbByIDUrl(int urlId) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM UrlAddress as s where s.urlId=:urlId");
		List<UrlAddress> l = new ArrayList<UrlAddress>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("urlId", urlId);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** AdPlan �� */
	public List<AdPlan> finbByIDAdPlan(int AdPlanId) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM AdPlan as s where s.adPlanId=:AdPlanId");
		List<AdPlan> l = new ArrayList<AdPlan>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("AdPlanId", AdPlanId);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** AdPlanCycle �� */
	public List<AdPlanCycle> finbByIDAdPlanC(int adPlanCycleId) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM AdPlanCycle as s where s.adPlanCycleId=:adPlanCycleId");
		List<AdPlanCycle> l = new ArrayList<AdPlanCycle>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("adPlanCycleId", adPlanCycleId);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** AdPlan �� */
	public List<Users> finbByIDUsers(int userid) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM Users as s where s.userId=:userid");
		List<Users> l = new ArrayList<Users>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("userid", userid);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** 2009-11-20���� */
	public List<AdBoxCount> finbByIDAndTime(int ReadyBoxId, String countTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("FROM AdBoxCount as s where s.readyBoxId=:ReadyBoxId and convert(varchar(20),s.adBoxCountTime,23)=:countTime ");
		List<AdBoxCount> l = new ArrayList<AdBoxCount>();
		try {
			Query qry = getSession().createQuery(sql.toString());
			qry.setInteger("ReadyBoxId", ReadyBoxId);
			qry.setString("countTime", countTime);
			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}

		return l;
	}

	/** ����ʵ�� */
	public void saveReport(ReportCount report, String tab) {

		Transaction tx = getSession().beginTransaction();

		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into ").append(tab);
			sql.append(" (ReportTime, AdplanName, AdplanCycleId, AdplanCycleName, AdBoxId, AdBoxName, AdCreativeId, ");
			sql.append(" AdCreativeName, AdCreativeImg, AdCreativeUrl, WebMasterId, WebMasterName, ");
			sql.append(" UrlId, UrlName, ");
			sql.append(" UserId, UserName, [Browse], BrowseTrue, PV, UV, IP, PayType, WebMasterPrice, ");
			sql.append(" Discount, Count, CountTo, Money, RealCount, RealMoney) ");
			// sql.append(" , ReportStatus, PayId, AddTime ) ");

			sql.append(" values(:ReportTime, :AdplanName, :AdplanCycleId, :AdplanCycleName, :AdBoxId, :AdBoxName, :AdCreativeId,");
			sql.append(" :AdCreativeName, :AdCreativeImg, :AdCreativeUrl, :WebMasterId, :WebMasterName, ");
			sql.append(" :UrlId, :UrlName, ");
			sql.append(" :UserId, :UserName,:Browse, :BrowseTrue, :PV, :UV, :IP, :PayType, :WebMasterPrice, ");
			sql.append(" :Discount, :Count, :CountTo, :Money, :RealCount, :RealMoney ) ");
			// sql.append(" , :ReportStatus, :PayId, :AddTime ) ");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.setString("ReportTime", report.getReportTime());
			query.setString("AdplanName", report.getAdplanName());

			query.setInteger("AdplanCycleId", report.getAdplanCycleId());
			query.setString("AdplanCycleName", report.getAdplanCycleName());
			query.setInteger("AdBoxId", report.getAdBoxId());
			query.setString("AdBoxName", report.getAdBoxName());
			query.setInteger("AdCreativeId", report.getAdCreativeId());

			query.setString("AdCreativeName", report.getAdCreativeName());
			query.setString("AdCreativeImg", report.getAdCreativeImg());
			query.setString("AdCreativeUrl", report.getAdCreativeUrl());
			query.setInteger("WebMasterId", report.getWebMasterId());
			query.setString("WebMasterName", report.getWebMasterName());

			query.setInteger("UrlId", report.getUrlId());
			query.setString("UrlName", report.getAdplanName());
			query.setInteger("UserId", report.getUserId());
			query.setString("UserName", report.getUserName());
			query.setInteger("Browse", report.getBrowse());

			query.setInteger("BrowseTrue", report.getBrowseTrue());
			query.setInteger("PV", report.getPv());
			query.setInteger("UV", report.getUv());
			query.setInteger("IP", report.getIp());
			query.setInteger("PayType", report.getPayType());

			query.setFloat("WebMasterPrice", report.getWebMasterPrice());
			query.setFloat("Discount", report.getDiscount());
			query.setInteger("Count", report.getCount());
			query.setInteger("CountTo", report.getCountTo());
			query.setFloat("Money", report.getMoney());

			query.setInteger("RealCount", report.getRealCount());
			query.setFloat("RealMoney", report.getRealMoney());
			// query.setInteger("ReportStatus", 0);
			// query.setInteger("PayId", 0);
			// query.setDate("AddTime", new Date());

			// query.addEntity(Report.class);
			query.executeUpdate();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			getSession().close();
		}
	}

	// private SessionFactory sessionFactory = null;
	// public void createSession(){
	//
	// String tablename = "Report_100";
	// try {
	// Configuration cfg = new
	// Configuration().addClass(Report.class).configure();
	//
	// Table table = cfg.getClassMapping("Report.class").getTable();
	// table.setName(tablename);
	// ((Object) cfg.getClassMapping("Report.class")).setsetTable(table);
	//
	// sessionFactory = cfg.buildSessionFactory();
	// }
	// catch (MappingException ex) {
	// ex.printStackTrace();
	// }catch (HibernateException ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// public void insertPO(Report re) throws HibernateException {
	// Session session = sessionFactory.openSession();
	// Transaction tx = session.beginTransaction();
	// session.save(re);
	// tx.commit();
	// session.close();
	// }

}
