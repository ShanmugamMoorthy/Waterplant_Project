package org.we5.waterplant.javaclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {
	static String dbURL = "jdbc:sqlserver://localhost";
	static	String user = "RakeshSpark";
	static	String pass = "Rakesh#1234";

public static Connection getConnection() throws SQLException
{
	Connection con = null;
	try  
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(dbURL, user, pass);
	}
	catch (ClassNotFoundException e) 
	{
		System.out.println("OracleDriver not found!");
		e.printStackTrace();
		return null;
	}
	
	try
	{
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","Krish@123");		
	}
	catch (SQLException e) 
	{
		System.out.println("Connection Failed! Check output Consoole");
		e.printStackTrace();
		return null;
	}
	if(con!=null)
	{
		System.out.println("Connection Success!!!");
		return con;
	}
	else
	{
		System.out.println("Connection Failed!!!");
		return null;
	}
	
}
public static void closeConnection(Connection con){
	if(con!=null) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

public static void closeStatement(PreparedStatement ps) {
	if (ps != null) {
	try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}

}
