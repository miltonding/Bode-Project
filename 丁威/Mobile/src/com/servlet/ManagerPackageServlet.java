package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.ManagerPackafeService;
import com.service.iface.ManagerPackageServiceIface;

public class ManagerPackageServlet extends HttpServlet {

	private ManagerPackageServiceIface managerPackageService=null;
	/**
	 * Constructor of the object.
	 */
	public ManagerPackageServlet() {
		super();
		managerPackageService=new ManagerPackafeService();
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
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int pp_id=Integer.parseInt(request.getParameter("pp_id"));
		System.out.println(pp_id);
		
		//获取手机号
		HttpSession session = request.getSession();
		Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
		String tel_numb=(String)map.get("mobile");
		
		//更换套餐
		managerPackageService.changePackage(pp_id, tel_numb);
			
		request.getRequestDispatcher("QueryBusinessServlet").forward(request, response);
		
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
