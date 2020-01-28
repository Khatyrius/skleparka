package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Payment;

public class PaymentTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		Payment payment = new Payment(1,"card",1,20,1,1, 1, "pending");
		
		//when
		int paymentId = 1;
		String paymentType = "card";
		int userId = 1;
		double amount = 20;
		int sellerId = 1;
		int billingId = 1;
		int orderId = 1;
		String status = "pending";
		
		//then
		assertTrue(payment.getPaymentId() == paymentId);
		assertTrue(payment.getPaymentType().equals(paymentType));
		assertTrue(payment.getUserId() == userId);
		assertTrue(payment.getAmount() == amount);
		assertTrue(payment.getSellerId() == sellerId);
		assertTrue(payment.getBillingId() == billingId);
		assertTrue(payment.getOrderId() == orderId);
		assertTrue(payment.getStatus().equals(status));
		
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		Payment payment = new Payment();
		int paymentId = 1;
		String paymentType = "card";
		int userId = 1;
		double amount = 20;
		int sellerId = 1;
		int billingId = 1;
		int orderId = 1;
		String status = "pending";
		
		//when
		payment.setPaymentId(paymentId);
		payment.setPaymentType(paymentType);
		payment.setUserId(userId);
		payment.setAmount(amount);
		payment.setSellerId(sellerId);
		payment.setBillingId(billingId);
		payment.setOrderId(orderId);
		payment.setStatus(status);
		
		//then
		assertTrue(payment.getPaymentId() == paymentId);
		assertTrue(payment.getPaymentType().equals(paymentType));
		assertTrue(payment.getUserId() == userId);
		assertTrue(payment.getAmount() == amount);
		assertTrue(payment.getSellerId() == sellerId);
		assertTrue(payment.getBillingId() == billingId);
		assertTrue(payment.getOrderId() == orderId);
		assertTrue(payment.getStatus().equals(status));
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		Payment payment = new Payment(1,"card",1,20,1,1,1, "pending");
		Payment otherPayment = new Payment(2,"paypal",2,20,1,1, 1, "cancelled");
		
		//when
		
		
		//then
		assertSame(payment,payment);
		assertEquals(payment,payment);
		assertNotEquals(payment, otherPayment);
		
		assertTrue(payment.hashCode() == payment.hashCode());
		assertFalse(payment.hashCode() == otherPayment.hashCode());
		
		assertTrue(payment.equals(payment));
		assertFalse(payment.equals(otherPayment));
		assertFalse(payment.equals(null));
		assertFalse(payment.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		Payment payment = new Payment();
		
		//when
		String text = "Payment [paymentId=0, paymentType=null, userId=0, amount=0.0, sellerId=0, billingId=0, orderId=0, status=null]";

		//then
		assertEquals(payment.toString(), text);
	}
}
