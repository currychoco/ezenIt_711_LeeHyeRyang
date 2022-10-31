package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 파라미터로 넘겨받은 id/pw가 기존 보유하고 있는 멤버와 일치하는 경우
		// session에 log값을 부여
		HttpSession session = request.getSession();
		session.setAttribute("log", "apple"); // setAttribute() -> 활용
		
		//request.setAttribute("list", ArrayList);
		
		//
		String log = (String)session.getAttribute("log"); // <- 형 변환 해야함 return type : Object
		if(log != null) {
			// 로그인 회원에게만 처리될 로직
			session.removeAttribute("log");
		}else {
			// 로그인 페이지로 흐름 제어 또는 홈으로 돌려보내기
		}
		
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
