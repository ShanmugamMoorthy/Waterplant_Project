package org.we5.waterplant.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Menu
 */
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("RawMaterial")) 
		{
			RequestDispatcher send = request.getRequestDispatcher("Rawmaterial");
			send.forward(request, response);
		}
		else if(action.equals("Stock")) 
		{
			RequestDispatcher send = request.getRequestDispatcher("Stock");
			send.forward(request, response);
		}
		else if(action.equals("Billing")) 
		{
			RequestDispatcher send = request.getRequestDispatcher("Billing.jsp");
			send.forward(request, response);
		}
		else if(action.equals("Expense")) 
		{
			RequestDispatcher send = request.getRequestDispatcher("Expense.jsp");
			send.forward(request, response);
		}
		else if(action.equals("Dashboard")) 
		{
			RequestDispatcher send = request.getRequestDispatcher("Dashboard.jsp");
			send.forward(request, response);
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
