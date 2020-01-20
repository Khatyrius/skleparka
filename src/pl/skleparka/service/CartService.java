package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.Product;
import pl.skleparka.dao.CartDAO;
import pl.skleparka.dao.DAOFactory;

public class CartService {

	public List<Product> getProductFromCartOfUser(int userId){
		return GetDao().getCartProducts(userId);
	}
	
	public List<Product> getDistinctProductsFromCartOfUser(int userId){
		return GetDao().getDistinctCartProducts(userId);
	}
	
	public void addItemToUserCart(int itemId, int userId) {
		GetDao().addItemToUserCart(itemId, userId);
	}
	
	public void deleteItemFromUserCart(int itemId, int userId) {
		GetDao().deleteItemFromUserCart(itemId, userId);
	}
	
	public void deleteAllItemsFromUserCart(int userId) {
		GetDao().deleteAllFromCart(userId);
	}
	
	private CartDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		CartDAO cartDao = factory.getCartDAO();
		return cartDao;
	}
}
