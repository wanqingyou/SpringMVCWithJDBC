package com.spring.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.DAO;
import com.spring.model.Customers;
import com.spring.model.Orders;

/**
 * Service implementation
 * @author WYou
 * @since 16.5.0
 */
@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private DAO appDao;

	public DAO getAppDao() {
		return appDao;
	}

	public void setAppDao(DAO appDao) {
		this.appDao = appDao;
	}

	@Override
	public List<Customers> getCustomerByOrderDate(Date orderDate) {
		return appDao.getCustomerByOrderDate(orderDate);
	}

	@Override
	public List<Orders> getOrdersBelongTo(String custName) {
		return appDao.getOrdersBelongTo(custName);
	}

	@Override
	public int updateOrderAmount(int orderId, double newAmount) {
		return appDao.updateOrderAmount(orderId, newAmount);
	}

}
