package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Shipment;

public class ShipmentTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		int shipmentId = 1;
		int orderId = 1;
		int userId = 1;
		String trackingNumber = "CA23";
		String returnAddress = "Lubsko";
		String carrier = "DHL";
		float charge = 4.5f;
		String status = "EnRoute";
		
		
		//when
		Shipment shipment = new Shipment(shipmentId, orderId, userId, trackingNumber, returnAddress, carrier, charge, status);
		
		//then
		assertTrue(shipmentId == shipment.getShipmentId());
		assertTrue(orderId == shipment.getOrderId());
		assertTrue(userId == shipment.getUserId());
		assertTrue(trackingNumber.equals(shipment.getTrackingNumber()));
		assertTrue(returnAddress.equals(shipment.getReturnAddress()));
		assertTrue(carrier.equals(shipment.getCarrier()));
		assertTrue(charge == shipment.getCharge());
		assertTrue(status.equals(shipment.getStatus()));
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		Shipment shipment = new Shipment();

		int shipmentId = 1;
		int orderId = 1;
		int userId = 1;
		String trackingNumber = "CA23";
		String returnAddress = "Lubsko";
		String carrier = "DHL";
		float charge = 4.5f;
		String status = "EnRoute";
		
		//when
		shipment.setCarrier(carrier);
		shipment.setCharge(charge);
		shipment.setOrderId(orderId);
		shipment.setReturnAddress(returnAddress);
		shipment.setShipmentId(shipmentId);
		shipment.setStatus(status);
		shipment.setTrackingNumber(trackingNumber);
		shipment.setUserId(userId);

		
		//then
		assertTrue(shipmentId == shipment.getShipmentId());
		assertTrue(orderId == shipment.getOrderId());
		assertTrue(userId == shipment.getUserId());
		assertTrue(trackingNumber.equals(shipment.getTrackingNumber()));
		assertTrue(returnAddress.equals(shipment.getReturnAddress()));
		assertTrue(carrier.equals(shipment.getCarrier()));
		assertTrue(charge == shipment.getCharge());
		assertTrue(status.equals(shipment.getStatus()));
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		Shipment shipment = new Shipment(1,1,1,"CA23","Lubsko","DHL",4.5f,"EnRoute");
		Shipment otherShipment = new Shipment(1,1,1,"CA23","Lubsko","DHL",4.5f,"InWarehouse");
		
		//when
		
		
		//then
		assertSame(shipment,shipment);
		assertEquals(shipment,shipment);
		assertNotEquals(shipment, otherShipment);
		
		assertTrue(shipment.hashCode() == shipment.hashCode());
		assertFalse(shipment.hashCode() == otherShipment.hashCode());
		
		assertTrue(shipment.equals(shipment));
		assertFalse(shipment.equals(otherShipment));
		assertFalse(shipment.equals(null));
		assertFalse(shipment.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		Shipment shipment = new Shipment();
		
		//when
		String text = "Shipment [shipmentId=0, orderId=0, userId=0, trackingNumber=null, returnAddress=null, carrier=null, charge=0.0, status=null]";
		
		//then
		assertEquals(shipment.toString(), text);
	}
}
