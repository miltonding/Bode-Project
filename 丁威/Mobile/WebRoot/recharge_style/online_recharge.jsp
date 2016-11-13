<%@ page language="java" pageEncoding="gb2312"%>
<%@page import="java.util.List"%>
<%@page import="com.domian.Preferential"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'online_recharge.jsp' starting page</title>
    
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
	
	function checkOne(){
		var tel_numb=document.getElementById("tel_numb").value;
		var discount=document.getElementById("discount").value;
		var T4=document.getElementById("T4").value;
		if(tel_numb.length!=0&&discount.length!=0&&T4.length!=0){
			document.getElementById("formOne").submit();
		
		}
	
	}
	
	
	function setDiscount(i){
		var values = i.split(",");
		document.getElementById("discount").value = values[1];
		document.getElementById("T4").value = parseInt(values[1])+parseInt(values[0]);	
	}
	
		//重置
		function resetOne(){
		history.go(0);
	
	}

	</script>
  </head>
  
  <body>
    <div id="online-contain">

    	<div id="card-procedure2"></div>
    <div id="online-main">
    
       
        <form method="POST" action="servlet/OnlineRechargeServlet?flag=confirmInfo" id="formOne" class="form-style">
	<table  class="table-style-online" >

  <tr>
    <td class="left-td-style">充值号码：</td>
    <td class="right-td-style"><input type="text" name="T3" id="tel_numb" size="20" class="input-form-style" value="${map.mobile}" id="tel_numb" ></td>
   </tr>
  <tr>
  	 <td class="left-td-style">充值金额：</td>
     <td class="right-td-style">
     <%
     	List<Preferential> onlineList = (List<Preferential>)request.getAttribute("onlineList");
          
          	if(onlineList!=null){
          		for(int i=0;i<onlineList.size();i++){
          			Preferential o=onlineList.get(i); %>
        <input type="radio" name="tel_charge" id="tel_charge" value="<%=o.getTel_charge()%>,<%=o.getDis_amount() %>" onclick="setDiscount(this.value)"/><%=o.getTel_charge() %>元
      <%	
      
      		}
		}      
       %>
       </td>
  </tr>
   <tr>
  	 <td class="left-td-style">充值优惠：</td>
     <td class="right-td-style"><input type="text" readOnly="readOnly" name="discount" id="discount" size="20" class="input-form-style"  ></td>
  </tr>
  <tr>
  	 <td class="left-td-style">实际到账：</td>
     <td class="right-td-style"><input type="text" name="T4" id="T4" size="20"  class="input-form-style"   ></td>
  </tr>
   
     <tr class="button-tr-style" >
  	 <td colspan="2" class="left-td-style"> 
     
       <input type="button"  value="下一步" class="button-sub"  onclick="checkOne()"/>
       <input type="button" value="重置" class="button-res" onclick="resetOne()"/>
     </td>
     </tr>
  
 </table>
      
 </form>
   
    
    </div>

</div>
  </body>
</html>
