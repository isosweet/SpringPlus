package com.springplus.ssh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springplus.ssh.dao.UserDao;
import com.springplus.ssh.entity.User;

@Service("userService")
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void saveUser(User user) throws Exception{		
		userDao.save(user);
	}	
	
	
	
}
