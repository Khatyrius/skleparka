package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Product;

public class ProductTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		Product product = new Product(1,"drukarka",1,"3d",10,"dziala","image", 1);
		
		//when
		int productId = 1;
		String productName = "drukarka";
		int quantity = 1;
		String type = "3d";
		double price = 10;
		String description = "dziala";
		String imageUrl = "image";
		int sellerId = 1;
		
		//then
		assertTrue(product.getProductId() == productId);
		assertTrue(product.getProductName().equals(productName));
		assertTrue(product.getQuantity() == quantity);
		assertTrue(product.getType().equals(type));
		assertTrue(product.getPrice() == price);
		assertTrue(product.getDescription().equals(description));
		assertTrue(product.getImageUrl().equals(imageUrl));
		assertTrue(product.getSellerId() == sellerId);		
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		Product product = new Product();
		int productId = 1;
		String productName = "drukarka";
		int quantity = 1;
		String type = "3d";
		double price = 10;
		String description = "dziala";
		String imageUrl = "image";
		int sellerId = 1;
		
		//when
		product.setProductId(productId);
		product.setProductName(productName);
		product.setQuantity(quantity);
		product.setType(type);
		product.setPrice(price);
		product.setDescription(description);
		product.setImageUrl(imageUrl);
		product.setSellerId(sellerId);
		
		//then
		assertTrue(product.getProductId() == productId);
		assertTrue(product.getProductName().equals(productName));
		assertTrue(product.getQuantity() == quantity);
		assertTrue(product.getType().equals(type));
		assertTrue(product.getPrice() == price);
		assertTrue(product.getDescription().equals(description));
		assertTrue(product.getImageUrl().equals(imageUrl));
		assertTrue(product.getSellerId() == sellerId);	
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		Product product = new Product(1,"drukarka",1,"3d",10,"dziala","image", 1);
		Product otherProduct = new Product(2,"tez drukarka", 1, "3d", 10, "tez dziala", "img", 2);
		
		//when
		
		
		//then
		assertSame(product,product);
		assertEquals(product,product);
		assertNotEquals(product, otherProduct);
		
		assertTrue(product.hashCode() == product.hashCode());
		assertFalse(product.hashCode() == otherProduct.hashCode());
		
		assertTrue(product.equals(product));
		assertFalse(product.equals(otherProduct));
		assertFalse(product.equals(null));
		assertFalse(product.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		Product product = new Product();
		
		//when
		String text = "Product [productId=0, itemName=null, quantity=0, type=null, price=0.0, description=null, imageUrl=null, sellerId=0]";
		
		//then
		assertEquals(product.toString(), text);
	}
}
