package com.epam.rank.task.workspace;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.Actions;
import com.epam.rank.task.helper.SortHelper;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.UserService;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
		sh = new SortHelper();
	}
	SortHelper sh;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {

			 
			//System.out.println(sh.getSortQuery(request));
			

			List<UserModel> users = null;
			int amountOfUsers = UserService.countUsers();
			if (request.getParameter("page") == null) {
				users = UserService.getAllUsers(0, sh.getSortQuery(request));
			} else {
				users = UserService.getAllUsers(Integer.parseInt(request
						.getParameter("page")) - 1, sh.getSortQuery(request));
			}
			request.setAttribute("amountOfPages", Math.ceil(amountOfUsers / 5d));
			request.setAttribute("users", users);
			request.setAttribute("page", "/view/admin_page.jsp");
			request.getRequestDispatcher("/view/layout/app_layout.jsp")
					.forward(request, response);
			return;
		} else if (action.equals(Actions.NEW.toString())) {
			Integer userId = Integer.parseInt(request.getParameter("userId"));

			UserService.changeRole(userId, request.getParameter("role"));
			response.sendRedirect(request.getHeader("Referer"));

			return;
		}

		request.setAttribute("page", "/view/home.jsp");
		request.getRequestDispatcher("/view/layout/app_layout.jsp").forward(
				request, response);
	}

}
