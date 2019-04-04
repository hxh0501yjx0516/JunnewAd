package com.pancou.ad.dao;

import hib.HibernateSessionFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

@Entity
@SuppressWarnings("unchecked")
public class PlatDao {

	/** 获得Session */
	public synchronized Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	/**
	 * 一览页面(有分页)
	 * 
	 * @param clazz
	 *            表对应的类名
	 * @param firstIndex
	 *            第一条
	 * @param pagesize
	 *            显示条数
	 * @param pname
	 *            查询参数名
	 * @param intername
	 *            查询参数值
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, int pagesize,
			String pname, String nameValue) {
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
				qry.setString("value", "%" + nameValue + "%");
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
	 * HQL全查询（有分页）
	 * 
	 * @param hql
	 *            HQL语句
	 * @param firstIndex
	 *            分页参数
	 * @param pagesize
	 *            分页参数
	 * @param condition
	 *            拼接条件
	 * @param parms
	 *            条件参数
	 * @return 对象List
	 */
	public List findAll(int firstIndex, int pagesize, String hql,
			String condition, Map<String, Object> parms) {
		try {
			List l = null;
			Query qry = getSession().createQuery(hql + condition);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
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
	 * HQL全查询（无分页）
	 * 
	 * @param hql
	 *            hql语句
	 * @param condition
	 *            条件语句
	 * @param parms
	 *            条件参数
	 * @return
	 */
	public List findAll(String hql, String condition, Map<String, Object> parms) {
		try {
			List l = null;
			Query qry = getSession().createQuery(hql + condition);

			if (parms != null) {
				Set keys = parms.keySet();
				Iterator<String> it = keys.iterator();
				while (it.hasNext()) {
					String key = it.next();
					qry.setParameter(key, parms.get(key));
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

	/**
	 * 全查询（有分页）
	 * 
	 * @param clazz
	 * @param firstIndex
	 * @param pagesize
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, int pagesize) {
		return findAll(clazz, firstIndex, pagesize, "", "");
	}

	/**
	 * 全查询（无分页）
	 * 
	 * @param clazz
	 * @return
	 */

	public List findAll(Class clazz) {
		String[] names = new String[0];
		String[] values = new String[0];
		return findAll(clazz, names, values);
	}

	/**
	 * 根据条件查询（无分页）
	 * 
	 * @param clazz
	 *            类名
	 * @param pname
	 *            参数
	 * @param pvalue
	 *            参数值
	 * @return
	 */
	public List findAll(Class clazz, String[] pname, String[] pvalue) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				if (i == 0) {
					// 第一个查询条件，或者只有一个查询条件 时
					hql.append(" where ").append(pname[i])
							.append("=:value" + i + " ");
				} else {
					// 其他查询条件
					hql.append(" and ").append(pname[i])
							.append("=:value" + i + " ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				qry.setString("value" + i, pvalue[i]);
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
	 * 根据条件查询（无分页） 和findAll不同的是，可以带order子句
	 * 
	 * @param clazz
	 *            类名
	 * @param pname
	 *            参数
	 * @param pvalue
	 *            参数值
	 * @param ordersql
	 *            order子句 如：“order by id desc”
	 * @return
	 */
	public List findAllOrder(Class clazz, String[] pname, String[] pvalue,
			String ordersql) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				if (i == 0) {
					// 第一个查询条件，或者只有一个查询条件 时
					hql.append(" where ").append(pname[i])
							.append("=:value" + i + " ");
				} else {
					// 其他查询条件
					hql.append(" and ").append(pname[i])
							.append("=:value" + i + " ");
				}
			}
			hql.append(" ").append(ordersql);
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				qry.setString("value" + i, pvalue[i]);
			}
			l = qry.list();
			// Collections.sort(l);
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}

	}

	/**
	 * 一览页面(有分页)
	 * 
	 * @param clazz
	 *            表对应的类名
	 * @param firstIndex
	 *            第一条
	 * @param pagesize
	 *            显示条数
	 * @param pname
	 *            查询参数名
	 * @param intername
	 *            查询参数值
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, int pagesize,
			String[] pname, String[] pvalue) {
		try {
			boolean flag = false;
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				if ((i == 0 || flag == false) && pvalue[i] != null) {
					// 第一个查询条件，或者只有一个查询条件 时
					hql.append(" where ").append(pname[i])
							.append("=:value" + i + " ");
					flag = true;
				} else if (pvalue[i] != null && flag == true) {
					// 其他查询条件
					hql.append(" and ").append(pname[i])
							.append("=:value" + i + " ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				qry.setString("value" + i, pvalue[i]);
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
	public int save(Object entity) {
		int id = 0;
		try {
			id = Integer.parseInt(getSession().save(entity).toString());
			// System.out.println("保存后实体的ID为："+id);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
		return id;
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

	/** 加载实体 */
	public void load(Class clazz, java.io.Serializable id) {

		getSession().load(clazz, id);
	}

	/** 根据ID查找实体 */

	public Object findById(Class clazz, int id) {
		Object obj = getSession().get(clazz, id);
		return obj;
	}

	/** 更新所有记录 */

	public boolean update(Object entity) {
		boolean flag = false;
		try {
			getSession().beginTransaction().begin();
			getSession().merge(entity);
			getSession().getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().close();
		}
		return flag;
	}

	/**
	 * 统计对象记录数
	 * 
	 * @param clazz
	 *            表对应的类名
	 * @param pname
	 *            查询参数名
	 * @param nameValue
	 *            查询参数值
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
			if (nameValue != null && !"".equals(nameValue)) {
				qry.setString("value", "%" + nameValue.trim() + "%");
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0)
					.toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}

	}

	/**
	 * 统计对象记录
	 * 
	 * @param clazz
	 *            表对应的类名
	 * @param pname
	 *            查询参数名
	 * @param nameValue
	 *            查询参数值
	 * @return
	 */
	public int getCount(Class clazz, String[] pname, String[] pvalue) {
		try {
			getSession().clear();
			StringBuffer hql = new StringBuffer();
			hql.append("select count(*) from ").append(clazz.getName());

			for (int i = 0; i < pname.length; i++) {
				// 循环查询条件数组
				if (i == 0 && pvalue[i] != null) {
					// 第一个查询条件，或只有一个查询条件时
					hql.append(" where ").append(pname[i])
							.append("=:value" + i + " ");
				} else if (pvalue[i] != null) {
					// 其他查询条件
					hql.append(" and ").append(pname[i])
							.append("=:value" + i + " ");
				}
			}
			Query qry = getSession().createQuery(hql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				qry.setString("value" + i, pvalue[i]);
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0)
					.toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}

	}

	/**
	 * 依据查询条件统计对象记录数
	 * 
	 * @param clazz
	 *            查询实体类
	 * @param condition
	 *            条件语句（条件开头以and开始，字段名前加实体类的小写首字母，如: and u.userId = :userId）
	 * @param parms
	 *            参数集合
	 * @return
	 */
	public int getCount(Class clazz, String condition, Map<String, Object> parms) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			String buffux = "";
			if (null != clazz.getName()) {
				int index = clazz.getName().lastIndexOf(".");
				buffux = clazz.getName().substring(index + 1, index + 2)
						.toLowerCase();
			}
			sb.append("select count(*) from " + clazz.getName()).append(" ")
					.append(buffux).append(" where 1=1 ").append(condition);
			Query qry = getSession().createQuery(sb.toString());
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0)
					.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			getSession().close();
		}

	}

	/**
	 * 
	 * 依据查询条件统计对象记录数 (传入SQL语句)
	 * 
	 * @param sql
	 *            sql查询数据源（表名）
	 * @param condition
	 *            where条件
	 * @param parms
	 *            条件参数
	 * @return
	 */
	public int getCount(String sql, Map<String, Object> parms) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append(sql);
			Query qry = getSession().createSQLQuery(sb.toString());
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0)
					.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			getSession().close();
		}

	}

	/**
	 * 通过传自定义条件语句取列表
	 * 
	 * @param clazz
	 *            表对应的类名
	 * @param firstIndex
	 *            开始游标
	 * @param pagesize
	 *            每页条数
	 * @param strsql
	 *            条件语句
	 * @return
	 */
	public List findAllBySql(Class clazz, int firstIndex, int pagesize,
			String strsql) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			if (strsql != null && !"".equals(strsql)) {
				hql.append(" where " + strsql);
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
	 * 根据表名及参数查询数据
	 * 
	 * @param tabName
	 *            表名
	 * @param index
	 *            第一页
	 * @param pagesize
	 *            显示条数
	 * @param pname
	 *            参数名
	 * @param pvalue
	 *            参数值
	 * @return
	 */
	public List findByPara(String tabName, int index, int pagesize,
			String[] pname, String[] pvalue) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(tabName);

		for (int i = 0; i < pname.length; i++) {
			// 循环查询条件数组
			if (i == 0) {
				// 第一个查询条件，或者只有一个查询条件 时
				sql.append(" where ").append(pname[i])
						.append("=:value" + i + " ");
			} else {
				// 其他查询条件
				sql.append(" and ").append(pname[i])
						.append("=:value" + i + " ");
			}
		}
		try {
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				qry.setString("value" + i, pvalue[i]);
			}
			// 分页第一条
			qry.setFirstResult(index);
			// 分页，页面显示条数
			qry.setMaxResults(pagesize);
			return qry.list();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			getSession().close();
		}
	}

	/**
	 * 根据条件查询表中数据总数
	 * 
	 * @param tabName
	 *            表名
	 * @param pname
	 *            查询条件
	 * @param pvalue
	 *            参数值
	 * @return
	 */
	public int countByPara(String tabName, String[] pname, String[] pvalue) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);

		for (int i = 0; i < pname.length; i++) {
			// 循环查询条件数组
			if (i == 0) {
				// 第一个查询条件，或者只有一个查询条件 时
				sql.append(" where ").append(pname[i])
						.append("=:value" + i + " ");
			} else {
				// 其他查询条件
				sql.append(" and ").append(pname[i])
						.append("=:value" + i + " ");
			}
		}
		try {
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// 设置参数
			for (int i = 0; i < pname.length; i++) {
				qry.setString("value" + i, pvalue[i]);
			}
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list()
					.get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		} finally {
			getSession().close();
		}
	}

	/**
	 * 不带参数原生态SQL查询
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param strsql
	 * @return
	 */
	public List findBySql(int firstIndex, int pagesize, String strsql) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);

			qry.setFirstResult(firstIndex);

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
	 * 原生态SQL查询，以变量名显式调用返回值
	 * 
	 * @param firstIndex
	 *            分页开始
	 * @param pagesize
	 *            每页个数
	 * @param strsql
	 *            sql语句
	 * @param parms
	 *            参数map
	 * @param objectes
	 *            显示调用名称集合(此名称对应sql语句中变量别名，如果为class对象类型则sql中对象别名为小写首字母)
	 * @return
	 */

	public List findBySql(int firstIndex, int pagesize, String strsql,
			Map<String, Object> parms, List<Object> objectes) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			for (int i = 0; i < objectes.size(); i++) {
				Object o = objectes.get(i);
				if (o instanceof String) {
					qry.addScalar(o.toString(), Hibernate.STRING);
				} else if (o instanceof Class) {
					int index = ((Class) o).getName().lastIndexOf(".");
					String className = ((Class) o).getName()
							.substring(index + 1, index + 2).toLowerCase();
					qry.addEntity(className, (Class) o);
				}
			}
			// qry.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			qry.setFirstResult(firstIndex);

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
	 * 带参数的原生态sql查询
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param strsql
	 *            SQL语句
	 * @param parms
	 *            参数MAP
	 * @return
	 */

	public List findBySql(int firstIndex, int pagesize, String strsql,
			Map<String, Object> parms) {
		List l = null;
		try {
			SQLQuery qry = (SQLQuery) getSession().createSQLQuery(strsql);
			// .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			qry.setFirstResult(firstIndex);

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
	 * 带参数的原生态sql查询（无分页）
	 * 
	 * @param firstIndex
	 * @param pagesize
	 * @param strsql
	 *            SQL语句
	 * @param parms
	 *            参数MAP
	 * @return
	 */

	public List findBySql(String strsql, Map<String, Object> parms) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
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
	 * 带参数的原生态sql查询（无分页）
	 * 
	 * @param strsql
	 * @param parms
	 * @param cols
	 * @return
	 */

	public List findBySql(String strsql, Map<String, Object> parms,
			List<Object> cols) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			for (int i = 0; i < cols.size(); i++) {
				Object o = cols.get(i);
				if (o instanceof String) {
					qry.addScalar(o.toString(), Hibernate.STRING);
				} else if (o instanceof Class) {
					int index = ((Class) o).getName().lastIndexOf(".");
					String className = ((Class) o).getName()
							.substring(index + 1, index + 2).toLowerCase();
					qry.addEntity(className, (Class) o);
				}
			}
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				qry.setParameter(key, parms.get(key));
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
	 * 自定义SQL查询SUM
	 * 
	 * @param strsql
	 * @return
	 */
	public List getSum(String strsql) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
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
	 * 通过传条件子句查询总计数目
	 * 
	 * @param tabName
	 *            表名
	 * @param sqlCondi
	 *            sql条件子句
	 * @return
	 */
	public int countBySql(String tabName, String sqlCondi) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);
		if (sqlCondi != "" && sqlCondi != null)
			sql.append(" where ").append(sqlCondi);
		try {
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list()
					.get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		} finally {
			getSession().close();
		}
	}

	/**
	 * 查询字段最大值
	 * 
	 * @param tabName
	 *            表名
	 * @param colName
	 *            字段名
	 * @return
	 */
	public int getColMax(String tabName, String colName) {
		String sql = "";
		sql = "select max(" + colName + ") from " + tabName;
		try {
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list()
					.get(0).toString());

		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		} finally {
			getSession().close();
		}
	}

	/**
	 * 自定义sql增删改
	 * 
	 * @param sql
	 */
	public void doSql(String sql) {
		try {
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
	 * 自定义sql增删改（带参数）
	 * 
	 * @param sql
	 */
	public int doSql(String sql, Map<String, Object> parms) {
		int count = 0;
		try {

			getSession().beginTransaction();
			Query qry = getSession().createQuery(sql).setProperties(parms);
			count = qry.executeUpdate();
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().close();
		} finally {

			getSession().close();
		}
		return count;
	}

	/**
	 * 执行存储过程
	 * 
	 * @param strsql
	 *            存储过程语句
	 * @param parms
	 *            参数
	 * @return
	 */

	public int doCall(String strsql, Map<Integer, Object> parms) {
		int count = 0;
		try {
			getSession().beginTransaction();
			SQLQuery qry = getSession().createSQLQuery(strsql);
			Set keys = parms.keySet();
			Iterator<Integer> it = keys.iterator();
			while (it.hasNext()) {
				int key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			count = qry.executeUpdate();
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
		return count;
	}

	/**
	 * 自定义sql查询,需手动关闭连接
	 * 
	 * @param sql
	 */
	public Query query(String sql, int firstIndex, int pagesize) {
		Query qry = query(sql);
		if (0 != firstIndex && 0 != pagesize) {
			qry.setFirstResult(firstIndex);
			qry.setMaxResults(pagesize);
		}
		return qry;
	}

	/**
	 * 自定义sql查询,需手动关闭连接
	 * 
	 * @param sql
	 */
	public Query query(String sql) {
		Query qry = getSession().createQuery(sql);
		return qry;
	}

	public void closeSession() {
		if (getSession() != null && getSession().isOpen()) {
			getSession().close();
		}
	}

	/** 分页用:获取指定数量对象记录 传入 HQL,开始位置,最大记录数; 2010-4-27 */
	public List getPage(Class clazz, int firstIndex, int pagesize,
			HttpServletRequest request) {

		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append("select * from cp_user where 1=1 ");
			if ("1".equals(request.getParameter("sonid"))) {// 查询所有账户 包括子账户
				sb.append(" and pcpid=0 ");
			} else {
				sb.append(" and pcpid>-1 "); // 仅仅查一级账户
			}
			if ("".equals(request.getParameter("cpid"))
					|| request.getParameter("cpid") == null) {// 不查询cpid
				sb.append(" and cpid>-1 ");
			} else {
				sb.append(" and cpid=:cpid ");
			}
			if (!"".equals(request.getParameter("qryname"))
					&& request.getParameter("qryname") != null) {// 登录名查询
				sb.append(" and name like '%qryname%' ");
			}

			SQLQuery query = getSession().createSQLQuery(sb.toString());

			// Query query =
			// getSession().createSQLQuery("select name from sp_service group by name");
			query.setFirstResult(firstIndex);
			query.setMaxResults(pagesize);
			if (!"".equals(request.getParameter("cpid"))
					&& request.getParameter("cpid") != null) {// 不查询cpid
				query.setString("cpid", request.getParameter("cpid"));
			}
			if (!"".equals(request.getParameter("qryname"))
					&& request.getParameter("qryname") != null) {// 登录名查询
				query.setString("qryname", request.getParameter("qryname"));
			}

			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}

	}
}
