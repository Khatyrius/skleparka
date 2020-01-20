package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Shipment;

public interface ShipmentDAO extends GenericDAO<Shipment, Integer>{
Shipment getShipmentByTackingNumber(String trackingNumber);
List<Shipment> getAllShipmentWithStatus(String status);
List<Shipment> getAllShipmentsWithUserId(int userId);
List<Shipment> getAllShipmentWithCarrier(String carrier);
}
