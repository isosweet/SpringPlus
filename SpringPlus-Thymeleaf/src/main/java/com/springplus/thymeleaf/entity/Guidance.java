package com.springplus.thymeleaf.entity;

import java.io.Serializable;

/**
 * 引导语
 * 
 * @author wangxuezheng
 *
 */
public class Guidance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/** 引导语 */
	private String message;
	
	/** 正在使用 0 正在使用 1 过期 */
	private Integer isUsing;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Integer isUsing) {
		this.isUsing = isUsing;
	}
	
}
