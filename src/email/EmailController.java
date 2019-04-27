package email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 폼에서 입력한 값을 vo에 저장
		String senderName = request.getParameter("senderName");
		String senderMail = request.getParameter("senderMail");
		String receiveMail = request.getParameter("receiveMail");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		EmailVO vo = new EmailVO();
		vo.setSenderName(senderName);
		vo.setSenderMail(senderMail);
		vo.setReceiveMail(receiveMail);
		vo.setSubject(subject);
		vo.setMessage(message);
		
		// 이메일 전송
		EmailService service = new EmailService();
		try {
			service.mailSender(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 폼으로 되돌아가기
		response.sendRedirect(request.getContextPath() + "/email/write.jsp?message=ok");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
