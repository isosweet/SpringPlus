package com.springplus.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springplus.ssm.entity.User;
import com.springplus.ssm.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService serv;
	
	@GetMapping("getUser")
	public @ResponseBody  long getUser(String userId) throws Exception{
		
		System.out.println("userid---->" + userId);
		
		return serv.getUser(userId);
	}
	
}
