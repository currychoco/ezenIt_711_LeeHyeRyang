package com.board.board.controller;
//요청에 대한 Model과 View를 호출 

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.board.controller.action.Action;

/**
* Servlet implementation class BoardServlet
*/
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 /**
  * @see HttpServlet#HttpServlet()
  */
 public BoardServlet() {
     super();
     // TODO Auto-generated constructor stub
 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String command = (String) request.getParameter("command");
		System.out.printf("[%s]에서 요청받음을 확인함\n", command);
		
		// 액션팩토리를 통한 액션객체 생성 시도 
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);
		if(action != null)
			action.execute(request, response); // 성공하면 액션 처리진행 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
