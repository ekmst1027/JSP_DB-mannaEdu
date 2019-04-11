<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!-- JSTL(Jsp Standard Tag Library) -->
<!-- prefix="태그의 접두어" uri="태그의 uri" -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자바코드 방식</h2>
	<%
	// pageContext.setAttribute("num", 100);
	// out.println(pageContext.getAttribute("num"));
	// request.getHeaderNames() : 헤더 이름
	Enumeration<String> headerNames = request.getHeaderNames();
	// hasMoreElements() : 다음 요소가 있으면 true
	while(headerNames.hasMoreElements()) {
		String key = (String)headerNames.nextElement();
		String value = request.getHeader(key);
		out.println(key + " : " + value + "<br>");
	}
	%>
<!-- 	EL의 내장변수 -->
<%-- 	${cookie } --%>
<%-- 	${header } --%>

	<h2>JSTL방식</h2>
	<c:forEach var="h" items="${header }">
		${h.key } => ${h.value }<br>
	</c:forEach>
<%-- 	<c:set var="num" value="100" /> --%>
<%-- 	<c:out var="${num }" /> --%>
	
	<!-- set var="변수명" value="값" -->
	<c:set var="browser" value="${header['user-agent'] }" />
	<hr>
	<!-- out value="출력할 값" -->
	<c:out value="${browser }" />
	<!-- remove var="변수" : 변수를 삭제함 -->
	<hr>
	<c:remove var="browser" />
	<c:out value="${browser }" />
	
</body>
</html>
