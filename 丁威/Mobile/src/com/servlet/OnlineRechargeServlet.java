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
		
		//��ѯ�Żݻ
		if("queryPreferential".equals(flag)){
		// ����service,���Żݻ
		List<Preferential> onlineList = onlineRechargeService.QueryAllPreferential();
		
		// ���θ�jsp
		request.setAttribute("onlineList", onlineList);
		request.getRequestDispatcher("/recharge_style/online_recharge.jsp").forward(request, response);
		}
		
		
		
		
		
		//���Σ���תonline_confim.jsp
		else if("confirmInfo".equals(flag)){
			//System.out.println("11111111111111111111111111111111111");
			//����ֻ�����
			String tel_numb=request.getParameter("T3");
			System.out.println(tel_numb);
			//��ó�ֵ���
			String temp=request.getParameter("tel_charge");
			String[] tempOne = temp.split(",");
			int tel_charge=Integer.parseInt(tempOne[0]);
			System.out.println(tel_charge);
			//��ȡʵ�ʵ���
			double sum_amount=Double.parseDouble(request.getParameter("T4"));
			System.out.println(sum_amount);
			//����service
			//���ݺ����ͻ�����
			String customer_name = onlineRechargeService.QueryNameByTel(tel_numb);
			//����tel_charge��ѯ�Żݻ����,�Żݱ��
			 Map<String, Object> map = onlineRechargeService.QueryPreName(tel_charge);
			 String preferential_name=(String)map.get("preferential_name");
			 int preferential_id=(Integer)map.get("preferential_id");
			
			//���� ����תonline_confirm.jsp
			//�˿���
			request.setAttribute("customer_name", customer_name);
			//����
			request.setAttribute("tel_numb", tel_numb);
			//��ֵ���
			request.setAttribute("tel_charge", tel_charge);
			//��ֵ�Ż���
			request.setAttribute("preferential_name", preferential_name);
			//��ֵ�Żݱ��
			request.setAttribute("preferential_id", preferential_id);
			//ʵ�ʵ���
			request.setAttribute("sum_amount", sum_amount);
			
			request.getRequestDispatcher("/recharge_style/online_confirm.jsp").forward(request, response);
			
		}
		
		
		//����֧��
		else if("onlineRecharge".equals(flag)){
			System.out.println("22222222222222222222222222222222");
			//��ȡ����ֵ����,���룬�Żݱ�ţ�ʵ�ʵ��ˣ���ֵ���
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
			
			//����servie,�洢����
			int result=onlineRechargeService.onlineRecharge(new OnlineRechargeConditions(recharge_type_name,tel_numb,preferential_id,sum_amount,tel_charge));
			//��ֵ�ɹ�,��ת
			String register="";
			if(result==1){
				//����map���˻����
				HttpSession session = request.getSession();
				Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
				//��¼״̬,ˢ�����
				if(map!=null){
					double balance=(Double)map.get("balance");
					System.out.println(balance);
					balance=onlineRechargeService.QueryAccount_balance(tel_numb);
					map.put("balance", balance);
					register="���߳�ֵ";
				}else{
					//δ��¼״̬
					register="���߳�ֵ";
				}
			}else{
				register="���߳�ֵ��";
				
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
