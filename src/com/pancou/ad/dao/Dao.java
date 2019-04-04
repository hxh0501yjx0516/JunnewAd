package com.pancou.ad.dao;

import java.util.ArrayList;
import java.util.List;
import hib.HibernateSessionFactory;
import javax.persistence.Entity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


@Entity
public class Dao {
	
	/** 获得Session */
	public synchronized Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	/**
	 * 一览页面(有分页)
	 * @param clazz 表对应的类名
	 * @param firstIndex 第一条
	 * @param pagesize 显示条数
	 * @param pname 查询参数名
	 * @param intername 查询参数值
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, 
			int pagesize, String pname, String nameValue) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			if (nameValue != null && !"".equals(nameValue)) {
				hql.append(" where ").append(pname).append(" like :value ");
			}
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			if (nameValue != null && !"".equals(nameValue)) {
				qry.setString("value", "%"+nameValue.trim()+"%");
			}
			// 第一条
			qry.setFirstResult(firstIndex);
			// 该页最后一条
			qry.setMaxResults(pagesize);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	

	/**
	 * 根据条件查询（无分页）
	 * @param clazz 类名
	 * @param pname 参数
	 * @param pvalue 参数值
	 * @return
	 */
	public List findAll(Class clazz, String[] pname, String[] pvalue) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i =0; i< pname.length; i++) {
				// 循环查询条件数组
				if (i == 0 ) {
					// 第一个查询条件，只有一个查询条件情况
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
				} else {
					// 其他查询条件
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	
	/**
	 * 一览页面（有分页）
	 * @param clazz 表对应的类名
	 * @param firstIndex 第一条
	 * @param pagesize 显示条数
	 * @param pname 查询参数名
	 * @param intername 查询参数值
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, 
			int pagesize, String[] pname, String[] pvalue) { 
		try {
			boolean flag = false;
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			
			for (int i =0; i< pname.length; i++) {
				// 循环查询条件数组
				if ((i == 0 || flag == false) && pvalue[i] != null && !"".equals(pvalue[i])) {
					// 第一个查询条件，或只有一个查询条件时
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
					flag = true;
				} else if (pvalue[i] != null && !"".equals(pvalue[i]) && flag==true) {
					// 其他查询条件
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i =0; i< pname.length; i++) {
				if ( pvalue[i] != null && !"".equals(pvalue[i])) {
					qry.setString("value"+i, pvalue[i]);
				}
			}
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	
	/** 保存实体 */
	public void save(Object entity) {
		try {
			getSession().save(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
	}
	/** 保存实体 */
	public void saveOrUpdate(Object entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
	}


	/** 删除实体 */
	public void delete(Object entity) {
		try {
			getSession().delete(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			getSession().beginTransaction().rollback();
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
	}
	
	/** 加载实体*/
	public void load(Class clazz, java.io.Serializable id){
	
		getSession().load(clazz, id);
	}


	
	/** 更新所有记录 */

	public boolean update(Object entity) {
		boolean flag = false;
		;
		try {
			getSession().beginTransaction().begin();
			getSession().merge(entity);
			getSession().getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			getSession().close();
		}
		return flag;
	}
	
	/**
	 * 统计对象记录
	 * @param clazz 表对应的类名
	 * @param pname 查询的参数名
	 * @param nameValue 查询参数值
	 * @return
	 */
	public int getCount(Class clazz, String pname, String nameValue) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from " + clazz.getName());
			if (nameValue != null && !"".equals(nameValue)) {
				sb.append(" where ").append(pname).append(" like :value ");
			}
			Query qry = getSession().createQuery(sb.toString());
			// 设置参数
			qry.setString("value", "%"+nameValue.trim()+"%");
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	
	/**
	 * 统计对象记录
	 * @param clazz 表对应的类名
	 * @param pname 查询参数名
	 * @param nameValue 查询参数值
	 * @return
	 */
	public int getCount(Class clazz, String[] pname, String[] pvalue) {
		try {
			getSession().clear();			
			StringBuffer hql = new StringBuffer();
			hql.append("select count(*) from ").append(clazz.getName());
			
			for (int i =0; i< pname.length; i++) {
				// 循环查询条件数组
				if (i == 0 && pvalue[i] != null) {
					// 第一个查询条件，或只有一个查询条件时
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
				} else if (pvalue[i] != null) {
					// 其他查询条件
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	/**
	 * 通过自定义条件语句取列表
	 * @param clazz 表对应的列名
	 * @param firstIndex 开始游标
	 * @param pagesize 每页条数
	 * @param strsql 条件语句
	 * @return
	 */
	public List findAllBySql(Class clazz, int firstIndex, 
			int pagesize, String strsql) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			if (strsql != null && !"".equals(strsql)) {
				hql.append(" where "+strsql);
			}
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 第一条
			qry.setFirstResult(firstIndex);
			// 该页最后一条
			qry.setMaxResults(pagesize);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}

	/**
	 * 根据表及参数条件查询
	 * @param tabName 表名
	 * @param index 第一页
	 * @param pagesize 显示条数
	 * @param pname 参数名
	 * @param pvalue 参数值
	 * @return
	 */
	public List findByPara(String tabName, int index, int pagesize, String[] pname, String[] pvalue){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(tabName);

		for (int i =0; i< pname.length; i++) {
			// 循环查询条件数组
			if (i == 0 ) {
				// 第一个查询条件或只有一个查询条件时
				sql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
			} else {
				// 其他查询条件
				sql.append(" and ").append(pname[i]).append("=:value"+i+" ");
			}
		}
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// 设置参数
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);				
			}
			// 分页第一条
			qry.setFirstResult(index);
			// 分页，页面显示条数
			qry.setMaxResults(pagesize);
			return qry.list();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
			
		} finally{
			getSession().close();
		}
	}
	

	/**
	 * 根据条件查询表中数据
	 * @param tabName 表名
	 * @param pname   查询条件
	 * @param pvalue  参数值
	 * @return
	 */
	public int countByPara(String tabName, String[] pname, String[] pvalue){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);

		for (int i =0; i< pname.length; i++) {
			// 循环查询条件数组
			if (i == 0 ) {
				// 第一个查询条件，或只有一个查询条件 时
				sql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
			} else {
				// 其他查询条件
				sql.append(" and ").append(pname[i]).append("=:value"+i+" ");
			}
		}
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// 设置参数
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	
	/**
	 * 原生sql查询
	 * @param firstIndex 开始游标
	 * @param pagesize 每页条数
	 * @param strsql  完整的sql
	 * @return
	 */
	public List findBySql(int firstIndex, int pagesize, String strsql){
		try{
			Query qry = getSession().createSQLQuery(strsql);
			// 第一条
			qry.setFirstResult(firstIndex);
			// 该页最后一条
			qry.setMaxResults(pagesize);
			List l = qry.list();
			return l;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			getSession().close();
		}		
	}

	/**
	 * 通过传条件子句获得数目
	 * @param tabName 表名
	 * @param sqlCondi  sql条件子句
	 * @return
	 */
	public int countBySql(String tabName, String sqlCondi){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);
		if (sqlCondi!=""&&sqlCondi!=null)
			sql.append(" where ").append(sqlCondi);
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());			
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	
	/**
	 * 查询字段的最大值
	 * @param tabName 表名
	 * @param colName  字段名
	 * @return
	 */
	public int getColMax(String tabName, String colName){
		String sql ="";
		sql="select max("+colName+") from "+tabName;
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());			
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	
	/**
	 * 自定义sql的增删改
	 * @param sql
	 */
	public void doSql (String sql) {
		try{			
			getSession().beginTransaction();
			Query qry = getSession().createQuery(sql);
			qry.executeUpdate();
			getSession().beginTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			getSession().close();
			
		} finally {
			
			getSession().close();
		}
	}

	/**
	 * 根据条件查询（无分页）
	 * @param clazz 类名
	 * @param pname 参数
	 * @param pvalue 参数值
	 * @return
	 */
	public List findNews(Class clazz) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName()).append(" where state=1 ");
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}

	/**
	 * 根据条件查询（无分页）
	 * @param clazz 类名
	 * @param pname 参数
	 * @param pvalue 参数值
	 * @return
	 */
	public List findAllByTab(Class clazz) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	
	/**
	 * 测试SP通道 SQL
	 * @return
	 */
	public List findAllSp (Class clazz1, Class clazz2, int cpid) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("select t2.interid as interid,t2.name as name,t2.longcode as longcode, t2.alias as alias FROM ").append(clazz1.getName()).append(" t1, ");
			hql.append(clazz2.getName()).append(" t2 where t1.interid = t2.interid " );
			hql.append(" and t1.cpid =:cpid and t1.state=1 and t1.balance > 0 order by t1.cpid ");
			
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			qry.setInteger("cpid", cpid);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	}

}
