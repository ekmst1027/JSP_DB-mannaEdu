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
컨텍스트 패스 : ${path }
	<%
	// new Cookie("쿠키변수명", "값")
	Cookie cookie = new Cookie("id", "kim");
	Cookie cookie2 = new Cookie("pwd", "1234");
	// 쿠키는 스트링만 저장 가능
	Cookie cookie3 = new Cookie("age", "20");
	// 쿠키의 유효시간 설정(초단위)
	cookie.setMaxAge(10);
	// 쿠키가 클라이언트에 저장됨
	response.addCookie(cookie);
	response.addCookie(cookie2);
	%>
	쿠키가 생성되었습니다.
	<br>
	<a href="useCookie.jsp?name=kim">쿠키 확인</a>
	
</body>
</html>