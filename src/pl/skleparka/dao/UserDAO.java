package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.User;

public interface UserDAO extends GenericDAO<User, Integer>{
	User getUserByUsername(String username);
	User getUserByMail(String mail);
	void blockUser(int userId);
	void unblockUser(int userId);
	List<User> getUsersByFirstName(String firstName);
	List<User> getUsersByLastName(String lastName);
	List<User> getUsersByEmail(String email);
	List<User> getUsersByUsername(String username);
	List<User> getUsersByActiveStatus(boolean active);
	
}
