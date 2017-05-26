package com.springplus.thymeleaf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 宠物动态
 * 
 * @author wangxuezheng
 *
 */
public class PetDynamic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/** 正文 */
	private String textContext;
	
	/** 图片路径 */
	private String imgPath;
	
	/** 创建时间 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTextContext() {
		return textContext;
	}

	public void setTextContext(String textContext) {
		this.textContext = textContext;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
