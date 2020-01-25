package pl.skleparka.filters;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.skleparka.beans.User;
import pl.skleparka.service.UserService;

public class UserFilter {
	UserService userService = UserService.getInstance();
	
	public void filterUsersById(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<User> users = new ArrayList<User>();
		HttpSession session = request.getSession();
		try {
		users.add(userService.getUserById(Integer.parseInt(searchPhrase)));
		}catch(NumberFormatException e) {
			System.out.println("Someone tried to filter ID with a word");
		}
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
	}

	public void filterUsersByUsername(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<User> users = userService.getUsersByUsername(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
	}

	public void filterUsersByEmail(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<User> users = userService.getUsersByEmail(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
	}

	public void filterUsersByFirstName(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<User> users = userService.getUsersByFirstName(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
	}

	public void filterUsersByLastName(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		List<User> users = userService.getUsersByLastName(searchPhrase);
		HttpSession session = request.getSession();
		session.setAttribute("userList", users);
   		request.setAttribute("userList", users);
	}

	public void filterUsersByStatus(HttpServletRequest request, HttpServletResponse response, String searchPhrase) {
		HttpSession session = request.getSession();
		if(searchPhrase.toLowerCase() == "true") {
			List<User> users = userService.getUsersByActiveStatus(true);
			session.setAttribute("userList", users);
	   		request.setAttribute("userList", users);
		} else {
			List<User> users = userService.getUsersByActiveStatus(false);
			session.setAttribute("userList", users);
	   		request.setAttribute("userList", users);
		}
	}
}
