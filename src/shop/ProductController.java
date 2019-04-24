package shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.ProductDAO;
import shop.vo.ProductVO;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// get방식 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();	// 요청받은 주소
		String path = request.getContextPath();	// 컨텍스트 패스
		ProductDAO dao = new ProductDAO();
		
		if(url.indexOf("list.do") != -1) {
			List<ProductVO> items = dao.listProduct();	// 상품 목록
			request.setAttribute("list", items);	// 저장
			// 포워딩(화면전환, 출력)
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_list.jsp");
			rd.forward(request, response);
			
		} else if(url.indexOf("detail.do") != -1) {
			// 클릭한 상품코드
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			// 상세정보를 vo에 받음
			ProductVO vo = dao.detailProduct(product_id);
			// 출력시키기 전에 저장
			request.setAttribute("vo", vo);
			// 포워딩되면서 출력됨
			RequestDispatcher rd = request.getRequestDispatcher("/shop/product_detail.jsp");
			rd.forward(request, response);
		
		} 
	}

	// post방식 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
