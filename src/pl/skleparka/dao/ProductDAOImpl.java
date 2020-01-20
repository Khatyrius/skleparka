package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.Product;
import pl.skleparka.util.DBConnector;

public class ProductDAOImpl implements ProductDAO{

	@Override
	public void create(Product newProduct) {
		Connection con;
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO item(item_name,quantity"
						+",type, price, description,"
						+"image_url, seller_id)"
						+" VALUES("
						+"\'"+ newProduct.getItemName()+"\', "
						+ newProduct.getQuantity() + ", "
						+"\'"+ newProduct.getType() + "\', "
						+ newProduct.getPrice() + ", "
						+"\'"+ newProduct.getDescription() + "\', "
						+"\'"+ newProduct.getImageUrl() + "\', "
						+ newProduct.getSellerId() + ")";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con, null, st);
		}catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}

	@Override
	public Product read(Integer productId) {
		Product product = new Product();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item WHERE item_id = " + productId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				product.setProductId(productId);
				product.setItemName(rs.getString("item_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setType(rs.getString("type"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Product with ID: " + productId + " couldn't be found");
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void update(Product updatedProduct) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE item SET item_name = \'" + updatedProduct.getItemName() +"\'"
							+ ", quantity = "+ updatedProduct.getQuantity()
							+ ", type = \'" + updatedProduct.getType() + "\'"
							+ ", price = " + updatedProduct.getPrice()
							+ ", description = \'" + updatedProduct.getDescription() + "\'"
							+ ", image_url = \'" + updatedProduct.getImageUrl() + "\'"
							+ " WHERE item_id = "+ updatedProduct.getProductId() +";";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con, null, st);
		}catch (Exception e) {
			System.out.println("Update of product failed");
			e.printStackTrace();
		
		}
		
	}

	@Override
	public void delete(Integer productId) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "DELETE FROM item WHERE item_id = " + productId + ";";
			st = con.createStatement();
			st.executeUpdate(query);
			DBConnector.close(con, null, st);
		}catch (Exception e) {
			System.out.println("Failed to delete product with ID: " + productId);
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getAll() {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {

				Product product = new Product();
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setType(rs.getString("type"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
				
				products.add(product);
			}
				DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all products");
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> getProductsBySellerId(int sellerId) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item WHERE seller_id = \'" + sellerId + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);		
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setType(rs.getString("type"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(sellerId);
				
				products.add(product);
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Prodcuts with seller id: " + sellerId + " couldn't be found");
			e.printStackTrace();
		}
		return products;
	}
	
	@Override
	public Product getProductByItemName(String itemName) {
		Product product = new Product();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item WHERE item_name = \'" + itemName + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);		
			if (rs.next()) {
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(itemName);
				product.setType(rs.getString("type"));
				product.setQuantity(rs.getInt("quantity"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Prodcut with name: " + itemName + " couldn't be found");
			e.printStackTrace();
		}
		return product;
	}
	
	@Override
	public List<Product> getProductsByType(String type) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item WHERE type = \'" +type + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setType(rs.getString("type"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
				
				products.add(product);
				}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all products with type " + type);
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> getProductsByPrice(double price) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item WHERE price = " + price + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setType(rs.getString("type"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
				
				products.add(product);
				}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all products that have price equal to " + price);
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Product> getProductsBySearch(String searchPhrase) {
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from item where item_name like '%" + searchPhrase + "%';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("item_id"));
				product.setItemName(rs.getString("item_name"));
				product.setQuantity(rs.getInt("quantity"));
				product.setType(rs.getString("type"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setImageUrl(rs.getString("image_url"));
				product.setSellerId(rs.getInt("seller_id"));
				
				products.add(product);
				}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with products with name  " + searchPhrase);
			e.printStackTrace();
		}
		return products;
	}

}
