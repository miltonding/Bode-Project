<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link href="css/reset.css" rel="stylesheet" type="text/css" />
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	//全局变量
	var userflag=false;
	var mobileflag=false;
	
	
	
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



	//账号的回调函数
	function usernameCallback(){
		//响应已经完成,状态为4
		if(xmlHttpRequest.readyState==4){
		//服务器返回的状态码：”200=“成功”
			if(xmlHttpRequest.status=200){
			//服务器返回数据的文本格式的内容
			//非常注意,返回的一定是String
				var response=xmlHttpRequest.responseText;
					//账号可用
					if(response=="true"){
						userflag=true;
						document.getElementById("spanOne").innerHTML="";
					}else{
						document.getElementById("spanOne").innerHTML="账号已存在";
					}
			}
		}
	}
	
	//手机号码的回调函数
	function mobileCallback(){
		//响应已经完成,状态为4
		if(xmlHttpRequest.readyState==4){
		//服务器返回的状态码：”200=“成功”
			if(xmlHttpRequest.status=200){
			//服务器返回数据的文本格式的内容
			//非常注意,返回的一定是String
				var response=xmlHttpRequest.responseText;
					//号码可注册
					if(response=="success"){
					document.getElementById("spanThree").innerHTML="";
						mobileflag=true;
					}else if(response=="registered"){
						document.getElementById("spanThree").innerHTML="该手机号码已经注册";
					}else if(response=="notsale"){
						document.getElementById("spanThree").innerHTML="该手机号码不能注册";
					}
			}
		}
	}
	
	
	
	//前台逐条验证
	function checkOne(){
		var username=document.getElementById("username").value;
		var reg = /^(\w){1,20}$/;
		if(reg.test(username)==true){	
		//验证通过,看账号是否存在,使用ajax
		//1创建对象
		getXmlHttpRequest();
		var username=document.getElementById("username").value;
		var url="servlet/RegisterServlet?flag=ajaxusername&username="+username;
		//2打开请求目标URL地址
		xmlHttpRequest.open("POST",url,true);
		//3绑定回调函数
		xmlHttpRequest.onreadystatechange=usernameCallback;
		//4发送请求
		xmlHttpRequest.send(null);
			
		}else{
			document.getElementById("spanOne").innerHTML="账号必须是1到20位，并且不能有特殊符号!!";
			
			userflag=false;
		}
	}
	

	function checkTwo(){
		var dname=document.getElementById("dname").value;
		var reg = /^(\w){1,20}$/;
		if(reg.test(dname)==true){
		document.getElementById("spanTwo").innerHTML="";
			return true;
		}else{
			document.getElementById("spanTwo").innerHTML="姓名必须是1到20位，并且不能有特殊符号!!";
			return false;
		}
	}
	
	

	function checkThree(){
		var mobile=document.getElementById("mobile").value; 
		var reg = /^1[3,5,8]\d{9}$/;
		if(reg.test(mobile)==true){
		//格式通过，发送ajax请求
		//1创建对象
		getXmlHttpRequest();
		var mobile=document.getElementById("mobile").value; 
		var url="servlet/RegisterServlet?flag=ajaxmobile&mobile="+mobile;
		//2打开请求目标URL地址
		xmlHttpRequest.open("POST",url,true);
		//3绑定回调函数
		xmlHttpRequest.onreadystatechange=mobileCallback;
		//4发送请求
		xmlHttpRequest.send(null);
		
		}else{
			document.getElementById("spanThree").innerHTML="手机号码以13,15,18开头，且为11位!!";
			phoneflag=false;
		}
	}
	
	
	function checkFour(){
	var idNumber=document.getElementById("idNumber").value;
		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(reg.test(idNumber)==true){
		document.getElementById("spanFour").innerHTML="";
			return true;
		}else{
			document.getElementById("spanFour").innerHTML="身份证号码格式不正确!!";
			return false;
		}
	}

	function checkFive(){
	var birth=document.getElementById("birth").value;
		var reg = /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
		if(reg.test(birth)==true){
			document.getElementById("spanFive").innerHTML="";
			return true;
		}else{
			document.getElementById("spanFive").innerHTML="格式为yyyy-mm-dd!!";
			return false;
		}
	}
	
		function checkSix(){
		var pwd=document.getElementById("pwd").value;
		var reg =  /^(\w){1,20}$/;
		if(reg.test(pwd)==true){
			document.getElementById("spanSix").innerHTML="";
			return true;
		}else{
			document.getElementById("spanSix").innerHTML="密码必须是1到20位，并且不能有特殊符号!!";
			return false;
		}
	}
	
	function checkSeven(){
		var pwd=document.getElementById("pwd").value;
		var repeatPwd=document.getElementById("repeatPwd").value;
		if(pwd==repeatPwd){
			document.getElementById("spanSeven").innerHTML="";
			return true;
		}else{
			document.getElementById("spanSeven").innerHTML="两次密码不一样!";
			return false;
		}
	}
	
	
	//提交表单函数
	function submitForm(){
	//逐条验证都成功
		if(userflag&&checkTwo()&&mobileflag&&checkFour()&&checkFive()&&checkSix()&&checkSeven()==true){
			//ajax验证通过
			return true;	
		}else{
				return false;
			}

	}
	
	
	
	

	</script>
  </head>
  
  <body>
  <div id="register-contain">
	<div id="register-main">
	<img src="image/register.jpg" id="reg-img" />

	<!-- 表单 -->
   <form class="add-customer" action="servlet/RegisterServlet?flag=formmessage"  method="post" onsubmit="return submitForm()" >
   	
   	<table>
   		<tr>
   			<td class="regtitle">账户：</td>
   			<td class="reginput"><span><input type="text"  id="username" name="username" onblur="checkOne()" /></span>
   			</td>
   			<td><span class="reginwarning" id="spanOne"></span></td>
   		</tr>
   		<tr>
   			<td class="regtitle">姓名：</td>
   			<td><span><input type="text"  id="dname" name="dname"  onblur="checkTwo()"/></span></td>
   			<td><span class="reginwarning" id="spanTwo"></span></td>
   		</tr>
   		<tr>
   			<td class="regtitle">手机号码：</td>
   			<td><span><input type="text" id="mobile" name="mobile" onblur="checkThree()"/></span></td>
   			<td><span class="reginwarning" id="spanThree"></td>
   		</tr>
   		<tr>
   			<td class="regtitle">身份证号码：</td>
   			<td><span><input type="text"  id="idNumber" name="idNumber" onblur="checkFour()"/></span></td>
   			<td><span class="reginwarning" id="spanFour"></td>
   		</tr>
   		<tr>
   			<td class="regtitle">出生日期:</td>
   			<td><span><input type="text" id="birth" name="birth" onblur="checkFive()"/></span></td>
   			<td><span class="reginwarning" id="spanFive"></td>
   		</tr>
   		<tr>
   			<td class="regtitle">登录密码：</td>
   			<td><span><input type="password" id="pwd" name="pwd" onblur="checkSix()"/></span></td>
   			<td><span class="reginwarning" id="spanSix"></td>
   		</tr>
   		<tr>
   			<td class="regtitle">确认密码：</td>
   			<td><span><input type="password"  id="repeatPwd" name="repeatPwd" onblur="checkSeven()"/></span></td>
   			<td><span class="reginwarning" id="spanSeven"></td>
   		</tr>
   		<tr>
   			<td colspan="3" style="padding-left:300px;"><button type="submit" id="reg-sub">立即注册</button></td>
   		</tr>
   	</table>

   </form>
    </div>
</div>
</body>
</html>
