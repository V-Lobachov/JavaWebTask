package com.epam.rank.task.model;

import java.sql.Timestamp;


public class AccountHistoryModel {

	private Integer id;
	private String message;
	private Integer accout_id;
	private Integer user_id;
	private Timestamp created_at;
	private String account; 

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public AccountHistoryModel() {
		// TODO Auto-generated constructor stub
	}

	public AccountHistoryModel(String message, Integer accout_id) {
		super();
		this.message = message;
		this.accout_id = accout_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getAccout_id() {
		return accout_id;
	}

	public void setAccout_id(Integer accout_id) {
		this.accout_id = accout_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}



}
