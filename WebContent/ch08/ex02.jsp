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
	<%
	// String name = request.getParameter("name");
	// out.println("이름 : " + name);
	%>
	
	<!-- action 속성이 없으면 현재 페이지로 값을 전달함 -->
	<form method="get">
		이름 : <input name="name" value="${param.name }">
		<input type="submit" value="확인">
	</form>
	
	이름 : ${param.name }	 <!-- null 처리 포함됨 -->
</body>
</html>