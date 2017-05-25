package com.jeeboot.admin.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.alibaba.fastjson.JSON;
import com.jeeboot.admin.dao.BaseDao;

/**
 * @author Administrator
 * 
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	@Resource(name = "sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获取实体类
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获取实体类名
	 * 
	 * @return
	 */
	protected String getEntityName() {
		return getEntityClass().getName();
	}

	protected String getIdentifierPropertyName() {
		String field = super.getSessionFactory().getClassMetadata(getEntityName()).getIdentifierPropertyName();
		return field;
	}

	/**
	 * 根据条件查询总记录数
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 总记录数
	 * @throws Exception
	 */
	protected Integer findCountByCriteria(final Criterion... criterions) throws Exception {
		return findCountByCriteria(Arrays.asList(criterions));
	}

	/**
	 * 根据条件查询总记录数
	 * 
	 * @param criterions
	 *            查询条件
	 * @return 总记录数
	 * @throws Exception
	 */
	protected Integer findCountByCriteria(final List<Criterion> criterions) throws Exception {
		int count = 0;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(getEntityClass());
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			criteria.setProjection(Projections.rowCount());
			count = Integer.parseInt(criteria.uniqueResult().toString());
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	protected Object findAggregateByCriteria(ProjectionList projectionList, final List<Criterion> criterions) throws Exception {

		Object obj = null;

		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(getEntityClass());

			if (projectionList != null) {
				criteria.setProjection(projectionList);
			}

			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			obj = criteria.list();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return obj;
	}

	/**
	 * 根据条件查询
	 * 
	 * @param criterions
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final Criterion... criterions) throws Exception {
		return findByCriteria(Arrays.asList(criterions));
	}

	/**
	 * 根据条件查询
	 * 
	 * @param criterions
	 *            查询条件
	 * @return
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final List<Criterion> criterions) throws Exception {
		return findByCriteria(null, true, 0, 0, 0, criterions);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param orderProperty
	 *            排序列属性名
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param criterions
	 *            查询条件
	 * @return list集合
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final List<Criterion> criterions) throws Exception {
		return findByCriteria(orderProperty, isAsc, 0, 0, 0, criterions);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param orderProperty
	 *            排序列属性名
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param criterions
	 *            查询条件
	 * @return list集合
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final Criterion... criterions) throws Exception {
		return findByCriteria(orderProperty, isAsc, 0, 0, 0, criterions);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param orderProperty
	 *            排序列属性名
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param firstResult
	 *            开始记录序号
	 * @param maxResults
	 *            最大记录数
	 * @param criterions
	 *            查询条件
	 * @return list集合
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final int pageSize, final int pageNo, final int totalCount,
					final Criterion... criterions) throws Exception {
		return findByCriteria(orderProperty, isAsc, pageSize, pageNo, totalCount, Arrays.asList(criterions));
	}

	/**
	 * 根据条件查询
	 * 
	 * @param orderProperty
	 *            实体类名
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param firstResult
	 *            开始记录序号
	 * @param maxResults
	 *            最大记录数
	 * @param criterions
	 *            查询条件
	 * @return list集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final int pageSize, final int pageNo, final int totalCount,
					final List<Criterion> criterions) throws Exception {
		List<T> list = new ArrayList<T>();
		Session session = null;
		try {
			if (session == null) {
				session = getHibernateTemplate().getSessionFactory().openSession();
			}
			Criteria criteria = session.createCriteria(getEntityClass());

			// 按属性条件查询
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			// 按某个属性排序
			if (orderProperty != null && orderProperty.length() > 0) {
				if (isAsc) {
					criteria.addOrder(Order.asc(orderProperty));
				} else {
					criteria.addOrder(Order.desc(orderProperty));
				}
			}
			// 用于分页查询
			if (pageSize > 0) {
				criteria.setFirstResult(pageSize * (pageNo - 1));
				criteria.setMaxResults(pageSize * pageNo > totalCount ? totalCount % pageSize : pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/**
	 * 查询
	 * 
	 * @param orderProperty
	 *            排序字段
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param firstResult
	 *            起始编号
	 * @param maxResults
	 *            结束编号
	 * @param criterions
	 *            查询条件
	 * @return List<T>
	 * @throws Exception
	 */
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final int firstResult, final int maxResults,
					final Criterion... criterions) throws Exception {
		return findByCriteria(orderProperty, isAsc, firstResult, maxResults, Arrays.asList(criterions));
	}

	/**
	 * 查询
	 * 
	 * @param orderProperty
	 *            排序字段
	 * @param isAsc
	 *            排序方式,true表示升序,false表示降序,当propertyName赋值为null时,此参数无效
	 * @param firstResult
	 *            起始编号
	 * @param maxResults
	 *            结束编号
	 * @param criterions
	 *            查询条件
	 * @return List<T>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(final String orderProperty, final boolean isAsc, final int firstResult, final int maxResults,
					final List<Criterion> criterions) throws Exception {
		List<T> list = new ArrayList<T>();
		Session session = null;
		try {
			if (session == null) {
				session = getHibernateTemplate().getSessionFactory().openSession();
			}
			Criteria criteria = session.createCriteria(getEntityClass());

			// 按属性条件查询
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			// 按某个属性排序
			if (orderProperty != null && orderProperty.length() > 0) {
				if (isAsc) {
					criteria.addOrder(Order.asc(orderProperty));
				} else {
					criteria.addOrder(Order.desc(orderProperty));
				}
			}

			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResults);

			list = criteria.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/**
	 * 查询条数
	 * 
	 * @param hql
	 *            HQL字符串
	 * @param values
	 *            参数值,注意顺序
	 * @return
	 * @throws Exception
	 */
	protected Integer findCountByHql(final String hql, final Object... values) throws Exception {
		int count = 0;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			//将Long型转换为int类型
			count = ((Long)query.uniqueResult()).intValue();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	protected Object findAggregateByHql(final String hql, final Object... values) throws Exception {
		Object obj = null;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			obj = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return obj;
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param hql
	 *            HQL字符串
	 * @param values
	 *            值,注意顺序
	 * @return
	 * @throws Exception
	 */
	protected List<T> findByHql(final String hql, final Object... values) throws Exception {
		return findByHql(hql, 0, 0, 0, values);
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param hql
	 *            HQL字符串
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNo
	 *            页码
	 * @param totalCount
	 *            总条数
	 * @param values
	 *            参数值,注意顺序
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByHql(final String hql, final Integer pageSize, final Integer pageNo, Integer totalCount, Object... values) throws Exception {
		List<T> list = new ArrayList<T>();
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			System.out.println(hql);
			System.out.println(JSON.toJSONString(values));
			if (values != null && values.length > 0) {
				int i = 0;
				for (Object value : values) {
					if (value.getClass().isArray()) {
						Object[] arr = (Object[]) value;
						for (Object item : arr) {
							query.setParameter(i, item);
							System.out.println(query.getNamedParameters().length);
							i++;
						}
					} else {
						query.setParameter(i, value);
						i++;
					}
				}
			}
			if (pageSize > 0) {
				query.setFirstResult(pageSize * (pageNo - 1));
				query.setMaxResults(pageSize * pageNo > totalCount ? totalCount % pageSize : pageSize);
			}
			list = query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/**
	 * 执行HQL语句,适合增删改
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws Exception
	 */
	protected Integer excuteHql(final String hql, Object... values) throws Exception {
		int count = 0;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			count = query.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findBySql(final String sql, Object... values) throws Exception {
		List<T> list = new ArrayList<T>();
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			list = query.addEntity(getEntityClass()).list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/**
	 * 添加
	 * 
	 * @throws Exception
	 */
	@Override
	public Serializable save(T entity) throws Exception {
		Serializable serializable = null;
		try {
			getHibernateTemplate().clear();
			serializable = getHibernateTemplate().save(entity);
			getHibernateTemplate().flush();
		} catch (Exception e) {
			throw e;
		}

		return serializable;
	}

	/**
	 * 批量添加
	 */
	@Override
	public Boolean save(List<T> entitys) throws Exception {
		boolean flag = false;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			session.clear();
			for (int i = 0; i < entitys.size(); i++) {
				session.save(entitys.get(i));
				if (i + 1 % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean delete(Serializable id) throws Exception {
		boolean flag = false;
		try {
			T instance = getHibernateTemplate().get(getEntityClass(), id);
			if (instance != null) {
				getHibernateTemplate().clear();
				getHibernateTemplate().delete(instance);
				getHibernateTemplate().flush();
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean delete(T entity) throws Exception {
		boolean flag = false;
		try {
			getHibernateTemplate().clear();
			getHibernateTemplate().delete(entity);
			getHibernateTemplate().flush();
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 更新
	 */
	@Override
	public Boolean update(T entity) throws Exception {
		boolean flag = false;
		try {
			getHibernateTemplate().clear();
			getHibernateTemplate().update(entity);
			getHibernateTemplate().flush();
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 批量更新
	 */
	@Override
	public Boolean update(List<T> entitys) throws Exception {
		boolean flag = false;
		Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			session.clear();
			for (int i = 0; i < entitys.size(); i++) {
				session.update(entitys.get(i));
				if (i + 1 % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;
	}

	@Override
	public T findById(Serializable id) throws Exception {
		T instance = null;
		try {
			instance = getHibernateTemplate().get(getEntityClass(), id);
		} catch (Exception e) {
			throw e;
		}
		return instance;
	}

	@Override
	public List<T> findAll() throws Exception {
		List<T> list = new ArrayList<T>();
		try {
			list = getHibernateTemplate().loadAll(getEntityClass());
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	@Override
	public Boolean isRepeat(Serializable id) throws Exception {
		boolean flag = false;
		try {
			int count = findCountByCriteria(Restrictions.eq(getIdentifierPropertyName(), id));
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}
}
