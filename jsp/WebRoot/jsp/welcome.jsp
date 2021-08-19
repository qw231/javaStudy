<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//String name = request.getParameter("username");
	//String name = (String)request.getAttribute("uname");
	
	String name = (String)session.getAttribute("uname");
 %>
 
 <%
 	if(name==null){
 		out.println("<script type='text/javascript'>alert('你还未登录');location.href='/java97jsp/login.jsp';</script>");	
 	}
  %>
 
<%=name %>,welcome<a href="/java97jsp/action/loginout.jsp">注销</a>
</body>
</html>