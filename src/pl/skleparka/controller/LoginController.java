package pl.skleparka.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.beans.User;
import pl.skleparka.service.BillingInfoService;
import pl.skleparka.service.UserService;
import pl.skleparka.util.Authenticator;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		UserService us = new UserService();
		BillingInfoService billingInfoServie = new BillingInfoService();
		boolean result = Authenticator.authenticate(username, password);
		boolean isActive = us.getUserByUsername(username).isActive();
		HttpSession session = request.getSession();		
		
		if (result && isActive) {
			User user = us.getUserByUsername(username);
			BillingInfo userBillingInfo = billingInfoServie.getBillingInfoByUserId(user.getId());		
			System.out.println("Zalogowa³ siê u¿ytkownik: " + user);
			session.setAttribute("users", user);
			request.setAttribute("users", user);
			session.setAttribute("billingInfo", userBillingInfo);
	   		request.setAttribute("billingInfo", userBillingInfo);
	   		response.sendRedirect(request.getContextPath());
		} else if(isActive){
			session.invalidate();
			request.setAttribute("errorMessage", "Wprowadzono b³êdne dane logowania!");
			request.getRequestDispatcher("/login.jsp").forward(request, response); 
		} else if(us.getUserByUsername(username) != null && isActive == false){
			session.invalidate();
			request.setAttribute("errorMessage", "Taki u¿ytkownik nie istnieje lub jest zablokowany!");
			request.getRequestDispatcher("/login.jsp").forward(request, response); 
		}	
	}	
}