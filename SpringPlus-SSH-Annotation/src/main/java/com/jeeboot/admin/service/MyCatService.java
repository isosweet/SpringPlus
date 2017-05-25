package com.jeeboot.admin.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeboot.admin.dao.MyCatDao;
import com.jeeboot.admin.entity.MyCat;

@Service("myCatService")
@Transactional
public class MyCatService {
	
	@Autowired
	private MyCatDao myCatDao;
	
	public Boolean save(MyCat myCat) throws Exception{
		Serializable seri = myCatDao.save(myCat);
		if (seri != null) return true;
		return false;
	}
	
	public MyCat findMyCatById(String id) throws Exception{
		return myCatDao.findById(id);
	}
	
	
	
}
