package survey;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import survey.dao.SurveyDAO;
import survey.vo.SurveyResultVO;
import survey.vo.SurveySummaryVO;
import survey.vo.SurveyVO;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();	// 컨텍스트 패스
		String url = request.getRequestURI();	// 요청한 주소
		SurveyDAO dao = new SurveyDAO();
		// 설문입력 페이지로 이동
		if(url.indexOf("input.do") != -1) {
			SurveyVO vo = dao.viewQuestion(1);	// 1번 문제 리턴
			request.setAttribute("vo", vo);	// request 영역에 저장
			// 설문 응답 입력 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("/survey/survey_input.jsp");
			rd.forward(request, response);
			
		} else if(url.indexOf("insert.do") != -1) {
			int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));
			int num = Integer.parseInt(request.getParameter("num"));
			// 응답내용을 저장할 vo 생성
			SurveyResultVO vo = new SurveyResultVO();
			vo.setSurvey_idx(survey_idx);
			vo.setNum(num);
			
			// 응답내용을 테이블에 저장
			dao.insertSurvey(vo);
			response.sendRedirect(path + "/survey/input_result.jsp");
			
		} else if(url.indexOf("survey_result.do") != -1) {
			int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));
			List<SurveySummaryVO> items = dao.listSummary(survey_idx);
			request.setAttribute("list", items);
			RequestDispatcher rd = request.getRequestDispatcher("/survey/survey_result.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
