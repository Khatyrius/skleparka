package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Product;

public interface CartDAO{
List<Product> getCartProducts(int userId);
void addItemToUserCart(int itemId, int userId);
void deleteItemFromUserCart(int itemId, int userId);
void deleteAllFromCart(int userId);
List<Product> getDistinctCartProducts(int userId);
}
