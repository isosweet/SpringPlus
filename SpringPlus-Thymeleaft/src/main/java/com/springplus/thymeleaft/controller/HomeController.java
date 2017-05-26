package com.springplus.thymeleaft.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("home")
	public String toHome(Model model){
		
		model.addAttribute("home", "这是首页");
		
		model.addAttribute("today", new Date());
		return "home";
	}
}
