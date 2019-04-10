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
<h2>자바코드 방식</h2>
<%	// session.getAttribute("세션변수명")
String name = (String)session.getAttribute("name");
int age = (int)session.getAttribute("age");
String job = (String)session.getAttribute("job");
%>
이름 : <%=name %>
나이 : <%=age %>
직업 : <%=job %>

<h2>EL 방식</h2>
이름 : ${sessionScope.name }<br>
나이 : ${sessionScope.age }<br>
직업 : ${sessionScope.job }<br>
</body>
</html>