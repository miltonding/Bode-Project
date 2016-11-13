<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   	<title>欢迎登录移动网上营业厅</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/reset.css" rel="stylesheet" type="text/css" />
	<link href="css/index.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript">5
    
	 function userOnClick(){
       document.getElementById('operator').style.color="#037DC9";
	   document.getElementById('operator').style.backgroundImage="url(1.jpg)";
	   document.getElementById('register').style.display="";
	   document.getElementById('current').style.color="white";
	  document.getElementById('current').style.backgroundImage="url(image/hd_title_bg1.gif)";
	    
	 }
	 
	  function userOnClick2(){
	   document.getElementById('register').style.display="none";
	   document.getElementById('current').style.color="#005BAF";
	   document.getElementById('current').style.backgroundImage="url(1.jpg)";
	   document.getElementById('operator').style.color="white";
	   document.getElementById('operator').style.backgroundImage="url(image/hd_title_bg1.gif)";
	    
	 }
	 
	
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



	//姓名的回调函数
	function dnameCallback(){
		//响应已经完成,状态为4
		if(xmlHttpRequest.readyState==4){
		//服务器返回的状态码：”200=“成功”
			if(xmlHttpRequest.status=200){
			//服务器返回数据的文本格式的内容
			//非常注意,返回的一定是String
				var response=xmlHttpRequest.responseText;
				alert("回应信息"+response);
				if(response=="true"){
					location.href="index.jsp";
				}else if(response=="false"){
					document.getElementById("spanOne").innerHTML="账号密码错误!!";
				}else{
					document.getElementById("spanOne").innerHTML="该账号已登录!!";
				}
					
			}
		}
	}
	
	function submitOne(){
	//发送ajax请求
		//1创建对象
		getXmlHttpRequest();
		var username=document.getElementById("username").value;
		var pwd=document.getElementById("pwd").value;
		var url="servlet/LoginServlet?username="+username+"&pwd="+pwd;
		//2打开请求目标URL地址
		xmlHttpRequest.open("POST",url,true);
		//3绑定回调函数
		xmlHttpRequest.onreadystatechange=dnameCallback;
		//4发送请求
		xmlHttpRequest.send(null);
	}
	</script>
  </head>
  
  <body>
 <div id="contain">
  <div id="head">
    <img src="image/logo.gif"  />
  </div>
    <div id="main">
      <ul class="main-recommend-list">
        <li><a href="">在线充值</a>
            <p>冲100送5，冲200送12，多冲多送</p>
        </li>
        <li><a  href="">选号购机</a>
            <p>海量靓号免费选，在线购机优惠多</p>
        </li>
        <li><a href="">优惠活动</a>
            <p>话费免费领，登录有惊喜！缤纷活动尽在促销 优惠专区</p>
        </li>
      
        
        <li><a href=""> 查询办理</a>
            <p>千余项业务查询办理，让您轻松掌握、自在选择！</p>
        </li>
      </ul>
      <div id="main-loginBox">
       	
       <ul id="main-loginBox-tab" >
          <li><span class="current" id="current" >用户登录</span></li>
           <!--<li><span class="operator" id="operator" onmouseover="userOnClick2()" >业务员登录</span></li>-->
           <!--<li><span class="operator" id="operator" onmouseover="userOnClick2()" >业务员登录</span></li>-->
       </ul>
      
      
    
       <form class="main-loginBox-center" action=""  >
       	
       		<div class="tusername">
            	<span>账户</span>
                <input type="text" name="username" id="username"/>&nbsp;</div>
            <div class="tpassword"> <span>密码</span>
                <input type="password" name="pwd" id="pwd"/></div>
             <div class="submitbutt">
               <button type="button" onclick="submitOne()"><font color="#ffffff" size="4">登录</font></button>
               <a href="register.jsp">
               		<span id="register" style="display:'';font-size:17px" >立即注册！</span>
               	
               </a>
             </div>
             <br>
            <tr > <span style="text-align:center;color:red;margin-left:150px;font-size:16px" id="spanOne"></span></tr>
       </form>
       </div>
    </div>


</div>
 </body>
</html>
