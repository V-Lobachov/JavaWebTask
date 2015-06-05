package com.epam.rank.task.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.epam.rank.task.model.AccountHistoryModel;

public class AccountHistoryDAO extends BaseDAO {

	private static final String COUNT_STORIES = "SELECT COUNT(*) FROM account_history WHERE user_id = ?;";
	private static final String NEW_STORY = "INSERT INTO account_history (message, account_id, user_id, created_at) VALUE (?, ?, ?, ?);";
	private static final String GET_ALL_HISTORY = ""
			+ "SELECT a.*, account "
			+ "FROM account_history AS a JOIN bank_account AS b ON a.account_id = b.id "
			+ "WHERE a.user_id = ? LIMIT ?,?;";

	public Integer countStories(Integer user_id) {
		int amount = 0;
		try {
			statement = con.prepareStatement(COUNT_STORIES);
			statement.setInt(1, user_id);
			result = statement.executeQuery();
			result.next();
			amount = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return amount;
	}

	public void newStory(AccountHistoryModel history) {
		try {
			statement = con.prepareStatement(NEW_STORY);
			statement.setString(1, history.getMessage());
			statement.setInt(2, history.getAccout_id());
			statement.setInt(3, history.getUser_id());
			statement.setTimestamp(4, new Timestamp(Calendar.getInstance()
					.getTime().getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getAllHistory(Integer user_id, Integer page) {
		try {
			statement = con.prepareStatement(GET_ALL_HISTORY);
			statement.setInt(1, user_id);
			statement.setInt(2, page*5);
			statement.setInt(3, 5);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
