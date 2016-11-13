<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.domian.Package"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>套餐选择</title>
    
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
	<style>
	.package-list-result {
    border: 2px solid #DDF0FE;
    padding: 35px 17px 0px;
    height:400px;
    width:900px;
    margin-top:10px;
}

.package-list {
    position: relative;
    padding: 35px 20px 0px;
    z-index: 1000;
}

.package-list li {
    float: left;
    display: inline;
    width: 140px;
    margin-left: 42px;
    margin-right: 42px;
    padding-bottom: 35px;
}
.package-info {
    border: 1px solid #D6D6D6;
    padding: 15px 0px;
    text-align: center;
    background: #FFF none repeat scroll 0% 0%;
}
.package-info-price {
    color: #55B4FC;
    font: 24px/48px "microsoft yahei";
}
.package-info-des {
    color: #8FC320;
    line-height: 20px;
    font-weight: 700;
}
.package-name {
    height: 27px;
    line-height: 27px;
    text-align: center;
    background: #E5E5E5 none repeat scroll 0% 0%;
}
.package-action {
    padding-top: 8px;
}

.package-action .add-compare, .package-select-result .package-action .add-compared, .package-select-result .package-action .handle-now {
    float: left;
    margin-right: 0px;
    padding: 0px 6px;
    color: #FFF;
    font: 12px/24px "microsoft yahei";
}
.blue-btn {
    background: #32ADFB none repeat scroll 0% 0%;
}

</style>
 <script type="text/javascript">
 	/**
		确认提交方法
		如果提交按钮的innerHTML为提交的话，将提价按钮的innerHTML改为支付，并将确认信息显示
		如果提交按钮的 innerHTML为支付的话，直接跳转账户界面
	*/
	function comfirmInfor(){

		if( document.getElementById("submit").innerHTML=='提交'){
	 		document.getElementById("submit").innerHTML='支付';
   			document.getElementById('comfirmInfor').style.display='';
	 	}else{
	 		location.href='recharge_style/Account.html';
	 	}
  }
  
 </script>
  </head>
  
  <body>
   <div id="setmeal-contain"><!-- setmeal-contain 开始-->
	<div id="setmeal-main" ><!-- setmeal-main 开始-->
	
		<table  class="table-style" style="float:left" >
		  <tr>
		    <td class="left-td-style" >手机号码：</td>
		    <td class="right-td-style"><%=request.getParameter("tel_numb")%></td>
		  </tr>
		  <tr>
		    <td class="left-td-style" >充值金额：</td>
		    <td class="right-td-style"><%=request.getParameter("init_account")%></td>
		  </tr>
		  <tr>
		    <td class="left-td-style">号码归属地：</td>
		    <td class="right-td-style"><%
		    	String tel_add=request.getParameter("tel_add");
		    		    	tel_add=new String(tel_add.getBytes("ISO-8859-1"),"UTF-8");
		    		    	out.print(tel_add);
		    %>地区</td>
		   </tr>
		  <tr>
		  	 <td class="left-td-style">付款方式：</td>
		     <td class="right-td-style">  <img  src="image/h-show-buyPro-userCos-pay-unipay.gif" /><span>网银支付</span></td>
		  </tr>
		  
		 </table>
		 <img src="image/qqtsl58.gif"  style="margin-top:30px"/>

		<div class="package-list package-list-result" id="packageListResult">
			<ul class="clearfix">
			<%
				Map<String,List> businessList=(Map<String,List>)request.getAttribute("businessList");
					if(businessList!=null){
						List<Package> list=(List<Package>)businessList.get("list");
					
						
						List<String> freeListOne=(List<String>)businessList.get("freeListOne");
						List<String> freeListTwo=(List<String>)businessList.get("freeListTwo");
						
						for(int i=0;i<freeListOne.size();i++){
							Package b=list.get(2*i+1);
			%>
				
				<li>	
				<div title="" class="package-info"><p class="package-info-price">￥<strong><%=b.getPp_fee() %></strong></p><p title="" class="package-info-des">赠送<%=freeListOne.get(i) %><br><%=freeListTwo.get(i) %>流量</p></div>
					<div title="" class="package-name"><%=b.getPp_name() %></div>
					<div class="package-action"><a name="" href="recharge_style/Account.jsp?flag=payMobile&tel_numb=<%=request.getParameter("tel_numb")%>&init_account=<%=request.getParameter("init_account") %>&pp_id=<%=b.getPp_id()%>" class="blue-btn add-compare">立即办理</a></div>
				</li>
				

		<%			}
				}		
		 %>
				
			</ul>
		</div>

		
	
	</div><!-- setmeal-main 结束-->
</div><!-- setmeal-contain 结束-->
  </body>
</html>
