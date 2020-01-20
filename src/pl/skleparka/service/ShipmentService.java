package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.Shipment;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.ShipmentDAO;


public class ShipmentService {
	private static ShipmentService instance;
    
    private ShipmentService(){}
    
    public static ShipmentService getInstance(){
        if(instance == null){
            instance = new ShipmentService();
        }
        return instance;
    }
    
	public void addShipment(int orderId, int userId, String trackingNumber, String returnAddress, String carrier, float charge, String status) {
		Shipment shipment = new Shipment();
		status = status.toUpperCase();
		
		shipment.setOrderId(orderId);
		shipment.setUserId(userId);
		shipment.setTrackingNumber(trackingNumber);
		shipment.setReturnAddress(returnAddress);
		shipment.setCarrier(carrier);
		shipment.setCharge(charge);
		shipment.setStatus(status);
		
		getDao().create(shipment);
	}
	
	public Shipment getShipmentWithId(int shipmentId) {
		return getDao().read(shipmentId);
	}
	
	public void updateShipmentWithId(int shipmentId, String trackingNumber, String returnAddress, String carrier, String status) {
		Shipment shipment = getDao().read(shipmentId);
		status = status.toUpperCase();
		
		if(!trackingNumber.isEmpty() && !trackingNumber.equals(shipment.getTrackingNumber())) {
		 shipment.setTrackingNumber(trackingNumber);
		}
		
		if(!returnAddress.isEmpty() && !returnAddress.equals(shipment.getReturnAddress())) {
		shipment.setReturnAddress(returnAddress);
		}

		if(!carrier.isEmpty() && !carrier.equals(shipment.getCarrier())) {
		shipment.setCarrier(carrier);
		}

		if(!status.isEmpty() && !status.equals(shipment.getStatus())) {
		shipment.setStatus(status);
		}
		
		getDao().update(shipment);
	}
	
	public List<Shipment> getAllShipments(){
		return getDao().getAll();
	}
	
	public Shipment getShipmentByTrackingNumber(String trackingNumber) {
		return getDao().getShipmentByTackingNumber(trackingNumber);
	}
	
	public List<Shipment> getAllShipmentsWithUserId(int userId){
		return getDao().getAllShipmentsWithUserId(userId);
	}
	
	public List<Shipment> getAllShipmentsWithStatus(String status){
		return getDao().getAllShipmentWithStatus(status);
	}
	
	public List<Shipment> getAllShipmentsWithCarrier(String carrier){
		return getDao().getAllShipmentWithCarrier(carrier);
	}

	private ShipmentDAO getDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		ShipmentDAO orderDao = factory.getShipmentDAO();
		return orderDao;
	}
}
