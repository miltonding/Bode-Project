<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>移动网上营业厅首页</title>

<script type="text/javascript">
</script>
</head>

<frameset rows="135,*" cols="*" framespacing="0"  frameborder="no" border="0" id="framset"  >
  
  <frame src="common/header.jsp" name="topFrame" scrolling="no" id="topFrame" title="topFrame" /> 
  
  <frameset cols="220,*" frameborder="no" border="0" framespacing="0" id="main-frameset" >
    <frame src="common/menu.jsp" name="leftFrame" scrolling="no"  noresize="false" id="leftFrame" title="leftFrame" >
    <frame src="common/content.jsp" name="mainFrame" id="mainFrame" title="mainFrame"  style="border:1px #1A76B7 solid;" noresize="false" />
	</frameset> </frameset>
	<noframes></noframes>


</html>

