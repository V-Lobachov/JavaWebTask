package com.epam.rank.task.model.validation;

import com.epam.rank.task.model.BankAccountModel;
import com.epam.rank.task.model.CreditCardModel;
import com.epam.rank.task.model.PaymentModel;
import com.epam.rank.task.model.dao.BankAccountDAO;
import com.epam.rank.task.model.transformer.BankAccountTransformer;

public class PaymentValidation extends Validation {
	public boolean validateBalance(PaymentModel payment, CreditCardModel card, String message) {
		boolean result = true;
		BankAccountDAO accountBridge = new BankAccountDAO();
		BankAccountModel account = BankAccountTransformer
				.getBankAccount(accountBridge.getAccount(card.getAccount_id()));
		accountBridge.close();
		if(payment.getAmount() > account.getBalance()){
			errors.add(message);
			result = false;
		}
		return result;
	}
}
