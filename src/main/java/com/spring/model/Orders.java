package com.spring.model;

import java.util.Date;

/**
 * A POJO class represents the order entity.
 * @author WYou
 * @since 16.5.0
 */
public class Orders {
	private int orderID;
	private Date orderDate;
	private double amount;
	private Customers customer;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

}
