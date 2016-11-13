<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/reset.css" rel="stylesheet" type="text/css" />
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	
	var xmlHttpRequest = null;
		/**
			(1)创建XMLHttpRequest对象
		*/
		function getXmlHttpRequest(){
		
			try{
				//Firefox,Opera,Safari
				xmlHttpRequest = new XMLHttpRequest();
			}catch(e){
			
				//IE
				try{
					//IE6及以下
					xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
				
					//IE6以上
					xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
		}
		


	//退出函数
	function loginOutOne(){
		alert(1);
	//发送ajax请求
		//1创建对象
		getXmlHttpRequest();
		var url="servlet/LoginoutServlet";
		//2打开请求目标URL地址
		xmlHttpRequest.open("POST",url,true);
		//3绑定回调函数
		xmlHttpRequest.onreadystatechange=outCallback;
		//4发送请求
		xmlHttpRequest.send(null);

	
		
		}
		
		//回调函数
	function outCallback(){
		//响应已经完成,状态为4
		if(xmlHttpRequest.readyState==4){
		//服务器返回的状态码：”200=“成功”
			if(xmlHttpRequest.status=200){
			//服务器返回数据的文本格式的内容
			//非常注意,返回的一定是String
				var response=xmlHttpRequest.responseText;
				alert("回应结果"+response);
					//账号可用
					if(response=="success"){
						//顶级页面刷新
						window.top.location.href="index.jsp";
					}	
			}
		}
	}
	
	
	//未登录，登录
	function clickOne(){
		//顶级窗口跳转
		window.top.location.href="login.jsp";
	}

	
	</script>
  </head>
  
  <body>
   <div id="head-contain">
    	<div id="head-head">
          <marquee>欢迎来到中国移动!
          <%	Map<String,Object> map=(Map<String,Object>)session.getAttribute("map");
          	//未登录
          		if(map==null){
          	%>	
          	<span> 您尚未登录!!!</span> 
        	<span class="loginout"> <a href="javascript:void(0)" onclick="clickOne()">[登录]</a> </span>
          	
          	<%
          		//登录了
          		}else{
           %>  
          <span> ${map.dname} ! ${map.mobile} ! 您的账户余额为 ： ${map.balance}</span> 
        <span class="loginout"> <a href="javascript:void(0)" onclick="loginOutOne()">[退出]</a> </span>
        <%
        	}
         %>
            </marquee>  
        </div>
            
	<div id="head-main">
  
    <img class="logo" src="<%=basePath%>image/logo.gif" />
    <img src="image/js_logo.gif" height="89" class="js-log"/>
   <img  id="image-right" align="right" src="image/yd_right_logo.gif" />       
           
    </div>
   </div>
  </body>
</html>
