<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="rent.RentDao"%>
<%@page import="rent.RentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%
	if(session.getAttribute("id")!=null){
		String id = (String)session.getAttribute("id");
		
		RentDao dao = RentDao.getInstance();
		List<RentDto> list = dao.getRentAllById(id);
		System.out.println(list.size());
	%>
	<jsp:include page="header.jsp"/> 
	<section>
		<table>
			<thead>
				<tr>
					<th>예약코드</th>
					<th>자동차 코드</th>
					<th>예약일</th>
					<th>시작일</th>
					<th>종료일</th>
					<th>가격</th>
					<th>추가요금</th>
					<th>반납여부</th>
				</tr>
			</thead>
			<tbody>
				<%for(RentDto rent : list){ 
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				Timestamp curTime = new Timestamp(System.currentTimeMillis());
				%>
				<tr>
					<td><%=rent.getNo() %></td>
					<td><%=rent.getCarno() %></td>
					<td><%=sdf.format(rent.getResDate()) %></td>
					<td><%=sdf.format(rent.getsDate()) %></td>
					<td><%=sdf.format(rent.geteDate()) %></td>
					<td><%=rent.getPrice() %></td>
					<td><%=rent.getExtra() %></td>
					<td><%=rent.getCarReturn() %></td>
					<%if(!rent.getCarReturn() && curTime.after(rent.getsDate())){%>
						<td><button onclick='location.href="returnCarPro.jsp?no=<%=rent.getNo()%>"'>반납</button></td>
					<%}else if(curTime.before(rent.getsDate())){%>
						<td><button onclick='location.href="rentCancelPro.jsp?no=<%=rent.getNo()%>"'>취소</button></td>
					<%} %>
				</tr>
				<%} %>
			</tbody>
		</table>
	</section>
	<jsp:include page="footer.jsp"/>
	<%} else{
		response.sendRedirect("login");
	}%>
</body>
</html>