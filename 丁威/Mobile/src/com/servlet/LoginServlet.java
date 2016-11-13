package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domian.Customer;
import com.service.LoginService;
import com.service.iface.LoginServiceIface;

public class LoginServlet extends HttpServlet {

	private LoginServiceIface loginService=null;
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
		loginService=new LoginService();
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
		
		//获取session application
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();
		
		String flag="false";
		//获取
		String username=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		
		Customer customer=new Customer(username,pwd);
		//调用service
		//查出姓名
		String dname=loginService.login(customer);
		
		System.out.println("查出的姓名为");
		System.out.println(dname);
		application.setAttribute("username", username);
		//使用HashSet解决重复登录BUG
		
		HashSet<String> liveCustomer=(HashSet<String>)application.getAttribute("liveCustomer");
		
		//没有在线名单
		if(liveCustomer==null){
			System.out.println("11111111111");
			//创建在线名单
			liveCustomer=new HashSet<String>();
			//验证通过
			if(dname.equals("")==false){
				//存入session
				session.setAttribute("username", username);
				//返回姓名，号码。余额
				Map<String, Object> map=loginService.findAllByID(username);
				String mobile=(String)map.get("mobile");
				double balance=(Double)map.get("balance");
				System.out.println(dname+mobile+balance);
				map.put("dname", dname);
				//存入session
				session.setAttribute("map", map);
				//存入application
				liveCustomer.add(username);
				application.setAttribute("liveCustomer", liveCustomer);
				flag="true";
			}
		}
		
		//存在在线名单
		else{
			System.out.println("222222222222222222222222222222");
			//验证通过
			if(dname.equals("")==false){
				//重复登录
				if(liveCustomer.contains(username)==true){
					flag="logined";
					
				}else{
					//存入application
					session.setAttribute("username", username);
					//返回姓名，号码。余额
					Map<String, Object> map=loginService.findAllByID(username);
					String mobile=(String)map.get("mobile");
					double balance=(Double)map.get("balance");
					System.out.println(dname+mobile+balance);
					map.put("dname", dname);
					//存入session
					session.setAttribute("map", map);
					//存入application
					liveCustomer.add(username);
					application.setAttribute("liveCustomer", liveCustomer);
					flag="true";
					
				}	
			}			
		}
		
		out.print(flag);
		
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
