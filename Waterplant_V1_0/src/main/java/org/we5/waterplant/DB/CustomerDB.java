package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.we5.waterplant.javaclass.CustomerVo;

import com.mysql.jdbc.PreparedStatement;

public class CustomerDB {
	
	public List<CustomerVo> getCustomer() {
		int id = 0;
		Connection conn = null;
		List<CustomerVo> customerList=new ArrayList<CustomerVo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "Select * from waterplant_customer";
			ResultSet rs = st.executeQuery(sql);
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

	public int addCustomer(String name, String company, String email, String mobileNumber, int credit) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_customer (Name,Company,Email,MobileNumber,Credit) values ('" + name
					+ "','" + company + "','" + email + "','" + mobileNumber + "','" + credit + "')";
			id = st.executeUpdate(sql);
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

	public int updateCustomerById(int custId,String name, String company, String email, String mobileNumber, int credit) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn =DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "UPDATE waterplant_customer set " +  "Name='" + name
					+ "',Company='" + company + "',Email='" + email + "',MobileNumber='" + mobileNumber+"',Credit=" + credit+ " where CustID=" + custId;
			
			System.out.println("sql ::::"+sql);
			id = st.executeUpdate(sql);
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
	
	public boolean deleteCustomerById(int custId) {
		boolean isDelete=false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			String sql = "delete from waterplant_customer where custID="+custId;
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
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
	
}