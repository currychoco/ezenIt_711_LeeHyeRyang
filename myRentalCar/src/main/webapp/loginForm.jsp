<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<section>
		<form method="post" action="loginFormPro.jsp">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" id="id" name="id"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
			</table>
			<input type="submit" value="로그인">
		</form>
	</section>
	<jsp:include page="footer.jsp"/>
</body>
</html>