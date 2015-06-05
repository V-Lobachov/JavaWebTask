package com.epam.rank.task.model.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.rank.task.model.CreditCardModel;

public class CreditCardTransformer {

	public static CreditCardModel getCreditCard(ResultSet set) {
		CreditCardModel creditCard = null;

		try {
			if (set.next()) {
				creditCard = new CreditCardModel();
				parseCreditCard(creditCard, set);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return creditCard;
	}

	public static ArrayList<CreditCardModel> getAllCreditCards(ResultSet set) {
		ArrayList<CreditCardModel> creditCards = new ArrayList<CreditCardModel>();
		CreditCardModel creditCard = null;
		try {
			while (set.next()) {
				creditCard = new CreditCardModel();
				parseCreditCard(creditCard, set);
				creditCards.add(creditCard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return creditCards;
	}

	private static void parseCreditCard(CreditCardModel creditCard,
			ResultSet set) {
		try {
			creditCard.setId(set.getInt("id"));
			creditCard.setAccount_id(set.getInt("account_id"));
			creditCard.setCard(set.getString("card"));
			creditCard.setUser_id(set.getInt("user_id"));
			creditCard.setAccount(set.getString("account"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
