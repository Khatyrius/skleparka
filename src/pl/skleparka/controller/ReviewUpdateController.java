package pl.skleparka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Review;
import pl.skleparka.service.ReviewService;

/**
 * Servlet implementation class UpdateReviewController
 */
@WebServlet("/updateReview")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int reviewId = Integer.valueOf(request.getParameter("reviewId"));
		Review review = ReviewService.getInstance().getReview(reviewId);
		session.setAttribute("updateReview", review);
		request.setAttribute("updateReview", review);
		request.getRequestDispatcher("updateReview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Review review = (Review) session.getAttribute("updateReview");
		int reviewId = review.getReviewId();
		String description = request.getParameter("description");
		int rating = Integer.valueOf(request.getParameter("rating"));
		int productId = review.getProductId();
		ReviewService.getInstance().updateReview(reviewId, description, rating);
		response.sendRedirect(request.getContextPath() + "/review?productId=" + productId);
		}

}
