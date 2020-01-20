package pl.skleparka.service;

import java.util.List;

import pl.skleparka.beans.History;
import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.HistoryDAO;

public class HistoryService {
	
	public void addToHistory(int userId, int orderId) {
		History history = new History();
		
		history.setUserId(userId);
		history.setOrderId(orderId);
		
		GetDao().create(history);
	}
	
	public History getHistoryById(int historyId) {
		return GetDao().read(historyId);
	}

	public List<History> getEntireHistory(){
		return GetDao().getAll();	
	}
	
	public List<History> getHistoryOfUser(int userId){
		return GetDao().getHistoryOfUser(userId);
	}
	
	private HistoryDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		HistoryDAO historyDao = factory.getHistoryDAO();
		return historyDao;
	}
}
