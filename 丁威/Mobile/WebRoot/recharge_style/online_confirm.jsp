<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'online_comfirm.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	<link href="css/reset.css" rel="stylesheet" type="text/css" />
	
  </head>
  
  <body>
    <div id="online-contain">
	<div id="online-head">
    </div>
    <div class="clear"></div>
    	<div id="card-procedure3"></div>
    <div id="online-main">
    
       
        <form method="POST" action="recharge_style/Account.jsp?flag=onlineRecharge" class="form-style">
<table  class="table-style" >
  <tr>
    <td class="left-td-style">充值方式：</td>
    <td class="right-td-style">在线充值</td>
  </tr>
 <tr>
    <td class="left-td-style">客户姓名：</td>
    <td class="right-td-style">${customer_name}</td>
  </tr>
  <tr>
    <td class="left-td-style">充值号码：</td>
    <td class="right-td-style"><span id="numb">${tel_numb}</span><span>请认真核对</span></td>
   </tr>
  <tr>
  	 <td class="left-td-style">充值金额：</td>
     <td class="right-td-style">${tel_charge}元</td>
  </tr>
   <tr>
  	 <td class="left-td-style">充值优惠：</td>
     <td class="right-td-style"><span id="cheap">${preferential_name}</span></td>
  </tr>
   
    <tr>
  	 <td class="left-td-style">实际到账：</td>
     <td class="right-td-style"><span id="acc">${sum_amount}</span>元</td>
  </tr>
  	<!-- 使用隐藏域提交 -->
     <input type="hidden" name="recharge_type_name" value="在线充值"/>
     <input type="hidden" name="tel_numb" value="${tel_numb}"/>
     <input type="hidden" name="preferential_id" value="${preferential_id}"/>
     <input type="hidden" name=sum_amount value="${sum_amount}"/>
     <input type="hidden" name="tel_charge" value="${tel_charge}">
   
  
     <tr class="button-tr-style" >

  	 <td colspan="2" class="left-td-style" >

     <input type="submit"  value="支付" class="button-sub"   />

     <input type="reset" value="上一步" class="button-res" onclick="location.href='servlet/OnlineRechargeServlet?flag=queryPreferential'"/>

     

     </td>

     </tr>

 </table>
     
 </form>
 
    </div>

</div>
  </body>
</html>
