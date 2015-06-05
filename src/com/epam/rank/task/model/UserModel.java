package com.epam.rank.task.model;

public class UserModel {

	private Integer id;

	private String email;
	private String password;

	private String firstName;
	private String lastName;
	private String role;

	public UserModel() {
		role = "user";
	}

	public UserModel(String email, String password, String firstName,
			String lastName) {
		this();

		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", role=" + role + "]";
	}

}
