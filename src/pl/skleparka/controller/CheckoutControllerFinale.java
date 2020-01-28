package pl.skleparka.controller;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.Shipment;
import pl.skleparka.beans.User;
import pl.skleparka.service.OrderService;
import pl.skleparka.service.PaymentService;
import pl.skleparka.service.ShipmentService;

/**
 * Servlet implementation class CheckoutControllerFinale
 */
@WebServlet("/checkoutFinale")
public class CheckoutControllerFinale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int orderId;
	private String trackingNumber;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutControllerFinale() {
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
		User user = (User)session.getAttribute("users");
		orderId = (int)session.getAttribute("orderId");
		trackingNumber = (String) session.getAttribute("trackingNumber");
		String command = request.getParameter("command");
		if(command!=null) {
		session.setAttribute("command", command);
		
		if(command.equals("simulate")) {
		
		OrderService.getInstance().updateOrderStatus(orderId, "Paid");
		Shipment shipment = ShipmentService.getInstance().getShipmentByTrackingNumber(trackingNumber);
		ShipmentService.getInstance().updateShipmentWithId(shipment.getShipmentId(), "", "", "", "Send");
		int paymentId = PaymentService.getInstance().getPaymentForOrder(orderId).getPaymentId();
		PaymentService.getInstance().updatePayment(paymentId, "Completed");
		sendMailToUser(user.getEmail());
		response.sendRedirect(request.getContextPath() + "/home");
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void sendMailToUser(String email) { 
		// change accordingly 
		String to = email; 
		
		// change accordingly 
		String from = "skleparka1@wp.pl"; 
		
		// or IP address 
		String host = "smtp.wp.pl"; 
		
		// mail id 
		final String username = "skleparka1@wp.pl";
		
		// correct password for gmail id 
		final String password = "SokolWLesie"; 

		System.out.println("TLSEmail Start"); 
		// Get the session object 
		
		// Get system properties 
		Properties properties = System.getProperties(); 
		
		// Setup mail server 
		properties.setProperty("mail.smtp.host", host); 
		
		// SSL Port 
		properties.put("mail.smtp.port", "465"); 
		
		// enable authentication 
		properties.put("mail.smtp.auth", "true"); 
		
		// SSL Factory 
		properties.put("mail.smtp.socketFactory.class", 
				"javax.net.ssl.SSLSocketFactory"); 

		// creating Session instance referenced to 
		// Authenticator object to pass in 
		// Session.getInstance argument 
		Session session = Session.getDefaultInstance(properties, 
			new javax.mail.Authenticator() { 
				
				// override the getPasswordAuthentication 
				// method 
				protected PasswordAuthentication 
						getPasswordAuthentication() { 
					return new PasswordAuthentication("skleparka1@wp.pl", 
													"SokolWLesie"); 
				} 
			}); 
//compose the message 
try { 
	// javax.mail.internet.MimeMessage class is mostly 
	// used for abstraction. 
	MimeMessage message = new MimeMessage(session); 
	
	// header field of the header. 
	message.setFrom(new InternetAddress(from)); 
	
	message.addRecipient(Message.RecipientType.TO, 
						new InternetAddress(to)); 
	message.setSubject("Skleparka - twój zakup"); 
	message.setText("Twoja p³atnoœæ dla zamówienia id= " + orderId +" zosta³a potwierdzona a produkt wys³any, kod œledzenia paczki = " + trackingNumber); 

	// Send message 
	Transport.send(message); 
	System.out.println("Mail send.."); 
} 
catch (MessagingException mex) { 
	mex.printStackTrace(); 
} 
} 
}
