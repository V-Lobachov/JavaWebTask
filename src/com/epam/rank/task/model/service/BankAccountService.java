package com.epam.rank.task.model.service;

import java.util.ArrayList;

import com.epam.rank.task.model.AccountHistoryModel;
import com.epam.rank.task.model.BankAccountModel;
import com.epam.rank.task.model.dao.BankAccountDAO;
import com.epam.rank.task.model.transformer.BankAccountTransformer;
import com.epam.rank.task.model.validation.Validation;

public class BankAccountService {

	public static ArrayList<BankAccountModel> getUserAccounts(Integer id) {
		ArrayList<BankAccountModel> result = null;
		BankAccountDAO accountBridge = new BankAccountDAO();
		result = BankAccountTransformer.getAllAccounts(accountBridge
				.getAllAccounts(id));
		accountBridge.close();
		return result;
	}

	public static ArrayList<String> createBankAccount(BankAccountModel account) {
		ArrayList<String> errors = null;
		Validation validator = new Validation();

		validator.presence(account, "account");
		validator.presence(account, "balance");
		validator.validateFormat("^\\d{20}$", account.getAccount(),
				"wrong account format. It must contain 20 numbers");
		validator.validateFormat("^\\d+[\\.|,]\\d{1,2}$", account.getBalance()
				.toString(), "wrong currency format");

		errors = validator.validationReport();
		if (errors.isEmpty()) {
			BankAccountDAO accountBridge = new BankAccountDAO();
			accountBridge.createAccount(account);
			accountBridge.close();
		}

		return errors;
	}

	public static ArrayList<String> undateBalance(Integer id, Integer user_id,
			String balance) {
		ArrayList<String> errors = null;

		BankAccountDAO accountBridge = null;
		AccountHistoryModel story = null;
		Validation validator = new Validation();

		validator.validateFormat("^\\d+[\\.|,]\\d{1,2}$", balance,
				"wrong currency format");
		errors = validator.validationReport();

		if (errors.isEmpty()) {
			accountBridge = new BankAccountDAO();
			story = new AccountHistoryModel();
			story.setAccout_id(id);
			story.setUser_id(user_id);
			story.setMessage("contribution was made in the amount of "+ balance);
			accountBridge.updateBalance(id, Float.parseFloat(balance));
			AccountHistoryService.createStory(story);
			accountBridge.close();
		}
		return errors;
	}

	public static void freezeAccount(Integer userId, Integer accountId) {
		BankAccountDAO accountBridge = new BankAccountDAO();

		accountBridge.freezeAccount(userId, accountId);
		accountBridge.close();
	}

	public static void unfreezeAccount(Integer accountId) {
		BankAccountDAO accountBridge = new BankAccountDAO();
		accountBridge.unfreezeAccount(accountId);
		accountBridge.close();
	}
}
