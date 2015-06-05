package com.epam.rank.task.workspace.payment;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.rank.task.helper.PaymentHalper;
import com.epam.rank.task.model.CreditCardModel;
import com.epam.rank.task.model.PaymentModel;
import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.service.CreditCardService;
import com.epam.rank.task.model.service.PaymentService;

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<CreditCardModel> creditCards;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer userId = ((UserModel) request.getSession().getAttribute("user"))
				.getId();

		creditCards = CreditCardService.getActiveCreditCards(userId);
		request.setAttribute("activeCC", creditCards);
		request.setAttribute("act", "create");
		request.setAttribute("page", "/view/payment/new.jsp");
		request.getRequestDispatcher("/view/layout/app_layout.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer ccid = null;
		CreditCardModel card = null;
		String cardNumber = (String) request.getParameter("card");
		for (CreditCardModel creditCardModel : creditCards) {
			if (creditCardModel.getCard().equals(cardNumber)) {
				ccid = creditCardModel.getId();
				card = creditCardModel;
			}
			
		}

		PaymentModel payment = PaymentHalper.parsePayment(request, ccid);
		ArrayList<String> errors = PaymentService.makePayment(payment, card);
		if (errors.isEmpty()) {
			creditCards.clear();
			request.setAttribute("page", "/view/user/user.jsp");
			request.getRequestDispatcher("/view/layout/app_layout.jsp").forward(request, response);;
		} else {
			request.getSession().setAttribute("errors", errors);
			response.sendRedirect(request.getHeader("Referer"));
		}
	}

}
