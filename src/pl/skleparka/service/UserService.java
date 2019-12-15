package pl.skleparka.service;

import pl.skleparka.dao.DAOFactory;
import pl.skleparka.dao.UserDAO;
import pl.skleparka.model.User;

public class UserService {
	public void addUser(String name, String surname, int phoneNumber, String address, String city, String username, String email, String password) {
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setPhoneNumber(phoneNumber);
		user.setAddress(address);
		user.setCity(city);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		userDao.create(user);
	}
	
	public User getUserById(long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		User user = userDao.read(userId);
		return user;
	}
	
	public User getUserByUsername(String username) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		User user = userDao.getUserByUsername(username);
		return user;
	}
}