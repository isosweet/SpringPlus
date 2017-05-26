package com.springplus.thymeleaft.dao.impl;

import org.springframework.stereotype.Repository;

import com.springplus.thymeleaft.dao.UserDao;
import com.springplus.thymeleaft.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User> {

}
