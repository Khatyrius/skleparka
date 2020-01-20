package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Review;

public interface ReviewDAO extends GenericDAO<Review, Integer>{
	List<Review> getAllForItem(int itemId);
	List<Review> getAllFromUser(int userId);
}
