package com.spring.dao;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.spring.model.Customers;
import com.spring.model.Orders;

/**
 * Defines DAO operations for the models.
 * @author WYou
 * @since 16.5.0
 */
public interface DAO {
	public void setDataSource(DataSource dataSource);

	public List<Customers> getCustomerByOrderDate(Date orderDate);

	public List<Orders> getOrdersBelongTo(String custName);

	public int updateOrderAmount(int orderId, double newAmount);

	public Customers getCustomer(int custID);
}
