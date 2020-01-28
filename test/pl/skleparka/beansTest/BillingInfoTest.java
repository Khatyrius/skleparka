package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Cart;

public class BillingInfoTest {
	
	@Test
	public void testThatCreateBillingInfoWithDefaultConstructorIsNotNull() {
		//given
		
		
		//when
		BillingInfo billingInfo = new BillingInfo();
		
		//then
		assertNotNull(billingInfo);
	}
	
	@Test
	public void testThatBillingInfoWithConstructorDataHasCorrectData() {
		//given
		int billingInfoId = 1;
		int userId = 1;
		String cardNumber = "123";
		Date expirationDate = Date.valueOf(LocalDate.now());
		int securityCode = 123;
		String billingAddress = "Test";
		
		//when
		BillingInfo billingInfo = new BillingInfo(billingInfoId, userId, cardNumber, expirationDate, securityCode, billingAddress);
		
		//then
		assertEquals(billingInfoId, billingInfo.getBillingInfoId());
		assertEquals(userId, billingInfo.getUserId());
		assertEquals(cardNumber, billingInfo.getCardNumber());
		assertEquals(expirationDate, billingInfo.getExpirationDate());
		assertEquals(securityCode, billingInfo.getSecurityCode());
		assertEquals(billingAddress, billingInfo.getBillingAddress());
	}
	
	@Test
	public void testThatSettersReturnTheExpectedValue() {
		//given
		BillingInfo billingInfo = new BillingInfo();
		
		//when
		billingInfo.setBillingInfoId(1);
		billingInfo.setUserId(1);
		billingInfo.setCardNumber("123");
		billingInfo.setExpirationDate(Date.valueOf(LocalDate.now()));
		billingInfo.setSecurityCode(123);
		billingInfo.setBillingAddress("Miasto");
		
		//then
		assertEquals(1, billingInfo.getBillingInfoId());
		assertEquals(1, billingInfo.getUserId());
		assertEquals("123", billingInfo.getCardNumber());
		assertEquals(Date.valueOf(LocalDate.now()), billingInfo.getExpirationDate());
		assertEquals(123, billingInfo.getSecurityCode());
		assertEquals("Miasto", billingInfo.getBillingAddress());
	}
	
	@Test
	public void shouldHashCodeAndEqualsWorkAsIntended() {
		//given
		int billingInfoId = 1;
		int userId = 1;
		String cardNumber = "123";
		Date expirationDate = Date.valueOf(LocalDate.now());
		int securityCode = 123;
		String billingAddress = "Test";
		
		//when
		BillingInfo billingInfo = new BillingInfo(billingInfoId, userId, cardNumber, expirationDate, securityCode, billingAddress);
		BillingInfo identicalBillingInfo = new BillingInfo(billingInfoId, userId, cardNumber, expirationDate, securityCode, billingAddress);
		BillingInfo otherBillingInfo = new BillingInfo(2,2,"321",Date.valueOf(LocalDate.MIN),123,"Test");
		BillingInfo nullInfo = new BillingInfo();
		//then
		assertEquals(billingInfo, identicalBillingInfo);
		assertNotEquals(billingInfo, otherBillingInfo);
		
		assertFalse(billingInfo.equals(new Cart()));
		assertTrue(billingInfo.equals(billingInfo));
		assertTrue(billingInfo.equals(identicalBillingInfo));
		assertFalse(billingInfo.equals(otherBillingInfo));
		assertFalse(nullInfo.equals(billingInfo));
		assertTrue(billingInfo.hashCode() == identicalBillingInfo.hashCode());
		assertFalse(billingInfo.hashCode() == otherBillingInfo.hashCode());
		assertFalse(billingInfo.equals(null));
	}

	@Test
	public void testToString() {
		//given
		BillingInfo billingInfo = new BillingInfo();

		//when
		String text = "BillingInfo [billingInfoId=0, userId=0, cardNumber=null, expirationDate=null, securityCode=0, billingAddress=null]";
		
		//then
		assertTrue(billingInfo.toString().equals(text));
	}
}
