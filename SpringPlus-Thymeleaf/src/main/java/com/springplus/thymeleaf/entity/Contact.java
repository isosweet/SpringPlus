package com.springplus.thymeleaf.entity;

import java.io.Serializable;

import javax.xml.crypto.Data;

/**
 * 联系我们
 * 
 * @author wangxuezheng
 *
 */
public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/** 姓名 */
	private String name;
	
	/** e-mail */
	private String email;
	
	/** 主题 */
	private String subject;
	
	/** 内容 */
	private String message;
	
	/** 创建时间 */
	private Data createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Data getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Data createTime) {
		this.createTime = createTime;
	}
}
