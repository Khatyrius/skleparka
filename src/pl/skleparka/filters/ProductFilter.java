package pl.skleparka.filters;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Product;
import pl.skleparka.service.ProductService;

public class ProductFilter {
	ProductService productService = ProductService.getInstance();
	
	public void filterProductsById(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<Product> products = new ArrayList<Product>();
		HttpSession session = request.getSession();
		try {
			products.add(productService.getProduct(Integer.parseInt(searchPhrase)));
		}catch(NumberFormatException e) {
			System.out.println("Someone tried to filter ID with a word");
		}
		session.setAttribute("productsList", products);
   		request.setAttribute("prodcutsList", products);
	}
	
	public void filterProductsByName(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<Product> products = productService.getAllProductsBySearch(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("productsList", products);
   		request.setAttribute("productsList", products);
	}
	
	public void filterProductsByType(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<Product> products = productService.getAllProductsByType(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("productsList", products);
   		request.setAttribute("productsList", products);
	}
}
