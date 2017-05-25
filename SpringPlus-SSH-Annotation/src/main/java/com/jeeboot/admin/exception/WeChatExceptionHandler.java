package com.jeeboot.admin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jeeboot.admin.message.ResponseResult;

@ControllerAdvice
public class WeChatExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(WeChatException.class)
	public Object exceptionhandling(WeChatException weChatException){
		
		ResponseResult responseResult = weChatException.getResponseResult();
		
		return JSON.toJSONString(responseResult);
	}
	
}
