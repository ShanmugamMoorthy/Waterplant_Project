package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.we5.waterplant.javaclass.ExpenseDetails;

public class ExpenseDAO {

	public boolean insertExpense(ExpenseDetails expenseDetails) {
		boolean isInsert = false;
		Connection conn = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			// conn = DriverManager.getConnection(dbURL, user, pass);
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_expense (expenseType,Quantity,Amount,Date_of_creation) values ('"
					+ expenseDetails.getExpense_Type()
					+ "','"
					+ expenseDetails.getQuantity()
					+ "','"
					+ expenseDetails.getPrice()
					+ "','"
					+ expenseDetails.getDate() + "')";
			int id = st.executeUpdate(sql);
			isInsert = true;
		} catch (SQLException ex) {
			isInsert = false;
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			isInsert = false;
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
		return isInsert;
	}

	public List<ExpenseDetails> getExpenseDetails() {
		List<ExpenseDetails> expenseList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_expense";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int expenseId = rs.getInt("expenseId");
				String expenseType = rs.getString("expenseType");
				Integer quantity = rs.getInt("Quantity");
				Integer price = rs.getInt("Amount");
				Timestamp cts = rs.getTimestamp("Date_of_creation");
				ExpenseDetails expenseDetails = new ExpenseDetails(expenseType,
						quantity, price, cts.toString());
				expenseDetails.setExpense_Type(expenseType);
				expenseDetails.setQuantity(quantity);
				expenseDetails.setPrice(price);
				expenseDetails.setDate(cts.toString());
				expenseDetails.setExpenseId(expenseId);
				// expenseDetails.setExpenseCategory(expenseType);
				expenseList.add(expenseDetails);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		return expenseList;
	}
 
	public boolean deleteExpenseById(int expenseId) {
		boolean isDelete=false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			String sql = "delete  from waterplant_expense where expenseId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, expenseId);
		    stmt.executeUpdate();
		    isDelete=true;
		} catch (SQLException ex) {
			isDelete=false;
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			isDelete=false;
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
		return isDelete;
	}

	
	public int updateExpenseById(int expenseID,String expenseType, int quantity, Double price) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DBConnection.getConnection();
			String sql = "UPDATE waterplant_expense set expenseType='" + expenseType + "',Quantity='" + quantity
					+ "',Amount='" + price.floatValue() +"where expenseId=?";
			
			System.out.println("sql :::"+sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, expenseID);
			id = stmt.executeUpdate(sql);
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
		return id;
	}
}
