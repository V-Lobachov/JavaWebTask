package com.epam.rank.task.model.service;

import java.util.ArrayList;

import com.epam.rank.task.model.AccountHistoryModel;
import com.epam.rank.task.model.CreditCardModel;
import com.epam.rank.task.model.PaymentModel;
import com.epam.rank.task.model.dao.PaymentDAO;
import com.epam.rank.task.model.validation.PaymentValidation;

public class PaymentService {
	public static ArrayList<String> makePayment(PaymentModel payment,
			CreditCardModel card) {
		ArrayList<String> errors = null;
		PaymentDAO paymentBridge = new PaymentDAO();
		PaymentValidation validator = new PaymentValidation();
		AccountHistoryModel story = null;

		validator.presence(payment, "amount");
		validator.presence(payment, "card_id");
		validator.presence(payment, "destination");
		validator.validateFormat("^\\d+[\\.|,]\\d{1,2}$", payment.getAmount()
				.toString(), "Wrong data rormat. must be floating number 0.00");
		validator.validateFormat("^\\d{20}$", payment.getDestination(),
				"wrong account format. It must contain 20 numbers");
		validator.validateBalance(payment, card, "Not enough of funds");
		errors = validator.validationReport();
		if (errors.isEmpty()) {
			story = new AccountHistoryModel();
			story.setAccout_id(card.getAccount_id());
			story.setUser_id(card.getUser_id());
			story.setMessage("Was made transfer of funds to "
					+ payment.getDestination() + " in the amount of "
					+ payment.getAmount());

			paymentBridge.newPayment(payment);
			AccountHistoryService.createStory(story);
		}
		paymentBridge.close();

		return errors;
	}
}
