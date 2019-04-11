<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 함수 태그 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 변수 선언 set var="변수명" value="값" -->
<c:set var="str" value="hello jsp" />
<%
// pageContext.setAttribute("str", "hello jsp");
%>
<!-- fn:함수 -->
문자열의 길이<br>
${fn:length(str) }<br>
<%
int len=((String)pageContext.getAttribute("str")).length();
%>
<%=len %>
키워드의 인덱스<br>
${fn:indexOf(str, 'jsp') }<br>
문자열 내용 변경<br>
${fn:replace(str, 'jsp', 'java') }<br>
</body>
</html>