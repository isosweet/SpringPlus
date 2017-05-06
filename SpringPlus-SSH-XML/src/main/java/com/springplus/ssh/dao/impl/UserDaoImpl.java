package com.springplus.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import com.springplus.ssh.dao.UserDao;
import com.springplus.ssh.dao.base.BaseDaoImpl;
import com.springplus.ssh.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	
}
