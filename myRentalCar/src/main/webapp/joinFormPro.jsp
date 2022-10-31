<%@page import="user.UserDao"%>
<%@page import="user.UserDto"%>
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
		
		UserDto user = null;
		UserDao dao = UserDao.getInstance();
		
		if(request.getParameter("id") != null) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String license = request.getParameter("license");
			
			System.out.println(name);
			System.out.println(id);
			System.out.println(address);
			
			user = new UserDto(name, id, password, license, phone, address);
			dao.join(user);	
			response.sendRedirect("index");
		}else {
			response.sendRedirect("index");
		}
		%>
</body>
</html>