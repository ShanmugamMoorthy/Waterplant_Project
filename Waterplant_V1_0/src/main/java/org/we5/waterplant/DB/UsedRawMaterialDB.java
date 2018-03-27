package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsedRawMaterialDB {
	
	public int addUsedMaterial(int usedstockid, String usedmetrial,  int Quantity, int productId) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_usedstocks (usedstockid,usedmetrial,Quantity,productId) values ("
					+ usedstockid + ",'" + usedmetrial + "'," + Quantity + "," + productId + ")";
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
	
	public List<List<String>> getUsedMaterials() {
		Connection conn = null;
		ResultSet rs = null;
		List<List<String>> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_usedstocks";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				List<String> templist = new ArrayList<>();
				templist.add(rs.getString("usedstockid"));
				templist.add(rs.getString("usedmetrial"));
				templist.add(rs.getString("quantity"));
				templist.add(rs.getString("productid"));
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
}
