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
import pl.skleparka.beans.User;
import pl.skleparka.service.OrderService;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
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
    	request.setCharacterEncoding("UTF-8");
    	String command = request.getParameter("command");
		if(command == null) command = "userProfile";
		if(command.equals("orderHistory")) {
			getUserOrderHsitory(request, response);    
		}
		session.setAttribute("command", command);
   		request.getRequestDispatcher("profile.jsp").forward(request, response);

	}

   	private void getUserOrderHsitory(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		List<Order> orders = OrderService.getInstance().getAllOrdersOfUser(((User)session.getAttribute("users")).getId());
		request.setAttribute("orderHistory", orders);
		session.setAttribute("orderHistory", orders);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
