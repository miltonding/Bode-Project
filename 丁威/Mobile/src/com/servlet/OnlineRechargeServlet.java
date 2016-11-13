package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domian.OnlineRechargeConditions;
import com.domian.Preferential;
import com.service.OnlineRechargeService;
import com.service.iface.OnlineRechargeServiceIface;

public class OnlineRechargeServlet extends HttpServlet {

	private OnlineRechargeServiceIface onlineRechargeService = null;

	/**
	 * Constructor of the object.
	 */
	public OnlineRechargeServlet() {
		super();
		onlineRechargeService = new OnlineRechargeService();

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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String flag=request.getParameter("flag");
		System.out.println(flag);
		
		//查询优惠活动
		if("queryPreferential".equals(flag)){
		// 调用service,查优惠活动
		List<Preferential> onlineList = onlineRechargeService.QueryAllPreferential();
		
		// 传参给jsp
		request.setAttribute("onlineList", onlineList);
		request.getRequestDispatcher("/recharge_style/online_recharge.jsp").forward(request, response);
		}
		
		
		
		
		
		//传参，跳转online_confim.jsp
		else if("confirmInfo".equals(flag)){
			//System.out.println("11111111111111111111111111111111111");
			//获得手机号码
			String tel_numb=request.getParameter("T3");
			System.out.println(tel_numb);
			//获得充值金额
			String temp=request.getParameter("tel_charge");
			String[] tempOne = temp.split(",");
			int tel_charge=Integer.parseInt(tempOne[0]);
			System.out.println(tel_charge);
			//获取实际到账
			double sum_amount=Double.parseDouble(request.getParameter("T4"));
			System.out.println(sum_amount);
			//调用service
			//根据号码查客户姓名
			String customer_name = onlineRechargeService.QueryNameByTel(tel_numb);
			//根据tel_charge查询优惠活动名称,优惠编号
			 Map<String, Object> map = onlineRechargeService.QueryPreName(tel_charge);
			 String preferential_name=(String)map.get("preferential_name");
			 int preferential_id=(Integer)map.get("preferential_id");
			
			//传参 ，跳转online_confirm.jsp
			//顾客名
			request.setAttribute("customer_name", customer_name);
			//号码
			request.setAttribute("tel_numb", tel_numb);
			//充值余额
			request.setAttribute("tel_charge", tel_charge);
			//充值优惠名
			request.setAttribute("preferential_name", preferential_name);
			//充值优惠编号
			request.setAttribute("preferential_id", preferential_id);
			//实际到账
			request.setAttribute("sum_amount", sum_amount);
			
			request.getRequestDispatcher("/recharge_style/online_confirm.jsp").forward(request, response);
			
		}
		
		
		//在线支付
		else if("onlineRecharge".equals(flag)){
			System.out.println("22222222222222222222222222222222");
			//获取，充值名称,号码，优惠编号，实际到账，充值金额
			String recharge_type_name=request.getParameter("recharge_type_name");
			recharge_type_name=new String(recharge_type_name.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(recharge_type_name);
			String tel_numb=request.getParameter("tel_numb");
			System.out.println(tel_numb);
			int preferential_id=Integer.parseInt(request.getParameter("preferential_id"));
			System.out.println(preferential_id);
			double sum_amount=Double.parseDouble(request.getParameter("sum_amount"));
			System.out.println(sum_amount);
			int tel_charge=Integer.parseInt(request.getParameter("tel_charge").trim());
			System.out.println(tel_charge);
			
			//调用servie,存储过程
			int result=onlineRechargeService.onlineRecharge(new OnlineRechargeConditions(recharge_type_name,tel_numb,preferential_id,sum_amount,tel_charge));
			//充值成功,跳转
			String register="";
			if(result==1){
				//更新map的账户余额
				HttpSession session = request.getSession();
				Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
				//登录状态,刷新余额
				if(map!=null){
					double balance=(Double)map.get("balance");
					System.out.println(balance);
					balance=onlineRechargeService.QueryAccount_balance(tel_numb);
					map.put("balance", balance);
					register="在线充值";
				}else{
					//未登录状态
					register="在线充值";
				}
			}else{
				register="在线充值不";
				
			}
			request.setAttribute("register", register);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
			
			
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
