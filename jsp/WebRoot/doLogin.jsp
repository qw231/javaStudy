<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//post请求的中文乱码处理
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=UTF-8");

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	if("admin".equals(username) && "123".equals(password)){
		//response.sendRedirect("/java97jsp/jsp/welcome.jsp?username="+username);
		//request.setAttribute("uname", username);
		
		session.setAttribute("uname", username);
		//session.setMaxInactiveInterval(5);
		
		//重定向
		response.sendRedirect("/java97jsp/jsp/welcome.jsp");
		
		//转发
		//request.getRequestDispatcher("/jsp/welcome.jsp").forward(request,response);
		
		//response.sendRedirect("/java97jsp/WEB-INF/bdqn.jsp");无效
		//request.getRequestDispatcher("/WEB-INF/bdqn.jsp").forward(request,response);
	}else{
		//response.sendRedirect("/java97jsp/login.jsp?msg=用户名或密码错误");//不识别中文
		
		//session.setAttribute("msg", "用户名或密码错误");
		//response.sendRedirect("/java97jsp/login.jsp");
		
		out.println("<script type='text/javascript'>alert('用户名或密码错误');location.href='/java97jsp/login.jsp';</script>");	
		
	}
	
	//username = new String(username.getBytes("iso-8859-1"),"utf-8");
	
	//System.out.println(username+","+password);
	
	/* 1.若用户名和密码是admin,123,跳转到welcome.jsp,在该页面显示当前用户的用户名
	2.若用户名是中文，如何处理
	3.转发和重定向的区别 */
%>