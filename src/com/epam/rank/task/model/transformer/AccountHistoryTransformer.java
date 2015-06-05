package com.epam.rank.task.model.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.rank.task.model.AccountHistoryModel;

public class AccountHistoryTransformer {

	private static void parseHistory(AccountHistoryModel story,
			ResultSet historyData) {
		try {
			story.setMessage(historyData.getString("message"));
			story.setAccout_id(historyData.getInt("account_id"));
			story.setId(historyData.getInt("id"));
			story.setUser_id(historyData.getInt("user_id"));
			story.setCreated_at(historyData.getTimestamp("created_at"));
			story.setAccount(historyData.getString("account"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<AccountHistoryModel> getAllAccountHistory(
			ResultSet set) {
		ArrayList<AccountHistoryModel> history = new ArrayList<>();
		AccountHistoryModel story = null;
		try {
			while (set.next()) {
				story = new AccountHistoryModel();
				parseHistory(story, set);
				history.add(story);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return history;
	}
}
