<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="${path }/include/jquery-3.3.1.min.js"></script>
</head>
<body>
<%-- ${변수 or 식} --%>
${2 + 5 }<br>
${4 / 5 }<br>
${7 mod 5 }<br>
${2 < 3 }<br>
${3.1 le 3.2 }<br> <!-- less 작다 -->
${(5 > 3) ? 5 : 3 }<br>	<!-- 삼항연산자 -->

</body>
</html>