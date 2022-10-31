<%@page import="board.BoardDto"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

</head>
<script>

function submitDelete() {
	let no = document.getElementById("no");
	
	let form = document.getElementById("my-form");
	form.setAttribute("action", "boardDelete");
	form.submit();
}

function submitUpdate() {
	let form = document.getElementById("my-form");
	form.setAttribute("action", "boardUpdateForm.jsp");
	form.submit();
}

</script>
<body>
	<%
	BoardDao dao = BoardDao.getInstance();
	BoardDto board = null;
	
	request.setCharacterEncoding("utf-8");
	System.out.println(request.getParameter("no"));
	if(request.getParameter("no") != null){
		int no = Integer.parseInt(request.getParameter("no"));
		dao.viewCnt(no);
		board = dao.getBoardByNo(no);%>	
	
    <h1>EZEN BOARD</h1>
    <div class="form-container">
        <form id="my-form" method="post">
            <input type="text" value="<%=board.getTitle() %>" readonly>
            <textarea rows="20" readonly>
            <%=board.getContent() %>
            </textarea>
            <input type="hidden" id="no" name = "no" value="<%=no %>">
            <input type="button" onclick="location.href='board'" value="글목록">
            <input type="button" onclick="submitUpdate()" value="수정하기">
            <input type="button" onclick="submitDelete()" value="삭제하기" />
        </form>
    </div>
    <%}
    else {
    	response.sendRedirect("index"); // board 조회 실패
    }
    %>
</body>

</html>