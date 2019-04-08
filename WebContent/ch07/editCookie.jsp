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
		// addCookie(new Cookie("변수명", "새로운 값"))
		response.addCookie(new Cookie("id", "park"));
	%>
	아이디 : ${cookie.id.value }
	<br>
	쿠키가 변경되었습니다.
	<br>
	<a href="useCookie.jsp">쿠키 확인</a>
</body>
</html>