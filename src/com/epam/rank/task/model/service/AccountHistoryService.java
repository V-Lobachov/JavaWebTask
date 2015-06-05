package com.epam.rank.task.model.service;

import java.util.ArrayList;

import com.epam.rank.task.model.AccountHistoryModel;
import com.epam.rank.task.model.dao.AccountHistoryDAO;
import com.epam.rank.task.model.transformer.AccountHistoryTransformer;

public class AccountHistoryService {

	public static void createStory(AccountHistoryModel story) {
		AccountHistoryDAO historyBridge = new AccountHistoryDAO();
		historyBridge.newStory(story);
		historyBridge.close();
	}

	public static ArrayList<AccountHistoryModel> getAllHistroy(Integer user_id, Integer page) {
		ArrayList<AccountHistoryModel> history = new ArrayList<>();
		AccountHistoryDAO historyBridge = new AccountHistoryDAO();
		history = AccountHistoryTransformer.getAllAccountHistory(historyBridge
				.getAllHistory(user_id, page));

		historyBridge.close();
		return history;
	}
	
	public static Integer countStories(Integer user_id){
		Integer result = null;
		AccountHistoryDAO historyBridge = new AccountHistoryDAO();
		result = historyBridge.countStories(user_id);
		historyBridge.close();
		return result;
	}
}
