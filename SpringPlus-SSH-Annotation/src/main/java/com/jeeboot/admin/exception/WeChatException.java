package com.jeeboot.admin.exception;

import com.jeeboot.admin.message.ResponseResult;

public class WeChatException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResponseResult responseResult;
	
	public WeChatException(ResponseResult responseResult){
		super();
		this.responseResult = responseResult;
	}

	public ResponseResult getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(ResponseResult responseResult) {
		this.responseResult = responseResult;
	}

}
