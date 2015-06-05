package com.epam.rank.task.model.validation;

import java.sql.SQLException;

import com.epam.rank.task.model.dao.UserDAO;

public class UserValidation extends Validation {

	

	public UserValidation() {
		super();
	}

	public boolean uniqEmail(String email) {
		boolean result = true;
		UserDAO userBridge = new UserDAO();
		try {
			if (userBridge.findByEmail(email).next()) {
				result = false;
				errors.add(String.format("%s is not uniq", email));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userBridge.close();
		return result;
	}
}
