package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_servlet/*")
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 요청한 주소
		String url = request.getRequestURI();
		MemberDAO dao = new MemberDAO();	// dao 객체 생성
		if(url.indexOf("login.do") != -1) {
			// 아이디, 비번을 vo에 저장
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			System.out.println(vo);
			String page = "/ch07/session_login.jsp";	// 로그인 페이지 
			
			// BCrypt 방식의 보안 로그인
			String result = dao.loginCheckBcrypt(vo);
			request.setAttribute("result", result);
			if(!result.equals("로그인 실패")) {
				HttpSession session = request.getSession();	// 세션 객체 생성 
				session.setAttribute("userid", userid);	// 세션변수 저장
				session.setAttribute("message", result);
				page = "/ch07/main.jsp";	// 로그인 성공 페이지
			}
			// 페이지 이동
			response.sendRedirect(request.getContextPath() + page);
			
		} else if(url.indexOf("logout.do") != -1) {
			// 세션 초기화(전체 삭제)
			// request.getSession().invalidate();
			// 세션 객체 생성
			HttpSession session = request.getSession();
			// 세션값을 모두 초기화시킴
			session.invalidate();
			// 페이지 이동
			String page = request.getContextPath() + "/ch07/session_login.jsp?message=logout";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
