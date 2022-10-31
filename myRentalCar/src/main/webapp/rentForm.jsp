<%@page import="car.CarDto"%>
<%@page import="car.CarDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");

	if(session.getAttribute("id")==null){
		response.sendRedirect("login");
	}else{
		CarDto car = null;
		
		if(request.getParameter("no")!=null){
			System.out.println(request.getParameter("no"));
			int no = Integer.parseInt(request.getParameter("no"));
			
			CarDao dao = CarDao.getInstance();	
			car = dao.getCarByNo(no);
		}
		int extra = (int)(car.getPrice()*0.03);
		System.out.println(extra);
	%>
	<jsp:include page="header.jsp"/>
	<section>
	<table>
		<tr>
			<th>이미지</th>
			<td><img src="<%=car.getImg()%>" height="200px" width="400px"></td>
		</tr>
		<tr>
			<th>차량코드</th>
			<td><%=car.getNo() %></td>
		</tr>
		<tr>
			<th>차량번호</th>
			<td><%=car.getCar_no() %></td>
		</tr>
		<tr>
			<th>차종류</th>
			<td><%=car.getName() %></td>
		</tr>
		<tr>
			<th>1시간당 가격</th>
			<td><%=car.getPrice() %>원</td>
		</tr>
		<tr>
			<th>추가 금액</th>
			<td>(1분)<%=extra%>원</td>
		</tr>
		<tr>
			<th>연도</th>
			<td><%=car.getDOM() %></td>
		</tr>
		<tr>
			<th>연료</th>
			<td><%=car.getFuel() %></td>
		</tr>
	</table>
		<form method="post" action="rentFormPro.jsp">
			<input type="hidden" value="<%=car.getNo() %>" name="no" id="no">
			<table>
				<tr>
					<th>시작일</th>
					<td><input type="datetime-local" name="sDate" id="sDate"></td>
				</tr>
				<tr>
					<th>이용시간</th>
					<td>
						<select name="useTime">
							<option value="4">4시간</option>
							<option value="8">8시간</option>
							<option value="12">12시간</option>
							<option value="24">24시간</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="submit" value="예약">
		</form>
	</section>
	<%} %>
	<jsp:include page="footer.jsp"/>
</body>
</html>