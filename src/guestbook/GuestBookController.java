package guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.GuestBookDAO;
import guestbook.vo.GuestBookVO;

@WebServlet("/guestbook_servlet/*")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 사용자가 요청한 주소
		GuestBookDAO dao = new GuestBookDAO();
		if(uri.indexOf("list.do") != -1) {
			System.out.println("list.do");
			String searchkey = request.getParameter("searchkey");
			// 검색옵션이 null일 경우 이름으로 찾기로 설정
			if(searchkey==null) searchkey = "name";
			String search = request.getParameter("search");
			if(search==null) search="";
			List<GuestBookVO> list = dao.getList(searchkey, search);	// 방명록 리스트 리턴
			request.setAttribute("list", list);	// request 영역에 저장
			// 검색옵션과 검색키워드를 request 영역에 저장하여 다시 보내줌
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			
			// list.jsp로 포워딩하여 출력시킴
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/list.jsp");
			rd.forward(request, response);
			
		} else if(uri.indexOf("passwd_check.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String passwd = request.getParameter("passwd");
			System.out.println("글번호 : " + idx);
			System.out.println("비밀번호 : " + passwd);
			boolean result = dao.passwdCheck(idx, passwd);
			
			String url = "";
			// 비밀번호가 맞으면
			if(result) {
				url = "/guestbook/edit.jsp";
				// 게시물 번호에 해당하는 레코드가 vo에 저장됨
				GuestBookVO vo = dao.gbDetail(idx);
				// edit.jsp 페이지에 출력시키기 위해 request 영역에 저장
				request.setAttribute("vo", vo);
				
			// 비밀번호가 틀리면
			} else {
				url = "/guestbook_servlet/list.do";
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		} else if(uri.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			GuestBookVO vo = new GuestBookVO();
			vo.setIdx(idx);
			vo.setName(name);
			vo.setEmail(email);
			vo.setPasswd(passwd);
			vo.setContent(content);
			dao.gbUpdate(vo);	// 레코드가 수정됨
			// list.do로 페이지 주소가 변경됨
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
			
		} else if(uri.indexOf("delete.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			// dao 메소드 호출
			dao.gbDelete(idx);
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
			
		} else if(uri.indexOf("insert.do") != -1) {
			// write.jsp에서 입력한 내용들을 받아서 vo에 저장
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");
			GuestBookVO vo = new GuestBookVO();
			vo.setName(name);
			vo.setEmail(email);
			vo.setContent(content);
			vo.setPasswd(passwd);
			// 레코드가 추가됨
			dao.gbInsert(vo);
			// 목록 화면으로 이동
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
