package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.History;
import pl.skleparka.util.DBConnector;

public class HistoryDAOImpl implements HistoryDAO {

	@Override
	public void create(History newHistory) {
		Connection con;		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO history(user_id,order_id)"
						+" VALUES("
						+ newHistory.getUserId() + ", "
						+ newHistory.getOrderId() +");";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to add order to history");
			e.printStackTrace();
		
		}
		
	}

	@Override
	public History read(Integer historyId) {
		History history = new History();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from history WHERE history_id = " + historyId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				history.setHistoryId(historyId);
				history.setUserId(rs.getInt("user_id"));
				history.setOrderId(rs.getInt("order_id"));;
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("History with ID: " + historyId + " couldn't be found");
			e.printStackTrace();
		}
		return history;
	}

	@Override
	public void update(History updateObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<History> getAll() {
		List<History> entireHistory = new ArrayList<History>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from history;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				History history = new History();
				
				history.setHistoryId(rs.getInt("history_id"));
				history.setUserId(rs.getInt("user_id"));
				history.setOrderId(rs.getInt("order_id"));;
				
				entireHistory.add(history);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make entire history list ");
			e.printStackTrace();
		}
		return entireHistory;
	}

	@Override
	public List<History> getHistoryOfUser(int userId) {
		List<History> userHistory = new ArrayList<History>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from history WHERE user_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				History history = new History();
				
				history.setHistoryId(rs.getInt("history_id"));
				history.setUserId(rs.getInt("user_id"));
				history.setOrderId(rs.getInt("order_id"));;
				
				userHistory.add(history);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make user history list ");
			e.printStackTrace();
		}
		return userHistory;
	}

}
