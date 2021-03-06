package org.we5.waterplant.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.we5.waterplant.javaclass.StockMaterial;

public class StockDB {
	
	
	public List<StockMaterial> getStockMaterials()
	{
		Connection conn = null;
		ArrayList<StockMaterial> stockMaterialsList=new ArrayList<StockMaterial>();
		try {
			conn = DBConnection.getConnection();
			Statement st1 = conn.createStatement();
			String sql1 = "select * from waterplant_instock";
			ResultSet rs1 = st1.executeQuery(sql1);
			while (rs1.next()) {
				StockMaterial stockMaterial=new StockMaterial();
				stockMaterial.setStockId(rs1.getInt("StockID"));
				stockMaterial.setStockName(rs1.getString("Stock_ProductName"));
				stockMaterial.setQuantity(rs1.getInt("Quantity"));
				stockMaterial.setPrice(rs1.getInt("Price"));
				stockMaterial.setTotal(rs1.getDouble("Total"));
				stockMaterialsList.add(stockMaterial);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockMaterialsList;
	}

	public List<List<String>> getDetails() {
		Connection conn = null;
		ResultSet rs = null;
		List<List<String>> list = new ArrayList<>();
		int _1ltr_Qty = 0;
		int Chlorine_quantity = 0, ph_quantity = 0, anti_quantity = 0, sleeve_quantity = 0, cap_quantity = 0,
				bottle_quantity = 0, card_quantity = 0, tape_quantity = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_product";
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
			String new_sql = "select count(*) as Recordcount from waterplant_instock";
			ResultSet new_rs = new_st.executeQuery(new_sql);
			while (new_rs.next()) {
				if (Integer.parseInt(new_rs.getString("Recordcount")) == 0) {
					String new_sql1 = "insert into waterplant_instock (Stock_ProductName,Quantity) values ('"
							+ StockName + "','" + _1ltr_Qty + "')";
					conn.createStatement().executeUpdate(new_sql1);
				} else {

					String new_sql1 = "update waterplant_instock set Quantity='" + _1ltr_Qty + "' where StockID='" + id
							+ "'";
					int res = conn.createStatement().executeUpdate(new_sql1);
					System.out.println(res);
				}
			}
			Statement st1 = conn.createStatement();
			String sql1 = "select * from waterplant_instock";
			ResultSet rs1 = st1.executeQuery(sql1);
			while (rs1.next()) {
				List<String> templist = new ArrayList<>();
				templist.add(rs1.getString("StockID"));
				templist.add(rs1.getString("Stock_ProductName"));
				templist.add(rs1.getString("Quantity"));
				templist.add(rs1.getString("Price"));
				templist.add(rs1.getString("Total"));
				list.add(templist);
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
		return list;
	}
	
	public int addStock(String stockname, int quantity, String date) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_instock (Stock_ProductName, Quantity, Date_of_creation) values ('" + stockname + "',"+ quantity + ",'" + date + "')";
			System.out.println(" sql ::::"+sql);
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

	// public static void main(String[] args) {
	// StockDB obj = new StockDB();
	// List<List<String>> list=obj.getDetails();
	// }
	public int updateStockById(int stockId,String stockname, int quantity, String date){
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			String sql = "update waterplant_instock set Stock_ProductName='" + stockname + "', Quantity='" + quantity +"' where StockID ='"+stockId+"'";
			PreparedStatement stmt = conn.prepareStatement(sql);
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
	
	public boolean deleteStockById(int stockId) {
		boolean isDelete=false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnection.getConnection();
			String sql = "delete  from waterplant_instock where StockID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, stockId);
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
