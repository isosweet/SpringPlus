package com.jeeboot.admin.dao;

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

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	Boolean delete(final Serializable id) throws Exception;

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
	 * 批量更新
	 * 
	 * @param entitys
	 * @return
	 * @throws Exception
	 */
	Boolean update(List<T> entitys) throws Exception;

	/**
	 * 主键查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findById(final Serializable id) throws Exception;

	/**
	 * 查询全部
	 * 
	 * @return
	 * @throws Exception
	 */
	List<T> findAll() throws Exception;

	/**
	 * 根据ID检测重复
	 * 
	 * @param id
	 * @return
	 */
	Boolean isRepeat(final Serializable id) throws Exception;

}