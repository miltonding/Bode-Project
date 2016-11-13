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

import com.domian.Business;
import com.domian.Package;
import com.service.QueryBusinessService;
import com.service.iface.QueryBusinessServiceIface;

public class QueryBusinessServlet extends HttpServlet {
	private QueryBusinessServiceIface queryBusinessService=null;
	/**
	 * Constructor of the object.
	 */
	public QueryBusinessServlet() {
		super();
		queryBusinessService=new QueryBusinessService();
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
		
		//��ú���
		HttpSession session = request.getSession();
		Map<String, Object> map=(Map<String, Object>)session.getAttribute("map");
		String tel_numb=(String)map.get("mobile");
		//��ѯ����/����ҵ��
		List<Business> businessList = queryBusinessService.queryFreeBusiness(tel_numb);
		//��ѯδ��ͨ�Ŀ��շ�ҵ��
		List<Business> notOpenBusiness = queryBusinessService.queryNotOpenChargeBusiness(tel_numb);
		//��ѯ��ͨ���շ�ҵ��
		List<Business> openBusiness = queryBusinessService.queryOpenChargeBusiness(tel_numb);
		//��ѯ��ǰ�ײ�
		List<Package> OpenPackage = queryBusinessService.queryOpenPackage(tel_numb);
		//��ѯ�����ײ�
		List<Package> NotOpenPackage = queryBusinessService.queryNotOpenPackage(tel_numb);
		
		
		
		//����
		request.setAttribute("businessList", businessList);
		//δ��ͨ���շ�ҵ��
		request.setAttribute("notOpenBusiness", notOpenBusiness);
		//δ��ͨ���շ�ҵ��
		request.setAttribute("openBusiness", openBusiness);
		//��ǰ�ײ�
		request.setAttribute("OpenPackage", OpenPackage);
		//�����ײ�
		request.setAttribute("NotOpenPackage", NotOpenPackage);
		
		//union
		
		//��ת
		request.getRequestDispatcher("/business/replacement_business.jsp").forward(request, response);
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
