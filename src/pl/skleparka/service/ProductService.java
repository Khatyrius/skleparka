package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.Product;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.ProductDAO;

public class ProductService {
	private static ProductService instance;
    
    private ProductService(){}
    
    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }
    
	public void addProduct(String itemName, int quantity, String type, double price, String description, String imageUrl, int sellerId) {
		Product product = new Product();
		product.setProductName(itemName);
		product.setQuantity(quantity);
		product.setType(type);
		product.setPrice(price);
		product.setDescription(description);
		product.setImageUrl(imageUrl);
		product.setSellerId(1);
		
		GetDao().create(product);
	}
	
	public Product getProduct(int productId) {
		return GetDao().read(productId);
	}
	
	public void updateProduct(int productId, String productName, int quantity, String type, double price, String description, String imageUrl) {
		Product product = getProduct(productId);
		
		if(!productName.isEmpty() && !productName.equals(product.getProductName())) {
			product.setProductName(productName);
		}
		
		if(quantity != product.getQuantity() && quantity >=0) {
			product.setQuantity(quantity);
		}
		
		if(!type.isEmpty() && !type.equals(product.getType())) {
			product.setType(type);
		}
		
		if(price != 0.0 && price != product.getPrice()) {
			product.setPrice(price);
		}
		
		if(!description.isEmpty() && !description.equals(product.getDescription())) {
			product.setDescription(description);
		}
		
		if(!imageUrl.isEmpty() && !imageUrl.equals(product.getImageUrl())) {
			product.setDescription(imageUrl);
		}
		
		GetDao().update(product);
	}
	
	public void deleteProduct(int productId) {
		GetDao().delete(productId);
	}
	
	public List<Product> getAllProducts(){
		return GetDao().getAll();
	}
	
	public List<Product> getAllProductsFromSeller(int sellerId){
		return GetDao().getProductsBySellerId(sellerId);
	}
	
	public Product getProductByName(String itemName) {
		return GetDao().getProductByItemName(itemName);
	}
	
	public List<Product> getAllProductsByType(String type){
		return GetDao().getProductsByType(type);
	}
	
	public List<Product> getAllProductsByPrice(double price){
		return GetDao().getProductsByPrice(price);
	}
	
	public List<Product> getAllProductsBySearch(String searchPhrase){
		return GetDao().getProductsByProductName(searchPhrase);
	}
	
	private ProductDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		ProductDAO productDao = factory.getProductDAO();
		return productDao;
	}
}
