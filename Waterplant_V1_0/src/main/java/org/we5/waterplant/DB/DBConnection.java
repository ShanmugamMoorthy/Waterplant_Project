package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static String dbURL = "jdbc:mysql://localhost:3306/waterplant1";
	static String user = "root";
	static String pass = "admin@123";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, user, pass);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;

	}

}
