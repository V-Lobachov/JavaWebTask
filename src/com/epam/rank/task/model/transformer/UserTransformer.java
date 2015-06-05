package com.epam.rank.task.model.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.epam.rank.task.model.UserModel;

public class UserTransformer {

	public static UserModel getUser(ResultSet userData) {
		UserModel user = null;

		try {
			if (userData.next()) {
				user = new UserModel();
				parseUser(user, userData);
			} else {
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return user;
	}

	private static void parseUser(UserModel user, ResultSet userData) {
		try {
			user.setId(userData.getInt("id"));
			user.setEmail(userData.getString("email"));
			user.setPassword(userData.getString("pass"));
			user.setFirstName(userData.getString("first_name"));
			user.setLastName(userData.getString("last_name"));
			user.setRole(userData.getString("role"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<UserModel> getAllUsers(ResultSet setOfUsers){
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		UserModel user = null;
		try {
			while(setOfUsers.next()){
				user = new UserModel();
				parseUser(user, setOfUsers);
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
