package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.model.User;


public interface UserDAO extends GenericDAO<User, Long> {

	List<User> getAll();
	User getUserByUsername(String username);
	
}