<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 세션 수정 method는 별로 없음
// session.setAttribute("id", "park");
Enumeration<String> attr = session.getAttributeNames();
while(attr.hasMoreElements()) {
	// 다음 요소
	String key = attr.nextElement();
	// session.getAttribute("세션변수명") : 세션값 조회
	Object value = session.getAttribute(key);
	out.println("세션변수명 : " + key);
	out.println(", 세션값 : " + value + "<br>");
}

String id = (String) session.getAttribute("id");
Integer age = (Integer) session.getAttribute("age");
Object a = session.getAttribute("age");
Double height = (Double) session.getAttribute("height");
%>

아이디 : ${sessionScope.id }<br>
비번 : ${sessionScope.passwd } <br>
나이 : ${sessionScope.age }<br>
키 : ${sessionScope.height }<br>
세션ID : <%=session.getId() %><br>

<a href="deleteSession.jsp">세션 삭제</a>
</body>
</html>