package com.epam.rank.task.workspace.account;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.AccountHelper;
import com.epam.rank.task.helper.Actions;
import com.epam.rank.task.model.BankAccountModel;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.BankAccountService;

public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private String action;
	private ArrayList<String> errors;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action = (String) request.getParameter("action");

		if (action == null || action.equals(Actions.INDEX.toString())) {
			UserModel currentUser = (UserModel) request.getSession()
					.getAttribute("user");
			Integer id = null;
			if (request.getParameter("id") == null) {
				id = currentUser.getId();
			} else {
				if (currentUser.getRole().equals("admin")) {
					id = Integer.parseInt(request.getParameter("id"));

				} else {
					id = currentUser.getId();
				}
			}

			ArrayList<BankAccountModel> accounts = BankAccountService
					.getUserAccounts(id);

			request.setAttribute("bankAccounts", accounts);
			request.setAttribute("page", "/view/bank_account/index.jsp");

		} else if (action.equals(Actions.NEW.toString())) {
			request.setAttribute("act", "create");
			request.setAttribute("page", "/view/bank_account/new.jsp");

		} else if (action.equals(Actions.EDIT.toString())) {

			request.setAttribute("act", "update");
			request.setAttribute("accountId", request.getParameter("accountId"));
			request.setAttribute("page", "/view/bank_account/edit.jsp");
		} else if (action.equals(Actions.DELITE.toString())) {
			UserModel user = (UserModel) request.getSession().getAttribute(
					"user");
			Integer userId = user.getId();
			Integer accountId = null;
			if (request.getParameter("accountId") != null) {
				accountId = Integer.parseInt(request.getParameter("accountId"));
				if (request.getParameter("unfreeze") == null) {
					BankAccountService.freezeAccount(userId, accountId);
				} else {
					BankAccountService.unfreezeAccount(accountId);
				}
				
				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
				//response.sendRedirect(request.getHeader("Referer"));
				return;
			} else {
				response.sendError(404);
				return;
			}

		}

		request.getRequestDispatcher("/view/layout/app_layout.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action = (String) request.getParameter("action");
		if (action.equals(Actions.CREATE.toString())) {
			UserModel user = (UserModel) request.getSession().getAttribute(
					"user");

			errors = BankAccountService.createBankAccount(AccountHelper
					.parseAccount(request, user.getId()));

			if (errors.isEmpty()) {
				request.setAttribute("action", "index");
				response.sendRedirect("/EpamRankTask4/user/account");
				return;
			} else {
				request.getSession().setAttribute("errors", errors);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
		} else if (action.equals(Actions.UPDATE.toString())) {

			String balance = request.getParameter("balance");
			String accountId = request.getParameter("accountId");
			Integer userId = ((UserModel) request.getSession().getAttribute(
					"user")).getId();
			Integer id = null;
			if (accountId == null) {
				response.sendError(404);
				return;
			} else {
				id = Integer.parseInt(accountId);
			}
			errors = BankAccountService.undateBalance(id, userId, balance);
			if (errors.isEmpty()) {
				request.setAttribute("action", "index");
				response.sendRedirect("/EpamRankTask4/user/account");
				return;
			} else {
				request.getSession().setAttribute("errors", errors);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}
		}
	}
}
