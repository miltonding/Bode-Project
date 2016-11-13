<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.domian.Mobile"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'select_number.jsp' starting page</title>
    
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
	<script>

        /**
		 	选择城市，选中某个城市城市颜色变为背景颜色变为
			红色，边框变为黄色
		 */
		function chooseCity(ii){
			//ii代表选中的li对象
			var temp = document.getElementById('myul');
			var lis = temp.getElementsByTagName('li');  //获得ul下面所有的li对象
		    
            for(var i = 0; i < lis.length; i ++){       
			 
				  if(lis[i].getAttribute("value")==ii.getAttribute("value")){ 
				  //遍历li对象，循环对比li对象value值是否和传入ii对象值是否一致
				  
				  		//给hidden city赋值
				  		document.getElementById("city").value = ii.getAttribute("value");
						lis[i].style.background="#FFDDDD";
						lis[i].style.borderColor="#FFCC00";
				  }else{
				  
						 lis[i].style.background="#E1E1E1";
						 lis[i].style.borderColor="#BEBEBE";
				  
				  }
				  		
		     }
	
		}

		function query_telfee(ii,ul_id){
		
		temp = document.getElementById(ul_id);
		lis = temp.getElementsByTagName('li');
	
			for(var i = 0; i < lis.length; i ++){
			
			if(lis[i].getAttribute("value")==ii.getAttribute("value")){
			   if(ul_id == "my-ul-fee"){
			   		document.getElementById("fee").value = ii.getAttribute("value");
			   }else if(ul_id == "dnseg"){
			   		document.getElementById("num").value = ii.getAttribute("value");
			   }else{
			   		document.getElementById("numtype").value = ii.getAttribute("value");
			   }
			   lis[i].style.background="#03AFEB";
			   lis[i].style.color="white";
			   
			   
			}else{
				lis[i].style.background="white";
			   lis[i].style.color="#666666";
			   
			   }
			   
			}			
		
		}

	</script>
	<script type="text/javascript">
	var flag=false;
	
	//指定数字只能为4位	
	 function checkOne(){
		document.getElementById("waringSpanOne").innerHTML="";
		var selectNumber=document.getElementById("inputstyle").value;
		var reg = /^(\d)+/;
		if(reg.test(selectNumber)==true&&selectNumber.length<5){
			flag=true;
		}else{
			document.getElementById("waringSpanOne").innerHTML="指定数字只能为4位";
		}
	}	
	
	
	function checkNumber(){
		var reg = /^(\d)+$/;
		var pageIndex=document.getElementById("pageIndex").value;
		if(reg.test(pageIndex)==true){
			return true;
		
		}else{
			alert("请输入指定的数字");
			return false;
		}	
	
	
	}
	
	function submitOne(){
		//输入合法提交
		if(flag==true){
			//提交表单
			document.getElementById("goalpage").value=1;
			document.getElementById("selinfo").submit();
		}
	}

	//指定页数,提交表单
	function submitTwo(){
		if(checkNumber()==true){
			//改变影藏域里面的目标页数
			document.getElementById("goalpage").value=document.getElementById("pageIndex").value;
			//提交表单
			document.getElementById("selinfo").submit();
		}
	
	}
	//搜索页数
	function changePages(i){
		//改变影藏域里面的目标页数
		document.getElementById("goalpage").value=i;
		//提交表单
		document.getElementById("selinfo").submit();
	}
	</script>
	</head>
	
  
  <body >
   <div id="select_number_contain">
    <div id="select_number_main_main">
    	<ul class="loc" id="myul" >
        	<li  onclick="chooseCity(this)" value="南京" >南京</li>
            <li  onclick="chooseCity(this)" value="镇江">镇江</li>
            <li onclick="chooseCity(this)" value="常州">常州</li>
            <li onclick="chooseCity(this)"  value="无锡">无锡</li>
            <li onclick="chooseCity(this)" value="苏州">苏州</li>
            <li onclick="chooseCity(this)" value="南通" >南通</li>
            <li onclick="chooseCity(this)"  value="泰州">泰州</li>
            <li onclick="chooseCity(this)" value="扬州">扬州</li>
            <li onclick="chooseCity(this)" value="宿迁">宿迁</li>
            <li onclick="chooseCity(this)" value="淮安" >淮安</li>
            <li onclick="chooseCity(this)" value="徐州" >徐州</li>
            <li onclick="chooseCity(this)" value="连云港">连云港</li>
            <li onclick="chooseCity(this)" value="盐城">盐城</li>
        </ul>
        <div class="clear"></div>
        <ul class="fee" id="my-ul-fee">
            <li><span class="numb-form">预存话费</span></li>
            <li  onclick="query_telfee(this,'my-ul-fee')" value="" class="renyi">任意</li>
            <li  onclick="query_telfee(this,'my-ul-fee')" value="50">50元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="100">100元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="200">200元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="300">300元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="400">400元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="500">500元</li>
             <li onclick="query_telfee(this,'my-ul-fee')" value="600">600元</li>
            <li onclick="query_telfee(this,'my-ul-fee')"  value="800">800元</li>
        </ul>
     <div class="clear"></div>
        <ul id="dnseg">
            <li><span class="numb-form">号段</span></li>
            <li class="renyi" onclick="query_telfee(this,'dnseg')" value="">任意</li>
             <li onclick="query_telfee(this,'dnseg')" value="13X" >13X</li>
             <li onclick="query_telfee(this,'dnseg')" value="15X">15X</li>
             <li onclick="query_telfee(this,'dnseg')" value="18X">18X</li>
        </ul>
     <div class="clear"></div>
        <ul id="type">
            <li><span class="numb-form">手机卡类别</span></li>
            <li class="renyi" onclick="query_telfee(this,'type')" value="">任意</li>
            <li onclick="query_telfee(this,'type')" value="4G">4G</li>
            <li onclick="query_telfee(this,'type')" value="3G">3G</li>
            <li onclick="query_telfee(this,'type')" value="GSM">GSM</li>
      </ul>
      <div class="clear"></div>
      
      
      <div id="search">
      
      
       <form action="servlet/SelectMobileServlet" method="post" id="selinfo">
  		<input type="hidden" id="city" name="city" value="${city}"/>
  		<input type="hidden" id="fee" name="fee" value="${fee}"/>
  		<input type="hidden" id="num" name="num"/ value="${num}">
  		<input type="hidden" id="numtype" name="numtype" value="${numtype}"/>
  	 	<span class="numb-form">指定数字</span>
     	<input type="text"  id="inputstyle" name="inputstyle" onblur="checkOne()" value="${inputstyle}"/>
     	<input type="hidden" id="goalpage" name="goalpage" value="1">
  		<button type="button" id="sousuo" onclick="submitOne()"></button>
  		 <span style="color:red;font-size:14px" id="waringSpanOne"></span>
 	 </form>      
      </div>
      
      
      
      <div class="clear"></div>
      
      <!-- 展示T_moblie的内容 -->
      <table width="700" class="table-infor-record-style" id="allMobile" >
  <tr>
    <th>号码</th>
    <th>号码归属地</th>
    <th>预存话费</th>
    <th>操作</th>
  </tr>
  
    
     <%  
     //注意空指针异常
     	if(request.getAttribute("mobileList")!=null){
     List<Mobile> mobileList=(List<Mobile>)request.getAttribute("mobileList");
     	
     	for(int i=0;i<mobileList.size();i++){
     	Mobile mobile=mobileList.get(i);
     %> 
  <tr>
    <td><%= mobile.getTel_numb()%></td>
    <td>江苏<%=mobile.getTel_add()%></td>
    <td><%= mobile.getAcc_init_amount()%></td> 
    <td>
       <div  align="center">
        <input type="button" class="buy" value="立即购买" onclick="location.href='servlet/AllPackageServlet?tel_numb=<%= mobile.getTel_numb()%>&init_account=<%= mobile.getAcc_init_amount()%>&tel_add=<%=mobile.getTel_add()%>' "/>
        </div>
        </td>
  </tr>
  
  <% }
  }
   %>

</table>

<div id="clear"></div>
<table   class="table-infor-record-style" id="page" >
 <%if(request.getAttribute("mobileList")!=null){ %>
  <tr>
    <th width="113" >第${currentPage}页/共${pageCount}页</th>
    <td >
	<a href="javascript:void(0)" onclick="changePages(1)">首页</a>
     <a href="javascript:void(0)" onclick="changePages(${currentPage-1})">上一页 </a>
     <a href="javascript:void(0)" onclick="changePages(${currentPage+1})">下一页 </a>
     <a href="javascript:void(0)" onclick="changePages(${pageCount})">尾页</a> 
     <input type="text" class="input-page-style" id="pageIndex" onblur="checkNumber()">
    	<input type="button" value="go" onclick="submitTwo()" />
    	</td>
  </tr>
      	<%}else{ %>
      	<tr ><div style="margin-left:300px;font-size:24px;color:red">请输入查询条件!!</div></tr>
      	<%} %>
</table>

  </div>
</div>


  </body>
</html>
