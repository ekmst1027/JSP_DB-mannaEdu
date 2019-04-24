package shop;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Constants.Constants;
import shop.dao.AdminDAO;
import shop.dao.ProductDAO;
import shop.vo.MemberVO;
import shop.vo.ProductVO;

@WebServlet("/admin_servlet/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String path = request.getContextPath();
		
		// 로그인
		if(url.indexOf("login.do") != -1) {
			AdminDAO dao = new AdminDAO();
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPasswd(passwd);
			String name = dao.loginCheck(vo);
			// 로그인 실패
			if(name == null) {
				String page = path + "/shop/admin_login.jsp?message=error";
				response.sendRedirect(page);
			// 로그인 성공
			} else {
				// 세션 객체 생성
				HttpSession session = request.getSession();
				// 세션 변수 저장
				session.setAttribute("admin_userid", vo.getUserid());
				session.setAttribute("admin_name", name);
				session.setAttribute("userid", userid);
				session.setAttribute("name", name);
				session.setAttribute("result", name + "님 환영합니다.");
				String page = "/shop/admin_result.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
				
			}
			
		// 로그아웃
		} else if(url.indexOf("logout.do") != -1) {
			// 세션 객체 생성
			HttpSession session = request.getSession();
			// 세션 초기화
			session.invalidate();
			// 로그인 페이지로 이동
			String page = path + "/shop/admin_login.jsp?message=logout";
			response.sendRedirect(page);
			
		// 상품추가
		} else if(url.indexOf("product_insert.do") != -1) {
			ProductDAO dao = new ProductDAO();
			// 파일 업로드 처리
			// request를 확장시킨 MultipartRequest 생성
			// new MultipartRequest(request, 파일업로드 디렉토리, 업로드용량, 파일인코딩, 중복파일정책)
			MultipartRequest multi = new MultipartRequest(
					request, 
					"/Users/kyeongmin/Documents/Java_study/basic_mannaEdu/.metadata/.plugins/"
					+ "org.eclipse.wst.server.core/tmp0/wtpwebapps/jsp02/images/",
					Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy()
					);
			
			String product_name = multi.getParameter("product_name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String filename = " ";	// 공백 1개
			
			try {
				// 첨부파일의 집합
				Enumeration files = multi.getFileNames();
				// 다음 요소가 있으면
				while(files.hasMoreElements()) {
					// 첨부파일의 이름
					String file1 = (String) files.nextElement();
					filename = multi.getFilesystemName(file1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ProductVO vo = new ProductVO();
			vo.setProduct_name(product_name);
			vo.setPrice(price);
			vo.setDescription(description);
			
			// 첨부 파일이 없을 때
			// trim() 문자열의 좌우 공백 제거
			if(filename == null || filename.trim().equals("")) {
				filename = "-";
			}
			vo.setPicture_url(filename);
			dao.insertProduct(vo);
			
			// 목록으로 이동
			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);
			
		// 상품 상세정보
		} else if(url.indexOf("edit.do") != -1) {
			ProductDAO dao = new ProductDAO();
			// 상품 코드
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			// 상품 코드에 해당하는 상세 정보 리턴 
			ProductVO vo = dao.detailProduct(product_id);
			// 출력하기 전에 request 영역에 저장
			request.setAttribute("vo", vo);
			// 편집폼 페이지로 포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_edit.jsp");
			rd.forward(request, response);
			
		// 상품수정
		} else if(url.indexOf("update.do") != -1) {
			ProductDAO dao = new ProductDAO();
			MultipartRequest multi = new MultipartRequest(
					request, 
					"/Users/kyeongmin/Documents/Java_study/basic_mannaEdu/.metadata/.plugins/"
					+ "org.eclipse.wst.server.core/tmp0/wtpwebapps/jsp02/images/",
					Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy()
					);
			
			String picture_url = " ";
			int filesize = 0;
			try {
				Enumeration files = multi.getFileNames();
				while(files.hasMoreElements()) {
					String file1 = (String) files.nextElement();
					// 첨부파일의 이름
					picture_url = multi.getFilesystemName(file1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// request => multi로 변경
			String product_name = multi.getParameter("product_name");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			int product_id = Integer.parseInt(multi.getParameter("product_id"));
			
			ProductVO vo = new ProductVO();
			vo.setProduct_id(product_id);
			vo.setProduct_name(product_name);
			vo.setPrice(price);
			vo.setDescription(description);
			
			if(picture_url == null || picture_url.trim().equals("")) {
				// 새로운 첨부파일이 없을 때(테이블의 정보를 가져옴)
				ProductVO vo2 = dao.detailProduct(product_id);
				picture_url = vo2.getPicture_url();
				vo.setPicture_url(picture_url);
			} else {
				// 새로운 첨부파일이 있을 때
				vo.setPicture_url(picture_url);
			}
			
			dao.updateProduct(vo);
			
			// 목록으로 이동
			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);
			
		// 상품삭제
		} else if(url.indexOf("delete.do") != -1) {
			ProductDAO dao = new ProductDAO();
			MultipartRequest multi = new MultipartRequest(
					request, 
					"/Users/kyeongmin/Documents/Java_study/basic_mannaEdu/.metadata/.plugins/"
					+ "org.eclipse.wst.server.core/tmp0/wtpwebapps/jsp02/images/",
					Constants.MAX_UPLOAD, "utf-8", new DefaultFileRenamePolicy()
					);
			
			int product_id = Integer.parseInt(multi.getParameter("product_id"));
			dao.deleteProduct(product_id);
			
			// 목록으로 이동
			String page = path + "/product_servlet/list.do";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
