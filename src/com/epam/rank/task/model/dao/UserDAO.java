package com.epam.rank.task.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.rank.task.model.UserModel;
import com.epam.rank.task.model.security.KryptoPassword;

public class UserDAO extends BaseDAO {

	private static final String CREATE_USER = "INSERT INTO user (email, pass, salt, first_name, last_name, role)  VALUE (?,?,?,?,?,?);";
	private static final String GET_USER = "SELECT id, email, pass, first_name, last_name, role FROM user WHERE email = ? AND  pass =?;";
	private static final String FIND_BY_EMAIL = "SELECT email FROM user WHERE email = ?";
	private static final String FIND_BY_ID = "SELECT * FROM user WHERE id = ?;";
	private static final String GET_ALL_USERS = "SELECT id, email, pass, first_name, last_name, role FROM user LIMIT ?,?;";
	private static final String UPDATE_USER = "UPDATE user SET email =?,first_name=?,last_name=? WHERE id = ?;";
	private static final String CHANGE_ROLE = "UPDATE user SET role = ? WHERE id = ?;";
	private static final String COUNT = "SELECT count(*) FROM user;";
	private static final String FIND_USER = ""
			+ "SELECT id, email, pass, first_name, last_name, role "
			+ "FROM user WHERE lower(first_name) REGEXP lower(?) "
			+ "OR lower(last_name) REGEXP lower(?) "
			+ "OR lower(email) REGEXP lower(?) LIMIT 5;";

	public ResultSet findUser(String userClue) {
		try {
			String regexp = String.format(".*%s.*", userClue);
			
			statement = con.prepareStatement(FIND_USER);
			statement.setString(1, regexp);
			statement.setString(2, regexp);
			statement.setString(3, regexp);
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Integer countUsers() {
		Integer amount = null;
		try {
			statement = con.prepareStatement(COUNT);
			result = statement.executeQuery();
			result.next();
			amount = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	public ResultSet getUser(String email, String password) {
		String encryptedPassword = KryptoPassword.encrypt(password);

		try {
			statement = con.prepareStatement(GET_USER);
			statement.setString(1, email);
			statement.setString(2, encryptedPassword);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void changeRole(Integer userId, String role) {
		try {
			statement = con.prepareStatement(CHANGE_ROLE);
			statement.setString(1, role);
			statement.setInt(2, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet findByEmail(String email) {

		try {
			statement = con.prepareStatement(FIND_BY_EMAIL);
			statement.setString(1, email);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ResultSet findById(String id) {
		try {
			statement = con.prepareStatement(FIND_BY_ID);
			statement.setInt(1, Integer.parseInt(id));
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public void updateUser(UserModel user) {
		try {
			statement = con.prepareStatement(UPDATE_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setInt(4, user.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createUser(UserModel user) {

		try {
			statement = con.prepareStatement(CREATE_USER);

			statement.setString(1, user.getEmail());
			statement.setString(2, KryptoPassword.encrypt(user.getPassword()));
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getFirstName());
			statement.setString(5, user.getLastName());
			statement.setString(6, user.getRole());

			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ResultSet getAll(Integer page, String sortQuery) {
String query = String.format("SELECT id, email, pass, first_name, last_name, role FROM user %s LIMIT ?,?;",sortQuery);
		try {
			statement = con.prepareStatement(query);
			statement.setInt(1, page * 5);
			statement.setInt(2, 5);
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
