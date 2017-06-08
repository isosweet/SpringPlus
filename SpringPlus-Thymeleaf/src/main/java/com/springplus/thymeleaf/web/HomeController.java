package com.springplus.thymeleaf.web;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Controller
public class HomeController {
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@RequestMapping("/")
	public String home(Model model){
		
		
		model.addAttribute("title", "这是一段动态的文字");
		return "index";
	}
	
	/**
	 * 静态化
	 * @throws IOException 
	 */
	@RequestMapping("genStatic")
	public @ResponseBody void genStatic() throws IOException{

		//构造上下文(Model)
        Context context = new Context();
        context.setVariable("title", "这是一段动态的文字,网页静态化");
		
        //渲染模板
        FileWriter write = new FileWriter("D:\\result.html");
        templateEngine.process("index", context, write);
        
        System.out.println("ok");
	}
	
	
	
}
