package com.epam.rank.task.workspace.credits;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.Actions;
import com.epam.rank.task.helper.CreditCardHelper;
import com.epam.rank.task.model.BankAccountModel;
import com.epam.rank.task.model.CreditCardModel;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.BankAccountService;
import com.epam.rank.task.model.service.CreditCardService;

public class CreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreditCardServlet() {
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

			Integer userId = ((UserModel) request.getSession().getAttribute(
					"user")).getId();
			ArrayList<CreditCardModel> creditCards = CreditCardService
					.getAll(userId);
			request.setAttribute("creditCards", creditCards);
			request.setAttribute("page", "/view/credit_card/index.jsp");
		} else if (action.equals(Actions.NEW.toString())) {
			Integer userId = ((UserModel) request.getSession().getAttribute(
					"user")).getId();
			ArrayList<BankAccountModel> accounts = BankAccountService
					.getUserAccounts(userId);
			request.setAttribute("act", "create");
			request.setAttribute("accounts", accounts);
			request.setAttribute("page", "/view/credit_card/new.jsp");
		} else if (action.equals(Actions.DELITE.toString())) {
			Integer userId = ((UserModel) request.getSession().getAttribute(
					"user")).getId();

			CreditCardService.deleteCC(Integer.parseInt(request
					.getParameter("ccId")));

			ArrayList<CreditCardModel> creditCards = CreditCardService
					.getAll(userId);
			request.setAttribute("creditCards", creditCards);
			request.setAttribute("page", "/view/credit_card/index.jsp");

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
			Integer userId = ((UserModel) request.getSession().getAttribute(
					"user")).getId();

			errors = CreditCardService.createCreditCard(CreditCardHelper
					.parseCard(request, userId));
			if (errors.isEmpty()) {
				response.sendRedirect("/EpamRankTask4/user/creditCards");
				return;
			} else {
				request.getSession().setAttribute("errors", errors);
				response.sendRedirect(request.getHeader("Referer"));
				return;
			}

		}
	}

}
