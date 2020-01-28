package pl.skleparka.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Order;
import pl.skleparka.beans.Payment;
import pl.skleparka.beans.Product;
import pl.skleparka.beans.Shipment;
import pl.skleparka.beans.User;
import pl.skleparka.filters.ProductFilter;
import pl.skleparka.filters.UserFilter;
import pl.skleparka.service.OrderService;
import pl.skleparka.service.PaymentService;
import pl.skleparka.service.ProductService;
import pl.skleparka.service.ShipmentService;
import pl.skleparka.service.UserService;

/**
 * Servlet implementation class ControlPanelController
 */
@WebServlet("/controlPanel")
public class ControlPanelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = UserService.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlPanelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
		if(command == null) command = "controlUsers";
		switch(command) {
		case "controlUsers": controlUsers(request, response);
			break;
		case "controlProducts": controlProducts(request, response);
			break;
		case "controlOrders": controlOrders(request, response);
			break;
		case "controlShipments": controlShipments(request, response);
			break;
		case "controlPayments": controlPayments(request, response);
			break;
		case "filterUsers": filterUsers(request, response);
			break;
		case "filterProducts": filterProducts(request, response);
			break;
		default: controlUsers(request, response);
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("users");
		
		session.setAttribute("command", command);
		
		if(user.getType().equals("admin")) {
			request.getRequestDispatcher("controlPanel.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	private void controlUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = userService.getUserList();
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
   		System.out.println("Za³adowano liste u¿ytkowników przez u¿ytkownika " + ((User)session.getAttribute("users")).getUsername());
   		
	}

	private void controlProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = ProductService.getInstance();
		List<Product> products = productService.getAllProducts();
		
		HttpSession session = request.getSession();
		session.setAttribute("productsList", products);
   		request.setAttribute("productsList", products);
   		
   		System.out.println("Za³adowano liste produktów przez u¿ytkownika " + ((User)session.getAttribute("users")).getUsername());
	}

	private void controlOrders(HttpServletRequest request, HttpServletResponse response) {
		OrderService orderService = OrderService.getInstance();
		List<Order> orders = orderService.getAllOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orderList", orders);
   		request.setAttribute("orderList", orders);
   		System.out.println("Za³adowano liste zamówieñ przez u¿ytkownika " + ((User)session.getAttribute("users")).getUsername());
	}

	private void controlShipments(HttpServletRequest request, HttpServletResponse response) {
		ShipmentService shipmentService = ShipmentService.getInstance();
		List<Shipment> shipmnets = shipmentService.getAllShipments();
		HttpSession session = request.getSession();
		session.setAttribute("shipmentsList", shipmnets);
   		request.setAttribute("shipmentsList", shipmnets);
   		System.out.println("Za³adowano liste dostaw przez u¿ytkownika " + ((User)session.getAttribute("users")).getUsername());
	}

	private void controlPayments(HttpServletRequest request, HttpServletResponse response) {
		PaymentService paymentService = PaymentService.getInstance();
		List<Payment> payments = paymentService.getAllPayments();
		HttpSession session = request.getSession();
		session.setAttribute("paymentsList", payments);
   		request.setAttribute("paymentsList", payments);
   		System.out.println("Za³adowano liste p³atnoœci przez u¿ytkownika " + ((User)session.getAttribute("users")).getUsername());
	}

	private void filterUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPhrase = request.getParameter("search");
		String parameter = request.getParameter("parameter");
		UserFilter userFilter = new UserFilter();
		if(searchPhrase.isEmpty()) {
			controlUsers(request, response);
		} else {
		
		if(parameter == null) parameter = "username";
		switch(parameter) {
		case "id": userFilter.filterUsersById(request, response, searchPhrase);
			break;
		case "username": userFilter.filterUsersByUsername(request, response, searchPhrase);
			break;
		case "email": userFilter.filterUsersByEmail(request, response, searchPhrase);
			break;
		case "fristName": userFilter.filterUsersByFirstName(request, response, searchPhrase);
			break;
		case "lastName": userFilter.filterUsersByLastName(request, response, searchPhrase);
			break;
		case "active": userFilter.filterUsersByStatus(request, response, searchPhrase);
			break;
		default: userFilter.filterUsersByUsername(request, response, searchPhrase);
		}
		}
	}
	
	private void filterProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchPhrase = request.getParameter("search");
		String parameter = request.getParameter("parameter");
		ProductFilter productFilter = new ProductFilter();
		if(searchPhrase.isEmpty()) {
			controlProducts(request, response);
		} else {
		
		if(parameter == null) parameter = "name";
		switch(parameter) {
		case "id": productFilter.filterProductsById(request, response, searchPhrase);
			break;
		case "name": productFilter.filterProductsByName(request, response, searchPhrase);
			break;
		case "type": productFilter.filterProductsByType(request, response, searchPhrase);
			break;
		default: productFilter.filterProductsByName(request, response, searchPhrase);
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("action");
		int userId = Integer.valueOf(request.getParameter("userId"));
		if(command == null) command = "blockUser";
		switch(command) {
		case "blockUser": blockUser(request, response, userId);
			break;
		case "activateUser" : activateUser(request, response, userId);
			break;
		default: blockUser(request, response, userId);
		}
	}

	private void activateUser(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException {
		userService.unblockUser(userId);
		response.sendRedirect(request.getContextPath() + "/controlPanel");
	}

	private void blockUser(HttpServletRequest request, HttpServletResponse response, int userId) throws IOException {
		userService.blockUser(userId);
		response.sendRedirect(request.getContextPath() + "/controlPanel");
	}

}
