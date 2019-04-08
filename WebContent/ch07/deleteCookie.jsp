<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// new Cookie("쿠키변수명", ""); 쿠키가 삭제됨
	Cookie cookie = new Cookie("id", "");
	cookie.setMaxAge(0);	// 즉시 삭제
	// cookie.setMaxAge(-1);	// 브라우저를 닫을 때 삭제
	response.addCookie(cookie);
	// pwd 쿠키변수 삭제
	Cookie cookie2 = new Cookie("pwd", "");
	cookie2.setMaxAge(0);
	response.addCookie(cookie2);
%>
쿠키가 삭제되었습니다.
<a href="useCookie.jsp">쿠키 확인</a>
</body>
</html>