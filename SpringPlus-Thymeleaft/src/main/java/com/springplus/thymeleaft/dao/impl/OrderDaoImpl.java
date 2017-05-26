package com.springplus.thymeleaft.dao.impl;

import org.springframework.stereotype.Repository;

import com.springplus.thymeleaft.dao.OrderDao;

@Repository("orderDao")
public class OrderDaoImpl<Order> extends BaseDaoImpl<Order> implements OrderDao<Order> {

}
