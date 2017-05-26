package com.springplus.thymeleaf.entity;

import java.io.Serializable;

/**
 * 宠物画廊
 * 
 * @author wangxuezheng
 *
 */
public class PetGallery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/** 宠物名称 */
	private String petName;
	
	/** 简介 */
	private String introduction;

	/** 图片路径 */
	private String imgPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
	
}
