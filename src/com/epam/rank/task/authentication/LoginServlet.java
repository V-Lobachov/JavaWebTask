package com.epam.rank.task.authentication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.UserHelper;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("/EpamRankTask4/");
			return;
		}
		request.getRequestDispatcher("view/login_page.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = new String(request.getParameter("email").getBytes(),
				StandardCharsets.UTF_8);
		String password = new String(request.getParameter("password")
				.getBytes(), StandardCharsets.UTF_8);
		;

		UserModel user = UserService.getUser(email, password);
		if (user != null) {
			UserHelper.setSession(request, user);
			response.sendRedirect("");
			return;
		} else {
			ArrayList<String> errors = new ArrayList<String>();
			errors.add("No such user. Check that email and password is correct");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("view/login_page.jsp").forward(
					request, response);
		}

	}

}
