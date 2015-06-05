package com.epam.rank.task.helper;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.rank.task.model.UserModel;

public class UserHelper {
	public static UserModel parseUser(HttpServletRequest request) {
		UserModel user = new UserModel();
		user.setEmail(new String(request.getParameter("email").getBytes(
				StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
		user.setFirstName(new String(request.getParameter("first_name")
				.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
		user.setLastName(new String(request.getParameter("last_name").getBytes(
				StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

		if (request.getParameter("password") != null) {
			user.setPassword(new String(request.getParameter("password")
					.getBytes(StandardCharsets.ISO_8859_1),
					StandardCharsets.UTF_8));
		}
		return user;
	}

	public static void setSession(HttpServletRequest request, UserModel user) {
		HttpSession userSession = null;
		userSession = request.getSession(true);
		userSession.setAttribute("user", user);

	}

}
