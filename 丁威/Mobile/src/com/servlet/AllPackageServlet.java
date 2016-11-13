package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domian.Package;
import com.service.AllPackageService;
import com.service.iface.AllPackageServiceIface;

public class AllPackageServlet extends HttpServlet {

	private AllPackageServiceIface allPackageService=null;
	/**
	 * Constructor of the object.
	 */
	public AllPackageServlet() {
		super();
		allPackageService=new AllPackageService();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throwsw IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		Map<String, List> businessList=allPackageService.selectAllBusiness();

		
		//获取参数
		String tel_numb=request.getParameter("tel_numb");
		double init_account=Double.parseDouble(request.getParameter("init_account"));
		String tel_add=request.getParameter("tel_add");
		tel_add=new String(tel_add.getBytes("ISO-8859-1"),"UTF-8");
		
		request.setAttribute("tel_numb", tel_numb);
		request.setAttribute("init_account", init_account);
		request.setAttribute("tel_add", tel_add);
		request.setAttribute("businessList", businessList);
		
		request.getRequestDispatcher("/numericalSelection/select_number_bysetmeal.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
