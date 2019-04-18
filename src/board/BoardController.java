package board;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.vo.BoardVO;
import common.Constants.Constants;


@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// get 방식 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청한 주소 
		String url = request.getRequestURL().toString();
		BoardDAO dao = new BoardDAO();
		
		// 스트링.indexOf("키워드") : 키워드가 포함된 위치값, 없으면 -1
		if(url.indexOf("list.do") != -1) {
//			System.out.println("list.do....");
			List<BoardVO> list = dao.list();	// 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 출력 페이지로 이동
			String page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("insert.do") != -1) {
			BoardVO vo = new BoardVO();
			File uploadDir = new File(Constants.UPLOAD_PATH);
			
			// 디렉토리가 없으면 디렉토리 생성
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			// request 객체를 확장한 MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest(
					request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());
			
			String filename = "";
			int filesize = 0;
			try {
				Enumeration files = multi.getFileNames();	// 업로드 파일 집합
				while(files.hasMoreElements()) {
					String file1 = (String)files.nextElement();	// 다음 파일
					filename = multi.getFilesystemName(file1);	// 파일 이름
					File f1 = multi.getFile(file1);
					if(f1 != null) {
						filesize = (int)f1.length();	// 파일 크기
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String passwd = multi.getParameter("passwd");
			String ip = request.getRemoteAddr();	// 클라이언트의 ip 주소
			vo.setWriter(writer);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPasswd(passwd);
			vo.setIp(ip);
			
			// 업로드한 파일이 없을 경우의 처리
			if(filename == null || filename.equals("")) {
				filename = "-";
			}
			
			vo.setFilename(filename);
			vo.setFilesize(filesize);
			System.out.println(vo);
			dao.insert(vo);	// 레코드가 추가됨
			// 게시물 목록 갱신
			String page = request.getContextPath() + "/board_servlet/list.do";
			response.sendRedirect(page);
		}
	}

	// post 방식 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
