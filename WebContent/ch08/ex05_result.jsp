<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<h2>자바코드 방식</h2>
<%
ArrayList<String> items = (ArrayList<String>)request.getAttribute("items");
for(String str : items) {
	out.println(str + "<br>");
}
%>

<h2>EL&JSTL 방식</h2>
<!-- forEach var="개별변수" items="집합변수" -->
<c:forEach var="fruit" items="${items }">
	${fruit }<br>
</c:forEach>
</body>
</html>