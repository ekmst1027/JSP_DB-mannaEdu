package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	public void mailSender(EmailVO vo) throws Exception {
		String host = "smtp.gmail.com";	// smtp 서버 주소
		// gmail id, @gmail.com은 입력하지 않음
		String username = "지메일아이디";
		String password = "지메일비밀번호";
		int port = 587;
		
		String senderMail = vo.getSenderMail();
		String senderName = vo.getSenderName();
		String recipient = vo.getReceiveMail();	// 받는 사람의 메일주소
		String subject = vo.getSubject();	// 제목
		String body = vo.getMessage();	// 메일 본문
		
		// 정보를 담기 위한 객체 생성
		Properties props = System.getProperties();
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		// Session 생성
		Session session = Session.getDefaultInstance(
			props, new javax.mail.Authenticator() {
				String un = username;
				String pw = password;
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(un, pw);
				}
			});
		
		session.setDebug(true);	// for debug
		Message mimeMessage = new MimeMessage(session);	// MimeMessage
		
		// 이메일 발신
		// (이메일주소는 폼에 입력한 이메일 주소가 아닌 gmail 주소로 표시됨)
		mimeMessage.addFrom(new InternetAddress[] {
				new InternetAddress(senderMail, senderName)
		});
		
		// 수신자 .TO .CC(참조) .BCC(숨은참조)
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);
		
	}
}
