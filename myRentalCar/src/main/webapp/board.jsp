<%@page import="board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
<body>
	<%
	BoardDao dao = BoardDao.getInstance();
	ArrayList<BoardDto> list = dao.getBoardAll();
	%>
	<jsp:include page="header.jsp"/>
	<section>
        <table>
            <thead>
                <tr>
                    <th>no</th>
                    <th width="200px">title</th>
                    <th>user</th>
                    <th>regDate</th>
                    <th>modDate</th>
                    <th>viewCnt</th>
                </tr>
            </thead>
            <tbody>
            <%for(BoardDto board : list) {%>
                <tr>
                    <td><%=board.getNo() %></td>
                    <td><a href="boardView.jsp?no=<%=board.getNo()%>"><%=board.getTitle() %></a></td>
                    <td><%=board.getUser() %></td>
                    <td><%=board.getRegDate() %></td>
                    <td><%=board.getModDate() %></td>
                    <td><%=board.getViewCnt() %></td>
                </tr>
            <%} %>
            </tbody>
        </table>
        <button onclick="location.href='boardWriteForm.jsp'">글쓰기</button>
    </section>
    <jsp:include page="footer.jsp"/>
</body>

</html>