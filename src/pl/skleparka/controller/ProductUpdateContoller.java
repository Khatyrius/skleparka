package pl.skleparka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Product;
import pl.skleparka.service.ProductService;

/**
 * Servlet implementation class ProductUpdateContoller
 */
@WebServlet("/updateProduct")
public class ProductUpdateContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		Product product = ProductService.getInstance().getProduct(Integer.valueOf(request.getParameter("productId")));
		session.setAttribute("updateProduct", product);
		request.setAttribute("updateProduct", product);
		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int productId = (((Product)session.getAttribute("updateProduct")).getProductId());
		String productName = request.getParameter("productName");
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		String type = request.getParameter("type");
		double price = Double.valueOf(request.getParameter("price"));
		String description = request.getParameter("description");
		String imageUrl = request.getParameter("imageUrl");
		
		ProductService.getInstance().updateProduct(productId, productName, quantity, type, price, description, imageUrl);
		response.sendRedirect(request.getContextPath() + "/controlPanel?command=controlProducts");
	}

}
