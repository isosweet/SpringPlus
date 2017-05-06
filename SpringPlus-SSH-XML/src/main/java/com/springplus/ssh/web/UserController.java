package com.springplus.ssh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springplus.ssh.entity.User;
import com.springplus.ssh.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("toSaveUser")
	public String toSaveUser(){
		
		return "user";
	}
	
	@RequestMapping("doSaveUser")
	public @ResponseBody String doSaveUser(User user) throws Exception{
		
		
		System.out.println(user.getUserName());
		
		userService.saveUser(user);
		
		return "";
	}
	
	
	
	
	
	
}
