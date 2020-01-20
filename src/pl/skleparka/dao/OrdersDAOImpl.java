package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.Order;
import pl.skleparka.util.DBConnector;

public class OrdersDAOImpl implements OrdersDAO{

	@Override
	public void create(Order newOrder) {
		Connection con;		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO orders(user_id,order_date"
						+",order_status, total)"
						+" VALUES("
						+ newOrder.getUserId()+", "
						+"\'"+ newOrder.getOrderDate() + "\', "
						+"\'"+ newOrder.getStatus() + "\', "
						+ newOrder.getTotal() + ");";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to create new order");
			e.printStackTrace();
		
		}
	}

	@Override
	public Order read(Integer orderId) {
		Order order = new Order();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from orders WHERE order_id = " + orderId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				order.setOrderId(orderId);
				order.setUserId(rs.getInt("customer_id"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setStatus(rs.getString("order_status"));
				order.setTotal(rs.getDouble("total"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Order with ID: " + orderId + " couldn't be found");
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void update(Order updatedOrder) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE orders SET order_status = \'" + updatedOrder.getStatus() +"\'"
							+ " WHERE order_id = "+ updatedOrder.getOrderId() +";";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Update of order with id = " + updatedOrder.getOrderId() + "failed");
			e.printStackTrace();
		
		}
		
	}

	@Override
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from orders;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Order order = new Order();
					
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("customer_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setStatus(rs.getString("order_status"));
					order.setTotal(rs.getDouble("total"));
					
					orders.add(order);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all orders");
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> getAllOrdersOfUser(int userId) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from orders WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Order order = new Order();
					
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("customer_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setStatus(rs.getString("order_status"));
					order.setTotal(rs.getDouble("total"));
					
					orders.add(order);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all orders of user with id =" + userId);
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> getAllOrdersWithStatus(String status) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from orders WHERE order_status = \'" + status.toUpperCase() + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Order order = new Order();
					
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("customer_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setStatus(rs.getString("order_status"));
					order.setTotal(rs.getDouble("total"));
					
					orders.add(order);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all orders with status =" + status);
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> getAllOrdersWithDate(Date date) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from orders WHERE order_date = \'" + date + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Order order = new Order();
					
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("customer_id"));
					order.setOrderDate(rs.getDate("order_date"));
					order.setStatus(rs.getString("order_status"));
					order.setTotal(rs.getDouble("total"));
					
					orders.add(order);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all orders with date =" + date);
			e.printStackTrace();
		}
		return orders;
	}

}
