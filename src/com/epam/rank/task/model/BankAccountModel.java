package com.epam.rank.task.model;

public class BankAccountModel {

	private Integer id;
	private String account;
	private Float balance;
	private Integer user_id;
	private Boolean active;

	public BankAccountModel() {
	}

	public BankAccountModel(String account, Float balance, Integer user_id) {
		super();
		this.account = account;
		this.balance = balance;
		this.user_id = user_id;
	}

	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
