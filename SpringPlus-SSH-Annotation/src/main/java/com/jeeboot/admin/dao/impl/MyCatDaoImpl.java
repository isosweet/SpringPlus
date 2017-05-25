package com.jeeboot.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeeboot.admin.dao.MyCatDao;
import com.jeeboot.admin.entity.MyCat;

@Repository("myCatDao")
public class MyCatDaoImpl extends BaseDaoImpl<MyCat> implements MyCatDao {



}
