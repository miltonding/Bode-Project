<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>江苏移动网上营业厅首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	
	</script>
  </head>
  
  <body>
   	<div class="left">
    <div  id="tit1">
        <div class="tit_nav">
            <span class="menu-title">选&nbsp;&nbsp; 号</span>
        </div>
        <ul class="tit_con">
        	<li>
            <span class="menu-infor">
            <a href="numericalSelection/select_number.jsp" target="content1">号码办理</a>
            <span>
            </li>
        </ul>
            
    </div>
    
     <div class="tit" >
            <div class="tit_nav">
                <span class="menu-title">业&nbsp;&nbsp; 务</span>
            </div>
            <ul class="tit_con">
        	
            <li>
                <span class="menu-infor">
                <a href="servlet/OnlineRechargeServlet?flag=queryPreferential" target="content1">在线充值</a>    
                <span>
            </li>
            
             <li>
                <span class="menu-infor">
                <a href="recharge_style/rechargeable_card.jsp" target="content1">充值卡充值</a>
                <span>
            </li>
            
              <li>
                <span class="menu-infor">
                <a href="servlet/RechargeInforServlet?goalPage=1" target="content1">充值缴费记录</a>
                </span>
            </li>
            
             <li>
                <span class="menu-infor">
                <a href="servlet/QueryBusinessServlet" target="content1">我的业务</a>
                </span>
            </li>
            
            
            
        </ul>
           
            
          
            
    </div>
    
  
</div>

  </body>
</html>
