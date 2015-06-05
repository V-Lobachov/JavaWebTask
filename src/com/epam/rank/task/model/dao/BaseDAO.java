package com.epam.rank.task.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.rank.task.model.connection.ConnectionMySql;

public class BaseDAO {

	protected Connection con = ConnectionMySql.getConnection();
	protected ResultSet result;
	protected PreparedStatement statement;

	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
