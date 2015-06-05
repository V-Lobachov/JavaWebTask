package com.epam.rank.task.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.rank.task.model.BankAccountModel;

public class BankAccountDAO extends BaseDAO {
	private static final String GET_ALL_USER_ACCOUNTS = "SELECT * FROM bank_account WHERE user_id = ?;";
	private static final String CREATE_ACCOUNT = "INSERT INTO bank_account (account, balance, user_id) VALUE (?,?,?)";
	private static final String GET_ACCOUNT_BY_ID = "SELECT * FROM bank_account WHERE id = ?;";
	private static final String UPDATE_BALANCE = "UPDATE bank_account SET balance = balance + ? WHERE id = ?;";
	private static final String UPDATE_ACCOUNT_ACTIVITY = "UPDATE bank_account SET active = ? WHERE id = ?";


	public BankAccountDAO() {
	}

	public void createAccount(BankAccountModel account) {
		try {
			statement = con.prepareStatement(CREATE_ACCOUNT);
			statement.setString(1, account.getAccount());
			statement.setFloat(2, account.getBalance());
			statement.setInt(3, account.getUser_id());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ResultSet getAccount(Integer id) {
		try {
			statement = con.prepareStatement(GET_ACCOUNT_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ResultSet getAllAccounts(Integer userID) {

		try {
			statement = con.prepareStatement(GET_ALL_USER_ACCOUNTS);
			statement.setInt(1, userID);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public void updateBalance(Integer id, Float balance) {
		try {
			statement = con.prepareStatement(UPDATE_BALANCE);
			statement.setFloat(1, balance);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void freezeAccount(Integer user_id, Integer accountId) {
		try {
			statement = con.prepareStatement(UPDATE_ACCOUNT_ACTIVITY);
					//+ " AND user_id = ?;");
			statement.setBoolean(1, false);
			statement.setInt(2, accountId);
			//statement.setInt(3, user_id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unfreezeAccount(Integer accountId) {
		try {
			statement = con.prepareStatement(UPDATE_ACCOUNT_ACTIVITY);
			statement.setBoolean(1, true);
			statement.setInt(2, accountId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
