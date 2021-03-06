package com.springplus.thymeleaf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class of 用户.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class User implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	private String id;

	/** 用户名. */
	private String userName;

	/** 密码. */
	private String password;

	/** 手机号码. */
	private String mobileNumber;

	/** 注册时间. */
	private Date registerTime;

	/**
	 * Constructor.
	 */
	public User() {
	}

	/**
	 * Set the id.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get the id.
	 * 
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Set the 用户名.
	 * 
	 * @param userName
	 *            用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the 用户名.
	 * 
	 * @return 用户名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Set the 密码.
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the 密码.
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Set the 手机号码.
	 * 
	 * @param mobileNumber
	 *            手机号码
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Get the 手机号码.
	 * 
	 * @return 手机号码
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Set the 注册时间.
	 * 
	 * @param registerTime
	 *            注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * Get the 注册时间.
	 * 
	 * @return 注册时间
	 */
	public Date getRegisterTime() {
		return this.registerTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
