<%@page import="notice.NoticeDto"%>
<%@page import="java.util.List"%>
<%@page import="notice.NoticeDao"%>
<%@page import="board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
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
	NoticeDao dao = NoticeDao.getInstance();
	List<NoticeDto> list = dao.getNoticeAll();
	%>
	<jsp:include page="header.jsp"/>
	<section>
        <table>
            <thead>
                <tr>
                    <th>no</th>
                    <th width="200px">title</th>
                    <th>manager</th>
                    <th>regDate</th>
                    <th>viewCnt</th>
                </tr>
            </thead>
            <tbody>
            <%for(NoticeDto n : list) {%>
                <tr>
                    <td><%=n.getNo() %></td>
                    <td><a href="noticeView?no=<%=n.getNo()%>"><%=n.getTitle() %></a></td>
                    <td><%=n.getWriter() %></td>
                    <td><%=n.getRegDate() %></td>
                    <td><%=n.getViewCnt() %></td>
                </tr>
            <%} %>
            </tbody>
        </table>
    </section>
	<jsp:include page="header.jsp"></jsp:include>
</body>
</html>