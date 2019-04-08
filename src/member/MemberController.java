package member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// url pattern 정의
@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청한 url
		String url = request.getRequestURI().toString();
		// 컨텍스트 패스
		String context = request.getContextPath();
		MemberDAO dao = new MemberDAO();
		if(url.indexOf("list.do") != -1) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<MemberVO> list = dao.memberList();
			// 맵에 저장
			map.put("list", list);
			map.put("count", list.size());
			request.setAttribute("map", map);
			System.out.println(map);
			String page = "/ch06/member_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("join.do") != -1) {
			// member.jsp에서 넘오언 값들을 vo에 저장
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberVO vo = new MemberVO(userid, passwd, name, address, tel);
			System.out.println(vo);
			int rows = dao.insert(vo);
			System.out.println("추가된 레코드 수 : " + rows);
			
		} else if(url.indexOf("view.do") != -1) {
			String userid = request.getParameter("userid");
			MemberVO vo = dao.memberDetail(userid);
			request.setAttribute("vo", vo);
			String page = "/ch06/member_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("update.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			MemberVO vo = new MemberVO(userid, passwd, name, address, tel);
			dao.update(vo);
			response.sendRedirect(context + "/ch06/member.jsp");
			
		} else if(url.indexOf("delete.do") != -1) {
			String userid = request.getParameter("userid");
			dao.delete(userid);
			response.sendRedirect(context + "/ch06/member.jsp");
		} else if(url.indexOf("login.do") != -1) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			System.out.println("아이디 : " + userid);
			System.out.println("패스워드 : " + passwd);
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			String result = dao.loginCheck(vo);
			System.out.println(result);
			request.setAttribute("result", result);
			String page = "/ch06/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if(url.indexOf("join_bcrypt.do") != -1) {
			// BCrypt 방식의 비밀번호 암호화
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			vo.setName(name);
			dao.insertBcrypt(vo);
		} else if(url.indexOf("login_bcrypt.do") != -1) {
			// BCrypt 방식의 보안 로그인
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			String result = dao.loginCheckBcrypt(vo);
			request.setAttribute("result", result);
			String page = "/ch06/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("join_sha.do") != -1) {
			// sha256 방식의 비밀번호 암호화
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			vo.setName(name);
			dao.insertSha256(vo);
		} else if(url.indexOf("login_sha.do") != -1) {
			// sha256 방식의 보안 로그인
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			String result = dao.loginCheckSha256(vo);
			request.setAttribute("result", result);
			String page = "/ch06/login_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
