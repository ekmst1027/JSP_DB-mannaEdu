package chart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/chart_servlet/*")
public class ChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.println(url);
		if(url.indexOf("cart_money_list.do") != -1) {
			ChartService service = new ChartService();
			// 차트에 입력할 데이터를 json object로 받아옴
			JSONObject json = service.getChartData();
			request.setAttribute("data", json);
			System.out.println("data : " + json);
			String page = "/chart/chart02_result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
