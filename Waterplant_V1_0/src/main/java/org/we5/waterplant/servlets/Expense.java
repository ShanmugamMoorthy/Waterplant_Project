package org.we5.waterplant.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.we5.waterplant.DB.ExpenseDAO;
import org.we5.waterplant.javaclass.ExpenseDetails;

import com.google.gson.Gson;

/**
 * Servlet implementation class Expense
 */
@WebServlet("/Expense")
public class Expense extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<ExpenseDetails> Expenselist = new ArrayList<ExpenseDetails>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ExpenseDAO expenseDAO = new ExpenseDAO();
		RequestDispatcher send = request.getRequestDispatcher("Expense.jsp");
		send.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		System.out.println("expense sb ===" + sb);
		JSONObject jObj = new JSONObject(sb.toString());

		// TODO Auto-generated method stub
		if (jObj.optString("submit") != null
				&& request.getParameter("submit") != null) {
			System.out.println("home");
			request.setAttribute("Expenselist", Expenselist);
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		} else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType").equalsIgnoreCase(
						"ExpenseTypeDropDown")) {
			ArrayList<String> ExpenseType = new ArrayList<String>();
			ExpenseType.add("Labout");
			ExpenseType.add("Raw Materials");
			ExpenseType.add("Transport");
			ExpenseType.add("Electricity");
			ExpenseType.add("Generator");
			ExpenseType.add("Rent");
			ExpenseType.add("Maintanance");
			ExpenseType.add("MISC");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(ExpenseType, response.getWriter());

		} else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType")
						.equalsIgnoreCase("expenseLoad")) {
			ExpenseDAO expenseDAO = new ExpenseDAO();
			List<ExpenseDetails> expenseList = expenseDAO.getExpenseDetails();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(expenseList, response.getWriter());

		} else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType").equalsIgnoreCase("Add")) {
			System.out.println("Add Expense invoked...");
			String ExpenseType = jObj.optString("selectedDropItem");
			int Quantity = Integer.parseInt(jObj.optString("Quantity"));
			double Price = Double.parseDouble(jObj.optString("Price"));
			String curDate = jObj.optString("Date");
			// Date dt = new Date(curDate);
			/*
			 * System.out.println("today: "+dt); SimpleDateFormat format = new
			 * SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); String DateToStr =
			 * format.format(curDate);
			 * System.out.println("DateToStr ==="+DateToStr);
			 */

			ExpenseDetails obj = new ExpenseDetails(ExpenseType, Quantity,
					Price, curDate);
			ExpenseDAO expenseDAO = new ExpenseDAO();
			boolean isInsert = expenseDAO.insertExpense(obj);
			Expenselist.add(obj);
			request.setAttribute("Expenselist", Expenselist);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(Expenselist, response.getWriter());
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		} else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType").equalsIgnoreCase("Delete")) {
			int expenseId = Integer.parseInt(jObj.optString("expenseId"));
			ExpenseDAO expenseDAO = new ExpenseDAO();
			boolean isDelete = expenseDAO.deleteExpenseById(expenseId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(isDelete, response.getWriter());
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		}else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType").equalsIgnoreCase("Edit")) {
			ExpenseDAO expenseDAO = new ExpenseDAO();
			
			System.out.println("expenseId ::"+jObj.optString("expenseId"));
			int expenseId = Integer.parseInt(jObj.optString("expenseId"));
			String ExpenseType = jObj.optString("selectedDropItem");
			int quantity = Integer.parseInt(jObj.optString("Quantity"));
			double price = Double.parseDouble(jObj.optString("Price"));
//			String curDate = jObj.optString("Date");
			int updateValue=expenseDAO.updateExpenseById(expenseId, ExpenseType, quantity, price);
			new Gson().toJson(updateValue, response.getWriter());
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		}else if (jObj.optString("ExpenseType") != null
				&& jObj.optString("ExpenseType").equalsIgnoreCase("Delete")) {
			int expenseId = Integer.parseInt(jObj.optString("expenseId"));
			ExpenseDAO expenseDAO = new ExpenseDAO();
			boolean isDelete = expenseDAO.deleteExpenseById(expenseId);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			new Gson().toJson(isDelete, response.getWriter());
			RequestDispatcher send = request
					.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		}
	}
}
