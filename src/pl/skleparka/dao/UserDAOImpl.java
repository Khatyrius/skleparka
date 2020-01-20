package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.User;
import pl.skleparka.util.DBConnector;

public class UserDAOImpl implements UserDAO {

	@Override
	public void create(User newUser) {
		Connection con;		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO users(username,user_email"
						+",user_password, user_firstname, user_middlename,"
						+"user_lastname)"
						+" VALUES("
						+"\'"+ newUser.getUsername()+"\',"
						+"\'"+ newUser.getEmail() + "\',"
						+"\'"+ newUser.getPassword() + "\',"
						+"\'"+ newUser.getFirstName() + "\',"
						+"\'"+ newUser.getMiddleName() + "\',"
						+"\'"+ newUser.getLastName() + "\');";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to add user");
			e.printStackTrace();
		
		}
}

	@Override
	public User read(Integer userId) {
		User user = new User();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from users WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				user.setId(userId);
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("user_email"));
				user.setPassword(rs.getString("user_password"));
				user.setFirstName(rs.getString("user_firstname"));
				user.setMiddleName(rs.getString("user_middlename"));
				user.setLastName(rs.getString("user_lastname"));
				user.setType(rs.getString("account_type"));
				user.setActive(rs.getBoolean("isActive"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("User with ID: " + userId + " couldn't be found");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(User updatedUser) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE users SET user_firstname = \'" + updatedUser.getFirstName() +"\'"
							+ ", user_middlename = \'"+ updatedUser.getMiddleName() +"\'"
							+ ", user_lastname = \'" + updatedUser.getLastName() +"\'" 
							+ ", user_password = \'" + updatedUser.getPassword() +"\'"
							+ ", isActive = " + updatedUser.isActive()
							+ " WHERE user_id = "+ updatedUser.getId() +";";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Update of user failed");
			e.printStackTrace();
		
		}
	}

	@Override
	public void delete(Integer userId) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "DELETE FROM users WHERE user_id = " + userId + ";";
			st = con.createStatement();
			st.executeUpdate(query);
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to delete user with ID: " + userId);
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from users;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				if(!rs.getString("account_type").equals("admin")) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("user_email"));
				user.setPassword(rs.getString("user_password"));
				user.setFirstName(rs.getString("user_firstname"));
				user.setMiddleName(rs.getString("user_middlename"));
				user.setLastName(rs.getString("user_lastname"));
				user.setType(rs.getString("account_type"));
				user.setActive(rs.getBoolean("isActive"));
				users.add(user);
				}
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all users");
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from users WHERE username = \'" + username + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);		
			if (rs.next()) {
				user.setId(rs.getInt("user_Id"));
				user.setUsername(username);
				user.setEmail(rs.getString("user_email"));
				user.setPassword(rs.getString("user_password"));
				user.setFirstName(rs.getString("user_firstname"));
				user.setMiddleName(rs.getString("user_middlename"));
				user.setLastName(rs.getString("user_lastname"));
				user.setType(rs.getString("account_type"));
				user.setActive(rs.getBoolean("isActive"));
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("User with username: " + username + " couldn't be found");
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public User getUserByMail(String mail) {
		User user = new User();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from users WHERE user_email = \'" + mail + "\';";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(mail);
				user.setPassword(rs.getString("user_password"));
				user.setFirstName(rs.getString("user_firstname"));
				user.setMiddleName(rs.getString("user_middlename"));
				user.setLastName(rs.getString("user_lastname"));
				user.setType(rs.getString("account_type"));
				user.setActive(rs.getBoolean("isActive"));
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("User with email: " + mail + " couldn't be found");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void blockUser(int userId) {
		Connection con  = null;
		Statement st = null;
		
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE users SET isActive = 0 WHERE user_id = " + userId + ";"; 
			st = con.createStatement();
			st.executeUpdate(query);
			DBConnector.close(con, null, st);
		} catch(Exception e) {
			System.out.println("Couldn't block user with ID: " + userId);
			e.printStackTrace();
		}
		
	}

	@Override
	public void unblockUser(int userId) {
		Connection con  = null;
		Statement st = null;
		
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE users SET isActive = 1 WHERE user_id = " + userId + ";"; 
			st = con.createStatement();
			st.executeUpdate(query);
			DBConnector.close(con, null, st);
		} catch(Exception e) {
			System.out.println("Couldn't unblock user with ID: " + userId);
			e.printStackTrace();
		}
	}

}
