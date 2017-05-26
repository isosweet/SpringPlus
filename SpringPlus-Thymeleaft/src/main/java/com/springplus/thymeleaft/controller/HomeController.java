package com.springplus.thymeleaft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@RequestMapping("toHome")
	public String toHome(Model model){
		
		model.addAttribute("home", "这是首页");
		return "home";
	}
	
}
