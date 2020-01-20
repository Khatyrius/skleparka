package pl.skleparka.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import pl.skleparka.beans.Order;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.OrdersDAO;

public class OrderService {
	private static OrderService instance;
    
    private OrderService(){}
    
    public static OrderService getInstance(){
        if(instance == null){
            instance = new OrderService();
        }
        return instance;
    }
    
	public void addNewOrder(int userId, String status, double total) {        
		Order order = new Order();

		order.setUserId(userId);
		order.setOrderDate(Date.valueOf(LocalDate.now()));
		order.setStatus(status);
		order.setTotal(total);
		
		getDao().create(order);
	}
	
	public Order getOrderById(int orderId) {
		return getDao().read(orderId);
	}
	
	public void updateOrderStatus(int orderId, String status) {
		Order order = getDao().read(orderId);
		
		if(!status.isEmpty() && !status.equals(order.getStatus())) {
		order.setStatus(status);
		}
		
		getDao().update(order);
	}
	
	public List<Order> getAllOrders(){
		return getDao().getAll();
	}
	
	public List<Order> getAllOrdersOfUser(int userId){
		return getDao().getAllOrdersOfUser(userId);
	}
	
	public List<Order> getAllOrdersWithStatus(String status){
		return getDao().getAllOrdersWithStatus(status);
	}

	public List<Order> getAllOrdersWithDate(Date date){
		return getDao().getAllOrdersWithDate(date);
	}
	
	private OrdersDAO getDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		OrdersDAO orderDao = factory.getOrdersDAO();
		return orderDao;
	}

}
