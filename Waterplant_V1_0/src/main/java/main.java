import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		ArrayList<String> Drop=null;
		Connection conn = null;
		try {
			String dbURL = "jdbc:jtds:sqlserver://localhost;databaseName=WaterPlant;integratedSecurity=true;";
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
		
	ArrayList<String> nu=Drop;

		// TODO Auto-generated method stub

	}

}
