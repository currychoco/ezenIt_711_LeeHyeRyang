<%@page import="rent.RentDao"%>
<%@page import="car.CarDto"%>
<%@page import="rent.RentDto"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="user.UserDto"%>
<%@page import="user.UserDao"%>
<%@page import="car.CarDao"%>
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

	if(request.getParameter("no")!=null){
		// userno과 carno 구하기
		UserDao dao = UserDao.getInstance();
		String userId = (String)session.getAttribute("id");
		UserDto user = dao.getUserById(userId);
		int userno = user.getNo();
		int carno = Integer.parseInt(request.getParameter("no"));
		
		//시작일 구하기
		String sday = request.getParameter("sDate");
		sday = sday.replace("T"," ") + ":00";
		Timestamp sDate = Timestamp.valueOf(sday);
		
		//이용시간을 이용하여 종료시간 구하기
		//long retryDate = System.currentTimeMillis();

        int sec = 600;
        int useTime = Integer.parseInt(request.getParameter("useTime"));

        //Timestamp original = new Timestamp(sDate);

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(sDate.getTime());

        cal.add(Calendar.HOUR, useTime);

        Timestamp eDate = new Timestamp(cal.getTime().getTime());
        System.out.println(sDate);
        System.out.println(eDate);
		
        //총 이용가격 구하기
        CarDao cardao = CarDao.getInstance();
        CarDto car = cardao.getCarByNo(carno);
        int carPrice = car.getPrice();
        int price = carPrice * useTime;
        
        RentDao rentdao = RentDao.getInstance();
        RentDto rent = new RentDto(userno,carno,sDate,eDate,price);
		rentdao.setRentCar(rent);
		response.sendRedirect("myPage");
	}else{
		response.sendRedirect("rentCar");
	}

%>
</body>
</html>