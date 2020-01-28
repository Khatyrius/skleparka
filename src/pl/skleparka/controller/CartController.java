package pl.skleparka.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Product;
import pl.skleparka.beans.User;
import pl.skleparka.service.CartService;
import pl.skleparka.service.ProductService;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	CartService cartService = CartService.getInstance();
   	ProductService productService = ProductService.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
    	HttpSession session = request.getSession();
    	List<Product> products = new ArrayList<Product>();
    	int userId = ((User)session.getAttribute("users")).getId();
    	products = cartService.getProductFromCartOfUser(userId);
    	Map<Integer, Long> countForId = products.stream()
    	        .collect(Collectors.groupingBy(Product::getProductId, Collectors.counting()));
    	List<Product> distinctProducts = new ArrayList<Product>();
    	distinctProducts = cartService.getDistinctProductsFromCartOfUser(userId);
    	for(Product product : distinctProducts) {
    		product.setQuantity(Integer.valueOf(Long.toString(countForId.get(product.getProductId()))));
    	}
		session.setAttribute("cart", distinctProducts);
   		request.setAttribute("cart", distinctProducts);
   		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("action");
		request.setCharacterEncoding("UTF-8");
		int productId = Integer.valueOf(request.getParameter("productId"));
		if(command == null) command = "addToCart";
		switch(command) {
		case "addToCart": addToCart(request, response, productId);
			break;
		case "removeFromCart" : removeFromCart(request, response, productId);
			break;
		default: addToCart(request, response, productId);
		}
	}
		
		private void addToCart(HttpServletRequest request, HttpServletResponse response, int productId) throws ServletException, IOException {
			HttpSession session = request.getSession();
			int userId = ((User)session.getAttribute("users")).getId();
			if(productService.getProduct(productId).getQuantity() > 0) {
			cartService.addItemToUserCart(productId, userId);
			productService.updateProduct(productId,"", productService.getProduct(productId).getQuantity()-1, "", 0.0, "", "");
			}
			response.sendRedirect(request.getContextPath() + "/shop");
		}
		
		private void removeFromCart(HttpServletRequest request, HttpServletResponse response, int productId) throws ServletException, IOException {
			HttpSession session = request.getSession();
			int userId = ((User)session.getAttribute("users")).getId();
			
			int quantity = 0;
			@SuppressWarnings("unchecked")
			List<Product> products = ((List<Product>)session.getAttribute("cart"));
			for(Product product: products) {
				if(product.getProductId() == productId) {
					quantity = product.getQuantity();
				}
			}
			
			
			cartService.deleteItemFromUserCart(productId, userId);
			productService.updateProduct(productId,"", productService.getProduct(productId).getQuantity() + quantity, "", 0.0, "", "");
			response.sendRedirect(request.getContextPath() + "/cart");
		}

}
