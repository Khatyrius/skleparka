package pl.skleparka.controller;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import pl.skleparka.beans.Product;
import pl.skleparka.beans.User;
import pl.skleparka.service.CartService;
import pl.skleparka.service.ProductService;
 
@WebListener
public class SessionController implements HttpSessionListener  {
 
	 @Override
	  public void sessionCreated(HttpSessionEvent se) {
	      HttpSession session = se.getSession();
	      session.setMaxInactiveInterval(900);
	  }

	  @Override
	  public void sessionDestroyed(HttpSessionEvent se) {
		  HttpSession session = se.getSession();
		  User user = (User)session.getAttribute("users");
		  if(user != null) {
	      System.out.println("Session of user " + user.getUsername() + " has ended. User has been logged out");
	      CartService cartService = CartService.getInstance();
	      ProductService productService = ProductService.getInstance();
	      
			@SuppressWarnings("unchecked")
			List<Product> products = ((List<Product>)session.getAttribute("cart"));
			
			for(Product product: products) {		
				cartService.deleteItemFromUserCart(product.getProductId(), user.getId());
				productService.updateProduct(product.getProductId(),product.getProductName(), productService.getProduct(product.getProductId()).getQuantity() + product.getQuantity(), "", 0.0, "", "");
			}
			
			System.out.println("Removed producuts from cart of user " + user.getUsername());
		  }
	  }
}