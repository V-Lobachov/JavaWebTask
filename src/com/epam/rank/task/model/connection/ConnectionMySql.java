package com.epam.rank.task.model.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionMySql {

	private static Connection conn = null;
	/*
	 * private static String name = "root"; private static String pass =
	 * "rt6745683"; private static String url =
	 * "jdbc:mysql://localhost:3306/epamTask4";
	 */
	private static InitialContext initContext;
	private static DataSource dataSource;

	private ConnectionMySql() {

	}

	public static Connection getConnection() {
		/*
		 * synchronized (ConnectionMySql.class) { if (conn == null) { init(); }
		 * }
		 */
		init();
		return conn;
	}

	private static void init() {

		try {
			initContext = new InitialContext();
			dataSource = (DataSource) initContext
					.lookup("java:comp/env/jdbc/EpamRankTask4");
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); conn =
		 * DriverManager.getConnection(url, name, pass); } catch (SQLException
		 * e) { e.printStackTrace(); } catch (ClassNotFoundException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}
}
