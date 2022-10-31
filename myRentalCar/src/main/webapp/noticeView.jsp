<%@page import="notice.NoticeDao"%>
<%@page import="notice.NoticeDto"%>
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
	NoticeDao dao = NoticeDao.getInstance();

	if(request.getParameter("no") != null){
		int no = Integer.parseInt(request.getParameter("no"));
		dao.viewCnt(no);
		NoticeDto notice = dao.getNotice(no);%>	
	
    <h1>EZEN BOARD</h1>
    <div class="form-container">
        <form id="my-form" method="post">
            <input type="text" value="<%=notice.getTitle() %>" readonly>
            <textarea rows="20" readonly>
            <%=notice.getContent() %>
            </textarea>
            <input type="button" onclick="location.href='notice'" value="목록">
        </form>
    </div>
    <%}
    else {
    	response.sendRedirect("notice"); // board 조회 실패
    }
    %>
</body>
</html>