<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<h2>이메일 보내기</h2>
	<form method="post" action="${path }/email_servlet/send.do">
		발신자 이름 : <input name="senderName"><br>
		발신자 이메일주소 : <input name="senderMail"><br>
		수신자 이메일주소 : <input name="receiveMail"><br>
		제목 : <input name="subject"><br>
		내용 : <textarea rows="5" cols="80" name="message"></textarea><br>
		<input type="submit" value="전송">
	</form>
	<!-- 메일 전송 결과 확인 메시지 출력 -->
	<c:if test="${param.message == 'ok' }">
		<span style="color:red;">이메일 발송이 완료되었습니다.</span>
	</c:if>
</body>
</html>