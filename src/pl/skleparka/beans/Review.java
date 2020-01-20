package pl.skleparka.beans;

public class Review {
	
	private int reviewId;
	private int userId;
	private int productId;
	private String description;
	private int rating;
	private String username;

public Review() {}

public Review(int reviewId, int userId, int productId, String description, int rating) {
	super();
	this.reviewId = reviewId;
	this.userId = userId;
	this.productId = productId;
	this.description = description;
	this.rating = rating;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public int getReviewId() {
	return reviewId;
}

public void setReviewId(int reviewId) {
	this.reviewId = reviewId;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + productId;
	result = prime * result + rating;
	result = prime * result + reviewId;
	result = prime * result + userId;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Review other = (Review) obj;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (productId != other.productId)
		return false;
	if (rating != other.rating)
		return false;
	if (reviewId != other.reviewId)
		return false;
	if (userId != other.userId)
		return false;
	return true;
}

@Override
public String toString() {
	return "Rating [reviewId=" + reviewId + ", userId=" + userId + ", productId=" + productId + ", description="
			+ description + ", rating=" + rating + "]";
}



}
