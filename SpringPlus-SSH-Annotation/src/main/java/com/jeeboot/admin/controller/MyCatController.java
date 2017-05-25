package com.jeeboot.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jeeboot.admin.common.controller.BaseController;
import com.jeeboot.admin.entity.MyCat;
import com.jeeboot.admin.message.ResponseResult;
import com.jeeboot.admin.service.MyCatService;

@Controller
@RequestMapping("/myCat")
public class MyCatController extends BaseController{
	
	@Autowired
	private MyCatService myCatService;
	
	@RequestMapping(value = "/saveMyCat", method = RequestMethod.GET)
	public String toSaveMyCat(Model model){
		
		logger.info("来自后台...");
		
		model.addAttribute("test", "来自后台");
		
		return "wechat/mycat/form";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveMyCat", method = RequestMethod.POST)
	public Object doSaveMyCat(String name, Integer age) throws Exception{
		
		MyCat myCat = new MyCat();
		myCat.setName(name);
		myCat.setAge(age);
		
		boolean flag = myCatService.save(myCat);
		if(flag)
			return JSON.toJSONString(new ResponseResult("00", "保存成功"));
		
		return JSON.toJSONString(new ResponseResult("-0", "保存失败"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/tosaveMyCat", method = RequestMethod.POST)
	public ResponseResult SaveMyCat(String name, Integer age) throws Exception{
		
		logger.info("提交表单");
		
		MyCat myCat = new MyCat();
		myCat.setName(name);
		myCat.setAge(age);
		
		boolean flag = myCatService.save(myCat);
		if(flag)
			return new ResponseResult("00", "保存成功");
		
		return new ResponseResult("-0", "保存失败");
	}
	
}
