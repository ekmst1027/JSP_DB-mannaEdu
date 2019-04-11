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
	<!-- set var="변수명" value="값" -->
	<c:set var="country" value="Korea" />
	<!-- if test="조건식" -->
	<c:if test="${country != null }">
		국가명 : ${country }<br>
		국가명 : <c:out value="${country }" /><br>
	</c:if>
	<%
	int[] nums = {10, 70, 80, 50, 40, 30, 20};
	%>
	<c:set var="num" value="<%=nums %>" />
	<!-- var="개별값" items="집합" -->
	<c:forEach var="n" items="${num }">
		${n },
	</c:forEach>
	<br>
	<!-- choose ~ when ~ otherwise -->
	<c:set var="season" value="겨울" />
	<c:choose>
		<c:when test="${season == '봄' }">
			<img src="${path }/images/spring.jpg">
		</c:when>
		<c:when test="${season == '여름' }">
			<img src="${path }/images/summer.jpg">
		</c:when>
		<c:when test="${season == '가을' }">
			<img src="${path }/images/autumn.jpg">
		</c:when>
		<c:when test="${season == '겨울' }">
			<img src="${path }/images/winter3.jpg">
		</c:when>
		<c:otherwise>기타 모든 경우...</c:otherwise>
	</c:choose>
</body>
</html>