<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
MemberVO vo = new MemberVO();
vo.setUserid("kim");
vo.setName("김철수");
vo.setPasswd("1234");
request.setAttribute("vo", vo);
%>
<jsp:forward page="ex07_result.jsp"></jsp:forward>
</body>
</html>