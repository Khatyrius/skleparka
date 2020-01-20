package pl.skleparka.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.skleparka.beans.Shipment;
import pl.skleparka.util.DBConnector;

public class ShipmentDAOImpl implements ShipmentDAO{

	@Override
	public void create(Shipment newShipment) {
		Connection con;		
		try {
			con = DBConnector.getConnection();
			String query = "INSERT INTO shipment(order_id,customer_id"
						+",tracking_number, returnAddress, carrier,"
						+"charge, status)"
						+" VALUES("
						+"\'"+ newShipment.getOrderId()+"\',"
						+"\'"+ newShipment.getUserId() + "\',"
						+"\'"+ newShipment.getTrackingNumber() + "\',"
						+"\'"+ newShipment.getReturnAddress() + "\',"
						+"\'"+ newShipment.getCarrier() + "\',"
						+"\'"+ newShipment.getCharge() + "\',"
						+"\'"+ newShipment.getStatus() + "\');";
			
			Statement st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Failed to add new shipment");
			e.printStackTrace();
		
		}
	}

	@Override
	public Shipment read(Integer shipmentId) {
		Shipment shipment = new Shipment();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from shipment WHERE shipment_id = " + shipmentId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				shipment.setShipmentId(shipmentId);
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Shipment with ID: " + shipmentId + " couldn't be found");
			e.printStackTrace();
		}
		return shipment;
	}

	@Override
	public void update(Shipment updatedShipment) {
		Connection con;
		Statement st;
		try {
			con = DBConnector.getConnection();
			String query = "UPDATE shipment SET tracking_number = \'" + updatedShipment.getTrackingNumber() +"\'"
							+ ", returnAddress = \'"+ updatedShipment.getReturnAddress() +"\'"
							+ ", carrier = \'" + updatedShipment.getCarrier() +"\'" 
							+ ", status = \'" + updatedShipment.getStatus() +"\';";
			
			st = con.createStatement();
			st.executeUpdate(query);
			
			DBConnector.close(con,null,st);
		}catch (Exception e) {
			System.out.println("Update of shipment with id =" + updatedShipment.getShipmentId() + "failed");
			e.printStackTrace();
		
		}
	}

	@Override
	public void delete(Integer key) {
	}

	@Override
	public List<Shipment> getAll() {
		List<Shipment> shipments = new ArrayList<Shipment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from shipment;";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				Shipment shipment = new Shipment();
				
				shipment.setShipmentId(rs.getInt("shipment_id"));
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
				
				shipments.add(shipment);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all shipments");
			e.printStackTrace();
		}
		return shipments;
	}

	@Override
	public Shipment getShipmentByTackingNumber(String trackingNumber) {
		Shipment shipment = new Shipment();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query = "SELECT * from shipment WHERE tracking_number = " + trackingNumber + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			if (rs.next()) {
				shipment.setShipmentId(rs.getInt("shipment_id"));
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't get a shipment with tracking number = " + trackingNumber);
			e.printStackTrace();
		}
		return shipment;
	}

	@Override
	public List<Shipment> getAllShipmentWithStatus(String status) {
		List<Shipment> shipments = new ArrayList<Shipment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query =  "SELECT * from shipment WHERE status = " + status + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				Shipment shipment = new Shipment();
				
				shipment.setShipmentId(rs.getInt("shipment_id"));
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
				
				shipments.add(shipment);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all shipments with status = " + status);
			e.printStackTrace();
		}
		return shipments;
	}

	@Override
	public List<Shipment> getAllShipmentsWithUserId(int userId) {
		List<Shipment> shipments = new ArrayList<Shipment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query =  "SELECT * from shipment WHERE customer_id = " + userId + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				Shipment shipment = new Shipment();
				
				shipment.setShipmentId(rs.getInt("shipment_id"));
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
				
				shipments.add(shipment);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all shipments with user id = " + userId);
			e.printStackTrace();
		}
		return shipments;
	}

	@Override
	public List<Shipment> getAllShipmentWithCarrier(String carrier) {
		List<Shipment> shipments = new ArrayList<Shipment>();
		Connection con;
		ResultSet rs;
		Statement st = null;
		try {
			con = DBConnector.getConnection();
			String query =  "SELECT * from shipment WHERE carrier = " + carrier + ";";
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			
			while (rs.next()) {
				Shipment shipment = new Shipment();
				
				shipment.setShipmentId(rs.getInt("shipment_id"));
				shipment.setUserId(rs.getInt("customer_id"));
				shipment.setTrackingNumber(rs.getString("tracking_number"));
				shipment.setStatus(rs.getString("status"));
				shipment.setReturnAddress(rs.getString("returnAddress"));
				shipment.setOrderId(rs.getInt("order_id"));
				shipment.setCharge(rs.getFloat("charge"));
				shipment.setCarrier(rs.getString("carrier"));
				
				shipments.add(shipment);
			}
			
			DBConnector.close(con, rs, st);
		} catch (Exception e) {
			System.out.println("Couldn't make a list of all shipments with carrier = " + carrier);
			e.printStackTrace();
		}
		return shipments;
	}

}
