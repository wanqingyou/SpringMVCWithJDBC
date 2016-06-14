package com.spring.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Customers;
import com.spring.model.Orders;

/**
 * An implementation of the DAO interface.
 * @author WYou
 * @since 16.5.0
 */
@Repository
public class DAOImpl implements DAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Customers> getCustomerByOrderDate(Date orderDate) {
		String sql = "SELECT customers.customer_id, customers.name, customers.contact, customers.address FROM CUSTOMERS"
				+ ", ORDERS WHERE ORDERS.CUSTOMER_ID = customers.CUSTOMER_ID AND ORDERS.order_date = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, orderDate);
			List<Customers> customers = new ArrayList<Customers>();
			ResultSet rs = ps.executeQuery();
			System.out.println("Search... " + orderDate);
			while (rs.next()) {
				Customers cust = new Customers();
				cust.setCustomerID(rs.getInt("customer_id"));
				cust.setCustomerName(rs.getString("name"));
				cust.setContactInformation(rs.getString("contact"));
				cust.setAddress(rs.getString("address"));
				customers.add(cust);
			}
			rs.close();
			ps.close();
			return customers;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {

				}
			}
		}
	}

	@Override
	public List<Orders> getOrdersBelongTo(String custName) {
		String sql = "SELECT ORDERS.order_id, ORDERS.order_date, ORDERS.amount, Customers.CUSTOMER_ID FROM CUSTOMERS"
				+ ", ORDERS WHERE ORDERS.CUSTOMER_ID = customers.CUSTOMER_ID AND LOWER(CUSTOMERS.NAME) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + custName.toLowerCase() + "%");
			System.out.println("Search... " + custName);
			List<Orders> orders = new ArrayList<Orders>();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customers cust = getCustomer(rs.getInt("CUSTOMER_ID"));
				Orders order = new Orders();
				order.setOrderID(rs.getInt("order_id"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setAmount(rs.getDouble("amount"));
				order.setCustomer(cust);
				orders.add(order);
			}
			rs.close();
			ps.close();
			return orders;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {

				}
			}
		}
	}

	@Override
	public int updateOrderAmount(int orderId, double newAmount) {
		String sql = "UPDATE order SET amount = ? WHERE order_id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, newAmount);
			ps.setInt(2, orderId);

			int rs = ps.executeUpdate();

			ps.close();
			return rs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {

				}
			}
		}
	}

	@Override
	public Customers getCustomer(int custID) {
		String sql = "SELECT * FROM CUSTOMERS" + " WHERE CUSTOMER_ID = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custID);
			System.out.println("Search... " + custID);
			Customers cust = new Customers();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cust.setCustomerID(custID);
				cust.setCustomerName(rs.getString("name"));
				cust.setContactInformation(rs.getString("contact"));
				cust.setAddress(rs.getString("address"));
			}
			rs.close();
			ps.close();
			return cust;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {

				}
			}
		}
	}

}
