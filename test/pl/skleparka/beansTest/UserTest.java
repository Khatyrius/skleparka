package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.User;

public class UserTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		int id = 1;
		String email = "test@test.pl";
		String password = "secret";
		String firstName = "Bartosz";
		String lastName = "Musielak";
		String middleName = "";
		String type = "admin";
		String username = "admin";
		boolean isActive = true;
		
		
		//when
		User user = new User(id, email, password, firstName, lastName, middleName, type, username, isActive);
		
		//then
		assertEquals(id, user.getId());	
		assertEquals(email, user.getEmail());	
		assertEquals(password, user.getPassword());	
		assertEquals(firstName, user.getFirstName());	
		assertEquals(lastName, user.getLastName());	
		assertEquals(middleName, user.getMiddleName());	
		assertEquals(type, user.getType());	
		assertEquals(username, user.getUsername());	
		assertEquals(isActive, user.isActive());	
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		User user = new User();
		int id = 1;
		String email = "test@test.pl";
		String password = "secret";
		String firstName = "Bartosz";
		String lastName = "Musielak";
		String middleName = "";
		String type = "admin";
		String username = "admin";
		boolean isActive = true;
		
		//when
		user.setActive(isActive);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setId(id);
		user.setLastName(lastName);
		user.setMiddleName(middleName);
		user.setPassword(password);
		user.setType(type);
		user.setUsername(username);
		
		//then
		assertEquals(id, user.getId());	
		assertEquals(email, user.getEmail());	
		assertEquals(password, user.getPassword());	
		assertEquals(firstName, user.getFirstName());	
		assertEquals(lastName, user.getLastName());	
		assertEquals(middleName, user.getMiddleName());	
		assertEquals(type, user.getType());	
		assertEquals(username, user.getUsername());	
		assertEquals(isActive, user.isActive());
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		User user = new User(1,"test@test.pl","secret","Bartosz","Musielak","","admin","admin",true);
		User otherUser = new User(1,"test@test.pl","secret","Bartosz","Musielak","","admin","admin",false);
		
		//when

		
		//then
		assertSame(user,user);
		assertEquals(user,user);
		assertNotEquals(user, otherUser);
		
		assertTrue(user.hashCode() == user.hashCode());
		assertFalse(user.hashCode() == otherUser.hashCode());
		
		assertTrue(user.equals(user));
		assertFalse(user.equals(otherUser));
		assertFalse(user.equals(null));
		assertFalse(user.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		User user = new User();
		
		//when
		String text = "User [id=0, email=null, password=null, firstName=null, lastName=null, middleName=, type=null, username=null, isActive=false]";

		//then
		assertEquals(user.toString(), text);
	}
}
