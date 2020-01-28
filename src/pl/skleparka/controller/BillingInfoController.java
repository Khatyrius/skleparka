package pl.skleparka.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.User;
import pl.skleparka.service.BillingInfoService;
import pl.skleparka.service.UserService;

/**
 * Servlet implementation class BillingInfoController
 */
@WebServlet("/billingInfo")
public class BillingInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		HttpSession session = request.getSession();
		session.setAttribute("command", command);
		if(command.equals("updateBillingInfo")) {
		request.getRequestDispatcher("updateBillingInfo.jsp").forward(request, response);
		}else if(command.equals("addBillingInfo")) {
		request.getRequestDispatcher("addBillingInfo.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		HttpSession session = request.getSession();
		session.setAttribute("command", command);
		int userId = ((User)session.getAttribute("users")).getId();
		String cardNumber;
		int securityCode;
		String billingAddress;
		Date expirationDate;
		switch(command) {
		case "addBillingInfo": 
			cardNumber = request.getParameter("cardNumber");
			expirationDate = Date.valueOf(request.getParameter("expirationDate"));
			securityCode = Integer.valueOf(request.getParameter("securityCode"));
			billingAddress = request.getParameter("billingAddress");
			createBillingInfo(cardNumber, expirationDate,securityCode ,billingAddress, userId);
			session.setAttribute("billingInfo", BillingInfoService.getInstance().getBillingInfoByUserId(userId));
			response.sendRedirect(request.getContextPath() + "/profile");
			break;
		case "updateBillingInfo" : 
			cardNumber = request.getParameter("cardNumber");
			expirationDate = Date.valueOf(request.getParameter("expirationDate"));
			securityCode = Integer.valueOf(request.getParameter("securityCode"));
			billingAddress = request.getParameter("billingAddress");
			updateBillingInfo(cardNumber, expirationDate,securityCode ,billingAddress, userId);
			session.setAttribute("users", UserService.getInstance().getUserById(userId));
			session.setAttribute("billingInfo", BillingInfoService.getInstance().getBillingInfoByUserId(userId));
			response.sendRedirect(request.getContextPath() + "/profile");
			break;
		default:
			System.out.println("Not valid option in billing info edit");
		}
	}

	private void updateBillingInfo(String cardNumber, Date expirationDate, int securityCode, String billingAddress,
			int userId) {
		int billingInfoId =BillingInfoService.getInstance().getBillingInfoByUserId(userId).getBillingInfoId();
		BillingInfoService.getInstance().updateBillingInfo(billingInfoId, cardNumber, expirationDate, securityCode, billingAddress);
	}

	private void createBillingInfo(String cardNumber, Date expirationDate, int securityCode, String billingAddress,
			int userId) {
		BillingInfoService.getInstance().addBillingInfo(userId, cardNumber, expirationDate, securityCode, billingAddress);
	}

}
