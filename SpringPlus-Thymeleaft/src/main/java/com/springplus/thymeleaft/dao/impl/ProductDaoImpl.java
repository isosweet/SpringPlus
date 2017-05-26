package com.springplus.thymeleaft.dao.impl;

import org.springframework.stereotype.Repository;

import com.springplus.thymeleaft.dao.ProductDao;
import com.springplus.thymeleaft.entity.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao<Product> {

}
