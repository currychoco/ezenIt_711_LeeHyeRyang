<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/grid.css">
    <title>EZEN RENTAL CAR</title>
</head>
<body>
 	<header>
        <h1><a href = "index">EZEN RENTAL CAR</a></h1>
        <%if(session.getAttribute("id")!=null){ 
	        String id =(String)session.getAttribute("id");
	      	System.out.println(id);%>
	        <span><b><%=id %></b>님 안녕하세요!</span>
	       <p><a href="logoutPro.jsp">로그아웃</a></p>
        <%}else{%>
        	<button onclick="location.href='login'">로그인</button>
	        <button onclick="location.href='join'">회원가입</button>
	    <%} %>
    </header>
    <nav>
        <ul>
            <li><a href="rental">차량대여</a></li>
            <li><a href="board">커뮤니티</a></li>
            <li><a href="notice">공지사항</a></li>
            <li><a href="myPage">마이페이지</a></li>
        </ul>
    </nav>
    <aside>
        광고이미지
    </aside>
</body>
</html>