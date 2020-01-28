package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.Payment;
import pl.skleparka.util.DBConnector;

public class PaymentDAOImpl implements PaymentDAO{

	@Override
	public void create(Payment newPayment) {
		Connection con;		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO payment(payment_type,customer_id"
						+",amount, billing_id, seller_id,"
						+"order_id,status)"
						+" VALUES("
						+"\'"+ newPayment.getPaymentType()+"\', "
						+ newPayment.getUserId() + ", "
						+ newPayment.getAmount() + ", "
						+ newPayment.getBillingId() + ", "
						+ newPayment.getSellerId() + ", "
						+ newPayment.getOrderId() + ", "
						+"\'"+ newPayment.getStatus()+"\');";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to add payment");
			e.printStackTrace();
		
		}
	}

	@Override
	public Payment read(Integer paymentId) {
		Payment payment = new Payment();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE payment_id = " + paymentId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				payment.setPaymentId(paymentId);
				payment.setUserId(rs.getInt("customer_id"));
				payment.setPaymentType(rs.getString("payment_type"));
				payment.setAmount(rs.getDouble("amount"));
				payment.setSellerId(rs.getInt("seller_id"));
				payment.setOrderId(rs.getInt("order_id"));
				payment.setBillingId(rs.getInt("billing_id"));
				payment.setStatus(rs.getString("status"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Payment with ID: " + paymentId + " couldn't be found");
			e.printStackTrace();
		}
		return payment;
	}

	@Override
	public void update(Payment updatedPayment) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE payment SET status = \'" + updatedPayment.getStatus() +"\'"
							+ " WHERE payment_id = "+ updatedPayment.getPaymentId() +";";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Update of payment failed");
			e.printStackTrace();
		
		}
	}

	@Override
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Payment> getAll() {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments");
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public Payment getPaymentForOrder(int orderId) {
		Payment payment = new Payment();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE order_id = " + orderId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			if(rs.next()) {
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
			}
				
				DBConnector.close(con, rs, st);
			}catch (Exception e) {
			System.out.println("Couldn't get a payment with order id =" + orderId);
			e.printStackTrace();
		}
		return payment;
	}

	@Override
	public List<Payment> getAllPaymentsOfUser(int userId) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE customer_id = "+ userId +";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments of user with id = " + userId );
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> getAllPaymentsWithLessThan(double amount) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE amount < "+ amount +";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments with amount less than " + amount );
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> getAllPaymentsWithMoreThan(double amount) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE amount > "+ amount +";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments with amount more than " + amount );
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> getAllPayemntsEqualTo(double amount) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE amount = "+ amount +";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments with amount equal to " + amount );
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public List<Payment> getAllPaymentsWithStatus(String status) {
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from payment WHERE status = \'"+ status.toUpperCase() +"\';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
					Payment payment = new Payment();
					payment.setPaymentId(rs.getInt("payment_id"));
					payment.setUserId(rs.getInt("customer_id"));
					payment.setPaymentType(rs.getString("payment_type"));
					payment.setAmount(rs.getDouble("amount"));
					payment.setSellerId(rs.getInt("seller_id"));
					payment.setOrderId(rs.getInt("order_id"));
					payment.setBillingId(rs.getInt("billing_id"));
					payment.setStatus(rs.getString("status"));
					payments.add(payment);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all payments with status " + status );
			e.printStackTrace();
		}
		return payments;
	}

}
