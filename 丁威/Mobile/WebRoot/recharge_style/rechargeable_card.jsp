<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rechargeable_card.jsp' starting page</title>
    
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
	
	<script type="text/javascript">
	function submitOne(){
		if(document.getElementById("tel_numb").value.length!=0&&document.getElementById("card_id").value.length!=0&&document.getElementById("card_pwd").value.length!=0){
			document.getElementById("cardReForm").submit();
		}
	}
	
	
	function resetOne(){
		document.getElementById("tel_numb").value="";
		document.getElementById("card_id").value="";
		document.getElementById("card_pwd").value="";
	
	}
	
	</script>
  </head>
  
  <body id="card-body">
<div id="card-contain">
	<div id="card-main">
    	<div id="card-procedure"></div>
        
<form method="POST" action="servlet/CardRechargeServlet" class="form-style" id="cardReForm">
<table  class="table-style" >
  <tr>
    <td class="left-td-style">手机号码：</td>
    <td class="right-td-style"><input type="text" name="tel_numb" id="tel_numb" size="20" class="input-form-style"  value="${map.mobile}" ></td>
    
  </tr>
  <tr>
    <td class="left-td-style">充值卡卡号：</td>
    <td class="right-td-style"><input type="text" name="card_id" id="card_id" size="20" class="input-form-style"  ></td>
   </tr>
  <tr>
  	 <td class="left-td-style">充值卡密码：</td>
     <td class="right-td-style"><input type="password" name="card_pwd" id= "card_pwd" size="20" class="input-form-style"  ></td>
  </tr>
   <tr class="button-tr-style" >
  	 <td colspan="2" class="left-td-style" >
     <input type="button"  value="提交" class="button-sub" onclick="submitOne()"/>
     <input type="button" value="重置" class="button-res" onclick="resetOne()"/>
     
     </td>
     </tr>
  </table>
        
 </form>
        
        
        
    
    </div>

</div>
</body>
</html>
