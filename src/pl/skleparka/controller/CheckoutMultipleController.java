package pl.skleparka.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Order;
import pl.skleparka.beans.Product;
import pl.skleparka.beans.User;
import pl.skleparka.service.CartService;
import pl.skleparka.service.OrderService;
import pl.skleparka.service.PaymentService;
import pl.skleparka.service.ProductService;
import pl.skleparka.service.ShipmentService;

/**
 * Servlet implementation class CheckoutMultipleController
 */
@WebServlet("/checkoutMultiple")
public class CheckoutMultipleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutMultipleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Product> products = (List<Product>)session.getAttribute("cart");
		double total = 0;
		
		for(Product product : products) {
			total += (product.getPrice() * product.getQuantity());
		}
		
		session.setAttribute("total", total);
		request.getRequestDispatcher("checkoutMultiple.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String paymentType = request.getParameter("paymentType");
		String carrier = request.getParameter("carrier");
		double total = (double) session.getAttribute("total");
		List<Product> products = (List<Product>)session.getAttribute("cart");
		String address = request.getParameter("address");
		User user = (User)session.getAttribute("users");
		float charge = 0;
		
		switch(carrier){
		case "dhl":
			charge = 20;
			break;
		case "inPost":
			charge = 15;
			break;
		case "pigeon":
			charge = 100;
			break;
		}
		
		total += charge;		
		
		OrderService.getInstance().addNewOrder(user.getId(), "Waiting for payment", total);
		List<Order> orders = OrderService.getInstance().getAllOrdersOfUser(user.getId());
		int orderId = 0;
		
		for(Order order: orders) {
			if(order.getTotal() == total) {
				orderId = order.getOrderId();
			}
		}
		
		String trackingNumber = generateTrackingNumber();
		
		PaymentService.getInstance().addPayment(paymentType, user.getId(), total, 1, 1, orderId);
				
		ShipmentService.getInstance().addShipment(orderId, user.getId(),trackingNumber , address, carrier, charge, "Waiting for payment");
				
		session.setAttribute("orderId", orderId);
		session.setAttribute("trackingNumber", trackingNumber);
		
		for(Product product : products) {
			CartService.getInstance().deleteItemFromUserCart(product.getProductId(), user.getId());	
		}
		
		request.getRequestDispatcher("checkoutFinale.jsp").forward(request, response);
	}
	
	private String generateTrackingNumber() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 	    
	    return generatedString;
	}

}
