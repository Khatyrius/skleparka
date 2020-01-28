package pl.skleparka.beansTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.Review;

public class ReviewTest {
	@Test
	public void shouldConstructorReturnExpectedValues() {
		//given
		Review review = new Review(1,1,1,"nice",1);
		
		//when
		int reviewId = 1;
		int userId = 1;
		int productId = 1;
		String description = "nice";
		int rating = 1;
		
		//then
		assertTrue(review.getReviewId() == reviewId);
		assertTrue(review.getUserId() == userId);
		assertTrue(review.getProductId() == productId);
		assertTrue(review.getDescription().equals(description));
		assertTrue(review.getRating() == rating);
	}
	
	@Test
	public void shouldGettersReturnTheRightValueFromSetters() {
		//given
		Review review = new Review();

		int reviewId = 1;
		int userId = 1;
		int productId = 1;
		String description = "nice";
		int rating = 1;
		String username = "tak";
		
		//when
		review.setReviewId(reviewId);
		review.setUserId(userId);
		review.setProductId(productId);
		review.setDescription(description);
		review.setRating(rating);
		review.setUsername(username);

		
		//then
		assertTrue(review.getReviewId() == reviewId);
		assertTrue(review.getUserId() == userId);
		assertTrue(review.getProductId() == productId);
		assertTrue(review.getDescription().equals(description));
		assertTrue(review.getRating() == rating);
		assertTrue(review.getUsername() == username);
	}
	
	@Test
	public void testHashCodeAndEquals() {
		//given
		Review review = new Review(1,1,1,"nice",1);
		Review otherReview = new Review(1,1,1,"nice",2);
		
		//when
		
		
		//then
		assertSame(review,review);
		assertEquals(review,review);
		assertNotEquals(review, otherReview);
		
		assertTrue(review.hashCode() == review.hashCode());
		assertFalse(review.hashCode() == otherReview.hashCode());
		
		assertTrue(review.equals(review));
		assertFalse(review.equals(otherReview));
		assertFalse(review.equals(null));
		assertFalse(review.equals(new BillingInfo()));
	}
	
	@Test
	public void testToString() {
		//given
		Review review = new Review();
		
		//when
		String text = "Rating [reviewId=0, userId=0, productId=0, description=null, rating=0]";

		//then
		assertEquals(review.toString(), text);
	}
}
