package com.springplus.thymeleaf.entity;

import java.io.Serializable;

import javax.xml.crypto.Data;

/**
 * 咨询
 * 
 * @author wangxuezheng
 *
 */
public class Advisory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/** 标题 */
	private String title;
	
	/** 摘要 */
	private String summary;
	
	/** 正文 */
	private String textContext;
	
	/** 图片路径 */
	private String imgPath;
	
	/** 创建时间 */
	private Data createTime;
	
	/** 是否显示 0 显示 1 不显示 */
	private Integer isShow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public Data getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Data createTime) {
		this.createTime = createTime;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
}
