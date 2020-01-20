package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.util.DBConnector;

public class BillingInfoDAOImpl implements BillingInfoDAO {

	@Override
	public void create(BillingInfo newBillingInfo) {
		Connection con;
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO billing_info(user_id,card_number"
						+",expiration_date, security_code, billing_address,)"
						+" VALUES("
						+ newBillingInfo.getUserId()+", "
						+"\'"+ newBillingInfo.getCardNumber() + "\', "
						+"\'"+ newBillingInfo.getExpirationDate() + "\', "
						+ newBillingInfo.getSecurityCode() + ", "
						+"\'"+ newBillingInfo.getBillingAddress() + "\')";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}	
	}

	@Override
	public BillingInfo read(Integer billingId) {
		BillingInfo billingInfo = new BillingInfo();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from billing_info WHERE billing_id = " + billingId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				billingInfo.setBillingInfoId(billingId);
				billingInfo.setUserId(rs.getInt("user_id"));
				billingInfo.setCardNumber(rs.getString("card_number"));
				billingInfo.setExpirationDate(rs.getDate("expiration_date"));
				billingInfo.setSecurityCode(rs.getInt("security_code"));
				billingInfo.setBillingAddress(rs.getString("billing_address"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("BillingInfo with ID: " + billingId + " couldn't be found");
			e.printStackTrace();
		}
		return billingInfo;
	}

	@Override
	public void update(BillingInfo updatedBillingInfo) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE billing_info SET card_number = \'" + updatedBillingInfo.getCardNumber() +"\'"
							+ ", expiration_date = \'"+ updatedBillingInfo.getExpirationDate() +"\'"
							+ ", security_code = " + updatedBillingInfo.getSecurityCode()
							+ ", billing_address = \'" + updatedBillingInfo.getBillingAddress()+"\'"
							+ " WHERE billing_id = "+ updatedBillingInfo.getBillingInfoId() +";";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Update of billing info failed");
			e.printStackTrace();
		
		}
		
	}

	@Override
	public void delete(Integer billingInfoId) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "DELETE FROM billing_info WHERE billing_id = " + billingInfoId + ";";
			st = con.createStatement();
			st.executeUpdate(query);
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to delete billing info with ID: " + billingInfoId);
			e.printStackTrace();
		}	
	}

	@Override
	public List<BillingInfo> getAll() {
		ArrayList<BillingInfo> billingInfos = new ArrayList<BillingInfo>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from billing_info;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				BillingInfo billingInfo = new BillingInfo();
				billingInfo.setBillingInfoId(rs.getInt("billing_id"));
				billingInfo.setUserId(rs.getInt("user_id"));
				billingInfo.setCardNumber(rs.getString("card_number"));
				billingInfo.setExpirationDate(rs.getDate("expiration_date"));
				billingInfo.setSecurityCode(rs.getInt("security_code"));
				billingInfo.setBillingAddress(rs.getString("billing_address"));
				
				billingInfos.add(billingInfo);
			}
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list with all billing infos");
			e.printStackTrace();
		}
		return billingInfos;
	}

	@Override
	public BillingInfo getByUserId(int userId) {
		BillingInfo billingInfo = new BillingInfo();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from billing_info WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				billingInfo.setBillingInfoId(rs.getInt("billing_id"));
				billingInfo.setUserId(userId);
				billingInfo.setCardNumber(rs.getString("card_number"));
				billingInfo.setExpirationDate(rs.getDate("expiration_date"));
				billingInfo.setSecurityCode(rs.getInt("security_code"));
				billingInfo.setBillingAddress(rs.getString("billing_address"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("BillingInfo with user ID: " + userId + " couldn't be found");
			e.printStackTrace();
		}
		return billingInfo;
	}

}
