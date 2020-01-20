package pl.skleparka.dao;

import pl.skleparka.beans.User;

public interface UserDAO extends GenericDAO<User, Integer>{
	User getUserByUsername(String username);
	User getUserByMail(String mail);
	void blockUser(int userId);
	void unblockUser(int userId);
}
