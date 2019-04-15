package memo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.dao.MemoDAO;
import memo.vo.MemoVO;

// 서블릿 매핑(클래스와 url pattern을 연결시키는 코드)
@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// get방식 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에서 요청한 url
		String url = request.getRequestURL().toString();
		System.out.println(url);
		MemoDAO dao = new MemoDAO();
		if(url.indexOf("list.do") != -1) {
			// 검색옵션, 검색키워드
			String searchkey = request.getParameter("searchkey");
			String search = request.getParameter("search");
			
			// 메모 리스트를 받음
			List<MemoVO> list = dao.listMemo(searchkey, search);	
			request.setAttribute("list", list);	// request 영역에 저장
			// 페이지 이동(포워딩)
			String page = "/memo/memo_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("insert.do") != -1) {
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			MemoVO vo = new MemoVO(writer, memo);
			System.out.println(vo);
			dao.insertMemo(vo);
			
		} else if(url.indexOf("view.do") != -1) {
			int idx =Integer.parseInt(request.getParameter("idx"));
			System.out.println(idx);
			// dao method 호출
			MemoVO vo = dao.viewMemo(idx);
			// forwarding
			request.setAttribute("vo", vo);
			String page = "/memo/memo_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("update.do") != -1) {
			// 폼에서 넘어온 값들을 vo에 저장
			int idx = Integer.parseInt(request.getParameter("idx"));
			String writer = request.getParameter("writer");
			String memo = request.getParameter("memo");
			MemoVO vo = new MemoVO();
			vo.setIdx(idx);
			vo.setWriter(writer);
			vo.setMemo(memo);
			dao.updateMemo(vo);
			// 페이지 이동
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
			
		} else if(url.indexOf("del.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			dao.deleteMemo(idx);
			// ajax 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
			
		} else if(url.indexOf("delete_all.do") != -1) {
			// 체크박스 배열이 넘어옴
			String[] idx = request.getParameterValues("idx");
			// 하나도 체크 안하면 null, 하나 이상 체크하면 배열로 넘어옴
			if(idx != null) {
				for(int i = 0; i < idx.length; i++) {
					dao.deleteMemo(Integer.parseInt(idx[i]));
				}
			}
			// 페이지 이동 처리
			response.sendRedirect(request.getContextPath() + "/memo/memo.jsp");
		}
	}

	// post방식 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
