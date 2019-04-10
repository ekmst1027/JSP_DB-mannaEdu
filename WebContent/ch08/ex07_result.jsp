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
<h2>Java 코드 방식</h2>
<%
MemberVO vo = (MemberVO)request.getAttribute("vo");
if(vo != null) {
	out.println("이름 : " + vo.getName() + "<br>");
	out.println("아이디 : " + vo.getUserid() + "<br>");
	out.println("비번 : " + vo.getPasswd() + "<br>");
} else {
	out.println("출력할 값이 없습니다.");
}
%>
<h2>EL 방식</h2>
이름 : ${vo.name }<br>	<!-- getter가 호출됨 -->
아이디 : ${vo.userid }<br>
비번 : ${vo.passwd }<br>
</body>
</html>