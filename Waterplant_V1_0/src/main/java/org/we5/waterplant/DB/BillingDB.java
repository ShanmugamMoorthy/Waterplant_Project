package org.we5.waterplant.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.we5.waterplant.javaclass.CustomerVo;

public class BillingDB {


	public List<List<String>> getDetails() {
		Connection conn = null;
		ResultSet rs = null;
		List<List<String>> list = new ArrayList<>();
		int _1ltr_Qty = 0;
		int Chlorine_quantity = 0, ph_quantity = 0, anti_quantity = 0, sleeve_quantity = 0, cap_quantity = 0,
				bottle_quantity = 0, card_quantity = 0, tape_quantity = 0;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(dbURL, user, pass);
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from WaterPlant_Product";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("Prod_Name").equalsIgnoreCase("Chlorine")) {
					Chlorine_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("ph-booster")) {
					ph_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Anti-scale chemical")) {
					anti_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Sleeve")) {
					sleeve_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Cap")) {
					cap_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Bottle")) {
					bottle_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Cardboard")) {
					card_quantity = Integer.parseInt(rs.getString("Quantity"));

				} else if (rs.getString("Prod_Name").equalsIgnoreCase("Tape")) {
					tape_quantity = Integer.parseInt(rs.getString("Quantity"));
				}
			}
			System.out.println(cap_quantity);
			for (int i = 0; i <= bottle_quantity; i--) {
				if (i / 12 == 0) {
					card_quantity -= 1;
					tape_quantity -= 1;
				}
				if (Chlorine_quantity != 0 && anti_quantity != 0 && ph_quantity != 0 && cap_quantity != 0
						&& sleeve_quantity != 0 && card_quantity != 0 && tape_quantity != 0) {
					_1ltr_Qty = _1ltr_Qty + 1;
					Chlorine_quantity -= 1;
					anti_quantity -= 1;
					ph_quantity -= 1;
					cap_quantity -= 1;
					sleeve_quantity -= 1;
				} else {
					break;
				}
			}

			System.out.println(_1ltr_Qty);
			String StockName = "1 Litre";
			int id = 1001;
			Statement new_st = conn.createStatement();
			String new_sql = "select count(*) as Recordcount from WaterPlant_inStock";
			ResultSet new_rs = new_st.executeQuery(new_sql);
			while (new_rs.next()) {
				if (Integer.parseInt(new_rs.getString("Recordcount")) == 0) {
					String new_sql1 = "insert into WaterPlant_inStock (Stock_ProductName,Quantity) values ('"
							+ StockName + "','" + _1ltr_Qty + "')";
					conn.createStatement().executeUpdate(new_sql1);
				} else {

					String new_sql1 = "update WaterPlant_inStock set Quantity='" + _1ltr_Qty + "' where StockID='" + id
							+ "'";
					int res = conn.createStatement().executeUpdate(new_sql1);
					System.out.println(res);
				}
			}
			Statement st1 = conn.createStatement();
			String sql1 = "select * from WaterPlant_inStock";
			ResultSet rs1 = st1.executeQuery(sql1);
			while (rs1.next()) {
				List<String> templist = new ArrayList<>();
				templist.add(rs1.getString("Stock_ProductName"));
				templist.add(rs1.getString("Quantity"));
				list.add(templist);
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
		return list;
	}
	
	public int addBillAmount(int  custId, int paymentType,double grandTotal) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String payment="insert into waterplant_payment (cust_ID,Payment_Type,Amount) values("+custId+","+paymentType+","+grandTotal+")";
			id = st.executeUpdate(payment);
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
		return id;
	}
	
	public Map<String,String> getPaymentTypes() {
				int id = 0;
		Connection conn = null;
		 Map<String,String> paymentTypeMap=new HashMap<String, String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String paymentTypeQuery="select catid,category from waterplant_paymentcategory";
			ResultSet rs = st.executeQuery(paymentTypeQuery);
			while(rs.next())
			{
				paymentTypeMap.put(rs.getString("catid"), rs.getString("category"));
			}
			System.out.println("paymentTypeMap ::::"+paymentTypeMap);
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
		return paymentTypeMap;
	}
	
	public List<CustomerVo> customerName() {
		int id = 0;
		Connection conn = null;
		List<CustomerVo> customerList=new ArrayList<CustomerVo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String customerNameQuery = "select custId,name,company,email,mobilenumber,credit from waterplant_customer";
			ResultSet rs = st.executeQuery(customerNameQuery);
			while (rs.next()) {
				CustomerVo customer=new CustomerVo();
//				customer.setCustId(Integer.parseInt(rs.getString("custId")));
				customer.setCode(rs.getString("custId"));
				customer.setName(rs.getString("name"));
				customer.setcompany(rs.getString("company"));
				customer.setMobileNumber(rs.getString("mobileNumber"));
				customer.setEmail(rs.getString("email"));
				customer.setCredit(Integer.parseInt(rs.getString("credit")));	
				customerList.add(customer);
			}
			System.out.println("customerList ::::" + customerList.toString());
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
		return customerList;
	}


	// public static void main(String[] args) {
	// StockDB obj = new StockDB();
	// List<List<String>> list=obj.getDetails();
	// }
}
