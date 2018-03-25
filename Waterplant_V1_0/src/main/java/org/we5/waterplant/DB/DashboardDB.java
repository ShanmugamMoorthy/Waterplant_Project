package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.we5.waterplant.javaclass.ExpenseDetails;
import org.we5.waterplant.javaclass.GetRawmaterial;
import org.we5.waterplant.javaclass.PaymentDetails;
import org.we5.waterplant.javaclass.RawMaterialVo;

import com.mysql.jdbc.Statement;

public class DashboardDB {

	public List<GetRawmaterial> getRawmaterialData() {
		List<GetRawmaterial> templist = new ArrayList<>();
		// List<List<String>> list = new ArrayList<>();
		JSONObject main = new JSONObject();
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		Connection conn = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL, user, pass);
			conn=DBConnection.getConnection();
			Statement st = (Statement) conn.createStatement();
			String sql = "Select Prod_Name,Quantity from WaterPlant_Product";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				JSONObject element = new JSONObject();
				element.put("data", rs.getString("Quantity"));
				element.put("label", rs.getString("Prod_Name"));
				items.add(element);
				main.put("data", new JSONArray(items));
				GetRawmaterial rawMaterials = new GetRawmaterial();
				rawMaterials.setProd_name(rs.getString("Prod_Name"));
				rawMaterials.setQuantity(Integer.parseInt(rs.getString("Quantity")));
				templist.add(rawMaterials);
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
		return templist;
	}



	
	public List<ExpenseDetails> getExpenseDetailsWithTimestamp(java.sql.Date currentTimeStamp) {
		List<ExpenseDetails> expenseList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL, user, pass);
			conn=DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select Quantity,Amount from WaterPlant_expense where DATE(Date_of_creation)=?");
			statement.setDate(1, currentTimeStamp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ExpenseDetails expenseDetails = new ExpenseDetails();
				Integer quantity = rs.getInt("Quantity");
				Integer price = rs.getInt("Amount");
				expenseDetails.setQuantity(quantity);
				expenseDetails.setPrice(price);
				expenseList.add(expenseDetails);
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
		return expenseList;
	}
	
	public List<RawMaterialVo> getRawMaterialsWithFromToTimestamp(java.sql.Date currentTimeStamp) {
		List<RawMaterialVo> rawMaterialVoList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select * from WaterPlant_product where DATE(Date_of_creation)=?");
			statement.setDate(1, currentTimeStamp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				RawMaterialVo rawMaterialVo = new RawMaterialVo();
				Integer quantity = rs.getInt("Quantity");
				Integer price = rs.getInt("Price");
				rawMaterialVo.setQuantity(quantity);
				rawMaterialVo.setPrice(price);
				rawMaterialVo.setDateOfCreation(rs.getDate("Date_of_Creation"));
				rawMaterialVo.setProdId(rs.getInt("ProdID"));
				rawMaterialVo.setVendId(rs.getInt("Vend_ID"));
				rawMaterialVoList.add(rawMaterialVo);
			}
			System.out.println("rawMaterialVoList ====>"+rawMaterialVoList.toString());
			
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
		return rawMaterialVoList;
	}

	
	
	public List<RawMaterialVo> getRawMaterialsWithFromToTimestamp(java.sql.Date fromTimeStamp,java.sql.Date toTimeStamp) {
		List<RawMaterialVo> rawMaterialVoList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select * from WaterPlant_product where DATE(Date_of_creation) between ? and ?");
			statement.setDate(1, fromTimeStamp);
			statement.setDate(2, toTimeStamp);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				RawMaterialVo rawMaterialVo = new RawMaterialVo();
				Integer quantity = rs.getInt("Quantity");
				Integer price = rs.getInt("Price");
				rawMaterialVo.setQuantity(quantity);
				rawMaterialVo.setPrice(price);
				rawMaterialVo.setDateOfCreation(rs.getDate("Date_of_Creation"));
				rawMaterialVo.setProdId(rs.getInt("ProdID"));
				rawMaterialVo.setVendId(rs.getInt("Vend_ID"));
				rawMaterialVoList.add(rawMaterialVo);
			}
			
			System.out.println("rawMaterialVoList 1 ====>"+rawMaterialVoList.toString());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rawMaterialVoList;
	}
	
	
	public List<ExpenseDetails> getExpenseDetailsWithFromToTimestamp(java.sql.Date fromTimeStamp,java.sql.Date toTimeStamp) {
		List<ExpenseDetails> expenseList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select Quantity,Amount from WaterPlant_expense where where DATE(Date_of_creation) between ? and ?");
			statement.setDate(1, fromTimeStamp);
			statement.setDate(2, toTimeStamp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ExpenseDetails expenseDetails = new ExpenseDetails();
				Integer quantity = rs.getInt("Quantity");
				Integer price = rs.getInt("Amount");
				expenseDetails.setQuantity(quantity);
				expenseDetails.setPrice(price);
				expenseList.add(expenseDetails);
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
		return expenseList;
	}
	
	
	
	public List<PaymentDetails> getPaymentDetailsWithTimestamp(java.sql.Date currentTimeStamp) {
		List<PaymentDetails> paymentList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select amount,payment_Date  from WaterPlant_payment where DATE(payment_Date)=?");
			statement.setDate(1, currentTimeStamp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) { 
				PaymentDetails paymentDetails = new PaymentDetails();
				Integer amount = rs.getInt("Amount");
				paymentDetails.setAmount(amount);
				paymentList.add(paymentDetails);
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
		return paymentList;
	}
	
	
	
	public List<PaymentDetails> getPaymentDetailsWithFromToTimestamp(java.sql.Date fromTimeStamp,java.sql.Date toTimeStamp) {
		List<PaymentDetails> paymentList = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("Select amount,payment_Date  from WaterPlant_payment where DATE(payment_Date) between ? and ?");
			statement.setDate(1, fromTimeStamp);
			statement.setDate(2, toTimeStamp);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) { 
				PaymentDetails paymentDetails = new PaymentDetails();
				Integer amount = rs.getInt("Amount");
				paymentDetails.setAmount(amount);
				paymentList.add(paymentDetails);
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
		return paymentList;
	}
}
