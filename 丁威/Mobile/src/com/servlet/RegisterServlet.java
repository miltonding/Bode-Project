package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domian.Customer;
import com.service.RegisterService;
import com.service.iface.RegisterServiceIface;

public class RegisterServlet extends HttpServlet {

	private RegisterServiceIface registerService=null;
	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
		super();
		registerService=new RegisterService();
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
		
		//客户端 ajax验证
		//验证username重复
		if(flag.equals("ajaxusername")){
			String username=request.getParameter("username");
			//调用service
			boolean result=registerService.countUser(username);
			out.print(result);
			
		}
		
		//验证手机号码是否可以注册账号
		if(flag.equals("ajaxmobile")){
			String mobile=request.getParameter("mobile");
			//调用service
			String result=registerService.isSale(mobile);
			System.out.println("手机号码状态");
			System.out.println(result);
			out.print(result);
			
		}
		
		//servlet处理业务
		//flag=formmessage
		if(flag.equals("formmessage")){
			String username=request.getParameter("username");
			String dname=request.getParameter("dname");
			String mobile=request.getParameter("mobile");
			String idNumber=request.getParameter("idNumber");
			String birth=request.getParameter("birth");
			String pwd=request.getParameter("pwd");
			Customer customer=new Customer(username,dname,idNumber,mobile,birth,pwd);
			boolean result=registerService.saveMsg(customer);
			
			//插入成功
			if(result==true){
				request.setAttribute("register", "注册");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				
			}
			
			
			
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
