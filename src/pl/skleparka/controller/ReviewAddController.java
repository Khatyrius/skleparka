package pl.skleparka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.User;
import pl.skleparka.service.ReviewService;

/**
 * Servlet implementation class AddReviewContoller
 */
@WebServlet("/addReview")
public class ReviewAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("productId", request.getParameter("productId"));
		request.getRequestDispatcher("addReview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String description = request.getParameter("description");
		request.setCharacterEncoding("UTF-8");
		int rating = Integer.valueOf(request.getParameter("rating"));
		int userId = ((User)session.getAttribute("users")).getId();
		int productId = Integer.valueOf(session.getAttribute("productId").toString());
		ReviewService.getInstance().addReview(userId, productId, description, rating);
		
		response.sendRedirect(request.getContextPath() + "/review?productId=" + productId);
	}

}
