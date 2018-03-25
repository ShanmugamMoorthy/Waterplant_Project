package org.we5.waterplant.DAO;

import org.we5.waterplant.javaclass.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BillingDAO {

	String dbURL = "jdbc:jtds:sqlserver://localhost;databaseName=WaterPlant;integratedSecurity=true;";
	GetBilling Bill=new GetBilling();
	
	/*public void BillingAdd() {
		boolean record = false;
		Connection conn = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL);
			Statement st = conn.createStatement();
			String sql = "Select * from WaterPlant_User where UserName='" + username + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("UserPassword").equals(password)) {
					record = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}


}
*/	public ArrayList<String> BillingDrop() {
		ArrayList<String> Drop=null;
		Connection conn = null;
		try {
			
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL);
			Statement st = conn.createStatement();
			String sql = "Select ProdID,Prod_Name,Price from WaterPlant_Product ";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("Quantity").equals(0)) {
					
				}else {
					
					Drop.add(rs.getString("ProdID"));
					Drop.add(rs.getString("Prod_Name"));
					Drop.add(rs.getString("Price"));
					
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return Drop;


}

}
