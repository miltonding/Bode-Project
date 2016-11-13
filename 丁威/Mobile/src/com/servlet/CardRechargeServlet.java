package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domian.CardRechargeConditions;
import com.service.CardRechargeService;
import com.service.OnlineRechargeService;
import com.service.iface.CardRechargeServiceIface;

public class CardRechargeServlet extends HttpServlet {

	private CardRechargeServiceIface cardRechargeService=null;
	/**
	 * Constructor of the object.
	 */
	public CardRechargeServlet() {
		super();
		cardRechargeService=new CardRechargeService();
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
		
		String tel_numb=request.getParameter("tel_numb").trim();
		System.out.println(tel_numb);
		int card_id=Integer.parseInt(request.getParameter("card_id").trim());
		System.out.println(card_id);
		String card_pwd=request.getParameter("card_pwd");
		System.out.println(card_pwd);
		
		boolean result = cardRechargeService.cardRecharge(new CardRechargeConditions(tel_numb,card_id,card_pwd));
		
		//��ֵ�ɹ�,��ת
		String register="";
		if(result==true){
			//����map���˻����
			HttpSession session = request.getSession();
			Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
			//��¼״̬,ˢ�����
			if(map!=null){
				double balance=(Double)map.get("balance");
				System.out.println(balance);
				OnlineRechargeService online=new OnlineRechargeService();
				balance=online.QueryAccount_balance(tel_numb);
				map.put("balance", balance);
				register="��ֵ����ֵ";
			}else{
				//δ��¼״̬
				register="��ֵ����ֵ";
			}
		}else{
			register="��ֵ��";
			
		}
		request.setAttribute("register", register);
		request.getRequestDispatcher("/success.jsp").forward(request, response);
		
		
		
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
