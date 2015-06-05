package com.epam.rank.task.model;

public class PaymentModel {
	private Integer id;
	private Float amount;
	private String destination;
	private Integer card_id;
	
	public PaymentModel() {
		// TODO Auto-generated constructor stub
	}

	public PaymentModel(Float amount, String destination, Integer card_id) {
		super();
		this.amount = amount;
		this.destination = destination;
		this.card_id = card_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getCard_id() {
		return card_id;
	}

	public void setCard_id(Integer card_id) {
		this.card_id = card_id;
	}
	
	
}
