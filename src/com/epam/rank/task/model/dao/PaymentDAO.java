package com.epam.rank.task.model.dao;

import java.sql.SQLException;

import com.epam.rank.task.model.PaymentModel;

public class PaymentDAO extends BaseDAO {
	private static final String NEW_PAYMENT = "INSERT INTO payment (amount, destinaion, card_id)  VALUE (?,?,?);";
	private static final String MAKE_PAY = ""
			+ "UPDATE bank_account SET balance = balance - ? "
			+ "WHERE id = (SELECT account_id FROM credit_card WHERE id = ?);";

	public PaymentDAO() {

	}

	public void newPayment(PaymentModel payment) {
		try {
			con.setAutoCommit(false);
			statement = con.prepareStatement(NEW_PAYMENT);
			statement.setFloat(1, payment.getAmount());
			statement.setString(2, payment.getDestination());
			statement.setInt(3, payment.getCard_id());

			statement.executeUpdate();
			statement = con.prepareStatement(MAKE_PAY);
			statement.setFloat(1, payment.getAmount());
			statement.setInt(2, payment.getCard_id());
			statement.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
