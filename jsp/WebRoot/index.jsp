<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(path);
System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    你好，java web <br>
   <!--  指令，表达式，静态资源，声明，小脚本，注释 -->
   
   <%int num1=1; %>
   <%!int num2=1; %>
   
   num1:<%=num1++ %><br/>
   num2:<%=num2++ %>
   
   <%!
   		public int add(int x,int y){
   			return x+y;
   		}
    %>
    
   <%--jsp注释 --%> 
   <!--  HTML注释 -->
    
    <%
    	/* out是jsp的9大内置对象之一 */
    	out.print(add(1,2));
    	
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
     %>
   
  </body>
</html>
