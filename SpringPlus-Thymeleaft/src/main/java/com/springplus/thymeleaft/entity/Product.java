package com.springplus.thymeleaft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Model class of 商品.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table(name = "t_product")
public class Product implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id")
	private String id;

	/** 商品名称. */
	@Column(name ="product_name")
	private String productName;

	/** 商品价格. */
	@Column(name ="price")
	private Double price;

	/** 生产日期. */
	@Column(name ="production_date")
	private Date productionDate;

	/** 类别. */
	@Column(name ="category")
	private Integer category;

	/**
	 * Constructor.
	 */
	public Product() {
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
	 * Set the 商品名称.
	 * 
	 * @param productName
	 *            商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Get the 商品名称.
	 * 
	 * @return 商品名称
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * Set the 商品价格.
	 * 
	 * @param price
	 *            商品价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the 商品价格.
	 * 
	 * @return 商品价格
	 */
	public Double getPrice() {
		return this.price;
	}

	/**
	 * Set the 生产日期.
	 * 
	 * @param productionDate
	 *            生产日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * Get the 生产日期.
	 * 
	 * @return 生产日期
	 */
	public Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * Set the 类别.
	 * 
	 * @param category
	 *            类别
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * Get the 类别.
	 * 
	 * @return 类别
	 */
	public Integer getCategory() {
		return this.category;
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
		Product other = (Product) obj;
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
