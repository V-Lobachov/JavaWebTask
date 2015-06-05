package com.epam.rank.task.model.service;

import java.util.ArrayList;

import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.dao.UserDAO;
import com.epam.rank.task.model.transformer.UserTransformer;
import com.epam.rank.task.model.validation.UserValidation;

public class UserService {

	public static UserModel getUser(String email, String password) {

		UserDAO userBridge = new UserDAO();
		UserModel user = UserTransformer.getUser(userBridge.getUser(email,
				password));
		userBridge.close();
		return user;
	}

	public static UserModel getById(String id) {
		UserDAO userBridge = new UserDAO();
		UserModel user = UserTransformer.getUser(userBridge.findById(id));
		userBridge.close();
		return user;
	}

	public static ArrayList<UserModel> fingUser(String userClue) {
		ArrayList<UserModel> users = null;
		UserDAO userBridge = new UserDAO();
		users = UserTransformer.getAllUsers(userBridge.findUser(userClue));
		userBridge.close();
		return users;
	}

	public static ArrayList<String> validateUser(UserModel user) {
		UserValidation validator = new UserValidation();
		ArrayList<String> errors = null;
		validator.presence(user, "email");
		validator.presence(user, "password");
		validator.presence(user, "firstName");
		validator.presence(user, "lastName");
		validator.isEmailValid(user.getEmail());
		validator.uniqEmail(user.getEmail());

		errors = validator.validationReport();
		return errors;

	}

	public static ArrayList<String> updateUser(UserModel user) {
		UserDAO userBridge = new UserDAO();
		UserValidation validator = new UserValidation();
		validator.presence(user, "email");
		validator.presence(user, "firstName");
		validator.presence(user, "lastName");
		validator.isEmailValid(user.getEmail());

		ArrayList<String> errors = validator.validationReport();
		if (errors.isEmpty()) {

			userBridge.updateUser(user);

		}
		userBridge.close();
		return errors;
	}

	public static ArrayList<UserModel> getAllUsers(Integer page, String sortQuery) {
		UserDAO userBridge = new UserDAO();
		ArrayList<UserModel> users = UserTransformer.getAllUsers(userBridge
				.getAll(page, sortQuery));
		userBridge.close();

		return users;
	}

	public static Integer countUsers() {
		Integer result = null;
		UserDAO userBridge = new UserDAO();
		result = userBridge.countUsers();
		userBridge.close();
		return result;
	}

	public static void changeRole(Integer userId, String newRole) {
		UserDAO userBridge = new UserDAO();
		userBridge.changeRole(userId, newRole);
		userBridge.close();
	}

	public static void createUser(UserModel user) {

		UserDAO userBridge = new UserDAO();
		userBridge.createUser(user);
		userBridge.close();
	}
}
