<%@page import="car.CarDto"%>
<%@page import="java.util.List"%>
<%@page import="car.CarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%
	CarDao dao = CarDao.getInstance();
	List<CarDto> list = dao.getCarAll();
	%>
	<jsp:include page="header.jsp"/>
	<section>
		<table>
			<thead>
				<tr>
					<th>차량코드</th>
					<th>차량번호</th>
					<th>차종류</th>
					<th>1시간당 가격</th>
					<th>이미지</th>
					<th>연도</th>
					<th>연료</th>
				</tr>
			</thead>
			<tbody>
				<%for(CarDto car : list){ %>
				<tr>
					<td><%=car.getNo() %></td>
					<td><%=car.getCar_no() %></td>
					<td><%=car.getName() %></td>
					<td><%=car.getPrice() %></td>
					<td><img src="<%=car.getImg()%>" height="200px" width="400px"></td>
					<td><%=car.getDOM() %></td>
					<td><%=car.getFuel() %></td>
					<td><button onclick="location.href='rentCar?no=<%=car.getNo()%>'">대여</button></td>
				</tr>
				<%} %>
			</tbody>
		</table>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>