package com.epam.rank.task.workspace;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.UserHelper;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.UserService;

public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//String id = request.getParameter("id");
		UserModel currentUser = (UserModel) request.getSession().getAttribute(
				"user");
		
		/*
		if (id == null) {
			id = (String) request.getSession().getAttribute("id");
			if (id == null) {
				response.sendError(404);
			}
		} else {
			if (currentUser.getRole().equals("admin")) {
				request.getSession().setAttribute("id", id);
			} else {
				request.getSession().setAttribute("id",
						Integer.toString(currentUser.getId()));
			}
			
		}
*/
		
		if (currentUser != null) {
			request.setAttribute("userToEdit", currentUser);

			request.setAttribute("page", "/view/user/edit.jsp");
			request.getRequestDispatcher("/view/layout/app_layout.jsp")
					.forward(request, response);
		} else {
			response.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserModel user = UserHelper.parseUser(request);
		user.setId(((UserModel) request.getSession().getAttribute("user")).getId());
		//user.setId(Integer.parseInt(request.getParameter("id")));
		ArrayList<String> errors = UserService.updateUser(user);
		if (errors.isEmpty()) {
		//	request.getSession().removeAttribute("id");
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("viewUser", user.getId());
			response.sendRedirect("/EpamRankTask4/user");
		} else {
			request.getSession().setAttribute("errors", errors);
			response.sendRedirect(request.getHeader("Referer"));
		}

	}
}
