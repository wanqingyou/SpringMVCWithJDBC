package com.spring.service;

import java.sql.Date;
import java.util.List;

import com.spring.model.Customers;
import com.spring.model.Orders;

/**
 * Define service interface.
 * @author WYou
 * @Since 16.5.0
 */
public interface AppService {
	public List<Customers> getCustomerByOrderDate(Date orderDate);

	public List<Orders> getOrdersBelongTo(String custName);

	public int updateOrderAmount(int orderId, double newAmount);

}
