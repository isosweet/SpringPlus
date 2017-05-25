package com.jeeboot.admin.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jeeboot.admin.dao.BaseDao;

/**
 * @author Administrator
 * 
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	/*
     * 注入sessionFactory
     */
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
    	// 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
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
			session = this.getSession();
			Criteria criteria = session.createCriteria(getEntityClass());
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			criteria.setProjection(Projections.rowCount());
			count = Integer.parseInt(criteria.uniqueResult().toString());
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	protected Object findAggregateByCriteria(ProjectionList projectionList, final List<Criterion> criterions) throws Exception {

		Object obj = null;

		Session session = null;
		try {
			session = this.getSession();
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
				session = this.getSession();
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
				session = this.getSession();
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
			session = this.getSession();
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
		}
		return count;
	}

	protected Object findAggregateByHql(final String hql, final Object... values) throws Exception {
		Object obj = null;
		Session session = null;
		try {
			session = this.getSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			obj = query.list();
		} catch (Exception e) {
			throw e;
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
			session = this.getSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				int i = 0;
				for (Object value : values) {
					if (value.getClass().isArray()) {
						Object[] arr = (Object[]) value;
						for (Object item : arr) {
							query.setParameter(i, item);
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
			session = this.getSession();
			Query query = session.createQuery(hql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			count = query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findBySql(final String sql, Object... values) throws Exception {
		List<T> list = new ArrayList<T>();
		Session session = null;
		try {
			session = this.getSession();
			SQLQuery query = session.createSQLQuery(sql);
			if (values != null && values.length > 0) {
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			list = query.addEntity(getEntityClass()).list();
		} catch (Exception e) {
			throw e;
		} 
		return list;
	}

	/**
	 * 添加
	 * 
	 * @throws Exception
	 */
	public Serializable save(T entity) throws Exception {
		Serializable serializable = null;
		Session session = null;
		try {
			session = getSession();
			serializable = session.save(entity);
		} catch (Exception e) {
			throw e;
		}

		return serializable;
	}
	
	public Boolean saveOrUpdate(T entity) throws Exception {
		Session session = null;
		try {
			session = getSession();
			session.saveOrUpdate(entity);
		} catch (Exception e) {
			throw e;
		}

		return true;
	}
	
	/**
	 * 批量添加
	 */
	public Boolean save(List<T> entitys) throws Exception {
		boolean flag = false;
		Session session = null;
		try {
			session = this.getSession();
			session.clear();
			for (int i = 0; i < entitys.size(); i++) {
				session.save(entitys.get(i));
				if (i + 1 % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 删除
	 */
	public Boolean delete(T entity) throws Exception {
		boolean flag = false;
		Session session = null;
		try {
			session = this.getSession();
			session.delete(entity);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 更新
	 */
	public Boolean update(T entity) throws Exception {
		boolean flag = false;
		Session session = null;
		try {
			session = this.getSession();
			session.update(entity);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public T findById(Serializable id) throws Exception {
		T instance = null;
		Session session = null;
		try {
			session = this.getSession();
			instance = (T) session.get(getEntityClass(), id);
		} catch (Exception e) {
			throw e;
		}
		return instance;
	}

}
