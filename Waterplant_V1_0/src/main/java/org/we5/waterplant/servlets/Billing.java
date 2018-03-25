package org.we5.waterplant.servlets;

import java.io.BufferedReader;

import org.json.JSONObject;
import org.we5.waterplant.DAO.BillingDAO;
import org.we5.waterplant.DB.BillingDB;
import org.we5.waterplant.javaclass.CustomerVo;
import org.we5.waterplant.javaclass.GetBilling;
import org.we5.waterplant.javaclass.PaymentType;

import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Billing
 */
@WebServlet("/Billing")
public class Billing extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Billing() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
    ArrayList<GetBilling> listall = new ArrayList<GetBilling>();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

	         GetBilling personData = new GetBilling();
	         personData.setCustName("Fname");
	         personData.setPrice(1);   
	         personData.getCustName();
	         personData.getPrice(); 

	         String json = new Gson().toJson(personData);
	         
	         response.setContentType("application/json");
	         response.getWriter().write(json);
	         
	         RequestDispatcher send = request.getRequestDispatcher("Billing.jsp");
	 		send.forward(request, response);

	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<PaymentType> paymentTypeList=new ArrayList<PaymentType>();
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("sb ==="+sb);
        JSONObject jObj = new JSONObject(sb.toString());
        BillingDB billingDB=new BillingDB();
        List<CustomerVo> customerList=new ArrayList<CustomerVo>();
        Map<String,String> customerMap=new HashMap<String, String>();
		if (jObj.optString("parameter") != null){
		if(jObj.getString("parameter").equalsIgnoreCase("Add")) {
			int custId=Integer.parseInt(jObj.getString("Customer"));
			int paymentType=Integer.parseInt(jObj.getString("payment"));
			double grandTotal=Double.parseDouble(jObj.getString("GrandTotal"));
			//to do custid
			billingDB.addBillAmount(custId, paymentType,grandTotal);
			RequestDispatcher send = request.getRequestDispatcher("Billing.jsp");
			send.forward(request, response);
		}else if(jObj.getString("parameter").equalsIgnoreCase("customerName"))
		{
			customerList=billingDB.customerName();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(customerList, response.getWriter());

		}
		else if(jObj.getString("parameter") .equalsIgnoreCase("paymentType"))
		{
			customerMap=billingDB.getPaymentTypes();
			for (Map.Entry<String, String> entry : customerMap.entrySet())
			{
				PaymentType paymentType=new PaymentType();
				paymentType.setId(entry.getKey());
				paymentType.setType(entry.getValue());
				paymentTypeList.add(paymentType);
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(paymentTypeList, response.getWriter());

		}
				 
		
		}
}
}
