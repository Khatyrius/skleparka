package pl.skleparka.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pl.skleparka.beans.Order;
import pl.skleparka.beans.Product;
import pl.skleparka.beans.Shipment;
import pl.skleparka.beans.User;
import pl.skleparka.service.OrderService;
import pl.skleparka.service.PaymentService;
import pl.skleparka.service.ProductService;
import pl.skleparka.service.ShipmentService;

/**
 * Servlet implementation class CheckoutContoller
 */
@WebServlet("/checkout")
public class CheckoutContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.valueOf(request.getParameter("productId"));
		Product product = ProductService.getInstance().getProduct(productId);
		HttpSession session = request.getSession();
		session.setAttribute("total", product.getPrice());
		session.setAttribute("product", product);
		request.getRequestDispatcher("checkout.jsp").forward(request,response);
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
		Product product = (Product)session.getAttribute("product");
		int productId = product.getProductId();
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
		
		/*if(simulatePayment()) {
			OrderService.getInstance().updateOrderStatus(orderId, "Paid");
			Shipment shipment = ShipmentService.getInstance().getShipmentByTrackingNumber(tracingNumber);
			ShipmentService.getInstance().updateShipmentWithId(shipment.getShipmentId(), "", "", "", "Send");
			sendMailToUser(user.getEmail());
		}*/
		
		session.setAttribute("orderId", orderId);
		session.setAttribute("trackingNumber", trackingNumber);
		
		int productQuantity = ProductService.getInstance().getProduct(productId).getQuantity();
		ProductService.getInstance().updateProduct(productId, "", productQuantity - 1, "", 0.0, "", "");
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
