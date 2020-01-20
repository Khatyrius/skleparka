package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.Payment;

public interface PaymentDAO extends GenericDAO<Payment, Integer>{
Payment getPaymentForOrder(int orderId);
Payment getPaymentForShipement(int shipmentId);
List<Payment> getAllPaymentsOfUser(int userId);
List<Payment> getAllPaymentsWithLessThan(double amount);
List<Payment> getAllPaymentsWithMoreThan(double amount);
List<Payment> getAllPayemntsEqualTo(double amount);
List<Payment> getAllPaymentsWithStatus(String status);
}
