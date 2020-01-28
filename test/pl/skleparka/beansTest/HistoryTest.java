package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.History;

public class HistoryTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		History history = new History(1,1,1);
		
		//when
		int historyId = 1;
		int userId = 1;
		int orderId = 1;
		
		//then
		assertTrue(history.getHistoryId() == historyId);
		assertTrue(history.getUserId() == userId);
		assertTrue(history.getOrderId() == orderId);
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		History history = new History();
		
		//when
		history.setUserId(1);
		history.setHistoryId(1);
		history.setOrderId(1);
		
		//then
		assertTrue(history.getHistoryId() == 1);
		assertTrue(history.getUserId() == 1);
		assertTrue(history.getOrderId() == 1);
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		History history = new History(1,1,1);
		History otherHistory = new History(2,2,2);
		
		//when
		
		
		//then
		assertSame(history,history);
		assertEquals(history,history);
		assertNotEquals(history, otherHistory);
		
		assertTrue(history.hashCode() == history.hashCode());
		assertFalse(history.hashCode() == otherHistory.hashCode());
		
		assertTrue(history.equals(history));
		assertFalse(history.equals(otherHistory));
		assertFalse(history.equals(null));
		assertFalse(history.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		History history = new History();
		
		//when
		String text = "History [historyId=0, userId=0, orderId=0]";
		
		//then
		assertEquals(history.toString(), text);
	}
}
