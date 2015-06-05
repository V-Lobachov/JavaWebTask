package com.epam.rank.task.model.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.rank.task.model.BankAccountModel;

public class BankAccountTransformer {
	public static BankAccountModel getBankAccount(ResultSet set) {
		BankAccountModel account = null;

		try {
			if (set.next()) {
				account = new BankAccountModel();
				parseAccount(account, set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;
	}

	public static ArrayList<BankAccountModel> getAllAccounts(ResultSet set) {
		ArrayList<BankAccountModel> accountList = new ArrayList<>();
		BankAccountModel account = null;
		try {
			while (set.next()) {
				account = new BankAccountModel();
				parseAccount(account, set);
				accountList.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountList;
	}

	private static void parseAccount(BankAccountModel account, ResultSet set) {
		try {

			account.setId(set.getInt("id"));
			account.setAccount(set.getString("account"));
			account.setBalance(set.getFloat("balance"));
			account.setUser_id(set.getInt("user_id"));
			account.setActive(set.getBoolean("active"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
