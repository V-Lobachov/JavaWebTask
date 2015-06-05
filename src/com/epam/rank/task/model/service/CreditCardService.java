package com.epam.rank.task.model.service;

import java.util.ArrayList;

import com.epam.rank.task.model.CreditCardModel;
import com.epam.rank.task.model.dao.CreditCardDAO;
import com.epam.rank.task.model.transformer.CreditCardTransformer;
import com.epam.rank.task.model.validation.Validation;

public class CreditCardService {

	public static ArrayList<CreditCardModel> getAll(Integer userId) {
		ArrayList<CreditCardModel> creditCards = new ArrayList<CreditCardModel>();
		CreditCardDAO creditCardBridge = new CreditCardDAO();
		creditCards = CreditCardTransformer.getAllCreditCards(creditCardBridge
				.getAll(userId));
		creditCardBridge.close();
		return creditCards;
	}

	public static ArrayList<CreditCardModel> getActiveCreditCards(Integer userId) {
		ArrayList<CreditCardModel> result = null;
		CreditCardDAO ccBridge = new CreditCardDAO();

		result = CreditCardTransformer.getAllCreditCards(ccBridge.getActiveCC(userId));
		ccBridge.close();

		return result;
	}
	
	public static void deleteCC(Integer ccId){
		CreditCardDAO ccBridge = new CreditCardDAO();
		ccBridge.deleteCC(ccId);
		ccBridge.close();
	}

	public static ArrayList<String> createCreditCard(CreditCardModel creditCard) {
		ArrayList<String> errors = null;
		CreditCardDAO creditCardBridge = new CreditCardDAO();
		Validation validator = new Validation();

		validator.presence(creditCard, "card");
		validator.validateFormat("^\\d{16}$", creditCard.getCard(),
				"wrong card number format. must contain 16 numbers.");
		errors = validator.validationReport();
		if (errors.isEmpty()) {
			creditCardBridge.createCreditCard(creditCard);
			creditCardBridge.close();
		}

		return errors;
	}
}
