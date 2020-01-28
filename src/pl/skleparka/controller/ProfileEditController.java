package pl.skleparka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.User;
import pl.skleparka.service.UserService;

/**
 * Servlet implementation class ProfileEditController
 */
@WebServlet("/editProfile")
public class ProfileEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("command", command);
		if(command.equals("editProfile")) {
		request.getRequestDispatcher("editProfile.jsp").forward(request, response);
		}else if(command.equals("changePassword")) {
		request.getRequestDispatcher("editPassword.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		session.setAttribute("command", command);
		int userId = ((User)session.getAttribute("users")).getId();
		switch(command) {
		case "changePassword": 
			String password = request.getParameter("password");
			String passwordConfirm = request.getParameter("passwordConfirm");
			changeUserPasword(password, passwordConfirm, userId);
			response.sendRedirect(request.getContextPath() + "/profile");
			break;
		case "editProfile" : 
			String firstName = request.getParameter("firstName");
			String middleName = request.getParameter("middleName");
			String lastName = request.getParameter("lastName");
			updateUserProfile(firstName, middleName,lastName , userId);
			session.setAttribute("users", UserService.getInstance().getUserById(userId));
			response.sendRedirect(request.getContextPath() + "/profile");
			break;
		default:
			System.out.println("Not valid option in profile edit");
		}
	}

	private void updateUserProfile(String firstName, String middleName, String lastName , int userId) {
		UserService.getInstance().updateUser(userId, "", firstName, middleName, lastName, true);
	}

	private void changeUserPasword(String password, String passwordConfirm, int userId) {		
		if(password.equals(passwordConfirm)) {
			UserService.getInstance().updateUser(userId, password, "", "", "", true);
		}
	}

}
