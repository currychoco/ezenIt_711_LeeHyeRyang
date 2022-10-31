<%@page import="rent.RentDao"%>
<%@page import="rent.RentDto"%>
<%@page import="java.sql.Timestamp"%>
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
	
	//no파라미터 값 가져오기
	request.getParameter("no");
	int no = Integer.parseInt(request.getParameter("no"));
	
	//no값으로 RentDto 가져오기
	RentDao dao = RentDao.getInstance();
	RentDto rent = dao.getRentByNo(no);
	
	//현재시간
	Timestamp curTime = new Timestamp(System.currentTimeMillis());

	// 
	Timestamp sDate = rent.getsDate();
	if(curTime.before(sDate)){
		System.out.println("아직 대여시간이 시작되지 않았습니다.");
	}
	//제 시간에 반납했을 경우
	else if(curTime.before(rent.geteDate())){
		dao.returnCar(rent);
	}
	//시간을 넘어서 초과했을 경우
	else{
		//초과 시간을 구한다(분단위)
		long div = curTime.getTime() - rent.geteDate().getTime();
		int min = (int)(div/(60*1000));
		dao.returnCar(rent, min);
	}
	response.sendRedirect("myPage");
	%>
</body>
</html>