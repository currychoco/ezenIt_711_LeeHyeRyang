<%@page import="member.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDao"%>
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
MemberDao memberDao=MemberDao.getInstance();
ArrayList<MemberDto> list = memberDao.getMemberAll();
%>
	<jsp:include page="header.jsp"/>
	
	<section>
		<h3>회원목록조회/수정</h3>
		<table>
			<thead>
				<tr>
					<th>회원번호</th>
					<th>회원성명</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>가입일자</th>
					<th>고객등급</th>
					<th>거주지역</th>
				</tr>
			</thead>
			<tbody>
				<%for(MemberDto member : list){ %>
					<tr>
						<td><a href="update.jsp?custno=<%=member.getCustno()%>"><%=member.getCustno() %></a></td>
						<td><%=member.getCustname() %></td>
						<td><%=member.getPhone() %></td>
						<td><%=member.getAddress() %></td>
						<td><%=member.getJoindate() %></td>
						<td><%=member.toStringGrade() %></td>
						<td><%=member.getCity() %></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</section>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>