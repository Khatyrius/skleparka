package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Product;

public interface ProductDAO extends GenericDAO<Product, Integer>{
Product getProductByItemName(String itemName);
List<Product> getProductsBySellerId(int sellerId);
List<Product> getProductsByProductName(String searchPhrase);
List<Product> getProductsByType(String type);
List<Product> getProductsByPrice(double price);
}
