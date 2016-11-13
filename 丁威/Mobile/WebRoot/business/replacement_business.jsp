<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.domian.Package"%>
<%@page import="com.domian.Business"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'replacement_business.jsp' starting page</title>
    
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
  
 <body id="changebus-body">
<div id="changebus-contain">
	<div id="changebus-main">
   
     <div class="ywbl"></div>
   
   <table  class="table-infor-record-style2" width="700px" >
  <tr>
    <th width="20%">业务名称</th>
    <th width="10%">资费</th>
  
    <th width="25%">启用时间</th>
    <th  width="25%">终止时间</th>
    <th width="20%">操作</th>
  </tr>
 <% List<Business> businessList = (List<Business>)request.getAttribute("businessList");
 	if(businessList!=null){
 		for(int i=0;i<businessList.size();i++){
 			Business b=businessList.get(i);
 			int is_optional=b.getIs_optional();
 			int is_largess=b.getIs_largess();
 			String end_time=b.getEnd_time();
  %>
  
   <tr>
    <td><%=b.getBusiness_name() %></td>
    <td>
    <%
	    	if(is_largess==0){
	    		out.print("基础业务");
	    	}else{
	    		out.print("套餐内业务");
	    	}
     %>
    </td>
   
    <td id="st"><%=b.getStart_time() %></td>
    <td id="ed">
    <%          if(end_time!=null){
    				out.print(end_time);
    			}else{
    				out.print("--");
    			}
    			%>
    </td>
    <td>
    </td>
  </tr>
  
  
  <%
  		}
  	}
  
  		List<Business> openBusiness=(List<Business>)request.getAttribute("openBusiness");
  		if(openBusiness!=null){
  		//已开通的业务
	  		for(int i=0;i<openBusiness.size();i++){
	  			Business b=openBusiness.get(i);
	  			String end_time=b.getEnd_time();
   %>
  
  <tr>
    <td><%=b.getBusiness_name() %></td>
    <td><%=b.getBusiness_charge() %></td>
   
    <td id="st"><%=b.getStart_time() %></td>
    <td id="ed"> <%          if(end_time!=null){
    				out.print(end_time);
    			}else{
    				out.print("--");
    			}
    			%></td>
    
    
    <td>
    	<form  id="business" method="post" action="servlet/ManagerBusinessServlet?flag=closeBusiness" >
    		<input type="hidden" name="bussinessId" value="<%=b.getBusiness_id()%>"/>
    		<%  int status=b.getStatus();
    		//区分是否可操作
    			if(status==1){
    			%>
    		<input type="submit"  id="open" class="blywkt" value="关闭" />
    		<% }%>
    	</form>
    </td>
  </tr>
  
  <%
  		}
  	}
   %>
   
   
   
   
  	<%
  
  		List<Business> notOpenBusiness=(List<Business>)request.getAttribute("notOpenBusiness");
  		if(notOpenBusiness!=null){
	  		//未开通的业务
	  		for(int i=0;i<notOpenBusiness.size();i++){
	  			Business b=notOpenBusiness.get(i);
   %>
  
  <tr>
    <td><%=b.getBusiness_name() %></td>
    <td><%=b.getBusiness_charge() %></td>
   
    <td id="st"></td>
    <td id="ed"></td>
    
    
    
    <td>
    	<form  id="business" method="post" action="servlet/ManagerBusinessServlet?flag=addBusiness">
    		<input type="hidden" name="bussinessName" value="<%=b.getBusiness_name() %>"/>
    		<input type="submit"  id="open" class="blywkt" value="开通" />
    	</form>
    </td>
  </tr>
  
  <%
  		}
  	}
   %>

  
  
</table>

  <div class="tcbl"></div>
  
   <table class="table-infor-record-style2"  width="700px">
  <tr >
    <th>套餐名称</th>
    <th>套餐资费</th>
    <th>套餐详情</th>
    
    
    <th>操作</th>
  </tr>
  

  <%    //获取当前套餐
		List<Package> OpenPackage =(List<Package>)request.getAttribute("OpenPackage");
		if(OpenPackage!=null){ %>
  <tr>
    <td><%=OpenPackage.get(0).getPp_name()%></td>
    <td><%=(int)OpenPackage.get(0).getPp_fee()%>元</td>
    <td>通话<%=OpenPackage.get(1).getFreeBussiness()%>，流量<%=OpenPackage.get(0).getFreeBussiness()%></td>
 	<td><input type="button" id='dqtc' name="meal" class="dqtc" value= "当前套餐"/></td>
  </tr>

  
  <%} 
   	//未开通的套餐
		List<Package> NotOpenPackage =(List<Package>)request.getAttribute("NotOpenPackage");
		if(NotOpenPackage!=null){ 
			for(int i=0;i<NotOpenPackage.size()/2;i++){
		%>
   <tr>
     <td><%=NotOpenPackage.get(2*i+1).getPp_name()%></td>
    <td><%=(int)NotOpenPackage.get(2*i+1).getPp_fee()%>元</td>
    <td><%=NotOpenPackage.get(2*i).getFreeBussiness()%>，<%=NotOpenPackage.get(2*i+1).getFreeBussiness()%></td>
  	<td>
  	  <form method="post" action="servlet/ManagerPackageServlet">
  	  	<input type="hidden" name="pp_id" value="<%= NotOpenPackage.get(2*i+1).getPp_id()%>">
  		<input type="submit"  id='gb' name="meal"  class="dqtc"  value="开通"/></td>
  	  </form>
  </tr>
  
  	<%}
  		} 
  	%>
  

</table>

</div>
</div>
            
    </body>
</html>


