package org.we5.waterplant.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.we5.waterplant.javaclass.Product;
import org.we5.waterplant.javaclass.Vendor;

public class RawmaterialDB {
	

	public List<List<String>> getVendornames() {
		Connection conn = null;
		ResultSet rs = null;
		List<List<String>> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select VendID,Name from waterplant_vendor";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				List<String> templist = new ArrayList<>();
				// System.out.println(rs.getString("VendID"));
				// System.out.println(rs.getString("Name"));
				templist.add(rs.getString("VendID"));
				templist.add(rs.getString("Name"));
				list.add(templist);
			}

			// for (int i=0;i<templist.size();i++){
			//
			// }

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
	
	
	public List<Vendor> getVendorList() {
		Connection conn = null;
		ResultSet rs = null;
		List<Vendor> vendorList = new ArrayList<Vendor>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select VendID,Name,company,email,mobilenumber from waterplant_vendor";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				
				Vendor vendor=new Vendor();
				vendor.setCompany(rs.getString("company"));
				vendor.setEmail(rs.getString("email"));
				vendor.setMobileNumber(rs.getString("mobileNumber"));
				vendor.setName(rs.getString("name"));
//				vendor.setVendorId(Integer.parseInt(rs.getInt("vendorId")));
				vendorList.add(vendor);
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
		return vendorList;
	}


	public int newVendor(String vendor, String company, String email, String mobilenumber) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_vendor (Name,Company,Email,MobileNumber) values ('" + vendor + "','" + company + "','" + email + "','"+ mobilenumber + "')";
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

	public int addProduct(String Vend_ID, String Prod_Name, String Prod_Desc, int Quantity, float Price) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "insert into waterplant_product (Vend_ID,Prod_Name,Product_Desc,Quantity,Price) values ("
					+ Vend_ID + ",'" + Prod_Name + "','" + Prod_Desc + "'," + Quantity + "," + Price + ")";
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

	public List<List<String>> getProduct() {
		Connection conn = null;
		ResultSet rs = null;
		List<List<String>> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_product";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				List<String> templist = new ArrayList<>();
				templist.add(rs.getString("ProdID"));
				templist.add(rs.getString("Vend_ID"));
				templist.add(rs.getString("Prod_Name"));
				templist.add(rs.getString("Product_Desc"));
				templist.add(rs.getString("Quantity"));
				templist.add(rs.getString("Price"));
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
	
	public List<Product> getProductList() {
		Connection conn = null;
		ResultSet rs = null;
		List<Product> productList=new ArrayList<Product>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_product";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Product product=new Product();
				List<String> templist = new ArrayList<>();
				product.setProdId(Integer.parseInt(rs.getString("ProdID")));
				product.setVend_Id(Integer.parseInt(rs.getString("Vend_ID")));
				product.setProd_Name(rs.getString("Prod_Name"));
				product.setProduct_desc(rs.getString("Product_Desc"));
				product.setQuantity(Integer.parseInt(rs.getString("Quantity")));
				product.setPrice(Integer.parseInt(rs.getString("Price")));
				productList.add(product);
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
		return productList;
	}



	public List<String> getProductbyID(int id) {
		Connection conn = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from waterplant_product where ProdID='" + id + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("ProdID"));
				list.add(rs.getString("Vend_ID"));
				list.add(rs.getString("Prod_Name"));
				list.add(rs.getString("Product_Desc"));
				list.add(rs.getString("Quantity"));
				list.add(rs.getString("Price"));
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

	public int updateProductbyID(int ProdID, String Vend_ID, String Prod_Name, String Prod_Desc, int Quantity,
			float Price) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "UPDATE waterplant_product set Vend_ID='" + Vend_ID + "',Prod_Name='" + Prod_Name
					+ "',Product_Desc='" + Prod_Desc + "',Quantity='" + Quantity + "',Price='" + Price
					+ "' where ProdID='" + ProdID + "'";
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

	public int deleteProductbyID(int ProdID) {
		int id = 0;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DBConnection.getConnection();
			Statement st = conn.createStatement();
			String sql = "DELETE from waterplant_product where ProdID='" + ProdID + "'";
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
}
