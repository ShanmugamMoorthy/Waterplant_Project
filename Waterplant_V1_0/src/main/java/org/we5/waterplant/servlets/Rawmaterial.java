package org.we5.waterplant.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.we5.waterplant.DB.RawmaterialDB;
import org.we5.waterplant.DB.UsedRawMaterialDB;
import org.we5.waterplant.DB.VendorDB;
import org.we5.waterplant.exception.WaterPlantException;
import org.we5.waterplant.javaclass.Product;

import com.google.gson.Gson;

/**
 * Servlet implementation class Rawmaterial
 */
@WebServlet("/Rawmaterial")
public class Rawmaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Rawmaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	List<List<String>> listall = new ArrayList();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher send = request.getRequestDispatcher("Rawmaterial.jsp");
		send.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("pageload") != null) {

			if (request.getParameter("pageload").equals("getContent")) {
				RawmaterialDB obj = new RawmaterialDB();
				List<List<String>> list = obj.getProduct();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(list, response.getWriter());

			} else if (request.getParameter("pageload").equals("vendordropdown")) {
				VendorDB objDB = new VendorDB();
				List<List<String>> result = objDB.getVendornames();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(result, response.getWriter());

			} else if (request.getParameter("pageload").equals("dropdown")) {
				ArrayList<String> droplist = new ArrayList<String>();
				droplist.add("Chlorine");
				droplist.add("Ph-booster");
				droplist.add("Anti-Scale Chemical");
				droplist.add("Sleeve");
				droplist.add("Cap");
				droplist.add("Bottle");
				droplist.add("Cardboard");
				droplist.add("Tape");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(droplist, response.getWriter());

			}else if (request.getParameter("pageload").equals("editcontent")) {
				int id = Integer.parseInt(request.getParameter("rowid"));
				System.out.println(id);
				RawmaterialDB obj = new RawmaterialDB();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(obj.getProductbyID(id), response.getWriter());

			} else if (request.getParameter("pageload").equals("addedit")) {
				String Vend_ID, Prod_Name, Prod_Desc;
				int Quantity;
				float Price;
				int ProdID = Integer.parseInt(request.getParameter("rowid"));
				Vend_ID = request.getParameter("vendorname");
				Prod_Name = request.getParameter("dropdown");
				Prod_Desc = request.getParameter("editdesc");
				Quantity = Integer.parseInt(request.getParameter("quantity"));
				Price = Float.parseFloat(request.getParameter("price"));
				Price = Math.round((Price * 100) / 100);
				RawmaterialDB obj = new RawmaterialDB();
				int result = obj.updateProductbyID(ProdID, Vend_ID, Prod_Name, Prod_Desc, Quantity, Price);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(Integer.toString(result));

			} else if (request.getParameter("pageload").equals("delete")) {
				int ProdID = Integer.parseInt(request.getParameter("rowid"));
				RawmaterialDB obj = new RawmaterialDB();
				int result = obj.deleteProductbyID(ProdID);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(Integer.toString(result));

			} else if (request.getParameter("pageload").equals("addnew")) {
				String Vend_ID, Prod_Name, Prod_Desc;
				int Quantity;
				float Price;
				Vend_ID = request.getParameter("vendordrop");
				Prod_Name = request.getParameter("dropdown");
				Prod_Desc = request.getParameter("desc");
				Quantity = Integer.parseInt(request.getParameter("quantity"));
				Price = Float.parseFloat(request.getParameter("price"));
				Price = Math.round((Price * 100) / 100);
				RawmaterialDB obj = new RawmaterialDB();
				List<Product> productList=new ArrayList<Product>();
				productList=obj.getProductList();
					for (Product product : productList) {
					try {
						if (product.getProd_Name().equalsIgnoreCase(Prod_Name)) {
							product.setErrorMessage("Product name alreay exists");
							throw new WaterPlantException("Product name alreay exists");
						}
					} catch(WaterPlantException ex) {
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						new Gson().toJson(product, response.getWriter());
						throw new WaterPlantException("Product name alreay exists");
					}
					}
				int result = obj.addProduct(Vend_ID, Prod_Name, Prod_Desc, Quantity, Price);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(Integer.toString(result));
			} else if (request.getParameter("pageload").equals("addUsedRawMaterial")) {
				int usedstockid=Integer.parseInt(request.getParameter("usedstockid"));
				String usedmetrial=request.getParameter("usedmetrial");
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				int productid=Integer.parseInt(request.getParameter("productid"));
				UsedRawMaterialDB usedRawMaterialDB=new  UsedRawMaterialDB();
				int result=usedRawMaterialDB.addUsedMaterial(usedstockid, usedmetrial, quantity, productid);
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(Integer.toString(result));
			} else if (request.getParameter("pageload").equals("getUsedRawMaterials")) {
				UsedRawMaterialDB usedRawMaterialDB=new  UsedRawMaterialDB();
				List<List<String>> list = usedRawMaterialDB.getUsedMaterials();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				new Gson().toJson(list, response.getWriter());
			} 
		}
	}
}
