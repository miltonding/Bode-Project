<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Account.jsp' starting page</title>
    
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
  
 <body id="new-operator-body">
<div id="new-operator-contain">
<div class="zhzf"> </div>
<div id="new-operator-main">

  <!-- 在线支付和  买号码公用 -->
  <% 	String flag=request.getParameter("flag");
  		if(flag!=null){
  		//在线支付
  			if("onlineRecharge".equals(flag)){
   %>
   <form method="POST" action="servlet/OnlineRechargeServlet?flag=onlineRecharge" class="form-style">
	<table  class="table-style" >
 	 	<% String recharge_type_name=request.getParameter("recharge_type_name");
 	 		//中乱乱码
 	 		recharge_type_name=new String(recharge_type_name.getBytes("ISO-8859-1"),"UTF-8");
 	 		String tel_numb=request.getParameter("tel_numb");
 	 		int preferential_id=Integer.parseInt(request.getParameter("preferential_id"));
 	 		double sum_amount=Double.parseDouble(request.getParameter("sum_amount"));
 	 		int tel_charge=Integer.parseInt(request.getParameter("tel_charge"));
		
			
		%>
   
   		<!-- 使用影藏与提交 -->
   	<input type="hidden" name="recharge_type_name" id="recharge_type_name" value="<%=recharge_type_name %>"/>
     <input type="hidden" name="tel_numb" id="tel_numb" value="<%= tel_numb%>"/>
     <input type="hidden" name="preferential_id"  id="preferential_id" value="<%= preferential_id%>"/>
     <input type="hidden" name=sum_amount id="sum_amount" value="<%=sum_amount %>"/>
     <input type="hidden" name="tel_charge" id="tel_charge" value="<%= tel_charge%>">
         <tr>
    <td class="left-td-style">银行卡号：</td>
    <td class="right-td-style"><input type="text" name="T2" size="20" class="input-form-style" ></td>
  	</tr>
  	<tr>
    <td class="left-td-style">密码：</td>
    <td class="right-td-style"><input type="password" name="T3" size="20" class="input-form-style"  ></td>
   </tr>

    <tr class="button-tr-style" >
  	 <td colspan="2"class="right-td-style" >  
      <input type="submit" value="提交" name="B1"  class="button-sub" />
     <input type="reset" value="重置" name="B1" class="button-res" />
     
     </td>
     </tr>     
</table>
</form>
   
   
   
   <%		}
   			
   			
   			
   			//买号码
   			else if("payMobile".equals(flag)){ %>
   	<form method="POST" action="servlet/PayMobileServlet" class="form-style">
	<table  class="table-style" >
  <input type="hidden" name="tel_numb" value=" <%=request.getParameter("tel_numb") %>">
   <input type="hidden" name="init_account" value=" <%=request.getParameter("init_account") %>">
    <input type="hidden" name="pp_id" value="<%=request.getParameter("pp_id") %>">
        <tr>
    <td class="left-td-style">银行卡号：</td>
    <td class="right-td-style"><input type="text" name="T2" size="20" class="input-form-style" ></td>
  </tr>
  <tr>
    <td class="left-td-style">密码：</td>
    <td class="right-td-style"><input type="password" name="T3" size="20" class="input-form-style"  ></td>
   </tr>

    <tr class="button-tr-style" >
  	 <td colspan="2"class="right-td-style" >  
      <input type="submit" value="提交" name="B1" class="button-sub" />
     <input type="reset" value="重置" name="B1" class="button-res" />
     
     </td>
     </tr>      
</table>
</form>
    
    <%		}
    	} %>



</div>
</div>

</body>
</html>
