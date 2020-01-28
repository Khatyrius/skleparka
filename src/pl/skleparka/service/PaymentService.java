package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.Payment;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.PaymentDAO;

public class PaymentService {
	private static PaymentService instance;
    
    private PaymentService(){}
    
    public static PaymentService getInstance(){
        if(instance == null){
            instance = new PaymentService();
        }
        return instance;
    }
	
	public void addPayment(String paymentType, int userId, double amount, int sellerId, int billingId, int orderId) {
		Payment payment = new Payment();
		
		payment.setPaymentType(paymentType);
		payment.setUserId(userId);
		payment.setAmount(amount);
		payment.setSellerId(1);
		payment.setBillingId(billingId);
		payment.setOrderId(orderId);
		payment.setStatus("pending".toUpperCase());
		
		GetDao().create(payment);
	}
	
	public Payment getPaymentWithId(int paymentId) {
		return GetDao().read(paymentId);
	}
	
	public void updatePayment(int paymentId, String status) {
		Payment payment = getPaymentWithId(paymentId);
		
		if(!status.isEmpty() && !status.equals(payment.getStatus()))
		payment.setStatus(status.toUpperCase());
		
		GetDao().update(payment);
	}
	
	public List<Payment> getAllPayments(){
		return GetDao().getAll();
	}
	
	public Payment getPaymentForOrder(int orderId) {
		return GetDao().getPaymentForOrder(orderId);
	}
	
	public List<Payment> getAllPaymentsOfUser(int userId){
		return GetDao().getAllPaymentsOfUser(userId);
	}
	
	public List<Payment> getAllPaymentsWithAmountLessThan(double amount){
		return GetDao().getAllPaymentsWithLessThan(amount);
	}
	
	public List<Payment> getAllPaymentsWithAmountMoreThan(double amount){
		return GetDao().getAllPaymentsWithMoreThan(amount);
	}
	
	public List<Payment> getAllPaymentsWithAmountEqualTo(double amount){
		return GetDao().getAllPayemntsEqualTo(amount);
	}
	
	public List<Payment> getAllPaymentsWithStatus(String status){
		return GetDao().getAllPaymentsWithStatus(status);
	}

	private PaymentDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		PaymentDAO paymentDao = factory.getPaymentDAO();
		return paymentDao;
	}
}
