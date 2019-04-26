package chart;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import shop.dao.CartDAO;
import shop.vo.CartVO;

@WebServlet("/chart_servlet/*")
public class ChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.println(url);
		
		if(url.indexOf("chart_money_list.do") != -1) {
			ChartService service = new ChartService();
			// 차트에 입력할 데이터를 json object로 받아옴
			JSONObject json = service.getChartData();
			request.setAttribute("data", json);
			System.out.println("data : " + json);
			String page = "/chart/chart02_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if(url.indexOf("jfree_chart1.do") != -1) {
			CartDAO cartDao = new CartDAO();	// 장바구니 dao 객체
			ChartService service = new ChartService();	// 차트 서비스 객체
			List<CartVO> list = cartDao.cartMoney();	// 장바구니 금액 통계
			// 차트를 생성한 후 이미지로 변환하는 코드
			service.makeImage(list, response);
			
		} else if(url.indexOf("jfree_chart2.do") != -1) {
			ChartService service = new ChartService();	// 차트 서비스 객체
			String message = service.makePdf();	// pdf 문서가 생성됨
			request.setAttribute("message", message);
			String page = "/chart/jfree_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
