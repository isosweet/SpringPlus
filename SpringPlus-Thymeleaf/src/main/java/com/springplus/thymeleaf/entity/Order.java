package com.springplus.thymeleaf.entity;

import java.io.Serializable;

/**
 * Model class of 订单.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class Order implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	private String id;

	/** 用户id. */
	private String userId;

	/** 商品id. */
	private String productId;

	/** 数量. */
	private Integer quantity;

	/** 送货地址. */
	private String addressId;

	/**
	 * Constructor.
	 */
	public Order() {
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
	 * Set the 用户id.
	 * 
	 * @param userId
	 *            用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Get the 用户id.
	 * 
	 * @return 用户id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Set the 商品id.
	 * 
	 * @param productId
	 *            商品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Get the 商品id.
	 * 
	 * @return 商品id
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * Set the 数量.
	 * 
	 * @param quantity
	 *            数量
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the 数量.
	 * 
	 * @return 数量
	 */
	public Integer getQuantity() {
		return this.quantity;
	}

	/**
	 * Set the 送货地址.
	 * 
	 * @param addressId
	 *            送货地址
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * Get the 送货地址.
	 * 
	 * @return 送货地址
	 */
	public String getAddressId() {
		return this.addressId;
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
		Order other = (Order) obj;
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