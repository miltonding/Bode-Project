<%@ page language="java" pageEncoding="gb2312"%>
<%@page import="java.util.List"%>
<%@page import="com.domian.RechargeInfor"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'record.jsp' starting page</title>
    
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
		if(document.getElementById("inputIndex").value.length!=0){
			document.getElementById("recordForm").submit();
		}
	
	}
	</script>
  </head>
  
  <body id="record-body"  >
<div id="record-contain">
<div id="record-main">
    

    <div class="record-tel">����<span class="tel-account">${map.mobile}</span>�ĳ�ֵ��¼���£�</div>
    
    <table width="700px"  class="table-infor-record-style2">
  <tr >
    <th>��ֵʱ��</th>
    <th>��ֵ��Ԫ��</th>
    <th>��ֵ��ʽ</th>
    <th>��ֵ�����</th>
    <th>���п����</th>
    <th>�Żݽ��</th>
  </tr>
  
  <%
  	List<RechargeInfor> rechargeList=(List<RechargeInfor>)request.getAttribute("rechargeList");
  	int sum_recharge=0;
  	int count=0;
  	int sum_discount=0;
  	if(rechargeList!=null){
  		for(int i=0;i<rechargeList.size();i++){
  			RechargeInfor r=rechargeList.get(i);
  			count++;
  			int discount_amount=r.getDiscount_amount();
  			sum_discount+=discount_amount;
   %>
  <tr>
    <td><%=r.getRecharge_time() %></td>
    <td><%int recharge_money=r.getRecharge_money();
			    sum_recharge+=recharge_money;
			    out.println(recharge_money);
     %></td>
    <td><%=r.getRecharge_type_name() %></td>
    <td><% int card_id=r.getCard_id();
    			if(card_id!=0){
    				out.print(card_id);
    			}else{
    				out.print("--");
    			}
    
    %></td>
    <td><% int bank_card_numb=r.getBank_card_numb();
    			if(bank_card_numb!=0){
    				out.print(bank_card_numb);
    			}else{
    				out.print("--");
    			}
    
    %></td>
    <td><%=r.getDiscount_amount() %></td>
  </tr>
  
  <%
    	}  
    }else{
   %>
 <span style="color:red;font-size:26px"> ��û�г�ֵ��¼!!!!!!!!!!!!!!!!!!!!</span>
   <%} %>
    <tr>
    <td style="background:#79c8E6">�ܼ�</td>
    <td><%out.print(sum_recharge); %></td>
    <td>--</td>
    <td><%out.print(count);%>��</td>
    <td><%out.print(count);%>��</td>
    <td><%out.print(sum_discount); %></td>
  </tr>


  
</table>

<form action="servlet/RechargeInforServlet" method="post" id="recordForm">
  <table   class="table-infor-record-style2" id="page2">
	  <tr>
	    <th width="113">��${goalPage}ҳ/��${pageCount}ҳ</th>
	    <td id="page-skip"> 
	    <a href="servlet/RechargeInforServlet?goalPage=1">��ҳ</a>
	      <a href="servlet/RechargeInforServlet?goalPage=${goalPage-1}">��һҳ </a>
	      <a href="servlet/RechargeInforServlet?goalPage=${goalPage+1}">��һҳ </a>
	     <a href="servlet/RechargeInforServlet?goalPage=${pageCount}">βҳ</a>
	     <input type="text"  name="goalPage" id="inputIndex" class="input-page-style">	
	    	<input type="button" value="go" onclick="submitOne()" /> </td>
	  </tr>
	</table>
</form>

</div>

 </div>

</body>
</html>
