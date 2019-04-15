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
	<h2>설문조사 결과</h2>
	<table border="1">
		<tr align="center">
			<th>문항</th>
			<th>응답수</th>
			<th>응답비율</th>
		</tr>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.num }</td>
				<td>${vo.sum_num }</td>
				<td>${vo.rate }%</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/jsp02/survey_servlet/input.do">설문메인화면</a>
</body>
</html>