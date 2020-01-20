package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.Product;
import pl.skleparka.util.DBConnector;

public class CartDAOImpl implements CartDAO{

	@Override
	public List<Product> getCartProducts(int userId) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * FROM cart WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				int productId = rs.getInt("item_id");
				products.add(GetDao().read(productId));
			}
			
			DBConnector.close(con, rs, st);
		}catch (Exception e) {
			System.out.println("Couldn't find any products in cart of user " + userId);
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void addItemToUserCart(int itemId, int userId) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO cart(user_id,item_id) VALUES(" + userId + ", " + itemId +");"; 
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con, null, st);
		} catch (Exception e) {
			System.out.println("Couldn't add item into cart of user " + userId);
			e.printStackTrace();
		}
	}

	@Override
	public void deleteItemFromUserCart(int itemId, int userId) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DBConnector.getConnection();
			String query = "DELETE FROM cart WHERE user_id = " + userId + " and item_id = " + itemId +";"; 
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con, null, st);
		} catch (Exception e) {
			System.out.println("Couldn't delete product from cart of user " + userId);
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteAllFromCart(int userId) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = DBConnector.getConnection();
			String query = "DELETE FROM cart WHERE user_id = " + userId + ";"; 
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con, null, st);
		} catch (Exception e) {
			System.out.println("Couldn't delete all products from cart of user " + userId);
			e.printStackTrace();
		}
	}
	
	private ProductDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		return factory.getProductDAO();
	}

	@Override
	public List<Product> getDistinctCartProducts(int userId) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT DISTINCT * FROM cart WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				int productId = rs.getInt("item_id");
				products.add(GetDao().read(productId));
			}
			
			DBConnector.close(con, rs, st);
		}catch (Exception e) {
			System.out.println("Couldn't find any products in cart of user " + userId);
			e.printStackTrace();
		}
		return products;
	}
}
