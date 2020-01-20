package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.Review;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.ReviewDAO;

public class ReviewService {
	private static ReviewService instance;
    
    private ReviewService(){}
    
    public static ReviewService getInstance(){
        if(instance == null){
            instance = new ReviewService();
        }
        return instance;
    }
    
	public void addReview(int userId,int productId, String description, int rating) {
		Review review = new Review();
		review.setUserId(userId);
		review.setProductId(productId);
		review.setDescription(description);
		
		if(rating < 1) {
			rating = 1;
		} else if( rating > 5) {
			rating = 5;
		} else 
		review.setRating(rating);
		
		GetDao().create(review);
	}
	
	public Review getReview(int reviewId) {
		return GetDao().read(reviewId);
	}
	
	public void deleteReview(int reviewId){
		GetDao().delete(reviewId);
	}
	
	public List<Review> getAllReviews(){
		return GetDao().getAll();
	}
	
	public List<Review> getAllReviewsForProduct(int productId){
		return GetDao().getAllForItem(productId);
	}
	
	public List<Review> getAllReviewsFromUser(int userId){
		return GetDao().getAllFromUser(userId);
	}

	private ReviewDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		ReviewDAO reviewDao = factory.getReviewDAO();
		return reviewDao;
	}
}
