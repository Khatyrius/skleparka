package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Order;

public class OrderTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		Order order = new Order(1,1,Date.valueOf(LocalDate.now()),"pending",10.00);
		
		//when
		int orderId = 1;
		int userId = 1;
		Date date = Date.valueOf(LocalDate.now());
		String status = "pending";
		double total = 10.00;
		
		//then
		assertTrue(order.getUserId() == userId);
		assertTrue(order.getOrderId() == orderId);
		assertTrue(order.getOrderDate().equals(date));
		assertTrue(order.getStatus().equals(status));
		assertTrue(order.getTotal() == total);
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		Order order = new Order();
		int orderId = 1;
		int userId = 1;
		Date date = Date.valueOf(LocalDate.now());
		String status = "pending";
		double total = 10.00;
		
		//when
		order.setOrderId(orderId);
		order.setUserId(userId);
		order.setOrderDate(date);
		order.setStatus(status);
		order.setTotal(total);
		
		//then
		assertTrue(order.getUserId() == userId);
		assertTrue(order.getOrderId() == orderId);
		assertTrue(order.getOrderDate() == date);
		assertTrue(order.getStatus().equals(status));
		assertTrue(order.getTotal() == total);
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		Order order = new Order(1,1,Date.valueOf(LocalDate.now()),"pending",10.00);
		Order otherOrder = new Order(2,2,Date.valueOf(LocalDate.now()),"cancelled",10.00);
		
		//when
		
		
		//then
		assertSame(order,order);
		assertEquals(order,order);
		assertNotEquals(order, otherOrder);
		
		assertTrue(order.hashCode() == order.hashCode());
		assertFalse(order.hashCode() == otherOrder.hashCode());
		
		assertTrue(order.equals(order));
		assertFalse(order.equals(otherOrder));
		assertFalse(order.equals(null));
		assertFalse(order.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		Order order = new Order();
		
		//when
		String text = "Order [orderId=0, userId=0, orderDate=null, status=null, total=0.0]";
		
		//then
		assertEquals(order.toString(), text);
	}
}
