package com.pancou.ad.dao;

import hib.HibernateSessionFactory;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.pancou.ad.vo.AdBoxCount;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Users;

public class AnalysisDataDao {

	/** 获得Session */
	public synchronized Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * AdBoxCount 表 数据分析首页-- 数据总量
	 * 
	 * @param abtime
	 * @return
	 */
	public int findSumAdBoxCCount(String abtime, String webMaster, String url,
			String adPlanCycleId, String adPlanId, String userid, Users users,
			String qryUserGroupId) {

		StringBuffer sql = new StringBuffer();
		sql.append("select count(tc.bro) from (");
		sql.append("select sum([browse]) bro, sum([browseTrue]) broT, sum(pv) pv, ");
		sql.append("sum(uv) uv,sum(ip) ip, AreaCountHour ");
		sql.append(" from  AreaCount_").append(abtime).append(" a ");

		sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan ap on r. AdPlanId = ap.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId  where 1=1 ");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and w.WebMasterName like :webMaster ");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and url.Url like :url ");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and ap.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and u.userid =:userid ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}
		sql.append(" group by AreaCountHour ");
		sql.append(" ) tc ");
		List l = new ArrayList();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());
			// qry.setString("abtime", abtime);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l.get(0) == null ? 0 : Integer.parseInt(l.get(0).toString());
	}

	/**
	 * AdBoxCount 表 数据分析首页-- 数据总量
	 * 
	 * @param abtime
	 * @return
	 */
	public List<AdBoxCount> findSumAdBoxC(boolean bool, int index, int size,
			String abtime, String webMaster, String url, String adPlanCycleId,
			String adPlanId, String userid, Users users, String qryUserGroupId) {

		StringBuffer sql = new StringBuffer();
		sql.append("select sum([browse]) bro, sum([browseTrue]) broT, sum(pv) pv, ");
		sql.append("sum(uv) uv,sum(ip) ip, AreaCountHour ");
		sql.append(" from  AreaCount_").append(abtime).append(" a ");

		sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan ap on r. AdPlanId = ap.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId  where 1=1 ");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and w.WebMasterName like :webMaster ");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and url.Url like :url ");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and ap.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and u.userid =:userid ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}
		sql.append(" group by AreaCountHour order by AreaCountHour");
		List<AdBoxCount> l = new ArrayList<AdBoxCount>();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());
			// qry.setString("abtime", abtime);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			if (bool) {
				// 第一条
				qry.setFirstResult(index);
				// 最大
				qry.setMaxResults(size);
			}
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	public List<AdBoxCount> findSumAdBoxCA(Users users, String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum([browse]) bro, sum([browseTrue]) broT, sum(pv) pv, ");
		sql.append("sum(uv) uv,sum(ip) ip, a.AdBoxCountTime ");
		sql.append(" from  AdBoxCount").append(" a ");
		sql.append(" left join readybox r on a.readyboxid = r.readyboxid left join adplan ap on r.adplanid = ap.adplanid ");
		sql.append(" where datediff(day,getdate(),a.AdBoxCountTime) = 0 ");
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and ap.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and ap.userGroup =:userGroup ");
			}
		}
		sql.append(" group by a.AdBoxCountTime");
		List<AdBoxCount> l = new ArrayList<AdBoxCount>();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return l;
	}

	/**
	 * AdBoxCount 表 总量统计详细 -- 数据总量全部
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public int findDetailAllCount(String addTimef, String addTimet,
			String webMaster, String url, String adPlanCycleId,
			String adPlanId, String userid, String readyBoxId, Users users,
			String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(tc.bro) from (");
		sql.append("select * from (");
		sql.append(" select sum(s.[browse]) bro, sum(s.[browseTrue]) broT, sum(s.pv) pv, ");
		sql.append(" sum(s.uv) uv,sum(s.ip) ip, ");
		if (addTimef.equals(addTimet)) {
			// 当天按小时显示
			sql.append(" datepart(HH,s.AdBoxCountTime) AdBoxCountTime ");

		} else {
			sql.append(" s.AdBoxCountTime ");

		}
		sql.append(" from AdBoxCount s ");
		sql.append(" left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");
		sql.append(" where 1=1");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and w.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and url.Url like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and ac.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and a.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and u.userid =:userid ");
		}
		if (!"".equals(readyBoxId) && readyBoxId != null) {
			sql.append(" and r.readyBoxId=:readyBoxId ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}

		if (addTimef.equals(addTimet)) {
			// 当天按小时显示
			sql.append(" group by datepart(HH,s.AdBoxCountTime) ) t");
		} else {
			sql.append(" group by  s.AdBoxCountTime ) t");
			sql.append("   where convert(varchar(10), t.AdBoxCountTime,23) between :addTimef and :addTimet");
		}
		sql.append("  ) tc");
		List l = new ArrayList();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!addTimef.equals(addTimet)) {
				// 不按小时显示
				qry.setString("addTimef", addTimef);
				qry.setString("addTimet", addTimet);
			}

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (!"".equals(readyBoxId) && readyBoxId != null) {
				qry.setString("readyBoxId", readyBoxId);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return l.get(0) == null ? 0 : Integer.parseInt(l.get(0).toString());
	}

	/**
	 * AdBoxCount 表 总量统计详细 -- 数据总量全部
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public List<AdBoxCount> findDetailAll(int firstIndex, int pagesize,
			String addTimef, String addTimet, String webMaster, String url,
			String adPlanCycleId, String adPlanId, String userid,
			String readyBoxId, Users users, String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select sum(s.[browse]) bro, sum(s.[browseTrue]) broT, sum(s.pv) pv, ");
		sql.append(" sum(s.uv) uv,sum(s.ip) ip, ");
		if (addTimef.equals(addTimet)) {
			// 当天按小时显示
			sql.append(" datepart(HH,s.AdBoxCountTime) AdBoxCountTime ");

		} else {
			sql.append(" s.AdBoxCountTime ");

		}
		sql.append(" from AdBoxCount s ");
		sql.append(" left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");
		sql.append(" where 1=1");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and w.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and url.Url like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and ac.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and a.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and u.userid =:userid ");
		}
		if (!"".equals(readyBoxId) && readyBoxId != null) {
			sql.append(" and r.readyBoxId=:readyBoxId ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}

		if (addTimef.equals(addTimet)) {
			// 当天按小时显示
			sql.append(" group by datepart(HH,s.AdBoxCountTime) ) t");
		} else {
			sql.append(" group by  s.AdBoxCountTime ) t");
			sql.append("   where convert(varchar(10), t.AdBoxCountTime,23) between :addTimef and :addTimet");
		}

		List<AdBoxCount> l = new ArrayList<AdBoxCount>();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!addTimef.equals(addTimet)) {
				// 不按小时显示
				qry.setString("addTimef", addTimef);
				qry.setString("addTimet", addTimet);
			}

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (!"".equals(readyBoxId) && readyBoxId != null) {
				qry.setString("readyBoxId", readyBoxId);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			// 第一条
			qry.setFirstResult(firstIndex);
			// 最大
			qry.setMaxResults(pagesize);

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return l;
	}

	/**
	 * AreaCount_110629 表 数据分析首页--曲线分析图
	 * 
	 * @return
	 */
	public List findLineData() {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum([browse]) bro, sum(pv) pv, sum(uv) uv,sum(ip) ip  from AreaCount_110719");
		List l = new ArrayList();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * AreaCount_110629 表 数据分析首页-- 饼图
	 * 
	 * @param tab
	 * @return
	 */
	public List findPieData(String tab, Users users, String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(a.[Browse]) bro, sum(a.BrowseTrue) brot, sum(a.pv) pv, sum(a.uv) uv, sum(a.ip) ip, c.p  ");
		sql.append(" from junnew.AreaCount_").append(tab);
		sql.append(" a, junnew.chinaarea c,readybox rb,adplan ap ");
		sql.append(" where a.ProvinceId = c.pid and a.CityId = c.cid and a.readyBoxId = rb.readyBoxId and rb.adPlanId = ap.adPlanId ");
		// sql.append("  ");
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and ap.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and ap.userGroup =:userGroup ");
			}
		}
		sql.append("group by c.p");
		List l = new ArrayList();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * AreaCount_110629 表 数据分析详细-- 饼图
	 * 
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param interval
	 *            间隔天数
	 * @param firstIndex
	 * @param pagesize
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public int findPieDetailDataCount(String addTimef, String addTimet,
			int interval, int firstIndex, int pagesize, String webMaster,
			String url, String adPlanCycleId, String adPlanId, String userid,
			Users users, String qryUserGroupId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		StringBuffer sql = new StringBuffer();
		sql.append("select count(tc.bro) from (");
		sql.append("select sum(t.bro) bro, sum(t.brot) brot, sum(t.pv) pv, sum(t.uv) uv, sum(t.ip) ip, t.p from (");

		for (int i = 0; i <= interval; i++) {

			String tab = sdf.format(addDay(addTimef, i));
			sql.append("select * from ");
			sql.append(" (select sum(a.[Browse]) bro, sum(a.BrowseTrue) brot, sum(a.pv) pv, sum(a.uv) uv, sum(a.ip) ip, c.p  ");
			sql.append(" from junnew.AreaCount_").append(tab);
			sql.append(" a left join junnew.chinaarea c ");
			// sql.append(" where a.ProvinceId = c.pid and a.CityId = c.cid group by c.p ) ");
			sql.append(" on a.ProvinceId = c.pid and a.CityId = c.cid  ");

			sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
			sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
			sql.append(" left join AdPlan ap on r. AdPlanId = ap.AdPlanId ");
			sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
			sql.append(" left join users u on r. userid = u.userid ");
			sql.append(" left join Url url on r.UrlId = url.UrlId  where 1=1 ");

			if (!"".equals(webMaster) && webMaster != null) {
				sql.append(" and w.WebMasterName like :webMaster ");
			}
			if (!"".equals(url) && url != null) {
				sql.append(" and url.Url like :url ");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				sql.append(" and ap.AdPlanId =:adPlanId ");
			}
			if (!"".equals(userid) && userid != null) {
				sql.append(" and u.userid =:userid ");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				sql.append(" and u.userGroup =:userGroup ");
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					sql.append(" and u.userGroup =:userGroup ");
				}
			}
			sql.append("group by c.p ) tt ");

			sql.append(" union all ");

		}
		sql.setLength(sql.length() - 10);
		sql.append(" ) t group by t.p  ");
		sql.append(" ) tc ");
		List l = new ArrayList();
		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}

			l = qry.list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return l.get(0) == null ? 0 : Integer.parseInt(l.get(0).toString());
	}

	/**
	 * AreaCount_110629 表 数据分析详细-- 饼图
	 * 
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param interval
	 *            间隔天数
	 * @param firstIndex
	 * @param pagesize
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public List findPieDetailData(String addTimef, String addTimet,
			int interval, int firstIndex, int pagesize, String webMaster,
			String url, String adPlanCycleId, String adPlanId, String userid,
			Users users, String qryUserGroupId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(t.bro) bro, sum(t.brot) brot, sum(t.pv) pv, sum(t.uv) uv, sum(t.ip) ip, t.p from (");

		for (int i = 0; i <= interval; i++) {

			String tab = sdf.format(addDay(addTimef, i));
			sql.append("select * from ");
			sql.append(" (select sum(a.[Browse]) bro, sum(a.BrowseTrue) brot, sum(a.pv) pv, sum(a.uv) uv, sum(a.ip) ip, c.p  ");
			sql.append(" from junnew.AreaCount_").append(tab);
			sql.append(" a left join junnew.chinaarea c ");
			// sql.append(" where a.ProvinceId = c.pid and a.CityId = c.cid group by c.p ) ");
			sql.append(" on a.ProvinceId = c.pid and a.CityId = c.cid  ");

			sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
			sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
			sql.append(" left join AdPlan ap on r. AdPlanId = ap.AdPlanId ");
			sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
			sql.append(" left join users u on r. userid = u.userid ");
			sql.append(" left join Url url on r.UrlId = url.UrlId  where 1=1 ");

			if (!"".equals(webMaster) && webMaster != null) {
				sql.append(" and w.WebMasterName like :webMaster ");
			}
			if (!"".equals(url) && url != null) {
				sql.append(" and url.Url like :url ");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				sql.append(" and ap.AdPlanId =:adPlanId ");
			}
			if (!"".equals(userid) && userid != null) {
				sql.append(" and u.userid =:userid ");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				sql.append(" and u.userGroup =:userGroup ");
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					sql.append(" and u.userGroup =:userGroup ");
				}
			}
			sql.append("group by c.p ) tt ");

			sql.append(" union all ");

		}
		sql.setLength(sql.length() - 10);
		sql.append(" ) t group by t.p order by pv desc ");

		List l = new ArrayList();

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			// 第一条
			qry.setFirstResult(firstIndex);
			// 最大
			qry.setMaxResults(pagesize);
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * 数据分析首页--数据来源列表
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 * @param addTimet
	 * @param webMaster
	 * @param url
	 * @param adPlanCycleId
	 * @param adPlanId
	 * @param userid
	 * @return
	 */
	public List findSourceMainList(int firstIndex, int pagesize,
			String addTimef, String addTimet, String webMaster, String url,
			String adPlanCycleId, String adPlanId, String userid, Users users,
			String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select ac.AdPlanCycleName, a.AdPlanName, url.Url, w.WebMasterName, u.username,s.Source, s.AddTime, ");
		sql.append(" r.AdPlanCycleId, r.AdPlanId, u.userid ");
		sql.append(" from SourceCount s left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");

		sql.append(" ) t where convert(varchar(10), t.AddTime,23) between :addTimef and :addTimet");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and t.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and t.Url like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and t.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and t.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and t.userid =:userid ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}
		List l = new ArrayList();

		try {
			SQLQuery qry = getSession().createSQLQuery(sql.toString());

			qry.setString("addTimef", addTimef);
			qry.setString("addTimet", addTimet);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + url + "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			// 第一条
			qry.setFirstResult(firstIndex);
			// 最大
			qry.setMaxResults(pagesize);

			qry.addScalar("Source", Hibernate.STRING);

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}
		return l;
	}

	/**
	 * 数据分析首页--数据来源列表总数
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 * @param addTimet
	 * @param webMaster
	 * @param url
	 * @param adPlanCycleId
	 * @param adPlanId
	 * @param userid
	 * @return
	 */
	public int findSourceCount(String addTimef, String addTimet,
			String webMaster, String url, String adPlanCycleId,
			String adPlanId, String userid, Users users, String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(s.sourceCountId) ");
		sql.append(" from SourceCount s left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");

		sql.append(" where convert(varchar(10), s.AddTime,23) between :addTimef and :addTimet");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and w.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and s.Source like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and ac.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and a.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and u.userid =:userid ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup =:userGroup ");
			}
		}
		int l = 0;

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			qry.setString("addTimef", addTimef);
			qry.setString("addTimet", addTimet);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + URLEncoder.encode(url, "utf-8")
						+ "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			l = (Integer) qry.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}
		return l;
	}

	/**
	 * 数据分析首页--数据来源列表
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 * @param addTimet
	 * @param webMaster
	 * @param url
	 * @param adPlanCycleId
	 * @param adPlanId
	 * @param userid
	 * @return
	 */
	public List findSourceList(int firstIndex, int pagesize, String addTimef,
			String addTimet, String webMaster, String url,
			String adPlanCycleId, String adPlanId, String userid, Users users,
			String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select ac.AdPlanCycleName, a.AdPlanName, url.Url, w.WebMasterName, u.realname,s.Source, s.AddTime, ");
		sql.append(" r.AdPlanCycleId, r.AdPlanId, u.userid,u.userGroup ");
		sql.append(" from SourceCount s left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");

		sql.append(" ) t where convert(varchar(10), t.AddTime,23) between :addTimef and :addTimet");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and t.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and t.Source like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and t.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and t.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and t.userid =:userid ");
		}
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and t.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and t.userGroup =:userGroup ");
			}
		}
		List l = null;

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			qry.setString("addTimef", addTimef);
			qry.setString("addTimet", addTimet);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + URLEncoder.encode(url, "utf-8")
						+ "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}

			// 第一条
			qry.setFirstResult(firstIndex);
			// 最大
			qry.setMaxResults(pagesize);
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}
		return l;
	}

	/**
	 * 数据分析报表导出--数据来源列表
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param addTimef
	 * @param addTimet
	 * @param webMaster
	 * @param url
	 * @param adPlanCycleId
	 * @param adPlanId
	 * @param userid
	 * @return
	 */
	public List findSourceReportList(String addTimef, String addTimet,
			String webMaster, String url, String adPlanCycleId,
			String adPlanId, String userid, Users users) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append(" select ac.AdPlanCycleName, a.AdPlanName, url.Url, w.WebMasterName, u.realname,s.Source, s.AddTime, ");
		sql.append(" r.AdPlanCycleId, r.AdPlanId, u.userid, ");

		sql.append(" r.readyBoxId,ab.adBoxName,act.adCreativeId,act.adCreativeName,act.adCreativeUrl ");

		sql.append(" from SourceCount s left join ReadyBox r on s.ReadyBoxId= r.ReadyBoxId ");
		sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
		sql.append(" left join AdPlan a on r. AdPlanId = a.AdPlanId ");
		sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
		sql.append(" left join users u on r. userid = u.userid ");
		sql.append(" left join Url url on r.UrlId = url.UrlId ");

		sql.append(" left join AdBox ab on r.adBoxId = ab.adBoxId ");
		sql.append(" left join AdCreative act on r.adCreativeId = act.adCreativeId ");

		sql.append(" ) t where convert(varchar(10), t.AddTime,23) between :addTimef and :addTimet");

		if (!"".equals(webMaster) && webMaster != null) {
			sql.append(" and t.WebMasterName like :webMaster");
		}
		if (!"".equals(url) && url != null) {
			sql.append(" and t.Source like :url");
		}
		if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			sql.append(" and t.AdPlanCycleId =:adPlanCycleId");
		}

		if (!"".equals(adPlanId) && adPlanId != null) {
			sql.append(" and t.AdPlanId =:adPlanId ");
		}
		if (!"".equals(userid) && userid != null) {
			sql.append(" and t.userid =:userid ");
		}
		List l = null;

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			qry.setString("addTimef", addTimef);
			qry.setString("addTimet", addTimet);

			if (!"".equals(webMaster) && webMaster != null) {
				qry.setString("webMaster", "%" + webMaster + "%");
			}
			if (!"".equals(url) && url != null) {
				qry.setString("url", "%" + URLEncoder.encode(url, "utf-8")
						+ "%");
			}
			if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
				qry.setString("adPlanCycleId", adPlanCycleId);
			}

			if (!"".equals(adPlanId) && adPlanId != null) {
				qry.setString("adPlanId", adPlanId);
			}
			if (!"".equals(userid) && userid != null) {
				qry.setString("userid", userid);
			}
			// // 第一条
			// qry.setFirstResult(firstIndex);
			// // 最大
			// qry.setMaxResults(pagesize);
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}
		return l;
	}

	/**
	 * AdBoxCount 表 数据分析详细-- 数据总量
	 * 
	 * @return
	 */
	public List<AdBoxCount> findSumAdBoxCDetail(Users users,
			String qryUserGroupId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(abc.[browse]) bro, sum(abc.[browseTrue]) broT, sum(abc.pv) pv, sum(abc.uv) uv,sum(abc.ip) ip, abc.AdBoxCountTime from AdBoxCount abc "
				+ " left join ReadyBox rb on abc.readyBoxId = rb.readyBoxId "
				+ " left join AdPlan ap on rb.adPlanId = ap.adPlanId ");
		List<AdBoxCount> l = new ArrayList<AdBoxCount>();
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and ap.userGroup =:userGroup ");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and ap.userGroup =:userGroup ");
			}
		}
		sql.append(" group by AdBoxCountTime ");

		try {
			Query qry = getSession().createSQLQuery(sql.toString());
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * 获得媒介信息
	 * 
	 * @return
	 */
	public List<Users> getUsersList(Users users, String qryUserGroupId) {

		List<Users> list = new ArrayList<Users>();
		StringBuffer sql = new StringBuffer();
		sql.append("select u from Users u,UserRole ur where u.userId = ur.userId and ur.roleId =:roleId and u.state = :state");
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and u.userGroup = :userGroup");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and u.userGroup = :userGroup");
			}
		}
		try {
			Query qry = getSession().createQuery(sql.toString())
					.setParameter("roleId", 11).setParameter("state", 0);
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			list = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return list;
	}

	/**
	 * 广告计划周期信息
	 * 
	 * @return
	 */
	public List<AdPlanCycle> getAdPlanCycleList(Users users,
			String qryUserGroupId) {

		List<AdPlanCycle> list = new ArrayList<AdPlanCycle>();
		StringBuffer sql = new StringBuffer();
		sql.append("select ac from AdPlanCycle ac,AdPlan a where ac.adPlanId = a.adPlanId ");
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" and a.userGroup = :userGroup");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" and a.userGroup = :userGroup");
			}
		}
		try {
			Query qry = getSession().createQuery(sql.toString());
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			list = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return list;
	}

	/**
	 * 广告计划信息
	 * 
	 * @return
	 */
	public List<AdPlan> getAdPlanList(Users users, String qryUserGroupId) {

		List<AdPlan> list = new ArrayList<AdPlan>();
		StringBuffer sql = new StringBuffer();
		sql.append("from AdPlan a ");
		if (users.getUserGroup() != 0) {
			// 执行
			sql.append(" where a.userGroup = :userGroup");
		} else {
			// 管理员
			if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
				sql.append(" where  a.userGroup = :userGroup");
			}
		}
		try {
			Query qry = getSession().createQuery(sql.toString());
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			list = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}
		return list;
	}

	/**
	 * AreaCount_110629 表 数据分析详细-- 饼图-域名
	 * 
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param interval
	 *            间隔天数
	 * @param firstIndex
	 * @param pagesize
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public List findPieDomainData(String addTimef, String addTimet,
			int interval, int firstIndex, int pagesize, int isaccept,
			String domain, boolean flag, Users users, String qryUserGroupId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.Domain, sum(t.pv) pv, sum(t.uv) uv, sum(t.ip) ip from (");

		for (int i = 0; i <= interval; i++) {

			String tab = sdf.format(addDay(addTimef, i));
			sql.append("select * from ");
			sql.append(" (select a.Domain, sum(a.pv) pv, sum(a.uv) uv, sum(a.ip) ip, sum(a.[Browse]) bro, sum(a.BrowseTrue) brot  ");
			sql.append(" from junnew.DomainCount_").append(tab);
			sql.append(" a left join junnew.chinaarea c ");
			// sql.append(" where a.ProvinceId = c.pid and a.CityId = c.cid group by c.p ) ");
			sql.append(" on a.ProvinceId = c.pid and a.CityId = c.cid  ");

			sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
			// sql.append(" left join AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
			sql.append(" left join AdPlan ap on r.AdPlanId = ap.AdPlanId ");
			// sql.append(" left join WebMaster w on r. WebMasterId = w.WebMasterId ");
			// sql.append(" left join users u on r. userid = u.userid ");
			// sql.append(" left join Url url on r.UrlId = url.UrlId  ");
			sql.append(" where 1=1 ");

			if (!"".equals(isaccept) && isaccept != -1) {// 是否合法来源 1－合法，0－不合法
				sql.append(" and a.IsAccept =:isaccept ");
			}
			if (!"".equals(domain) && domain != null) {// 域名 如baidu.com
				sql.append(" and a.Domain like :domain ");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				sql.append(" where ap.userGroup = :userGroup");
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					sql.append(" where  ap.userGroup = :userGroup");
				}
			}
			// if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			// sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
			// }
			//
			// if (!"".equals(adPlanId) && adPlanId != null) {
			// sql.append(" and ap.AdPlanId =:adPlanId ");
			// }
			// if (!"".equals(userid) && userid != null) {
			// sql.append(" and u.userid =:userid ");
			// }

			sql.append("group by a.Domain ) tt ");
			sql.append(" union all ");

		}
		sql.setLength(sql.length() - 10);
		sql.append(" ) t group by t.Domain order by t.Domain ");

		List l = new ArrayList();

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!"".equals(isaccept) && isaccept != -1) {
				qry.setInteger("isaccept", isaccept);
			}
			// if (!"".equals(url) && url != null) {
			// qry.setString("url", "%"+ url +"%");
			// }
			if (!"".equals(domain) && domain != null) {
				qry.setString("domain", "%" + domain + "%");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			// if (!"".equals(adPlanId) && adPlanId != null) {
			// qry.setString("adPlanId", adPlanId);
			// }
			// if (!"".equals(userid) && userid != null) {
			// qry.setString("userid", userid);
			// }
			if (flag == true) {// 分页
				// 第一条
				qry.setFirstResult(firstIndex);
				// 最大
				qry.setMaxResults(pagesize);
			}

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * 数据分析详细-- 饼图-域名--按小时统计
	 * 
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param interval
	 *            间隔天数
	 * @param firstIndex
	 * @param pagesize
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public List findPieDomainByHour(String addTimef, String addTimet,
			int interval, int firstIndex, int pagesize, int isaccept,
			String domain, boolean flag, Users users, String qryUserGroupId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		StringBuffer sql = new StringBuffer();
		if (addTimef.equals(addTimet)) {
			// 按小时 显示曲线图
			sql.append("select t.CountHour, sum(t.pv) pv, sum(t.uv) uv, sum(t.ip) ip from (");
		} else {
			// 按天显示曲线图
			sql.append("select t.CountTime, sum(t.pv) pv, sum(t.uv) uv, sum(t.ip) ip from (");
		}

		for (int i = 0; i <= interval; i++) {

			String tab = sdf.format(addDay(addTimef, i));
			sql.append("select * from ");
			if (addTimef.equals(addTimet)) {
				// 按小时显示曲线图
				sql.append(" (select a.CountHour, ");
			} else {
				// 按天显示曲线图
				sql.append(" (select convert(varchar(10),a.CountTime,23) as CountTime, ");
			}

			sql.append(" sum(a.pv) pv, sum(a.uv) uv, sum(a.ip) ip, sum(a.[Browse]) bro, sum(a.BrowseTrue) brot  ");
			sql.append(" from junnew.DomainCount_").append(tab);
			sql.append(" a left join junnew.chinaarea c ");
			sql.append(" on a.ProvinceId = c.pid and a.CityId = c.cid  ");

			sql.append(" left join ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
			sql.append(" left join AdPlan ap on ap.ReadyBoxId= r.ReadyBoxId ");
			sql.append(" where 1=1 ");

			if (!"".equals(isaccept) && isaccept != -1) {// 是否合法来源 1－合法，0－不合法
				sql.append(" and a.IsAccept =:isaccept ");
			}
			if (!"".equals(domain) && domain != null) {// 域名 如baidu.com
				sql.append(" and a.Domain like :domain ");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				sql.append(" where ap.userGroup = :userGroup");
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					sql.append(" where  ap.userGroup = :userGroup");
				}
			}
			if (addTimef.equals(addTimet)) {
				// 按小时 显示曲线图
				sql.append("group by a.CountHour ) tt ");
			} else {
				// 按天
				sql.append("group by convert(varchar(10),a.CountTime,23) ) tt ");
			}
			sql.append(" union all ");

		}
		sql.setLength(sql.length() - 10);
		if (addTimef.equals(addTimet)) {
			// 按小时
			sql.append(" ) t group by t.CountHour order by t.CountHour ");
		} else {
			// 按天
			sql.append(" ) t group by convert(varchar(10),t.CountTime,23) order by convert(varchar(10),t.CountTime,23) ");
		}

		List l = new ArrayList();

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!"".equals(isaccept) && isaccept != -1) {
				qry.setInteger("isaccept", isaccept);
			}
			if (!"".equals(domain) && domain != null) {
				qry.setString("domain", "%" + domain + "%");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			if (flag == true) {// 分页
				// 第一条
				qry.setFirstResult(firstIndex);
				// 最大
				qry.setMaxResults(pagesize);
			}

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * AreaCount_110629 表 数据分析详细-- 饼图-域名--导出Excel
	 * 
	 * @param addTimef
	 *            开始时间
	 * @param addTimet
	 *            结束时间
	 * @param interval
	 *            间隔天数
	 * @param firstIndex
	 * @param pagesize
	 * @param webMaster
	 *            站长
	 * @param url
	 *            域名
	 * @param adPlanCycleId
	 *            计划周期
	 * @param adPlanId
	 *            计划
	 * @param userid
	 *            媒介
	 * @return
	 */
	public List findDomainExcelData(String addTimef, String addTimet,
			int interval, int isaccept, String domain, Users users,
			String qryUserGroupId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.CountTime, t.CountHour, t.Domain, t.pv, t.uv, t.ip, t.p, t.c, ");
		sql.append(" t.AdPlanCycleName, t.AdPlanName, t.WebMasterName, t.AdCreativeName from (");

		for (int i = 0; i <= interval; i++) {

			String tab = sdf.format(addDay(addTimef, i));
			sql.append("select * from ");
			sql.append(" (select a.Domain, a.CountHour, a.CountTime, c.p, c.c, a.pv, a.uv, a.ip, ");
			sql.append(" ac.AdPlanCycleName, ap.AdPlanName, w.WebMasterName, adc.AdCreativeName, ");
			sql.append(" a.[Browse] as bro, a.BrowseTrue as brot  ");
			sql.append(" from junnew.DomainCount_").append(tab);
			sql.append(" a left join junnew.chinaarea c ");
			// sql.append(" where a.ProvinceId = c.pid and a.CityId = c.cid group by c.p ) ");
			sql.append(" on a.ProvinceId = c.pid and a.CityId = c.cid  ");

			sql.append(" left join junnew.ReadyBox r on a.ReadyBoxId= r.ReadyBoxId ");
			sql.append(" left join junnew.AdPlanCycle ac on r. AdPlanCycleId = ac.AdPlanCycleId ");
			sql.append(" left join junnew.AdPlan ap on r. AdPlanId = ap.AdPlanId ");
			sql.append(" left join junnew.WebMaster w on r. WebMasterId = w.WebMasterId ");
			sql.append(" left join junnew.users u on r. userid = u.userid ");
			sql.append(" left join junnew.Url url on r.UrlId = url.UrlId  ");
			sql.append(" left join junnew.AdCreative adc on r.AdCreativeId= adc.AdCreativeId ");
			sql.append(" where 1=1 ");

			if (!"".equals(isaccept) && isaccept != -1) {// 是否合法来源 1－合法，0－不合法
				sql.append(" and a.IsAccept =:isaccept ");
			}
			if (!"".equals(domain) && domain != null) {// 域名 如baidu.com
				sql.append(" and a.Domain like :domain ");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				sql.append(" where ap.userGroup = :userGroup");
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					sql.append(" where  ap.userGroup = :userGroup");
				}
			}
			// if (!"".equals(adPlanCycleId) && adPlanCycleId != null) {
			// sql.append(" and ac.AdPlanCycleId =:adPlanCycleId ");
			// }
			//
			// if (!"".equals(adPlanId) && adPlanId != null) {
			// sql.append(" and ap.AdPlanId =:adPlanId ");
			// }
			// if (!"".equals(userid) && userid != null) {
			// sql.append(" and u.userid =:userid ");
			// }

			sql.append(" ) tt ");

			sql.append(" union all ");

		}
		sql.setLength(sql.length() - 10);
		sql.append(" ) t order by t.Domain ");

		List l = new ArrayList();

		try {
			Query qry = getSession().createSQLQuery(sql.toString());

			if (!"".equals(isaccept) && isaccept != -1) {
				qry.setInteger("isaccept", isaccept);
			}
			// if (!"".equals(url) && url != null) {
			// qry.setString("url", "%"+ url +"%");
			// }
			if (!"".equals(domain) && domain != null) {
				qry.setString("domain", "%" + domain + "%");
			}
			if (users.getUserGroup() != 0) {
				// 执行
				qry.setInteger("userGroup", users.getUserGroup());
			} else {
				// 管理员
				if (null != qryUserGroupId && !"".equals(qryUserGroupId)) {
					qry.setInteger("userGroup",
							Integer.parseInt(qryUserGroupId));
				}
			}
			// if (!"".equals(adPlanId) && adPlanId != null) {
			// qry.setString("adPlanId", adPlanId);
			// }
			// if (!"".equals(userid) && userid != null) {
			// qry.setString("userid", userid);
			// }

			l = qry.list();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			getSession().close();

		}

		return l;
	}

	/**
	 * 日期加加一天
	 * 
	 * @param strDate
	 * @param rd
	 * @return
	 */
	public static Date addDay(String strDate, int rd) {
		if (strDate == null)
			return null;
		try {
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			java.util.Date date = formatter.parse(strDate);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(GregorianCalendar.DATE, rd);
			date = calendar.getTime();
			return date;
		} catch (ParseException pe) {

		}
		return null;
	}

}
