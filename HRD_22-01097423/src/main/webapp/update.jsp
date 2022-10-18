<%@page import="member.MemberDao"%>
<%@page import="member.MemberDto"%>
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
int custno = Integer.parseInt(request.getParameter("custno"));

MemberDao memberDao = MemberDao.getInstance();
MemberDto member = memberDao.getMember(custno);
%>
	<jsp:include page="header.jsp"/>
	
	<section>
		<form method="POST" action="updatePro.jsp">
			<table>
				<tr>
					<th>회원번호</th>
					<td>
						<input type="text" id="custno" name="custno" value=<%=custno %> readonly>
					</td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input type="text" id="custname" name="custname" value=<%=member.getCustname() %>></td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td><input type="text" id="phone" name="phone" value=<%=member.getPhone() %>></td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td><input type="text" id="address" name="address" value=<%=member.getAddress()%>></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input type="text" id="joindate" name="joindate" value=<%=member.getJoindate() %> readonly></td>
				</tr>
				<tr>
					<th>고객등급[A:VIP,B:일반,C:직원]</th>
					<td>
						<select id="grade" name="grade">
							<option value="A">A</option>
							<option value="B">B</option>
							<option value="C">C</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td><input type="text" id="city" name="city" value=<%=member.getCity() %>></td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input type="submit" value="수정">
						<button type="button" onclick="location.href='list.jsp'">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>