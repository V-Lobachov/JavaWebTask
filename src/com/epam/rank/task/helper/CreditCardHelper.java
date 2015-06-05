package com.epam.rank.task.helper;

import javax.servlet.http.HttpServletRequest;

import com.epam.rank.task.model.CreditCardModel;

public class CreditCardHelper {
	public static CreditCardModel parseCard(HttpServletRequest request,
			Integer userId) {
		CreditCardModel creditCard = new CreditCardModel();

		creditCard.setAccount(request.getParameter("account"));
		creditCard.setCard(request.getParameter("card"));
		creditCard.setUser_id(userId);
		return creditCard;
	}
}
