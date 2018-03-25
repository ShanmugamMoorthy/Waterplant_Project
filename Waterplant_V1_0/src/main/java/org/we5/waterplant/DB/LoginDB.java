package org.we5.waterplant.DB;

import java.sql.*;

public class LoginDB {
	

	public String loginCheck(String username, String password) {
		Connection conn = null;
		int role = 0; 
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL,user,pass);
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "Select * from waterplant_user where UserName='" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("UserPassword").equals(password)) {
					role = Integer.parseInt(rs.getString("Role_id"));
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (role == 1) {
			return "Admin";

		} else if (role == 2) {
			return "Employee";

		} else {
			return "Invalid";
		}
	}
}