package shop;

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
import javax.servlet.http.HttpSession;

import shop.dao.CartDAO;
import shop.vo.CartVO;

@WebServlet("/cart_servlet/*")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();	// 요청한 주소
		String path = request.getContextPath();	// 컨텍스트 패스
		CartDAO dao = new CartDAO();
		HttpSession session = request.getSession();	// 세션 객체
		// 세션변수 userid 값 조회
		String userid = (String) session.getAttribute("userid");
		
		if(url.indexOf("list.do") != -1) {
			// 장바구니 금액 합계
			int sumMoney = dao.sumMoney(userid);
			// 배송료 계산
			int fee = sumMoney >= 50000 ? 0 : 2500;
			// 합계금액(장바구니 금액 합계 + 배송료)
			int sum = sumMoney + fee;
			// 맵에 저장(위 3개 저장)
			Map<String, Object> map = new HashMap<>();
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);
			map.put("sum", sum);
			request.setAttribute("map", map);
			
			List<CartVO> items = dao.cartList(userid);	// 장바구니 목록
			request.setAttribute("list", items);	// 출력을 위해 저장
			String page = "/shop/cart_list.jsp";	// 출력 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		// 장바구니에 담기
		} else if(url.indexOf("insert.do") != -1) {
			// 아이디가 없으면 로그인 페이지로 이동
			if(userid == null) {
				response.sendRedirect(path + "/shop/login.jsp");
				
			// 로그인한 사용자이면 장바구니 테이블에 추가 처리 후 목록으로
			} else {
				CartVO vo = new CartVO();
				vo.setUserid(userid);
				vo.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
				vo.setAmount(Integer.parseInt(request.getParameter("amount")));
				dao.cartInsert(vo);
				response.sendRedirect(path + "/cart_servlet/list.do");
			}
			
		// 장바구니 비우기
		} else if(url.indexOf("deleteAll.do") != -1) {
			dao.cartClear(userid);
			response.sendRedirect(path + "/cart_servlet/list.do");
			
		// 장바구니 개별상품 삭제
		} else if(url.indexOf("delete.do") != -1) {
			CartVO vo = new CartVO();
			// 사용자 아이디와 상품코드를 vo에 저장하여 넘김
			vo.setUserid(userid);
			vo.setCart_id(Integer.parseInt(request.getParameter("cart_id")));
			dao.cartDel(vo);
			response.sendRedirect(path + "/cart_servlet/list.do");
			
		// 장바구니 값 수정
		} else if(url.indexOf("update.do") != -1) {
			String[] product_id = request.getParameterValues("product_id");
			String[] amount = request.getParameterValues("amount");
			String[] cart_id = request.getParameterValues("cart_id");
			for(int i = 0; i < product_id.length; i++) {
				CartVO vo = new CartVO();
				vo.setCart_id(Integer.parseInt(cart_id[i]));
//				vo.setUserid(userid);
//				vo.setProduct_id(Integer.parseInt(product_id[i]));
				vo.setAmount(Integer.parseInt(amount[i]));
				dao.cartUpdate(vo);
			}
			response.sendRedirect(path + "/cart_servlet/list.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}













