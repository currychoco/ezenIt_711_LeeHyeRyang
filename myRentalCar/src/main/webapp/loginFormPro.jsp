<%@page import="user.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	UserDao dao = UserDao.getInstance();
	
	if(request.getParameter("id")!= null && request.getParameter("password")!=null){
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean login = dao.login(id, password);
		
		if(login){
			session.setAttribute("id", id);
			System.out.println("세션저장성공");
			response.sendRedirect("index");
		}else{
			System.out.println("세션저장실패");
			response.sendRedirect("index");
		}
	}else{
		response.sendRedirect("index");
	}
	%>
</body>
</html>