package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.vo.BoardCommentVO;
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
			
		} else if(url.indexOf("download.do") != -1) {
			// 클릭한 첨부파일의 글번호
			int num = Integer.parseInt(request.getParameter("num"));
			String filename = dao.getFileName(num);
			System.out.println("첨부파일 이름 : " + filename);
			
			// 파일 다운로드 처리
			String path = Constants.UPLOAD_PATH + filename;
			byte b[] = new byte[4096];	// 바이트 배열
			
			// 파일을 읽기 위한 입력스트림
			FileInputStream fis = new FileInputStream(path);
			// 파일의 종류(마임타입)
			String mimeType = getServletContext().getMimeType(path);
			
			// 다운로드할 파일의 형식
			if(mimeType == null) {
				mimeType = "application/octet-stream;charset=utf-8";
			}
			
			// 한글 파일 이름이 깨지지 않도록 처리
			filename = new String(filename.getBytes("utf-8"), "8859_1");
			
			response.setHeader("Content-Disposition", 
					"attachment;filename="+filename);	// 헤더 전
			
			// body 전송
			// 출력 스트림 생성
			ServletOutputStream out = response.getOutputStream();
			int numRead;
			while(true) {
				numRead = fis.read(b, 0, b.length);	// 파일을 읽음
				if(numRead == -1) break;	// 내용이 있으면
				out.write(b, 0, numRead);	// 파일 저장
			}
			out.flush();	// 스트림을 비우기
			out.close();	// 출력스트림 닫기
			fis.close();	// 입력스트림 닫기
			
			// 다운로드 횟수 증가 처리
			dao.plusDown(num);
			
		} else if(url.indexOf("view.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			System.out.println("게시물번호 : " + num);
			dao.plusReadCount(num, request.getSession());	// 조회수 증가 처리
			BoardVO vo = dao.view(num, true);
			System.out.println("상세화면용 vo : " + vo);
			// 출력을 위해 request 영역에 저장
			request.setAttribute("vo", vo);
			
			// 출력 페이지로 이동
			String page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("pass_check.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			String passwd = request.getParameter("passwd");
			System.out.println("글번호 : " + num + ", 비번 : " + passwd);
			
			// 올바른 비밀번호인지 확인
			String result = dao.passwdCheck(num, passwd);
			System.out.println("비밀번호 체크 결과 : " + result);
			String page = "";
			// 비번이 맞으면 수정 화면으로 이동
			if(result != null) {
				page = "/board/edit.jsp";
				request.setAttribute("vo", dao.view(num, false));
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
				
			// 비번이 틀리면 되돌아감
			} else {
				page = request.getContextPath()
						+ "/board_servlet/view.do?num="+num+"&message=error";
				response.sendRedirect(page);
			}
			
			
		} else if(url.indexOf("update.do") != -1) {
			// 폼에서 입력한 값을 vo에 저장
			BoardVO vo = new BoardVO();
			
			// 첨부파일처리를 위한 MultipartRequest 선언
			MultipartRequest multi = new MultipartRequest(
					request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD,
					"utf-8", new DefaultFileRenamePolicy());
			
			String filename = " ";
			int filesize = 0;
			try {
				Enumeration files = multi.getFileNames();	// 첨부파일 집합
				while(files.hasMoreElements()) {
					String file1 = (String) files.nextElement();
					
					// 첨부파일의 이름
					filename = multi.getFilesystemName(file1);
					File f1 = multi.getFile(file1);
					
					// 첨부파일의 size
					if(f1 != null) {
						filesize = (int) f1.length();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// request => multi로 변경
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String passwd = multi.getParameter("passwd");
			String ip = request.getRemoteAddr();	// ip 주소
			int num = Integer.parseInt(multi.getParameter("num"));
			
			// 첨부파일 삭제 처리
			String fileDel = multi.getParameter("fileDel");
			
			// 체크가 되지 않으면 null, 체크되면 on
			if(fileDel != null && fileDel.equals("on")) {
				// 테이블에 저장된 파일 이름
				String fileName = dao.getFileName(num);
				File f = new File(Constants.UPLOAD_PATH + fileName);
				f.delete();	// 파일 삭제
				// 첨부파일 관련 레코드 정보 수정
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setSubject(subject);
				vo.setContent(content);
				vo.setPasswd(passwd);
				vo.setIp(ip);
				vo.setFilename("-");
				vo.setFilesize(0);
				vo.setDown(0);
				dao.update(vo);
			}
			
			vo.setNum(num);
			vo.setWriter(writer);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPasswd(passwd);
			vo.setIp(ip);
			
			if(filename == null || filename.trim().equals("")) {
				// 새로운 첨부파일이 없을 때(테이블의 정보를 가져옴)
				BoardVO vo2 = dao.view(num, false);
				String fName = vo2.getFilename();
				int fSize = vo2.getFilesize();
				int fDown = vo2.getDown();
				vo.setFilename(fName);
				vo.setFilesize(fSize);
				vo.setDown(fDown);
				
			} else {
				// 새로운 첨부파일이 있을 때
				vo.setFilename(filename);
				vo.setFilesize(filesize);
			}
			
			String result = dao.passwdCheck(num, passwd);
			// 비밀번호가 맞을 때
			if(result != null) {
				// dao에 update 요청
				dao.update(vo);
				// list.do로 이동
				String page = request.getContextPath() + "/board_servlet/list.do";
				response.sendRedirect(page);
				
			// 비밀번호가 틀렸을 때
			} else {
				request.setAttribute("vo", vo);
				String page = "/board/edit.jsp?pwd_error=y";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
			
		} else if(url.indexOf("delete.do") != -1) {
			// enctype = "nultipart/form-data"로 넘온 값은 request 객체로 받을 수 없음
//			int num = Integer.parseInt(request.getParameter("num"));
			MultipartRequest multi = new MultipartRequest(
					request, Constants.UPLOAD_PATH,
					Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("num"));
			System.out.println("삭제할 게시물번호 : " + num);
			
			// 레코드 삭제(숨김) 처리
			dao.delete(num);
			
			// 목록으로 이동
			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);
			
		} else if(url.indexOf("commentList.do") != -1) {
			// 게시물 번호
			int num = Integer.parseInt(request.getParameter("num"));
			// 댓글 목록이 넘어옴
			List<BoardCommentVO> list = dao.commentList(num);
			// 출력페이지에서 읽을 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 화면 전환
			String page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("commentAdd.do") != -1) {
			// view.jsp에서 넘어온 값들을 vo에 저장
			BoardCommentVO vo = new BoardCommentVO();
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			vo.setBoard_num(board_num);
			vo.setWriter(writer);
			vo.setContent(content);
			// 레코드가 추가됨
			dao.commentAdd(vo);
			// 실행이 끝나면 view.jsp의 콜백함수(success)로 넘어감
			
		} else if(url.indexOf("reply.do") != -1) {
			// 게시물 번호 조회
			int num = Integer.parseInt(request.getParameter("num"));
			
			// 게시물 내용을 vo로 받음
			BoardVO vo = dao.view(num, false);
			
			// 답변 작성의 편의를 위해 reply.jsp 페이지에 vo를 전달
			vo.setContent("====게시물의 내용====\n" + vo.getContent());
			request.setAttribute("vo", vo);
			String page = "/board/reply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("insertReply.do") != -1) {
			// 원글의 게시물번호(답글의 대상)
			int num = Integer.parseInt(request.getParameter("num"));
			
			// 원글 내용
			BoardVO vo = dao.view(num, false);
			int ref = vo.getRef();	// 답변 그룹 번호
			int re_step = vo.getRe_step() + 1;	// 출력순번
			int re_level = vo.getRe_level() + 1;	// 답변 단계
			
			// 답변 내용
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");
			
			vo.setWriter(writer);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPasswd(passwd);
			vo.setRef(ref);
			vo.setRe_level(re_level);
			vo.setRe_step(re_step);
			// 첨부파일 관련 정보
			vo.setFilename("-");
			vo.setFilesize(0);
			vo.setDown(0);
			// 답글 순서 조정
			dao.updateStep(ref, re_step);
			// 답글 쓰기
			dao.reply(vo);
			// 목록으로 이동
			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);
			
		}
	}

	// post 방식 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
