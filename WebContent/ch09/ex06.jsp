<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- format 태그(날짜, 숫자의 출력 형식 지정) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Date date = new Date();
out.println(date + "<br>");
%>
<c:set var="date" value="<%=new Date() %>" />
${date }<br>
날짜만 출력<br>
<fmt:formatDate value="${date }" /><br>
<fmt:formatDate value="${date }" type="date" /><br>
시간만 출력<br>
<fmt:formatDate value="${date }" type="time" /><br>
패턴을 사용할 경우<br>
<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss" /><br>
<fmt:formatDate value="${date }" dateStyle="full" /><br>
<fmt:formatDate value="${date }" dateStyle="long" /><br>
<fmt:formatDate value="${date }" dateStyle="medium" /><br>
<fmt:formatDate value="${date }" dateStyle="short" /><br>

<c:set var="num" value="123456789" />
${num }<br>
천단위 컴머<br>
<fmt:formatNumber value="${num }" groupingUsed="true" /><br>
<!-- 패턴을 사용하여 소수점 처리 및 컴머 위치 조정 -->
<fmt:formatNumber value="${num }" pattern="#,####.###" /><br>
<!-- 소수 빈자리를 0으로 채움 -->
<fmt:formatNumber value="${num }" pattern="#,####.000" /><br>
</body>
</html>