package org.we5.waterplant.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.we5.waterplant.DB.DashboardDB;
import org.we5.waterplant.javaclass.DashboardVo;
import org.we5.waterplant.javaclass.ExpenseDetails;
import org.we5.waterplant.javaclass.PaymentDetails;
import org.we5.waterplant.javaclass.RawMaterialVo;

import com.google.gson.Gson;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Get Dashboard method invoked");
		RequestDispatcher send = request.getRequestDispatcher("Dashboard.jsp");
		send.forward(request, response);

	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Dashboard Get method invoked");
		RequestDispatcher send = request.getRequestDispatcher("Dashboard.jsp");
		send.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Dashboard POST method invoked");
		DashboardDB objDB = new DashboardDB();
		ArrayList<DashboardVo> dashArrayList=null;
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println("Dashboard sb ==="+sb);
        JSONObject jObj = new JSONObject(sb.toString());
        System.out.println("pageload ::::"+jObj.optString("pageload"));
        
           
			/*if (request.getParameter("pageload").equalsIgnoreCase("Rawmaterial")) {
				List<GetRawmaterial> result = objDB.getRawmaterialData();
				System.out.println(result);
				// for
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(result, response.getWriter());
			}*/
			 if(jObj.optString("pageload") != null && jObj.optString("pageload").equalsIgnoreCase("reportGeneration"))
		        {
		    		List<Integer> amountList = new ArrayList();
		    		
					List<ExpenseDetails> expenseListCurrent = objDB.getExpenseDetailsWithTimestamp(new java.sql.Date(System.currentTimeMillis()));
					
					Integer totalExpense = getTotalExpense(expenseListCurrent);
					
					List<PaymentDetails> paymentDetails = objDB.getPaymentDetailsWithTimestamp(new java.sql.Date(System.currentTimeMillis()));
					
					List<RawMaterialVo> rawMaterialVoList = objDB.getRawMaterialsWithFromToTimestamp(new java.sql.Date(System.currentTimeMillis()));
					System.out.println("rawMaterialVoList ===>"+rawMaterialVoList.size());
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date fromDateVal;
					java.util.Date toDateVal;
					java.sql.Date fromDateVal1 = null;
					java.sql.Date toDateVal1 = null;
						try {
							fromDateVal = sdf1.parse("2018-02-19");
							fromDateVal1 = new java.sql.Date(fromDateVal.getTime());
							toDateVal = sdf1.parse("2018-03-19");
							toDateVal1 = new java.sql.Date(toDateVal.getTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
				
					List<RawMaterialVo> rawMaterialVoList1 = objDB.getRawMaterialsWithFromToTimestamp(fromDateVal1,toDateVal1);
					Integer salesAmount = getPayment(paymentDetails);
					DashboardVo dashboardVo=new DashboardVo();
					dashboardVo.setExpense(totalExpense);
					JSONArray resultantJSONArray = new JSONArray();
					
					
					JSONObject expenseData = new JSONObject();
					expenseData.put("key", "Expense");
					expenseData.put("y", 700);
					
					resultantJSONArray.put(expenseData);
					if(salesAmount-totalExpense>0)
					{
						
						dashboardVo.setProfit(salesAmount-totalExpense);
					}
					else
					{
						dashboardVo.setLoss(salesAmount-totalExpense);
					}
					
					int profit=(int) dashboardVo.getProfit();
					JSONObject profitData = new JSONObject();
					profitData.put("key", "Profit");
					profitData.put("y",700 );
					

					
					JSONObject lossData = new JSONObject();
					lossData.put("key", "Loss");
					lossData.put("y", 0);
					
					JSONObject rawMaterialExpense = new JSONObject();
					rawMaterialExpense.put("key", "RawMetrialExpense");
					rawMaterialExpense.put("y", 100);
					
					JSONObject eletricityExpense = new JSONObject();
					eletricityExpense.put("key", "EletricityExpense");
					eletricityExpense.put("y", 100);
					
					/*amountList.add(totalExpense);
					amountList.add(salesAmount);
					*/
					
					
					resultantJSONArray.put(profitData);
					resultantJSONArray.put(lossData);
					resultantJSONArray.put(rawMaterialExpense);
					resultantJSONArray.put(eletricityExpense);

					
					/*dashArrayList=new ArrayList<DashboardVo>();
					dashArrayList.add(dashboardVo);
					*/
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					
					System.out.println("resultantJSONArray ===="+resultantJSONArray.toString(2));
//					new Gson().toJson(resultantJSONArray, response.getWriter());
					
//					response.setContentType("text/html");
//			        response.getWriter().write(resultantJSONArray.toString());
					
					PrintWriter out = response.getWriter();
					out.print(resultantJSONArray.toString());
					out.flush();
				
			
		        }
		    
			if (jObj.optString("pageload") != null && jObj.optString("pageload").equalsIgnoreCase("reportGenerationDate") && jObj.optString("toDate")!= null
					&& jObj.optString("fromDate") != null) {
				
				
				List<Integer> amountList = new ArrayList();
				String fromDate = jObj.optString("fromDate");
				String toDate = jObj.optString("toDate");
				
				System.out.println(" fromDate :::"+fromDate+" toDate ::"+toDate);
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
				java.util.Date fromDateVal;
				java.util.Date toDateVal;
				java.sql.Date fromDateVal1 = null;
				java.sql.Date toDateVal1 = null;
					try {
						fromDateVal = sdf1.parse(fromDate);
						fromDateVal1 = new java.sql.Date(fromDateVal.getTime());
						toDateVal = sdf1.parse(toDate);
						toDateVal1 = new java.sql.Date(toDateVal.getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				List<ExpenseDetails> expenseList=objDB.getExpenseDetailsWithFromToTimestamp(fromDateVal1, toDateVal1);
				List<PaymentDetails> paymentList = objDB.getPaymentDetailsWithFromToTimestamp(fromDateVal1, toDateVal1);
				Integer totalExpense = getTotalExpense(expenseList);
				Integer salesAmount = getPayment(paymentList);
				
				
				DashboardVo dashboardVo=new DashboardVo();
				dashboardVo.setExpense(totalExpense);
				if(salesAmount-totalExpense>0)
					dashboardVo.setProfit(salesAmount-totalExpense);
				else
					dashboardVo.setLoss(salesAmount-totalExpense);
				
				dashArrayList=new ArrayList<DashboardVo>();
				dashArrayList.add(dashboardVo);
				amountList.add(totalExpense);
				amountList.add(salesAmount);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(dashArrayList, response.getWriter());
			}

	}

	private Integer getPayment(List<PaymentDetails> paymentDetails) {
		Integer payment = 0;
		for (PaymentDetails paymentDetails2 : paymentDetails) {
			Integer amount = paymentDetails2.getAmount();
			payment += amount;
		}
		return payment;
	}

	private Integer getTotalExpense(List<ExpenseDetails> expenseListCurrent) {
		Integer tot = 0;
		for (ExpenseDetails expenseDetails : expenseListCurrent) {
			Double price = expenseDetails.getPrice();
			Integer priceInt = price.intValue();
			Integer quantity = expenseDetails.getQuantity();
			tot += priceInt * quantity;
		}
		return tot;
	}

}
