package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.ManagerBusinessService;
import com.service.iface.ManagerBusinessServiceIface;

public class ManagerBusinessServlet extends HttpServlet {

	private ManagerBusinessServiceIface managerBusinessService=null;
	/**
	 * Constructor of the object.
	 */
	public ManagerBusinessServlet() {
		super();
		managerBusinessService=new ManagerBusinessService();
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
		
		String flag=request.getParameter("flag");
		
		HttpSession session = request.getSession();
		Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
		String tel_numb=(String)map.get("mobile");
		
		//开通业务
		if("addBusiness".equals(flag)){
			String bussinessName= request.getParameter("bussinessName");
			bussinessName=new String(bussinessName.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(bussinessName);
			
			//查询编号
			int business_id=managerBusinessService.QueryBusinessId(bussinessName);
			System.out.println(business_id);
			//调用存储过程
			managerBusinessService.addBusiness(tel_numb, business_id);
			
			request.getRequestDispatcher("QueryBusinessServlet").forward(request, response);
			
			
		}
		
		
		//关闭业务
		else if("closeBusiness".equals(flag)){
			int bussinessId=Integer.parseInt(request.getParameter("bussinessId"));
			System.out.println(bussinessId+"...........................");
			
			//调用service
			boolean result=managerBusinessService.closeBusiness(tel_numb, bussinessId);
			
			request.getRequestDispatcher("QueryBusinessServlet").forward(request, response);
			
			
			
			
		}
		
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
