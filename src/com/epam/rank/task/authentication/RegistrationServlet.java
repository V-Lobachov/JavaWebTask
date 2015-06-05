package com.epam.rank.task.authentication;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.UserHelper;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.UserService;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/view/registration_page.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel user = UserHelper.parseUser(request);

		ArrayList<String> errors = UserService.validateUser(user);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			
			request.setAttribute("email", user.getEmail());
			request.setAttribute("password", user.getPassword());
			request.setAttribute("first_name", user.getFirstName());
			request.setAttribute("last_name", user.getLastName());
			
			request.getRequestDispatcher("/view/registration_page.jsp")
					.forward(request, response);
			
			return;
		} else {
			UserService.createUser(user);
			UserHelper.setSession(request, user);
		}
		response.sendRedirect("");
	}
}
