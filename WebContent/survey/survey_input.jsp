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
	<h3>[설문조사]</h3>
	<br>
	<h3>${vo.question }</h3>
	<form method="post" action="${path }/survey_servlet/insert.do">
		<input type="radio" name="num" value="1" checked>${vo.ans1 }<br>
		<input type="radio" name="num" value="2">${vo.ans2 }<br>
		<input type="radio" name="num" value="3">${vo.ans3 }<br>
		<input type="radio" name="num" value="4">${vo.ans4 }<br>
		<input type="hidden" name="survey_idx" value="${vo.survey_idx }">
		<input type="submit" value="투표">
	</form>
</body>
</html>