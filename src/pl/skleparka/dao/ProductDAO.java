package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Product;

public interface ProductDAO extends GenericDAO<Product, Integer>{
List<Product> getProductsBySellerId(int sellerId);
List<Product> getProductsBySearch(String searchPhrase);
List<Product> getProductsByType(String type);
Product getProductByItemName(String itemName);
List<Product> getProductsByPrice(double price);
}
