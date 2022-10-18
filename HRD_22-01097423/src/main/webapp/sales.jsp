<%@page import="member.SalesMemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="money.MoneyDao"%>
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
MoneyDao moneyDao = MoneyDao.getInstance();
ArrayList<SalesMemberDto> list = moneyDao.getSales();
%>
	<jsp:include page="header.jsp"/>
	
	<section>
		<table>
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원성명</th>
					<th>고객등급</th>
					<th>매출</th>
				</tr>
			</thead>
			<tbody>
				<%for(SalesMemberDto s : list){ %>
					<tr>
						<td><%=s.getCustno() %></td>
						<td><%=s.getCustname() %></td>
						<td><%=s.getGrade() %></td>
						<td><%=s.getPrice() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</section>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>