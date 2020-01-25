package pl.skleparka.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.skleparka.service.ProductService;

/**
 * Servlet implementation class AddProductController
 */
@WebServlet("/addProduct")
public class ProductAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String productName = request.getParameter("productName");
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		String type = request.getParameter("type");
		double price = Double.valueOf(request.getParameter("price"));
		String description = request.getParameter("description");
		String imageUrl = request.getParameter("imageUrl");
		
		ProductService.getInstance().addProduct(productName, quantity, type, price, description, imageUrl, 1);
		response.sendRedirect(request.getContextPath() + "/controlPanel?command=controlProducts");
	}

}
