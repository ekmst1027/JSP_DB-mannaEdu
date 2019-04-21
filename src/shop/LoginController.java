package shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.vo.MemberVO;
import shop.dao.MemberDAO;

@WebServlet("/login_servlet/*")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String path = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		if(url.indexOf("login.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			String name = dao.loginCheck(vo);	// 로그인 체크
			// 로그인 실패 ==> 로그인 페이지로 이동
			if(name == null) {
				String page = path + "/shop/login.jsp?message=error";
				response.sendRedirect(page);
				
			// 로그인 성공 ==> 세션 저장 ==> 페이지 이동
			} else {
				HttpSession session = request.getSession();	// 세션 객체
				session.setAttribute("userid", userid);	// 세션 저장
				session.setAttribute("name", name);
				session.setAttribute("result", name+"님 환영합니다.");
				String page = "/shop/login_result.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
			
		} else if(url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();	// session 객체 생성
			session.invalidate();	// 세션의 모든 값들을 초기화시킴
			String page = path + "/shop/login.jsp?message=logout";
			response.sendRedirect(page);	// 로그인 페이지로 이동
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
