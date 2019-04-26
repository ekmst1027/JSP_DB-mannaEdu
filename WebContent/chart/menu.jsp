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
	<h2>차트 메뉴</h2>
	<a href="${path }/chart/chart01.jsp">구글차트(json)</a><br>
	<a href="${path }/chart/chart02.jsp">구글차트(db)</a><br>
	<a href="${path }/chart_servlet/jfree_chart1.do">JFreeChart(png)</a><br>
	<a href="${path }/chart_servlet/jfree_chart2.do">JFreeChart(pdf)</a><br>
</body>
</html>