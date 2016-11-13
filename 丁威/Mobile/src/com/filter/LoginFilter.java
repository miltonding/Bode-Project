package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		HttpSession session = request.getSession();
		String username=(String)session.getAttribute("username");
		String uri=request.getRequestURI();
		
		//请求充值记录，我的业务
		if(uri.endsWith("servlet/RechargeInforServlet")||uri.endsWith("servlet/QueryBusinessServlet")){
			System.out.println(uri);
			System.out.println("请求充值记录，我的业务");
			//权限设置
			if(username==null){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>  alert('请登录！！！')");
				out.println("window.top.location.href='/Mobile/login.jsp'</script>");
				
			}else{
				chain.doFilter(arg0, arg1);
				System.out.println("放行了。。。。。。。。。。。。。。。。。。。。。。。");
			}
			
		}else{
			chain.doFilter(arg0, arg1);
			
		}
		
		
		
		
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
