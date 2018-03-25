package org.we5.waterplant.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.we5.waterplant.DB.CustomerDB;
import org.we5.waterplant.javaclass.CustomerVo;

import com.google.gson.Gson;


@WebServlet("/Customer")
public class Customer extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	     RequestDispatcher send = request.getRequestDispatcher("Customer.jsp");
		 		send.forward(request, response);

	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	System.out.println("customer service");
    	
    	BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("customer sb ==="+sb);
        JSONObject jObj = new JSONObject(sb.toString());
    
    	
    	if (jObj.getString("pageload").equals("getcustomer")) {
    		List<CustomerVo> customerList=new ArrayList<CustomerVo>();
			CustomerDB customerDB=new CustomerDB();
			customerList=customerDB.getCustomer();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(customerList, response.getWriter());

		}
    	
    	if (jObj.getString("pageload").equals("addcustomer")) {
			//int custId=Integer.parseInt(jObj.getString("custId"));
			String name=jObj.getString("name");
			String company=jObj.getString("company");
			String email=jObj.getString("email");
			String mobileNumber=jObj.getString("mobileNumber");
			System.out.println("credit ::::"+jObj.getInt("credit"));
			int credit=jObj.getInt("credit");
			CustomerDB customerDB=new CustomerDB();
			int result=customerDB.addCustomer(name, company, email, mobileNumber, credit);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(result));
		}if (jObj.getString("pageload").equals("updatecustomer")) {
			int custId=Integer.parseInt(jObj.getString("code"));
			String name=jObj.getString("name");
			String company=jObj.getString("company");
			String email=jObj.getString("email");
			String mobileNumber=jObj.getString("mobileNumber");
			int credit=jObj.getInt("credit");
			CustomerDB customerDB=new CustomerDB();
			int result=customerDB.updateCustomerById(custId,name, company, email, mobileNumber, credit);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Integer.toString(result));
		}	if (jObj.optString("pageload") != null
				&& jObj.optString("pageload").equalsIgnoreCase("Delete")) {
			int expenseId =jObj.optInt("userId");
			CustomerDB customerDB=new CustomerDB();
			boolean isDelete = customerDB.deleteCustomerById(expenseId);
			System.out.println("isDelete :::"+isDelete);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(isDelete, response.getWriter());
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		}
    }
}
