package com.springplus.ssh.dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

	/**
	 * 添加
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Serializable save(final T entity) throws Exception;

	/**
	 * 批量添加
	 * 
	 * @param entitys
	 * @return
	 * @throws Exception
	 */
	Boolean save(final List<T> entitys) throws Exception;
	
	Boolean saveOrUpdate(T entity) throws Exception;

	/**
	 * 删除
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Boolean delete(final T entity) throws Exception;

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Boolean update(final T entity) throws Exception;

	/**
	 * 主键查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findById(final Serializable id) throws Exception;

}