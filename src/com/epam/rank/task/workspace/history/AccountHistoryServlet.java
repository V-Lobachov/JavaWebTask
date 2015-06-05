package com.epam.rank.task.workspace.history;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.model.AccountHistoryModel;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.AccountHistoryService;

public class AccountHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private Integer user_id;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (user_id == null || user_id != Integer.parseInt( request.getParameter("userId"))) {
			UserModel user = (UserModel) request.getSession().getAttribute(
					"user");
			user_id = user.getId();
			if (user.getRole().equals("admin")) {
				if (request.getParameter("userId") != null) {
					user_id = Integer.parseInt(request.getParameter("userId"));
				}
			}
		}
		ArrayList<AccountHistoryModel> history = null;

		int amountOfHistory = AccountHistoryService.countStories(user_id);
		if (request.getParameter("page") == null) {
			history = AccountHistoryService.getAllHistroy(user_id, 0);
		} else {
			history = AccountHistoryService.getAllHistroy(user_id, Integer.parseInt(request
					.getParameter("page")) - 1);
		}
		request.setAttribute("user_id", user_id);
		request.setAttribute("amountOfPages", Math.ceil(amountOfHistory / 5d));
		request.setAttribute("history", history);
		request.setAttribute("page", "/view/history/index.jsp");
		request.getRequestDispatcher("/view/layout/app_layout.jsp").forward(
				request, response);
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
