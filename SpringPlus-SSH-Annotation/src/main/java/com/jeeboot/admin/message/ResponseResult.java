package com.jeeboot.admin.message;

import java.io.Serializable;

public class ResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code = "";
	
	private String message = "";
	
	private Object data = "";
	
	public ResponseResult() {
		super();
	}

	public ResponseResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	public ResponseResult(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
