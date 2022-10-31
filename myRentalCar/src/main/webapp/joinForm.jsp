<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<section>
		<form method="post" action="joinFormPro.jsp">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" id="name" name="name"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<th>휴대폰 번호</th>
					<td><input type="text" id="phone" name="phone"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" id="address" name="address"></td>
				</tr>
				<tr>
					<th>면허 번호</th>
					<td><input type="text" id="license" name="license"></td>
				</tr>
			</table>
			<input type="submit" value="가입">
		</form>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>