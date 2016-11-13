package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domian.Mobile;
import com.service.SelectMobileService;
import com.service.iface.SelectMobileServiceIface;

public class SelectMobileServlet extends HttpServlet {
	
	private SelectMobileServiceIface selectMobileService=null;
	/**
	 * Constructor of the object.
	 */
	public SelectMobileServlet() {
		super();
		selectMobileService=new SelectMobileService();
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
		
		
		//ҳ���С
		int pageSize=6;
		//ҳ��
		int pageCount=0;

		
		//�״β�ѯ���ҵ�һҳ
			//��ò��ҵĲ���
			//��������
			String city=request.getParameter("city");
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			double fee=Double.parseDouble(request.getParameter("fee"));
			System.out.println(fee);
			//��ȡ�ַ���
			String num=request.getParameter("num");
			num=num.substring(0,2);
			System.out.println(num);
			String numtype=request.getParameter("numtype");
			System.out.println(numtype);
			//ָ������
			String inputstyle=request.getParameter("inputstyle");
			System.out.println(inputstyle);
			int uncheckedGoalPage=Integer.parseInt(request.getParameter("goalpage"));
			System.out.println(uncheckedGoalPage);
			System.out.println("����Ϊ��������");
			
			Mobile m=new Mobile(city,fee,num,numtype,inputstyle);
			//����ҳ��
			int pages=selectMobileService.ALLSelectMobile(m);
			if(pages%pageSize==0){
				pageCount=pages/pageSize;
				
			}else{
				pageCount=pages/pageSize+1;
				
			}
		
			
			//�����goalPage,����������
			int goalPage=0;
			if(uncheckedGoalPage<1){
				goalPage=1;
				
			}else if(uncheckedGoalPage>pageCount){
				goalPage=pageCount;
				
			}else{
				
				goalPage=uncheckedGoalPage;
			}
			System.out.println(goalPage);
			//��ҳ��ѯ
			List<Mobile> mobileList = selectMobileService.SelectMobile(m, goalPage, pageSize);
			

			//���أ���ѯ����
			request.setAttribute("city", city);
			request.setAttribute("fee", fee);
			request.setAttribute("num", num);
			request.setAttribute("numtype", numtype);
			request.setAttribute("inputstyle", inputstyle);
			
			//���ز�ѯ���
			request.setAttribute("mobileList", mobileList);
			request.setAttribute("currentPage", goalPage);
			request.setAttribute("pageCount", pageCount);
			request.getRequestDispatcher("/numericalSelection/select_number.jsp").forward(request, response);

		
		
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
