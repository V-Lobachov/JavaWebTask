package com.epam.rank.task.helper;

import javax.servlet.http.HttpServletRequest;

import com.epam.rank.task.model.PaymentModel;

public class PaymentHalper {
	public static PaymentModel parsePayment(HttpServletRequest request, Integer ccId) {
		PaymentModel payment = new PaymentModel();

		payment.setDestination(request.getParameter("bill"));
		try{
		payment.setAmount(Float.parseFloat(request.getParameter("amount")));
		}catch(NumberFormatException e){
			payment.setAmount(0.23123f);
		}
		payment.setCard_id(ccId);

		return payment;
	}
}
