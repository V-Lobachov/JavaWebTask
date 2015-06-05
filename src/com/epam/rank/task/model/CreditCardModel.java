package com.epam.rank.task.model;

public class CreditCardModel {
	private Integer id;
	private String card;
	private Integer account_id;
	private Integer user_id;
	private String account;

	public CreditCardModel() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardModel(String card, Integer account_id, Integer user_id) {
		super();
		this.card = card;
		this.account_id = account_id;
		this.user_id = user_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
