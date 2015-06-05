package com.epam.rank.task.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.rank.task.model.CreditCardModel;

public class CreditCardDAO extends BaseDAO {

	private static final String GET_ALL_CREDIT_CARDS = ""
			+ "SELECT c.id, c.card, c.account_id, c.user_id, a.account  "
			+ "FROM credit_card AS c JOIN bank_account AS a ON c.account_id = a.id "
			+ "WHERE c.user_id =? AND is_active = 1;";
	private static final String CREATE_NEW_CARD = ""
			+ "INSERT INTO credit_card (is_active, card, account_id, user_id )"
			+ "VALUE (1 ,?, (SELECT id FROM bank_account WHERE account = ? and user_id = ?), ?);";

	private static final String GET_ACTIV_CC = ""
			+ "SELECT c.*, account "
			+ "FROM credit_card AS c JOIN bank_account AS b ON b.id = c.account_id  "
			+ "WHERE b.active AND b.user_id = ? AND is_active = 1;";

	private static final String DELETE_CC = "UPDATE credit_card SET is_active = 0 WHERE id = ?;";

	public CreditCardDAO() {

	}

	public void deleteCC(Integer ccId) {
		try {
			statement = con.prepareStatement(DELETE_CC);
			statement.setInt(1, ccId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getAll(Integer userId) {
		try {
			statement = con.prepareStatement(GET_ALL_CREDIT_CARDS);
			statement.setInt(1, userId);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void createCreditCard(CreditCardModel creditCard) {
		try {
			statement = con.prepareStatement(CREATE_NEW_CARD);
			statement.setString(1, creditCard.getCard());
			statement.setString(2, creditCard.getAccount());
			statement.setInt(3, creditCard.getUser_id());
			statement.setInt(4, creditCard.getUser_id());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getActiveCC(Integer userId) {
		try {
			statement = con.prepareStatement(GET_ACTIV_CC);
			statement.setInt(1, userId);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
