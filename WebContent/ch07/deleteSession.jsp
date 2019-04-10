<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%
// 세션에 저장된 개별값 삭제
// session.removeAttribute("id");
// session.removeAttribute("age");
// 모든 세션변수 삭제, 세션 초기화
session.invalidate();
%>

<%-- 아이디 : ${sessionScope.id }<br> --%>
<%-- 비밀번호 : ${sessionScope.passwd }<br> --%>
<%-- 나이 : ${sessionScope.age }<br> --%>
<%-- 키 : ${sessionScope.height }<br> --%>
세션ID : <%=session.getId() %><br> 
<a href="viewSession.jsp">세션 확인</a>
</body>
</html>