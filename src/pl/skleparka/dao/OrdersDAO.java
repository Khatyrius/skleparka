package pl.skleparka.dao;

import java.sql.Date;
import java.util.List;

import pl.skleparka.beans.Order;

public interface OrdersDAO extends GenericDAO<Order, Integer> {
List<Order> getAllOrdersOfUser(int userId);
List<Order> getAllOrdersWithStatus(String status);
List<Order> getAllOrdersWithDate(Date date);
}
