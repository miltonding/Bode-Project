package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domian.RechargeInfor;
import com.service.RechargeInforService;
import com.service.iface.RechargeInforServiceIface;

public class RechargeInforServlet extends HttpServlet {

	private RechargeInforServiceIface rechargeInforService=null;
	/**
	 * Constructor of the object.
	 */
	public RechargeInforServlet() {
		super();
		rechargeInforService=new RechargeInforService();
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
		//页数
		int pageCount=0;
		int pageSize=6;
		int uncheckedPage=Integer.parseInt(request.getParameter("goalPage"));
		System.out.println(uncheckedPage);
		
		HttpSession session = request.getSession();
		Map<String, Object> map=(Map<String, Object>)session.getAttribute("map");
		String tel_numb=(String)map.get("mobile");
		//计算页数
		int count=rechargeInforService.countRechargeInfor(tel_numb);
		if(count%pageSize==0){
			pageCount=count/pageSize;
			
		}else{
			pageCount=count/pageSize+1;
			
		}
		//解决页码溢出问题
		int goalPage=0;
		if(uncheckedPage<1){
			goalPage=1;
			
		}else if(uncheckedPage>pageCount){
			goalPage=pageCount;
			
		}else{
			goalPage=uncheckedPage;
			
		}
		System.out.println(goalPage+"....................................");
		List<RechargeInfor> rechargeList = rechargeInforService.rechargeInfor(tel_numb,goalPage,pageSize);
		
		request.setAttribute("rechargeList", rechargeList);
		request.setAttribute("goalPage", goalPage);
		request.setAttribute("pageCount", pageCount);
		request.getRequestDispatcher("/recharge_style/record.jsp").forward(request, response);
		
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
