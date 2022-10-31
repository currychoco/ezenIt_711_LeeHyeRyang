<%@page import="rent.RentDao"%>
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
	if(request.getParameter("no")!=null){
		RentDao dao = RentDao.getInstance();
		
		int no = Integer.parseInt(request.getParameter("no"));
		dao.rentCancel(no);
		response.sendRedirect("myPage");
	}
	%>
</body>
</html>